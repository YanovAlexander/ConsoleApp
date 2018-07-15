package ua.com.corevalue.controller.commands;

import ua.com.corevalue.service.ExitException;
import ua.com.corevalue.service.InputString;
import ua.com.corevalue.view.View;

public class Exit implements Command {
    private View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public String format() {
        return "exit";
    }

    @Override
    public boolean canProcess(InputString entry) {
        String[] splitFormat = format().split("\\|");
        String[] parameters = entry.getParameters();
        return parameters[0].equals(splitFormat[0]);
    }

    @Override
    public void process(InputString entry) {
        view.write("Good Bye!");
        throw new ExitException();
    }
}
