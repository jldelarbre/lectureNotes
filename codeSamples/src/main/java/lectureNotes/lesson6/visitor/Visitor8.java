package lectureNotes.lesson6.visitor;

public class Visitor8 {
    
    class Point {
        double x;
        double y;
    }
    
    interface Shape {
        
        void move(double x, double y);
        void rotate(double angle);
        
        void accept(ShapeVisitor shapeVisitor);
    }

    /////////////
    // Visitor //
    /////////////
    
    // Have to know every subclasses: Modified every time a subtype is added
    interface ShapeVisitor {
        void visit(Square square);
        void visit(Circle circle);
    }
    
    // Force Square and Circle to be in the same package and to know every Shape implementation
    class ShapeToXmlSaver implements ShapeVisitor {
        public void visit(Square square) {
            // ...
        }

        public void visit(Circle circle) {
            // ...
        }
    }

    class ShapeToJsonSaver implements ShapeVisitor {
        public void visit(Square square) {
            // ...
        }

        public void visit(Circle circle) {
            // ...
        }
    }
    
    void useVisitor() {
        Shape shape = new Square();
        ShapeVisitor xmlSaver = new ShapeToXmlSaver();
        
        shape.accept(xmlSaver); // FIRST DISPATCH
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

        @Override
        public void accept(ShapeVisitor shapeVisitor) {
            shapeVisitor.visit(this); // SECOND (and third) DISPATCH
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

        @Override
        public void accept(ShapeVisitor shapeVisitor) {
            shapeVisitor.visit(this); // SECOND (and third) DISPATCH
        }
    }
}
