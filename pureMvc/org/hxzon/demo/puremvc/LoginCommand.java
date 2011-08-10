package org.hxzon.demo.puremvc;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

public class LoginCommand extends SimpleCommand {

	@Override
	public void execute(INotification notification) {
		UsersProxy usersProxy = (UsersProxy) this.facade.retrieveProxy(UsersProxy.NAME);
		LoginVO userInfo = (LoginVO) notification.getBody();
		if(usersProxy.checkLogin(userInfo)){
			sendNotification(LoginFacade.LOGIN_SUCCESSFUL, null, null);
		} else {
			sendNotification(LoginFacade.LOGIN_FAIL, null, null);
		}
	}
}
