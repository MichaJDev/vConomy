package vConomy;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import vConomy.Bussines.Listeners.Listeners;
import vConomy.Bussines.Listeners.Auth.LoginListener;
import vConomy.Data.Configuration.Config;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {

	private Config cfg;

	private Listeners list;

	public void onEnable() {
		cfg = new Config(this);
		list = new Listeners(this);
		if (!cfg.getDir().exists()) {
			getLogger().warning("vConomy Configuration and Files not setup");
			Setup();
		}
		GetCommands();
		GetListeners();

	}

	private void Setup() {
		cfg.Setup();
		cfg.getMatDB().Setup();
		cfg.getWriter().Setup();
	}

	public void GetCommands() {

	}

	public void GetListeners() {
		getServer().getPluginManager().registerEvents(list.getLogin(), this);
	}
}
