package fr.anthonykgross;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.pircbotx.PircBotX;

import fr.anthonykgross.event.EventListener;

public class Service {
	private static Service service 		= null;
	private Logger logger				= null;
	private Game game 					= null;
	private PircBotX bot 				= null;
	private EventListener eventListener = null;
	private FileConfiguration config 	= null;
	
	private Service(){
		this.eventListener 	= new EventListener();
		this.game 			= new Game();
		this.eventListener.AddEvent("irc-message", this.game);
	}
	
	public static Service getInstance(){
		if(Service.service == null){
			Service.service = new Service();
		}
		return Service.service;
	}
	
	public Service setLogger(Logger logger){
		this.logger = logger;
		return this;
	}
	public Service setBot(PircBotX bot){
		this.bot = bot;
		return this;
	}
	public Service setConfig(FileConfiguration config){
		this.config = config;
		return this;
	}
	
	public PircBotX getBot(){
		return this.bot;
	}
	public Logger getLogger(){
		return this.logger;
	}
	public Game getGame(){
		return this.game;
	}
	public FileConfiguration getConfig(){
		return this.config;
	}
	public EventListener getEventListener(){
		return this.eventListener;
	}
}
