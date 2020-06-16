package vConomy.Data.Models;

import java.util.UUID;

import org.bukkit.entity.Player;

import vConomy.Main;
import vConomy.Data.Configuration.Config;
import vConomy.Data.Configuration.Writers.Writer;

@SuppressWarnings("unused")
public class Bank {

	private Player player;
	private UUID bankUUID;
	private int amount;
	private int maxAmount;

	private Main main;
	private Config cfg;
	private Writer log;

	public Bank(Main main, Player p, int maxAmount) {
		this.main = main;
		this.player = p;
		this.bankUUID = p.getUniqueId();
		this.maxAmount = maxAmount;
		this.cfg = new Config(main);
		this.log = cfg.getWriter();
	}

	public Bank(Main main, Player p, int amount, int maxAmount) {
		this.main = main;
		this.player = p;
		this.bankUUID = p.getUniqueId();
		this.amount = amount;
		this.maxAmount = maxAmount;
	}

	public Player getPlayer() {
		return player;
	}

	public UUID getBankUUID() {
		return bankUUID;
	}

	public int getAmount() {
		return amount;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	public void Withdraw(int amount) {
		this.amount = (this.amount - amount);
	}

	public void Deposit(int amount) {
		this.amount = (this.amount + amount);
	}

}
