package com.shadowlandsmc.gunsandcrime;

import java.util.List;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.Plugin;

public class VillagerTrading {

	Plugin plugin;
	Drugs drugs;
	
	public VillagerTrading(Plugin plugin, Drugs drugs) {
		this.plugin = plugin;
		this.drugs = drugs;
	}
	
	public void addDruggstoVillager(AbstractVillager villager) {
		villager.setCustomName("Homeless Bum");
		List<MerchantRecipe> trades = villager.getRecipes();
		trades.add(new MerchantRecipe(drugs.getDrugs(), drugs.maxTrades()));
		villager.setRecipes(trades);
	}
	
}
