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
        
        if (email.length() < 6){
            return false;
        }
        else if (email.indexOf('@') == -1 || email.indexOf('.')==-1){
            return false;
        }
        else if(email.indexOf('@')==0 || email.indexOf('@')==email.length()-1){
            return false;
        }
        else if(email.indexOf('.')==0 || email.indexOf('.')==email.length()-1){
            return false;
        }
        else if(email.indexOf('@')!=email.lastIndexOf('@')){
            return false;
        }
        else {

            String[] atArr = email.split("@");
            String[] dotArr = email.split("\\.");
            
            
            if(atArr[0].charAt(atArr[0].length()-1)<46 || atArr[0].charAt(atArr[0].length()-1)>122){
                return false;
            }
            if(dotArr[1].length()<2){
                return false;
            }
            for(int j=0;j<atArr[1].length();j++){
                if (atArr[1].charAt(j)<36 ||atArr[1].charAt(j)>122){
                    return false;
                }
            }
            for(int j=0;j<atArr[0].length();j++){
                if (atArr[0].charAt(j)<36 ||atArr[0].charAt(j)>122){
                    return false;
                }
            }
            return true;
        }
    }
}
