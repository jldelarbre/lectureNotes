package lectureNotes.lesson2.refacto3;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        ConditionHolder conditionHolder = new ConditionHolder();
        // The condition is never set elsewhere
        conditionHolder.setSomeCondition(true);
        
        MyProcessor myProcessor = new MyProcessor();
        myProcessor.conditionHolder = conditionHolder;
        // Add some objectToProcess
        
        myProcessor.compute();
    }
    
    static class ConditionHolder {
        private boolean someCondition;

        public boolean isSomeCondition() {
            return someCondition;
        }

        public void setSomeCondition(boolean someCondition) {
            this.someCondition = someCondition;
        }
        
        // ...
    }
    
    static class ObjectToProcess {
    }
    
    static class MyProcessor {
        
        ConditionHolder conditionHolder;
        List<ObjectToProcess> objectsToProcess = new ArrayList<>();
        
        void compute() {
            for (ObjectToProcess objectToProcess : objectsToProcess) {
                // The condition is constant for the method 'compute':
                // We mix two unrelated processes.
                // We can separate them and have two dedicated 'for' loop
                // for each cases
                if (conditionHolder.isSomeCondition()) {
                    firstProcessing(objectToProcess);
                } else {
                    secondProcessing(objectToProcess);
                }
            }
        }
        
        void firstProcessing(ObjectToProcess objectToProcess) {
            // ...
        }
        
        void secondProcessing(ObjectToProcess objectToProcess) {
            // ...
        }
    }
}
