package ua.com.corevalue.controller.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.corevalue.model.DatabaseManager;
import ua.com.corevalue.service.InputString;
import ua.com.corevalue.view.View;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class SaveEmployeeTest {
    private View view;
    private DatabaseManager manager;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new SaveEmployee(view, manager);
    }

    @Test
    public void saveEmployeeCanProcessTest() {
        //when
        InputString userInput = new InputString("createEmployee");
        boolean result = command.canProcess(userInput);
        //then
        assertTrue(result);
    }

    @Test
    public void saveEmployeeCantProcessWithoutParametersTest() {
        //when
        InputString userInput = new InputString("");
        boolean result = command.canProcess(userInput);
        //then
        assertFalse(result);
    }
}
