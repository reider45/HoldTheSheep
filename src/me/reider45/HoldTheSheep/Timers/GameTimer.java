package me.reider45.HoldTheSheep.Timers;

import me.reider45.HoldTheSheep.Main;
import me.reider45.HoldTheSheep.Game.GameEnd;
import me.reider45.HoldTheSheep.Objects.Game;
import me.reider45.HoldTheSheep.Utilities.Chat;

import org.bukkit.scheduler.BukkitRunnable;

public class GameTimer {
	
	public static void startTimer(final Game game){

		new BukkitRunnable() {
			public void run(){

				int time = game.getGameTimer();

				// If the timer should still be running
				if(time > 0){
					
					// Verify the game should keep going
					if(game.getPlayers().size() <= 1){
						this.cancel();
						GameEnd.endGame(game);
					}
					
					if(game.getScores().containsValue(60)){
						this.cancel();
						GameEnd.endGame(game);
					}
					
					// End of verify

					// Broadcast
					if(time % 60 == 0){
						Chat.broadcast("§aGame ending in §6"+ (time/60) + "§a minutes!");
					}else
					
					if(time <= 10 || time % 15 == 0 && time < 60){
						Chat.broadcast("§aGame ending in §6" + time + "§a seconds!");
					}

					// Subtract time, broadcast if at x time left
					game.setGameTimer(--time);

				} else {
					// End the game
					this.cancel();
					GameEnd.endGame(game);
				}
			}

		}.runTaskTimer(Main.getInstance(), 20L, 20L);

	}

}
