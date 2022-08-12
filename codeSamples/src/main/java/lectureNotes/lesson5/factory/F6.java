package lectureNotes.lesson5.factory;

public class F6 {

    ////////////////////////////////
    // Business layer API package //
    ////////////////////////////////
	
    interface RailNetwork {
        void transportPeople();
    }
    
    interface Ticket {
    	void checkValidity();
    	
    	// ABSTRACT FACTORY
    	interface Factory {
    		Ticket build();
    	}
    }

    ///////////////////////////////////////////
    // Business layer implementation package //
    ///////////////////////////////////////////
    
    static class RailNetworkImp implements RailNetwork {
    	
    	private final Ticket.Factory ticketFactory;

        private RailNetworkImp(Ticket.Factory ticketFactory) {
        	this.ticketFactory = ticketFactory;
        }
        
        @Override
        public void transportPeople() {
        	// Business layer may need to build object but is not allowed
        	// to instantiate them:
        	//     - This the role of factories => it has a factory as dependency
        	//
        	// Business layer shall not be statically linked to any dependency:
        	//     - The factory is abstract
        	//
        	// => GOOD DESIGN: SRP, OCP, LSP, DIP
        	// !!! TESTABLE CODE !!!
        	
        	// ...
        	Ticket ticket = ticketFactory.build();
        	// ...
        	ticket.checkValidity();
        }
    }
    
    static class TicketImpl implements Ticket {
    	@Override
    	public void checkValidity() { /* ... */ }
    	
    	static class Factory implements Ticket.Factory {
    		@Override
    		public Ticket build() {
    			return new TicketImpl();
    		}
    	}
    }
    
    // THE FACTORY METHOD
    public static RailNetwork buildRailNetwork() {
    	Ticket.Factory ticketFactory = new TicketImpl.Factory();
    	return new RailNetworkImp(ticketFactory);
    }
    
    /////////////////
    // Application //
    /////////////////
    
    public static void main(String[] args) {
        RailNetwork railway = buildRailNetwork();
        railway.transportPeople();
    }
}
