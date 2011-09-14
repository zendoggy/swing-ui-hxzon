package test.swing;

import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CurrencyBox extends JTextField {

    public CurrencyBox() {
      this.getDocument().addDocumentListener(new DocumentListener() {

        public void changedUpdate(DocumentEvent e) {
        }

        public void insertUpdate(DocumentEvent e) {
            // currency symbol must be showed on the right of numeric value and
            // current numeric value is null and user attempts to write the first digit:
            // without rewriting just editing data, it would be write on the right of the text field and
            // this is not correct...
            try {
              final BigDecimal n = new BigDecimal(getText());
              SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                  setText(n+"");
                  setCaretPosition(getText().length());
                }
              });
            }
            catch (Exception ex1) {
            }
        }

        public void removeUpdate(DocumentEvent e) {
        }

      });
    }


    public void processKeyEvent(KeyEvent e) {
      super.processKeyEvent(e);
    }

  }


