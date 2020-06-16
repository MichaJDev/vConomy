package vConomy.Bussines.Listeners;

import vConomy.Main;
import vConomy.Bussines.Listeners.Auth.LoginListener;

public class Listeners {

	Main main;
	private LoginListener login;
	public Listeners(Main main) {
		this.main = main;
		this.login = new LoginListener(main);
	}
	
	public LoginListener getLogin() {
		return login;
	}

}
