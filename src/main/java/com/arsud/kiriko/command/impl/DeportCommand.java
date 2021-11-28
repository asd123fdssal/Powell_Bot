package com.arsud.kiriko.command.impl;

import java.util.List;

import com.arsud.kiriko.command.CommandContext;
import com.arsud.kiriko.command.ICommand;
import com.arsud.kiriko.main.Config;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class DeportCommand implements ICommand {

	@Override
	public void handle(CommandContext ctx) {
		final TextChannel channel = ctx.getChannel();
		final Message message = ctx.getMessage();
		final Member member = ctx.getMember();
		final List<String> args = ctx.getArgs();

		if (args.size() < 2 || message.getMentionedMembers().isEmpty()) {
			channel.sendMessage("명령어 사용이 잘못됐습니다.").queue();
			return;
		}

		final Member target = message.getMentionedMembers().get(0);

		if (!member.canInteract(target) || !member.hasPermission(Permission.VOICE_MOVE_OTHERS)) {
			channel.sendMessage("유저 이동 권한을 가지고 있지 않습니다.").queue();
			return;
		}

		final Member selfMember = ctx.getSelfMember();

		if (!selfMember.canInteract(target) || !selfMember.hasPermission(Permission.VOICE_MOVE_OTHERS)) {
			channel.sendMessage("파월봇의 권한이 낮아 유저를 이동할 수 없습니다.").queue();
			return;
		}

		final String voiceChannel = String.join(" ", args.subList(2, args.size()));
		AudioManager manager = target.getGuild().getAudioManager();

		Config.AK_MEMBER = target;
		Config.AUTO_KICK = true;
		Config.AK_CHANNEL = ctx.getGuild().getVoiceChannelsByName("", false).get(0);

		if (manager.getConnectedChannel().getName().equals(voiceChannel)) {
			manager.closeAudioConnection();
		}
	}

	@Override
	public String getName() {
		return "deport";
	}

	@Override
	public String getHelp() {
		return "유저를 음성 채널에서 추방합니다.\n" + "사용법 : `!deport <@유저> <채팅방 이름>`\n" + "예시 : `!deport @히오스 시공 좋아";
	}

}
