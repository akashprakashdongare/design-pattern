package main.tutorial.chainOfResponsibility;

class Creature{
    public String name;
    public int attack,defence;
    public Creature(String name, int attack, int defence){
        this.name = name;
        this.attack = attack;
        this.defence = defence;
    }
    @Override
    public String toString() {
        return "{name=" + name + ", attack=" + attack + ", defence=" + defence + "}";
    }
}
class CreatureModifier{
    protected Creature creature;
    protected CreatureModifier next;
    public CreatureModifier(Creature creature){ this.creature = creature; }
    public void add(CreatureModifier cm){
        if(null != next){
            next.add(cm);
        } else {
            next = cm;
        }
    }
    public void handle(){ if(null != next) next.handle(); }
}
class DoubleAttackModifier extends CreatureModifier{
    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }
    @Override
    public void handle() {
        System.out.println("Doubling " + creature.name + "'s attack");
        creature.attack *= 2;
        super.handle();
    }
}
class IncreaseDefenseModifier extends CreatureModifier{
    public IncreaseDefenseModifier(Creature creature) {
        super(creature);
    }
    @Override
    public void handle() {
        System.out.println("Increasing " + creature.name + "'s defense");
        creature.defence += 3;
        super.handle();
    }
}
class NoBonusesModifier extends CreatureModifier{
    public NoBonusesModifier(Creature creature) {
        super(creature);
    }
    @Override
    public void handle(){
        System.out.println("No bonuses for you!");
    }
    
}
public class MethodChain {
    public static void main(String[] args) {
        Creature goblin = new Creature("Goblin", 2, 2);
        CreatureModifier root = new CreatureModifier(goblin);

        root.add(new NoBonusesModifier(goblin));

        System.out.println("Let's double goblian attack..");
        root.add(new DoubleAttackModifier(goblin));

        System.out.println("Let's increase goblian defence..");
        root.add(new IncreaseDefenseModifier(goblin));

        root.handle();
        System.out.println(goblin);
    }
}
