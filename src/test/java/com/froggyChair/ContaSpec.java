package com.froggyChair;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.InvalidAlgorithmParameterException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaSpec {
    //This tests verifies if a newly create account is empty
    @Test
    public void accountStartsEmpty(){
        ContaMagica conta = new ContaMagica();
        assertTrue(conta.getSaldo() == 0, () -> "A conta deve começar com saldo = 0");
    }
    // This test verifies if it is possible to deposit a negative number
    @Test
    public void tryNegativeDeposit(){
        assertThrows(INVALID_OPER_EXCEPTION.class, ()->{
            ContaMagica conta = new ContaMagica();
            conta.deposito(-1000);
        });
    }
    //This test verifies if there are any changes when there is a deposit under 50k
    @Test
    public void under50K() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(1000);
        assertTrue(conta.getStatus() == 0, () -> "O status não deve mudar se a conta nunca passou de 50k");
    }
    public void underk50kEdge() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(49999);
        assertTrue(conta.getStatus() == 0, () -> "O status não deve mudar se a conta nunca passou de 50k");
    }

    //This test ensures that the switch from silver to gold is working properly
    @Test
    public void switchSilverGold() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(50000);
        assertTrue(conta.getStatus() == 1, () -> "O status deve ser igual 1 após a conta atingir 50k");
    }
    @Test
    public void switchSilverGoldEdge() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(200000);
        assertTrue(conta.getStatus() == 1, () -> "O status deve ser igual 1 após a conta atingir 50k");
    }
    //Makes sure silver account gets 0% interest in their deposits
    @Test
    public void silverGetsNothing() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(10);
        assertTrue(conta.getSaldo() == 10, () -> "Contas silver não ganham bonus nos depositos");
    }

    // Makes sures gold account gets 1% interest in their deposits
    @Test
    public void goldProfit() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(100000);
        conta.deposito(1000);
        assertTrue(conta.getSaldo() == 100000 + (1000 * 0.01), () -> "Contas gold ganham 1% nos depositos");
    }

    @Test
    public void goldPersistence() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(50000);
        conta.deposito(50000);
        assertTrue(conta.getStatus() == 1, () -> "Contas Gold com menos de 200k permanecem Gold");
    }
    @Test
    public void goldToPlatinum() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(200000);
        conta.deposito(0);
        assertTrue(conta.getStatus() == 2);
    }
    @Test
    public void ensurePlatinumBonus() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(200000);
        conta.deposito(100000);
        conta.deposito(100000);
        assertTrue(conta.getSaldo() == 200000 + (100000 * 1.01) + (100000 * 1.025));
    }

    @Test
    public void cannotGetNegativeCash(){
        assertThrows(INVALID_OPER_EXCEPTION.class, () -> {
            ContaMagica conta = new ContaMagica();
            conta.deposito(10);
            conta.retirada(-1);
        });
    }
    
    @Test 
    public void cannotGetMoreCashThanWhatYouHavE(){
        assertThrows(INVALID_OPER_EXCEPTION.class, () -> {
            ContaMagica conta = new ContaMagica();
            conta.deposito(10);
            conta.retirada(11);
        });
    }

    @Test 
    public void withdrawSilver() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(1000);
        conta.retirada(100);
        assertTrue(conta.getSaldo()==900,() -> "Conta deve conter 900 apos retirada");

    }

    @Test 
    public void fallGoldToSilver() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(50000);
        conta.retirada(26000);
        assertTrue(conta.getStatus()==0,() -> "Conta deve cair para Silver");

    }

    @Test 
    public void fallPlatinumToGold() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(200000);
        conta.deposito(0);
        conta.retirada(110000);
        assertTrue(conta.getStatus()==1,() -> "Conta deve cair para Gold");

    }

    @Test 
    public void doubleFall() throws INVALID_OPER_EXCEPTION{
        ContaMagica conta = new ContaMagica();
        conta.deposito(200000);
        conta.deposito(0);
        conta.retirada(190000);
        assertTrue(conta.getStatus()==1,() -> "Conta deve cair para Gold mesmo tendo retirado o sificiente para ser Silver");

    }

}