package interfaces;

public class Rectangle implements Polygon{
    public int width;

    private int sides=4;

    public Rectangle(int width){
        this.width=width;
    }

    @Override
    public float getArea() {
        return (float) (width*width);
    }

    @Override
    public int getSides() {
        return sides;
    }
}
