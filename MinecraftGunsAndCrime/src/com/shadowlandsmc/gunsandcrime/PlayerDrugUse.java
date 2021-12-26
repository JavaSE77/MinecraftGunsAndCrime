package com.shadowlandsmc.gunsandcrime;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PlayerDrugUse implements Listener {

	private Plugin plugin = Main.plugin;
	
	Crack drugs = new Crack(plugin);
	
	@EventHandler
	public void playerUseDrugs(PlayerInteractEvent event) {
		
		if(event.getAction() == Action.RIGHT_CLICK_AIR) {
			if(event.getPlayer().getInventory().getItemInMainHand().isSimilar(drugs.getParaphernalia())) {
					//compareItems(drugs.getParaphernalia(), event.getPlayer().getInventory().getItemInMainHand())) 
				if(event.getPlayer().getInventory().containsAtLeast(drugs.getDrugs(), 1)) {
				event.getPlayer().sendMessage("You have smoked crack");
				drugs.consume(event.getPlayer());
				event.getPlayer().getInventory().removeItem(drugs.getDrugs());
				}
			}
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
