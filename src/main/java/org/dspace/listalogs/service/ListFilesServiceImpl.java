package org.dspace.listalogs.service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author erik.valdivieso
 */
public class ListFilesServiceImpl implements ListFilesService {
    
    private final FileFilter onlyDirsFilter = new OnlyDirsFilter();
    private final FileFilter onlyNotDirsFilter = new OnlyNotDirsFilter();

    @Override
    public File[] getDirectoryList(File dir) throws IOException {
        return getFiles(dir, true);
    }

    @Override
    public File[] getFileList(File dir) throws IOException {
        return getFiles(dir, false);
    }

    @Override
    public byte[] getContent(File file) throws IOException {
        if (file.exists() && file.isFile()) {
            return Files.readAllBytes(file.toPath());
        } else {
            return null;
        }
    }

    private File[] getFiles(File dir, boolean onlyDirs) throws IOException {
        if (dir.isDirectory()) {
            File tmp;

            if (dir.isAbsolute()) {
                tmp = dir;
            } else {
                tmp = dir.getAbsoluteFile();
            }
            
            if (onlyDirs) {
                return tmp.listFiles(onlyDirsFilter);
            } else {
                return tmp.listFiles(onlyNotDirsFilter);
            }
        } else {
            return null;
        }
    }
    
    class OnlyDirsFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            return pathname.isDirectory();
        }
        
    }
    
    class OnlyNotDirsFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            return !pathname.isDirectory();
        }
        
    }
    
}
