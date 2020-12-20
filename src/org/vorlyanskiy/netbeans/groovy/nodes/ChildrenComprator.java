package org.vorlyanskiy.netbeans.groovy.nodes;

import java.util.Comparator;
import org.openide.nodes.Node;

public class ChildrenComprator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 != null && o2 == null) {
            return 1;
        }
        if (o1 == null && o2 != null) {
            return -1;
        }
        return o1.getName().compareTo(o2.getName());
    }

}
