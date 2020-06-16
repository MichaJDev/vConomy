package vConomy.Bussines.Listeners.Auth;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import vConomy.Main;
import vConomy.Data.Configuration.Config;
import vConomy.Data.Configuration.Writers.Writer;

@SuppressWarnings("unused")
public class LoginListener implements Listener {

	private Main main;
	private Config cfg;
	private Writer writer;
	
	public LoginListener(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
	}

	@EventHandler
	public void OnLogin(PlayerJoinEvent e) {
		for (File f : cfg.getBankDB().GetDir().listFiles()) {
			if (!f.getName().contains(e.getPlayer().getUniqueId().toString())) {
				main.getLogger().info("Player logged in for the first time, creating bankaccount!");
				cfg.getBankDB().CreateBank(e.getPlayer());
				
			}
		}
	}
}
