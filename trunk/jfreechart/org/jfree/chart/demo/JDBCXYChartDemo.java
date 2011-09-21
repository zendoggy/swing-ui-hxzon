package org.jfree.chart.demo;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.jdbc.JDBCXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class JDBCXYChartDemo extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public JDBCXYChartDemo(String s) {
        super(s);
        XYDataset xydataset = readData();
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("JDBC XY Chart Demo", "Date", "Value", xydataset, true, true, false);
        jfreechart.setBackgroundPaint(Color.yellow);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        setContentPane(chartpanel);
    }

    private XYDataset readData() {
        JDBCXYDataset jdbcxydataset = null;
        String s = "jdbc:postgresql://nomad/jfreechartdb";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException classnotfoundexception) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(classnotfoundexception.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(s, "jfreechart", "password");
            jdbcxydataset = new JDBCXYDataset(connection);
            String s1 = "SELECT * FROM XYDATA1;";
            jdbcxydataset.executeQuery(s1);
            connection.close();
        } catch (SQLException sqlexception) {
            System.err.print("SQLException: ");
            System.err.println(sqlexception.getMessage());
        } catch (Exception exception) {
            System.err.print("Exception: ");
            System.err.println(exception.getMessage());
        }
        return jdbcxydataset;
    }

    public static void main(String args[]) {
        JDBCXYChartDemo jdbcxychartdemo = new JDBCXYChartDemo("JDBC XY Chart Demo");
        jdbcxychartdemo.pack();
        RefineryUtilities.centerFrameOnScreen(jdbcxychartdemo);
        jdbcxychartdemo.setVisible(true);
    }
}
