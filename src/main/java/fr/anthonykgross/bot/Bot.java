package fr.anthonykgross.bot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;

import fr.anthonykgross.Service;


public class Bot extends Thread {
	
	private String url;
	private String login;
	private String pass;
	private String port;
	private String channel;
	private Service service = null;
	
	public Bot(String url, String login, String pass, String port, String channel) {
		this.url 		= url;
		this.channel	= channel;
		this.port 		= port;
		this.login 		= login;
		this.pass 		= pass;
		this.service    = Service.getInstance(null);
    }
	
    
	public void run(){
		this.service.getLogger().info("Thread run");
		//Configure what we want our bot to do
        Configuration configuration = new Configuration.Builder()
		        		.setAutoNickChange(false) //Twitch doesn't support multiple users
		        	    .setOnJoinWhoEnabled(false) //Twitch doesn't support WHO command
		        	    .setCapEnabled(true)
		        	    .addCapHandler(new EnableCapHandler("twitch.tv/membership")) //Twitch by default doesn't send JOIN, PART, and NAMES unless you request it, see https://github.com/justintv/Twitch-API/blob/master/IRC.md#membership
		        	    .addServer(this.url, Integer.parseInt(this.port))
		        	    .setName(this.login)
		        	    .setServerPassword(this.pass)
		        	    .addAutoJoinChannel(this.channel)
                        .addListener(new Listener())
                        .buildConfiguration();

        //Create our bot with the configuration
        PircBotX bot = new PircBotX(configuration);
        //Connect to the server
        try {
			bot.startBot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.service.getLogger().info(e.getMessage());
		}
	}
}
