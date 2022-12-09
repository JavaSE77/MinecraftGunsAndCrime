package com.shadowlandsmc.gunsandcrime;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
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
			
			String atribute = "fellow";
			if (event.getPlayer().getName().equalsIgnoreCase("randufett")) atribute = "gay";
			if (event.getPlayer().getName().equalsIgnoreCase("_thewizard_")) atribute = "gay";
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + event.getPlayer().getName() +" written_book{pages:"
					+ "['{\"text\":\"Greetings my "+ atribute + " sojourner! Welcome to the Young Hegelians Minecraft Server"
					+ " season three! I am most certain this season will be the best one yet. I had a vision of fertile"
					+ " land full of castles and manors fit for kings! \"}','{\"text\":\"Alas, we must not waste time with"
					+ " banter. But first, I must tell you about this land. Our ancestors tell a story about a brave adventurer"
					+ " who once ripped a hole through space and time by killing the dragon of power, Jean. \"}','{\"text\":\"Jean\\'s"
					+ " power was the only thing holding our ancestor\\'s land together, and without the dragon, the land perished. Our"
					+ " ancestors found refuge in this land, and have their best alchemists dedicated to protecting the dragon of this"
					+ " land. \"}','{\"text\":\"They have cast a protection spell over the eye of ender crafting recipe, making it"
					+ " significantly more difficult to craft. The new crafting recipe requires nether stars, conduits, and mob "
					+ "spawners in addition to blaze rods and ender pearls. \"}','{\"text\":\"The kingdom\\'s top alchemist, Jared,"
					+ " has found a way to craft mob spawners, and he has permitted me to share the secrets with you. But I must"
					+ " warn you, \"}','{\"text\":\"Jared is not sure how stable this newfound power is. \\\\u0020He has discovered"
					+ " some more recipes to make otherwise uncraftable items obtainable. Here are the notes directly from his lab"
					+ " notebook: \\\\n \"}','{\"text\":\"- To craft the mob spawner, you need four netherite ingots, and a totem "
					+ "of undying, as well as a few chains.\"}','{\"text\":\"- Alternate recipes have been found for ender chests and"
					+ " crystals avoiding the need for the eye of ender. \"}','{\"text\":\"- The crafting recipe for enchanted golden "
					+ "apples has been recovered, except it now requires a regular golden apple to craft instead of a normal red apple.\"}"
					+ "','{\"text\":\"- Copper can now be used to craft chainmail armor.\\\\n- A trident can now be crafted with three"
					+ " nautilus shells and some prismarine.\"}','{\"text\":\"Unfortunetly, during his experiements, Jared seems to have"
					+ " damaged the fabric of this universe, causing some \\\\\"anomalies\\\\\" \"}','{\"text\":\"The wither can only"
					+ " be summoned in the nether\\\\nElder guardians now drop nautilus shells.\\\\nIron golems now only drop iron when"
					+ " killed by a player.\"}','{\"text\":\"When an evoker is killed, he now summons a vex next to you for revenge"
					+ ".\\\\nVoid damage is now inflicted above the nether roof.\"}','{\"text\":\"Our top biologist Elie seems to"
					+ " have designed a way to artificially manufacture spawn eggs for use in mob spawners. This could prove very"
					+ " useful for you. \"}','{\"text\":\"All of these recipes can be found in your recipe book, and remember,"
					+ " with great power, comes great responsibility. Good luck\"}'],title:Introduction,author:Cicerone}");
			
			
			
			List<String> manufactures = plugin.getConfig().getStringList("AuthorizedManufactures");
			if(manufactures.contains(event.getPlayer().getName().toLowerCase())) {
				
				tutorial(event.getPlayer());
				
			}
		}
		 return;
		
	}
	
	
	public void tutorial(Player player) {
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
		pages.add(ChatColor.RED + "Our recipe is simple, first you must build a drug den. Once complete, you can cook crack in it");
		pages.add(ChatColor.RED + "To make crack: Combine one bonemeal and 8 diamonds in a crafting grid to make 4 crack");
		pages.add(ChatColor.RED + "To make a crack pipe: Combine one netherite and one stick");
		pages.add(ChatColor.RED + "To make 'Christmas' cookies: Combine one diamond and one cookie");
		pages.add(ChatColor.RED + "To make Santas Milk: Combine one netherite and one milk bucket");
		pages.add(ChatColor.RED + "To make the Holy Bible: Combine 4 diamonds, 4 netherite ingots, and one book");
		
		//set all the meta to items
		BookMeta bookMeta = (BookMeta) item.getItemMeta();
		bookMeta.setTitle("Secret Message");
		bookMeta.setAuthor("Drug Dealer");
		bookMeta.setLore(lore);
		bookMeta.setPages(pages);
		
		item.setItemMeta(bookMeta);
		
		player.getInventory().addItem(item);
		
		
	}
	
}
