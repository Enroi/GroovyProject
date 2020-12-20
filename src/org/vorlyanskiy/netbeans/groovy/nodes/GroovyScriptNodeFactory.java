package org.vorlyanskiy.netbeans.groovy.nodes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.event.ChangeListener;
import org.vorlyanskiy.netbeans.groovy.GroovyProject;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

@NodeFactory.Registration(projectType = "org-groovy-project", position = 10)
public class GroovyScriptNodeFactory implements NodeFactory {

    @Override
    public NodeList<?> createNodes(Project project) {
        GroovyProject p = project.getLookup().lookup(GroovyProject.class);
        if (p == null) {
            throw new RuntimeException("Can not find project");
        }
        return new ProjectNodesList(p);
    }

    private class ProjectNodesList implements NodeList<FileObject> {

        private final GroovyProject project;

        public ProjectNodesList(GroovyProject project) {
            this.project = project;
        }

        @Override
        public List<FileObject> keys() {
            FileObject projectDirectory = project.getProjectDirectory().getFileObject(".");
            List<FileObject> fileObjects = Arrays.asList(projectDirectory.getChildren())
                    .stream()
                    .filter(fo -> {
                        return fo.isFolder()
                                || (fo.getExt() != null && fo.getExt().equalsIgnoreCase("groovy"));
                    })
                    .sorted((fo1, fo2) -> {
                        return fo1.getName().compareToIgnoreCase(fo2.getName());
                    }).collect(Collectors.toList());
            return fileObjects;
        }

        @Override
        public Node node(FileObject fo) {
            Node nodeDelegate = null;
            try {
                nodeDelegate = DataObject.find(fo).getNodeDelegate();
            } catch (DataObjectNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            }
            if (!nodeDelegate.isLeaf()) {
                GroovyChildren groovyChildren = new GroovyChildren(fo);
                groovyChildren.setComparator(new ChildrenComprator());
                nodeDelegate = new FilterNode(nodeDelegate, groovyChildren);
            }
            return nodeDelegate;
        }

        @Override
        public void addNotify() {
        }

        @Override
        public void removeNotify() {
        }

        @Override
        public void addChangeListener(ChangeListener cl) {
        }

        @Override
        public void removeChangeListener(ChangeListener cl) {
        }

    }
    
    private class ChildrenComprator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1 == null && o2 == null) {
                return 0;
            } if (o1 != null && o2 == null) {
                return 1;
            } if (o1 == null && o2 != null) {
                return -1;
            }
            return o1.getName().compareTo(o2.getName());
        }
        
    }
    
    private class GroovyChildren extends Children.SortedArray {

        private final FileObject fo;

        private GroovyChildren(FileObject fo) {
            this.fo = fo;
        }

        @Override
        protected Collection<Node> initCollection() {
            List<Node> result = Arrays.asList(fo.getChildren())
                    .stream()
                    .filter(fo -> {
                        return fo.isFolder()
                                || (fo.getExt() != null && fo.getExt().equalsIgnoreCase("groovy"));
                    })
                    .sorted((fo1, fo2) -> {
                        return fo1.getName().compareToIgnoreCase(fo2.getName());
                    }).map(fob -> {
                Node nodeDelegate = null;
                try {
                    nodeDelegate = DataObject.find(fob).getNodeDelegate();
                } catch (DataObjectNotFoundException ex) {
                    Exceptions.printStackTrace(ex);
                }
                if (!nodeDelegate.isLeaf()) {
                    nodeDelegate = new FilterNode(nodeDelegate, new GroovyChildren(fob));
                }
                return nodeDelegate;
            }).collect(Collectors.toList());
            return result;
        }

    }

}
