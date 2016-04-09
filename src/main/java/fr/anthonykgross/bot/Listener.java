package fr.anthonykgross.bot;

import org.bukkit.Bukkit;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectAttemptFailedEvent;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;
import fr.anthonykgross.Service;

public class Listener extends ListenerAdapter{
	private Service service 	= null;
	
	public Listener(){
		this.service 	= Service.getInstance(null);
	}
	
	public void onMessage(MessageEvent event) throws Exception {
		this.service.getEventListener().fireIrcMessage(event.getUser().getNick(), event.getMessage().toString());
	}
	
	public void onConnectAttemptFailed(ConnectAttemptFailedEvent event) throws Exception {
		this.service.getLogger().info(event.toString());
	}
	
	public void onConnect(ConnectEvent event) throws Exception {
		this.service.getLogger().info(event.toString());
	}
	public void onDisconnect(ConnectEvent event) throws Exception {
		this.service.getLogger().info(event.toString());
	}
}