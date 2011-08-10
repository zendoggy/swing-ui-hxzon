package org.hxzon.swing.components.ext;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hxzon.swing.components.easy.HEasyJList;
import org.hxzon.swing.model.HEasyJListModel;
import org.hxzon.swing.model.HEasyJModelValue;
import org.hxzon.swing.util.HChangeListener;
import org.hxzon.swing.util.HChangeListenerSupport;

public class HSelectListPair<V> extends JPanel {
	private static final long serialVersionUID = 1L;
	private HEasyJList<V> unselList;
	private HEasyJList<V> selList;
	private Action addBt, removeBt, addAllBt, removeAllBt;

	private boolean showIndex = true;
//    private List srcData;
//    private List selectedData;
//    private Comparator indexCompator;

//    private ListCellRenderer srcListRenderer;
//    private ListCellRenderer selectedListRenderer;
//    private boolean resort = false;

	private HChangeListenerSupport<HSelectListPair<V>> listenerSupport = new HChangeListenerSupport<HSelectListPair<V>>();

	public HSelectListPair(String srcListTitle, String destListTitle) {
		/* init layout */
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		// center button panel(add, remove, addAll, removeAll)
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
//		addBt.setFocusable(false);
//		removeBt.setFocusable(false);
//		addAllBt.setFocusable(false);
//		removeAllBt.setFocusable(false);
//		addBt.setToolTipText("添加到右侧列表");
//		removeBt.setToolTipText("从右侧列表移除");
//		addAllBt.setToolTipText("添加全部");
//		removeAllBt.setToolTipText("移除全部");
		initAction();
		center.add(new JButton(addBt));
		center.add(Box.createVerticalStrut(10));
		center.add(new JButton(removeBt));
		center.add(Box.createVerticalStrut(30));
		center.add(new JButton(addAllBt));
		center.add(Box.createVerticalStrut(10));
		center.add(new JButton(removeAllBt));
		// left list
		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(150, 350));
		left.setSize(new Dimension(150, 350));
		left.setBorder(BorderFactory.createTitledBorder(left.getBorder(), srcListTitle));
		left.setLayout(new BorderLayout());
		unselList = new HEasyJList<V>();
//        srcList.setCellRenderer(getSrcListRenderer());
		unselList.setVisibleRowCount(10);
		left.add(new JScrollPane(unselList), BorderLayout.CENTER);
		// right list
		JPanel right = new JPanel();
		right.setPreferredSize(new Dimension(150, 350));
		right.setSize(new Dimension(150, 350));
		right.setBorder(BorderFactory.createTitledBorder(right.getBorder(), destListTitle));
		right.setLayout(new BorderLayout());
		selList = new HEasyJList<V>();
//        selectedList.setCellRenderer(getSelectedListRenderer());
		selList.setVisibleRowCount(10);
		right.add(new JScrollPane(selList), BorderLayout.CENTER);
		// add them all
		add(left);
		add(center);
		add(right);

		unselList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					add(false);
//					addBt.setEnabled(false);
				}
			}
		});
		selList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					remove();
			}
		});
		// right list is empty when begin
		removeAllBt.setEnabled(false);
		removeBt.setEnabled(false);
		// if left list is empty
		if (getUnselListModel().getValues().size() < 1) {
			addAllBt.setEnabled(false);
		}
		// if left list no select item
		if (unselList.getSelectionModel().isSelectionEmpty()) {
			addBt.setEnabled(false);
		}
		// if left list has select item
		unselList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
//					addBt.setEnabled(!getSelection().contains(srcList.getSelectedValue()));
					addBt.setEnabled(!getUnselListModel().isEmpty() && unselList.getSelectedValues().length > 0);
				}
			}
		});
		selList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
//					addBt.setEnabled(!getSelection().contains(srcList.getSelectedValue()));
					removeBt.setEnabled(!getSelListModel().isEmpty() && selList.getSelectedValues().length > 0);
				}
			}
		});
		HChangeListener<HSelectListPair<V>> selectBtListener = new HChangeListener<HSelectListPair<V>>() {

			public void change(HSelectListPair<V> e) {
				int selectedCount = getSelListModel().getValues().size();
				int unselCount = getUnselListModel().getValues().size();
//				addBt.setEnabled(unselCount != 0);
				addAllBt.setEnabled(unselCount != 0);
//				removeBt.setEnabled(selectedCount != 0);
				removeAllBt.setEnabled(selectedCount != 0);
			}

		};
		addChangeListener(selectBtListener);
	}

	private void initAction() {
		addBt = new AbstractAction(" >>") {
			public void actionPerformed(ActionEvent e) {
				add(true);
			}
		};
		removeBt = new AbstractAction("<< ") {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		};
		addAllBt = new AbstractAction(">>>") {
			public void actionPerformed(ActionEvent e) {
				add_All();
			}
		};
		removeAllBt = new AbstractAction("<<<") {
			public void actionPerformed(ActionEvent e) {
				remove_All();
			}
		};
	}

	public HEasyJListModel<V> getSelListModel() {
		return selList.getModel();
	}

	public HEasyJListModel<V> getUnselListModel() {
		return unselList.getModel();
	}

	public void setSourceData(List<HEasyJModelValue<V>> srcData) {
		selList.getModel().clearValues();
		unselList.getModel().clearValues();
		unselList.getModel().addValue(srcData);
		fireChangeListener();
	}

