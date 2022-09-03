package lectureNotes.lesson6.visitor;

import static lectureNotes.lesson6.visitor.Visitor6.ShapeToXmlSaverImpl.saveToXmlImpl;
import static lectureNotes.lesson6.visitor.Visitor6.ShapeToJsonSaverImpl.saveToJsonImpl;

public class Visitor6 {
    
    class Point {
        double x;
        double y;
    }
    
    interface Shape {
        
        void move(double x, double y);
        void rotate(double angle);
    }

    /////////////////
    // Dispatching //
    /////////////////

    static class ShapeToXmlSaver {
        static void saveToXml(Shape shape) {
            if (shape instanceof Square) {
                saveToXmlImpl((Square) shape);
            } else if (shape instanceof Circle) {
                saveToXmlImpl((Circle) shape);
            }
        }
    }

    static class ShapeToJsonSaver {
        static void saveToJson(Shape shape) {
            if (shape instanceof Square) {
                saveToJsonImpl((Square) shape);
            } else if (shape instanceof Circle) {
                saveToJsonImpl((Circle) shape);
            }
        }
    }

    /////////////////////
    // Implementations //
    /////////////////////
    
    static class ShapeToXmlSaverImpl {
        static void saveToXmlImpl(Square square) {
            // ...
        }

        static void saveToXmlImpl(Circle circle) {
            // ...
        }
    }

    static class ShapeToJsonSaverImpl {
        static void saveToJsonImpl(Square square) {
            // ...
        }

        static void saveToJsonImpl(Circle circle) {
            // ...
        }
    }

    ///////////////////////////
    // Shape implementations //
    ///////////////////////////

    class Square implements Shape {
        Point topLeft;
        double size;
        double orientation;
        
        @Override
        public void move(double x, double y) {
            // ...
        }
        
        @Override
        public void rotate(double angle) {
            // ...
        }
    }
    
    class Circle implements Shape {
        Point center;
        double radius;
        
        @Override
        public void move(double x, double y) {
            // ...
        }
        
        @Override
        public void rotate(double angle) {
            // ...
        }
    }
}
