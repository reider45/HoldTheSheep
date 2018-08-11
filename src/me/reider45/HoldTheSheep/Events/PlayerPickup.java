package me.reider45.HoldTheSheep.Events;

import me.reider45.HoldTheSheep.Main;
import me.reider45.HoldTheSheep.Objects.Game;

import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerPickup implements Listener {
	
	@EventHandler
	public void onPickUp(PlayerInteractEntityEvent e){
		if(e.getRightClicked() instanceof Sheep){
			
			final Player p = e.getPlayer();
			final Game game = Game.getInstance();
			
			Sheep s = (Sheep)e.getRightClicked();
			
			if(game.getState() != Game.State.INGAME){
				return;
			}
			
			if(s.getUniqueId().equals(game.getSheep().getUniqueId())){
				// They clicked the game sheep
				
				if(Game.getInstance().getSheepHolder() == null){
					// Pickup sheep
					p.setPassenger(s);
					p.removePotionEffect(PotionEffectType.SPEED);
					game.setSheepHolder(p);
					
					// Start score
					new BukkitRunnable() {
						public void run(){
							
							if(game.getSheepHolder() == null){ this.cancel(); return; }
							
							if(game.getSheepHolder().getUniqueId().equals(p.getUniqueId())){
								int score = game.getScores().get(p.getUniqueId());
								
								if(score < 60){
									game.setScore(p, ++score);
								}else{
									this.cancel();
									
								}
								
							}else{
								this.cancel();
							}
						}
					}.runTaskTimer(Main.getInstance(), 20L, 20L);
					
				}
			}
			
		}
		
	}
}
