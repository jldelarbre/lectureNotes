package lectureNotes.lesson2.refacto3.refactored;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        ConditionHolder conditionHolder = new ConditionHolder(true);
        
        MyProcessorFactory myProcessorFactory = new MyProcessorFactory();
        MyProcessor myProcessor = myProcessorFactory.build(conditionHolder);
        
        myProcessor.compute();
    }
    
    static class ConditionHolder {
        // We clearly show with final that it is constant
        // hence we can extract 'isSomeCondition' from the for loop of MyProcessor.compute
        private final boolean someCondition;
        
        public ConditionHolder(boolean someCondition) {
            this.someCondition = someCondition;
        }

        public boolean isSomeCondition() {
            return someCondition;
        }
        
        // ...
    }
    
    static class ObjectToProcess {
    }
    
    // We define an abstraction the 2 'subProcessor'
    interface ISubProcessor {
        void process(ObjectToProcess objectToProcess);
    }
    
    static class FirstSubProcessor implements ISubProcessor {

        @Override
        public void process(ObjectToProcess objectToProcess) {
            // ...
        }
    }
    
    static class SecondSubProcessor implements ISubProcessor {
        
        @Override
        public void process(ObjectToProcess objectToProcess) {
            // ...
        }
    }
    
    // MyProcessor is a concrete class, it would be better to make it inherit from an interface
    // but we can't refactor the whole application in one time
    // Choose a point where to stop refactoring and keep it for another time
    static class MyProcessor {
        
        List<ObjectToProcess> objectsToProcess = new ArrayList<>();
        ISubProcessor subProcessor;
        
        // 'compute' depends only on a 'subProcessor' abstraction
        // It no longer need to select the right 'subProcessor'
        void compute() {
            for (ObjectToProcess objectToProcess : objectsToProcess) {
                subProcessor.process(objectToProcess);
            }
        }
        
        // Here another kind of smell which warn you about bad non reusable code
        void computeAnotherBadImplementation() {
            for (ObjectToProcess objectToProcess : objectsToProcess) {
            	// An "if comb" is often a smell
                if (subProcessor instanceof FirstSubProcessor) {
                    FirstSubProcessor firstSubProcessor = (FirstSubProcessor) subProcessor;
                    firstSubProcessor.process(objectToProcess);
                } else if (subProcessor instanceof SecondSubProcessor) {
                    SecondSubProcessor secondSubProcessor = (SecondSubProcessor) subProcessor;
                    secondSubProcessor.process(objectToProcess);
                }
            }
        }
    }
    
    static class MyProcessorFactory {
        
        // The factory aims to wire the application, not only it build objects
        // but it also connects them together selecting the right implementation (wiring)
        MyProcessor build(ConditionHolder conditionHolder) {
            ISubProcessor subProcessor;
            if (conditionHolder.isSomeCondition()) {
                subProcessor = new FirstSubProcessor();
            } else {
                subProcessor = new SecondSubProcessor();
            }
            
            MyProcessor myProcessor = new MyProcessor();
            myProcessor.subProcessor = subProcessor;
            // Add some objectToProcess
            
            return myProcessor;
        }
    }
}
