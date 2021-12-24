test myFeature for myUseCase:
    Using TasMock
    Execute scxml file: myUseCase.scxml
    Expect results:
        state machine created:
            4 children,
            states: state1, state2
        1 RPC call(s) on remote system:
            on myFeature
            parameters: param1, param2
        1 event(s) raised
            named myEvent
            parameters: eventParam1 = someValue, eventParam2 = otherValue
        1 message(s) logged
            my message
