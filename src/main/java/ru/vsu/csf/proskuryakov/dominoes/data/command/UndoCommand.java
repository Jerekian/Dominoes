package ru.vsu.csf.proskuryakov.dominoes.data.command;

import ru.vsu.csf.proskuryakov.dominoes.core.GameState;
import ru.vsu.csf.proskuryakov.dominoes.gui.Dominoes;
import ru.vsu.csf.proskuryakov.dominoes.gui.controllers.Game;

public class UndoCommand extends Command {

    public UndoCommand(GameState gameState) {
        super(gameState);
    }

    @Override
    public boolean execute() {

        if(Dominoes.app.getCommandHistory().isEmpty()){
            System.out.println("История пуста");
            return false;
        }

        Command command = Dominoes.app.getCommandHistory().pop();

        if(command != null){
            commandDescription = "Отмена команды: \"" + command.getCommandDescription() + "\"";
            backup();

            command.undo();
            Game.game.setGameState(command.getGameState());
            return true;
        }
        return false;
    }
}
