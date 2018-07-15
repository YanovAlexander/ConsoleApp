package ua.com.corevalue.controller.commands;

import ua.com.corevalue.model.DatabaseManager;
import ua.com.corevalue.model.entity.EmployeeData;
import ua.com.corevalue.service.InputString;
import ua.com.corevalue.view.View;

import java.util.List;

public class FindSubordinates implements Command {
    private boolean interrupt;
    private DatabaseManager manager;
    private View view;

    public FindSubordinates(View view, DatabaseManager manager) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(InputString entry) {
        String[] splitFormat = format().split("\\|");
        String[] parameters = entry.getParameters();
        return parameters[0].equals(splitFormat[0]);
    }

    @Override
    public String format() {
        return "findSubordinates";
    }

    @Override
    public void process(InputString entry) {
        interrupt = false;
        List<EmployeeData> employees = getEmployee();

        if (!interrupt) {
            view.write("+------------------------SUBORDINATES-----------------------------");
            employees.forEach(employee -> {
                view.write("+------------------------------------------------------------------");
                view.write("Employee:");
                view.write("First name: " + employee.getFirstName());
                view.write("Last name: " + employee.getLastName());
                view.write("Email: " + employee.getEmail());
                view.write("Manager: " + employee.getManager().getFirstName() + " " + employee.getManager().getLastName());
            });
            view.write("+------------------------------------------------------------------");
        } else {
            view.write("Back to main menu");
        }
    }

    private List<EmployeeData> getEmployee() {
        boolean exit = false;
        List<EmployeeData> employee = null;

        while (!exit) {
            view.write("Enter employee email to find his subordinates employees, " +
                    "or 'cancel' for exit to main menu");
            String input = view.read();

            if (input.equals("cancel")) {
                exit = true;
                interrupt = true;
            } else if (input.trim().isEmpty()) {
                view.write("email can't be empty");
            }

            if (!interrupt) {
                employee = manager.getSubordinatesByManagerEmail(input);

                if (employee != null && !employee.isEmpty()) {
                    exit = true;
                } else {
                    view.write("manager not exist by email or do not have subordinates");
                }
            }
        }
        return employee;
    }
}
