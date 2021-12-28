package com.shadowlandsmc.gunsandcrime;

import java.util.List;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftVillager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class commandProcessor implements CommandExecutor {

	private Plugin plugin = Main.plugin;
	
	Crack crack = new Crack(plugin);
	HolyTexts bible = new HolyTexts(plugin);
	VillagerTrading tradingAPI = new VillagerTrading(plugin, crack, bible);
	
	  public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		    if (cmdLabel.equalsIgnoreCase("drugs")) {
		      if (args.length >= 1 && args[0].equalsIgnoreCase("give")) {
		        if (sender.hasPermission("gensandcrime.drugs.give")) {
		        	if (sender instanceof Player) {
		        		Player player = (Player) sender;
		        		player.getInventory().addItem(crack.getDrugs());
		        		player.getInventory().addItem(crack.getParaphernalia());
		        		player.getInventory().addItem(bible.getHolyText());
		        	} else {
		        		sender.sendMessage("You must be a player to get drugs");
		        	}
		        	
		        }
		        else { 
		        	sender.sendMessage(ChatColor.RED + "You must be OP to give drugs via commands");
		        }
		      } else if (args.length >= 1 && args[0].equalsIgnoreCase("dealer")) {
		    	  //spawn a drug dealer trader
		    	  if(sender instanceof Player) {
		    		 Player player = (Player) sender; 
		    		 WanderingTrader drugDealer = (WanderingTrader) player.getWorld().spawnEntity(player.getLocation(), EntityType.WANDERING_TRADER);
		    		 //traders spawned like this will not despawn
		    		 drugDealer.setDespawnDelay(-1);
		    		 plugin.getLogger().info("Adding drug dealer to world at location: " + player.getLocation());
		    		 tradingAPI.addDruggstoVillager(drugDealer);
		    	  } else {
		    		  sender.sendMessage("You must be a player to spawn a drug dealer");
		    	  }
		    	  
		      } else if (args.length >= 1 && args[0].equalsIgnoreCase("help")) {
		    	  sender.sendMessage(ChatColor.GOLD + "Please use command like this: /drugs <command>");
		    	  sender.sendMessage(ChatColor.GOLD + "/drugs help - display this page again");
		    	  sender.sendMessage(ChatColor.GOLD + "/drugs give - give the sender drugs");
		    	  sender.sendMessage(ChatColor.GOLD + "/drugs dealer - spawn a drug dealer");
		      } else {
		    	  sender.sendMessage(ChatColor.RED + "Please use command like this: /drugs help");
		      }
		    }
			return false;
	  }
		        
	
}
