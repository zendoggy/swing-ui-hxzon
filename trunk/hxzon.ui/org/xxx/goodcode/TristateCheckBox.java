package org.xxx.goodcode;

import java.awt.Graphics;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
    
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
    
public class TristateCheckBox extends JCheckBox {
    
    public final static int SELECTED = 1;
    public final static int DESELECTED = 2;
    public final static int NOTSPECIFIED = 3;
    
    class ProxyHandler implements InvocationHandler {
        public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
            String methodName = method.getName();
            if (isEnabled() && !isPainting && methodName.equals("setPressed")) {
                boolean isPressed = ((Boolean) args[0]).booleanValue();
                if (isPressed == false && buttonModel.isArmed()) {
                    nextState();
                }
            }
            if (isPainting && state == NOTSPECIFIED) {
                if (methodName.equals("isPressed")
                        || methodName.equals("isArmed")) {
                    return Boolean.TRUE;
                }
            }
            if (methodName.equals("isSelected")) {
                if (state == SELECTED || state == NOTSPECIFIED) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            }
            if (methodName.equals("setSelected")) {
                if (Boolean.TRUE.equals(args[0])) {
                    setState(SELECTED);
                } else {
                    setState(DESELECTED);
                }
            }
            return method.invoke(buttonModel, args);
        }
    }
    
    private boolean isPainting = false;
    private ButtonModel buttonModel = null;
    private int state = DESELECTED;
    
    public TristateCheckBox() {
        this(null);
    }
    
    public TristateCheckBox(String text) {
        this(text, false);
    }
    
    public TristateCheckBox(String text, boolean isSelected) {
        this(text, isSelected ? SELECTED : DESELECTED);
    }
    
    public TristateCheckBox(String text, int state) {
        super(text, null, false);
        this.buttonModel = this.getModel();
        ButtonModel proxyModel = (ButtonModel) Proxy.newProxyInstance(
                TristateCheckBox.class.getClassLoader(),
                new Class[] { ButtonModel.class }, new ProxyHandler());
        this.setModel(proxyModel);
        this.setState(state);
    }
    
    public int getState() {
        return state;
    }
    
    public void nextState() {
        if (state == SELECTED) {
            state = NOTSPECIFIED;
        } else if (state == DESELECTED) {
            state = SELECTED;
        } else {
            state = DESELECTED;
        }
        this.fireStateChanged();
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public void paintComponent(Graphics g) {
        isPainting = true;
        super.paintComponent(g);
        isPainting = false;
    }
    
    public static void main(String args[]) throws Exception {
        JFrame frame = new JFrame("TWaver中文社区-twaver.servasoft.com");
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        contentPane.add(new TristateCheckBox("i am selected", SELECTED));
        contentPane.add(new TristateCheckBox("i am not specified", NOTSPECIFIED));
        contentPane.add(new TristateCheckBox("i am deselected", DESELECTED));
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }

}

