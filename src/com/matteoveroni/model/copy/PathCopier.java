package com.matteoveroni.model.copy;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Matteo Veroni
 */
public class PathCopier implements Copier {

    private File source;
    private File target;
    
    private static final Logger LOG = LoggerFactory.getLogger(PathCopier.class);

    public PathCopier(File source, File target) {
        this.source = source;
        this.target = target;
    }

    public File getSource() {
        return source;
    }

    public void setSource(File source) {
        this.source = source;
    }

    public File getTarget() {
        return target;
    }

    public void setTarget(File target) {
        this.target = target;
    }

    @Override
    public void copy() {
        if (validSource() && validTarget()) {
            if (source.isFile() && target.isFile()) {
                try {
                    FileUtils.copyFile(source, target);
                } catch (IOException ex) {
                    printError("Errore I/O - " + ex);
                }
            } else if (source.isFile() && target.isDirectory()) {
                try {
                    FileUtils.copyFileToDirectory(source, target);
                } catch (IOException ex) {
                    printError("Errore I/O - " + ex);
                }
            } else if (source.isDirectory() && target.isDirectory()) {
                try {
                    FileUtils.copyDirectory(source, target);
                } catch (IOException ex) {
                    printError("Errore I/O - " + ex);
                }
            } else if (source.isDirectory() && target.isFile()) {
                printError("Can\'t write a directory inside a file!");
            }
        } else {
            printError("Source does\'t exists or not readable or Target not writable");
        }
    }

    private boolean validSource() {
        return (source.exists() && source.canRead());
    }

    private boolean validTarget() {
        return target.canWrite();
    }

    private void printError(String error) {
        LOG.error(error);
    }
}
