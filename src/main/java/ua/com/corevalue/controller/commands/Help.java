package ua.com.corevalue.controller.commands;

import ua.com.corevalue.service.InputString;
import ua.com.corevalue.view.View;

public class Help implements Command {

    private View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public String format() {
        return "help";
    }

    @Override
    public boolean canProcess(InputString entry) {
        String[] splitFormat = format().split("\\|");
        String[] parameters = entry.getParameters();
        return parameters[0].equals(splitFormat[0]);
    }

    @Override
    public void process(InputString entry) {
        entry.validateParameters(format());
        view.write("\t+----------------------------COMMANDS------------------------------");

        view.write("\t| exit");
        view.write("\t|\t-> To terminate the application");
        view.write("\t+------------------------------------------------------------------");

        view.write("\t| help");
        view.write("\t|\t-> To display this list on the screen");
        view.write("\t+------------------------------------------------------------------");

        view.write("\t| findSubordinates");
        view.write("\t|\t-> to find subordinates by employee email");
        view.write("\t+------------------------------------------------------------------");

        view.write("\t| findCeo");
        view.write("\t|\t-> To find CEO by employee email");
        view.write("\t+------------------------------------------------------------------");

        view.write("\t| createEmployee");
        view.write("\t|\t-> To save new employee");
        view.write("\t+------------------------------------------------------------------");
    }
}
