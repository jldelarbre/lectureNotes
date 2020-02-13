package lectureNotes.lesson4.lsp;

public class LSP1 {

    // Independence of l1 and l2 is invariant for Rectangle
    static class Rectangle {
        protected double l1;
        protected double l2;
        
        public double getL1() { return l1; }
        public void setL1(double l1) { this.l1 = l1; }
        public double getL2() { return l2; }
        public void setL2(double l2) { this.l2 = l2; }
        
        public double perimeter() {
            return 2*l1 + 2*l2;
        }
    }
    
    // "Square" does not fulfill API of "Rectangle" where l1 and l2 could vary independently
    // l1 == l2 is now an invariant for square
    static class Square extends Rectangle {
        
        public void setL1(double l1) { this.l1 = l1; this.l2 = l1; }
        public void setL2(double l2) { this.l1 = l2; this.l2 = l2; }
        
        public void setSize(double size) { setL1(size); }
        public double getSize() { return this.l1; }
    }
    
    public static void main(String[] args) {
        Rectangle rectangle = new Square();
        rectangle.setL1(3);
        rectangle.setL2(4);
        
        if (rectangle.perimeter() != 14) {
            System.out.println("This is not the perimeter expected for the rectangle");
        }
    }
}
