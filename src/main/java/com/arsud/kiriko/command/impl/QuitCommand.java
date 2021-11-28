package com.arsud.kiriko.command.impl;

import com.arsud.kiriko.command.CommandContext;
import com.arsud.kiriko.command.ICommand;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class QuitCommand implements ICommand {

	@Override
	public void handle(CommandContext ctx) {
		Member selfMember = ctx.getSelfMember();
		TextChannel channel = ctx.getChannel();
		GuildVoiceState selfVoiceState = selfMember.getVoiceState();

		if (!selfVoiceState.inVoiceChannel()) {
			ctx.getChannel().sendMessage("파월 봇이 음성 채팅에 참여해있지 않습니다.").queue();
			return;
		}

		AudioManager audioManager = ctx.getGuild().getAudioManager();
		audioManager.closeAudioConnection();

		channel.sendMessage("인플레는 일시적이다.").queue();
	}

	@Override
	public String getName() {
		return "quit";
	}

	@Override
	public String getHelp() {
		return "파월 봇을 음성 채널에서 퇴장시킵니다.\n" + "사용법 : `!quit`";
	}

}
