package vConomy.Surface.Commands.Wallet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vConomy.Main;
import vConomy.Data.Configuration.Config;
import vConomy.Data.Configuration.Databases.WalletDB;
import vConomy.Data.Configuration.Writers.Writer;
import vConomy.Data.Models.Wallet;
import vConomy.Data.Models.Enums.LogType;

public class WalletCmd implements CommandExecutor {

	private Main main;
	private Config cfg;
	private Writer writer;
	private WalletDB wallet;

	public WalletCmd(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
		wallet = cfg.getWalletDB();
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (c.getName().equalsIgnoreCase("wallet")) {
			if (s instanceof Player) {
				Player p = (Player) s;
				Wallet wall = wallet.GetWallet(p);
				if (s.hasPermission("vConomy.wallet")) {
					if (args.length == 0) {
						s.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a-&e+&a-------------&e+&a-"));
						s.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a-&e+&a" + s.getName()  + "'s wallet"+ "&e+&a-"));
						s.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a-&e+&a Amount: " + wall.getAmount() + " &e+&a"));
						s.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a-&e+&a-------------&e+&a-"));
					}
				}
			} else {
				main.log(LogType.INFO, "Console is not allowed to use this command.");

			}
		}

		return false;
	}

}
