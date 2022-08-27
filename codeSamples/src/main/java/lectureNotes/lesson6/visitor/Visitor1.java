package lectureNotes.lesson6.visitor;

public class Visitor1 {
	
	class Point {
		double x;
		double y;
	}

	abstract class Shape {}

	///////////////////////////
	// Shape data structures //
	///////////////////////////
	
	class Rectangle  extends Shape {
		Point topLeft;
		double width;
		double height;
		double orientation;
	}
	
	class Square extends Shape {
		Point topLeft;
		double size;
		double orientation;
	}
	
	class Circle extends Shape {
		Point center;
		double radius;
	}
	
	////////////////////////////////
	// Functions acting on shapes //
	////////////////////////////////
	
	static class GeometricalTransform {
		
		// Seems ugly, but it is just structured programming
		
		static void move(Shape shape, double x, double y) {
			if (shape instanceof Rectangle) {
				// ...
			} else if (shape instanceof Square) {
				// ...
			} else if (shape instanceof Circle) {
				// ...
			}
		}
		
		static void rotate(Shape shape, double angle) {
			if (shape instanceof Rectangle) {
				// ...
			} else if (shape instanceof Square) {
				// ...
			} else if (shape instanceof Circle) {
				// ...
			}
		}
	}
}
