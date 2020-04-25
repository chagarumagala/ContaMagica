package com.froggyChair;

public class ContaMagica {
    private double saldo;
    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;

    public ContaMagica(){
        this.saldo = 0;
    }
    public double getSaldo(){
        return saldo;
    }
    int getStatus()
    {
        return 1;
    }
    public void deposito(int valor) throws INVALID_OPER_EXCEPTION
    {
        if (valor<0)
        {
            throw new INVALID_OPER_EXCEPTION("valor invalido");
        }
    }
    public void retirada(int valor) throws INVALID_OPER_EXCEPTION
    {

    }
}