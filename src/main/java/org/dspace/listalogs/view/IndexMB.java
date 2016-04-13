package org.dspace.listalogs.view;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.dspace.listalogs.tools.Settings;

/**
 *
 * @author erik.valdivieso
 */
@ManagedBean
@RequestScoped
public class IndexMB implements Serializable {
    
    public static final String FLASH_KEY = "path";
    
    private String path;
    private List<String> paths;

    @PostConstruct
    protected void init() {
        String[] tmp = Settings.getDirectories();
        
        if (tmp == null || tmp.length == 0) {
            paths = new ArrayList<>(0);
        } else {
            paths = new ArrayList<>(tmp.length);
            File file;
            
            for (String tmpPath : tmp) {
                file = new File(tmpPath);
                
                if (file.exists()) {
                    paths.add(tmpPath);
                }
            }
        }
    }
    
    public List<String> getPaths() {
        return paths;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public void preProcess(ActionEvent event) {
        if (isValidPath()) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(FLASH_KEY, path);
        }
    }
    
    private boolean isValidPath() {
        for (String tmp : paths) {
            if (tmp.equals(path)) {
                return true;
            }
        }
        
        return false;
    }
}
