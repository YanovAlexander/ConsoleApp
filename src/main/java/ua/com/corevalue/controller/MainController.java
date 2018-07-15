package ua.com.corevalue.controller;

import ua.com.corevalue.controller.commands.*;
import ua.com.corevalue.model.DatabaseManager;
import ua.com.corevalue.service.ExitException;
import ua.com.corevalue.service.InputString;
import ua.com.corevalue.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainController {

    private List<Command> commands;
    private View view;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.commands = new ArrayList<>(Arrays.asList(
                new Help(view),
                new Exit(view),
                new SaveEmployee(view, manager),
                new FindSubordinates(view, manager),
                new FindCEO(view, manager)
        ));
    }

    public void run() {
        view.write("Welcome!");
        view.write("Use 'help' to list all commands");

        try {
            doCommand();
        } catch (ExitException e) {
            /*NOP*/
        }
    }

    private void doCommand() {
        while (true) {
            InputString entry = new InputString(view.read());

            for (Command command : commands) {
                try {
                    if (command.canProcess(entry)) {
                        command.process(entry);
                        break;
                    }
                } catch (Exception e) {
                    if (e instanceof ExitException) {
                        throw e;
                    }
                    printError(e);
                    break;
                }
            }
            view.write("Type command (or use 'help' to list all commands):");
        }
    }


    private void printError(Exception e) {
        String message = e.getMessage();
        Throwable cause = e.getCause();
        if (cause != null) {
            message += " " + cause.getMessage();
        }
        view.write("Error! Because of: " + message);
        view.write("Please try again.");
    }
}
