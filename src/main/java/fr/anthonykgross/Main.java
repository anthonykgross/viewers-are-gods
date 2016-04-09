package fr.anthonykgross;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import fr.anthonykgross.bot.Bot;

public class Main extends JavaPlugin{
	
	private Bot bot;
	private Service service = null;
	
	public void onEnable(){
		this.service = Service.getInstance(getLogger());
		
		this.saveDefaultConfig();
		this.service.getLogger().info("Hello twitch");
		
		this.bot = new Bot(
				this.getConfig().getString("irc.url"),
				this.getConfig().getString("irc.login"),
				this.getConfig().getString("irc.pass"),
				this.getConfig().getString("irc.port"),
				this.getConfig().getString("irc.channel")
		);
		this.bot.start();
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
            	
            	Service service 	= Service.getInstance(null);
            	Game g 				= service.getPlugin();
           	
            	Iterator <Entry<String, String>> it = g.getMessage().entrySet().iterator();
                while (it.hasNext()) {
                	Entry<String, String> e = it.next();
                	g.sendToRouter(e);
                	it.remove();
                }
            }
        }, 0L, 20L);
	}
	
}
