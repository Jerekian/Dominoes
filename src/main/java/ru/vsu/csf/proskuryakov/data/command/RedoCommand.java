package ru.vsu.csf.proskuryakov.data.command;

import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.gui.Dominoes;
import ru.vsu.csf.proskuryakov.gui.controllers.Game;

public class RedoCommand extends Command{

    public RedoCommand(GameState gameState) {
        super(gameState);
    }

    @Override
    public boolean execute() {
        if(Dominoes.app.getUndoCommandHistory().isEmpty()){
            System.out.println("История пуста");
            return false;
        }

        Command command = Dominoes.app.getUndoCommandHistory().pop();

        if(command != null){
            commandDescriptione = "Отмена команды: \"" + command.getCommandDescriptione() + "\"";

            command.undo();
            Game.game.setGameState(command.getGameState());
            return true;
        }
        return false;
    }
}
