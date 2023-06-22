package main.example.abstract_factory;

enum Type{
    MICRO, MINI, LUXURY
}
enum Location{
    DEFAULT, USA, INDIA
}

abstract class Car{
    private Type type;
    private Location location;

    public abstract void construct();

    public Car(Type type, Location location){
        this.type = type;
        this.location = location;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "{type=" + type + ", location=" + location + "}";
    }
    
}
class LuxuryCar extends Car{
    public LuxuryCar(Location location) {
        super(Type.LUXURY, location);
    }

    @Override
    public void construct() {
        System.out.println("Connecting to luxury car..");
    }
}
class MicronCar extends Car{
    public MicronCar(Location location) {
        super(Type.MICRO, location);
    }

    @Override
    public void construct() {
        System.out.println("Connecting to micron car..");
    } 
}
class MiniCar extends Car{
    public MiniCar(Location location) {
        super(Type.MINI, location);
    }

    @Override
    public void construct() {
        System.out.println("Connecting to mini car..");
    }
}
class IndianCarFactory{
   public static Car buildCar(Type type){
     Car car = null;
       switch(type){
        case MICRO:
          car = new MicronCar(Location.INDIA);
          break;
        case MINI:
          car = new MiniCar(Location.INDIA);
          break;
        case LUXURY:
          car = new LuxuryCar(Location.INDIA);
          break;
        default:
          break;
      }
      return car;
    }
}
class USACarFactory{
    public static Car buildCar(Type type){
        Car car = null;
          switch(type){
            case MINI:
              car = new MiniCar(Location.USA);
              break;
            case MICRO:
              car = new MicronCar(Location.USA);
              break;
            case LUXURY:
              car = new LuxuryCar(Location.USA);
              break;
            default:
              break;
          }
          return car;
    }
}
class DefaultCarFactory{
    public static Car buildCar(Type type){
        Car car = null;
          switch(type){
            case MICRO:
               car = new MicronCar(Location.DEFAULT);
               break;
            case MINI:
               car = new MiniCar(Location.DEFAULT);
               break;
            case LUXURY:
               car = new LuxuryCar(Location.DEFAULT);
               break;
            default:
               break;
          }
          return car;
    }
}
class CarFactory{
    private CarFactory(){}
    public static Car buildCar(Type type, Location location){
        Car car = null;
            switch(location){
                case USA:
                   car = USACarFactory.buildCar(type);
                   break;
                case INDIA:
                   car = IndianCarFactory.buildCar(type);
                   break;
                default:
                   car = DefaultCarFactory.buildCar(type);
                   break;
            }
          return car;
    }
}
public class AbstractFactoryExample {
    public static void main(String[] args) {
        System.out.println(CarFactory.buildCar(Type.MICRO, Location.INDIA));
        System.out.println(CarFactory.buildCar(Type.LUXURY, Location.INDIA));
        System.out.println(CarFactory.buildCar(Type.MICRO, Location.USA));
        System.out.println(CarFactory.buildCar(Type.MINI, Location.DEFAULT));
    }
}
