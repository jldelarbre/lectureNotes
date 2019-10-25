package lectureNotes.lesson2.refacto2;

import java.util.Date;

public class Refacto2 {

    static class MyObject {
        
        private String myState = "initialState";
        
        // Avoid to pass a parameter for output
        // Do not change state of input parameters
        public void doSomething (Date date) {
            long currentTimeMillis = System.currentTimeMillis() + 3600 * 1000;
            date.setTime(currentTimeMillis);
        }
        
        // A method either compute something and return the result: function,
        // or change object state: procedure
        public int malformedMethod(int input) {
            myState = myState.concat("Updated");
            return input * myState.length();
        }
    }
    
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        
        Date date = new Date();
        
        myObject.doSomething(date);
        System.out.println(date);
        
        int result = myObject.malformedMethod(5);
        System.out.println(result);
    }
}
