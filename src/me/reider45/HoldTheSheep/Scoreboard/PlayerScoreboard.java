package me.reider45.HoldTheSheep.Scoreboard;

import java.util.UUID;

import me.reider45.HoldTheSheep.Main;
import me.reider45.HoldTheSheep.Objects.Game;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class PlayerScoreboard {

	public static void startScoreboard(){

		final ScoreboardManager manager = Bukkit.getScoreboardManager();
		final Scoreboard board = manager.getNewScoreboard();

		final Objective obj = board.registerNewObjective("HTS", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§c§lHold The Sheep");

		new BukkitRunnable(){
			public void run(){	

				Game game = Game.getInstance();
				
				// Only run if ingame
				if(game.getState() != Game.State.INGAME){
					this.cancel();
					for(Player p : Bukkit.getOnlinePlayers()){
						p.setScoreboard(manager.getNewScoreboard());
					}
				}

				int highestScore = -1;
				
				// Add all the player's scores
				for(UUID id : game.getPlayers()){
					Player p = Bukkit.getPlayer(id);

					if(p != null){
						Score pscore = obj.getScore(ChatColor.YELLOW + p.getName());
						pscore.setScore( game.getScores().get(p.getUniqueId()) );
						
						if(pscore.getScore() > highestScore){
							highestScore = pscore.getScore();
						}
					}
				}
				
				// Add a blank space above the highest score (looks nice)
				Score blank = obj.getScore("");
				blank.setScore(highestScore);

				// Give everyone the scoreboard
				for(Player p : Bukkit.getOnlinePlayers()){
					p.setScoreboard(board);
				}
			}

		}.runTaskTimer(Main.getInstance(), 20L, 20L);

	}

}
