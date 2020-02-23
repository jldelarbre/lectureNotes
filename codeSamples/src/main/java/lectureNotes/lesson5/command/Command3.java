package lectureNotes.lesson5.command;

import static lectureNotes.lesson5.command.Command3.RailLinePlannerAddAction.buildAddAction;

import java.util.Stack;

public class Command3 {
    
    // Component responsible to plan train movements on rail line

    interface RailLinePlanner {
        void addTrain(String trainId, String timeSlot);
        void removeTrain(String trainId, String timeSlot);
        void moveTrain(String trainId, String oldTimeSlot, String newTimeSlot);
    }

    // Component responsible to execute update of rail line planner
    // RailLinePlannerUpdateAction applies command pattern but just with one extension:
    // only one method + the undo method
    // --------------------------------------------------------------------------------
    
    interface RailLinePlannerUpdateAction {
        void execute();
        void undo();
    }
    
    static class RailLinePlannerAddAction implements RailLinePlannerUpdateAction {
        RailLinePlanner railLinePlanner;
        String trainId;
        String timeSlot;
        
        public static RailLinePlannerUpdateAction buildAddAction(String trainId, String timeSlot) {
            RailLinePlannerAddAction railLinePlannerAddAction = new RailLinePlannerAddAction();
            railLinePlannerAddAction.trainId = trainId;
            railLinePlannerAddAction.timeSlot = timeSlot;
            return railLinePlannerAddAction;
        }
        
        @Override
        public void execute() {
            railLinePlanner.addTrain(trainId, timeSlot);
        }
        
        @Override
        public void undo() {
            railLinePlanner.removeTrain(trainId, timeSlot);
        }
    }
    
    static class RailLinePlannerRemoveAction implements RailLinePlannerUpdateAction {
        RailLinePlanner railLinePlanner;
        String trainId;
        String timeSlot;
        
        public static RailLinePlannerUpdateAction buildRemoveAction(String trainId, String timeSlot) {
            RailLinePlannerRemoveAction railLinePlannerRemoveAction = new RailLinePlannerRemoveAction();
            railLinePlannerRemoveAction.trainId = trainId;
            railLinePlannerRemoveAction.timeSlot = timeSlot;
            return railLinePlannerRemoveAction;
        }
        
        @Override
        public void execute() {
            railLinePlanner.removeTrain(trainId, timeSlot);
        }
        
        @Override
        public void undo() {
            railLinePlanner.addTrain(trainId, timeSlot);
        }
    }
    
    static class RailLinePlannerMoveAction implements RailLinePlannerUpdateAction {
        RailLinePlanner railLinePlanner;
        String trainId;
        String oldTimeSlot;
        String newTimeSlot;
        
        public static RailLinePlannerUpdateAction buildMoveAction(String trainId, String oldTimeSlot, String newTimeSlot) {
            RailLinePlannerMoveAction railLinePlannerMoveAction = new RailLinePlannerMoveAction();
            railLinePlannerMoveAction.trainId = trainId;
            railLinePlannerMoveAction.oldTimeSlot = oldTimeSlot;
            railLinePlannerMoveAction.newTimeSlot = newTimeSlot;
            return railLinePlannerMoveAction;
        }
        
        @Override
        public void execute() {
            railLinePlanner.moveTrain(trainId, oldTimeSlot, newTimeSlot);
        }
        
        @Override
        public void undo() {
            railLinePlanner.moveTrain(trainId, newTimeSlot, oldTimeSlot);
        }
    }
    
    // Undo / Redo manager
    // -------------------
    
    static class UndoRedoManager {
        Stack<RailLinePlannerUpdateAction> undoableActions;
        Stack<RailLinePlannerUpdateAction> redoableActions;
        
        void executeNewAction(RailLinePlannerUpdateAction railLinePlannerUpdateAction) {
            redoableActions.clear();
            undoableActions.push(railLinePlannerUpdateAction);
            railLinePlannerUpdateAction.execute();
        }
        
        void undo() {
            RailLinePlannerUpdateAction popedActions = undoableActions.pop();
            redoableActions.push(popedActions);
            popedActions.undo();
        }
        
        void redo() {
            RailLinePlannerUpdateAction popedActions = redoableActions.pop();
            undoableActions.push(popedActions);
            popedActions.execute();
        }
    }
    
    // GUI
    // ---
    
    static class Gui {
        UndoRedoManager undoRedoManager;
        
        void handleGuiActionAdd() {
            // Build RailLinePlannerUpdateAction with user entries
            RailLinePlannerUpdateAction railLinePlannerUpdateAction = buildAddAction("trainId", "timeSlot");
            
            // Call application core
            undoRedoManager.executeNewAction(railLinePlannerUpdateAction);
        }
        
        void handleGuiActionUndo() {
            undoRedoManager.undo();
        }
        
        void handleGuiActionRedo() {
            undoRedoManager.redo();
        }
    }
}
