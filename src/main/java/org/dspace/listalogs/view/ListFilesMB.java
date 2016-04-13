package org.dspace.listalogs.view;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.dspace.listalogs.service.ListFilesService;
import org.dspace.listalogs.service.ListFilesServiceImpl;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author erik.valdivieso
 */
@ManagedBean
@ViewScoped
public class ListFilesMB implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private File dir;
    private File[] files;
    
    private StreamedContent content;
    
    private final ListFilesService listFilesService = new ListFilesServiceImpl();
    
    @PostConstruct
    protected void init() {
        String path = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(IndexMB.FLASH_KEY);
        
        if (path != null) {
            dir = new File(path);
            files = listFilesService.getFileList(dir);
        } else {
            files = new File[0];
        }
        
    }
    
    public File[] getFiles() {
        return files;
    }

    public StreamedContent getContent() {
        return content;
    }
    
    public void preprocess(int idx) {
        if (idx >= 0 && idx < files.length) {
            File tmp = files[idx];
            
            try {
                content = new DefaultStreamedContent(listFilesService.getContent(tmp), Files.probeContentType(tmp.toPath()), tmp.getName());
            } catch (IOException ex) {
                Logger.getLogger(ListFilesMB.class.getName()).log(Level.SEVERE, "Error al generar el archivo de descarga", ex);
                content = null;
            }
        } else {
            content = null;
        }
    }
}
