package org.hxzon.demo.swingx;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hxzon.swing.layout.simple.SimpleLayout;
import org.hxzon.swing.layout.simple.SimpleLayoutData;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.renderer.DefaultListRenderer;
import org.jdesktop.swingx.renderer.StringValues;

public class JXListDemo extends JFrame {
    public JXListDemo() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new SimpleLayout());
        this.add(demo(), SimpleLayoutData.fillPercent(100));
        this.setLocation(300, 300);
        this.pack();
    }

    public JPanel demo() {
        JPanel panel = new JPanel(new SimpleLayout());
        JXList list = new JXList(personData());
        //?
        list.setSortable(true);
        list.setAutoCreateRowSorter(true);
        // implement a custom string representation, concated from first-, lastName
        org.jdesktop.swingx.renderer.StringValue sv = new org.jdesktop.swingx.renderer.StringValue() {
            public String getString(Object value) {
                if (value instanceof Person) {
                    Person contributor = (Person) value;
                    return contributor.getId() + ", " + contributor.getEmail();
                }
                return StringValues.TO_STRING.getString(value);
            }
        };
        list.setCellRenderer(new DefaultListRenderer(sv));
        // highlight condition: email.endsWith(".com")
        HighlightPredicate predicate = new HighlightPredicate() {
            public boolean isHighlighted(Component renderer, ComponentAdapter adapter) {
                if (!(adapter.getValue() instanceof Person))
                    return false;
                return ((Person) adapter.getValue()).getEmail().endsWith(".com");
            }
        };
        // highlight with foreground color 
        list.addHighlighter(new ColorHighlighter(predicate, null, Color.RED));
//        list.addHighlighter(new PainterHighlighter(predicate, goldStarPainter));
        list.setRolloverEnabled(true);
        //highlight odd row
        list.addHighlighter(new ColorHighlighter(HighlightPredicate.ODD, Color.decode("#d6e7ff"), null));
        //highlight mouse hover
        list.addHighlighter(new ColorHighlighter(HighlightPredicate.ROLLOVER_ROW, Color.BLUE, Color.RED));
        //ctrl+F open a search dialog
        panel.add(new JScrollPane(list), SimpleLayoutData.fillPercent(100));
        return panel;

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JXListDemo().setVisible(true);
                    ;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public static Person[] personData(){
        return new Person[]{
                new Person("1", "hello@163.com"),
                new Person("2", "hi@126.com"),
                new Person("3", "hxzon@163.org"),
                new Person("4", "hd@126.com"),
                new Person("5", "xzon@qq.org"), 
                new Person("6", "xuezh@mail.com"), 
                new Person("7", "baidu@mail.com"), 
                new Person("8", "hi@mail.org"), 
                new Person("9", "hello@mail.org"),
        };
    }

    public static class Person {
        private String id;
        private String email;

        public Person(String id, String email) {
            this.id = id;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }

}
