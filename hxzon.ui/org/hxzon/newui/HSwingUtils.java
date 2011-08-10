package org.hxzon.newui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.Popup;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.hxzon.util.TextValidationList;


public class HSwingUtils {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static Rectangle fullScreen;

    public static void centerInScreen(Window window) {

//        window.pack();
        Dimension size = window.getSize();
        int y = (screenSize.height - size.height) / 2;
        int x = (screenSize.width - size.width) / 2;
        window.setLocation(x, y);
    }

    public static void fullScreen(Window window) {
        if (fullScreen == null) {
            JFrame jframe = new JFrame();
            jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jframe.setVisible(true);
            fullScreen = jframe.getBounds();
            jframe.setVisible(false);
        }
        window.setBounds(fullScreen);
    }

    public static void createChildren(DefaultMutableTreeNode parent, Object children) {
        HDynamicUtilTreeNode.createChildren(parent, children);
    }

    public static class HDynamicUtilTreeNode extends DefaultMutableTreeNode {
        protected boolean hasChildren;
        /** Value to create children with. */
        protected Object childValue;
        /** Have the children been loaded yet? */
        protected boolean loadedChildren;

        public HDynamicUtilTreeNode(Object value, Object children) {
            super(value);
            loadedChildren = false;
            childValue = children;
            if (children != null) {
                if (children instanceof Collection) {
                    setAllowsChildren(true);
                } else if (children instanceof Object[]) {
                    setAllowsChildren(true);
                } else if (children instanceof char[]) {
                    setAllowsChildren(true);
                } else if (children instanceof byte[]) {
                    setAllowsChildren(true);
                } else if (children instanceof short[]) {
                    setAllowsChildren(true);
                } else if (children instanceof int[]) {
                    setAllowsChildren(true);
                } else if (children instanceof long[]) {
                    setAllowsChildren(true);
                } else if (children instanceof float[]) {
                    setAllowsChildren(true);
                } else if (children instanceof double[]) {
                    setAllowsChildren(true);
                } else if (children instanceof boolean[]) {
                    setAllowsChildren(true);
                } else if (children instanceof Map) {
                    setAllowsChildren(true);
                } else {
                    setAllowsChildren(false);
                }
            } else {
                setAllowsChildren(false);
            }
        }

        public static void createChildren(DefaultMutableTreeNode parent, Object children) {
            if (children instanceof Collection) {
                for (Object o : (Collection) children)
                    parent.add(new HDynamicUtilTreeNode(o, o));
            } else if (children instanceof Object[]) {
                for (Object o : (Object[]) children)
                    parent.add(new HDynamicUtilTreeNode(o, o));
            } else if (children instanceof char[]) {
                for (Object o : (char[]) children)
                    parent.add(new HDynamicUtilTreeNode(o, o));
            } else if (children instanceof byte[]) {
                for (Object o : (byte[]) children)
                    parent.add(new HDynamicUtilTreeNode(o, o));
            } else if (children instanceof short[]) {
                for (Object o : (short[]) children)
                    parent.add(new HDynamicUtilTreeNode(o, o));
            } else if (children instanceof int[]) {
                for (Object o : (int[]) children)
                    parent.add(new HDynamicUtilTreeNode(o, o));
            } else if (children instanceof long[]) {
                for (Object o : (long[]) children)
                    parent.add(new HDynamicUtilTreeNode(o, o));
            } else if (children instanceof float[]) {
                for (Object o : (float[]) children)
                    parent.add(new HDynamicUtilTreeNode(o, o));
            } else if (children instanceof double[]) {
                for (Object o : (double[]) children)
                    parent.add(new HDynamicUtilTreeNode(o, o));
            } else if (children instanceof boolean[]) {
                for (Object o : (boolean[]) children)
                    parent.add(new HDynamicUtilTreeNode(o, o));
            } else if (children instanceof Map) {
                Set<Map.Entry> set = (Set<Map.Entry>) (((Map) children).entrySet());
                for (Map.Entry o : set) {
                    parent.add(new HDynamicUtilTreeNode(o.getKey(), o.getValue()));
                }
            }
        }

        protected void loadChildren() {
            loadedChildren = true;
            createChildren(this, childValue);
        }

        public boolean isLeaf() {
            return !getAllowsChildren();
        }

        public int getChildCount() {
            if (!loadedChildren)
                loadChildren();
            return super.getChildCount();
        }

        public TreeNode getChildAt(int index) {
            if (!loadedChildren)
                loadChildren();
            return super.getChildAt(index);
        }

        public Enumeration children() {
            if (!loadedChildren)
                loadChildren();
            return super.children();
        }
    }

