package com.arsud.kiriko.command.impl;

import java.util.HashMap;

import com.arsud.kiriko.command.CommandContext;
import com.arsud.kiriko.command.ICommand;
import com.arsud.kiriko.main.Config;
import com.arsud.kiriko.util.StockCrawler;

import net.dv8tion.jda.api.entities.TextChannel;

public class StockCommand implements ICommand {

	@Override
	public void handle(CommandContext ctx) {
		TextChannel channel = ctx.getChannel();
		channel.sendMessage(stockAll(ctx.getArgs().get(0))).queue();
	}

	@Override
	public String getName() {
		return "stock";
	}

	@Override
	public String getHelp() {
		return "주식 정보를 표시합니다.\n" + "사용법 : `!stock <tikcer>`";
	}

	private String stockAll(String ticker) {
		try {
			HashMap<String, String> map = StockCrawler.getStockcrawler().runCrawling(ticker);
			if (map != null) {
				String answer = "```";

				for (int i = 0; i < Config.STOCK_HEADERS.length; i++) {
					answer += Config.STOCK_HEADER.get(Config.STOCK_HEADERS[i]) + " : "
							+ map.get(Config.STOCK_HEADERS[i]) + "\r\n";
				}

				answer += "```";
				return answer;
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "";
	}

}
