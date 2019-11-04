package ru.vsu.csf.proskuryakov.data.command;

import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.gui.GameController;

public class NextMoveCommand extends Command {


    public NextMoveCommand(GameState gameState) {
        super(gameState);
    }

    public NextMoveCommand(GameState gameState, String jsonString, String commandDescriptione) {
        super(gameState, jsonString, commandDescriptione);
    }

    @Override
    public boolean execute() {
        try {
            backup();
            commandDescriptione = GameController.nextMove(gameState);
            //GameController.addCommandToListOfTeams(move);
        }catch (Error e){
            return false;
        }
        return true;
    }


}
