package main.solid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

enum Colour{
    RED, GREEN, BLUE
}

enum Size{
    SMALL, MEDIUM, LARGE, YUGE
}

class Product{
    public String name;
    public Colour color;
    public Size size;

    public Product(String name, Colour color, Size size){
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter{

    public Stream<Product> filterByColour(List<Product> products, Colour colour){
        return products.stream().filter(p -> colour.equals(p.color));
    }

    public Stream<Product> filterBySize(List<Product> products, Size size){
        return products.stream().filter(p -> size.equals(p.size));
    }

    public Stream<Product> filterByColourAndSize(List<Product> products, Colour colour, Size size){
        return products.stream()
                       .filter(p -> colour.equals(p.color) 
                                    && size.equals(p.size));
    }
}

interface Specification<T>{
    boolean isSatisfied(T item);
}

interface Filter<T>{
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColourSpecification implements Specification<Product>{

    private Colour colour;

    public ColourSpecification(Colour colour){
        this.colour = colour;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return colour.equals(item.color);
    }
    
}

class SizeSpecification implements Specification<Product>{
    private Size size;

    public SizeSpecification(Size size){
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return size.equals(item.size);
    }
    
}

class BetterFilter implements Filter<Product>{

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
    
}

class AndSpecification implements Specification<Product>{
    private Specification<Product> firstSpecification;
    private Specification<Product> secondSpecification;
    
    public AndSpecification(Specification<Product> firstSpecification, Specification<Product> secondSpecification){
        this.firstSpecification = firstSpecification;
        this.secondSpecification = secondSpecification;
    }
    
    @Override
    public boolean isSatisfied(Product item) {
        return firstSpecification.isSatisfied(item) && secondSpecification.isSatisfied(item);
    }
}
public class OpenClose {
    public static void main(String[] args) {

        Product apple = new Product("Apple", Colour.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Colour.GREEN, Size.LARGE);
        Product house = new Product("House", Colour.BLUE, Size.LARGE);

        List<Product> products = new ArrayList<>();
        products.add(apple);
        products.add(tree);
        products.add(house);

        ProductFilter filter = new ProductFilter();

        System.out.println("Green products (Old) :");
        filter.filterByColour(products, Colour.GREEN)
              .forEach(p ->
                        System.out.println("- " + p.name + " is green"));

        BetterFilter betterFilter = new BetterFilter();

        System.out.println("Green products (New) :");

        betterFilter.filter(products, new ColourSpecification(Colour.GREEN))
                    .forEach(p -> System.out.println("- " + p.name + " is green"));

        System.out.println("Size product :");
        betterFilter.filter(products, new SizeSpecification(Size.LARGE))
                    .forEach(p -> System.out.println("- "+ p.name + " is large"));

        System.out.println("Colour and Size :");
        betterFilter.filter(products, 
                               new AndSpecification(
                                           new ColourSpecification(Colour.BLUE),
                                           new SizeSpecification(Size.LARGE)))
                    .forEach(p -> System.out.println("- "+ p.name +" is large and blue"));
        
    }
}
