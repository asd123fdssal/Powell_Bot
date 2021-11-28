package com.arsud.kiriko.command.impl;

import java.util.List;

import com.arsud.kiriko.command.CommandContext;
import com.arsud.kiriko.command.ICommand;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class KickCommand implements ICommand {

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

		if (!member.canInteract(target) || !member.hasPermission(Permission.KICK_MEMBERS)) {
			channel.sendMessage("명령 입력자의 권한이 낮습니다.").queue();
			return;
		}

		final Member selfMember = ctx.getSelfMember();

		if (!selfMember.canInteract(target) || !selfMember.hasPermission(Permission.KICK_MEMBERS)) {
			channel.sendMessage("파월봇의 권한이 낮아 유저를 추방할 수 없습니다.").queue();
			return;
		}

		final String reason = String.join(" ", args.subList(1, args.size()));
		ctx.getGuild().kick(target, reason).reason(reason).queue((it) -> channel.sendMessage("추방 되었습니다.").queue(),
				(error) -> channel.sendMessageFormat("추방에 실패했습니다.\n%s", error.getMessage()).queue());
	}

	@Override
	public String getName() {
		return "kick";
	}

	@Override
	public String getHelp() {
		return "유저를 서버에서 추방합니다.\n" + "사용법 : `!kick <@유저> <사유>`\n" + "예시 : `!kick @히오스 리그오브레전드 플레이`";
	}

}
