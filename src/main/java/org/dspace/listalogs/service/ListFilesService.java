package org.dspace.listalogs.service;

import java.io.File;
import java.io.InputStream;

/**
 *
 * @author erik.valdivieso
 */
public interface ListFilesService {
    
    File[] getDirectoryList(File dir);
    
    File[] getFileList(File dir);
    
    InputStream getContent(File file);
}
