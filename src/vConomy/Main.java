package vConomy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import vConomy.Bussines.Listeners.Listeners;
import vConomy.Data.Configuration.Config;
import vConomy.Data.Models.Bank;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {

	private Config cfg;
	private List<Bank> banks;
	private Listeners list;

	public void onEnable() {
		banks = new ArrayList<Bank>();
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

	public List<Bank> GetBanks() {
		return banks;
	}
}
