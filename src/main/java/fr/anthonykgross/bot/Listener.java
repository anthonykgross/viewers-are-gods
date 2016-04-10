package fr.anthonykgross.bot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectAttemptFailedEvent;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;
import fr.anthonykgross.Service;

public class Listener extends ListenerAdapter{
	private Service service 	= Service.getInstance();
	
	public void onMessage(MessageEvent event) throws Exception {
		this.service.getEventListener().fireIrcMessage(event);
	}
	
	public void onConnect(ConnectEvent event) throws Exception {
		this.service.getLogger().info("Twitch bot : Connected !");
	}
	public void onDisconnect(ConnectEvent event) throws Exception {
		this.service.getLogger().info("Twitch bot : Disconnected !");
	}
}