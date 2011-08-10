package org.hxzon.newui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTree;

public class HTree extends JPanel {
    private TreeModel treeModel;
    private JXTree tree;
    private JScrollPane scrollPane;

    public HTree() {
        this(null);
    }

    public HTree(Object model) {
        super(new MigLayout("fill"));
        add(getScrollPane(), "grow");
        preSettingTree();
        setData(model);
    }

    public void setData(Object model) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        HSwingUtils.createChildren(root, model);
        treeModel = new DefaultTreeModel(root, false);
        tree.setModel(treeModel);
    }

    protected void preSettingTree() {
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
    }

    public TreeModel getTreeModel() {
        return treeModel;
    }

    public JXTree getTree() {
        if (tree == null) {
            tree = new JXTree();
        }
        return tree;
    }

    public JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane(getTree());
        }
        return scrollPane;
    }

}
