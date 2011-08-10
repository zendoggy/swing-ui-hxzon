package org.hxzon.demo.puremvc;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

public class StartupCommand extends SimpleCommand implements Runnable{

	@Override
	public void run() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		    LoginScreenMediator mediator = (LoginScreenMediator) this.facade.retrieveMediator(LoginScreenMediator.NAME);
		    mediator.show();
		} catch (Exception e) {}
	}

	@Override
	public void execute(INotification notification) {
		SwingUtilities.invokeLater(this);
	}
}
