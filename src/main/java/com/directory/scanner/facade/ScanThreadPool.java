package com.directory.scanner.facade;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.directory.scanner.AppConstanst;
import com.directory.scanner.model.ConsoleCommand;
import com.directory.scanner.runnable.ScannerRunnable;

/**
 * Implementation of singleton for access to thread pool
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-05-05
 * 
 */
public class ScanThreadPool {
	
	private static ScanThreadPool instance = null;
	
	private ExecutorService threadPool;
	private Map< Integer,Future<?> > futures;
	private Map< Integer,ScannerRunnable> scanners;
	private int poolSize;
	

	/**
	 *  Private constructor
	 */
	private ScanThreadPool() {
		this.poolSize = 0;
		this.threadPool = Executors.newFixedThreadPool( AppConstanst.DEF_POOL_SIZE );
		this.futures= new HashMap< Integer, Future<?> >(); 
		this.scanners= new HashMap< Integer, ScannerRunnable >(); 
		
	}
	
	/**
	 *  Method to get instance of singleton
	 */
	public static ScanThreadPool getInstance(){
		if ( instance == null ){
			instance = new ScanThreadPool();
		}
		return instance;
	}
	
	/**
	 * @return the thread pool 
	 */
	public ExecutorService getThreadPool() {
		return threadPool;
	}

	/**
	 * @return the threads
	 */
	public Map < Integer,Future<?> > getFutures() {
		return futures;
	}
	
	/**
	 * @return the scanners
	 */
	public Map<Integer, ScannerRunnable> getScanners() {
		return scanners;
	}

	/**
	 *  Create new thread for scan command
	 *  put it in thread pool
	 *  
	 *  @param ConsoleCommand - consoleCommand
	 */
	public int createNewThread( ConsoleCommand consoleCommand ){
		poolSize++;
		if ( poolSize > AppConstanst.DEF_POOL_SIZE ){
			return -1;
		}
		ScannerRunnable scanner = new ScannerRunnable( consoleCommand );
		Future< ? > future =  threadPool.submit( scanner );
		int hashCode = future.hashCode();
		scanners.put( hashCode, scanner );
		futures.put( hashCode,future );
		return hashCode;
	}
	
	/**
	 *  Shut down thread pool
	 *  
	 */
	public void shutdownPool(  ){
		for ( int hashCode : futures.keySet() ){
			if ( !futures.get( hashCode ).isCancelled() ){
				futures.get( hashCode ).cancel( true );
				scanners.get( hashCode ).setTerminate( true );
			}
		}
		threadPool.shutdown();
	}
	
}
