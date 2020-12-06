package org.vorlyanskiy.netbeans.groovy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.netbeans.api.io.IOProvider;
import org.netbeans.api.io.InputOutput;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.vorlyanskiy.netbeans.groovy.utils.VariousProjectUtils;

/**
 *
 */
public class RunScript implements Runnable {

    private final FileObject fileObject;
    private final String pathToGroovy;

    public RunScript(FileObject fileObject, String pathToGroovy) {
        this.fileObject = fileObject;
        this.pathToGroovy = pathToGroovy;
    }

    @Override
    public void run() {
        InputOutput io = IOProvider.getDefault().getIO(fileObject.getName(), true);
        try {
            io.show();
            String pathToFile = fileObject.getPath();
            ProcessBuilder builder = new ProcessBuilder(pathToGroovy, pathToFile);
            Process process = builder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> outputs = bufferedReader.lines().collect(Collectors.toList());
            outputs.forEach(io.getOut()::println);
        } catch (IOException ex) {
            Arrays.asList(ex.getStackTrace()).stream().forEach(ste -> {
                io.getOut().println(ste);
            });
            Exceptions.printStackTrace(ex);
        }
        io.getOut().close();
    }

}
