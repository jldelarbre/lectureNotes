package lectureNotes.lesson4.lsp;

public class LSP3 {

    // Constraints on method signature with Liskov substitution principle
    // Contravariance of method arguments in the subtype.
    // Covariance of return types in the subtype.
    
    static class A {
    }
    
    static class B extends A {
    }
    
    static class C extends B {
    }
    
    static interface Service {
        B action(B b);
    }
    
    static interface DerivedService extends Service {
        @Override
        C action(B b);
        
        // Expect @Override but only overloading
        B action(A a);
    }
}
