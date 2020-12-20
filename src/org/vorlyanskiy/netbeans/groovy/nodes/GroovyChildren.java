package org.vorlyanskiy.netbeans.groovy.nodes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

public class GroovyChildren extends Children.SortedArray {

    private final FileObject fo;

    public GroovyChildren(FileObject fo) {
        this.fo = fo;
    }
    
    private void testMethod() {
        refresh();
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
