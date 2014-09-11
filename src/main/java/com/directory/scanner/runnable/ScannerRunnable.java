package com.directory.scanner.runnable;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import com.directory.scanner.AppConstanst;
import com.directory.scanner.Launcher;
import com.directory.scanner.model.ConsoleCommand;
import com.directory.scanner.service.FileSystemService;

/**
 * Implements Runnable for copying files
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-05-05
 * 
 */

public class ScannerRunnable implements Runnable {
	
	private static final Logger LOGGER = LogManager.getLogger( Launcher.class.getName() );
	
	private ConsoleCommand consoleCommand;
	private boolean terminate;
	
	public ScannerRunnable( ConsoleCommand consoleCommand ){
		this.consoleCommand = consoleCommand;
		this.terminate =  false;
	}

	@Override
	public void run() {
		
		ThreadContext.put( AppConstanst.CTX_KEY, consoleCommand.getThreadId().toString() );
		int iterate = 1;
		try{
			while ( !isTerminate() ) {
				LOGGER.info( AppConstanst.ITERATION+iterate );
				iterate++;
				FileSystemService fileSysSrv = new FileSystemService();
				List< File > directories = fileSysSrv.getDirectories( consoleCommand.getInputDirValue(), consoleCommand.isIncludeSubsValue(), false );
					LOGGER.info( AppConstanst.SOURCE_DIRS+directories.size() );
					Map< File, File > filesToCopy = fileSysSrv.getFilesToCopy( consoleCommand.getInputDirValue(),consoleCommand.getOutPutDirValue(), consoleCommand.getMaskValue() );
					LOGGER.info( AppConstanst.SOURCE_FILES_TO_COPY+filesToCopy.size() );
					for ( File sourceFile : filesToCopy.keySet() ){
						String result = fileSysSrv.copyFile( sourceFile, filesToCopy.get( sourceFile ) );
						LOGGER.info( result );
					}
				 	if ( consoleCommand.getAutoDelete() != null && consoleCommand.isAutoDeleteValue() ) {
				 		fileSysSrv.deleteFilesAndDirectories( consoleCommand.getInputDirValue() );
				 	} 
				Thread.sleep( consoleCommand.getWaitIntervalValue() );
			}
		} catch ( InterruptedException e ){
		}
		
		ThreadContext.clear();
		
	}

	/**
	 * @return the terminate
	 */
	public boolean isTerminate() {
		return terminate;
	}

	/**
	 * @param terminate the terminate to set
	 */
	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}

}
