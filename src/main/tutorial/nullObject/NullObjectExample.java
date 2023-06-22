package main.tutorial.nullObject;

import java.lang.reflect.Proxy;

interface Log{
    public void info(String msg);
    public void warn(String msg);
}
class BankAccount{
    private Log log;
    private int balance;
    public BankAccount(Log log){
        this.log = log;
    }
    public void deposit(int amount){
        balance += amount;
        log.info("Deposited " + amount);
    }
}
class ConsoleLog implements Log{

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void warn(String msg) {
        System.out.println(" WARNING : " + msg);
    }
    
}

final class NullLog implements Log{

    @Override
    public void info(String msg) {

    }

    @Override
    public void warn(String msg) {
        
    }

}
public class NullObjectExample {
    @SuppressWarnings("uncheck")
    public static <T> T noOp(Class<T> itf){
        return (T) Proxy.newProxyInstance(
            itf.getClassLoader(), 
            new Class<?>[]{itf}, 
            ((proxy, method, args) ->{
                if(method.getReturnType().equals(Void.TYPE))
                return null;
                else
                return method.getReturnType().getConstructor().newInstance();
            })
            );
    }
    public static void main(String[] args) {
        Log log = noOp(Log.class);
        //null; 
        // new NullLog();
        BankAccount bankAccount = new BankAccount(log);
        bankAccount.deposit(100);
    }
}
