package main.tutorial.builder;

class Person{
    String name;
    String position;

    @Override
    public String toString() {
        return "{name=" + name + ", position=" + position + "}";
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>>{
    protected Person person = new Person();

    public SELF withName(String name){
        this.person.name = name;
        return self();
    }

    public Person build(){
        return this.person;
    }

    protected SELF self(){
        return (SELF) (this);
    }

}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder>{

    public EmployeeBuilder worksAt(String position){
        this.person.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }

    
}

public class BuilderExp2 {
    public static void main(String[] args) {
        EmployeeBuilder personBuilder = new EmployeeBuilder();
        Person person = personBuilder.withName("John")
                                     .worksAt("Java developer")
                                     .build();
         System.out.println(person);


    }
}
