package com.shadowlandsmc.gunsandcrime;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class Kilos implements Drugs {

	private Plugin plugin;
	private ItemStack kilos = new ItemStack(Material.BONE_BLOCK,1);
	private ItemStack megaPipe = new ItemStack(Material.STICK,1);
	private Crack crack = new Crack(Main.plugin);
	
	public Kilos(Plugin plugin) {
	this.plugin	= plugin;	
	generateDrugs();
	}
	
	
	private void generateDrugs() {
		
		String DrugItem = plugin.getConfig().getString("Kilos.DrugItem");
		String DrugDisplayName = plugin.getConfig().getString("Kilos.DrugDisplayName").replaceAll("&", "§");
		List<String> DrugLore = (List<String>) plugin.getConfig().getStringList("Kilos.DrugLore");
		
		String ParaphernaliaItem = plugin.getConfig().getString("Kilos.ParaphernaliaItem");
		String ParaphernaliaDisplayName = plugin.getConfig().getString("Kilos.ParaphernaliaDisplayName").replaceAll("&", "§");
		List<String> ParaphernaliaLore = (List<String>) plugin.getConfig().getStringList("Kilos.ParaphernaliaLore");
		
		//Generate Crack
		this.kilos.setType(Material.getMaterial(DrugItem));
		ItemMeta meta = this.kilos.getItemMeta();
		meta.setDisplayName(DrugDisplayName);
		meta.setLore(DrugLore);
		meta.setCustomModelData(1);
		this.kilos.setItemMeta(meta);
		
		//Generate Crack Pipe
		this.megaPipe.setType(Material.getMaterial(ParaphernaliaItem));
		meta = this.megaPipe.getItemMeta();
		meta.setDisplayName(ParaphernaliaDisplayName);
		meta.setLore(ParaphernaliaLore);
		meta.setCustomModelData(1);
		this.megaPipe.setItemMeta(meta);
		
	}
	
	
	
	@Override
	public ItemStack getDrugs() {
		return this.kilos;
	}

	@Override
	public ItemStack getParaphernalia() {
		// TODO Auto-generated method stub
		return this.megaPipe;
	}
	
	
	@Override
	public void consume(Player player) {

		//Give the player the effects of the crack
		List<String> list = plugin.getConfig().getStringList("Kilos.DrugEffects");
		
		for(int i = 0; i < list.size(); i++) {
			
			//See how the config is setup:     - 'FAST_DIGGING,6000,0'
			String[] effects = list.get(i).split(",");
			
			player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effects[0]),Integer.parseInt(effects[1]),Integer.parseInt(effects[2])));
		}
		

		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
		    @Override
		    public void run() {

		    	//player will OD after one minute
		    	player.teleport(new Location(
		    			plugin.getServer().getWorld(plugin.getConfig().getString("GhettoCordsWorld")),
		    			plugin.getConfig().getDouble("GhettoCordsUpperX") -2, 
		    			plugin.getConfig().getDouble("GhettoCordsUpperY") -2, 
		    			plugin.getConfig().getDouble("GhettoCordsUpperZ") -2));
		    	player.damage(Integer.MAX_VALUE);
		    	player.sendMessage(ChatColor.BLACK + "You ODed on a kilo of crack");
		    		
		    	
		    }
		}, 1200L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
		
		
	}
	
	@Override
	public void addiction(Player player) {
		
		//don't do anything. You cannot get addicted to kilos because you die
		
	}
	
	@Override
	public void rehabilitate(Player player) {
		

		//don't do anything. You cannot get addicted to kilos because you die
		
	}
	
	@Override
	public void withdrawl(Player player) {
		

		//don't do anything. You cannot get addicted to kilos because you die
		
	}


	@Override
	public int maxTrades() {
		int maxTrades = plugin.getConfig().getInt("Kilos.MaxTrades");
		return maxTrades;
	}


	@Override
	public int costOfDrugs() {
		int costOfDrugs = plugin.getConfig().getInt("Kilos.CostOfDrugs");
		return costOfDrugs;
	}


	@Override
	public int costIncrease() {
		int CostIncrease = plugin.getConfig().getInt("Kilos.CostIncrease");
		return CostIncrease;
	}


	@Override
	public int costOfParaphernalia() {
		int CostOfParaphernalia = plugin.getConfig().getInt("Kilos.CostOfParaphernalia");
		return CostOfParaphernalia;
	}


	
}
