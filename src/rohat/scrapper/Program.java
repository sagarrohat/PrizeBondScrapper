package rohat.scrapper;

public class Program {

	public static void main(String[] args) {
		
		String[] urls = new String[] {
			"http://savings.gov.pk/rs-15000-draws/",
			"http://savings.gov.pk/rs-40000-premium-bonds-draws/",
			"http://savings.gov.pk/rs-40000-draws/",
			"http://savings.gov.pk/rs-25000-draws/",
			"http://savings.gov.pk/rs-7500-draws/",
			"http://savings.gov.pk/rs-1500-draws/",
			"http://savings.gov.pk/rs-750-draws/",
			"http://savings.gov.pk/rs-200-draws/",
			"http://savings.gov.pk/rs-100-draws/"
		};
		
		Scrapper scrapper = new Scrapper(urls);
		scrapper.run();
		
		ThreadPooling.getInstance().shutdown();
	}
	
}
