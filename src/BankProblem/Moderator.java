package BankProblem;

import java.time.LocalDate;

public class Moderator {
    public static void main(String[] args){
        //Creates a new bank
        Bank HSBC = new Bank("HSBC");

        /////////The customer class initiates everything, trying to match real life scenarios/////////

        ////Customer, "Marwan", wants to open an account at bank, "HSBC"

        //Link customer to the bank using "bank" argument in constructor
        Customer marwan = new Customer("Marwan Badr", "MB2277", LocalDate.of(2000,6,19), HSBC);

        //Create a new account with customer's data and specify a bank using "bank" argument in constructor
        Account marwanCurrentAccountHSBC = new Account(123123, "Current", marwan.getNationalID(), marwan.getFullName(), 3040.23, "USD", "1874263509098786", HSBC);

        //Add newly created account to the bank
        marwan.addAccount(marwanCurrentAccountHSBC);
        System.out.println(marwan.getAccounts());
        System.out.println(HSBC.getAccounts());


        ////Customer, "Marwan", wants to delete an account at bank, "HSBC"

        //Remove account from customer and bank records
        marwan.removeAccount(marwanCurrentAccountHSBC);
        System.out.println(marwan.getAccounts());
        System.out.println(HSBC.getAccounts());


        ////Customer, "Marwan", wants to deposit money at his account at bank, "HSBC"

        //Deposit money using deposit method from Customer class
        marwan.deposit(marwanCurrentAccountHSBC, 240.43);
        System.out.println(marwan.getAccounts());
        System.out.println(HSBC.getAccounts());


        ////Customer, "Marwan", wants to withdraw money from his account at bank, "HSBC"

        //Withdraw money using withdraw method from Customer class
        marwan.withdraw(marwanCurrentAccountHSBC, 240.43);
        System.out.println(marwan.getAccounts());
        System.out.println(HSBC.getAccounts());


    }
}
