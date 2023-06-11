package main.tutorial.memento;

class BankAccountToken{
    private int balance;
    public BankAccountToken(int balance){ this.balance = balance; }

    public int getBalance(){ return balance; }
}
class BankAccount{
    private int balance;
    public BankAccount(int balance){ this.balance = balance; }
    
    public BankAccountToken deposit(int amount){ 
        balance += amount;
        return new BankAccountToken(balance);
    }

    public void restore(BankAccountToken bat){ balance = bat.getBalance(); }


    @Override
    public String toString(){ return "{ balance : "+ balance + "}";}
}
public class MementoExample {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(100);
        BankAccountToken bankAccountToken1 = bankAccount.deposit(50);
        BankAccountToken bankAccountToken2 = bankAccount.deposit(25);

        System.out.println(bankAccount);

        // restore to bankAccountToken1

        bankAccount.restore(bankAccountToken1);
        System.out.println(bankAccount);

        bankAccount.restore(bankAccountToken2);
        System.out.println(bankAccount);
    }
}
