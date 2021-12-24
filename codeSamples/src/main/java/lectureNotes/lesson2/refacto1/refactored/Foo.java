package lectureNotes.lesson2.refacto1.refactored;

import java.util.Optional;

public class Foo {

    private final int param1;
    private final int param2;
    // When a parameter may be used or not, use Optional instead of null
    private final Optional<String> paramString;
    
    // It's easier to analyze this class, its state does not change.
    // Interaction with other objects are simplified without side effects
    // The constructor initialize Foo completely which could no longer be partially
    // initialized
    public Foo(int param1, int param2, Optional<String> paramString) {
        super();
        this.param1 = param1;
        this.param2 = param2;
        this.paramString = paramString;
    }

    public int doWork() {
        return param1 + param2;
    }
    
    public int doOtherWork() {
    	// With optional you are forced to clearly assume that paramString is initialized
        return param1 + paramString.get().length();
    }
    
    public int doThirdWork() {
    	// With optional reference validity could not be forgotten
        if (paramString.isPresent()) {
            return param1 + paramString.get().length();
        }
        return param1;
    }
    
    public int getParam1() {
        return param1;
    }
    public int getParam2() {
        return param2;
    }
    public Optional<String> getParamString() {
        return paramString;
    }
}
