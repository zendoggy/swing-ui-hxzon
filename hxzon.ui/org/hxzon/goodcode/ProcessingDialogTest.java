package org.hxzon.goodcode;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;



public class ProcessingDialogTest {
	public static void main(String args[]) {
		
		final ProcessingDialog processingDialog = new ProcessingDialog(new JFrame(),true);
		
		SwingWorker worker = new SwingWorker<Void,Integer>() {
			int n=20;
			public Void doInBackground() {
				List<Integer> list=new ArrayList<Integer>();
				try {
					for (int i = 0; i < n; i++){
						list.add(i);
						publish(i);
						setProgress(100*i/n);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			protected void process(List<Integer> chunks) {
				for (int number : chunks) {
					processingDialog.setValue(100*number/n);
				}
			}

			protected void done() {
				processingDialog.setVisible(false);
				JOptionPane.showMessageDialog(processingDialog, "文件下载完成");
				System.exit(0);
			}
		};
		worker.execute();
		processingDialog.setMessage("正在下载文件");
//		processingDialog.bar.setIndeterminate(true);
		processingDialog.setVisible(true);//must after worker.execute;
		//other code when processingDialog.setVisible(false);
	}
}
