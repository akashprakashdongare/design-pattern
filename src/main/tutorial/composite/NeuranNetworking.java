package main.tutorial.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

interface SomeNeuron extends Iterable<Neuron>{
    default void connectTo(SomeNeuron other){
        if(this == other) return;

        for(Neuron from : this){
            for(Neuron to : other){
                from.out.add(to);
                to.in.add(from);
            }
        }
    }
}
class Neuron implements SomeNeuron{
    public ArrayList<Neuron> in, out;

    @Override
    public Iterator<Neuron> iterator() {
        return Collections.singleton(this).iterator();
    }

    // public void connectTo(Neuron other){
    //     out.add(other);
    //     other.in.add(this);
    // }
}
class NeuronLayer extends ArrayList<Neuron> implements SomeNeuron{

}
public class NeuranNetworking {
    public static void main(String[] args) {
        Neuron neuron = new Neuron();
        Neuron neuron2 = new Neuron();
        NeuronLayer layer = new NeuronLayer();
        NeuronLayer layer2 = new NeuronLayer();

        neuron.connectTo(neuron2);
        neuron.connectTo(layer);
        layer.connectTo(neuron);
        layer.connectTo(layer2);
    }    
}
