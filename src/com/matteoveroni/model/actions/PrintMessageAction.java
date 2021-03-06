package com.matteoveroni.model.actions;

/**
 * @author Matteo Veroni
 */
public class PrintMessageAction implements Action {

    private String message = "";

    public PrintMessageAction(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println(message);
    }

}
