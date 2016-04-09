package fr.anthonykgross;

import java.util.logging.Logger;

import fr.anthonykgross.event.EventListener;

public class Service {
	private static Service service = null;
	private Logger logger;
	private Game plugin;
	private EventListener eventListener;
	
	private Service(Logger logger){
		this.logger			= logger;
		this.eventListener 	= new EventListener();
		this.plugin 		= new Game(this);
	}
	
	public static Service getInstance(Logger logger){
		if(Service.service == null){
			Service.service = new Service(logger);
		}
		return Service.service;
	}
	
	public Logger getLogger(){
		return this.logger;
	}
	public Game getPlugin(){
		return this.plugin;
	}
	public EventListener getEventListener(){
		return this.eventListener;
	}
}
