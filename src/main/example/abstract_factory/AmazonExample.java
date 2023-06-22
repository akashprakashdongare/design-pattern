package main.example.abstract_factory;

import java.util.Objects;

class Product{
    String description;
    public Product(String productName){
        this.description = productName;
    }

    public void getDescription(){
        System.out.println(this.description);
    }
}
class NullProduct extends Product{

    public NullProduct() {
        super("invalid product");
    }
    
}
class Nike{
    private Nike(){}
    public static Product getProduct(String name){
        if(Objects.equals(name, "shoes"))
            return new Product(name);
        else if(Objects.equals(name, "track-suit"))
            return new Product(name);

        return new NullProduct();
    }

}
class Biba{
    private Biba(){}
    public static Product getProduct(String name){
        if(Objects.equals(name, "saree"))
           return new Product(name);
        else if(Objects.equals(name, "ethnic-top"))
            return new Product(name);

        return new NullProduct();
    }
}
class Mango{
    private Mango(){}
    public static Product getProduct(String name){
        if(Objects.equals(name, "dress"))
           return new Product(name);
        else if(Objects.equals(name, "jeans"))
           return new Product(name);

        return new NullProduct();
    }
}
class ShoppingStore{
    private ShoppingStore(){}
    public static Product getProduct(String brand, String model){
        if(Objects.equals(brand, "Nike"))
           return Nike.getProduct(model);
        else if(Objects.equals(brand, "Biba"))
           return Biba.getProduct(model);
        else if(Objects.equals(brand, "Mango"))
           return Mango.getProduct(model);

        return new NullProduct();
    }
}
public class AmazonExample {
    public static void main(String[] args) {
        Product nike = ShoppingStore.getProduct("Nike", "shoes");
        nike.getDescription();

        System.out.println();

        Product biba = ShoppingStore.getProduct("Biba", "shoes");
        biba.getDescription();
    }
}
