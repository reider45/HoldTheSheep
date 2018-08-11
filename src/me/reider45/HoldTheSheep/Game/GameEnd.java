package me.reider45.HoldTheSheep.Game;

import java.util.UUID;

import me.reider45.HoldTheSheep.Main;
import me.reider45.HoldTheSheep.Objects.Game;
import me.reider45.HoldTheSheep.Utilities.Chat;
import me.reider45.HoldTheSheep.Utilities.Variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameEnd {

	public static void endGame(Game game){
		
		// Set game's state
		game.setState(Game.State.RESTARTING);
		
		// Handle players
		for(Player p : Bukkit.getOnlinePlayers()){
			
			p.setHealth(20.0);
			p.teleport(Variables.lobby);
			
		}
		
		// Cycle through all scores and get the highest one
		Player winner = null;
		int score = -1;
		for(UUID id : game.getPlayers()){
			Player p = Bukkit.getPlayer(id);
			
			if(id != null){
				if(game.getScores().get(id) > score){
					winner = p;
					score = game.getScores().get(id);
				}
			}
		}
		
		Chat.broadcast("§eThe game's over! " + winner.getName() + " wins the match!");
		
		// Kill the sheep
		if(!game.getSheep().isDead()){
			game.getSheep().setHealth(0.0);
		}
		
		new BukkitRunnable() {
			public void run(){
				
				for(Player p : Bukkit.getOnlinePlayers()){
					p.kickPlayer("The game is now restarting!");
				}
				
				// Start new game
				Main.getInstance().startGame();
				
			}
		}.runTaskLater(Main.getInstance(), 60L);
		
	}
	
}
