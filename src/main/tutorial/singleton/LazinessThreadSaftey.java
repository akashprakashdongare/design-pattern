package main.tutorial.singleton;

class LazySingleton{
    private static LazySingleton instance;

    private LazySingleton(){ System.out.println("initializing a lazy singleton.."); }

    public synchronized static LazySingleton getInstance(){
        if(null == instance)
          instance = new LazySingleton();

        return instance;
    }

    // double-check locking

    public LazySingleton getInstanceDoubleCheck(){
        if(null == instance){
            synchronized(LazySingleton.class){
                if(null == instance)
                   instance = new LazySingleton();
            }
        }
        return instance;
    }
}

public class LazinessThreadSaftey {
    public static void main(String[] args) {
        
    }
}
