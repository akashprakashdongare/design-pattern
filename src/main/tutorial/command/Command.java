package main.tutorial.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class BankAccount{
    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount){
        balance += amount;
        System.out.println("Deposite " + amount
           + ", balance is now " + balance);
    }

    public boolean withdraw(int amount){
        if(balance - amount >= overdraftLimit){
            balance -= amount;
            System.out.println("Withdrew " + amount
                + ", balance is now " + balance);
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "{balance=" + balance + "}";
    }
    
}
interface CommandA{
    void call();
    void undo();
}
class BankAccountCommand implements CommandA{
    private BankAccount account;
    private boolean succeeded;
    public enum Action{
        DEPOSITE, WITHDREW;
    }
    private Action action;
    private int amount;
    public BankAccountCommand(BankAccount account, Action action, int amount){
        this.account = account;
        this.action = action;
        this.amount = amount;
    }
    @Override
    public void call() {
        switch(action){
            case DEPOSITE:
            succeeded = true;
             account.deposit(amount);
             break;
             case WITHDREW:
              succeeded = account.withdraw(amount);
              break;
        }
    }
    @Override
    public void undo() {
        if(!succeeded) return;
        switch(action){
            case DEPOSITE:
             account.withdraw(amount);
             break;
            case WITHDREW:
              account.deposit(amount);
              break;
        }
    }
}
public class Command {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
        System.out.println(ba);

        List<BankAccountCommand> commands = Arrays.asList(
            new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSITE, 100),
            new BankAccountCommand(ba, BankAccountCommand.Action.WITHDREW, 1000)
        );

        for(CommandA command : commands){
            command.call();
            System.out.println(ba);
        }

        Collections.reverse(commands);
        for(CommandA command : commands){
            command.undo();
            System.out.println(ba);
        }
    }
}
