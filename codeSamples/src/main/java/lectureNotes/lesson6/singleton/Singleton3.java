package lectureNotes.lesson6.singleton;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class Singleton3 {

    @Singleton
    private static class GuiceSingleton {
        
        private GuiceSingleton() {}
        
        public void doX() { System.out.println("doX"); }
        public void doY() { System.out.println("doY"); }
    }
    
    static class SingletonUser {
        
        @Inject
        GuiceSingleton guiceSingleton;
        
        public void someMethod1() {
            guiceSingleton.doX();
        }
        
        public void someMethod2() {
            guiceSingleton.doY();
        }
    }
    
    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        SingletonUser singletonUser = injector.getInstance(SingletonUser.class);
        SingletonUser singletonUser2 = injector.getInstance(SingletonUser.class);
        
        singletonUser.someMethod1();
        singletonUser2.someMethod2();
    }
    
    // - Tests are independent !
    // - Mockable dependency !
    public static class SingletonTest {
        
        static class SingletonTestModule extends AbstractModule {
            
            @Provides // @Singleton
            GuiceSingleton provideGuiceSingleton() {
                GuiceSingleton mockSingleton = mock(GuiceSingleton.class);
                
                doAnswer(invocation -> {
                    System.out.println("doX - mocked");
                    return null;
                }).when(mockSingleton).doX();
                
                doAnswer(invocation -> {
                    System.out.println("doY - mocked");
                    return null;
                }).when(mockSingleton).doY();
                
                return mockSingleton;
            }
        }
        
        private static final Injector injector = Guice.createInjector(new SingletonTestModule());
        
        private SingletonUser singletonUser = injector.getInstance(SingletonUser.class);
        
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
