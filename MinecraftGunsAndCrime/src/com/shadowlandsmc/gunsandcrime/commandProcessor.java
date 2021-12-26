package com.shadowlandsmc.gunsandcrime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class commandProcessor implements CommandExecutor {

	private Plugin plugin = Main.plugin;
	
	Crack drugs = new Crack(plugin);
	HolyTexts bible = new HolyTexts(plugin);
	
	  public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		    if (cmdLabel.equalsIgnoreCase("drugs")) {
		      if (args.length >= 1 && args[0].equalsIgnoreCase("give")) {
		        if (sender.hasPermission("gensandcrime.drugs.give")) {
		        	if (sender instanceof Player) {
		        		Player player = (Player) sender;
		        		player.getInventory().addItem(drugs.getDrugs());
		        		player.getInventory().addItem(drugs.getParaphernalia());
		        		player.getInventory().addItem(bible.getHolyText());
		        	} else {
		        		sender.sendMessage("You must be a player to get drugs");
		        	}
		        	
		        }
		        else { 
		        	sender.sendMessage(ChatColor.RED + "You must be OP to give drugs via commands");
		        }
		      } 
		      else {
		    	  sender.sendMessage(ChatColor.RED + "Please use command like this: /drugs give");
		      }
		    }
			return false;
	  }
		        
	
}
