package com.matteoveroni.model.commands;

import com.matteoveroni.model.Model;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Matteo Veroni
 */
public class ExitCommand implements Command {

    private final Model model;

    private static final Logger LOG = LoggerFactory.getLogger(ExitCommand.class);

    public ExitCommand(Model model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.dispose();
        LOG.debug("Model disposed");
        LOG.info("Closing the application");
        Platform.exit();
    }

}
