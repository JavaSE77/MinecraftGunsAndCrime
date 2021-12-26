package com.shadowlandsmc.gunsandcrime;


import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

public class HolyTexts {

	private Plugin plugin;
	
	public HolyTexts(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public ItemStack getHolyText() {
		//https://www.spigotmc.org/threads/how-to-make-a-written-book.47065/
		//generate the bible for the player to read to become unaddicted to crack
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK, 1);
		
		//get all the meta from config
		String title = plugin.getConfig().getString("HolyTexts.ItemName").replaceAll("&", "§");
		String author = plugin.getConfig().getString("HolyTexts.Author").replaceAll("&", "§");
		List<String> lore = plugin.getConfig().getStringList("HolyTexts.BookLore");
		List<String> pages = plugin.getConfig().getStringList("HolyTexts.BookPages");
		
		//set all the meta to items
		BookMeta bookMeta = (BookMeta) item.getItemMeta();
		bookMeta.setTitle(title);
		bookMeta.setAuthor(author);
		bookMeta.setLore(lore);
		bookMeta.setPages(pages);
		
		item.setItemMeta(bookMeta);
		
		return item;
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
