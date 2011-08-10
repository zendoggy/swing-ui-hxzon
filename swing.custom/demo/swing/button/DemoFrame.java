package demo.swing.button;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DemoFrame extends JFrame {
    
    /**
     * Launch the application
     *
     * @param args
     */
    public static void main(String args[]) {
        try {
            DemoFrame frame = new DemoFrame();
            frame.getContentPane().setLayout(new FlowLayout());
            ImageButton button = new ImageButton();
            button.setBackgroundImage(new ImageIcon(DemoFrame.class
                    .getResource("button_up.png")));
            button.setRolloverBackgroundImage(new ImageIcon(DemoFrame.class
                    .getResource("button_over.png")));
            button.setPressedBackgroundImage(new ImageIcon(DemoFrame.class
                    .getResource("button_down.png")));
            button.setForeground(Color.WHITE);
            button.setText("demo");
            frame.getContentPane().add(button);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Create the frame
     */
    public DemoFrame() {
        super();
        setBounds(100, 100, 500, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
    }
    
}
