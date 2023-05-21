package main.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Point{
    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + "}";
    }
}

class Line{
    public Point start, end;

    public Line(Point start, Point end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Line other = (Line) obj;
        if(!start.equals(other.start)) return false;
        return end.equals(other.end);
    }
    
}

class VectorObject extends ArrayList<Line>{

}

class VectorRectangle extends VectorObject{

    public VectorRectangle(int x, int y, int width, int height){
        add(new Line(new Point(x, y), new Point(x + width, y)));
        add(new Line(new Point(x + width, y), new Point(x + width, y + height)));
        add(new Line(new Point(x, y), new Point(x, y + height)));
        add(new Line(new Point(x, y + height), new Point(x + width, y + height)));
    }
}

class LineToAdapterPoint implements Iterable<Point>{
    private static int count = 0;
    private Map<Integer, List<Point>> cache = new HashMap<>();
    private int hash;

    public LineToAdapterPoint(Line line){
        hash = line.hashCode();

        if(null != cache.get(hash)) return;

        List<Point> points = new ArrayList<>();

        System.out.println(
            String.format("%d: Generating point for line [%d,%d]-[%d-%d] (no caching)",
            (++count), line.start.x, line.start.y, line.end.x, line.end.y)
            );
          int left = Math.min(line.start.x, line.end.x);
          int right = Math.max(line.start.x, line.end.x);
          int top = Math.min(line.start.y, line.end.y);
          int bottom = Math.max(line.start.x, line.end.y);
          int dx = right - left;
          int dy = line.end.y - line.start.y;
          if(0 == dx){
            for(int y = top; y <= bottom; ++y){
                points.add(new Point(left, y));
            }
          } else if(0 == dy) {
              for(int x = left; x <= right; ++x){
                points.add(new Point(x, top));
              }
          }

          cache.put(hash, points);
    }

    @Override
    public Iterator<Point> iterator() {
        return cache.get(hash).iterator();
    }
}
public class VectorRaster {
    private final static List<VectorObject> vectorObjects = new ArrayList<>(
            Arrays.asList(
                new VectorRectangle(1, 1, 10, 10),
                new VectorRectangle(3, 3, 6, 6)
            )
        );
    
    public static void drawPoint(Point p){
        System.out.println(".");
    }

    private static void draw(){
        for(VectorObject vo : vectorObjects){
            for(Line line : vo){
                LineToAdapterPoint adapter = new LineToAdapterPoint(line);
                adapter.forEach(VectorRaster::drawPoint);
            }
        }
    }
    public static void main(String[] args) {
        draw();
        draw();
    }
}