    public static void bindChecker(final JTextField field, final String fieldName, final TextValidationList checker) {
        if (field == null || checker == null) {
            return;
        }
        KeyListener l = new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = field.getText();
                checker.check(text, fieldName);
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

        };
        field.addKeyListener(l);
    }
    
    public static void setupChecker(final HText c){
        
        KeyListener kl = new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                c.check();
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

        };
        FocusListener fl=new FocusListener(){

            @Override
            public void focusGained(FocusEvent e) {
                c.check();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
            
        };
        c.getComponent().addKeyListener(kl);
        c.getComponent().addFocusListener(fl);
    }

    public static void setTitleBorder(Container view, String title) {
        ((JComponent) view).setBorder(BorderFactory.createTitledBorder(((JComponent) view).getBorder(), title));
    }
    
    public static HPage getPage(HWidget widget) {
        Container c = widget.getComponent().getParent();
        for (; c != null;) {
            if (c instanceof HPage) {
                return (HPage) c;
            }
            c = c.getParent();
        }
        return null;
    }
    
    public static HPage getPage(Component com) {
        Container c = com.getParent();
        for (; c != null;) {
            if (c instanceof HPage) {
                return (HPage) c;
            }
            c = c.getParent();
        }
        return null;
    }
    
    public static HTitlePanel getTitlePanel(Component com){
        HPage page=HSwingUtils.getPage(com);
        if(page!=null){
            return page.getTitlePanel();
        }else{
            return null;
        }
    }

    public static class ButtonRenderer {
        public String getText(Object o) {
            return o.toString();
        }
    }

    public static class NameProvider {
        public String getName(Object o) {
            return o.toString();
        }
    }

    public static class NewComboPopup extends BasicComboPopup {
        JComboBox combo;
        private MenuElement[] elements;

        /** Creates a new instance of MonthComboPopup */
        public NewComboPopup(JComboBox combo) {
            super(combo);
            this.combo = combo;
        }

        public void setVisible(boolean b) {
            if (b == isVisible())
                return;

            if (b == false) {
                Boolean doCanceled = (Boolean) getClientProperty("JPopupMenu.firePopupMenuCanceled");
                if (doCanceled != null && doCanceled == Boolean.TRUE) {
                    putClientProperty("JPopupMenu.firePopupMenuCanceled", Boolean.FALSE);
                    firePopupMenuCanceled();
                }
                getSelectionModel().clearSelection();
            } else {
                if (isPopupMenu()) {
                    if (getSubElements().length > 0) {
                        MenuElement me[] = new MenuElement[2];
                        me[0] = getSubElements()[0];
                        me[1] = (MenuElement) this;
                        MenuSelectionManager.defaultManager().setSelectedPath(me);
                    } else {
                        MenuElement me[] = new MenuElement[1];
                        me[0] = (MenuElement) this;
                        MenuSelectionManager.defaultManager().setSelectedPath(me);
                    }
                }
            }

            if (b) {
                firePopupMenuWillBecomeVisible();
                setThePopup(invokeGetPopup());
                firePropertyChange("visible", Boolean.FALSE, Boolean.TRUE);
            } else if (getThePopup() != null) {
                Popup p = getThePopup();
                firePopupMenuWillBecomeInvisible();
                p.hide();
                setThePopup(null);
                firePropertyChange("visible", Boolean.TRUE, Boolean.FALSE);
            }
        }

        public void hide() {
            MenuSelectionManager manager = MenuSelectionManager.defaultManager();
            MenuElement[] selection = manager.getSelectedPath();
            Vector<MenuElement> tmp = new Vector<MenuElement>();
            for (int i = 0; i < selection.length; i++) {
                if (selection[i] != this)
                    tmp.add(selection[i]);
            }
            selection = new MenuElement[tmp.size()];
            for (int i = 0; i < tmp.size(); i++)
                selection[i] = tmp.get(i);
            manager.setSelectedPath(selection);
            comboBox.repaint();
        }

        private boolean isPopupMenu() {
            return !(getInvoker() instanceof JMenu);
        }

        void setThePopup(Popup p) {
            try {
                popupField.set(this, p);
            } catch (Exception e) {
            }
        }

        Popup getThePopup() {
            try {
                return (Popup) popupField.get(this);
            } catch (Exception e) {
            }
            return null;
        }

        Popup invokeGetPopup() {
            try {
                return (Popup) getPopupMethod.invoke(this);
            } catch (Exception e) {
                return null;
            }
        }

        static Field popupField;
        static Method getPopupMethod;
        static Class popupClass = JPopupMenu.class;
        static {
            try {
                popupField = popupClass.getDeclaredField("popup");
                popupField.setAccessible(true);
                getPopupMethod = popupClass.getDeclaredMethod("getPopup");
                getPopupMethod.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void initialize(final JComboBox combo) {
            Container parent = combo.getParent();
            Vector<MenuElement> tmp = new Vector<MenuElement>();
            while (parent != null) {
                if (parent instanceof MenuElement) {
                    tmp.add((MenuElement) parent);
                }
                parent = parent.getParent();
            }
            elements = new MenuElement[tmp.size()];
            for (int i = 0; i < tmp.size(); i++)
                elements[i] = tmp.get(i);
        }

        public MenuElement[] getSubElements() {
            if (elements == null)
                initialize(combo);
            return elements;
        }

    }
}
