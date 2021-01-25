package com.cursospring.baratierisale.services.validation;

import com.cursospring.baratierisale.dto.ClientNewDTO;
import com.cursospring.baratierisale.entities.enumS.ClientType;
import com.cursospring.baratierisale.repositories.ClientRepository;
import com.cursospring.baratierisale.services.exceptions.FieldMessage;
import com.cursospring.baratierisale.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class InsertClientValidator implements ConstraintValidator<InsertClient, ClientNewDTO> {


    public void initialize(InsertClient ann) {
    }
    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getType().equals(ClientType.PHYSICALPERSON.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","Disabled persons"));
        }


        if(objDto.getType().equals(ClientType.LEGALPERSON.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","Invalid legal person"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
