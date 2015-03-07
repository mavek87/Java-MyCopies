package com.matteoveroni.model.commands;

import com.matteoveroni.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Matteo Veroni
 */
public class DisposeModelCommand implements Command {

    private final Model model;

    private static final Logger LOG = LoggerFactory.getLogger(DisposeModelCommand.class);

    public DisposeModelCommand(Model model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.dispose();
        LOG.debug("Model disposed");
    }

}
