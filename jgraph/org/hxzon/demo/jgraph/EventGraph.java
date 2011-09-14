package org.hxzon.demo.jgraph;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

public class EventGraph extends JFrame {

    private static final long serialVersionUID = -2707712944901661771L;

    public EventGraph() {
        super("Hello, World!");
        //com.mxgraph.view.mxGraph
        final mxGraph graph = new mxGraph();
        //cell
        graph.setCellsMovable(false);
        graph.setCellsEditable(false);
        graph.setCellsResizable(false);
        graph.setCellsBendable(false);
        //edge
        graph.setEdgeLabelsMovable(false);
        graph.setConnectableEdges(false);
        graph.setAllowDanglingEdges(false);
        graph.setDisconnectOnMove(false);
        graph.setAllowNegativeCoordinates(false);

        Object root = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            String style = mxConstants.STYLE_FILLCOLOR + "=";
            Object gooseGroup = graph.insertVertex(root, null, "goose event", 0, 0, 20, 20);
            for (int i = 0; i < 100; i++) {
                mxCell v1 = (mxCell) graph.insertVertex(gooseGroup, null, i, i * 20, 20, 5, 80);
                v1.setConnectable(false);
                //com.mxgraph.util.mxConstants
                v1.setStyle(i % 2 == 0 ? style + "green" : style + "red;");
            }
            style = mxConstants.STYLE_SHAPE + "=";
            Object mmsGroup = graph.insertVertex(root, null, "mms event", 0, 120, 20, 20);
            for (int i = 0; i < 100; i++) {
                mxCell v1 = (mxCell) graph.insertVertex(mmsGroup, null, i, i * 20, 20, 5, 80);
                v1.setConnectable(false);
                //com.mxgraph.util.mxConstants
                v1.setStyle(i % 2 == 0 ? style + mxConstants.SHAPE_ACTOR : style + mxConstants.SHAPE_CLOUD);
            }
        } finally {
            graph.getModel().endUpdate();
        }
        //com.mxgraph.swing.mxGraphComponent
        final mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent e) {
                Object cell = graphComponent.getCellAt(e.getX(), e.getY());

                if (cell != null) {
                    System.out.println("cell=" + graph.getLabel(cell));
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EventGraph frame = new EventGraph();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 320);
                frame.setVisible(true);
            }
        });
    }
}