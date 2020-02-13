package lectureNotes.lesson4.lsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LSP5 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        // Concrete type is UnmodifiableList which inherits from List
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        
        // UnsupportedOperationException thrown
        // Violation of Liskov substitution principle:
        //
        // - No new exceptions should be thrown by methods of the subtype
        // - Subtype inherits method from super type that does not make sense for it
        // - ISP: read and write methods are altogether in the same interface even if
        //        not every client are concerned about them
        //
        // Some violation of LSP, ISP... are accepted as compromise
        unmodifiableList.add("a string");
    }
}
