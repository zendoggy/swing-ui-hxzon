package org.hxzon.newui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hxzon.newui.HSwingUtils.ButtonRenderer;


public class HCheckBoxGroup {
    private List titles;
    private List srcData;
    private List selectedData;
    private List unselectedData;
    private List<JCheckBox> buttonList = new ArrayList<JCheckBox>();
    ItemListener l ;
    private ButtonRenderer buttonRenderer;
    

    private boolean flag = false;

    private List<ChangeListener> listeners = new ArrayList<ChangeListener>();

    private void reset(){
        
    }
    public HCheckBoxGroup(){
        
    }
    public HCheckBoxGroup(List list, String title) {
        this(list, title, JCheckBox.class);
    }

    public HCheckBoxGroup(List list, String title, Class<? extends JCheckBox> clazz) {
        srcData = list;
        
        if (srcData == null || srcData.size() == 0) {
            return;
        }
        selectedData = new ArrayList();
        unselectedData =new ArrayList();
        unselectedData.addAll(srcData);
        l = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int index = buttonList.indexOf(e.getItemSelectable());
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedData.add(srcData.get(index));
                    unselectedData.remove(srcData.get(index));
                } else {
                    selectedData.remove(srcData.get(index));
                    unselectedData.add(srcData.get(index));
                }
                fireChangeListener();
            }
        };
            for(Object o: list){
                JCheckBox check=null;
                if(o instanceof JCheckBox){
                    check=(JCheckBox)o;
                    
                }else{
//                check = instance(clazz);
                }
                buttonList.add(check);
                check.addItemListener(l);
            }
        JCheckBox all = new JCheckBox("全选/清除");
        all.setName("all");
        
//        buttonList.add(all);//not add
    }

    public List getSelection() {
        return selectedData;
    }
    
    public List getUnselection(){
        return unselectedData;
    }

    public boolean isSelectAll() {
        return srcData.size() == selectedData.size();
    }

    public boolean isSelectEmpty() {
        return selectedData.isEmpty();
    }

    public int getSelectionSize() {
        return selectedData.size();
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

    public JCheckBox getCheckBox(String name) {
        if (name == null) {
            return null;
        }
        for (JCheckBox button : buttonList) {
            if (name.equals(button.getName()))
                return button;
        }
        return null;
    }
    
    public List<JCheckBox> getCheckBoxs(){
        return buttonList;
    }
    
    public void setEnabled(boolean b){
        for(JCheckBox button:buttonList){
            button.setEnabled(b);
        }
    }
    
    public JCheckBox getAllCheckBox(String text,String name){
        JCheckBox all=new JCheckBox();
        all.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                flag = true;
                for (JCheckBox button : buttonList) {
                    button.setSelected(selected);
                }
                flag = false;
                fireChangeListener();
            }
        });
        return all;
    }
    
    public void addCheckBox(JCheckBox button){
        button.addItemListener(l);
        buttonList.add(button);
    }
    
    public void addAllButton(JCheckBox button){
        button.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                flag = true;
                for (JCheckBox button : buttonList) {
                    button.setSelected(selected);
                }
                flag = false;
                fireChangeListener();
            }
        });
    }
    
    public void setAllowAll(boolean allow){
        
    }
    
    public void setSrcData(Collection values,Collection titles){
        this.srcData.clear();
        this.srcData.addAll(values);
        this.titles.clear();
        this.titles.addAll(titles);
    }
    
    public void setTitles(){
        
    }
    
    public void setButtonRenderer(ButtonRenderer buttonRenderer){
        this.buttonRenderer=buttonRenderer;
    }
    
    public ButtonRenderer getButtonRenderer(){
        if(this.buttonRenderer==null){
            this.buttonRenderer=new ButtonRenderer();
        }
        return this.buttonRenderer;
    }
    
    

}
