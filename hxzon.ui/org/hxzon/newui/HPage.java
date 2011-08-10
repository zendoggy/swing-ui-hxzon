package org.hxzon.newui;

import java.awt.Window;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class HPage extends HPanel {
    
    protected boolean vOrH = true;
    protected Window window;

    // window panel
    protected boolean showTitlePanel = true;
    protected JMenuBar menuBar = new JMenuBar();
    protected HTitlePanel titlePanel = new HTitlePanel();
    protected JPanel iconPanel = new JPanel(new MigLayout());
    protected JPanel buttonPanel = new JPanel(new MigLayout("debug,fill,right"));
    protected JPanel statusPanel = new JPanel(new MigLayout());

    // window property
    protected String title = "hello";
    protected boolean fullScreen = false;
    protected boolean centerInScreen = true;
    
    //
    protected Map<String,HWidget> widgets=new HashMap<String,HWidget>();

    public HPage() {
        init();
    }

    public HPage(JDialog dialog) {
        window = dialog;
    }

    public void init() {
        setLayout(new MigLayout("debug,fill"));
    }

    public void showMessage(String text) {
        titlePanel.showMessage(text);
    }

    public void showErrorMessage(String text) {
        titlePanel.showErrorMessage(text);
    }

    public void addButton(JButton button) {
        buttonPanel.add(button, "cell 0 0,alignx right");
    }

    public void showInDialog() {
        if (window == null) {
            window = new HDialog(title);
        }
        showInWindow(window);
    }

    public void showInDialog(JDialog dialog) {
        window = dialog;
        showInDialog();

    }

    public void showInFrame() {
        if (window == null) {
            window = new JFrame(title);
        }
        showInWindow(window);
    }

    public void showInFrame(JFrame frame) {
        window = frame;
        showInFrame();
    }

    public HTitlePanel getTitlePanel() {
        return titlePanel;
    }

    protected final void showInWindow(Window window) {
        window.setLayout(new MigLayout("debug,fill"));
        if (window instanceof JFrame) {
            ((JFrame) window).setJMenuBar(menuBar);
        }
        if (showTitlePanel) {
            window.add(titlePanel, "north");
        }
        window.add(statusPanel, "south");
        if (vOrH) {
            window.add(buttonPanel, "south");
        } else {
            window.add(buttonPanel, "east");
        }
        window.add(this, "grow");
        if (fullScreen) {
            HSwingUtils.fullScreen(window);
        } else if (centerInScreen) {
            HSwingUtils.centerInScreen(window);
        }
        window.setVisible(true);
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }
    
    public String getStringValue(String id){
        HWidget w=widgets.get(id);
        if(w!=null){
            return w.getStringValue();
        }
        return null;
    }
    
//    public void add(HWidget widget){
//        widgets.put(widget.getId(), widget);
//    }

}
