package main.tutorial.prototype;

import java.util.Arrays;

class Address implements Cloneable{
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber){
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "{streetName=" + streetName + ", houseNumber=" + houseNumber + "}";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Address(streetName, houseNumber);
    }

}

class Person implements Cloneable{
    public String[] names;
    public Address address;

    public Person(String[] names, Address address){
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "{names=" + Arrays.toString(names) + ", address=" + address + "}";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Person(names.clone(), (Address)address.clone());
    }    
}
public class UsingCloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person john = new Person(new String[]{"John", "Smith"}, 
                                         new Address("London Road", 123));
        Person jane = (Person) john.clone();
        jane.names[0] = "Jane";
        jane.address.houseNumber = 124;
        
        System.out.println("John : "+john);
        System.out.println("Jane : "+jane);

    }
}
