
/*
        public class SomethingIsWrong {
        public static void main(String[] args) {

        Rectangle myRect; >>>> FALTA CREAR NUEVO OBJETO, OBJETO QUE NO TIENE CODIGO Y ES EL QUE TUVE QUE HACER

        myRect.width = 40;
        myRect.height = 50;
        System.out.println("myRect's area is " + myRect.area());
            }
        }
        */

public class Rectangle {
    // DECLARACION DE VARIABLES DE LA CLASE
    int width = 0;
    int height = 0;
    float area = 0;

    // CONSTRUCTOR DE CLASE
    public Rectangle() {
    }

    // METODO PARA OBTENER EL AREA
    public float getArea() {
        area = width * height;
        return area;
    }

    public static void main(String[] args) {


        Rectangle myRect = new Rectangle();
        myRect.width = 40;
        myRect.height = 50;
        System.out.println("myRect's area is " + myRect.getArea());
    }
}