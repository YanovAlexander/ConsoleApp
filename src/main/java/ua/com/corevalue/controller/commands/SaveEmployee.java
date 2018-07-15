package ua.com.corevalue.controller.commands;

import ua.com.corevalue.model.DatabaseManager;
import ua.com.corevalue.model.entity.EmployeeData;
import ua.com.corevalue.service.InputString;
import ua.com.corevalue.view.View;

public class SaveEmployee implements Command{
    private boolean interruptCreate;
    private View view;
    private DatabaseManager manager;

    public SaveEmployee(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(InputString entry) {
        String[] splitFormat = format().split("\\|");
        String[] parameters = entry.getParameters();
        boolean equals = parameters[0].equals(splitFormat[0]);
        return equals;
    }

    @Override
    public String format() {
        return "saveEmployee";
    }

    @Override
    public void process(InputString entry) {
        interruptCreate = false;
        EmployeeData newEmployee = createEmployee();

        if (!interruptCreate) {
            manager.saveEmployee(newEmployee);
            view.write("Employee created successful!");
        } else {
            view.write("Exit to main menu");
        }
    }

    private EmployeeData createEmployee() {
        EmployeeData newEmployee = new EmployeeData();

        if (!interruptCreate) {
            newEmployee.setFirstName(createFirstName());
        }

        if (!interruptCreate) {
            newEmployee.setLastName(createLastName());
        }

        if (!interruptCreate) {
            newEmployee.setEmail(createEmail());
        }

        if (!interruptCreate) {
            newEmployee.setManager(getManager());
        }

        return newEmployee;
    }

    private EmployeeData getManager() {
        boolean exit = false;
        String input = null;
        EmployeeData manager = null;

        while (!exit) {
            view.write("Enter manager email to find manager or 'cancel' for exit to main menu");
            input = view.read();
            if (input.equals("cancel")) {
                exit = true;
                interruptCreate = true;
            } else if (input.trim().isEmpty()) {
                view.write("name can't be empty");
            }
            manager = findManagerByEmail(input);

            if (manager != null) {
                exit = true;
            } else if (!interruptCreate){
                view.write("manager not exist by email");
            }
        }
        return manager;
    }

    private EmployeeData findManagerByEmail(String email) {
        return manager.findEmployeeByEmail(email);
    }

    private String createEmail() {
        boolean exit = false;
        String input = null;

        while (!exit) {
            view.write("Enter  employee email or 'cancel' for exit to main menu");

            input = view.read();
            if (input.equals("cancel")) {
                exit = true;
                interruptCreate = true;
            } else if (input.trim().isEmpty()) {
                view.write("name can't be empty");
            } else {
                exit = true;
            }
        }
        return input;
    }

    private String createLastName() {
        boolean exit = false;
        String input = null;

        while (!exit) {
            view.write("Enter employee last name or 'cancel' for exit to main menu");

            input = view.read();
            if (input.equals("cancel")) {
                exit = true;
                interruptCreate = true;
            } else if (input.trim().isEmpty()) {
                view.write("name can't be empty");
            } else {
                exit = true;
            }
        }
        return input;
    }

    private String createFirstName() {
        boolean exit = false;
        String input = null;

        while (!exit) {
            view.write("Enter employee first name or 'cancel' for exit to main menu");

            input = view.read();
            if (input.equals("cancel")) {
                exit = true;
                interruptCreate = true;
            } else if (input.trim().isEmpty()) {
                view.write("name can't be empty");
            } else {
                exit = true;
            }
        }
        return input;
    }
}
