package vConomy.Bussines.Listeners.Auth;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import vConomy.Main;
import vConomy.Data.Configuration.Config;
import vConomy.Data.Configuration.Writers.Writer;
import vConomy.Data.Models.Bank;
import vConomy.Data.Models.Wallet;

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
			} else {
				main.GetBanks().add(cfg.getBankDB().GetBank(e.getPlayer()));
			}
		}
		for (File f : cfg.getWalletDB().GetDir().listFiles()) {
			if (!f.getName().contains(e.getPlayer().getUniqueId().toString())) {
				main.getLogger().info("Player logged in for the first time, creating bankaccount!");
				cfg.getWalletDB().CreateWallet(e.getPlayer());
			} else {
				main.GetWallets().add(cfg.getWalletDB().GetWallet(e.getPlayer()));
			}
		}
	}

	public void OnLogout(PlayerQuitEvent e) {
		Bank bank = cfg.getBankDB().GetBank(e.getPlayer());
		cfg.getBankDB().SaveBank(e.getPlayer(), bank);
		Wallet wallet = cfg.getWalletDB().GetWallet(e.getPlayer());
		cfg.getWalletDB().SaveWallet(e.getPlayer(), wallet);
	}
}
