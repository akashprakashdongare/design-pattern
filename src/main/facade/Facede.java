package main.facade;

import java.util.ArrayList;
import java.util.List;

class Buffer{
    private char[] characters;
    private int lineWidth;

    public Buffer(int lineHeight, int lineWidth){
        this.lineWidth = lineWidth;
        characters = new char[lineWidth * lineHeight];
    }

    public char charAt(int x, int y){
        return characters[y * lineWidth + x];
    }
}
class Viewport{
    private final Buffer buffer;
    private final int width;
    private final int height;
    private final int offsetx;
    private final int offsety;

    public Viewport(Buffer buffer, int width, int height, int offsetx, int offsety) {
        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.offsetx = offsetx;
        this.offsety = offsety;
    }

    public char charAt(int x, int y){
        return buffer.charAt(x + offsetx, y + offsety);
    }
}
class Console{
    private List<Viewport> viewports = new ArrayList<>();
    int width, height;

    public Console(int width, int height){
        this.width = width;
        this.height = height;
    }

    public static Console newConsole(int width, int height){
        Buffer buffer = new Buffer(width, height);
        Viewport viewport = new Viewport(buffer, width, height, 0, 0);
        Console console = new Console(width, height);
        console.addViewport(viewport);
        return console;
    }

    public void addViewport(Viewport viewport){
        viewports.add(viewport);
    }

    public void render(){
        for(int y = 0; y < height; ++y){
            for(int x = 0; x < width; ++x){
                for(Viewport vp : viewports){
                    System.out.print(vp.charAt(x, y));
                }
            }
            System.out.println();
        }
    }
}
public class Facede {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(30, 20);
        Viewport viewport = new Viewport(buffer, 30, 20, 0, 0);
        Console console = new Console(30, 20);
        console.addViewport(viewport);
        console.render();
    }
}
