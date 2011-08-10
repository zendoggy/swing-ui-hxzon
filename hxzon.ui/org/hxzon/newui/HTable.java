package org.hxzon.newui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import net.miginfocom.swing.MigLayout;

import org.hxzon.util.OgnlUtils;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate.RowGroupHighlightPredicate;
import org.jdesktop.swingx.table.DatePickerCellEditor;


public class HTable extends JPanel {
    private HOgnlTableModel tableModel;
    private JXTable table;
    private JScrollPane scrollPane;
    private List<SelectionListener> listeners = new ArrayList<SelectionListener>();
    private FireSelectionHandler fireSelectionHandler = new FireSelectionHandler();

    public HTable() {
        this(null, null, null);
    }

    public HTable(List list, String[] properties, String[] columnNames) {
        super(new MigLayout());
        add(getScrollPane());
        preSettingTable();
        setData(list, properties, columnNames);
    }

    protected void preSettingTable() {
        table.setHorizontalScrollEnabled(true);
        table.setColumnControlVisible(true);
        table.setColumnSelectionAllowed(true);
        table.setDefaultEditor(Date.class, new DatePickerCellEditor());
        table.setDefaultRenderer(RowIndex.class, new RowIndexRenderer());
        table.addHighlighter(new ColorHighlighter(new RowGroupHighlightPredicate(1), Color.WHITE, Color.GRAY));
        table.setRolloverEnabled(true);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2 && table.getSelectedColumn() == 0) {
                    int row = table.getSelectedRow();
                    table.setColumnSelectionAllowed(false);
                    table.setRowSelectionInterval(row, row);
                } else {
                    table.setColumnSelectionAllowed(true);
                }
            }
        });
        table.getSelectionModel().addListSelectionListener(fireSelectionHandler);
        table.getColumnModel().addColumnModelListener(fireSelectionHandler);
    }

    public void setData(List srcData, String[] properties, String[] columnNames) {
        tableModel.setColumnNames(columnNames);
        tableModel.setSrcData(srcData);
        tableModel.setProperties(properties);
    }

    public void setColumn(String... properties) {
        tableModel.setProperties(properties);
    }

    public void setRow(List srcData) {
        tableModel.setSrcData(srcData);
    }

    public void setColumnNames(String... columnNames) {
        tableModel.setColumnNames(columnNames);
    }

    public void setEditable(int... editable) {
        tableModel.setEditable(editable);
    }

    public HOgnlTableModel getTableModel() {
        if (tableModel == null) {
            tableModel = new HOgnlTableModel();
        }
        return tableModel;
    }

    public JXTable getTable() {
        if (table == null) {
            table = new JXTable(getTableModel());
        }
        return table;
    }

    public JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane(getTable());
        }
        return scrollPane;
    }

    public void addSelectionListener(SelectionListener l) {
        listeners.add(l);
    }

    public void removeSelectionListener(SelectionListener l) {
        listeners.remove(l);
    }

    public class FireSelectionHandler implements ListSelectionListener, TableColumnModelListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            for (SelectionListener l : listeners) {
                l.rowSelectionChanged(e);
            }
        }

        @Override
        public void columnAdded(TableColumnModelEvent e) {
        }

        @Override
        public void columnMarginChanged(ChangeEvent e) {
        }

        @Override
        public void columnMoved(TableColumnModelEvent e) {
        }

        @Override
        public void columnRemoved(TableColumnModelEvent e) {
        }

        @Override
        public void columnSelectionChanged(ListSelectionEvent e) {
            for (SelectionListener l : listeners) {
                l.colSelectionChanged(e);
            }
        }

    }

    public class SelectionListener implements EventListener {
        public void rowSelectionChanged(ListSelectionEvent e) {

        }

        public void colSelectionChanged(ListSelectionEvent e) {

        }
    }

    public class RowIndex {

    }

    public class RowIndexRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super
                    .getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setText(String.valueOf(row));
            return label;
        }

    }

    public class HOgnlTableModel extends AbstractTableModel {
        List srcData = new ArrayList();
        List<String> properties = new ArrayList<String>();
        List<String> columnNames = new ArrayList<String>();
        List<Integer> editable = new ArrayList<Integer>();

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0)
                return RowIndex.class;
            return OgnlUtils.getClass(srcData.get(0), properties.get(columnIndex - 1));
        }

        public String getColumnName(int column) {
            if (column == 0) {
                return "  ";
            }
            if (columnNames.isEmpty()) {
                return properties.get(column - 1);
            } else {
                return columnNames.get(column - 1);
            }
        }

        @Override
        public int getColumnCount() {
            return columnNames.isEmpty() ? properties.size() + 1 : columnNames.size() + 1;
        }

        @Override
        public int getRowCount() {
            return srcData.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0)
                return 0;// not return rowIndex,will make sort
            return OgnlUtils.getValue(srcData.get(rowIndex), properties.get(columnIndex - 1));
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            OgnlUtils.setValue(srcData.get(rowIndex), properties.get(columnIndex - 1), aValue);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            if (column == 0)
                return false;
            return editable.indexOf(column - 1) != -1;
        }

        public void setSrcData(List srcData) {
            this.srcData.clear();
            if (srcData != null) {
                this.srcData.addAll(srcData);
            }
            this.fireTableChanged(new TableModelEvent(this));
        }

        public List getSrcData() {
            return srcData;
        }

        public void setProperties(String... properties) {
            this.properties.clear();
            if (properties != null) {
                for (String column : properties) {
                    this.properties.add(column);
                }
            }
            this.fireTableStructureChanged();// must
        }

        public List getProperties() {
            return properties;
        }

        public void setColumnNames(String... columnNames) {
            this.columnNames.clear();
            if (columnNames != null) {
                for (String name : columnNames) {
                    this.columnNames.add(name);
                }
            }
            this.fireTableStructureChanged();// must
        }

        public List<String> getColumnNames() {
            return columnNames;
        }

        public void setEditable(int... editable) {
            this.editable.clear();
            if (editable != null) {
                for (int i : editable) {
                    this.editable.add(i);
                }
            }
            this.fireTableStructureChanged();// must
        }

        public List<Integer> getEditable() {
            return editable;
        }

    }

}
