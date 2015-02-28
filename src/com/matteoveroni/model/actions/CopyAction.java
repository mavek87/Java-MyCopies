package com.matteoveroni.model.actions;

import com.matteoveroni.model.copy.Copier;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matteo Veroni
 */
public class CopyAction implements Action {

    Copier copier;

    public CopyAction(Copier copier) {
        this.copier = copier;
    }

    @Override
    public void execute(){
        try {
            copier.copy();
        } catch (IOException ex) {
            Logger.getLogger(CopyAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
