package com.shadowlandsmc.gunsandcrime;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class PlayerDrugUse implements Listener {

	private Plugin plugin = Main.plugin;
	
	Crack crack = new Crack(plugin);
	ChristmasCookies cookies = new ChristmasCookies(plugin);
	Kilos kilos = new Kilos(plugin);
	
	@EventHandler
	public void playerUseDrugs(PlayerInteractEvent event) {
		
		if(event.getAction() == Action.RIGHT_CLICK_AIR) {
			if(event.getPlayer().getInventory().getItemInMainHand().isSimilar(crack.getParaphernalia())) {
					//compareItems(drugs.getParaphernalia(), event.getPlayer().getInventory().getItemInMainHand())) 
				if(event.getPlayer().getInventory().containsAtLeast(crack.getDrugs(), 1)) {
				event.getPlayer().sendMessage("You have smoked crack");
				crack.consume(event.getPlayer());
				event.getPlayer().getInventory().removeItem(crack.getDrugs());
				}
			} else if(event.getPlayer().getInventory().getItemInMainHand().isSimilar(cookies.getParaphernalia())) {
				//compareItems(drugs.getParaphernalia(), event.getPlayer().getInventory().getItemInMainHand())) 
			if(event.getPlayer().getInventory().containsAtLeast(cookies.getDrugs(), 1)) {
			event.getPlayer().sendMessage("You have smoked Chirstmas Cookies");
			cookies.consume(event.getPlayer());
			event.getPlayer().getInventory().removeItem(cookies.getDrugs());
			}
		}else if(event.getPlayer().getInventory().getItemInMainHand().isSimilar(kilos.getParaphernalia())) {
			//compareItems(drugs.getParaphernalia(), event.getPlayer().getInventory().getItemInMainHand())) 
		if(event.getPlayer().getInventory().containsAtLeast(kilos.getDrugs(), 1)) {
		event.getPlayer().sendMessage("You have smoked an entire kilo of crack. That was not a good idea");
		kilos.consume(event.getPlayer());
		event.getPlayer().getInventory().removeItem(kilos.getDrugs());
		}
	}
		}
		
	}
	
	@EventHandler
	public void playerConsumeCookies(PlayerItemConsumeEvent event) {
		
		if(event.getItem().isSimilar(cookies.getDrugs())) {
			event.getPlayer().sendMessage(ChatColor.GOLD + "You should not eat Santas cookies without Santas milk!");
			event.getPlayer().setHealth(20);
			event.getPlayer().setFoodLevel(20);
			event.getPlayer().setSaturation(20);
		} 
		
	}
	
	/**
	 * Compares the first item to the second item. First item needs to be most restrictive
	 * @return boolean
	 * */
	public boolean compareItems(ItemStack item1, ItemStack item2) {
		
		if (item1.getType() == item2.getType()) {
			if (item1.hasItemMeta()) {
			if (item1.hasItemMeta() && item2.hasItemMeta()) {
				if (item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName())) { 
					if (item1.getItemMeta().hasLore()) {
					if (item1.getItemMeta().hasLore() && item2.getItemMeta().hasLore()) {
						if (item1.getItemMeta().getLore().equals(item2.getItemMeta().getLore())) {
							if (item1.getDurability() == item2.getDurability()) {
								if (item1.getEnchantments().equals(item2.getEnchantments())) {
									return true;
								}
							} else return false;
						} else return false;
					} else return false;
					}
				} else return false;
			} else return false;
			}
			return true;
		}
		
		return false;
	}
	
	
}
