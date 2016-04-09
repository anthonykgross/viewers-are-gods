package fr.anthonykgross.event;

import java.util.HashMap;
import java.util.Map.Entry;

public class EventListener {
	public HashMap<String, EventInterface> events = new HashMap<String, EventInterface>();
	
	public void AddEvent(String event, EventInterface obj){
		this.events.put(event, obj);
	}
	
	public void fireIrcMessage(String nickname, String message){
		for(Entry<String, EventInterface> e : this.events.entrySet()){
			if(e.getKey().equals("irc-message")){
				e.getValue().onMessage(nickname, message);
			}
		}
	}
}
