package BankProblem;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Customer {
    private String fullName;
    private String nationalID;
    private LocalDate dateOfBirth; //YYYY-MM-DD
    private List<Account> accounts;
    private Bank bank;

    public Customer(String fullName, String nationalID, LocalDate dateOfBirth, Bank bank) {
        this.fullName = fullName;
        this.nationalID = nationalID;
        this.dateOfBirth = dateOfBirth;
        this.accounts = new ArrayList<Account>();
        this.bank = bank;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        bank.addAccount(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        bank.removeAccount(account);
    }

    public void deposit(Account account, double amount) {
        bank.depositMoney(account, amount);
    }

    public void withdraw(Account account, double amount) {
        bank.withdrawMoney(account, amount);
    }

    public String getFullName() {
        return fullName;
    }

    public String getNationalID() {
        return nationalID;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Bank getBank() {
        return bank;
    }


}
