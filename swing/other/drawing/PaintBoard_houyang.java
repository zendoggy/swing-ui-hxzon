package other.drawing;

/*houyang.java,可调画笔粗细-Java画图实验-swingcode -gcode
*画直线，曲线，矩形，椭圆，可调颜色，有橡皮擦功能
*可调画笔粗细
*没有解决闪烁功能
*hxz*/
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JColorChooser;

/*
*BasicStroke
*Choice
*validate();
**/
public class PaintBoard_houyang extends Frame implements ActionListener, MouseMotionListener, MouseListener, ItemListener {
    private static class PointInfo {
        int x, y;
        Color col;
        int tool;
        int boarder;

        PointInfo(int x, int y, Color col, int tool, int boarder) {
            this.x = x;
            this.y = y;
            this.col = col;
            this.tool = tool;
            this.boarder = boarder;
        }
    }

    int xx0 = 0, yy0 = 0;
    int xx1 = 0, yy1 = 0;
    int type = 6;
    int x = -1, y = -1;
    int con = 1;//画笔大小
    int Econ = 5;//橡皮大小
    int toolFlag = 0;//toolFlag:工具标记
    //toolFlag工具对应表：
    //（0--画笔）；（1--橡皮）；（2--清除）；
    //（3--直线）；（4--圆）；（5--矩形）；

    Color c = new Color(0, 0, 0); //画笔颜色
    BasicStroke size = new BasicStroke(con, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);//画笔粗细
    PointInfo cutflag = new PointInfo(-1, -1, c, 6, con);//截断标志
    Vector paintInfo = null;//点信息向量组
    int n = 1;

    // *工具面板--画笔，直线，圆，矩形，多边形,橡皮，清除*/
    Panel toolPanel;
    Button eraser, drLine, drCircle, drRect;
    Button clear, pen;
    Choice ColChoice, SizeChoice, EraserChoice;
    Button colchooser;
    Label colorLabel, sizeLabel, eraserSizeLabel;

