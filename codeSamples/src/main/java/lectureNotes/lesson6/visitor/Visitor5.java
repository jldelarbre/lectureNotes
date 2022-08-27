package lectureNotes.lesson6.visitor;

import static lectureNotes.lesson6.visitor.Visitor5.ShapeComputation.perimeter;

@SuppressWarnings("unused")
public class Visitor5 {
	
	class Point {
		double x;
		double y;
	}
	
	// We want to be able to add new functions acting on Shape, but without
	//
	// - Modifying existing shapes
	// - Pollute the interface with not closely related function

	interface Shape {
		
		// Methods closely related to the essence of Shape:
		// A geometric figure in a plane "movable" and "orientable"
		
		void move(double x, double y);
		void rotate(double angle);
		
		// Is it in the right place ?
		double perimeter();
		double area();
	}
	
	// Or add new functions instead of being methods of Shape interface...
	static class ShapeComputation {
		static double perimeter(Shape shape) {
			double perimeter = 0.;
			if (shape instanceof Rectangle) {
				// ...
			} else if (shape instanceof Square) {
				// ...
			} else if (shape instanceof Circle) {
				// ...
			}
			return perimeter;
		}
		
		static double area(Shape shape) {
			double area = 0.;
			if (shape instanceof Rectangle) {
				// ...
			} else if (shape instanceof Square) {
				// ...
			} else if (shape instanceof Circle) {
				// ...
			}
			return area;
		}
	}
	
	// Other functions we definitely not want to be part of Shape class
	static class ShapeUtility {
		static void saveToXml(Shape shape) {
			if (shape instanceof Rectangle) {
				// ...
			} else if (shape instanceof Square) {
				// ...
			} else if (shape instanceof Circle) {
				// ...
			}
		}

		static void saveToJson(Shape shape) {
			if (shape instanceof Rectangle) {
				// ...
			} else if (shape instanceof Square) {
				// ...
			} else if (shape instanceof Circle) {
				// ...
			}
		}
	}
	
	void useShapes() {
		double perimeter;
		
		Shape shape = new Rectangle();
		
		perimeter = perimeter(shape); // perimeter(this)
		perimeter = shape.perimeter();
		
		// - Really looks like a method call is equivalent to function call with object as first argument
		//
		// - perimeter function not only does the computation but also is dispatching to the code that
		//   works for the actual Shape
		//
		// It looks like 'extension method'
	}

	///////////////////////////////////////////////
	// Object oriented design solution for Shape //
	///////////////////////////////////////////////
	
	class Rectangle implements Shape {
		Point topLeft;
		double width;
		double height;
		double orientation;
		
		@Override
		public void move(double x, double y) {
			// ...
		}
		
		@Override
		public void rotate(double angle) {
			// ...
		}

		@Override public double perimeter() { return 0; /* Just satisfy the compiler */ }
		@Override public double area() { return 0; /* Just satisfy the compiler */ }
	}
	
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

		@Override public double perimeter() { return 0; /* Just satisfy the compiler */ }
		@Override public double area() { return 0; /* Just satisfy the compiler */ }
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

		@Override public double perimeter() { return 0; /* Just satisfy the compiler */ }
		@Override public double area() { return 0; /* Just satisfy the compiler */ }
	}
}
