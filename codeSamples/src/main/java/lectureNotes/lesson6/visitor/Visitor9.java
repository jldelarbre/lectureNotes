package lectureNotes.lesson6.visitor;

import static lectureNotes.lesson6.visitor.Visitor9.ShapeToXmlSaverBuilder.aShapeToXmlSaver;
import static lectureNotes.lesson6.visitor.Visitor9.ShapeToJsonSaverBuilder.aShapeToJsonSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.Maps;

public class Visitor9 {

    class Point {
        double x;
        double y;
    }
    
    ///////////////////
    // Shape package //
    ///////////////////
    
    interface Shape {
        
        void move(double x, double y);
        void rotate(double angle);
        
        // Should be nicer with extension method
        default void accept(ShapeVisitor shapeVisitor) {
            shapeVisitor.visit(this);
        }
    }
    
    interface ShapeVisitor {
        Map< Class<? extends Shape>,
             SpecificShapeVisitor<? extends Shape> > getShapeVisitorMap();
        
        default void visit(Shape shape) {
            launchSpecificVisitor(shape, shape.getClass());
        }
        
        private <S extends Shape> void launchSpecificVisitor(Shape shape, Class<S> shapeType) {
            
            // SECOND DISPATCH
            
            Optional<SpecificShapeVisitor<S>> specificShapeVisitor = getSpecificShapeVisitor(shapeType);
            if (specificShapeVisitor.isPresent()) {
                S castShape = specificShapeVisitor.get().getType().cast(shape);
                specificShapeVisitor.get().visit(castShape);
            } else {
                // Default behavior (could also throw exception)
                DefaultShapeVisitor.INSTANCE.visit(shape);
                ////////////////////////////////////////////////////////////
                // Allow to have a sparse matrix of ShapeVisitor/Visitor: //
                // (not allowed with simple polymorphism, except default  //
                // methods)                                               //
                //                                                        //
                //            ShapeToXmlSaver ShapeToJsonSaver            //
                //     Square        X               X                    //
                //     Circle        X                                    //
                ////////////////////////////////////////////////////////////
            }
        }
        
        private <S extends Shape> Optional<SpecificShapeVisitor<S>> getSpecificShapeVisitor(Class<S> shapeType) {
            Map<Class<? extends Shape>, SpecificShapeVisitor<? extends Shape>> shapeVisitorMap = getShapeVisitorMap();
            @SuppressWarnings("unchecked")
            Optional<SpecificShapeVisitor<S>> shapeVisitor = (Optional<SpecificShapeVisitor<S>>) (Optional<?>) Optional.ofNullable(shapeVisitorMap.get(shapeType));
            return shapeVisitor;
        }
    }
    
    interface SpecificShapeVisitor<S extends Shape> {
        void visit(S shape);
        
        Class<S> getType();
    }
    
    static final class DefaultShapeVisitor implements SpecificShapeVisitor<Shape> {
        public static final DefaultShapeVisitor INSTANCE = new DefaultShapeVisitor();
        
        @Override
        public void visit(Shape shape) {
            // Default action
        }

        @Override
        public Class<Shape> getType() {
            return Shape.class;
        }
    }
    
    ///////////////////////////////
    // XML saver visitor package //
    ///////////////////////////////
    
    static class ShapeToXmlSaver implements ShapeVisitor {
        private final Map<Class<? extends Shape>, SpecificShapeVisitor<? extends Shape>> shapeVisitorMap;
        
        public ShapeToXmlSaver(Map<Class<? extends Shape>, SpecificShapeVisitor<? extends Shape>> shapeVisitorMap) {
            this.shapeVisitorMap = shapeVisitorMap;
        }

        @Override
        public Map<Class<? extends Shape>, SpecificShapeVisitor<? extends Shape>> getShapeVisitorMap() {
            return shapeVisitorMap;
        }
    }
    
    static class ShapeToXmlSaverBuilder {
        private Map<Class<? extends Shape>, SpecificShapeVisitor<? extends Shape>> shapeVisitorMap = new HashMap<>();
        
        static ShapeToXmlSaverBuilder aShapeToXmlSaver() { return new ShapeToXmlSaverBuilder(); }

        public ShapeToXmlSaverBuilder with(SpecificShapeVisitor<? extends Shape> visitor) {
            shapeVisitorMap.put(visitor.getType(), visitor);
            return this;
        }
        
