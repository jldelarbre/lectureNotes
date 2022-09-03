package lectureNotes.lesson6.visitor;

public class Visitor3 {
    
    class Point {
        double x;
        double y;
    }

    abstract class Shape {
        abstract void move(double x, double y);
        abstract void rotate(double angle);
        
        // Add a new method to Shape has impact on every shapes...
        abstract void scale(double scaleFactor);
    }

    ////////////////////
    // MODIFIED CLASS //
    ////////////////////
    class Rectangle  extends Shape {
        Point topLeft;
        double width;
        double height;
        double orientation;
        
        @Override
        void move(double x, double y) {
            // ...
        }
        
        @Override
        void rotate(double angle) {
            // ...
        }

        @Override
        void scale(double scaleFactor) {
            // ...
        }
    }
    
    ////////////////////
    // MODIFIED CLASS //
    ////////////////////
    class Square extends Shape {
        Point topLeft;
        double size;
        double orientation;
        
        @Override
        void move(double x, double y) {
            // ...
        }
        
        @Override
        void rotate(double angle) {
            // ...
        }

        @Override
        void scale(double scaleFactor) {
            // ...
        }
    }
    
    ////////////////////
    // MODIFIED CLASS //
    ////////////////////
    class Circle extends Shape {
        Point center;
        double radius;
        
        @Override
        void move(double x, double y) {
            // ...
        }
        
        @Override
        void rotate(double angle) {
            // ...
        }

        @Override
        void scale(double scaleFactor) {
            // ...
        }
    }
}
