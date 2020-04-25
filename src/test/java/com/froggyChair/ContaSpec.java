package com.froggyChair;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}