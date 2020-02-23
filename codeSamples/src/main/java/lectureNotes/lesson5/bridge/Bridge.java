package lectureNotes.lesson5.bridge;

import java.util.Optional;

public class Bridge {

    interface TicketValidator {
        void validateTicket(String ticketId, Optional<String> discountCardId);
    }
    
    interface DiscountCardValidator {
        void validateDiscountCard(String cardId);
    }
    
    // Ticket validator abstraction
    // Operation for discount card validation is implemented by a collaborator of AbstractTicketValidator
    // The protected method validateDiscountCard allows inherited implementation to call
    // discount card validation
    // AbstractTicketValidator and TicketValidatorImpl can vary independently
    static abstract class AbstractTicketValidator implements TicketValidator {
        
        private DiscountCardValidator discountCardValidator;
        
        public void setDiscountCardValidator(DiscountCardValidator discountCardValidator) {
            this.discountCardValidator = discountCardValidator;
        }

        protected void validateDiscountCard(String discountCardId) {
            discountCardValidator.validateDiscountCard(discountCardId);
        }
    }
    
    // TicketValidatorImpl can use discount card operation without bothering with
    // implementation of this
    static class TicketValidatorImpl extends AbstractTicketValidator {
        
        @Override
        public void validateTicket(String ticketId, Optional<String> discountCardId) {
            // Validate ticket...
            if (discountCardId.isPresent()) {
                validateDiscountCard(discountCardId.get());
            }
        }
        
        @Override
        public void validateDiscountCard(String cardId) {
            // Validate discount card...
        }
    }
    
    // 2 implementors of DiscountCardValidator
    
    static class SncfDiscountCardValidator implements DiscountCardValidator {
        @Override
        public void validateDiscountCard(String cardId) {
            // Validate discount card...
        }
    }
    
    static class PrivateCompanyDiscountCardValidator implements DiscountCardValidator {
        @Override
        public void validateDiscountCard(String cardId) {
            // Validate discount card...
        }
    }
    
    // Ticket validator utilization sample
    
    public static void main(String[] args) {
        DiscountCardValidator sncfDiscountCardValidator = new SncfDiscountCardValidator();
        
        AbstractTicketValidator ticketValidator = new TicketValidatorImpl();
        ticketValidator.setDiscountCardValidator(sncfDiscountCardValidator);
        
        TicketValidator toulouseStationTicketValidator = ticketValidator;
        
        // Travelers use ticket validator
        toulouseStationTicketValidator.validateTicket("ticketId", Optional.of("cardId"));
        
        
        // Opening to competition of Toulouse station, concession won by another company
        DiscountCardValidator privateCompanyDiscountCardValidator = new PrivateCompanyDiscountCardValidator();
        ticketValidator.setDiscountCardValidator(privateCompanyDiscountCardValidator);
        
        // Travelers still use ticket validator
        toulouseStationTicketValidator.validateTicket("ticketId", Optional.of("cardId"));
    }
}
