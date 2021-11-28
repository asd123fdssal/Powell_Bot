package com.arsud.kiriko.main;

import java.util.HashMap;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class Config {

	public static final String PREFIX = "!";
	public static final String DEVELOPER_ID = "363291379419316234";

	public static Boolean AUTO_KICK = false;
	public static Member AK_MEMBER = null;
	public static VoiceChannel AK_CHANNEL = null;

	// POWELL BOT
	public static final String TOKEN = "OTE0NDY0OTAyMzM5NTAyMDgw.YaNbzg.Yp1VDzpoUG3zUH7xn8YfLBnysho";
	public static final String STATE = "Work for USA";

	/*
	 * // KIRIKO BOT public static final String TOKEN =
	 * "OTEzMzI0MzA1MjU1OTE5NjM2.YZ81iw.6slzUP7Jg3NwcIrzhFY0cUF0l34"; public static
	 * final String STATE = "비주얼 레슨";
	 */

	// Stock

	public final static HashMap<String, String> STOCK_HEADER = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -378097031231269720L;

		{
			put("name", "종목명");
			put("Price", "현재가");
			put("Change", "등락율");
			put("RSI (14)", "RSI (14)");
			put("Prev Close", "종가");
			put("Market Cap", "시가총액");
			put("EPS (ttm)", "EPS");
			put("P/E", "PER");
			put("P/S", "PSR");
			put("PEG", "PEG");
			put("ROE", "ROE");
			put("Profit Margin", "순이익률");
			put("Debt/Eq", "부채비율");
			put("Earnings", "실적발표일");
			put("52W Range", "52주");
			put("Dividend %", "배당률");
			put("Short Float", "공매도 비율");
			put("Insider Own", "내부자 비율");
			put("Inst Own", "기관 비율");
		}
	};

	public final static String[] STOCK_HEADERS = { "name", "Price", "Change", "RSI (14)", "Prev Close", "Market Cap",
			"EPS (ttm)", "P/E", "P/S", "PEG", "ROE", "Profit Margin", "Debt/Eq", "Earnings", "52W Range", "Dividend %",
			"Short Float", "Insider Own", "Inst Own" };
}
