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
}