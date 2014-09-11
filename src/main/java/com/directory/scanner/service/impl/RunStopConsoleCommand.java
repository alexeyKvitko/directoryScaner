package com.directory.scanner.service.impl;

import java.util.concurrent.Future;

import com.directory.scanner.AppConstanst;
import com.directory.scanner.facade.ScanThreadPool;
import com.directory.scanner.model.ConsoleCommand;
import com.directory.scanner.service.RunConsoleCommand;

/**
 * Class which implements RunConsoleCommand interface to run "stop" command 
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-05-04
 * 
 */
public class RunStopConsoleCommand implements RunConsoleCommand{
	
	private static final ScanThreadPool POOL_INST = ScanThreadPool.getInstance();

	@Override
	public ConsoleCommand runCommandFromJavaConsole( String[] parsedString ) {
		ConsoleCommand command = mapConsoleCommand( parsedString );
		StringBuilder errorSB = new StringBuilder();
		Future< ? > future = POOL_INST.getFutures().get( command.getThreadId() );
		if ( future != null ){
			future.cancel( true );
			POOL_INST.getScanners().get( command.getThreadId() ).setTerminate( true );
		} else {
			String value = parsedString.length < 2 ? AppConstanst.UNKNOWN : parsedString[ AppConstanst.THREAD_ID_IDX ];  
			errorSB.append( AppConstanst.INCORECT_THREAD_ID +value+AppConstanst.POSITIVE_INT+"\n" );
		}
		if ( errorSB.length() > 0 ){
			System.out.println( AppConstanst.ERROR_IN_STOP_COMMAND+errorSB.toString() );
			command.setCommand( AppConstanst.UNKNOWN);
		}
		
		return command;
	}
	
	/**
	 * Map from command from parsed Strign[] to ConsoleCommand model
	 * 
	 * @param ConsoleCommand consoleCommand - model for console command
	 * @param String[] parsedCommand - Array of string
	 */
	private ConsoleCommand mapConsoleCommand( String[] parsedString ){
		ConsoleCommand consoleCommand = new ConsoleCommand();
		consoleCommand.setCommand( parsedString[ AppConstanst.COMMAND_IDX ] );
		Integer threadId= null;
		try{
			if ( parsedString.length >= 2 ){
				threadId = Integer.parseInt( parsedString[ AppConstanst.THREAD_ID_IDX ] );
			}
		} catch (NumberFormatException e) {} 
		consoleCommand.setThreadId( threadId );
		return consoleCommand;
	}

}
