package me.reider45.HoldTheSheep.Events;

import me.reider45.HoldTheSheep.Main;
import me.reider45.HoldTheSheep.Objects.Game;

import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDamage implements Listener {

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e){

		if(Game.getInstance().getState() == Game.State.INGAME){
			if(e.getDamager() instanceof Player){

				if(e.getEntity() instanceof Player){
					Player hit = (Player) e.getEntity();
					// Cancel player v player damage, but still knockback
					e.setDamage(0.0);

					if(Game.getInstance().getSheepHolder() != null){
						if(Game.getInstance().getSheepHolder().getUniqueId().equals(hit.getUniqueId())){
							
							// Drop the sheep
							hit.getPassenger().eject();
							hit.getPassenger().teleport(hit.getLocation());

							// Half second delay so that the player can't re-grab instantly if sneaking
							new BukkitRunnable() {
								public void run(){
									Game.getInstance().setSheepHolder(null);
								}
							}.runTaskLater(Main.getInstance(), 10);
							
							hit.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
						}
					}

				}

				if(e.getEntity() instanceof Sheep){
					if(Game.getInstance().getSheep().getUniqueId().equals(e.getEntity().getUniqueId())){
						// Cancel hurting the sheep
						e.setCancelled(true);
					}
				}
			}
		}
	}

}
