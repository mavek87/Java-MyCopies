package com.matteoveroni.model.actions;

import com.matteoveroni.model.copy.Copier;
import java.io.IOException;

/**
 * @author Matteo Veroni
 */
public class CopyAction implements Action {

    private final Copier copier;

    public CopyAction(Copier copier) {
        this.copier = copier;
    }

    @Override
    public void execute() throws IOException {
        copier.copy();
    }

}
