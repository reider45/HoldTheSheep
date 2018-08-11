package me.reider45.HoldTheSheep.Utilities;

import java.util.ArrayList;
import java.util.List;

import me.reider45.HoldTheSheep.Main;

import org.bukkit.Location;

public class Variables {
	
	public static Integer 
	lobby_timer,
	game_timer,
	players_needed;
	
	public static List<Location>
	spawns;
	
	public static Location
	sheep,
	lobby;
	
	public static void registerVariables(){
		
		spawns = new ArrayList<Location>();
		for(String loc : Main.getInstance().getConfig().getStringList("Arena.Spawns")){
			spawns.add( Util.toLocation(loc) );
		}
		
		lobby = Util.toLocation(Main.getInstance().getConfig().getString("Arena.Lobby"));
		sheep = Util.toLocation(Main.getInstance().getConfig().getString("Arena.Sheep"));
		
		lobby_timer = Main.getInstance().getConfig().getInt("Game.Timer.Lobby");
		game_timer = Main.getInstance().getConfig().getInt("Game.Timer.Game");
		players_needed = Main.getInstance().getConfig().getInt("Game.Players");
	}
	
}
