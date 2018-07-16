package ua.com.corevalue.controller.commands;

import ua.com.corevalue.model.DatabaseManager;
import ua.com.corevalue.model.entity.EmployeeData;
import ua.com.corevalue.service.InputString;
import ua.com.corevalue.view.View;

import java.util.List;

public class FindCEO implements Command {
    private boolean interrupt;
    private View view;
    private DatabaseManager manager;

    public FindCEO(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(InputString entry) {
        String[] splitFormat = format().split("\\|");
        String[] parameters = entry.getParameters();
        return parameters[0].equals(splitFormat[0]);
    }

    @Override
    public String format() {
        return "findCeo";
    }

    @Override
    public void process(InputString entry) {
        interrupt = false;
        EmployeeData ceo = findCEO();

        if (!interrupt) {
            view.write("+------------------------CEO-----------------------------");
            view.write("First name: " + ceo.getFirstName());
            view.write("Last name: " + ceo.getLastName());
            view.write("Email: " + ceo.getEmail());
            view.write("+------------------------------------------------------------------");
        } else {
            view.write("Back to main menu");
        }

    }

    private EmployeeData findCEO() {
        boolean exit = false;
        EmployeeData employee = null;

        while (!exit) {
            view.write("Enter employee email to find CEO, " +
                    "or 'cancel' for exit to main menu");
            String input = view.read();

            if (input.equals("cancel")) {
                exit = true;
                interrupt = true;
            } else if (input.trim().isEmpty()) {
                view.write("email can't be empty");
            }

            if (!interrupt) {
                employee = manager.getCEOByEmployeeEmail(input);

                if (employee != null) {
                    exit = true;
                } else {
                    view.write("ceo is not exist by employee email");
                }
            }
        }
        return employee;
    }
}
