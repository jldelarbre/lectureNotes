package lectureNotes.lesson4.srp.historic;

public class SRP2 {

    // A sketch implementation of Multics (started in 1964)
    // The characters sent to the printer module hold only information about text:
    // the printable characters, space and new line (\n)
    // A driver manages the printer device and translates request for new line
    // in mechanical actions
    static class MulticsPrinterModule {
        
        PrintDriver printDriver;
        
        public MulticsPrinterModule(PrintDriver printDriver) {
            this.printDriver = printDriver;
        }

        // Print a string that contains only alphanumeric characters, punctuation symbols and space
        void printReadableString(String readableString) {
            printDriver.printString(readableString);
        }
        
        void nextLine() {
            printDriver.nextLine();
        }
    }
    
    interface PrintDriver {
        void printString(String str);
        
        void nextLine();
    }
    
    static class TeletypeASR33PrintDriver implements PrintDriver {

        @Override
        public void printString(String str) {
            // Print the string
        }

        @Override
        public void nextLine() {
            // Send CR and LF commands
        }
        
    }
}
