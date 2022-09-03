package lectureNotes.lesson7.library.p1;

////////////////////////////////////////////////////
// Client depends on Service library (p2 package) //
////////////////////////////////////////////////////

import lectureNotes.lesson7.library.p2.Service;

public class Client {

    Service service;
    
    void clientMethod() {
        
        // ...
        
        ///////////////////////////////////////////////////////////
        // Client calls Service library (executed in p2 package) //
        ///////////////////////////////////////////////////////////
        
        service.doSomething();
        
        // ...
        
    }
}
