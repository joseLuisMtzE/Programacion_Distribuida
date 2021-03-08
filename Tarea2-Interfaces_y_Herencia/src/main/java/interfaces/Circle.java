package interfaces;

public class Circle implements Polygon{
    public int radio;

    private int sides=1;

    public Circle(int radio){
        this.radio=radio;
    }

    @Override
    public float getArea() {
        return (float) (3.1415*Math.pow(radio,2));
    }

    @Override
    public int getSides() {
        return sides;
    }
}
