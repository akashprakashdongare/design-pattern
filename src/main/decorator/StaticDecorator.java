package main.decorator;

import java.util.function.Supplier;

interface ShapeCopy{
    String info();
}
class CircleCopy implements ShapeCopy{
    private float radius;

    public CircleCopy(){}
    public CircleCopy(float radius){ this.radius = radius; }

    public void resize(float factor){ radius *= factor; }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}
class SquareCopy implements ShapeCopy{
    private float side;
    
    public SquareCopy(){}
    public SquareCopy(float side){ this.side = side; }

    @Override
    public String info() {
        return "A square with side " + side;
    }
}
class ColoredShapeCopy<T extends ShapeCopy> implements ShapeCopy{
    private ShapeCopy shape;
    private String color;
    public ColoredShapeCopy(Supplier<? extends T> ctor, String color){
        this.color = color;
        this.shape = ctor.get();
    }
    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}
class TransparentShapeCopy<T extends ShapeCopy> implements ShapeCopy{
    private ShapeCopy shape;
    private int transparency;
    public TransparentShapeCopy(Supplier<? extends ShapeCopy> ctor, int transparency) {
        this.transparency = transparency;
        shape = ctor.get();
    }
    @Override
    public String info() {
        return shape.info() + " and the transparency is " + transparency + "%";
    }
    
}
public class StaticDecorator {
    public static void main(String[] args) {
        ColoredShapeCopy<SquareCopy> blueSquare = new ColoredShapeCopy<>(
            () -> new SquareCopy(20), 
            "blue");
        System.out.println(blueSquare.info());

        TransparentShapeCopy<ColoredShapeCopy<CircleCopy>> myCircle = new TransparentShapeCopy<>(
            () -> new ColoredShapeCopy<>(
                () -> new CircleCopy(5), "green"),
                 50
        );
        System.out.println(myCircle.info());
    }
}
