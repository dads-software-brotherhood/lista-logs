package org.dspace.listalogs.view;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.dspace.listalogs.service.ListFilesService;
import org.dspace.listalogs.service.ListFilesServiceImpl;

/**
 *
 * @author erik.valdivieso
 */
@ManagedBean
@ViewScoped
public class ListFilesMB implements Serializable {
    
    private File dir;
    
    private ListFilesService listFilesService = new ListFilesServiceImpl();
    
    @PostConstruct
    protected void init() {
        String path = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(IndexMB.FLASH_KEY);
        
        if (path != null) {
            dir = new File(path);
        }
        
    }
    
    public File[] getFiles() {
        try {
            return listFilesService.getFileList(dir);
        } catch (IOException ex) {
            Logger.getLogger(ListFilesMB.class.getName()).log(Level.SEVERE, "Error al recuperar archivos", ex);
            return new File[0];
        }
    }
    
}
