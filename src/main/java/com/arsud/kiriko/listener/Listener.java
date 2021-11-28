package com.arsud.kiriko.listener;

import org.jetbrains.annotations.NotNull;

import com.arsud.kiriko.command.CommandManager;
import com.arsud.kiriko.main.Config;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {

	private final CommandManager manager = new CommandManager();

	@Override
	public void onReady(@NotNull ReadyEvent event) {
		System.out.printf("%s is on Ready!", event.getJDA().getSelfUser().getName());
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		User user = event.getAuthor();

		if (user.isBot() || event.isWebhookMessage()) {
			return;
		}

		String raw = event.getMessage().getContentRaw();

		if (raw.startsWith(Config.PREFIX)) {
			manager.handle(event);
		}
	}

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		TextChannel channel = event.getGuild().getTextChannels().get(0);
		String message = String.format("%s에 어서오세요 %s님", event.getGuild().getName(),
				event.getMember().getUser().getAsTag());
		channel.sendMessage(message).queue();
	}

}
