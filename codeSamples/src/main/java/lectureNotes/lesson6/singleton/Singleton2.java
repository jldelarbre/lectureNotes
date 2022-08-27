package lectureNotes.lesson6.singleton;

import org.junit.Test;

public class Singleton2 {

    // Quick and dirty singleton implementation
    static class GOFSingleton {
        
        private static GOFSingleton instance = null;
        
        private GOFSingleton() {}
        
        public static GOFSingleton getInstance() {
            if (instance == null) {
                instance = new GOFSingleton();
            }
            return instance;
        }
        
        public void doX() { System.out.println("doX"); }
        public void doY() { System.out.println("doY"); }
    }
    
    static class SingletonUser {
        public void someMethod1() {
            GOFSingleton.getInstance().doX();
        }
        
        public void someMethod2() {
            GOFSingleton.getInstance().doY();
        }
    }
    
    // - Tests are not independent !
    // - Unmockable dependency !
    public static class SingletonTest {
    	
    	private SingletonUser singletonUser = new SingletonUser();
        
    	@Test
        public void test1() {
            singletonUser.someMethod1();
        }
        
    	@Test
        public void test2() {
            singletonUser.someMethod2();
        }
    }
}
