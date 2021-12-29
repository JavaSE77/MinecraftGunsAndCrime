package com.shadowlandsmc.gunsandcrime;


import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class GangMemberProtection implements Listener {

	private Plugin plugin = Main.plugin;
	
	
	//Handler player and mobs attacking gang members
	@EventHandler
	public void attackGangMember(EntityDamageByEntityEvent event) {
		
		if(event.isCancelled() || event.getEntity().getCustomName() == null) {
			return;
		}
		
		
		if(event.getEntity().getCustomName().equals(plugin.getConfig().getString("drugDealerName"))) {
			plugin.getLogger().info(event.getDamager().getName() + " is attacking a drug dealer at: " + event.getEntity().getLocation());
			
			double dealerAttack = event.getDamage() * 10;
			

			if(event.getDamager() instanceof LivingEntity) {
				LivingEntity attacker = (LivingEntity) event.getDamager();
				//check if the attacker will survive an attack
				if(attacker.getHealth() > dealerAttack) {
					attacker.damage(dealerAttack);
					
					if(attacker instanceof Player) {
						Player player = (Player) attacker;
						player.sendMessage(ChatColor.RED + "You attacked a gang member. The gang member shot you.");
						player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2f, 2f);
					}
				} else {
					//give them a null damage cause
					attacker.setLastDamageCause(null);
					attacker.damage(dealerAttack);
					plugin.getLogger().info("Gang Member killed " + attacker.getName());
					
					if(attacker instanceof Player) {
						Player player = (Player) attacker;
						player.sendMessage(ChatColor.RED + "You attacked a gang member. The gang member shot you dead.");
						player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2f, 2f);
						
					}
					
				}
			}
			
		}
		
	}
	
	//check if the player is in the ghetto
	@EventHandler(priority = EventPriority.LOWEST)
	public void playerDeathEvent(PlayerDeathEvent event) {
		Location location = event.getEntity().getLocation();
		if(location.getWorld().getName().equals(plugin.getConfig().getString("GhettoCordsWorld"))) {
			if(location.getBlockX() >= plugin.getConfig().getInt("GhettoCordsLowerX") 
					&& location.getBlockX() <= plugin.getConfig().getInt("GhettoCordsUpperX")) {
				if(location.getBlockY() >= plugin.getConfig().getInt("GhettoCordsLowerY") 
						&& location.getBlockY() <= plugin.getConfig().getInt("GhettoCordsUpperY")) {
					if(location.getBlockZ() >= plugin.getConfig().getInt("GhettoCordsLowerZ") 
							&& location.getBlockZ() <= plugin.getConfig().getInt("GhettoCordsUpperZ")) {
						
						//then the player is in the ghetto
						//remove the death message so we can handle it ourselves
						event.setDeathMessage(null);
				        //The way we are replacing color codes is bad practice, and may not work in future updates
						String msg = plugin.getConfig().getString("GhettoDeathMessage");
				        plugin.getServer().broadcastMessage(msg.replaceAll("&", "§").
				            replaceAll("%player%", event.getEntity().getName()));
						
					}
				}
			}
		}
	}
	
}
