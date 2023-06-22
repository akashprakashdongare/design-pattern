package main.example.decorator;

interface Pizza{
    public String getDescription();
    public double getPrice();
}
class Margherita implements Pizza{

    public Margherita(){}

    @Override
    public String getDescription() {
        return "This is a Margherita Pizza";
    }

    @Override
    public double getPrice() {
        return 400.00;
    }
    
}
class FarmHouse implements Pizza{

    public FarmHouse(){}

    @Override
    public String getDescription() {
        return "This is a Farm House Pizza";
    }

    @Override
    public double getPrice() {
        return 500.00;
    }
    
}
interface PizzaDecorator extends Pizza{}

class Cheese implements PizzaDecorator{
    public Pizza pizza;
    public Cheese(Pizza pizza){ this.pizza = pizza; }
    @Override
    public String getDescription() {
       return pizza.getDescription() + " with extrat Cheese";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 10.00;
    }
    
}
class Toppings implements PizzaDecorator{

    public Pizza pizza;
    public Toppings(Pizza pizza){ this.pizza = pizza; }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " with extra Toppings ";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 20.00;
    }
    
}
public class PizzaExample {
    public static void main(String[] args) {

        Pizza margherita = new Margherita();
        margherita = new Cheese(margherita);
        margherita = new Toppings(margherita);
        System.out.println(margherita.getDescription());
        System.out.println(margherita.getPrice());
    }
}
