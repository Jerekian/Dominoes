package ru.vsu.csf.proskuryakov.data.command;

import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.gui.Dominoes;
import ru.vsu.csf.proskuryakov.gui.controllers.Game;

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
            commandDescriptione = "Отмена команды: \"" + command.getCommandDescriptione() + "\"";
            backup();

            command.undo();
            Game.game.setGameState(command.getGameState());
            return true;
        }
        return false;
    }
}
