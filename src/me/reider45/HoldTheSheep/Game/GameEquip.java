package me.reider45.HoldTheSheep.Game;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GameEquip {
	
	public static ItemStack stick;
	
	public static void registerItems(){
		stick = new ItemStack(Material.STICK, 1);
		
		ItemMeta meta = stick.getItemMeta();
		meta.setDisplayName("§9Knockback Stick");
		stick.setItemMeta(meta);
		
		stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
	}
	
	public static void equipPlayer(Player p){
		
		p.getInventory().clear();
		p.setHealth(20.0);
		p.setFoodLevel(20);
		p.setGameMode(GameMode.SURVIVAL);
		p.getInventory().addItem(stick);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
		
	}

}
