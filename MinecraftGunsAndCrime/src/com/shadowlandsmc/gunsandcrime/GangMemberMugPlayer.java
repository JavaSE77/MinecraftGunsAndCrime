package com.shadowlandsmc.gunsandcrime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class GangMemberMugPlayer implements Listener {

	private Plugin plugin = Main.plugin;
	
	@EventHandler
	public void playerInteractEntityEvent(PlayerInteractEntityEvent event) {
		
		if(event.getRightClicked().getName().equals(plugin.getConfig().getString("drugDealerName"))) {
		    
		    Random random = new Random();
		    int random_int = random.nextInt(plugin.getConfig().getInt("drugDealerChanceOfMugging"));
		    
		    //random chance of mugging the player
		    if(random_int == 0) {

	    		List<ItemStack> itemsInInventory = new ArrayList<ItemStack>();
	    		
		    	for(int i = 0; i <= event.getPlayer().getInventory().getSize(); i++) {

		    		if(event.getPlayer().getInventory().getItem(i) != null) {
		    			itemsInInventory.add(event.getPlayer().getInventory().getItem(i));
		    		}
		    		
		    	}
		    	
		    	if(itemsInInventory.size() > 0) {
				    int itemMuggedInt = random.nextInt(itemsInInventory.size());
				    ItemStack muggedItem = itemsInInventory.get(itemMuggedInt);
				    if(muggedItem != null) {
				    	event.setCancelled(true);
				    	event.getPlayer().getInventory().removeItem(muggedItem);
			    		event.getPlayer().sendMessage(ChatColor.RED + "You have been mugged by a gang member. They have taken your " 
				    	+ muggedItem.getType().toString().toLowerCase().replaceAll("_", " "));
			    		
				        //The way we are replacing color codes is bad practice, and may not work in future updates
						String msg = plugin.getConfig().getString("GhettoMuggingMessage");
				        plugin.getServer().broadcastMessage(msg.replaceAll("&", "§").
				            replaceAll("%player%", event.getPlayer().getName()).replaceAll("%item%", muggedItem.getType().toString().toLowerCase().replaceAll("_", " ")));
			    		plugin.getLogger().info("Gang Member mugged " + event.getPlayer().getName() + "'s item. ItemStack: " + muggedItem.toString());
				    }
		    	}
		    	
		    }
			
		}
		
	}
	
}
