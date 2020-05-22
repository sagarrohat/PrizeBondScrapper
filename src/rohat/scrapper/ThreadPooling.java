package rohat.scrapper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPooling {

	private static ThreadPooling instance;
	
	private ExecutorService executorService;
	
	// Maximum number of threads in thread pool 
    static final int MAX_THREADS = 3;
    
	private ThreadPooling() {
		// creates a thread pool with MAX_T no. of  
        // threads as the fixed pool size
        executorService = Executors.newFixedThreadPool(MAX_THREADS); 
	}

	public static ThreadPooling getInstance() {
    	if(instance == null) {
    		instance = new ThreadPooling();
    	}
    	return instance;
    }
	
	public void execute(Runnable runnable) {
		executorService.execute(runnable);
	}
	
	public void shutdown() {
    	executorService.shutdown();
    }

}
