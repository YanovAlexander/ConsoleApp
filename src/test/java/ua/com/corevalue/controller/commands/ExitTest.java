package ua.com.corevalue.controller.commands;

import org.junit.Test;
import ua.com.corevalue.service.ExitException;
import ua.com.corevalue.service.InputString;
import ua.com.corevalue.view.ViewTest;

import static org.junit.Assert.*;

public class ExitTest {
    private ViewTest view = new ViewTest();

    @Test
    public void exitCanProcessTest() {
        //given
        Command command = new Exit(view);
        //when
        InputString userInput = new InputString("exit");
        boolean exit = command.canProcess(userInput);
        //then
        assertTrue(exit);
    }

    @Test
    public void exitCantProcessQWETest() {
        //given
        Command command = new Exit(view);
        //when
        InputString userInput = new InputString("qwe");
        boolean exit = command.canProcess(userInput);
        //then
        assertFalse(exit);
    }

    @Test
    public void exitProcessTest() {
        //given
        Command command = new Exit(view);
        //when
        try {
            InputString userInput = new InputString("exit");
            command.process(userInput);
            fail("Expected ExitException");
        } catch (ExitException e) {
            //do nothing
        }
        //then
        assertEquals("Good Bye!\n", view.getContent());
    }
}
