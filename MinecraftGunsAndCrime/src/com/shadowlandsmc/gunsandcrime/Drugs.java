package com.shadowlandsmc.gunsandcrime;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Drugs {

	//Returns the itemstack of the drug
	public ItemStack getDrugs();
	//consumes the drug. Does not implement pipes
	public void consume(Player player);
	//returns the itemstack of the pipe
	public ItemStack getParaphernalia(); 
	//sets the player addicted to drugs
	public void addiction(Player player);
	//removes the player from drug addiction
	public void rehabilitate(Player player);
	//gives the player withdrawl effects if they don't smoke crack
	public void withdrawl(Player player);
	//get the total amount of times a user can purchase this drug from a drug dealer
	public int maxTrades();
	//get the cost of drugs for players before they become addicted to crack
	public int costOfDrugs();
	//get the cost of drugs for people who are already addicted to the drugs
	public int costOfDrugsForAddicts();
	//get the cost of the paraphernalia for the drugs
	public int costOfParaphernalia();
	
}
