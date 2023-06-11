package main.tutorial.prototype;

class Employee{
    public String name;
    public AddressEmp address;

    public Employee(String name, AddressEmp address){
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other){
        name = other.name;
        address = new AddressEmp(other.address);
    }

    @Override
    public String toString() {
        return "{name=" + name + ", address=" + address + "}";
    }
    
}

class AddressEmp{
    public String streetAddress, city, country;

    public AddressEmp(String streetAddress, String city, String country){
        this.streetAddress = streetAddress;
        this.city = city;
        this.country =country;
    }

    public AddressEmp(AddressEmp other){
        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString() {
        return "{streetAddress=" + streetAddress + ", city=" + city + ", country=" + country + "}";
    }
}

public class UsingCopyConstructor {
    public static void main(String[] args) {
        Employee john = new Employee("John",
                           new AddressEmp("123 London", "London", "UK"));

        Employee chris = new Employee(john);
        chris.name = "Chris";

        System.out.println("John : " + john);
        System.out.println("Chris : " + chris);
    }
    
}
