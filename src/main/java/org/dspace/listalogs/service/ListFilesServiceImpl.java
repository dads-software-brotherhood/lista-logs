package org.dspace.listalogs.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dspace.listalogs.tools.FileTools;

/**
 *
 * @author erik.valdivieso
 */
public class ListFilesServiceImpl implements ListFilesService {

    @Override
    public File[] getDirectoryList(File dir) {
        try {
            return FileTools.getDirectoryList(dir);
        } catch (IOException ex) {
            Logger.getLogger(ListFilesServiceImpl.class.getName()).log(Level.SEVERE, "", ex);
        }

        return new File[0];
    }

    @Override
    public File[] getFileList(File dir) {
        try {
            return FileTools.getFileList(dir);
        } catch (IOException ex) {
            Logger.getLogger(ListFilesServiceImpl.class.getName()).log(Level.SEVERE, "", ex);
        }

        return new File[0];
    }

    @Override
    public InputStream getContent(File file) {
        try {
            byte[] tmp = FileTools.getContent(file);
            return new ByteArrayInputStream(tmp);
        } catch (IOException ex) {
            Logger.getLogger(ListFilesServiceImpl.class.getName()).log(Level.SEVERE, "", ex);
        }

        return null;
    }
}
