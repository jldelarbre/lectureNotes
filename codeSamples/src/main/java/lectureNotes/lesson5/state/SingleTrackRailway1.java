package lectureNotes.lesson5.state;

public class SingleTrackRailway1 {
    
    // Events associated with the "signaling control" FSM (finite state machine) 
    enum Event {
        REQUEST_TRACK_ACCESS_SIDE_A,
        REQUEST_TRACK_ACCESS_SIDE_B,
        ENTER_TRACK,
        RELEASE_TRACK;
    }
    
    // The signaling control processes the incoming events and controls signals and switchs on
    // the railway
    interface SignalingControl {
        
        void handleEvent(Event event);
    }
    
    // Sketch implementation of signaling control
    // Do not bothered with concurrency issues here
    static class SignalingControlImpl implements SignalingControl {
        
        // Signaling control behavior depends on its state
        enum State {
            WAITING_REQUEST_ACCESS,
            WAITING_TRAIN_ENTER_TRACK,
            WAITING_RAILWAY_RELEASE;
        }
        
        State state = State.WAITING_REQUEST_ACCESS;
        
        // handle event implementation is a double switch on state and incoming event
        @Override
        public void handleEvent(Event event) {
            
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
    // The client has to manage and raise event
    // This is an implementation that it has has to cope with
    // A service has not to expose its internals in anyway but rather expose an API
    // suitable for its clients
    static class Train {
        SignalingControl signalingControl = new SignalingControlImpl();
        
        void run() {
            // ...
            signalingControl.handleEvent(Event.REQUEST_TRACK_ACCESS_SIDE_A);
            waitGreenLight();
            
            start();
            signalingControl.handleEvent(Event.ENTER_TRACK);
            
            goThroughTrack();
            signalingControl.handleEvent(Event.RELEASE_TRACK);
        }
        
        private void waitGreenLight() {/*...*/}
        private void start() {/*...*/}
        private void goThroughTrack() {/*...*/}
    }
}
