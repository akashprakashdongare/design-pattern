package main.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class BasicSingleton implements Serializable{
    private static final BasicSingleton INSTANCE = new BasicSingleton();

    private BasicSingleton(){ System.out.println("Private constructor..");}

    public static BasicSingleton getInstance(){ return INSTANCE; }

    private int value = 0; 

    public void setValue(int value){ this.value = value; }
    public int getValue(){ return value; }
    
    protected Object readResolve(){ return INSTANCE; }

    
}

public class BasicSingletonExample {

    static void saveToFile(BasicSingleton basicSingleton, String filename) throws Exception{
        try(FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)){
                out.writeObject(basicSingleton);
            }
    }

    static BasicSingleton readFromFile(String filename) throws Exception{
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)){
                return (BasicSingleton)in.readObject();
            }
    }
    public static void main(String[] args) throws Exception {

        // 1. reflection
        // 2. serialization

        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(123);
        //System.out.println(singleton.getValue());

        String filename = "E:\\test-files\\Singleton.bin";
        saveToFile(singleton, filename);

        singleton.setValue(222);
        BasicSingleton singleton2 = readFromFile(filename);
        
        System.out.println(singleton == singleton2);
        System.out.println(singleton.getValue());
        System.out.println(singleton2.getValue());

    }
}
