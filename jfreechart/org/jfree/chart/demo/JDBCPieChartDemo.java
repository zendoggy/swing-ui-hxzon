package org.jfree.chart.demo;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class JDBCPieChartDemo extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public JDBCPieChartDemo(String s) {
        super(s);
        PieDataset piedataset = readData();
        JFreeChart jfreechart = ChartFactory.createPieChart("JDBC Pie Chart Demo", piedataset, true, true, false);
        jfreechart.setBackgroundPaint(Color.yellow);
        PiePlot pieplot = (PiePlot) jfreechart.getPlot();
        pieplot.setNoDataMessage("No data available");
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        setContentPane(chartpanel);
    }

    private PieDataset readData() {
        JDBCPieDataset jdbcpiedataset = null;
        String s = "jdbc:postgresql://nomad/jfreechartdb";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException classnotfoundexception) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(classnotfoundexception.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(s, "jfreechart", "password");
            jdbcpiedataset = new JDBCPieDataset(connection);
            String s1 = "SELECT * FROM PIEDATA1;";
            jdbcpiedataset.executeQuery(s1);
            connection.close();
        } catch (SQLException sqlexception) {
            System.err.print("SQLException: ");
            System.err.println(sqlexception.getMessage());
        } catch (Exception exception) {
            System.err.print("Exception: ");
            System.err.println(exception.getMessage());
        }
        return jdbcpiedataset;
    }

    public static void main(String args[]) {
        JDBCPieChartDemo jdbcpiechartdemo = new JDBCPieChartDemo("JDBC Pie Chart Demo");
        jdbcpiechartdemo.pack();
        RefineryUtilities.centerFrameOnScreen(jdbcpiechartdemo);
        jdbcpiechartdemo.setVisible(true);
    }
}
