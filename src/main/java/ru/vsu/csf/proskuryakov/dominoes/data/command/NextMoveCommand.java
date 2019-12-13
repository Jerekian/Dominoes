package ru.vsu.csf.proskuryakov.dominoes.data.command;

import ru.vsu.csf.proskuryakov.dominoes.core.GameState;
import ru.vsu.csf.proskuryakov.dominoes.gui.Dominoes;
import ru.vsu.csf.proskuryakov.dominoes.gui.GameController;

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
            commandDescription = GameController.nextMove(gameState);
            Dominoes.app.getUndoCommandHistory().clear();
            //GameController.addCommandToListOfTeams(move);
        }catch (Error e){
            return false;
        }
        return true;
    }


}
