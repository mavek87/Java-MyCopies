package com.matteoveroni.model.commands;

import java.io.IOException;

/**
 * @author Matteo Veroni
 */
public class ErrorAfterSomeWorkAction implements Action {

    private static int counter = 0;

    @Override
    public void execute() throws Exception {
        if (counter < 20) {
            System.out.println("Error Action" + counter + " print");
            counter++;
        } else {
            throw new IOException("Fake I/O exception -> not able to write on monitor stream");
        }
    }

}
