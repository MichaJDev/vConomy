package vConomy.Data.Models;

import java.awt.RenderingHints.Key;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import vConomy.Main;
import vConomy.Data.Configuration.Config;
import vConomy.Data.Configuration.Databases.MatDB;

@SuppressWarnings("unused")
public class Product {

	private Main main;
	private String name;
	private int cost;
	private Material mat;
	MatDB MatDb;

	public Product(Main main, int cost, Material mat) {
		this.main = main;
		this.cost = cost;
		this.mat = mat;
		this.MatDb = new Config(main).getMatDB();
		setName();
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	private void setName() {
		ConfigurationSection materials = MatDb.getMats().getConfigurationSection("Materials");
		for (String key : materials.getKeys(false)) {
			if (key.contains(mat.name().toString())) {
				name = key.replaceAll(mat.name().toString(), "").replace('"', ' ').replace(':', ' ');
			}
		}
	}
}
