package org.dspace.listalogs.view;

import java.io.File;
import java.io.Serializable;
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

    private static final long serialVersionUID = 1L;
    
    private File dir;
    
    private final ListFilesService listFilesService = new ListFilesServiceImpl();
    
    @PostConstruct
    protected void init() {
        String path = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(IndexMB.FLASH_KEY);
        
        if (path != null) {
            dir = new File(path);
        }
        
    }
    
    public File[] getFiles() {
        return listFilesService.getFileList(dir);
    }
}
