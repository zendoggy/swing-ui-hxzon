package org.hxzon.demo.jgraph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.CellRendererPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.math.RandomUtils;
import org.jdesktop.swingx.JXPanel;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxImageCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

public class MyCustomCanvas extends JFrame {

    private static final long serialVersionUID = -844106998814982739L;

    public MyCustomCanvas() {
        super("Custom Canvas");

        mxGraph graph = new mxGraph() {
            public void drawState(mxICanvas canvas, mxCellState state, boolean drawLabel) {
                MyCanvas myCanvas = isMyCanvas(canvas);
                // Indirection for wrapped swing canvas inside image canvas (used for creating
                // the preview image when cells are dragged)
                if (getModel().isVertex(state.getCell()) && myCanvas != null) {
                    myCanvas.drawVertex(state);
                }
                // Redirection of drawing vertices in SwingCanvas
                else if (getModel().isVertex(state.getCell()) && myCanvas != null) {
                    myCanvas.drawVertex(state);
                } else {
                    super.drawState(canvas, state, drawLabel);
                }
            }
        };
        //cell
        graph.setCellsResizable(false);
        //edge
        graph.setEdgeLabelsMovable(false);
        graph.setConnectableEdges(false);
        graph.setAllowDanglingEdges(false);
        graph.setDisconnectOnMove(false);

        Object root = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            Object balls = graph.insertVertex(root, null, "balls", 0, 0, 20, 20);
            mxCell lastCell = null;
            int x = 0;
            for (int i = 0; i < 100; i++) {
                Ball ball = randomBall();
                x += ball.r + 20;
                mxCell newCell = (mxCell) graph.insertVertex(balls, null, ball, x, 20, ball.r, ball.r);
                if (lastCell != null) {
                    graph.insertEdge(balls, null, null, lastCell, newCell);
                }
                lastCell = newCell;
            }
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph) {
            private static final long serialVersionUID = 4683716829748931448L;

            public mxInteractiveCanvas createCanvas() {
                return new MyCanvas(this);
            }
        };

        getContentPane().add(graphComponent);

    }

    public static MyCanvas isMyCanvas(mxICanvas canvas) {
        if (canvas instanceof MyCanvas) {
            return (MyCanvas) canvas;
        } else if (canvas instanceof mxImageCanvas && ((mxImageCanvas) canvas).getGraphicsCanvas() instanceof MyCanvas) {
            return (MyCanvas) ((mxImageCanvas) canvas).getGraphicsCanvas();
        }
        return null;
    }

    public class MyCanvas extends mxInteractiveCanvas {
        protected CellRendererPane rendererPane = new CellRendererPane();

        protected mxGraphComponent graphComponent;

        public MyCanvas(mxGraphComponent graphComponent) {
            this.graphComponent = graphComponent;
        }

        public void drawVertex(mxCellState state) {
            mxCell cell = (mxCell) state.getCell();
            Object value = cell.getValue();
            if (value instanceof Ball) {
                Ball ball = (Ball) value;
                rendererPane.paintComponent(g, ball, graphComponent, (int) state.getX() + translate.x, (int) state.getY() + translate.y, (int) state.getWidth(), (int) state.getHeight(), true);
            }

        }

    }

    public Ball randomBall() {
        int r = RandomUtils.nextInt(25) + 3;
        int c1 = RandomUtils.nextInt(255);
        int c2 = RandomUtils.nextInt(255);
        int c3 = RandomUtils.nextInt(255);
        Color color = new Color(c1, c2, c3);
        return new Ball(r, color);
    }

    public static class Ball extends JXPanel implements Serializable {
        private static final long serialVersionUID = 1L;
        public int r;
        public Color color;

        public Ball(int r, Color color) {
            this.r = r;
            this.color = color;
            super.setAlpha(0.5f);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillOval(0, 0, r, r);
        }

        public Dimension getPreferredSize() {
            return new Dimension(r, r);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MyCustomCanvas frame = new MyCustomCanvas();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 320);
                frame.setVisible(true);
            }
        });
    }

}
