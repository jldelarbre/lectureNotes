package lectureNotes.lesson7.plugin.p1;

////////////////////////////////////////////
// Client no longer depends on p2 package //
////////////////////////////////////////////

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
