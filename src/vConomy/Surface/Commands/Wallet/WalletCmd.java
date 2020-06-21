package vConomy.Surface.Commands.Wallet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vConomy.Main;
import vConomy.Bussines.Checks.AmountChecker;
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
				if (args.length == 0) {
					if (s.hasPermission("vConomy.wallet")) {

						if (args.length == 0) {
							s.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a-&e+&a-------------&e+&a-"));
							s.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&a-&e+&a" + s.getName() + "'s wallet" + "&e+&a-"));
							s.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&a-&e+&a Amount: " + wall.getAmount() + " &e+&a"));
							s.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a-&e+&a-------------&e+&a-"));
						}
					}
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("pay")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"Cannot use pay like that: /wallet pay <player> <amount>"));
					}
				} else if (args.length > 1) {
					if (args[0].equalsIgnoreCase("pay")) {
						if (!args[1].isEmpty() || AmountChecker.isInteger(args[1])) {
							Player tp = main.getServer().getPlayer(args[1]);
							Wallet tpWal = wallet.GetWallet(tp);
							Wallet myWal = wallet.GetWallet(p);
							if (AmountChecker.isInteger(args[1])) {
								tpWal.setAmount(tpWal.getAmount() + Integer.parseInt(args[2]));
								myWal.setAmount(myWal.getAmount() - Integer.parseInt(args[2]));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&',
										"Expected integer(number): /wallet pay <player> <amount>"));
							}
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"player slot cannot be empty: /wallet pay <player> <amount>"));
					}
				}
			} else {
				main.log(LogType.INFO, "Console is not allowed to use this command.");

			}
		}

		return false;
	}

}
