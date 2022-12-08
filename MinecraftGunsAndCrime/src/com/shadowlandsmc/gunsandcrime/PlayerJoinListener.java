package com.shadowlandsmc.gunsandcrime;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class PlayerJoinListener implements Listener {


	private Plugin plugin = Main.plugin;
	
	Crack crack = new Crack(plugin);
	ChristmasCookies cookies = new ChristmasCookies(plugin);
	HolyTexts bible = new HolyTexts(plugin);
	
	@EventHandler
	public void playerJoinEvent(PlayerJoinEvent event) {
		
		if (!event.getPlayer().hasPlayedBefore()) {
			List<String> manufactures = plugin.getConfig().getStringList("AuthorizedManufactures");
			if(manufactures.contains(event.getPlayer().getName())) {
				
				tutorial(event.getPlayer());
				
			}
		}
		 return;
		
	}
	
	
	public void tutorial(Player player) {
		
		player.sendMessage(ChatColor.RED + player.getName() + " you don't know me, but I know you. I charge you speak to no one about this");
		player.sendMessage(ChatColor.RED + "The last time I saw you, you were adicted to crack. Unfortunately, "
				+ "due to increased DEA crackdowns, we have had to run our operation a bit more low key.");
		player.sendMessage(ChatColor.RED + "Say would you be interested in helping us in our operations?"
				+ " You are the only person who knows that we are still making crack. You must keep our recipe a secret, "
				+ "but you should make it, sell it and start your drug empire.");
		
		//https://www.spigotmc.org/threads/how-to-make-a-written-book.47065/
		//generate lars intro book
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK, 1);
		

		List<String> lore = new ArrayList<String>();
		lore.add("For Lar's eyes only");
		
		List<String> pages = new ArrayList<String>();
		pages.add(ChatColor.RED + player.getName() + " you don't know me, but I know you. I charge you speak to no one about this");
		pages.add(ChatColor.RED + "The last time I saw you, you were adicted to crack. Unfortunately, "
				+ "due to increased DEA crackdowns, we have had to run our operation a bit more low key.");
		pages.add(ChatColor.RED + "Say would you be interested in helping us in our operations?"
				+ " You are the only person who knows that we are still making crack. You must keep our recipe a secret, "
				+ "but you should make it, sell it and start your drug empire.");
		pages.add(ChatColor.RED + "Our recipe is simple, first you must build a drug den. Once complete, you can cook crack in");
		pages.add(ChatColor.RED + "To make crack: Combine one bonemeal and 8 diamonds in a crafting grid to make 4 crack");
		pages.add(ChatColor.RED + "To make a crack pipe: Combine one crack and one stick");
		pages.add(ChatColor.RED + "To make 'Christmas' cookies: Combine one crack and one cookie");
		pages.add(ChatColor.RED + "To make the Holy Bible: Combine eight crack and one book");
		
		//set all the meta to items
		BookMeta bookMeta = (BookMeta) item.getItemMeta();
		bookMeta.setTitle("Secret Message");
		bookMeta.setAuthor("Drug Dealer");
		bookMeta.setLore(lore);
		bookMeta.setPages(pages);
		
		item.setItemMeta(bookMeta);
		
		
	}
	
}
