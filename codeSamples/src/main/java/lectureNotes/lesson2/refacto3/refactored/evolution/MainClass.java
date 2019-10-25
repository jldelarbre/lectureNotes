package lectureNotes.lesson2.refacto3.refactored.evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainClass {

    public static void main(String[] args) {
        ConditionHolder conditionHolder = new ConditionHolder(15);
        
        MyProcessorFactory myProcessorFactory = new MyProcessorFactory();
        MyProcessor myProcessor = myProcessorFactory.build(conditionHolder);
        
        myProcessor.compute();
    }
    
    // More conditions are added
    // thus ConditionHolder evolves
    static class ConditionHolder {
        private final int threshold;
        
        public ConditionHolder(int threshold) {
            this.threshold = threshold;
        }

        public boolean isFirstCondition() {
            return threshold > 10;
        }
        
        public boolean isSecondCondition() {
            return threshold > 20;
        }
        
        // ...
    }
    
    static class ObjectToProcess {
    }
    
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
    
    // We just have to add a new implementation for the new case 
    static class ThirdSubProcessor implements ISubProcessor {
        
        @Override
        public void process(ObjectToProcess objectToProcess) {
            // ...
        }
    }
    
    // 'MyProcessor' remains unchanged
    static class MyProcessor {
        
        List<ObjectToProcess> objectsToProcess = new ArrayList<>();
        ISubProcessor subProcessor;
        
        void compute() {
            for (ObjectToProcess objectToProcess : objectsToProcess) {
                subProcessor.process(objectToProcess);
            }
        }
    }
    
    // 'MyOtherProcessor' is another implementation of 'MyProcessor'
    // 'SubProcessors' are resused. It was not possible with the original implementation
    // where every 'subProcessors' implementation were mixed in the main loop
    static class MyOtherProcessor {
        
        List<ObjectToProcess> objectsToProcess = new ArrayList<>();
        ISubProcessor subProcessor;
        
        void compute() {
            for (ListIterator<ObjectToProcess> listIterator = objectsToProcess.listIterator();
                 listIterator.hasNext();) {
                
                int nextIndex = listIterator.nextIndex();
                ObjectToProcess objectToProcess = listIterator.next();
                if (nextIndex % 2 == 0) {
                    subProcessor.process(objectToProcess);
                }
            }
        }
    }
    
    // The factory wires the newly added 'subProcessor'
    static class MyProcessorFactory {
        
        MyProcessor build(ConditionHolder conditionHolder) {
            ISubProcessor subProcessor;
            if (conditionHolder.isFirstCondition()) {
                subProcessor = new FirstSubProcessor();
            } else if (conditionHolder.isSecondCondition()) {
                subProcessor = new SecondSubProcessor();
            } else {
                subProcessor = new ThirdSubProcessor();
            }
            
            MyProcessor myProcessor = new MyProcessor();
            myProcessor.subProcessor = subProcessor;
            // Add some objectToProcess
            
            return myProcessor;
        }
    }
}
