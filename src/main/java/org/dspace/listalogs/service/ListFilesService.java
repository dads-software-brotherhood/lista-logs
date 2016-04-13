package org.dspace.listalogs.service;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author erik.valdivieso
 */
public interface ListFilesService {
    
    File[] getDirectoryList(File dir) throws IOException;
    
    File[] getFileList(File dir) throws IOException;
    
    byte[] getContent(File file) throws IOException;
    
}
