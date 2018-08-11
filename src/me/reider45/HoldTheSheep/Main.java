package me.reider45.HoldTheSheep;


import me.reider45.HoldTheSheep.Events.PlayerDamage;
import me.reider45.HoldTheSheep.Events.PlayerFood;
import me.reider45.HoldTheSheep.Events.PlayerJoin;
import me.reider45.HoldTheSheep.Events.PlayerPickup;
import me.reider45.HoldTheSheep.Game.GameEquip;
import me.reider45.HoldTheSheep.Objects.Game;
import me.reider45.HoldTheSheep.Timers.LobbyTimer;
import me.reider45.HoldTheSheep.Utilities.Variables;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Main plugin;
	public static Main getInstance(){
		return plugin;
	}

	public void onEnable(){
		plugin = this;

		// Copy config defaults
		saveDefaultConfig();

		// Register Events
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerPickup(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerFood(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDamage(), this);
		
		// Start the match
		startGame();

	}

	public void onDisable(){
		plugin = null;

		saveConfig();
	}

	public void startGame(){
		// Register global variables
		Variables.registerVariables();

		// Lobby, Game, Players needed
		Game game = new Game(
				Variables.lobby_timer,
				Variables.game_timer,
				Variables.players_needed,
				Variables.spawns);

		// Register Game Items
		GameEquip.registerItems();

		// Start the lobby timer
		LobbyTimer.startTimer(game);
	}

}
