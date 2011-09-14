package other.drawing;

/*
*画图完整程序，有存储功能
*有橡皮筋效果，解决闪烁
*颜色选择板
*hxz*/
/**************************************************************
班别：04级4班
姓名：吕成宾
学号：20042102083

作业7.8：
    1.请设计一个简单的鼠标绘图程序，要求，能绘制基本图形（自由绘图，直线，椭圆，矩形）
2.增加图形的重画，存贮与文件操作
我的实现技术:
1.增加颜色选择框
2.采用JFileChooser文件保存与打开,文件保存与打开的后缀名为".msd"表示MouseDraw
3.采用对象串行化保存与加载.把自由绘图,直线,椭圆,矩形封装到一个类
4.在鼠标移动时进行重画,使画图时出现橡皮筋效果,但画面会出现闪烁
5.解决闪烁的方法时采用双缓存机制
***************************************************************/

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MouseDraw extends JFrame {
    private int drawType = 0; //画图类型 1.自由画图,2.画直线,3.画椭圆,4.画矩形
    private Color color = new Color(255, 0, 0); //画图颜色
    private int startX, startY; // 起点坐标
    private int currentX, currentY; //移动坐标(结束坐标)
    private ArrayList list; //保存绘图对象链表
    private DrawCanvas bp;

    MouseDraw() {
        setTitle("MouseDraw");
        setSize(800, 600);
        setLocation(100, 100);
        insertMenu();
//添加画布
        bp = new DrawCanvas();
        Container c = getContentPane();
        c.add(bp);
    }

    private void insertMenu() {
        MenuBar mb = new MenuBar();

        Menu m_File = new Menu("File");
        Menu m_Draw = new Menu("Draw");
        Menu m_Para = new Menu("Parameters");

        MenuItem mi_Open = new MenuItem("Open");
        MenuItem mi_Save = new MenuItem("Save");
        MenuItem mi_Exit = new MenuItem("Exit");
        MenuItem mi_Free = new MenuItem("DrawFree");
        MenuItem mi_Line = new MenuItem("DrawLine");
        MenuItem mi_Elli = new MenuItem("DrawElli");
        MenuItem mi_Rect = new MenuItem("DrawRect");
        MenuItem mi_Color = new MenuItem("Color");
        MenuItem mi_Clear = new MenuItem("Clear");
        m_File.add(mi_Open);
        m_File.add(mi_Save);
        m_File.add(mi_Exit);
        m_Draw.add(mi_Free);
        m_Draw.add(mi_Line);
        m_Draw.add(mi_Elli);
        m_Draw.add(mi_Rect);

        m_Para.add(mi_Color);
        m_Para.add(mi_Clear);

        mb.add(m_File);
        mb.add(m_Draw);
        mb.add(m_Para);

        setMenuBar(mb);
// 添加监听器
        mi_Free.addActionListener(new DrawAction(1));
        mi_Line.addActionListener(new DrawAction(2));
        mi_Elli.addActionListener(new DrawAction(3));
        mi_Rect.addActionListener(new DrawAction(4));
        mi_Clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (list.size() != 0) {
                    list.clear();
                    startX = startY = currentX = currentY = 0;
                    drawType = 0;
                    bp.repaint(); //重画

                }

            }
        });
        mi_Color.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                chooseColor();

            }
        });
        mi_Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                saveFile();
            }
        });
        mi_Open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                openFile();
            }
        });
        mi_Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0); // 退出
            }
        });

    }

    private void chooseColor() {
// 颜色对话框,选择颜色
        JColorChooser cc = new JColorChooser();
        color = cc.showDialog(this, "调色板", Color.red);
        startX = startY = currentX = currentY = 0;
    }

    private void saveFile() {
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "Save failed! There is no draw object!", "Result", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
// 文件保存对话框
        StringBuffer filePath = new StringBuffer(""); // 文件路径
        MyFilter my = new MyFilter();
        JFileChooser jf = new JFileChooser();
        jf.setFileFilter(my); // 设置过滤器
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = jf.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filePath.append(jf.getSelectedFile().getPath());
            // 如果没有后缀名,则加上后缀名
            if ((filePath.lastIndexOf(".")) == -1)
                filePath.append(".msd");

            try {
                ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(filePath.toString()));
                SaveObject so = new SaveObject(list);
                f.writeObject(so); //把对象写到文件中
                f.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openFile() {
//文件加载框
        MyFilter my = new MyFilter();
        JFileChooser jf = new JFileChooser();
        jf.setFileFilter(my); // 设置过滤器
        jf.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        int returnVal = jf.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) //按保存按钮时...
        {
            try {
                // 使用对象串行化读取文件,并绘图
                //JOptionPane.showMessageDialog(null, jf.getSelectedFile(), "Error", JOptionPane.ERROR_MESSAGE);
                ObjectInputStream f = new ObjectInputStream(new FileInputStream(jf.getSelectedFile()));
                SaveObject so = (SaveObject) (f.readObject());//读出对象
                f.close();
                list.clear(); // 先清空
                startX = startY = currentX = currentY = 0;
                drawType = 0;
                list = so.getArrayList();
                bp.repaint(); //重画
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

//为菜单"Draw"下的菜单项重写监听器,由构造函数里的参数传递画图类型到全局变量drawType
    private class DrawAction implements ActionListener {
        DrawAction(int type) {
            this.type = type;
        }

        public void actionPerformed(ActionEvent event) {
            drawType = type;
        }

        private int type;

    }

//内部类,画布:Canvas
    private class DrawCanvas extends Canvas {
        private DrawObject object;
        private ArrayList point;
        private ArrayList free;
        private Image bgImage;
        private Graphics bg;

        DrawCanvas() {
            setBackground(Color.white);
            addListener();
            list = new ArrayList();
            free = new ArrayList();
        }

        private void addListener() {
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (drawType != 0)
                        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    startX = currentX = e.getX();
                    startY = currentY = e.getY();
                    point = new ArrayList();
                    point.add(new Point(startX, startY)); //添加起始点
                }

                public void mouseReleased(MouseEvent e) //释放鼠标
                {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    currentX = e.getX();
                    currentY = e.getY();
                    point.add(new Point(currentX, currentY)); //添加结束点
                    object = new DrawObject(drawType, point, color);
                    list.add(object); //添加画图对象到list中
                    free.clear();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    currentX = e.getX();
                    currentY = e.getY();
                    // 自由画图时,把当前点添加到free链表和point链表中.
                    if (drawType == 1) {
                        free.add(new Point(currentX, currentY));
                        point.add(new Point(currentX, currentY));
                    }
                    repaint();
                }
            });
        }

//画椭圆
        private void drawElli(Graphics g, int start_x, int start_y, int current_x, int current_y) {
            if (current_x > start_x) {
                if (current_y > start_y)
                    g.drawOval(start_x, start_y, current_x - start_x, current_y - start_y);
                else
                    g.drawOval(start_x, current_y, current_x - start_x, start_y - current_y);
            } else {
                if (current_y > start_y)
                    g.drawOval(current_x, start_y, start_x - current_x, current_y - start_y);
                else
                    g.drawOval(current_x, current_y, start_x - current_x, start_y - current_y);
            }
        }

//画矩形
        private void drawRect(Graphics g, int start_x, int start_y, int current_x, int current_y) {
            if (current_x > start_x) {
                if (current_y > start_y) {
                    g.drawRect(start_x, start_y, current_x - start_x, current_y - start_y);
                } else {
                    g.drawRect(start_x, current_y, current_x - start_x, start_y - current_y);
                }
            } else {
                if (current_y > start_y) {
                    g.drawRect(current_x, start_y, start_x - current_x, current_y - start_y);
                } else {
                    g.drawRect(current_x, current_y, start_x - current_x, start_y - current_y);
                }
            }
        }

/*利用双缓冲机制清除画面闪烁
具体实现：
　　　 1.首先我们用createImage方法新建一后台图像类变量
　　　 2.然后使用getGraphics()方法得到当前图像的图形关联
　　　 3.在后台处理所有相关的处理，如清除屏幕，后台绘画等等
　　    当完成所有的后台工作后，复制已经绘制好的图像到前台，并覆盖前台的存在图像。
        这样我们的所有操作都是在后台前行，在屏幕显示新的图像前，这些内容都已经存
        在于后台了。所以你也将在任何时刻都看不到空屏幕的存在。也即代表闪烁消除了。

　　 Update()方法要实现下面三个步骤：
　　　 1.清除屏幕上的组件
　　　 2.设置相关联组件的前景色
　　　 3.调用paint方法重画屏幕
*/
        public void update(Graphics g) {
            //super.update(g);
            // 初始化buffer
            if (bgImage == null) {

                bgImage = createImage(this.getSize().width, this.getSize().height);
                bg = bgImage.getGraphics();

            }

            // 后台清屏,即设置组件和后台一样的颜色，大小
            bg.setColor(getBackground());
            bg.fillRect(0, 0, this.getSize().width, this.getSize().height);

            // 绘制相应的元素组件
            bg.setColor(getForeground());
            paint(bg);

            // 在屏幕上重画已经绘制好的圆
            g.drawImage(bgImage, 0, 0, this);

        }

//paint函数
        public void paint(Graphics g) {
            ArrayList p;
            //绘制之前保存的对象
            for (int i = 0; i < list.size(); i++) {
                g.setColor(((DrawObject) list.get(i)).getColor());
                p = ((DrawObject) list.get(i)).getArrayList();
                switch (((DrawObject) list.get(i)).getDrawtype()) {
                //1.自由画图,2.画直线,3.画椭圆,4.画矩形
                case 1: {
                    for (int j = 0; j < p.size() - 1; j++) {
                        g.drawLine((int) ((Point) p.get(j)).getX(), (int) ((Point) p.get(j)).getY(), (int) ((Point) p.get(j + 1)).getX(), (int) ((Point) p.get(j + 1)).getY());
                    }
                    break;
                }
                case 2: {
                    g.drawLine((int) ((Point) p.get(0)).getX(), (int) ((Point) p.get(0)).getY(), (int) ((Point) p.get(1)).getX(), (int) ((Point) p.get(1)).getY());
                    break;
                }
                case 3: {
                    drawElli(g, (int) ((Point) p.get(0)).getX(), (int) ((Point) p.get(0)).getY(), (int) ((Point) p.get(1)).getX(), (int) ((Point) p.get(1)).getY());
                    break;
                }
                case 4: {
                    drawRect(g, (int) ((Point) p.get(0)).getX(), (int) ((Point) p.get(0)).getY(), (int) ((Point) p.get(1)).getX(), (int) ((Point) p.get(1)).getY());
                    break;
                }
                default: {
                    break;
                }
                }
            }
            //对当前的点画图,使效果看起来来像拉动的橡皮筋,free链表在这里起作用,想想为什么不用point链表?
            g.setColor(color);
            switch (drawType) {
            case 1:
                for (int j = 0; j < free.size() - 1; j++) {
                    g.drawLine((int) ((Point) free.get(j)).getX(), (int) ((Point) free.get(j)).getY(), (int) ((Point) free.get(j + 1)).getX(), (int) ((Point) free.get(j + 1)).getY());
                }
                break;
            case 2:
                g.drawLine(startX, startY, currentX, currentY);
                break;
            case 3:
                drawElli(g, startX, startY, currentX, currentY);
                break;
            case 4:
                drawRect(g, startX, startY, currentX, currentY);
                break;
            default:
                break;
            }
        }
    }

    //绘图对象
    class DrawObject implements Serializable {
        int drawType; //绘图类型
        ArrayList point; //用来保存直线,椭圆,矩形起点坐标和终点坐标的链表
                         //用来保存自由绘图的移动过的每一个坐标
        Color color; //绘图颜色

        public DrawObject(int drawType, ArrayList point, Color color) {
            this.drawType = drawType;
            this.point = point;
            this.color = color;
        }

        public int getDrawtype() {
            return drawType;
        }

        public Color getColor() {
            return color;
        }

        public ArrayList getArrayList() {
            return point;
        }
    }

    // 设置过滤器,文件类型为".msd" 表示:mousedraw
    class MyFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File f) {
            if (f.isDirectory())
                return true;
            return f.getName().endsWith(".msd");
        }

        public String getDescription() {
            return ".msd";
        }
    }

    // 对象串行化 
    class SaveObject implements Serializable {
        private ArrayList list;

        public SaveObject(ArrayList list) {
            this.list = list;
        }

        public ArrayList getArrayList() {
            return list;
        }
    }

    public static void main(String args[]) {
        MouseDraw md = new MouseDraw();
        md.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        md.setVisible(true);
    }
}

