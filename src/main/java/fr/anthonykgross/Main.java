package fr.anthonykgross;

import java.util.Iterator;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.pircbotx.hooks.events.MessageEvent;

import fr.anthonykgross.bot.Bot;

public class Main extends JavaPlugin{
	
	private Bot bot;
	private Service service = null;
	
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(new GameListener(), this);
		
		
		this.service = Service.getInstance().setLogger(getLogger()).setConfig(this.getConfig());
		
		this.saveDefaultConfig();		
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
            	Service service 	= Service.getInstance();
            	Game g 				= service.getGame();
           	
            	Iterator <MessageEvent> it = g.getMessages().iterator();
                while (it.hasNext()) {
                	MessageEvent e = it.next();
                	g.sendToRouter(e);
                	it.remove();
                }
            }
        }, 0L, 20L);
	}
	
	
	
}
