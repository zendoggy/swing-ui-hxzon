package org.hxzon.demo.puremvc;

import org.puremvc.java.patterns.facade.Facade;
import org.puremvc.java.patterns.observer.Notification;

public class LoginFacade extends Facade {
	public static final String STARTUP = "startup";
	public static final String SUBMIT_LOGIN = "submitLogin";
	public static final String LOGIN_SUCCESSFUL = "loginSuccessful";
	public static final String LOGIN_FAIL = "loginFail";

	public static LoginFacade getInstance() {
		if (instance == null)
			instance = new LoginFacade();
		return (LoginFacade) instance;
	}

	protected void initializeController() {
		super.initializeController();
//		registerCommand(STARTUP, StartupCommand.class);
//		registerCommand(SUBMIT_LOGIN, LoginCommand.class);
		//change by hxzon
		registerCommand(STARTUP, new StartupCommand());
		registerCommand(SUBMIT_LOGIN, new LoginCommand());
	}

	@Override
	protected void initializeModel() {
		super.initializeModel();
		registerProxy(new UsersProxy());
	}

	@Override
	protected void initializeView() {
		super.initializeView();
		registerMediator(new LoginScreenMediator());
	}

	public static void main(String[] args) {
		getInstance().notifyObservers(new Notification(STARTUP, null, null));
	}
}
