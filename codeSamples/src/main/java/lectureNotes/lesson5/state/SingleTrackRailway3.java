package lectureNotes.lesson5.state;

public class SingleTrackRailway3 {

    enum Side {
        A, B;
    }
    
    interface SignalingControl {
        
        void requestTrackAccess(Side side);
        
        void enterTrack();
        
        void releaseTrack();
    }
    
    // Sketch implementation of signaling control
    // The implementation is roughly the same as before but each event is managed directly by
    // the "signaling control" methods.
    // Do not bothered with concurrency issues here
    //
    // Every states share the same source file. If we have to change the behavior of any
    // "signaling control" state, we change the common source file. We encounter the same issue
    // to add new state.
    static class SignalingControlImpl implements SignalingControl {
        
        enum State {
            WAITING_REQUEST_ACCESS,
            WAITING_TRAIN_ENTER_TRACK,
            WAITING_RAILWAY_RELEASE;
        }
        
        State state = State.WAITING_REQUEST_ACCESS;
        
        @Override
        public void requestTrackAccess(Side side) {
            
            switch(state) {
            case WAITING_REQUEST_ACCESS:
                switch (side) {
                case A:
                    System.out.println("SIDE B light: red");
                    System.out.println("SIDE A light: green");
                    System.out.println("Set railraod switch");
                    state = State.WAITING_TRAIN_ENTER_TRACK;
                    break;
                case B:
                    System.out.println("SIDE A light: red");
                    System.out.println("SIDE B light: green");
                    System.out.println("Set railraod switch");
                    state = State.WAITING_TRAIN_ENTER_TRACK;
                    break;
                default:
                    throw new RuntimeException();
                }
                break;
            case WAITING_TRAIN_ENTER_TRACK:
                System.out.println("Access denied");
                break;
            case WAITING_RAILWAY_RELEASE:
                System.out.println("Access denied");
                break;
            default:
                throw new RuntimeException();
            }
        }
        
        @Override
        public void enterTrack() {
            switch(state) {
            case WAITING_REQUEST_ACCESS:
                System.out.println("Emergency STOP");
                break;
            case WAITING_TRAIN_ENTER_TRACK:
                System.out.println("SIDE A light: red");
                System.out.println("SIDE B light: red");
                state = State.WAITING_RAILWAY_RELEASE;
                break;
            case WAITING_RAILWAY_RELEASE:
                System.out.println("Emergency STOP");
                break;
            default:
                throw new RuntimeException();
            }
        }
        
        @Override
        public void releaseTrack() {
            
            switch(state) {
            case WAITING_REQUEST_ACCESS:
                System.out.println("Emergency STOP");
                break;
            case WAITING_TRAIN_ENTER_TRACK:
                System.out.println("Emergency STOP");
                break;
            case WAITING_RAILWAY_RELEASE:
                state = State.WAITING_REQUEST_ACCESS;
                break;
            default:
                throw new RuntimeException();
            }
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
