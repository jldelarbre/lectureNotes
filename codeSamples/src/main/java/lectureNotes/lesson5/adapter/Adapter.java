package lectureNotes.lesson5.adapter;

public class Adapter {

    // FireDepartement define an interface to process a fire alert
    interface FireDepartement {
        // fire distance in kms from channel beginning
        void processFireAlert(double fireDistance);
    }
    
    // This component implements directly FireDepartement interface
    static class FrenchFireDepartement implements FireDepartement {
        @Override
        public void processFireAlert(double fireDistance) {
            // ...
        }
    }

    // But sometimes, we have some component that exists for the same purpose
    // but does not implement the expected interface
    static class EnglishFireDepartement {
        // fire distance in miles from channel beginning
        public void putOutFire(double fireDistance) {
            // ...
        }
    }
    
    // In those cases, we use an adaptor to use the foreign component
    // Adapter pattern also has the advantage to let others use and implement component
    // with different interfaces than ours:
    // EnglishFireDepartement is a fire department which does implements FireDepartement interface
    static class EnglishFireDepartementAdapter implements FireDepartement {
        private EnglishFireDepartement englishFireDepartement;
        
        @Override
        public void processFireAlert(double fireDistance) {
            englishFireDepartement.putOutFire(fireDistance / 1.60934);
        }
    }
    
    // Client for FireDepartement 
    static class ChannelTunnelSafetyCenter {
        
        private FireDepartement frenchFireDepartement;
        private FireDepartement englishFireDepartement;
        
        public void channelTunnelMonitoring() {
            // Detect fire...
            double fireDistance = 32.;
            FireDepartement nearestFireDepartement = detectNearestFireDepartement();
            nearestFireDepartement.processFireAlert(fireDistance);
        }
        
        private FireDepartement detectNearestFireDepartement() {
            FireDepartement nearestFireDepartement;
            
            // French case
            nearestFireDepartement = frenchFireDepartement;
            
            // English case
            nearestFireDepartement = englishFireDepartement;
            
            return nearestFireDepartement;
        }
    }
}
