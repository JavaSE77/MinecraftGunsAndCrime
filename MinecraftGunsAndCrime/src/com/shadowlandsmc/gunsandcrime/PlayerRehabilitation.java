package com.shadowlandsmc.gunsandcrime;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class PlayerRehabilitation implements Listener {


	private Plugin plugin = Main.plugin;
	
	Crack crack = new Crack(plugin);
	HolyTexts bible = new HolyTexts(plugin);
	
	@EventHandler
	public void playerUseDrugs(PlayerInteractEvent event) {
		
		if(event.getAction() == Action.RIGHT_CLICK_AIR) {
			if(bible.compareItems(bible.getHolyText(), event.getPlayer().getInventory().getItemInMainHand())) {
				
				event.getPlayer().sendMessage("You have opened the bible");
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				    @Override
				    public void run() {

						if(bible.compareItems(bible.getHolyText(), event.getPlayer().getInventory().getItemInMainHand())) {
						event.getPlayer().sendMessage("You have read the bible");
						
						crack.rehabilitate(event.getPlayer());
						
						} else {
							event.getPlayer().sendMessage("You did not retain any information from the bible. Maybe you should read it for longer next time");
						}
				    	
				    }
				}, 200L); //10 Tick initial delay
			}
		}
	}
	
	
}
