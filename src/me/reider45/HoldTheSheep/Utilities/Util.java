package me.reider45.HoldTheSheep.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Util {
	
	public static Location toLocation(String s) {
		String[] sp = s.split(",");
		return new Location(
				Bukkit.getWorld(sp[0]),
				Integer.parseInt(sp[1]),
				Integer.parseInt(sp[2]),
				Integer.parseInt(sp[3]));
	}

	public static String toString(Location l) {
		return l.getWorld().getName()
				+ "," + l.getBlockX()
				+ "," + l.getBlockY()
				+ "," + l.getBlockZ();
	}

}
