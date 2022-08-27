package lectureNotes.lesson6.visitor;

public class Visitor4 {
	
	class Point {
		double x;
		double y;
	}

	abstract class Shape {}

	///////////////////////////
	// Shape data structures //
	///////////////////////////
	
	/////////////////////////////
	// UNCHANGED DATA STRUCURE //
	/////////////////////////////
	class Rectangle  extends Shape {
		Point topLeft;
		double width;
		double height;
		double orientation;
	}
	
	/////////////////////////////
	// UNCHANGED DATA STRUCURE //
	/////////////////////////////
	class Square extends Shape {
		Point topLeft;
		double size;
		double orientation;
	}
	
	/////////////////////////////
	// UNCHANGED DATA STRUCURE //
	/////////////////////////////
	class Circle extends Shape {
		Point center;
		double radius;
	}
	
	////////////////////////////////
	// Functions acting on shapes //
	////////////////////////////////
	
	static class GeometricalTransform {
		
		// Seems ugly, but it is just structured programming
		//
		// Adding new functions has no impact on data structures
		
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
		
		static void scale(Shape shape, double scaleFactor) {
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
