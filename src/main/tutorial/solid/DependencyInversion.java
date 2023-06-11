package main.tutorial.solid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum Relationship{
    PARENT,
    CHILD,
    SIBLING
}

class Person{
    String name;

    public Person(String name){
        this.name = name;
    }
}

class PersonMapper{
    Person parent;
    Relationship relation;
    Person child;

    public PersonMapper(Person parent, Relationship relationship, Person child){
        this.parent = parent;
        this.relation = relationship;
        this.child = child;
    }
}

interface RelationshipBrowser{
    List<Person> findAllChildrenOf(String name);
}

// low-level
class Relationships implements RelationshipBrowser{
    private List<PersonMapper> relations = new ArrayList<>();

    public List<PersonMapper> getRelations(){
        return relations;
    }

    public void addParentAndChild(Person parent, Person child){
        relations.add(new PersonMapper(parent, Relationship.PARENT, child));
        relations.add(new PersonMapper(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
       return relations.stream()
                 .filter(pm -> name.equals(pm.parent.name)
                          && Relationship.PARENT.equals(pm.relation))
                 .map(pm -> pm.child)
                 .collect(Collectors.toList());
    }
}

//high-level
class Research{
    
    // public Research(Relationships relationships){
    //     List<PersonMapper> relations = relationships.getRelations();
    //     relations.stream()
    //              .filter(pm -> "John".equals(pm.parent.name)
    //                          && Relationship.PARENT.equals(pm.relation))
    //             .forEach(p -> {
    //                 System.out.println("John has a child called "+p.child.name);
    //             });
    // }

    public Research(RelationshipBrowser relationshipBrowser){
       List<Person> childrens = relationshipBrowser.findAllChildrenOf("John");
       for(Person child : childrens)
       System.out.println("John has a child called " + child.name);
    }
}
public class DependencyInversion {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        Research research = new Research(relationships);
    }
    
}
