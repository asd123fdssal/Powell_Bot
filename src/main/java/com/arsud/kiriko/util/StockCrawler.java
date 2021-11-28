package com.arsud.kiriko.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class StockCrawler {

	private static StockCrawler stockCrawler;

	private Document html = null;
	private HashMap<String, String> map;

	public StockCrawler() {

	}

	public Document getHtml(String url) throws Exception {
		try {
			Connection conn = Jsoup.connect(url);
			html = conn.get();
			return html;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String parseData() {
		if (html != null) {
			map = new HashMap<String, String>();

			Element elem = html.getElementsByClass("fullview-title").get(0);
			elem = elem.getElementsByClass("tab-link").get(0);
			map.put("name", elem.child(0).toString().replace("<b>", "").replace("</b>", ""));

			List<String> header = html.getElementsByClass("snapshot-td2-cp").eachText();
			List<String> value = html.getElementsByClass("snapshot-td2").eachText();

			for (int i = 0; i < header.size(); i++) {
				map.put(header.get(i), value.get(i));
			}
		}
		return "";
	}

	public HashMap<String, String> getMap() {
		return map;
	}

	public HashMap<String, String> runCrawling(String ticker) throws Exception {
		map = null;
		html = null;

		String url = "https://finviz.com/quote.ashx?t=" + ticker.toLowerCase();
		getHtml(url);
		parseData();
		return map;
	}

	public static StockCrawler getStockcrawler() {
		if (stockCrawler == null) {
			stockCrawler = new StockCrawler();
		}
		return stockCrawler;
	}

}
