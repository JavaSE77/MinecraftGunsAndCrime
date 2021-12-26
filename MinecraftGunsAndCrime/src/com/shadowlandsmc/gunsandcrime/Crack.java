package com.shadowlandsmc.gunsandcrime;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class Crack implements Drugs {

	private Plugin plugin;
	private ItemStack crack = new ItemStack(Material.BONE_MEAL,1);
	private ItemStack crackPipe = new ItemStack(Material.STICK,1);
	
	public Crack(Plugin plugin) {
	this.plugin	= plugin;	
	generateDrugs();
	}
	
	
	private void generateDrugs() {
		
		String DrugItem = plugin.getConfig().getString("Crack.DrugItem");
		String DrugDisplayName = plugin.getConfig().getString("Crack.DrugDisplayName").replaceAll("&", "§");
		List<String> DrugLore = (List<String>) plugin.getConfig().getStringList("Crack.DrugLore");
		
		String ParaphernaliaItem = plugin.getConfig().getString("Crack.ParaphernaliaItem");
		String ParaphernaliaDisplayName = plugin.getConfig().getString("Crack.ParaphernaliaDisplayName").replaceAll("&", "§");
		List<String> ParaphernaliaLore = (List<String>) plugin.getConfig().getStringList("Crack.ParaphernaliaLore");
		
		//Generate Crack
		this.crack.setType(Material.getMaterial(DrugItem));
		ItemMeta meta = this.crack.getItemMeta();
		meta.setDisplayName(DrugDisplayName);
		meta.setLore(DrugLore);
		this.crack.setItemMeta(meta);
		
		//Generate Crack Pipe
		this.crackPipe.setType(Material.getMaterial(ParaphernaliaItem));
		meta = this.crackPipe.getItemMeta();
		meta.setDisplayName(ParaphernaliaDisplayName);
		meta.setLore(ParaphernaliaLore);
		this.crackPipe.setItemMeta(meta);
		
	}
	
	
	
	@Override
	public ItemStack getDrugs() {
		return this.crack;
	}

	@Override
	public ItemStack getParaphernalia() {
		// TODO Auto-generated method stub
		return this.crackPipe;
	}
	
	
	@Override
	public void consume(Player player) {
		
		boolean inWithdrawl = false;
		//Give the player the withdrawl effects of the crack
		List<String> withdrawEffects = plugin.getConfig().getStringList("Crack.WithdrawlEffects");
		//If the player is in withdraw, remove the effects.
		for(int i = 0; i < withdrawEffects.size(); i++) {
			
			//See how the config is setup:     - 'FAST_DIGGING,6000,0'
			String[] effects = withdrawEffects.get(i).split(",");
			
			if(player.hasPotionEffect(PotionEffectType.getByName(effects[0]))) {
				player.removePotionEffect(PotionEffectType.getByName(effects[0]));
				inWithdrawl = true;
			}
			
		}
		if (inWithdrawl) {
			return;
		}
		
		//Give the player the effects of the crack
		List<String> list = plugin.getConfig().getStringList("Crack.DrugEffects");
		
		for(int i = 0; i < list.size(); i++) {
			
			//See how the config is setup:     - 'FAST_DIGGING,6000,0'
			String[] effects = list.get(i).split(",");
			
			player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effects[0]),Integer.parseInt(effects[1]),Integer.parseInt(effects[2])));
		}
		
	    Random random = new Random();
	    int random_int = random.nextInt(100);
		double prob = plugin.getConfig().getDouble("Crack.AddictionChance") * 100;

		//then you become addicted to the cracks
		if(prob >= random_int) {
			addiction(player);
		}
		
	}
	
	@Override
	public void addiction(Player player) {
		
		//Get the list of all crack addicts
		List<String> list = plugin.getConfig().getStringList("Crack.Addicts");
		
		//check to see if our player is an addict
		if(!list.contains(player.getName())) {
			player.sendMessage(ChatColor.RED + "You are addicted to crack! You must smoke crack every day, or suffer withdrawl.");
			list.add(player.getName());
			plugin.getConfig().set("Crack.Addicts", list);
			plugin.saveConfig();
		}
		
	}
	
	@Override
	public void rehabilitate(Player player) {
		
		//Get the list of all crack addicts
		List<String> list = plugin.getConfig().getStringList("Crack.Addicts");
		
		//check to see if our player is an addict
		if(list.contains(player.getName())) {
			player.sendMessage(ChatColor.GOLD + "You are no longer addicted to crack!");
			list.remove(player.getName());
			plugin.getConfig().set("Crack.Addicts", list);
			plugin.saveConfig();
		}
		
	}
	
	@Override
	public void withdrawl(Player player) {
		
		//Give the player the withdrawl effects of the crack
		List<String> list = plugin.getConfig().getStringList("Crack.WithdrawlEffects");
		
		for(int i = 0; i < list.size(); i++) {
			
			//See how the config is setup:     - 'FAST_DIGGING,6000,0'
			String[] effects = list.get(i).split(",");
			
			player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effects[0]),Integer.parseInt(effects[1]),Integer.parseInt(effects[2])));
		}
		
		player.sendMessage("You are having withdrawl symptoms. You must smoke crack!");
		
	}


	@Override
	public int maxTrades() {
		int maxTrades = plugin.getConfig().getInt("Crack.MaxTrades");
		return maxTrades;
	}


	@Override
	public int costOfDrugs() {
		int costOfDrugs = plugin.getConfig().getInt("Crack.CostOfDrugs");
		return costOfDrugs;
	}


	@Override
	public int costOfDrugsForAddicts() {
		int CostOfDrugsForAddicts = plugin.getConfig().getInt("Crack.CostOfDrugsForAddicts");
		return CostOfDrugsForAddicts;
	}


	@Override
	public int costOfParaphernalia() {
		int CostOfParaphernalia = plugin.getConfig().getInt("Crack.CostOfParaphernalia");
		return CostOfParaphernalia;
	}

}
