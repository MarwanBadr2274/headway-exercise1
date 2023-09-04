package BankProblem;

import java.util.List;
import java.util.ArrayList;

class Bank {
    private List<Account> accounts;
    private String name;

    public Bank(String name){
        this.name = name;
        accounts = new ArrayList<Account>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        if(accounts.contains(account)){
            accounts.remove(account);
        }
        else{
            System.out.println("Account not found. Might have been deleted");
        }
    }

    public void depositMoney(Account account, double amount) {
        if(accounts.contains(account)){
            account.deposit(amount);
        }
        else{
            System.out.println("Account not found. Might have been deleted");
        }

    }

    public void withdrawMoney(Account account, double amount) {
        if(accounts.contains(account)) {
            account.withdraw(amount);
        }
        else{
            System.out.println("Account not found. Might have been deleted");
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getName(){
        return name;
    }


}
