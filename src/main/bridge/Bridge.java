package main.bridge;

interface Renderer{
    void rendereCircle(float radius);
}
class VectorRenderer implements Renderer{
    @Override
    public void rendereCircle(float radius) {
        System.out.println("Drawing a circle of radius " + radius);
    }
}
class RasterRenderer implements Renderer{
    @Override
    public void rendereCircle(float radius) {
        System.out.println("Drawing pixels for circle of radius " + radius);
    }
}
abstract class Shape{
    protected Renderer renderer;
    public Shape(Renderer renderer){
        this.renderer = renderer;
    }

    abstract void draw();
    abstract void resize(float factor);
}
class Circle extends Shape{
    public float radius;
    public Circle(Renderer renderer){
        super(renderer);
    }
    public Circle(Renderer renderer, float radius){
        super(renderer);
        this.radius = radius;
        this.renderer = renderer;
    }
    @Override
    void draw() {
        renderer.rendereCircle(radius);
    }
    @Override
    void resize(float factor) {
        radius = radius * factor;
    }
}
public class Bridge {
    public static void main(String[] args) {
        RasterRenderer raster = new RasterRenderer();
        VectorRenderer vector = new VectorRenderer();
        Circle circle = new Circle(vector, 5);
        circle.draw();
        circle.resize(2);
        circle.draw();
    }
}
