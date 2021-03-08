package org.example;

import interfaces.Circle;
import interfaces.Polygon;
import interfaces.Rectangle;
import interfaces.Square;

public class App 
{
    public static void main( String[] args )
    {
        Polygon s = new Square(5);
        Polygon r = new Rectangle(12,6);
        Polygon c = new Circle(7);

        System.out.println("Square: "+ s.getArea()+", "+ s.getSides());
        System.out.println("Rectangle: "+ r.getArea()+", "+ r.getSides());
        System.out.println("Circle: "+ c.getArea()+", "+ c.getSides());
        //System.out.println( "Hello World!" );
    }
}
