package me.reider45.HoldTheSheep.Utilities;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;

public class Chat {
	
	public static void broadcast(String msg){
		Bukkit.broadcastMessage( ChatColor.RED + "[HTS] " + msg);
	}

}
