package lectureNotes.lesson6.visitor;

public class Visitor2 {
	
	class Point {
		double x;
		double y;
	}

	abstract class Shape {
		abstract void move(double x, double y);
		abstract void rotate(double angle);
	}

	///////////////////////////////////////////////
	// Object oriented design solution for Shape //
	///////////////////////////////////////////////
	
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
	}
	
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
	}
	
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
	}
	
	// New types (data structures) could be easily added:
	// No impact on existing types: Rectangle, Square, Circle
	//
	// BUT ...
	
	class Triangle extends Shape {
		Point a;
		Point b;
		Point c;
		
		@Override
		void move(double x, double y) {
			// ...
		}
		
		@Override
		void rotate(double angle) {
			// ...
		}
	}
}
