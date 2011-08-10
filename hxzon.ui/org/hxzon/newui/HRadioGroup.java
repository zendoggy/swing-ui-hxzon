package org.hxzon.newui;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hxzon.newui.HSwingUtils.ButtonRenderer;
import org.hxzon.newui.HSwingUtils.NameProvider;


public class HRadioGroup extends JPanel {
    private List srcData;
    private Object selectedData;
    private List<JRadioButton> buttonList = new ArrayList<JRadioButton>();
    private ButtonGroup group = new ButtonGroup();
    private ButtonRenderer renderer;
    private NameProvider nameProvider;

    private boolean flag = false;

    private List<ChangeListener> listeners = new ArrayList<ChangeListener>();

    public HRadioGroup(List list, String title) {
        this(list, title, null, null);
    }

    public HRadioGroup(List list, String title, ButtonRenderer renderer2, NameProvider nameProvider2) {
        super(new FlowLayout(FlowLayout.LEADING, 0, 0));
        srcData = list;
        renderer = renderer2;
        nameProvider = nameProvider2;
        setBorder(BorderFactory.createTitledBorder(getBorder(), title));
        if (srcData == null || srcData.size() == 0) {
            return;
        }

        ItemListener l = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int index = buttonList.indexOf(e.getItemSelectable());
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedData = srcData.get(index);
                    fireChangeListener();
                }
            }
        };
        for (Object o : srcData) {
            JRadioButton button = new JRadioButton(getRenderer().getText(o));
            button.setName(getNameProvider().getName(o));
            button.addItemListener(l);
            add(button);
            group.add(button);
            buttonList.add(button);
        }
    }

    public Object getSelection() {
        return selectedData;
    }

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        listeners.remove(listener);
    }

    private void fireChangeListener() {
        if (flag) {
            return;
        }
        ChangeEvent e=new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(e);
        }
    }

    public JRadioButton getButton(String name) {
        if (name == null) {
            return null;
        }
        for (JRadioButton button : buttonList) {
            if (name.equals(button.getName()))
                return button;
        }
        return null;
    }
    
    public void setEnabled(boolean b){
        for(JRadioButton button:buttonList){
            button.setEnabled(b);
        }
        super.setEnabled(b);
    }

    // public void setRenderer(JCheckBoxRenderer renderer) {
    // this.renderer = renderer;
    // }

    public ButtonRenderer getRenderer() {
        if (renderer == null) {
            renderer = new ButtonRenderer();
        }
        return renderer;
    }

    // public void setNameProvider(NameProvider provider) {
    // this.nameProvider = provider;
    // }

    public NameProvider getNameProvider() {
        if (nameProvider == null) {
            nameProvider = new NameProvider();
        }
        return nameProvider;
    }

}
