package main.tutorial.builder;

class Person{
    //address
    public String streetAddress, postcode, city;

    //employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "{streetAddress=" + streetAddress + ", postcode=" + postcode + ", city=" + city + ", companyName="
                + companyName + ", position=" + position + ", annualIncome=" + annualIncome + "}";
    }
}

// builder facade
class PersonBuilder{
    protected  Person person = new Person();

    public PersonAddressBuilder lives(){
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works(){
        return new PersonJobBuilder(person);
    }

    public Person build(){
        return person;
    }
}

class PersonAddressBuilder extends PersonBuilder{

    public PersonAddressBuilder(Person person){
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress){
        person.streetAddress = streetAddress;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postcode){
        person.postcode = postcode;
        return this;
    }

    public PersonAddressBuilder in(String city){
        person.city = city;
        return this;
    }
}

class PersonJobBuilder extends PersonBuilder{

    public PersonJobBuilder(Person person){
        this.person = person;
    }

    public PersonJobBuilder atA(String companyName){
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position){
        person.position = position;
        return this;
    }

    public PersonJobBuilder earning(int annualIncome){
        person.annualIncome = annualIncome;
        return this;
    }
}
public class BuilderExmp3 {
    public static void main(String[] args) {
        PersonBuilder pb = new PersonBuilder();
        Person person = pb
                         .lives()
                          .at("123 London Road")
                          .in("London")
                          .withPostcode("SW12BC")
                         .works()
                           .atA("Fabrikam")
                           .asA("Engineer")
                           .earning(123000)
                         .build();

        System.out.println(person);
    }
}
