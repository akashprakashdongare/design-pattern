package main.example.observe;

import java.util.ArrayList;
import java.util.List;

interface Subject{
    public void register(Observer observer);
    public void unregister(Observer observer);
    public void notifyobs();
    public void update(double updatedTemperature, double updatedHumidity);
}
interface Observer{
    public void update(double updatedTemperature, double updatedHumidity);
    public void register(WetherStation station);
    public void unregister(WetherStation station);
}
class WetherStation implements Subject{

    private double temperature;
    private double humidity;
    private List<Observer> observers;

    public WetherStation(){
        observers = new ArrayList<>();
    }

    @Override
    public void register(Observer observer) {
        if(!observers.contains(observer))
        observers.add(observer);
        else
        System.out.println("Observer already registered");
    }

    @Override
    public void unregister(Observer observer) {
        if(observers.contains(observer))
        observers.remove(observer);
        else
        System.out.println("Observer not registered");
    }

    @Override
    public void notifyobs() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(this.temperature, this.humidity);
        }
    }

    @Override
    public void update(double updatedTemperature, double updatedHumidity) {
        this.temperature = updatedTemperature;
        this.humidity = updatedTemperature;
        notify();
    }

}
class GetInfo implements Observer{
    private double temperature;
    private double humidity;

    public GetInfo(){}

    @Override
    public void update(double updatedTemperature, double updatedHumidity) {
        this.temperature = updatedTemperature;
        this.humidity = updatedHumidity;
    }

    @Override
    public void register(WetherStation station) {
        station.register(this);
    }

    @Override
    public void unregister(WetherStation station) {
        station.unregister(this);
    }

    public void showInfo(){
        System.out.println("Current temperature = " + this.temperature);
        System.out.println("Current humidity = " + this.humidity);
    }
    
}
public class WetherStationExample {
    
}
