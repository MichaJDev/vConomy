package vConomy.Data.Configuration.Writers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import vConomy.Main;

public class Writer {

	private Main main;

	public Writer(Main main) {
		this.main = main;
	}

	public void Setup() {
		CreateDir();
		CreateLog();
	}

	private void CreateDir() {
		File file = new File(main.getDataFolder(), "\\Logs\\");
		file.mkdirs();
	}

	private File GetDir() {
		return new File(main.getDataFolder(), "\\Logs\\");
	}

	private void CreateLog() {
		File file = new File(GetDir(), "errorlog.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				main.getLogger().severe(io.getMessage());
			}
		}
	}

	private File GetLogFile() {
		return new File(GetDir(), "errorlog.yml");
	}

	public void writeErrorLog(String msg) {
		FileConfiguration log = YamlConfiguration.loadConfiguration(GetLogFile());
		log.addDefault("[" + LocalDate.now().toString() + "]-[" + LocalTime.now() + "]: ", msg);
		log.options().copyDefaults(true);
		try {
			log.save(GetLogFile());
		} catch (IOException io) {
			main.getLogger().severe(io.getMessage());
		}
	}

	public void createBankFile(Player p) {
		File file = new File(GetDir(), p.getUniqueId() + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				main.getLogger().severe(io.getMessage());
				writeErrorLog(io.getMessage());
			}
		}
	}

	private File GetBankFile(UUID uuid) {
		return new File(GetDir(), uuid + ".yml");
	}

	public void writeBankLog(UUID uuid, String msg) {
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(GetBankFile(uuid));
		cfg.addDefault("[" + LocalDate.now() + "]-[" + LocalTime.now() + "]", msg);
	}

}
