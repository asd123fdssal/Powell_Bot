package com.arsud.kiriko.command;

import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandContext implements ICommandContext {

	private final GuildMessageReceivedEvent event;
	private final List<String> args;

	public CommandContext(GuildMessageReceivedEvent event, List<String> args) {
		this.event = event;
		this.args = args;
	}

	@Override
	public Guild getGuild() {
		return this.getEvent().getGuild();
	}

	@Override
	public GuildMessageReceivedEvent getEvent() {
		return this.event;
	}

	public List<String> getArgs() {
		return args;
	}

}
