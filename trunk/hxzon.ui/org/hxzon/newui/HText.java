package org.hxzon.newui;

import javax.swing.JTextField;

import org.hxzon.util.TextValidationList;


public class HText extends HWidget {
    protected TextValidationList textChecker;

    public HText(String id, String title) {
        this(id, title, false);

    }

    public HText(String id, String title, boolean notNull) {
        super(id, title);
        textChecker = new TextValidationList();
        if (notNull) {
            textChecker.addChecker("notNull", "typeError=不能为空");
        }
        HSwingUtils.setupChecker(this);
    }

    public HText(String id, String title, boolean notNull, String minLength, String maxLength) {
        super(id, title);
    }

    protected void setComponent() {
        component = new JTextField();
    }

    public void check() {
        textChecker.check(getComponent().getText(), id);
    }

    public TextValidationList getTextChecker() {
        return textChecker;
    }

    public JTextField getComponent() {
        return (JTextField) component;
    }

    public String getStringValue() {
        return getComponent().getText();
    }

    public void ancestorAdded() {
        super.ancestorAdded();
        if (page != null) {
            textChecker.addManager(page.getName());
        }
    }

}
