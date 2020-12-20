package org.vorlyanskiy.netbeans.groovy;

import java.awt.Image;
import javax.swing.Action;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 */
final class ProjectNode extends FilterNode {

    final GroovyProject project;

    public ProjectNode(Node node, GroovyProject project) throws DataObjectNotFoundException {
        super(node, NodeFactorySupport.createCompositeChildren(project, "Projects/org-groovy-project/Nodes"), new ProxyLookup(new Lookup[]{Lookups.singleton(project), node.getLookup()}));
        this.project = project;
    }

    @Override
    public Action[] getActions(boolean arg0) {
        return new Action[]{CommonProjectActions.newFileAction(), CommonProjectActions.customizeProjectAction(), CommonProjectActions.closeProjectAction()};
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage(GroovyProjectLogicalView.GROOVY_ICON);
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public String getDisplayName() {
        return project.getProjectDirectory().getName();
    }

}
