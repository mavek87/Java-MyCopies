package com.matteoveroni.model.commands;

import com.matteoveroni.model.copy.Copiable;
import java.io.IOException;

/**
 * @author Matteo Veroni
 */

public class CopyAction implements Action {

    Copiable copier;

    public CopyAction(Copiable copier) {
        this.copier = copier;
    }

    @Override
    public void execute() throws IOException{
        copier.copy();
    }

}
