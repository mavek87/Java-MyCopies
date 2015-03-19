package com.matteoveroni.model.actions;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matteo Veroni
 */
public class FakeErrorAfterSomeWorkAction implements Action {
    
    private static int counter = 0;
    private static final Logger LOG = LoggerFactory.getLogger(FakeErrorAfterSomeWorkAction.class);

    @Override
    public void execute() throws Exception {
        if (counter < 20) {
            LOG.info("Error Action" + counter + " print");
            counter++;
        } else {
            throw new IOException("Fake I/O exception -> not able to write on monitor stream");
        }
    }

}
