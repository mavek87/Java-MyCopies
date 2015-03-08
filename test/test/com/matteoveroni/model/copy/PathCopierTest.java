package test.com.matteoveroni.model.copy;

import com.matteoveroni.model.copy.PathCopier;
import com.matteoveroni.model.copy.PathCopier;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matteo Veroni
 */
public class PathCopierTest {
    
    private PathCopier pathCopier;
    
    public PathCopierTest() {
        System.out.println("PathCopier Test");
        
    }
    
    @Test
    public void sourcePathNotExistent(){
        String source = "";
        String target = "targetFile.txt";
        
        File fileSource = new File(source);
        File fileTarget = new File(target);
        
        pathCopier = new PathCopier(fileSource, fileTarget);
        //pathCopier.copy();
        assertTrue(true);
        
    }
    
    @Test
    public void targetPathNotExistent(){
    }

    @Test
    public void fileOnFileCopy(){
    }
    
    @Test
    public void fileOnFolderCopy(){
    }
    
    @Test
    public void folderOnFolderCopy(){
    }
    
    @Test
    public void folderOnFileCopy(){
    }
    
    
}
