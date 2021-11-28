package com.arsud.kiriko.main;

import javax.security.auth.login.LoginException;

import com.arsud.kiriko.listener.Listener;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class KIRIKO {

	public KIRIKO() {
		/*JDABuilder builder = JDABuilder.createDefault(Config.TOKEN, GatewayIntent.GUILD_MEMBERS,
				GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_EMOJIS);

		builder.disableCache(EnumSet.of(CacheFlag.CLIENT_STATUS, CacheFlag.ACTIVITY, CacheFlag.EMOTE));
		builder.enableCache(CacheFlag.VOICE_STATE);*/
		try {
			JDABuilder builder = JDABuilder.createDefault(Config.TOKEN);
			//builder.setToken(Config.TOKEN);
			builder.setAutoReconnect(true);
			builder.addEventListeners(new Listener());
			builder.setActivity(Activity.playing(Config.STATE));
			builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
			builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new KIRIKO();
	}

}
