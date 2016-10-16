package fr.anthonykgross;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.pircbotx.hooks.events.MessageEvent;

import fr.anthonykgross.event.EventInterface;

public class Game implements EventInterface{

	private ArrayList<MessageEvent> messages 			= new ArrayList<MessageEvent>();
	private HashMap<String, Double> polls			    = new HashMap<String, Double>();

	/**
	 * 
	 * @param entity
	 */
	public void addEntity(EntityType entity){
		Player p 		= Bukkit.getServer().getPlayer(Service.getInstance().getConfig().getString("config.player_to_focus"));
		
		if(p != null){
			World w 		= p.getLocation().getWorld();
			Location loc 	= p.getLocation();
			w.spawnEntity(loc, entity);
		}
	}

	/**
	 * 
	 */
	public void onMessage(MessageEvent event) {
		this.messages.add(event);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<MessageEvent> getMessages(){
		return this.messages;
	}
	
	/**
	 * 
	 * @param message
	 */
	public void sendToRouter(MessageEvent message){
		if(message.getMessage().equals("?poll")){
			Double nb_polls_to_summon 		= Service.getInstance().getConfig().getDouble("config.nb_polls_to_summon");
			String m 						= "Polls => ";
			
			Iterator <Entry<String, Double>> it = this.polls.entrySet().iterator();
	        while (it.hasNext()) {
	        	Entry<String, Double> e = it.next();
	        	m += e.getKey()+"("+(e.getValue()/nb_polls_to_summon*100)+") ";
	        }
	        Service.getInstance().getBot().send().message(message.getChannelSource(), m);
		}
		else if(message.getMessage().equals("?thanks")){
			String m 						= "Thanks to AnthonyKGROSS, Hugo4715, LoockysTwtch, F1redev & JustOneGamerzz";
	        Service.getInstance().getBot().send().message(message.getChannelSource(), m);
		}
		else{
			if(message.getMessage().startsWith("?")){
				String m 				= message.getMessage();
				String cmd 				= m.substring(1, m.length());
				
				try{
					EntityType.valueOf(cmd.toUpperCase());
					this.addPoll(cmd.toUpperCase());
				}
				catch(Exception e){
					Service.getInstance().getLogger().info(message.getUser().getNick()+" : "+message.getMessage());
					Service.getInstance().getLogger().info(e.getMessage());
				}
			}
			else{
				Bukkit.broadcastMessage(message.getUser().getNick()+" : "+message.getMessage().replace("&", "§"));
			}
		}
	}
	
	/**
	 * 
	 * @param cmd
	 */
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
        		Double nb_polls_to_summon 		= Service.getInstance().getConfig().getDouble("config.nb_polls_to_summon");
        		Double show_caution_at_percent 	= Service.getInstance().getConfig().getDouble("config.show_caution_at_percent");
        		String message_caution 			= Service.getInstance().getConfig().getString("config.message_caution");
        		String message_summon 			= Service.getInstance().getConfig().getString("config.message_summon");
        		Double percent 					= (e.getValue()/nb_polls_to_summon*100);
        		
	        	if(percent >= show_caution_at_percent){
	        		Bukkit.broadcastMessage(message_caution.replace("%entity%", e.getKey()).replace("%percent%", String.valueOf(percent)));
	        	}
	        	if(e.getValue() >= nb_polls_to_summon){
	        		Bukkit.broadcastMessage(message_summon.replace("%entity%", e.getKey()));
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
