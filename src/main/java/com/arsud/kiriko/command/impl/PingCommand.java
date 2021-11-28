package com.arsud.kiriko.command.impl;

import com.arsud.kiriko.command.CommandContext;
import com.arsud.kiriko.command.ICommand;

import net.dv8tion.jda.api.JDA;

public class PingCommand implements ICommand {

	@Override
	public void handle(CommandContext ctx) {
		JDA jda = ctx.getJDA();

		jda.getRestPing()
				.queue((ping) -> ctx.getChannel()
						.sendMessageFormat("Rest ping 결과는 %s ms.\nGateway ping 결과는 %s ms 입니다.", ping,
								jda.getGatewayPing())
						.queue());
	}

	@Override
	public String getName() {
		return "ping";
	}

	@Override
	public String getHelp() {
		return "현재 봇과 디스코드 서버 간의 핑을 표시합니다.\n" + "사용법 : `!ping`";
	}

}
