package com.froggyChair;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContaSpec {
    //This tests verifies 
    @Test
    public void accountStartsEmpty(){
        ContaMagica conta = new ContaMagica();
        assertTrue(conta.getSaldo() == 0, () -> "A conta deve começar com saldo = 0");
    }
    // This test verifies 
}