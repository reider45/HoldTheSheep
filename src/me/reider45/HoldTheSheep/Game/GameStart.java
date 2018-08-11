package me.reider45.HoldTheSheep.Game;

import me.reider45.HoldTheSheep.Objects.Game;
import me.reider45.HoldTheSheep.Scoreboard.PlayerScoreboard;
import me.reider45.HoldTheSheep.Timers.GameTimer;
import me.reider45.HoldTheSheep.Utilities.Variables;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class GameStart {
	
	public static void startGame(Game game){
		
		// Set game's state
		game.setState(Game.State.INGAME);
		
		// Handle players
		int count = 0;
		for(Player p : Bukkit.getOnlinePlayers()){
			
			// Add players to the game
			game.getPlayers().add(p.getUniqueId());
			game.getScores().put(p.getUniqueId(), 0);
			
			// Teleport them to their spot
			p.teleport( game.getSpawns().get(count) );
			
			// Equip them
			GameEquip.equipPlayer(p);
			
			count++;
		}
		
		// Spawn Sheep
		Sheep sheep = (Sheep)Variables.sheep.getWorld().spawnEntity(Variables.sheep, EntityType.SHEEP);
		sheep.setColor(DyeColor.PINK);
		
		game.setSheep(sheep);
		
		// Start game
		GameTimer.startTimer(game);
		
		// Start scoreboard
		PlayerScoreboard.startScoreboard();
		
	}

}
