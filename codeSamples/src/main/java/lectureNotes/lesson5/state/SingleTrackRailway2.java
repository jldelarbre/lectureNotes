package lectureNotes.lesson5.state;

public class SingleTrackRailway2 {

    enum Side {
        A, B;
    }
    
    // The "signaling control" API is refactored in way which no longer shows its internals
    // but rather expose an API suitable for its client
    interface SignalingControl {
        
        void requestTrackAccess(Side side);
        
        void enterTrack();
        
        void releaseTrack();
    }
    
    // Sketch implementation of signaling control
    // The implementation is the same as before but states and events are completely manage
    // internally
    // Do not bothered with concurrency issues here
    static class SignalingControlImpl implements SignalingControl {
        
        enum State {
            WAITING_REQUEST_ACCESS,
            WAITING_TRAIN_ENTER_TRACK,
            WAITING_RAILWAY_RELEASE;
        }
        
        enum Event {
            REQUEST_TRACK_ACCESS_SIDE_A,
            REQUEST_TRACK_ACCESS_SIDE_B,
            ENTER_TRACK,
            RELEASE_TRACK;
        }
        
        State state = State.WAITING_REQUEST_ACCESS;
        
        @Override
        public void requestTrackAccess(Side side) {
            handleEvent(side.equals(Side.A) ? Event.REQUEST_TRACK_ACCESS_SIDE_A : Event.REQUEST_TRACK_ACCESS_SIDE_B);
        }
        
        @Override
        public void enterTrack() {
            handleEvent(Event.ENTER_TRACK);
        }
        
        @Override
        public void releaseTrack() {
            handleEvent(Event.RELEASE_TRACK);
        }
        
        private void handleEvent(Event event) {
            
            switch(state) {
            case WAITING_REQUEST_ACCESS:
                switch (event) {
                case REQUEST_TRACK_ACCESS_SIDE_A:
                    System.out.println("SIDE B light: red");
                    System.out.println("SIDE A light: green");
                    System.out.println("Set railraod switch");
                    state = State.WAITING_TRAIN_ENTER_TRACK;
                    break;
                case REQUEST_TRACK_ACCESS_SIDE_B:
                    System.out.println("SIDE A light: red");
                    System.out.println("SIDE B light: green");
                    System.out.println("Set railraod switch");
                    state = State.WAITING_TRAIN_ENTER_TRACK;
                    break;
                case ENTER_TRACK:
                case RELEASE_TRACK:
                    System.out.println("Emergency STOP");
                    break;
                default:
                    throw new RuntimeException();
                }
                break;
            case WAITING_TRAIN_ENTER_TRACK:
                switch (event) {
                case REQUEST_TRACK_ACCESS_SIDE_A:
                case REQUEST_TRACK_ACCESS_SIDE_B:
                    System.out.println("Access denied");
                    break;
                case ENTER_TRACK:
                    System.out.println("SIDE A light: red");
                    System.out.println("SIDE B light: red");
                    state = State.WAITING_RAILWAY_RELEASE;
                    break;
                case RELEASE_TRACK:
                    System.out.println("Emergency STOP");
                    break;
                default:
                    throw new RuntimeException();
                }
                break;
            case WAITING_RAILWAY_RELEASE:
                switch (event) {
                case REQUEST_TRACK_ACCESS_SIDE_A:
                case REQUEST_TRACK_ACCESS_SIDE_B:
                    System.out.println("Access denied");
                    break;
                case ENTER_TRACK:
                    System.out.println("Emergency STOP");
                    break;
                case RELEASE_TRACK:
                    state = State.WAITING_REQUEST_ACCESS;
                    break;
                default:
                    throw new RuntimeException();
                }
                break;
            default:
                throw new RuntimeException();
            }
        }
    }
    
    // Here train is set as a signaling control client
    // The API fits the client needs, now we can refactor "signaling control" implementation
    // at will without  any impact
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
