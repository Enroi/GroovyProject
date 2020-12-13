package org.vorlyanskiy.netbeans.groovy.utils;

import java.util.prefs.Preferences;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.vorlyanskiy.netbeans.groovy.datamodel.OptionsDataModel;

/**
 *
 */
public class VariousProjectUtils {
    
    public static final String GROOVY_PATH = "GroovyPath";

    public static String getPath(Project project) {
        String path = null;
        if (project != null) {
            Preferences preferences = ProjectUtils.getPreferences(project, OptionsDataModel.class, true);
            String groovyPath = preferences.get(GROOVY_PATH, "");
            if (!groovyPath.isEmpty()) {
                path = groovyPath;
            }
        }
        return path;
    }
}
