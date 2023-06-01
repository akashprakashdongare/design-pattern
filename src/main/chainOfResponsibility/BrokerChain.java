package main.chainOfResponsibility;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.omg.CosNaming.NameComponentHolder;

import main.chainOfResponsibility.Query.Argument;

// CQS
class Event<Args>{
    private int index = 0;
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler){
        int i = index;
        handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key){
        handlers.remove(key);
    }

    public void fire(Args args){
        for(Consumer<Args> handler : handlers.values()){
            handler.accept(args);
        }
    }
}
class Query{
    public String creatureName;
    enum Argument{
        ATTACK, DEFENSE;
    }
    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result){
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}
class Game{
    public Event<Query> queries = new Event<>();
}
class Creature1{
    private Game game;
    public String name;
    public int baseAttack, baseDefense;
    public Creature1(Game game, String name, int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }
    public Game getGame() { return game; }
    public String getName() { return name; }
    public int getBaseAttack() { return baseAttack; }
    public int getBaseDefense() { return baseDefense; }

    public int getAttach(){
        Query q = new Query(name, Argument.ATTACK, baseAttack);
        game.queries.fire(q);
        return q.result;
    }

    public int getDefence(){
        Query query = new Query(name, Argument.DEFENSE, baseDefense);
        game.queries.fire(query);
        return query.result;
    }
    @Override
    public String toString() {
        return "{name=" + name + ", attack=" + getAttach() + ", defense="
                + getDefence() + "}";
    }
    
}
class CreatureModifier1{
    protected Game game;
    protected Creature1 creature1;
    public CreatureModifier1(Game game, Creature1 creature1) {
        this.game = game;
        this.creature1 = creature1;
    }
}
class DoubleAttachModifier1 extends CreatureModifier1 implements AutoCloseable{
    private final int token;
    public DoubleAttachModifier1(Game game, Creature1 creature1) {
        super(game, creature1);
        token = game.queries.subscribe(q -> {
            if(q.creatureName.equals(creature1.name) &&
                       q.argument == Argument.ATTACK){
                        q.result *=2;
                       }
        });
    }
    @Override
    public void close() //throws Exception 
    {
        game.queries.unsubscribe(token);
    }
    
}
class IncreaseDefenseModifier1 extends CreatureModifier1{

    public IncreaseDefenseModifier1(Game game, Creature1 creature1) {
        super(game, creature1);
        game.queries.subscribe(q -> {
            if(q.creatureName.equals(creature1.name)
                    && q.argument == Argument.DEFENSE){
                        q.result += 3;
                    }
        });
    }
    
}
public class BrokerChain {
    public static void main(String[] args) {
        Game game = new Game();
        Creature1 gobline = new Creature1(game, "Strong Gobline", 2, 2);
        System.out.println(gobline);

        IncreaseDefenseModifier1 icm = new IncreaseDefenseModifier1(game, gobline);
        DoubleAttachModifier1 dcm = new DoubleAttachModifier1(game, gobline);

        System.out.println(gobline);
        System.out.println(gobline);
    }
}
