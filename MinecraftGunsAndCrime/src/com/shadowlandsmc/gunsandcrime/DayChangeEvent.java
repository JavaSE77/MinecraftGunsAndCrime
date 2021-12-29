package com.shadowlandsmc.gunsandcrime;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DayChangeEvent {

	private Plugin plugin;
	public DayChangeEvent(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public void scheduleDayChangeChecker() {
	
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
		    @Override
		    public void run() {

		    	//get all the crack addicts, and if there aren't any, return
    			List<String> crackAddicts = plugin.getConfig().getStringList("Crack.Addicts");
		    	//get all the cookie addicts, and if there aren't any, return
    			List<String> cookieAddicts = plugin.getConfig().getStringList("ChristmasCookies.Addicts");
    			if(crackAddicts == null && cookieAddicts == null) return;
    			Crack crack = new Crack(plugin);
    			ChristmasCookies cookies = new ChristmasCookies(plugin);

		    	for (Player player : plugin.getServer().getOnlinePlayers() ) {
		    		
		    		if(player.getWorld().getTime() >= 1000 && player.getWorld().getTime() < 1200) {

		    			if(crackAddicts.contains(player.getName())) {
		    				crack.withdrawl(player);
		    			}
		    			if(cookieAddicts.contains(player.getName())) {
		    				cookies.withdrawl(player);
		    			}

		    		}
		    		
		    	}
		    		
		    	
		    }
		}, 0L, 200L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
		
	}
 }
