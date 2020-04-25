package com.froggyChair;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContaSpec {
    @Test
    // This tests verifies if a new account starts empty
    public void accountStartsEmpty(){
        ContaMagica conta = newContaMagica();
        assertTrue(conta.saldo() == 0, () -> "A conta deve começar com saldo = 0");
    }
}