package main.tutorial.solid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class Journal{
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;
    
    public void addEntrie(String text){
        entries.add("" + (++count) + ":" + text);
    }

    public void removeEntrie(int index){
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    //--------------------violation-------------------------------
    public void save(String filename) throws FileNotFoundException {
        try(PrintStream out = new PrintStream(filename)){
            out.println(toString());
        }
    }

    public void load(String filename){}

    public void load(URL url){}
    
}

class Persistance{

    public void saveToFile(Journal journal, String filename,boolean overwrite) throws FileNotFoundException{
        if(overwrite || new File(filename).exists()){
            try(PrintStream out = new PrintStream(filename)){
                out.println(journal.toString());
            }
        }
    }
}
public class SingleResponsiblility {
    public static void main(String[] args) throws Exception {
        Journal journal = new Journal();
        journal.addEntrie("I cried today");
        journal.addEntrie("I ate pizza today");
        System.out.println(journal);

        Persistance persistance = new Persistance();
        String filename = "E:\\test-files\\journal.txt";
        persistance.saveToFile(journal, filename, true);

       // Runtime.getRuntime().exec("notepad.exc"+filename);
    }
    
}
