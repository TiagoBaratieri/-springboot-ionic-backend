package com.cursospring.baratierisale.dto;

import com.cursospring.baratierisale.services.validation.InsertClient;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@InsertClient
public class ClientNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotEmpty(message = "Mandatory filling")
    @Length(min = 5, max = 120, message = "The length must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message = "Mandatory filling")
    @Email(message = "Email invalid")
    private String email;

    @NotEmpty(message = "Mandatory filling")
    private String cpfOuCnpj;
    private Integer type;

    @NotEmpty(message = "Mandatory filling")
    private String publicPlace;

    @NotEmpty(message = "Mandatory filling")
    private String number;
    private String complement;
    private String district;

    @NotEmpty(message = "Mandatory filling")
    private String cep;

    @NotEmpty(message = "Mandatory filling")
    private String telephone1;
    private String telephone2;
    private String telephone3;

    private Integer cityId;

    public ClientNewDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone3() {
        return telephone3;
    }

    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
