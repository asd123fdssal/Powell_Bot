package com.arsud.kiriko.command.impl;

import com.arsud.kiriko.command.CommandContext;
import com.arsud.kiriko.command.ICommand;
import com.arsud.kiriko.main.Config;

import net.dv8tion.jda.api.entities.User;

public class ShutdownCommand implements ICommand {

	@Override
	public void handle(CommandContext ctx) {
		User user = ctx.getMember().getUser();

		if (Config.DEVELOPER_ID.equalsIgnoreCase(user.getId())) {
			ctx.getChannel().sendMessageFormat("%s 님에 의해 종료 명령이 실행되었습니다.", ctx.getMember().getUser().getName())
					.queue();
			ctx.getJDA().shutdown();
		}
	}

	@Override
	public String getName() {
		return "shutdown";
	}

	@Override
	public String getHelp() {
		return "개발자 전용 커맨드";
	}

}
