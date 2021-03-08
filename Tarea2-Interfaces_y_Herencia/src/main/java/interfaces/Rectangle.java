package interfaces;

public class Rectangle implements Polygon{
    public int width;
    public int height;

    private int sides=4;

    public Rectangle(int width, int height){
        this.width=width;
        this.height=height;
    }

    @Override
    public float getArea() {
        return (float) (width*height);
    }

    @Override
    public int getSides() {
        return sides;
    }
}