//    public void addSelection(int fromIndex, int toIndex) {
//        if (fromIndex < 0) {
//            return;
//        }
//        if (toIndex > srcData.size()) {
//            toIndex = srcData.size();
//        }
//        List sublist = srcData.subList(fromIndex, toIndex);
//        selectedData.removeAll(sublist);
//        selectedData.addAll(sublist);
//        reInitSelectedData();
//        fireChangeListener();
//    }

	public void clearSelection() {
		unselList.getModel().addValue(getSelListModel().getValues());
		selList.getModel().clearValues();
		reInitSelectedData();
		fireChangeListener();
	}

	public boolean isSelectAll() {
		return getUnselListModel().getValues().isEmpty();
	}

	public boolean isSelectEmpty() {
		return getSelListModel().getValues().isEmpty();
	}

	public int getSelectionSize() {
		return getSelListModel().getValues().size();
	}

	public void showIndex(boolean show) {
		this.showIndex = show;
		unselList.showIndex(show);
		selList.showIndex(show);
	}

	public void resort(boolean resort) {
//        this.resort = resort;
	}

	public void addChangeListener(HChangeListener<HSelectListPair<V>> listener) {
		listenerSupport.addChangeListener(listener);
	}

	public void removeChangeListener(HChangeListener<HSelectListPair<V>> listener) {
		listenerSupport.removeChangeListener(listener);
	}

	private void fireChangeListener() {
		listenerSupport.fireChangeListener(this);
	}

	private void add(boolean jump) {
		List<HEasyJModelValue<V>> sel = unselList.getSelectedValueWraps();
		getUnselListModel().removeValues(sel);
		getSelListModel().addValue(sel);
		if (!getUnselListModel().isEmpty()) {
			unselList.setSelectedIndex(0);
		}
		reInitSelectedData();

		// 自动选中下一个未被添加过的项
//        if (jump) {
//            int max = srcList.getMaxSelectionIndex();
//            int length = srcList.getModel().getSize();
//            int newSelected = (max < (length - 1)) ? (max + 1) : 0;
//            final int mark = newSelected;
//            if (selectedData.contains(srcList.getModel().getElementAt(newSelected))) {
//                // 先向前推一位
//                if (newSelected == length - 1)
//                    newSelected = 0;
//                else
//                    newSelected++;
//
//                // 循环一周：直到再次遇见mark结束
//                while (newSelected != mark) {
//                    if (!selectedData.contains(srcList.getModel().getElementAt(newSelected)))
//                        break;// 找到了合适的落脚点，提前退出
//                    else {
//                        // 再向前推一位
//                        if (newSelected == length - 1)
//                            newSelected = 0;
//                        else
//                            newSelected++;
//                    }
//                }
//            }
//            srcList.setSelectedIndex(newSelected);
//        }

		fireChangeListener();
	}

	private void remove() {
//		int[] indices = selectedList.getSelectedIndices();
		List<HEasyJModelValue<V>> sel = selList.getSelectedValueWraps();
		getSelListModel().removeValues(sel);
		getUnselListModel().addValue(sel);
		if (!getSelListModel().isEmpty()) {
			selList.setSelectedIndex(0);
		}
//		int oldMax = selectedList.getMaxSelectionIndex();
//		boolean notTail = oldMax < (selectedList.getModel().getSize() - 1);
//		reInitSelectedData();
//		// 自动选中下一项
//		int newSelected = notTail ? (oldMax + 1 - indices.length) : 0;
//		selectedList.setSelectedIndex(newSelected);
		fireChangeListener();
	}

	private void add_All() {
//        selectedData.clear();
		selList.getModel().addValue(getUnselListModel().getValues());
		getUnselListModel().clearValues();
		reInitSelectedData();
		fireChangeListener();
	}

	private void remove_All() {
//        selectedData.clear();
		getUnselListModel().addValue(getSelListModel().getValues());
		getSelListModel().clearValues();
		reInitSelectedData();
		fireChangeListener();
	}

	private void reInitSelectedData() {
//        if (resort) {
//            Collections.sort(selectedData, indexCompator);
//        }
//        selectedList.setListData(selectedData.toArray());
		unselList.repaint();
		selList.repaint();
	}

}
