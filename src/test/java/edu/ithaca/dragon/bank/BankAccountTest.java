package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        /**
         * Equivalent classes are invalid prefixes and invalid domains.
         * The latter can be separated into invalid top-level domains and invalid website name.
         * Boundary cases are testing the minimum possible length or cases that test the first
         * and last characters of the email prefix. 
         */
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse(BankAccount.isEmailValid(""));

        //Email prefix testing
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));

        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));

        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));

        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));

        //Email domain testing
        assertFalse(BankAccount.isEmailValid("abc@mail.c"));
        assertTrue(BankAccount.isEmailValid("abc@mail.cc"));

        assertFalse(BankAccount.isEmailValid("abc@mail#archive.com"));
        assertTrue(BankAccount.isEmailValid("abc@mail-archive.com"));

        assertFalse(BankAccount.isEmailValid("abc@mail"));
        assertTrue(BankAccount.isEmailValid("abc@mail.org"));

        assertFalse(BankAccount.isEmailValid("abc@mail..com"));
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}