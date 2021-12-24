TEST_F(MyFeature_Test, myUseCase)
{
    TasMock tasMock = tasMockOfDataModel("");

    checkScxmlExecution(
        "myUseCase.scxml",
        {
            [](ScxmlStateMachine& stateMachine) {
                return stateMachineCheckerOf(stateMachine)
                ->withChildrenCountOf(4)
                ->withFollowingStates({"state1", "state2"});
            },

            [tasMock](ScxmlStateMachine&) {
                return rpcCallCheckerOf(tasMock)
                ->withRpcCallCountOf(1)
                ->expectingMyFeatureInvokedWith(param1, param2);
            },

            [](ScxmlStateMachine& stateMachine) {
                return eventCheckerOf(stateMachine)
                    ->expectingEvent({"myEvent",
                                      {{"eventParam1", "someValue"},
                                       {"eventParam2", "otherValue"}}});
            },

            [this](ScxmlStateMachine&) {
                return logCheckerOf(logger)
                ->expectingLogMessages({
                    "my message"
                });
            }
        }
    );
}
