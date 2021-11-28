package com.arsud.kiriko.command.impl;

import com.arsud.kiriko.command.CommandContext;
import com.arsud.kiriko.command.ICommand;
import com.arsud.kiriko.main.Config;

import net.dv8tion.jda.api.entities.TextChannel;

public class PardonCommand implements ICommand {

	@Override
	public void handle(CommandContext ctx) {
		Config.AUTO_KICK = false;
		Config.AK_CHANNEL = null;
		Config.AK_MEMBER = null;

		TextChannel channel = ctx.getChannel();
		channel.sendMessage("추방 기능을 해제했습니다.").queue();
	}

	@Override
	public String getName() {
		return "pardon";
	}

	@Override
	public String getHelp() {
		return "음성 채널 추방을 해제합니다.";
	}

}
