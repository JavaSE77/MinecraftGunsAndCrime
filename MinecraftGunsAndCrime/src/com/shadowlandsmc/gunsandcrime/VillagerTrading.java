package com.shadowlandsmc.gunsandcrime;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftMerchantRecipe;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.Plugin;

import com.google.common.collect.Lists;

public class VillagerTrading {

	Plugin plugin;
	Drugs drugs;
	HolyTexts bible;
	
	public VillagerTrading(Plugin plugin, Drugs drugs, HolyTexts bible) {
		this.plugin = plugin;
		this.drugs = drugs;
		this.bible = bible;
	}
	
	public void addDruggstoVillager(AbstractVillager villager) {
		villager.setCustomName(plugin.getConfig().getString("drugDealerName"));
		//use this line if you want them to keep their trades
		//List<MerchantRecipe> trades = Lists.newArrayList(villager.getRecipes());
		List<MerchantRecipe> trades = Lists.newArrayList();
		
		//add the drug trade
		MerchantRecipe drugRecipe = new MerchantRecipe(drugs.getDrugs(), 0, 0, false, 0, 0);
		List<ItemStack> ingredients = new ArrayList<ItemStack>();
		ingredients.add(new ItemStack(Material.DIAMOND, drugs.costOfDrugs()));
		ingredients.add(new ItemStack(Material.AIR));
		drugRecipe.setIngredients(ingredients);
		drugRecipe.setMaxUses(drugs.maxTrades());
		drugRecipe.setPriceMultiplier((float) drugs.costIncrease());
		
		
		//add the Paraphernalia trade
		MerchantRecipe paraphernaliaRecipe = new MerchantRecipe(drugs.getParaphernalia(), 0, 0, false, 0, 0);
		List<ItemStack> paraphernaliaIngredients = new ArrayList<ItemStack>();
		paraphernaliaIngredients.add(new ItemStack(Material.DIAMOND, drugs.costOfParaphernalia()));
		paraphernaliaIngredients.add(new ItemStack(Material.AIR));
		paraphernaliaRecipe.setIngredients(paraphernaliaIngredients);
		paraphernaliaRecipe.setMaxUses(drugs.maxTrades());
		paraphernaliaRecipe.setPriceMultiplier((float) drugs.costIncrease());
		
		
		//add the bible trade
		MerchantRecipe bibleRecipe = new MerchantRecipe(bible.getHolyText(), 0, 0, false, 0, 0);
		List<ItemStack> bibleIngredients = new ArrayList<ItemStack>();
		bibleIngredients.add(new ItemStack(Material.DIAMOND, bible.getCost()));
		bibleIngredients.add(new ItemStack(Material.AIR));
		bibleRecipe.setIngredients(bibleIngredients);
		bibleRecipe.setMaxUses(drugs.maxTrades());
		bibleRecipe.setPriceMultiplier((float) drugs.costIncrease());
		
		trades.add(drugRecipe);
		trades.add(paraphernaliaRecipe);
		trades.add(bibleRecipe);
		villager.setRecipes(trades);
	}
	
}
