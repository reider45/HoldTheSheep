package me.reider45.HoldTheSheep.Events;

import me.reider45.HoldTheSheep.Objects.Game;
import me.reider45.HoldTheSheep.Utilities.Chat;
import me.reider45.HoldTheSheep.Utilities.Variables;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		if(Game.getInstance().getState() == Game.State.INGAME
				|| Game.getInstance().getState() == Game.State.RESTARTING){
			e.setKickMessage("This game is in progress!");
			e.setResult(Result.KICK_OTHER);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		Game game = Game.getInstance();
		
		// Add to the game
		game.getPlayers().add(p.getUniqueId());
		
		// Teleport to the lobby
		p.teleport(Variables.lobby);
		p.setGameMode(GameMode.ADVENTURE);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		Game game = Game.getInstance();
		
		// Remove
		if(game.getPlayers().contains(p.getUniqueId())){
			game.getPlayers().remove(p.getUniqueId());
		}
		
		// Clear their scoreboard
		p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		
		Chat.broadcast("§aPlayer " + p.getName() + " §a has quit the match!");
		
	}

}
