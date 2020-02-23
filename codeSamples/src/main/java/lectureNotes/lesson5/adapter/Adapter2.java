package lectureNotes.lesson5.adapter;

public class Adapter2 {

    // Adapter pattern could be used to ease composition and multiple interface implementation
    interface TicketPuncher {
        void punchTicket(String ticketId);
    }
    
    interface DiscountCardValidator {
        void validateDiscountCard(String cardId);
    }
    
    // TicketValidator implements 2 interfaces. DiscountCardValidator is implemented using
    // composition. Adapter allows to use more components for this composition
    static class TicketValidator implements TicketPuncher, DiscountCardValidator {
        
        private DiscountCardValidator discountCardValidator;
        
        @Override
        public void punchTicket(String ticketId) {
            // Validate ticket...
        }
        
        @Override
        public void validateDiscountCard(String cardId) {
            discountCardValidator.validateDiscountCard(cardId);
        }
    }
    
    // Component directly usable for composition
    static class SncfDiscountCardValidator implements DiscountCardValidator {
        @Override
        public void validateDiscountCard(String cardId) {
            // Validate discount card...
        }
    }
    
    // Adapter makes composition possible 
    static class ThirdPartyDiscountCardValidatorAdapter implements DiscountCardValidator {
        private ThirdPartyDiscountCardValidator thirdPartdiscountCardValidator;
        
        @Override
        public void validateDiscountCard(String cardId) {
            thirdPartdiscountCardValidator.validateCard(cardId);
        }
    }
    
    // Component NOT directly usable for composition
    static class ThirdPartyDiscountCardValidator {
        public void validateCard(String cardId) {
            // Validate discount card...
        }
    }
}
