package com.shadowlandsmc.gunsandcrime;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class CrackCrafting {

	Plugin plugin = Main.plugin;
	
	Crack crack = new Crack(plugin);
	ChristmasCookies cookies = new ChristmasCookies(plugin);
	HolyTexts bible = new HolyTexts(plugin);
	
	public void addCraftingRecipes() {
		
		ItemStack crackItem = crack.getDrugs();
		
		ShapedRecipe crackRecipe = new ShapedRecipe(new NamespacedKey(plugin, "crack"), crackItem);
		
		crackRecipe.shape("ddd","dbd","ddd");
		crackRecipe.setIngredient('d', Material.DIAMOND);
		crackRecipe.setIngredient('b', Material.BONE_MEAL);
		
		Bukkit.addRecipe(crackRecipe);
		
		ItemStack cookiesItem = cookies.getDrugs();
		
		ShapelessRecipe cookieRecipe = new ShapelessRecipe(new NamespacedKey(plugin, "cookieRecipe"), cookiesItem);
		
		cookieRecipe.addIngredient(Material.COOKIE);
		cookieRecipe.addIngredient(Material.DIAMOND);
		
		Bukkit.addRecipe(cookieRecipe);
		
		ItemStack pipeItem = crack.getParaphernalia();
		
		ShapelessRecipe crackPipe = new ShapelessRecipe(new NamespacedKey(plugin, "crackPipe"), pipeItem);
		
		crackPipe.addIngredient(Material.STICK);
		crackPipe.addIngredient(Material.NETHERITE_INGOT);
		
		Bukkit.addRecipe(crackPipe);
		
		
		ItemStack milkItem = cookies.getParaphernalia();
		
		ShapelessRecipe milkRecipe = new ShapelessRecipe(new NamespacedKey(plugin, "milkRecipe"), milkItem);
		
		milkRecipe.addIngredient(Material.MILK_BUCKET);
		milkRecipe.addIngredient(Material.NETHERITE_INGOT);
		
		Bukkit.addRecipe(milkRecipe);
		
		
		ItemStack bibleItem = bible.getHolyText();
		
		ShapedRecipe bibleRecipe = new ShapedRecipe(new NamespacedKey(plugin, "bibleRecipe"), bibleItem);
		
		bibleRecipe.shape("dnd","nbn","dnd");
		bibleRecipe.setIngredient('d', Material.DIAMOND);
		bibleRecipe.setIngredient('b', Material.BOOK);
		bibleRecipe.setIngredient('n', Material.NETHERITE_INGOT);
		
		Bukkit.addRecipe(bibleRecipe);
		
	}
	
}
