package lectureNotes.lesson5.state;

public class SingleTrackRailway4 {

    enum Side {
        A, B;
    }
    
    interface SignalingControl {
        
        void requestTrackAccess(Side side);
        
        void enterTrack();
        
        void releaseTrack();
    }
    
    // Here we have a "canonical" state pattern implementation. It is flexible and each state is
    // Loosely coupled with each other.
    // It is fine but this implementation is cumbersome for simple case as here.
    // As long as an API for a service is well defined, there is room for implementation
    // scalability. Start with the simplest implementation as the one before (SingleTrackRailway3)
    // and the requirement evolve move to a more powerful implementation as State pattern when
    // it is really relevant
    static class SignalingControlImpl implements SignalingControl {
        
        static final SignalingControlState WAITING_REQUEST_ACCESS_STATE = new WaitingRequestAccessState();
        static final SignalingControlState WAITING_TRAIN_ENTER_TRACK_STATE = new WaitingTrainEnterTrackState();
        static final SignalingControlState WAITING_RAILWAY_RELEASE_STATE = new WaitingRailwayReleaseState();
        
        SignalingControlState state = WAITING_REQUEST_ACCESS_STATE;
        
        @Override
        public void requestTrackAccess(Side side) {
            state = state.requestTrackAccess(side);
        }
        
        @Override
        public void enterTrack() {
            state = state.enterTrack();
        }
        
        @Override
        public void releaseTrack() {
            state = state.releaseTrack();
        }
    }
    
    interface SignalingControlState {
        
        SignalingControlState requestTrackAccess(Side side);
        
        SignalingControlState enterTrack();
        
        SignalingControlState releaseTrack();
    }
    
    static class WaitingRequestAccessState implements SignalingControlState {

        @Override
        public SignalingControlState requestTrackAccess(Side side) {
            switch (side) {
            case A:
                System.out.println("SIDE B light: red");
                System.out.println("SIDE A light: green");
                System.out.println("Set railraod switch");
                return SignalingControlImpl.WAITING_TRAIN_ENTER_TRACK_STATE;
            case B:
                System.out.println("SIDE A light: red");
                System.out.println("SIDE B light: green");
                System.out.println("Set railraod switch");
                return SignalingControlImpl.WAITING_TRAIN_ENTER_TRACK_STATE;
            default:
                throw new RuntimeException();
            }
        }

        @Override
        public SignalingControlState enterTrack() {
            System.out.println("Access denied");
            return this;
        }

        @Override
        public SignalingControlState releaseTrack() {
            System.out.println("Access denied");
            return this;
        }
    }
    
    static class WaitingTrainEnterTrackState implements SignalingControlState {
        
        @Override
        public SignalingControlState requestTrackAccess(Side side) {
            System.out.println("Emergency STOP");
            return this;
        }
        
        @Override
        public SignalingControlState enterTrack() {
            System.out.println("SIDE A light: red");
            System.out.println("SIDE B light: red");
            return SignalingControlImpl.WAITING_RAILWAY_RELEASE_STATE;
        }
        
        @Override
        public SignalingControlState releaseTrack() {
            System.out.println("Emergency STOP");
            return this;
        }
    }
    
    static class WaitingRailwayReleaseState implements SignalingControlState {
        
        @Override
        public SignalingControlState requestTrackAccess(Side side) {
            System.out.println("Emergency STOP");
            return this;
        }
        
        @Override
        public SignalingControlState enterTrack() {
            System.out.println("Emergency STOP");
            return this;
        }
        
        @Override
        public SignalingControlState releaseTrack() {
            return SignalingControlImpl.WAITING_REQUEST_ACCESS_STATE;
        }
    }
    
    static class Train {
        SignalingControl signalingControl = new SignalingControlImpl();
        
        void run() {
            // ...
            signalingControl.requestTrackAccess(Side.A);
            waitGreenLight();
            
            start();
            signalingControl.enterTrack();
            
            goThroughTrack();
            signalingControl.releaseTrack();
        }
        
        private void waitGreenLight() {/*...*/}
        private void start() {/*...*/}
        private void goThroughTrack() {/*...*/}
    }
}
