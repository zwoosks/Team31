package me.zwoosks.team31;

import java.awt.Color;
import java.time.Instant;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	
	private static TextChannel tc = null;
	private static Member autor = null;
	private static String titol = null;
	private static String missatge = null;
	private static boolean actiu = false;
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String msg = e.getMessage().getContentRaw();
		String[] args = msg.split(" ");
		
		if(e.getChannel().getId().equals("684431965499293750") && !e.getAuthor().isBot()) {
			if(actiu) {
				if(tc == null) {
					try {
						tc = e.getMessage().getMentionedChannels().get(0);
						autor = e.getMember();
						e.getChannel().sendMessage(indicaTitol().build()).queue();
					} catch(Exception ex) {
						e.getChannel().sendMessage(errorCanal().build()).queue();
					}
				} else if(titol == null) {
					String missatge = e.getMessage().getContentRaw();
					if(e.getMessage().getContentRaw().length() <= 256) {
						titol = missatge;
						e.getChannel().sendMessage(indicaMissatge().build()).queue();
					} else {
						e.getChannel().sendMessage(errorTitol().build()).queue();
					}
				} else if(missatge == null) {
					missatge = e.getMessage().getContentRaw();
					tc.sendMessage(anunci(autor, titol, missatge).build()).queue();
					tc = null;
					autor = null;
					titol = null;
					missatge = null;
					actiu = false;
					e.getChannel().sendMessage(success().build()).queue();
				}
			}
		}
		
		if(args[0].equalsIgnoreCase("!anunci")) {
			if(e.getChannel().getId().equals("684431965499293750") && !e.getAuthor().isBot()) {
				
				if(tc == null) {
					actiu = true;
					e.getChannel().sendMessage(indicaCanal().build()).queue();
				}
				
			}
		}
	}
	
	private EmbedBuilder anunci(Member autor, String titol, String missatge) {
		 EmbedBuilder eb = new EmbedBuilder();
		 eb.setColor(new Color(255, 168, 5));
		 eb.setAuthor(autor.getEffectiveName(), "https://team31.cat/", autor.getUser().getAvatarUrl());
		 eb.setTitle(titol);
		 eb.setDescription(missatge);
		 eb.setTimestamp(Instant.now());
		 eb.setFooter("Team31 Bot • team31.cat", "https://team31.cat/img/icona-team31.png");
		 return eb;
	}
	
	private EmbedBuilder indicaMissatge() {
		 EmbedBuilder eb = new EmbedBuilder();
		 eb.setColor(new Color(255, 168, 5));
		 eb.setTitle("Anunci");
		 eb.setDescription("Indica missatge de l'anunci");
		 eb.setTimestamp(Instant.now());
		 eb.setFooter("Team31 Bot • team31.cat", "https://team31.cat/img/icona-team31.png");
		 return eb;
	}
	
	private EmbedBuilder indicaTitol() {
		 EmbedBuilder eb = new EmbedBuilder();
		 eb.setColor(new Color(255, 168, 5));
		 eb.setTitle("Anunci");
		 eb.setDescription("Indica el títol de l'anunci");
		 eb.setTimestamp(Instant.now());
		 eb.setFooter("Team31 Bot • team31.cat", "https://team31.cat/img/icona-team31.png");
		 return eb;
	}
	
	private EmbedBuilder indicaCanal() {
		 EmbedBuilder eb = new EmbedBuilder();
		 eb.setColor(new Color(255, 168, 5));
		 eb.setTitle("Anunci");
		 eb.setDescription("Indica on vols publicar l'anunci (#canal)");
		 eb.setTimestamp(Instant.now());
		 eb.setFooter("Team31 Bot • team31.cat", "https://team31.cat/img/icona-team31.png");
		 return eb;
	}
	
	private EmbedBuilder errorCanal() {
		 EmbedBuilder eb = new EmbedBuilder();
		 eb.setColor(Color.red);
		 eb.setTitle("Error");
		 eb.setDescription("Indica un canal vàlid (#canal)");
		 eb.setTimestamp(Instant.now());
		 eb.setFooter("Team31 Bot • team31.cat", "https://team31.cat/img/icona-team31.png");
		 return eb;
	}
	
	private EmbedBuilder errorTitol() {
		 EmbedBuilder eb = new EmbedBuilder();
		 eb.setColor(Color.red);
		 eb.setTitle("Error");
		 eb.setDescription("El títol no pot tenir més de 256 caràcters");
		 eb.setTimestamp(Instant.now());
		 eb.setFooter("Team31 Bot • team31.cat", "https://team31.cat/img/icona-team31.png");
		 return eb;
	}
	
	private EmbedBuilder success() {
		 EmbedBuilder eb = new EmbedBuilder();
		 eb.setColor(Color.green);
		 eb.setTitle("Missatge enviat");
		 eb.setDescription("S'ha enviat correctament l'anunci al canal indicat.");
		 eb.setTimestamp(Instant.now());
		 eb.setFooter("Team31 Bot • team31.cat", "https://team31.cat/img/icona-team31.png");
		 return eb;
	}
	
}