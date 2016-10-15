package fr.anthonykgross;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GameListener implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = (Player)e.getEntity();
		Service.getInstance().getBot().send().message(Service.getInstance().getConfig().getString("irc.channel"), e.getDeathMessage());
	}
}
