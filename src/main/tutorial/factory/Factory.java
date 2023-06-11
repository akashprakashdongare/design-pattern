package main.tutorial.factory;

enum CoordinateSystem{
    CARTESIAN,
    POLAR
}

class Point{
    private double x, y;

    // public Point(double a, double b, CoordinateSystem cs){
    //     switch(cs){
    //         case CARTESIAN :
    //                  x = a;
    //                  y = b;
    //                  break;
    //         case POLAR :
    //                  x = a * Math.cos(b);
    //                  y = a * Math.sin(b);
    //                  break;
    //     }
    // }/

    private Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static class PointFactory{
        public static Point newCartesianPoint(double x, double y){
            return new Point(x, y);
        }
    
        public static Point newPolarPoint(double rho, double theta){
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }
}
public class Factory {
    public static void main(String[] args) {
        Point point = Point.PointFactory.newCartesianPoint(2, 3);
    }
    
}
