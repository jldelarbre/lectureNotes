package lectureNotes.lesson2.refacto2.refactored;

import java.util.Date;

public class Refacto2 {

    static class MyObject {
        
        private String myState = "initialState";
        
        public Date doSomething() {
            Date date = new Date();
            long currentTimeMillis = System.currentTimeMillis() + 3600 * 1000;
            date.setTime(currentTimeMillis);
            
            return date;
        }

        // A procedure change the state of object
        public void stateChanger() {
            myState = myState.concat("Updated");
        }
        
        // A function always follows this scheme:
        // INPUT - PROCESS - OUTPUT
        // and do not make side effect
        public int processing(int input) {
            return input * myState.length();
        }
    }
    
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        
        Date date = myObject.doSomething();
        System.out.println(date);
        
        myObject.stateChanger();
        int result = myObject.processing(5);
        System.out.println(result);
    }
}
