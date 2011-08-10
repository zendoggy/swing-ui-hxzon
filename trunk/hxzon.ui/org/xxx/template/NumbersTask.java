package org.xxx.template;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

class NumbersTask extends SwingWorker<List<Integer>, Integer> {
	JTextArea textArea;
	int n;

	NumbersTask(JTextArea textArea, int n) {
		this.textArea = textArea;
		this.n = n;
	}

	@Override
	public List<Integer> doInBackground() {
		List numbers = new ArrayList();
		try {
			for (int i = 0; i < n && !isCancelled(); i++) {
				if (i % 3 == 0) {
					numbers.add(i);
					publish(i);
					Thread.sleep(1000);//for slowdown;
				}
				setProgress(100 * i / n);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return numbers;
	}

	@Override
	protected void process(List<Integer> chunks) {
		for (int number : chunks) {
			textArea.append(number + ",");
		}
	}
	@Override
	protected void done(){
		textArea.setText("done");
	}

	public static void main(String args[]) throws InterruptedException,
			ExecutionException {
		JTextArea textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(0, 0, 500, 300);
		int n = 100;
		final JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setBounds(0, 300, 500, 100);
		JFrame f = new JFrame("swing worker");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(300,300,500, 400);
		f.setLayout(null);
		f.add(sp);
		f.add(progressBar);
		f.setVisible(true);
		NumbersTask task = new NumbersTask(textArea, n);
		task.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("progress".equals(evt.getPropertyName())) {
					progressBar.setValue((Integer) evt.getNewValue());
//					progressBar.repaint();//don't need;
				}
			}
		});

		task.execute();
		System.out.println(task.get()); // get will wait for done;
		progressBar.setVisible(false);
	}
}
