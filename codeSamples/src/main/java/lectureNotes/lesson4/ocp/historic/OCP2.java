package lectureNotes.lesson4.ocp.historic;

public class OCP2 {
    
    // The FILE abstraction has been created to allow interaction of the system with
    // new kind of device without the need to reimplement all the code base.
    // Modifying behavior of existing library is done only by extending FILE abstraction.
    interface FILE {
        void open(String filename);
        void close();
        byte read();
        void write(byte in);
        void seek(int offset);
    }

    static class PunchedCard implements FILE {

        @Override public void open(String filename) {/* ... */}
        @Override public void close() {/* ... */}
        @Override public byte read() { return 0; }
        @Override public void write(byte in) {/* ... */}
        @Override public void seek(int offset) {/* ... */}
    }
    
    static class MagneticTape implements FILE {
        
        @Override public void open(String filename) {/* ... */}
        @Override public void close() {/* ... */}
        @Override public byte read() { return 0; }
        @Override public void write(byte in) {/* ... */}
        @Override public void seek(int offset) {/* ... */}
    }
    
    // A sketch implementation of UNIX copy module
    static class CopyModule {
        
        void copy(FILE source, FILE destination) {
            // ...
        }
    }
    
    // A sketch implementation of UNIX console
    static class Console {
        
        static private FILE stdin;
        static private FILE stdout;
        
        public static void systemStartup() {
            // Search installed devices on system...
            // stdin = ...;
            // stdout = ...;
        }
        
        byte getChar() {
            return stdin.read();
        }
        
        void puchar(byte in) {
            stdout.write(in);
        }
    }
}
