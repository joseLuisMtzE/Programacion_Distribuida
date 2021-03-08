package interfaces;

public class Square implements Polygon {
    public int width;

    private int sides=4;

    public Square(int width){
        this.width=width;
    }

    @Override
    public float getArea() {
        return (float) (Math.pow(this.width,2));
    }

    @Override
    public int getSides() {
        return sides;
    }
}
