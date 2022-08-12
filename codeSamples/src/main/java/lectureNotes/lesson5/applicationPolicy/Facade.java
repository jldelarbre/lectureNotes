package lectureNotes.lesson5.applicationPolicy;

import java.util.List;
import java.util.Optional;

public class Facade {

    interface Station {}
    
    interface Route {
        // Route with a departure, an arrival and optionally some intermediate points
        List<Station> getStations();
    }
    
    interface DiscountCard {}
    
    interface Travel {
        Route getOutwardTravel();
        Route getReturnTravel();
        
        Optional<DiscountCard> getDiscountCard();
    }
    
    interface Ticket {
        Travel getTravel();
        
        String getPaymentMean();
        
        double getPrice();
    }
    
    // Sells terminal is a facade that drives the process to purchase a ticket
    // The global way of the process is imposed: imposed policy
    // It hides the details like:
    // - building the route
    // - Applying discount policy
    // - Receive payment
    
    // REQUIRE A WELL DEFINED BUSINESS MODEL
    interface SellsTerminal {
        
        Route chooseRoute(String departure, String arrival);
        
        Travel selectDiscountCard(Optional<DiscountCard> discountCard, Route route);
        
        Ticket pay(Travel travel, String PaymentMean);
    }
    
    static class SellsTerminalImp implements SellsTerminal {
        
        RouteBuilder routeBuilder;
        Discounter discounter;
        PaymentService paymentService;

        @Override
        public Route chooseRoute(String departure, String arrival) {
            Route route = null;
            // Build route using routeBuilder ...
            // Choose direct route, connecting points, multimodal transport...
            return route;
        }

        @Override
        public Travel selectDiscountCard(Optional<DiscountCard> discountCard, Route route) {
            Travel travel = null;
            // Compute Ticket using discounter according to the given credit card
            return travel;
        }

        @Override
        public Ticket pay(Travel travel, String PaymentMean) {
            Ticket ticket = null;
            // Use paymentService with the given payment mean in order to deliver a ticket
            return ticket;
        }
    }
    
    // Real services that are responsible for: route building, discount and payment
    static class RouteBuilder{/* ... */}
    static class Discounter{/* ... */}
    static class PaymentService{/* ... */}
    
    // SellsTerminal client: Ticket purchase is quite simple for the client. Details are
    // hidden (route, discount, payment). Furthermore the policy is imposed by the facade
    // There is only one way to purchase a ticket.
    // ----------------------------------------------------------------------------------
    
    static class Gui {
        
        SellsTerminal sellsTerminal;
        
        @SuppressWarnings("unused")
        void handleEventUserBuyTicket(String departure, String arrival) {
            Route route = sellsTerminal.chooseRoute(departure, arrival);
            
            // Ask user for a discount card...
        }
        
        @SuppressWarnings("unused")
        void handleEventUserGiveDiscount(Optional<DiscountCard> discountCard, Route route) {
            Travel travel = sellsTerminal.selectDiscountCard(discountCard, route);
            
            // Launch payment session
        }
        
        @SuppressWarnings("unused")
        void handleEventUserGivePaymentMean(String paymentMean, Travel travel) {
            Ticket ticket = sellsTerminal.pay(travel, paymentMean);
            
            // Print ticket
        }
    }
}
