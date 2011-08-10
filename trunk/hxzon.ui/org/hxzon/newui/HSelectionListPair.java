package org.hxzon.newui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HSelectionListPair extends JPanel {
    private JList srcList;
    private JList selectedList;
    private JButton addBt, removeBt, addAllBt, removeAllBt;

    private List srcData;
    private List selectedData;
    private Comparator indexCompator;

    private ListCellRenderer srcListRenderer;
    private ListCellRenderer selectedListRenderer;
    private boolean showIndex = true;
    private boolean resort = false;

    private List<ChangeListener> listeners = new ArrayList<ChangeListener>();

    public HSelectionListPair(List list, String srcListTitle, String destListTitle) {
        /* 初始化界面布局 */
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // center button panel(add, remove, addAll, removeAll)
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        addBt = new JButton();
        removeBt = new JButton();
        addAllBt = new JButton();
        removeAllBt = new JButton();
        addBt.setFocusable(false);
        removeBt.setFocusable(false);
        addAllBt.setFocusable(false);
        removeAllBt.setFocusable(false);
        addBt.setToolTipText("添加到右侧列表");
        removeBt.setToolTipText("从右侧列表移除");
        addAllBt.setToolTipText("添加全部");
        removeAllBt.setToolTipText("移除全部");
        center.add(addBt);
        center.add(Box.createVerticalStrut(10));
        center.add(removeBt);
        center.add(Box.createVerticalStrut(30));
        center.add(addAllBt);
        center.add(Box.createVerticalStrut(10));
        center.add(removeAllBt);
        // left list
        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(150, 350));
        left.setSize(new Dimension(150, 350));
        left.setBorder(BorderFactory.createTitledBorder(left.getBorder(), srcListTitle));
        left.setLayout(new BorderLayout());
        srcList = new JList();
        srcList.setCellRenderer(getSrcListRenderer());
        srcList.setVisibleRowCount(10);
        left.add(new JScrollPane(srcList), BorderLayout.CENTER);
        // right list
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(150, 350));
        right.setSize(new Dimension(150, 350));
        right.setBorder(BorderFactory.createTitledBorder(right.getBorder(), destListTitle));
        right.setLayout(new BorderLayout());
        selectedList = new JList();
        selectedList.setCellRenderer(getSelectedListRenderer());
        selectedList.setVisibleRowCount(10);
        right.add(new JScrollPane(selectedList), BorderLayout.CENTER);
        // add them all
        add(left);
        add(center);
        add(right);

        /* 初始化数据 */
        selectedData = new ArrayList();
        if (list != null) {
            srcList.setListData(list.toArray());
            this.srcData = list;
        }
        indexCompator = new Comparator() {

            public int compare(Object o1, Object o2) {
                return srcData.indexOf(o1) - srcData.indexOf(o2);
            }

        };

        /* 初始化行为 */
        addBt.setAction(new AbstractAction(" >>") {
            public void actionPerformed(ActionEvent e) {
                add(true);
            }
        });
        removeBt.setAction(new AbstractAction("<< ") {
            public void actionPerformed(ActionEvent e) {
                remove();
            }
        });
        addAllBt.setAction(new AbstractAction(">>>") {
            public void actionPerformed(ActionEvent e) {
                add_All();
            }
        });
        removeAllBt.setAction(new AbstractAction("<<<") {
            public void actionPerformed(ActionEvent e) {
                remove_All();
            }
        });
        srcList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !selectedData.contains(srcList.getSelectedValue())) {
                    add(false);
                    addBt.setEnabled(false);
                }
            }
        });
        selectedList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2)
                    remove();
            }
        });
        // 初始状态右侧为空，故禁用两个移除按钮
        removeAllBt.setEnabled(false);
        removeBt.setEnabled(false);
        // 初始状态如果左侧列表为空，则禁用">>>"按钮
        if (srcData.size() < 1)
            addAllBt.setEnabled(false);
        // 初始状态如果左侧列表无选中项，则禁用">>"按钮
        if (srcList.getSelectionModel().isSelectionEmpty())
            addBt.setEnabled(false);
        // 监听srcList的当前项是否为在已选中项中，决定">>"按钮状态
        srcList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    addBt.setEnabled(!selectedData.contains(srcList.getSelectedValue()));
                }
            }
        });
        ChangeListener selectBtListener = new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                int selectedCount = selectedData.size();
                int srcCount = srcData.size();
                addBt.setEnabled(selectedCount != srcCount);
                addAllBt.setEnabled(selectedCount != srcCount);
                removeBt.setEnabled(selectedCount != 0);
                removeAllBt.setEnabled(selectedCount != 0);
            }

        };
        addChangeListener(selectBtListener);
    }

    public List getSelection() {
        return selectedData;
    }

    public void addSelection(int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            return;
        }
        if (toIndex > srcData.size()) {
            toIndex = srcData.size();
        }
        List sublist = srcData.subList(fromIndex, toIndex);
        selectedData.removeAll(sublist);
        selectedData.addAll(sublist);
        reInitSelectedData();
        fireChangeListener();
    }

    public void clearSelection() {
        selectedData.clear();
        reInitSelectedData();
        fireChangeListener();
    }

    public boolean isSelectAll() {
        return srcData.size() == selectedData.size();
    }

    public boolean isSelectEmpty() {
        return selectedData.isEmpty();
    }

    public int getSelectionSize() {
        return selectedData.size();
    }

    public void showIndex(boolean showIndex) {
        this.showIndex = showIndex;
    }

    public void resort(boolean resort) {
        this.resort = resort;
    }

    // public void setSrcListRenderer(ListCellRenderer renderer) {
    // srcListRenderer = renderer;
    // }
    //
    // public void setSelectedRenderer(ListCellRenderer renderer) {
    // selectedListRenderer = renderer;
    // }

    public ListCellRenderer getSrcListRenderer() {
        if (srcListRenderer == null) {
            srcListRenderer = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                        boolean cellHasFocus) {
                    JLabel comp = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                            cellHasFocus);
                    if (selectedData != null && selectedData.contains(value))
                        comp.setForeground(Color.gray);
                    if (showIndex)
                        comp.setText(index + 1 + ". " + value.toString());
                    return comp;
                }
            };
        }
        return srcListRenderer;
    }

    public ListCellRenderer getSelectedListRenderer() {
        if (selectedListRenderer == null) {
            selectedListRenderer = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                        boolean cellHasFocus) {
                    JLabel comp = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                            cellHasFocus);
                    if (showIndex)
                        comp.setText(index + 1 + ". " + value.toString());
                    return comp;
                }
            };
        }
        return selectedListRenderer;
    }

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        listeners.remove(listener);
    }

    private void fireChangeListener() {
        ChangeEvent e=new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(e);
        }
    }

    private void add(boolean jump) {
        // 添加选中项到右侧
        Object[] selected = srcList.getSelectedValues();
        List sel = Arrays.asList(selected);
        selectedData.removeAll(sel);
        selectedData.addAll(sel);
        reInitSelectedData();

        // 自动选中下一个未被添加过的项
        if (jump) {
            int max = srcList.getMaxSelectionIndex();
            int length = srcList.getModel().getSize();
            int newSelected = (max < (length - 1)) ? (max + 1) : 0;
            final int mark = newSelected;
            if (selectedData.contains(srcList.getModel().getElementAt(newSelected))) {
                // 先向前推一位
                if (newSelected == length - 1)
                    newSelected = 0;
                else
                    newSelected++;

                // 循环一周：直到再次遇见mark结束
                while (newSelected != mark) {
                    if (!selectedData.contains(srcList.getModel().getElementAt(newSelected)))
                        break;// 找到了合适的落脚点，提前退出
                    else {
                        // 再向前推一位
                        if (newSelected == length - 1)
                            newSelected = 0;
                        else
                            newSelected++;
                    }
                }
            }
            srcList.setSelectedIndex(newSelected);
        }

        // 发出通知改变相关按钮状态
        fireChangeListener();
    }

    private void remove() {
        // 从右侧移除选中项
        int[] indices = selectedList.getSelectedIndices();
        Object[] selected = selectedList.getSelectedValues();
        List sel = Arrays.asList(selected);
        selectedData.removeAll(sel);
        int oldMax = selectedList.getMaxSelectionIndex();
        boolean notTail = oldMax < (selectedList.getModel().getSize() - 1);
        reInitSelectedData();
        // 自动选中下一项
        int newSelected = notTail ? (oldMax + 1 - indices.length) : 0;
        selectedList.setSelectedIndex(newSelected);
        // 发出通知改变相关按钮状态
        fireChangeListener();
    }

    private void add_All() {
        // 从左侧添加全部项到右侧
        selectedData.clear();
        selectedData.addAll(srcData);
        reInitSelectedData();
        // 发出通知改变相关按钮状态
        fireChangeListener();
    }

    private void remove_All() {
        // 从右侧移除全部项（清空）
        selectedData.clear();
        reInitSelectedData();
        // 发出通知改变相关按钮状态
        fireChangeListener();
    }

    private void reInitSelectedData() {
        if (resort) {
            Collections.sort(selectedData, indexCompator);
        }
        selectedList.setListData(selectedData.toArray());
        srcList.repaint();
    }

}
