package org.hxzon.demo.puremvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;
import org.puremvc.java.patterns.observer.Notification;

public class LoginScreenMediator extends Mediator implements ActionListener{
	public static final String NAME = "LoginScreenMediator";
	private LoginScreen dialog;

	public LoginScreenMediator() {
		super(NAME, null);
	}

	public void show(){
        dialog = new LoginScreen(new javax.swing.JFrame(), true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        dialog.btnLogin.addActionListener(this);
        dialog.setVisible(true);
	}
	//list event interests
	public String[] listNotificationInterests() {
		return new String[] { LoginFacade.LOGIN_SUCCESSFUL, LoginFacade.LOGIN_FAIL };
	}
	//handle event
	public void handleNotification(INotification note) {
		String noteName = note.getName();
		if (LoginFacade.LOGIN_SUCCESSFUL.equals(noteName)) {
			JOptionPane.showMessageDialog(null, "Login ok!");
			System.exit(0);
		} else if (LoginFacade.LOGIN_FAIL.equals(noteName)) {
			JOptionPane.showMessageDialog(null, "Login failed, try again");
		}
	}
	//swing event->pure event(notification)
	@Override
	public void actionPerformed(ActionEvent e) {
			String name = dialog.userName.getText();
			String pass = new String(dialog.password.getPassword());
			LoginVO userInfo = new LoginVO( name, pass );
			this.facade.notifyObservers(new Notification(LoginFacade.SUBMIT_LOGIN, userInfo, null));
	}
}