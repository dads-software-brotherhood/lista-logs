package org.dspace.listalogs.service;

import java.io.File;

/**
 *
 * @author erik.valdivieso
 */
public interface ListFilesService {
    
    File[] getDirectoryList(File dir);
    
    File[] getFileList(File dir);
    
    byte[] getContent(File file);
}
