package other.drawing;

/*huatu继承监听接口，在main中监听时才产生，且匿名
*
*Container=e.getSource()
*Container.getGraphics()
*
*e.getActionCommand()
*e.getActionCommand().equals("hong")，不可以用==
*
*p=getContentPane(),p监听画图动作
*
*hxz*/
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class SimpleDrawing implements MouseMotionListener {
    static final int r = 3;
    static int ox, oy;

    public void mouseDragged(MouseEvent e) {
        Container c = (Container) e.getSource();//Container=e.getSource()
        Graphics g = c.getGraphics();//Container.getGraphics 
        if (ox >= 0) {//移动时ox=-1
            g.setColor(new Color(0, 0, 0));
            g.drawLine(ox, oy, e.getX(), e.getY());
        }
        ox = e.getX();
        oy = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
        ox = -1;
        oy = -1;
    }

    public static void main(String arg[]) {
        JFrame f = new JFrame("Simple Drawing");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container p = f.getContentPane();
        //Graphics g.setColor(new Color(255,0,0)); 
        //ButtonEvent arg=new ButtonEvent(arg); 
        p.addMouseMotionListener(new SimpleDrawing());//contentPane监听 
        f.setSize(200, 200);
        f.setVisible(true);
    }
}
