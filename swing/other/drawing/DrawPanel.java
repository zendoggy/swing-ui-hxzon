package other.drawing;

/*DrawPanel.java，直接使用直线曲线椭圆类，有橡皮筋效果,不会闪烁-Java画图实验-swingcode -gcode
*
*LinkedList<Shape> shapeList =new LinkedList<Shape>();
*
*BasicStroke
*Stroke 接口允许 Graphics2D 对象获得一个 Shape
*描绘一个 Shape 如同使用一支具有适当大小和形状的画笔描绘其轮廓。
*画笔着墨的区域是轮廓 Shape 封闭的区域。
*
*Graphics2D，Line2D.Float，Ellipse2D.Float，Rectangle
*
*Shape s:shapeList
*
*panel.setPreferredSize(new Dimension(320,240));
*hxz*/
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RectangularShape;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
*简单的画图程序
*@author Eastsun
*@version .1
*/
public class DrawPanel extends JFrame {
    LinkedList<Shape> shapeList = new LinkedList<Shape>();
    Shape shape;
    Stroke stroke = new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[] { 8f, 3f }, 1);
    Point start, end;
    final String[] type = new String[] { "Line", "Rectangle", "Ellipse" };
    JComboBox comboBox = new JComboBox(type);

    public DrawPanel() {
        super("DrawPanel");
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {//paintComponent
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.white);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(Color.black);
                for (Shape s : shapeList)
                    g2.draw(s);//
                if (shape != null) {
                    g2.setStroke(stroke);
                    g2.setPaint(Color.blue);//为 Graphics2D 上下文设置 Paint 属性
                    g2.draw(shape);//使用当前 Graphics2D 上下文的设置勾画 Shape 的轮廓
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
                if (shape != null)
                    shapeList.add(shape);
                shape = null;
                repaint();
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                end = e.getPoint();
                Object select = comboBox.getSelectedItem();
                if (select.equals(type[0])) //Line2D.Float是一个类
                    shape = new Line2D.Float(start, end);//设置形状为直线
                else { //设置形状为矩形或椭圆
                    if (select.equals(type[1]))
                        shape = new Rectangle();
                    else
                        shape = new Ellipse2D.Float();

                    ((RectangularShape) shape).setFrameFromDiagonal(start, end);
                }
                repaint();
            }
        });

        panel.setPreferredSize(new Dimension(320, 240));
        add(panel, BorderLayout.NORTH);

        add(comboBox, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }//end DrawPanel()

    public static void main(String[] args) {
        new DrawPanel();
    }
}
