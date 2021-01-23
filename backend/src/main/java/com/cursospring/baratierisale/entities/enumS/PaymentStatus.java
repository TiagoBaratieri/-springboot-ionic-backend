package com.cursospring.baratierisale.entities.enumS;

public enum PaymentStatus {

    PENDING(1,"Pendente"),
    PAID (3,"Quitado"),
    CANCELED(2,"Cancelado");

    private int cod;
    private String description;

    PaymentStatus(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(PaymentStatus x : PaymentStatus.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id invalido" + cod);
    }


}
