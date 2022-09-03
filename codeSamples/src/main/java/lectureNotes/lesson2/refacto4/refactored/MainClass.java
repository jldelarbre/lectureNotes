package lectureNotes.lesson2.refacto4.refactored;

public class MainClass {

    public static void main(String[] args) {
        MyProcessorFactory myProcessorFactory = new MyProcessorFactory();
        
        MyProcessor myProcessor = myProcessorFactory.build();
        
        myProcessor.compute();
    }
    
    static class ObjectToProcess {
    }
    
    interface ISubProcessor {
        void process(ObjectToProcess objectToProcess);
    }
    
    static class SubProcessor implements ISubProcessor {

        @Override
        public void process(ObjectToProcess objectToProcess) {
            // ...
        }
    }
    
    static class MyProcessor {
        
        ObjectToProcess objectToProcess;
        private final ISubProcessor subProcessor;
        
        public MyProcessor(ISubProcessor subProcessor) {
            this.subProcessor = subProcessor;
        }
        
        void compute() {
            subProcessor.process(objectToProcess);
        }
    }
    
    // The factory wires SubProcessor to MyProcessor
    static class MyProcessorFactory {
        
        MyProcessor build() {
            MyProcessor myProcessor = new MyProcessor(new SubProcessor());
            return myProcessor;
        }
    }
}
