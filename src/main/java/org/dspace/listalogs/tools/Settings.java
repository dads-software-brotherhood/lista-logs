package org.dspace.listalogs.tools;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erik.valdivieso
 */
public final class Settings {

    private String[] directories;
    
    private static final Settings INSTANCE = new Settings();
    
    private Settings() {
        ResourceBundle bundle = ResourceBundle.getBundle("/settings");
        
        try {
            String tmp = bundle.getString("PATHS");
            
            if (tmp != null && !tmp.isEmpty()) {
                directories = tmp.split(",");
            }
        } catch (Exception ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, "Error al leer las configuraciones", ex);
        }
    }
    
    public static String[] getDirectories() {
        return INSTANCE.directories;
    }
}
