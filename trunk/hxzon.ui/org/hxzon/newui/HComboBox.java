package org.hxzon.newui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hxzon.newui.HSwingUtils.ButtonRenderer;


public class HComboBox extends JComboBox {
    private List srcData = new ArrayList();
    private Object selectedData;
    
    private ButtonRenderer renderer;
    private List<ChangeListener> listeners = new ArrayList<ChangeListener>();

    public HComboBox() {
        super();
        this.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedData = e.getItem();
                    fireChangeListener();
                }
            }

        });
    }
    
    public HComboBox(Object[] list){
        this();
        setSrcData(list);
    }

    public HComboBox(Collection list) {
        this();
        setSrcData(list);
    }

    public void setSrcData(Collection list) {
        this.srcData.clear();
        this.srcData.addAll(list);
        update();
    }
    
    public void setSrcData(Object[] list){
        this.srcData.clear();
        for(Object o:list){
            this.srcData.add(o);
        }
        update();
    }
    
    public void addSrcData(Collection list){
        this.srcData.addAll(list);
        update();
    }
    
    public void addSrcData(Object[] list){
        for(Object o:list){
            this.srcData.add(o);
        }
        update();
    }

    public List getSrcData() {
        return srcData;
    }

    public Object getSelectedData() {
        return selectedData;
    }
    
    private void update(){
        this.removeAllItems();
        for(Object o:srcData){
            this.addItem(getButtonRenderer().getText(o));
        }
    }
    
    public ButtonRenderer getButtonRenderer() {
        if (renderer == null) {
            renderer = new ButtonRenderer();
        }
        return renderer;
    }
    
    public void setButtonRenderer(ButtonRenderer renderer){
        this.renderer=renderer;
        update();
    }

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        listeners.remove(listener);
    }

    private void fireChangeListener() {
        ChangeEvent e = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(e);
        }
    }
    
}
