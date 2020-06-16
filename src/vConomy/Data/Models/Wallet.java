package vConomy.Data.Models;

import java.util.UUID;

import org.bukkit.entity.Player;

import vConomy.Main;
import vConomy.Data.Configuration.Config;
import vConomy.Data.Configuration.Writers.Writer;

@SuppressWarnings("unused")
public class Wallet {

	private Player player;
	private UUID uuid;
	private int amount;
	private int maxAmount;

	private Main main;
	private Config cfg;
	private Writer writer;

	public Wallet(Main main, Player p, int maxAmount) {
		this.main = main;
		this.player = p;
		this.uuid = p.getUniqueId();
		this.maxAmount = maxAmount;
		this.cfg = new Config(main);
		this.writer = cfg.getWriter();
	}

	public Wallet(Main main, Player p, int amount, int maxAmount) {
		this.main = main;
		this.player = p;
		this.uuid = p.getUniqueId();
		this.amount = amount;
		this.maxAmount = maxAmount;
		this.cfg = new Config(main);
		this.writer = cfg.getWriter();
	}

	public Player getPlayer() {
		return player;
	}

	public UUID getUuid() {
		return uuid;
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

}
