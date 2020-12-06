package org.vorlyanskiy.netbeans.groovy.utils;

import java.util.Optional;
import java.util.prefs.Preferences;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import org.vorlyanskiy.netbeans.groovy.datamodel.OptionsDataModel;

/**
 *
 */
public class VariousProjectUtils {
    
    public static final String GROOVY_PATH = "GroovyPath";

    public String getPathToGroovy() {
        String result = null;
        Lookup.Result<Project> lookupResults2 = Utilities.actionsGlobalContext().lookupResult(Project.class);
        Optional<? extends Project> firstProject = lookupResults2.allInstances()
                .stream().findFirst();
        if (firstProject.isPresent()) {
            Project project = firstProject.get();
            result = getPath(project);
        }
        return result;
    }

    public static String getPath(Project project) {
        String path = null;
        Preferences preferences = ProjectUtils.getPreferences(project, OptionsDataModel.class, true);
        String groovyPath = preferences.get(GROOVY_PATH, "");
        if (!groovyPath.isEmpty()) {
            path = groovyPath;
        }
        return path;
    }
}
