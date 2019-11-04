package ru.vsu.csf.proskuryakov.data.command;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private List<Command> history = new ArrayList<>();

    public void push(Command c) {
        history.add(c);
    }

    public Command pop() {
        Command c = history.get(history.size()-1);
        history.remove(history.size()-1);
        return c;
    }

    public boolean isEmpty() { return history.isEmpty(); }

    public void clear(){
        history.clear();
    }

}
