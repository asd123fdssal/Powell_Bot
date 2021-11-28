package com.arsud.kiriko.command.impl;

import java.util.List;

import com.arsud.kiriko.command.CommandContext;
import com.arsud.kiriko.command.CommandManager;
import com.arsud.kiriko.command.ICommand;
import com.arsud.kiriko.main.Config;

import net.dv8tion.jda.api.entities.TextChannel;

public class HelpCommand implements ICommand {

	private final CommandManager manager;

	public HelpCommand(CommandManager manager) {
		this.manager = manager;
	}

	@Override
	public void handle(CommandContext ctx) {
		List<String> args = ctx.getArgs();
		TextChannel channel = ctx.getChannel();

		if (args.isEmpty()) {
			StringBuilder builder = new StringBuilder();

			builder.append("명령어 목록!\n");
			builder.append("도움말은 `!help [명령어]`를 입력해서 볼 수 있습니다.\n");
			manager.getCommands().stream().map(ICommand::getName)
					.forEach((it) -> builder.append('`').append(Config.PREFIX).append(it).append('`').append("\n"));
			
			channel.sendMessage(builder).queue();
			return;
		}

		String search = args.get(0);
		ICommand command = manager.getCommand(search);

		if (command == null) {
			channel.sendMessageFormat("%s (은)는 존재하지 않는 커맨드 입니다.", search).queue();
			return;
		}

		channel.sendMessage(command.getHelp()).queue();
	}

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getHelp() {
		return "각 명령어별 도움말을 표시합니다.\n" + "사용법 : `!help [명령어]`";
	}

	@Override
	public List<String> getAliases() {
		return List.of("commands", "commandlist");
	}
	
}
