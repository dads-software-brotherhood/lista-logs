package org.dspace.listalogs.tools;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author erik
 */
class OnlyDirsFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory();
    }
}
