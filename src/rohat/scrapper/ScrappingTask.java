package rohat.scrapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrappingTask implements Runnable {

	private String url;
	private static String[] prizeBondAmounts = new String[] { "premium", "40000", "25000", "15000", "7500", "1500", "750", "200", "100"};

	public ScrappingTask(String url) {
		super();
		this.url = url;
	}
	
	@Override
	public void run() {

		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByAttributeValue("rel", "noopener noreferrer");
			for (Element element : elements) {
				String[] splits = element.attr("href").split("/");
				String fileName = splits[splits.length - 1];
				System.out.println(fileName);

				boolean toDownload = true;
				
				// Loop through 1995 to 2020
				for (int i = 1995; i <= 2020; i++) {
					// if name contains year
					if (fileName.contains(i + "")) {
						// Loop through different bond types.
						for (int j = 0; j < prizeBondAmounts.length; j++) {
							if (fileName.toLowerCase().contains(prizeBondAmounts[j].toLowerCase())) {
								FileUtils.copyURLToFile(new URL(element.attr("href")), new File("/bonds/" + i + "/" + prizeBondAmounts[j] + "/" + fileName));
								toDownload = false;
								break;
							}
						}
						// if name does not contains info about bond type then toDownload will be true then download on year folder.
						if(toDownload) {
							FileUtils.copyURLToFile(new URL(element.attr("href")), new File("/bonds/" + i + "/" + fileName));
							toDownload = false;
							break;
						}
					}
				}
				// if name contains the year then toDownload will be false if not then download on main directory
				if (toDownload) {
					FileUtils.copyURLToFile(new URL(element.attr("href")), new File("/bonds/" + fileName));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
