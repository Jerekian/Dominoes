package ru.vsu.csf.proskuryakov.dominoes.data.command;

import ru.vsu.csf.proskuryakov.dominoes.core.GameState;
import ru.vsu.csf.proskuryakov.dominoes.gui.Dominoes;
import ru.vsu.csf.proskuryakov.dominoes.gui.controllers.Game;

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
            commandDescription = command.getCommandDescription().substring("Отмена команды:".length());
            //commandDescription = "Отмена команды: \"" + command.getCommandDescription() + "\"";

            command.undo();
            backup();
            Game.game.setGameState(command.getGameState());
            return true;
        }
        return false;
    }
}
