package org.hxzon.demo.jgraph;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class EventGraph extends JFrame {

	private static final long serialVersionUID = -2707712944901661771L;

	public EventGraph() {
		super("Hello, World!");

		final mxGraph graph = new mxGraph(){
			//for change the label
			public String convertValueToString(Object cell){
				return "event\n"+super.convertValueToString(cell);
			}
		};
		//cell
		graph.setCellsMovable(false);
		graph.setCellsEditable(false);
		graph.setCellsResizable(false);
		//edge
		graph.setEdgeLabelsMovable(false);
		graph.setConnectableEdges(false);
		graph.setAllowDanglingEdges(false);
		graph.setDisconnectOnMove(false);
		graph.setAllowNegativeCoordinates(false);
		graph.setCellsBendable(false);

		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try {
			for (int i = 0; i < 100; i++) {
				mxCell v1 = (mxCell) graph.insertVertex(parent, null, i, i * 20, 20, 5, 80);
				v1.setConnectable(false);
				//com.mxgraph.util.mxConstants
				v1.setStyle(i%2==0?"fillColor=green;":"fillColor=red;");
			}
		} finally {
			graph.getModel().endUpdate();
		}

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
		EventGraph frame = new EventGraph();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}
}