/*
*H:\java work\java ok>javac -deprecation DrawMain.java
注意：DrawMain.java 使用了未经检查或不安全的操作。
注意：要了解详细信息，请使用 -Xlint:unchecked 重新编译。

H:\java work\java ok>javac -Xlint:unchecked DrawMain.java
DrawMain.java:300: 警告：[unchecked] 对作为普通类型 java.util.ArrayList 的成员的
add(E) 的调用未经检查
                                        point.add(new Point(startX, startY)); /
/添加起始点
                                                 ^
DrawMain.java:307: 警告：[unchecked] 对作为普通类型 java.util.ArrayList 的成员的
add(E) 的调用未经检查
                                        point.add(new Point(currentX, currentY))
; //添加结束点
                                                 ^
DrawMain.java:309: 警告：[unchecked] 对作为普通类型 java.util.ArrayList 的成员的
add(E) 的调用未经检查
                                        list.add(object); //添加画图对象到list中

                                                ^
DrawMain.java:323: 警告：[unchecked] 对作为普通类型 java.util.ArrayList 的成员的
add(E) 的调用未经检查
                                                free.add(new Point(currentX, cur
rentY));
                                                        ^
DrawMain.java:324: 警告：[unchecked] 对作为普通类型 java.util.ArrayList 的成员的
add(E) 的调用未经检查
                                                point.add(new Point(currentX, cu
rrentY));
                                                         ^
5 警告*/
