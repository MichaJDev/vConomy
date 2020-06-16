package vConomy.Data.Configuration.Databases;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import vConomy.Main;
import vConomy.Data.Configuration.Config;
import vConomy.Data.Configuration.Writers.Writer;
import vConomy.Data.Models.Bank;

public class BankDB {

	private Main main;
	private Config cfg;
	private Writer writer;

	public BankDB(Main main) {
		this.main = main;
		this.cfg = new Config(main);
		writer = cfg.getWriter();
	}

	public void Setup() {
		CreateDir();
		CreateSettings();
	}

	private void CreateDir() {
		File file = new File(main.getDataFolder(), "\\Banks\\");
		file.mkdirs();
	}

	public File GetDir() {
		return new File(main.getDataFolder(), "\\Banks\\");
	}

	private void CreateSettings() {
		File file = new File(GetDir(), "settings.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				main.getLogger().severe(io.getMessage());
				writer.writeErrorLog(io.getMessage());
			}
			NestSettings();
		}
	}

	private File GetSettingsFile() {
		return new File(GetDir(), "settings.yml");
	}

	private FileConfiguration GetSettings() {
		return YamlConfiguration.loadConfiguration(GetSettingsFile());
	}

	private void NestSettings() {
		FileConfiguration cfg = GetSettings();
		cfg.addDefault("MaxAmount", 250000);
		cfg.options().copyDefaults(true);
		try {
			cfg.save(GetSettingsFile());
		} catch (IOException io) {
			main.getLogger().severe(io.getMessage());
			writer.writeErrorLog(io.getMessage());
		}
	}

	private File GetFile(Player p) {
		File file = new File(GetDir(), p.getUniqueId().toString() + ".yml");
		if (!file.exists()) {
			return null;
		}
		return file;
	}

	public Bank GetBank(Player p) {
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(GetFile(p));
		Bank bank = new Bank(main, p, cfg.getInt("Amount"));
		
		return bank;
	}

	public void CreateBank(Player p) {
		File file = new File(GetDir(), p.getUniqueId().toString() + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				main.getLogger().severe(io.getMessage());
				writer.writeErrorLog(io.getMessage());
			}
			NestBankStats(p);
		}
	}

	private void NestBankStats(Player p) {
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(GetFile(p));
		cfg.addDefault("Amount", 0);
		cfg.options().copyDefaults(true);
		try {
			cfg.save(GetFile(p));
		} catch (IOException io) {
			main.getLogger().severe(io.getMessage());
			writer.writeErrorLog(io.getMessage());
		}
	}
}
