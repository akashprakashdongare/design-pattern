package main.tutorial.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

enum EnumBaseSingleton{
    
    INSTANCE;

    EnumBaseSingleton(){ value = 42; }

    private int value;

    public int getValue(){ return this.value; }

    public void setValue(int value){ this.value = value; }

}
public class EnumBase {

    static void saveToFile(EnumBaseSingleton basicSingleton, String filename) throws Exception{
        try(FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)){
                out.writeObject(basicSingleton);
            }
    }

    static EnumBaseSingleton readFromFile(String filename) throws Exception{
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)){
                return (EnumBaseSingleton)in.readObject();
            }
        }

    public static void main(String[] args) throws Exception {

        String filename = "myfile.bin";
        // EnumBaseSingleton singleton = EnumBaseSingleton.INSTANCE;
        // singleton.setValue(111);
        // saveToFile(singleton, filename);
        EnumBaseSingleton singleton2 = readFromFile(filename);
        System.out.println(singleton2.getValue());
    }
    
}
