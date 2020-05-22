package rohat.scrapper;

public class Scrapper {

    private String[] urls;
	
	public Scrapper(String[] urls) {
		this.urls = urls;
	}

	public void run() {
		for(String url : urls) {
			Runnable runnable = new ScrappingTask(url);
			ThreadPooling.getInstance().execute(runnable);
		}		
	}
	
}
