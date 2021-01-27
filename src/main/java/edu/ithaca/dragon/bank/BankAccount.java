package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        String[] arr = email.split(".");
        if (email.indexOf('@') == -1 || email.indexOf('.')==-1){
            return false;
        }
        else if(email.length() <= 5){
            return false;
        }
        else if(arr[1].length()!=3||arr[0].indexOf('@')==0||arr[0].indexOf('@')==arr[0].length()-1){
            return false;
        } 
        else {
            for(int i=0;i<arr[1].length();i++){
                if (arr[1].charAt(i)<97 ||arr[1].charAt(i)>122){
                    return false;
                }
            }
            return true;
        }
    }
}
