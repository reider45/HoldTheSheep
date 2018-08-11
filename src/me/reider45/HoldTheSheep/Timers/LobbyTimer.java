package me.reider45.HoldTheSheep.Timers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.reider45.HoldTheSheep.Main;
import me.reider45.HoldTheSheep.Game.GameStart;
import me.reider45.HoldTheSheep.Objects.Game;
import me.reider45.HoldTheSheep.Utilities.Chat;
import me.reider45.HoldTheSheep.Utilities.Variables;

public class LobbyTimer {

	public static void startTimer(final Game game){

		new BukkitRunnable() {
			public void run(){

				int time = game.getLobbyTimer();

				// If the timer should still be running
				if(time > 0){

					// Broadcast
					if(time <= 10 || time % 15 == 0){
						Chat.broadcast("§aGame starting in §6" + time + "§a seconds!");
					}

					// Subtract time, broadcast if at x time left
					game.setLobbyTimer(--time);

				} else {

					// 3/3 Players Online
					if(game.getPlayersNeeded() == Bukkit.getOnlinePlayers().size()){

						// Start the game
						this.cancel();
						GameStart.startGame(game);

					} else {
						// Reset Lobby Timer
						Chat.broadcast("§aNot enough players to begin!");
						game.setLobbyTimer(Variables.lobby_timer);
					}
				}
			}

		}.runTaskTimer(Main.getInstance(), 20L, 20L);

	}

}
