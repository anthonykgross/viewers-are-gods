package fr.anthonykgross;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.anthonykgross.event.EventInterface;

public class Game implements EventInterface{
	
	public final static Double NB_POLL_PER_MONSTER		= 5.0;
	
	private Service service 							= null;
	private HashMap<String, String> message 			= new HashMap<String,String>();
	private HashMap<String, Double> polls			    = new HashMap<String, Double>();
	
	public Game(Service service){
		this.service = service;
		this.service.getEventListener().AddEvent("irc-message", this);
	}
	
	public void addEntity(EntityType entity){
		Player p 		= Bukkit.getServer().getPlayer("kkuet12");
		
		if(p != null){
			World w 		= p.getLocation().getWorld();
			Location loc 	= p.getLocation();
			w.spawnEntity(loc, entity);
		}
	}

	public void onMessage(String nickname, String message) {
		this.message.put(nickname, message);
	}
	
	public HashMap<String, String> getMessage(){
		return this.message;
	}
	
	public void sendToRouter(Entry<String, String> ircmessage){
		if(ircmessage.getValue().startsWith("?")){
			String m 				= ircmessage.getValue();
			String cmd 				= m.substring(1, m.length());
			
			try{
				EntityType.valueOf(cmd.toUpperCase());
				this.addPoll(cmd.toUpperCase());
			}
			catch(Exception e){
				service.getLogger().info(ircmessage.getKey()+" : "+ircmessage.getValue());
				service.getLogger().info(e.getMessage());
			}
		}
		else{
			Bukkit.broadcastMessage(ircmessage.getKey()+" : "+ircmessage.getValue());
		}
	}
	
	private void addPoll(String cmd){
		if(this.polls.containsKey(cmd)){
			Double value = this.polls.get(cmd);
			this.polls.remove(cmd);
			this.polls.put(cmd, value+1);
		}
		else{
			this.polls.put(cmd, 1.);
		}
		
		Boolean reset = false;
		Iterator <Entry<String, Double>> it = this.polls.entrySet().iterator();
        while (it.hasNext()) {
        	Entry<String, Double> e = it.next();
        	
        	if(!reset){
	        	if(e.getValue() >= (Game.NB_POLL_PER_MONSTER/2)){
	        		Bukkit.broadcastMessage("BOT : "+e.getKey()+"("+(e.getValue()/Game.NB_POLL_PER_MONSTER*100)+"%)");
	        	}
	        	if(e.getValue() >= Game.NB_POLL_PER_MONSTER){
	        		Bukkit.broadcastMessage("BOT : POKEMON GO ! "+e.getKey() +" attacks !");
	        		reset = true;
	        		try{
	        			this.addEntity(EntityType.valueOf(e.getKey().toUpperCase()));
	        		}
	        		catch(Exception ex){
	        			ex.printStackTrace();
	        		}
	        	}
        	}
        	if(reset){
        		it.remove();
        	}
        }
	}
}
