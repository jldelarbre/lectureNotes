package lectureNotes.lesson2.refacto4;

public class MainClass {

    public static void main(String[] args) {
        MyProcessor myProcessor = new MyProcessor();
        
        myProcessor.compute();
    }
    
    static class ObjectToProcess {
    }
    
    static class SubProcessor {

        public void process(ObjectToProcess objectToProcess) {
            // ...
        }
    }
    
    static class MyProcessor {
        
        ObjectToProcess objectToProcess;
        SubProcessor subProcessor = new SubProcessor();
        
        void compute() {
            subProcessor.process(objectToProcess);
        }
    }
}
