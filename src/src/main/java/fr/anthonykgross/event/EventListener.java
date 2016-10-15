package fr.anthonykgross.event;

import java.util.HashMap;
import java.util.Map.Entry;

import org.pircbotx.hooks.events.MessageEvent;

public class EventListener {
	public HashMap<String, EventInterface> events = new HashMap<String, EventInterface>();
	
	public void AddEvent(String event, EventInterface obj){
		this.events.put(event, obj);
	}
	
	public void fireIrcMessage(MessageEvent event){
		for(Entry<String, EventInterface> e : this.events.entrySet()){
			if(e.getKey().equals("irc-message")){
				e.getValue().onMessage(event);
			}
		}
	}
}
