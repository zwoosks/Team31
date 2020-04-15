package me.zwoosks.team31;

import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class App {
	
	public static JDA jda;
	
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws LoginException {
        
    	jda = new JDABuilder(AccountType.BOT).setToken("TOKEN").buildAsync();
    	jda.getPresence().setStatus(OnlineStatus.ONLINE);
    	jda.getPresence().setGame(Game.watching(Jugadors.getJugadors()));
    	jda.addEventListener(new Commands());
    	
    	
    	// Update de la gent al servidor
    	Timer timer = new Timer(); 
    	timer.schedule( new TimerTask() 
    	{ 
    	    public void run() {
    	    	String text = Jugadors.getJugadors();
    	    	jda.getPresence().setGame(Game.watching(text));
    	    } 
    	}, 0, 60*(1000*1));
    	
    }
    
    public static JDA getJDA() {
    	return jda;
    }

}
