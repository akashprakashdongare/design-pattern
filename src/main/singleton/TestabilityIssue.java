package main.singleton;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

class SingletonDatabase{
    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instanceCount = 0;
    public static int getInstanceCount(){ return instanceCount; }
    private SingletonDatabase(){
        instanceCount++;
        System.out.println("Initializing database..");
        try{
            File f = new File(
                              SingletonDatabase.class.getProtectionDomain()
                                    .getCodeSource().getLocation().getPath()
                                    );
            System.out.println(f.getPath());
            Path fullPath = Paths.get(f.getPath(), "capitals.txt");
            List<String> lines = Files.readAllLines(fullPath);

            lines.stream().forEach(line ->{
                String[] split = line.split(" ");
                capitals.put(split[0], Integer.parseInt(split[1]));
            });

        } catch(Exception e){

        }
    }
    private static final SingletonDatabase INSTANCE = new SingletonDatabase();
    public static SingletonDatabase getInstance(){ return INSTANCE; }
    public int getPopulations(String name){
        return capitals.get(name);
    }
}

class SingletonRecordFinder{

    public int getTotalPopulaton(List<String> names){
        int result = 0;
        for(String name : names){
            result += SingletonDatabase.getInstance().getPopulations(name);
        }
        return result;
    }
}
public class TestabilityIssue {
    public static void main(String[] args) {
        SingletonRecordFinder rf = new SingletonRecordFinder();
        List<String> names = Arrays.asList("Saudi Arabia", "Iran", "China", "India", "Japan", "Shri Lanka");
        int populations = rf.getTotalPopulaton(names);
        System.out.println(populations);
    }
}
