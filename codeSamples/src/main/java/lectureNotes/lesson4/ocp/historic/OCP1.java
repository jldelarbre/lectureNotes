package lectureNotes.lesson4.ocp.historic;

public class OCP1 {

    static class PunchedCard {/* ... */}
    
    // A sketch implementation of UNIX copy module in late 1950s
    static class PunchedCardCopyModule {
        
        void copy(PunchedCard source, PunchedCard destination) {
            // Make copy...
        }
    }
    
    static class MagneticTape {/* ... */}
    
    // A sketch implementation of UNIX copy module for tape device
    static class MagneticTapeCopyModule {
        
        void copy(MagneticTape source, MagneticTape destination) {
            // Need to rewrite all copy code for Magnetic tape ?
        }
    }
}
