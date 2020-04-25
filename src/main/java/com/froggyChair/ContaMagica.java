package com.froggyChair;

public class ContaMagica {
    private double saldo;
    private int status;
    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;

    public ContaMagica(){
        this.saldo = 0;
        this.status = 0;
    }
    public double getSaldo(){
        return saldo;
    }
    int getStatus(){
        return status;
    }
    public void deposito(int valor) throws INVALID_OPER_EXCEPTION{
        saldo =  saldo + valor;
        if (valor<0){
            throw new INVALID_OPER_EXCEPTION("valor invalido");
        }
        if (status == SILVER){
            if (saldo>50000){
                status = GOLD;
            }
        }
        else if (status == GOLD){
            if (saldo>200000){
                status = PLATINUM;
            }
        }
    }
    public void retirada(int valor) throws INVALID_OPER_EXCEPTION{

    }
}