        public ShapeToXmlSaver build() {
            return new ShapeToXmlSaver(Maps.newHashMap(shapeVisitorMap));
        }
    }
    
    //////////////////////////////////////////
    // XML saver visitor package extensions //
    //////////////////////////////////////////
    
    static class SquareToXmlSaver implements SpecificShapeVisitor<Square> {
        @Override
        public void visit(Square shape) {
            // ...
        }

        @Override
        public Class<Square> getType() {
            return Square.class;
        }
    }
    
    static class CircleToXmlSaver implements SpecificShapeVisitor<Circle> {
        @Override
        public void visit(Circle circle) {
            // ...
        }

        @Override
        public Class<Circle> getType() {
            return Circle.class;
        }
    }

    ////////////////////////////////
    // JSON saver visitor package //
    ////////////////////////////////
    
    static class ShapeToJsonSaver implements ShapeVisitor {
        private final Map<Class<? extends Shape>, SpecificShapeVisitor<? extends Shape>> shapeVisitorMap;
        
        public ShapeToJsonSaver(Map<Class<? extends Shape>, SpecificShapeVisitor<? extends Shape>> shapeVisitorMap) {
            this.shapeVisitorMap = shapeVisitorMap;
        }

        @Override
        public Map<Class<? extends Shape>, SpecificShapeVisitor<? extends Shape>> getShapeVisitorMap() {
            return shapeVisitorMap;
        }
    }
    
    static class ShapeToJsonSaverBuilder {
        private Map<Class<? extends Shape>, SpecificShapeVisitor<? extends Shape>> shapeVisitorMap = new HashMap<>();
        
        static ShapeToJsonSaverBuilder aShapeToJsonSaver() { return new ShapeToJsonSaverBuilder(); }

        public ShapeToJsonSaverBuilder with(SpecificShapeVisitor<? extends Shape> visitor) {
            shapeVisitorMap.put(visitor.getType(), visitor);
            return this;
        }
        
        public ShapeToJsonSaver build() {
            return new ShapeToJsonSaver(Maps.newHashMap(shapeVisitorMap));
        }
    }
    
    //////////////////////////////////////////
    // XML saver visitor package extensions //
    //////////////////////////////////////////

    static class SquareToJsonSaver implements SpecificShapeVisitor<Square> {
        @Override
        public void visit(Square shape) {
            // ...
        }

        @Override
        public Class<Square> getType() {
            return Square.class;
        }
    }
    
    ////////////////////////
    // Application sample //
    ////////////////////////
    
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        
        // Application wiring, we know we are using Square and Circle
        shapes.add(new Square()); // Quick and dirty: direct use of constructor
        shapes.add(new Circle()); // same
        
        List<ShapeVisitor>shapeVisitors = new ArrayList<>();

        // We add all the required visitors for the shapes we are using
        SquareToXmlSaver squareToXmlSaver = new SquareToXmlSaver();
        CircleToXmlSaver circleToXmlSaver = new CircleToXmlSaver();
        ShapeToXmlSaver shapeToXmlSaver = aShapeToXmlSaver().with(squareToXmlSaver).with(circleToXmlSaver).build();
        
        shapeVisitors.add(shapeToXmlSaver);
        
        // CircleToJsonSaver does not exist but it is a design application
        SquareToJsonSaver squareToJsonSaver = new SquareToJsonSaver();
        ShapeToJsonSaver shapeToJsonSaver = aShapeToJsonSaver().with(squareToJsonSaver).build();
        
        shapeVisitors.add(shapeToJsonSaver);
        
        useVisitor(shapes, shapeVisitors);
    }
    
    static void useVisitor(List<Shape> shapes, List<ShapeVisitor>shapeVisitors) {
        for (Shape shape : shapes) {
            for (ShapeVisitor shapeVisitor : shapeVisitors) {
                shape.accept(shapeVisitor); // FIRST DISPATCH
            }
        }
    }

    ///////////////////////////
    // Shape implementations //
    ///////////////////////////

    // Do not know anything about visitors:
    // - Visitor types: XML saver, JSON saver...
    // - On what Shape type they act on: Square, Circle...
    //
    static class Square implements Shape {
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
    
    static class Circle implements Shape {
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
