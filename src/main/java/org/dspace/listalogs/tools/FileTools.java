package org.dspace.listalogs.tools;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author erik
 */
public final class FileTools {
    
    FileTools() {
    }

    private static final FileFilter DIR_FILTER = new OnlyDirsFilter();
    private static final FileFilter FILES_FILTER = new OnlyNotDirsFilter();

    public static File[] getDirectoryList(File dir) throws IOException {
        return getFiles(dir, true);
    }

    public static File[] getFileList(File dir) throws IOException {
        return getFiles(dir, false);
    }

    public static byte[] getContent(File file) throws IOException {
        if (file.exists() && file.isFile()) {
            return Files.readAllBytes(file.toPath());
        } else {
            return null;
        }
    }

    private static File[] getFiles(File dir, boolean onlyDirs) throws IOException {
        if (dir.isDirectory()) {
            File tmp;

            if (dir.isAbsolute()) {
                tmp = dir;
            } else {
                tmp = dir.getAbsoluteFile();
            }
            
            if (onlyDirs) {
                return tmp.listFiles(DIR_FILTER);
            } else {
                return tmp.listFiles(FILES_FILTER);
            }
        } else {
            return null;
        }
    }
}
