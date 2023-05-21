package main.singleton;

import java.io.File;
import java.io.IOException;

class StaticBlockSingleton{
    private StaticBlockSingleton() throws IOException{
        System.out.println("Singleton is initializing..");
        File.createTempFile(".", ".");
    }

    private static StaticBlockSingleton INSTANCE;

    static{
        try{
            INSTANCE = new StaticBlockSingleton();
        } catch(Exception e){
            System.out.println("failed to create singleton");
        }
    }

    public StaticBlockSingleton getInstance(){ return INSTANCE; }
}

public class StaticBlock {
    public static void main(String[] args) {
        
    }
}
