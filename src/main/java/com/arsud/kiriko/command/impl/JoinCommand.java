package com.arsud.kiriko.command.impl;

import com.arsud.kiriko.command.CommandContext;
import com.arsud.kiriko.command.ICommand;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand implements ICommand {

	@Override
	public void handle(CommandContext ctx) {
		Member selfMember = ctx.getSelfMember();
		TextChannel channel = ctx.getChannel();
		GuildVoiceState selfVoiceState = selfMember.getVoiceState();

		if (selfVoiceState.inVoiceChannel()) {
			ctx.getChannel().sendMessage("이미 음성 채팅에 참가해 있습니다.").queue();
			return;
		}

		Member member = ctx.getMember();
		GuildVoiceState voiceState = member.getVoiceState();

		if (!voiceState.inVoiceChannel()) {
			ctx.getChannel().sendMessage("명령어를 입력한 사람은 음성 채팅에 참가해있어야 합니다.").queue();
			return;
		}

		AudioManager audioManager = ctx.getGuild().getAudioManager();
		VoiceChannel memberChannel = voiceState.getChannel();

		audioManager.openAudioConnection(memberChannel);
		channel.sendMessageFormat("연설을 위하여 %s에 연결합니다.", memberChannel.getName()).queue();
	}

	@Override
	public String getName() {
		return "join";
	}

	@Override
	public String getHelp() {
		return "파월 봇을 자신이 참여한 음성 채팅에 참가시킵니다.\n" + "사용법 : `!join`";
	}

}
