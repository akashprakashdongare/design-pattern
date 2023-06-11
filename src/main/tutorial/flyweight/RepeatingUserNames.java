package main.tutorial.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class User{
    public String fullname;

    public User(String fullname){ this.fullname = fullname; }
}
class User2{
    static List<String> strings = new ArrayList<>();
    public int[] names;

    public User2(String fullname){
         names = Arrays.stream(fullname.split(" "))
                       .mapToInt(s -> {
                            int idx = strings.indexOf(s);
                            if(idx != -1) return idx;
                            else {
                                strings.add(s);
                                return strings.size() - 1;
                            }
                       })
                       .toArray();
    }
    static Function<String, Integer> getOrAdd = (String s) -> {
        int idx = strings.indexOf(s);
        if(idx != -1) return idx;
        else {
            strings.add(s);
            return strings.size() - 1;
        }
    };
}
public class RepeatingUserNames {
    public static void main(String[] args) {
        User2 user = new User2("John Smith");
        User2 user2 = new User2("Jane Smith");
        System.out.println(user.strings);
    }
}
