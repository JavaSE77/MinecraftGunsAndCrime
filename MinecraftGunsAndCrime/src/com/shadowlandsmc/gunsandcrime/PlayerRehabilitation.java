package com.shadowlandsmc.gunsandcrime;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class PlayerRehabilitation implements Listener {


	private Plugin plugin = Main.plugin;
	
	Crack crack = new Crack(plugin);
	ChristmasCookies cookies = new ChristmasCookies(plugin);
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
						cookies.rehabilitate(event.getPlayer());
						
						//1/20 chance of the bible being removed from player inventory
					    Random random = new Random();
					    int random_int = random.nextInt(20);
					    if(random_int == 0) {
					    	event.getPlayer().sendMessage(ChatColor.BLACK + "Your Bible was torn while you were on drugs. You can no longer read it.");
						    event.getPlayer().getInventory().removeItem(bible.getHolyText());
					    }

						} else {
							event.getPlayer().sendMessage("You did not retain any information from the bible. Maybe you should read it for longer next time");
						}
				    	
				    }
				}, 200L); //10 Tick initial delay
			}
		}
	}
	
	
}