    PaintBoard_houyang(String s)//构造函数
    {
        super(s);
        addMouseMotionListener(this);
        addMouseListener(this);
        paintInfo = new Vector();
        /*各工具按钮及选择项*/
        //颜色选择
        ColChoice = new Choice();
        ColChoice.add("黑色");
        ColChoice.add("红色");
        ColChoice.add("蓝色");
        ColChoice.add("绿色");
        ColChoice.addItemListener(this);
        //画笔大小选择
        SizeChoice = new Choice();
        SizeChoice.add("1");
        SizeChoice.add("3");
        SizeChoice.add("5");
        SizeChoice.add("7");
        SizeChoice.add("9");
        SizeChoice.addItemListener(this);
        //橡皮大小选择
        EraserChoice = new Choice();
        EraserChoice.add("2");
        EraserChoice.add("3");
        EraserChoice.add("4");
        EraserChoice.add("5");
        EraserChoice.addItemListener(this);

        toolPanel = new Panel();
        clear = new Button("清除");
        eraser = new Button("橡皮");
        pen = new Button("画笔");
        drLine = new Button("画直线");
        drCircle = new Button("画圆形");
        drRect = new Button("画矩形");
        colchooser = new Button("显示调色板");

        //各组件事件监听
        clear.addActionListener(this);
        eraser.addActionListener(this);
        pen.addActionListener(this);
        drLine.addActionListener(this);
        drCircle.addActionListener(this);
        drRect.addActionListener(this);
        colchooser.addActionListener(this);
        colorLabel = new Label("画笔颜色", Label.CENTER);
        sizeLabel = new Label("画笔大小", Label.CENTER);
        eraserSizeLabel = new Label("橡皮大小", Label.CENTER);
        //面板添加组件
        toolPanel.add(pen);
        toolPanel.add(drLine);
        toolPanel.add(drCircle);
        toolPanel.add(drRect);
        toolPanel.add(colorLabel);
        toolPanel.add(ColChoice);
        toolPanel.add(sizeLabel);
        toolPanel.add(SizeChoice);
        toolPanel.add(colchooser);
        toolPanel.add(eraser);
        toolPanel.add(eraserSizeLabel);
        toolPanel.add(EraserChoice);
        toolPanel.add(clear);
        //工具面板到APPLET面板
        add(toolPanel, BorderLayout.NORTH);
        setBounds(60, 60, 800, 650);
        setVisible(true);
        validate();

        //dialog for save and load
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        PointInfo p1, p2;
        n = paintInfo.size();
        if (toolFlag == 2)
            g.clearRect(0, 0, getSize().width, getSize().height);//清除

        switch (type) {
        case 3:
            Line2D line = new Line2D.Double(xx0, yy0, xx1, yy1);
            g2d.draw(line);
            break;
        case 4:
            Ellipse2D ellipse1 = new Ellipse2D.Double(xx0, yy0, Math.abs(xx1 - xx0), Math.abs(yy1 - yy0));
            g2d.draw(ellipse1);
            break;
        case 5:
            Rectangle2D rect1 = new Rectangle2D.Double(xx0, yy0, Math.abs(xx1 - xx0), Math.abs(yy1 - yy0));
            g2d.draw(rect1);
            break;
        default:
            break;
        }

        for (int i = 0; i < n - 1; i++) {
            p1 = (PointInfo) paintInfo.elementAt(i);
            p2 = (PointInfo) paintInfo.elementAt(i + 1);
            size = new BasicStroke(p1.boarder, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
            g2d.setColor(p1.col);
            g2d.setStroke(size);

            if (p1.tool == p2.tool) {
                switch (p1.tool) {
                case 0://画笔
                    Line2D line1 = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
                    g2d.draw(line1);
                    break;
                case 1://橡皮
                    g.clearRect(p1.x, p1.y, p1.boarder, p1.boarder);
                    break;
                case 3://画直线
                    Line2D line2 = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
                    g2d.draw(line2);
                    break;
                case 4://画圆
                    Ellipse2D ellipse = new Ellipse2D.Double(p1.x, p1.y, Math.abs(p2.x - p1.x), Math.abs(p2.y - p1.y));
                    g2d.draw(ellipse);
                    break;
                case 5://画矩形
                    Rectangle2D rect = new Rectangle2D.Double(p1.x, p1.y, Math.abs(p2.x - p1.x), Math.abs(p2.y - p1.y));
                    g2d.draw(rect);
                    break;
                case 6://截断，跳过
                    i = i + 1;
                    break;
                default:
                    break;
                }//end switch
            }//end if
        }//end for
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == ColChoice)//预选颜色
        {
            String name = ColChoice.getSelectedItem();
            if (name == "黑色") {
                c = new Color(0, 0, 0);
            } else if (name == "红色") {
                c = new Color(255, 0, 0);
            } else if (name == "绿色") {
                c = new Color(0, 255, 0);
            } else if (name == "蓝色") {
                c = new Color(0, 0, 255);
            }
        } else if (e.getSource() == SizeChoice)//画笔大小
        {
            String selected = SizeChoice.getSelectedItem();
            if (selected == "1") {
                con = 1;
                size = new BasicStroke(con, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
            } else if (selected == "3") {
                con = 3;
                size = new BasicStroke(con, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
            } else if (selected == "5") {
                con = 5;
                size = new BasicStroke(con, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
            } else if (selected == "7") {
                con = 7;
                size = new BasicStroke(con, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
            } else if (selected == "9") {
                con = 9;
                size = new BasicStroke(con, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
            }
        } else if (e.getSource() == EraserChoice)//橡皮大小
        {
            String Esize = EraserChoice.getSelectedItem();
            if (Esize == "2") {
                Econ = 3 * 3;
            } else if (Esize == "3") {
                Econ = 4 * 4;
            } else if (Esize == "4") {
                Econ = 5 * 5;
            } else if (Esize == "5") {
                Econ = 6 * 6;
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        xx1 = (int) e.getX();
        yy1 = (int) e.getY();
        PointInfo p1;
        switch (toolFlag) {
        case 0://画笔
            x = (int) e.getX();
            y = (int) e.getY();
            p1 = new PointInfo(x, y, c, toolFlag, con);
            paintInfo.addElement(p1);
            repaint();
            break;

        case 1://橡皮
            x = (int) e.getX();
            y = (int) e.getY();
            p1 = new PointInfo(x, y, null, toolFlag, Econ);
            paintInfo.addElement(p1);
            repaint();
            break;
        case 3:

        case 4:
        case 5:
            repaint();
            break;

        default:
        }
//repaint();
    }

    public void mousePressed(MouseEvent e) {
        xx0 = (int) e.getX();
        yy0 = (int) e.getY();
        PointInfo p2;
        switch (toolFlag) {
        case 3://直线
            type = 3;
            x = (int) e.getX();
            y = (int) e.getY();
            p2 = new PointInfo(x, y, c, toolFlag, con);
            paintInfo.addElement(p2);
            break;

        case 4: //圆
            type = 4;
            x = (int) e.getX();
            y = (int) e.getY();
            p2 = new PointInfo(x, y, c, toolFlag, con);
            paintInfo.addElement(p2);
            break;

        case 5: //矩形
            type = 5;
            x = (int) e.getX();
            y = (int) e.getY();
            p2 = new PointInfo(x, y, c, toolFlag, con);
            paintInfo.addElement(p2);
            break;

        default:
            type = 6;
        }
    }

    public void mouseReleased(MouseEvent e) {
        PointInfo p3;
        switch (toolFlag) {
        case 0: //画笔
            paintInfo.addElement(cutflag);
            break;
        case 1: //eraser
            paintInfo.addElement(cutflag);
            break;
        case 3: //直线
            x = (int) e.getX();
            y = (int) e.getY();
            p3 = new PointInfo(x, y, c, toolFlag, con);
            paintInfo.addElement(p3);
            paintInfo.addElement(cutflag);
            repaint();
            break;
        case 4: //圆
            x = (int) e.getX();
            y = (int) e.getY();
            p3 = new PointInfo(x, y, c, toolFlag, con);
            paintInfo.addElement(p3);
            paintInfo.addElement(cutflag);
            repaint();
            break;
        case 5: //矩形
            x = (int) e.getX();
            y = (int) e.getY();
            p3 = new PointInfo(x, y, c, toolFlag, con);
            paintInfo.addElement(p3);
            paintInfo.addElement(cutflag);
            repaint();
            break;
        default:
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pen)//画笔
        {
            toolFlag = 0;
        }
        if (e.getSource() == eraser)//橡皮
        {
            toolFlag = 1;
        }
        if (e.getSource() == clear)//清除
        {
            toolFlag = 2;
            paintInfo.removeAllElements();
            repaint();
        }
        if (e.getSource() == drLine)//画线
        {
            toolFlag = 3;
        }
        if (e.getSource() == drCircle)//画圆
        {
            toolFlag = 4;
        }
        if (e.getSource() == drRect)//画矩形
        {
            toolFlag = 5;
        }
        if (e.getSource() == colchooser)//调色板
        {
            Color newColor = JColorChooser.showDialog(this, "调色板", c);
            c = newColor;
        }
    }

    public static void main(String args[]) {
        new PaintBoard_houyang("画图程序");
    }
}//end

/*
*javac -Xlint:unchecked houyang.java
*
houyang.java:270: 警告：[unchecked] 对作为普通类型 java.util.Vector 的成员的 add
Element(E) 的调用未经检查
                paintInfo.addElement(p1);
                                    ^
13 警告
*/
