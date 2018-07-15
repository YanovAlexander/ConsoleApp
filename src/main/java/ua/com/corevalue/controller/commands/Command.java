package ua.com.corevalue.controller.commands;

import ua.com.corevalue.service.InputString;

public interface Command {
    boolean canProcess(InputString entry);

    String format();

    void process(InputString entry);
}
