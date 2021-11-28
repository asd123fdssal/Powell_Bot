package com.arsud.kiriko.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.arsud.kiriko.command.impl.DeportCommand;
import com.arsud.kiriko.command.impl.HelpCommand;
import com.arsud.kiriko.command.impl.JoinCommand;
import com.arsud.kiriko.command.impl.KickCommand;
import com.arsud.kiriko.command.impl.PardonCommand;
import com.arsud.kiriko.command.impl.PingCommand;
import com.arsud.kiriko.command.impl.QuitCommand;
import com.arsud.kiriko.command.impl.ShutdownCommand;
import com.arsud.kiriko.command.impl.StockCommand;
import com.arsud.kiriko.main.Config;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandManager {

	private final List<ICommand> commands = new ArrayList<>();

	public CommandManager() {
		addCommand(new PingCommand());
		addCommand(new ShutdownCommand());
		addCommand(new JoinCommand());
		addCommand(new QuitCommand());
		addCommand(new HelpCommand(this));
		addCommand(new KickCommand());
		addCommand(new DeportCommand());
		addCommand(new PardonCommand());
		addCommand(new StockCommand());
	}

	private void addCommand(ICommand cmd) {
		Boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

		if (nameFound) {
			throw new IllegalArgumentException("이미 존재하는 커맨드입니다.");
		}

		commands.add(cmd);
	}

	public List<ICommand> getCommands() {
		return commands;
	}

	@NotNull
	public ICommand getCommand(String search) {
		for (ICommand cmd : this.commands) {
			if (cmd.getName().equalsIgnoreCase(search) || cmd.getAliases().contains(search)) {
				return cmd;
			}
		}
		return null;
	}

	public void handle(GuildMessageReceivedEvent event) {
		String[] split = event.getMessage().getContentRaw().replaceFirst(Config.PREFIX, "").split(" ");

		String invoke = split[0].toLowerCase();
		ICommand cmd = this.getCommand(invoke);

		if (cmd != null) {
			event.getChannel().sendTyping().queue();
			List<String> args = Arrays.asList(split).subList(1, split.length);

			CommandContext ctx = new CommandContext(event, args);

			cmd.handle(ctx);
		}
	}
}
