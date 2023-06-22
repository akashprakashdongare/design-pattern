package main.example.factory;


class Parent{
    public void display(){
        System.out.println("display method of parent..");
    }
}

class Child extends Parent{
    public void print(){
        System.out.println("print method of child..");
    }
}

public class Emaple {
    public static void main(String[] args) {
        Parent p = new Child();
        p.display();
        //p.print();
    }
}
