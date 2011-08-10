/*
 * MonthCombo.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package dyno.swing.beans;

import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author William Chen
 */
public class MonthCombo extends JComboBox {
    public void updateUI() {
        setUI(new MonthComboUI());
    }    
}
