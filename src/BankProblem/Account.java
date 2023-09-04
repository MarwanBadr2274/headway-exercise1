package BankProblem;

class Account {
    private int accountNumber;
    private String type;
    private String customerID;
    private String customerName;
    private double balance;
    private String currency;
    private String linkedCardNumber;
    private Bank bank;

    public Account(int accountNumber, String type, String customerID, String customerName, double balance, String currency, String linkedCardNumber, Bank bank) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.customerID = customerID;
        this.customerName = customerName;
        this.balance = balance;
        this.currency = currency;
        this.linkedCardNumber = linkedCardNumber;
        this.bank = bank;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLinkedCardNumber() {
        return linkedCardNumber;
    }

    public Bank getBank() {
        return bank;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", type='" + type + '\'' +
                ", customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", linkedCardNumber='" + linkedCardNumber + '\'' +
                ", bank=" + bank.getName() +
                '}';
    }
}
