package org.hxzon.newui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import net.miginfocom.swing.MigLayout;

public abstract class HWidget extends JPanel {
    protected String id;
    protected String title;
    protected JComponent component;
    protected HPage page;
    protected HPanel form;
    protected static AncestorListener l;
    
    public HWidget(String id){
        this(id, null);
    }

    public HWidget(String id,String title) {
        super(new MigLayout());
        this.id = id;
        this.title=title;
        setComponent();
        l=new AncestorListener(){

            @Override
            public void ancestorAdded(AncestorEvent event) {
                HWidget.this.ancestorAdded();
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }
            
        };
        component.addAncestorListener(l);
        if (title != null) {
            add(new JLabel(title));
        }
        add(component);
    }

    abstract void setComponent();

    public void setForm(HPanel form) {
        this.form = form;
    }

    public String getId() {
        return id;
    }
    
    public String getTitle(){
        return title;
    }

    public JComponent getComponent() {
        return component;
    }

    public HPanel getForm() {
        if (form == null) {
            if (this.getParent() instanceof HPanel) {
                form = (HPanel) this.getParent();
            }
        }
        return form;
    }

    public HTitlePanel getTitlePanel() {
        if (page != null) {
            return page.getTitlePanel();
        }
        return null;
    }
    
    public void ancestorAdded(){
        page = HSwingUtils.getPage(HWidget.this);
        if (page != null) {
            page.widgets.put(getId(), HWidget.this);
        }
    }
    
    public abstract String getStringValue();
    
    public int getIntValue(){
        return Integer.valueOf(getStringValue());
    }
    
}
