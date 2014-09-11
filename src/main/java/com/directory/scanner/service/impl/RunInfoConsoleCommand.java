package com.directory.scanner.service.impl;

import com.directory.scanner.AppConstanst;
import com.directory.scanner.facade.ScanThreadPool;
import com.directory.scanner.model.ConsoleCommand;
import com.directory.scanner.service.RunConsoleCommand;

/**
 * Class which implements RunConsoleCommand interface to run "info" command 
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-05-04
 * 
 */
public class RunInfoConsoleCommand implements RunConsoleCommand{
	
	private static final ScanThreadPool POOL_INST = ScanThreadPool.getInstance();

	@Override
	public ConsoleCommand runCommandFromJavaConsole( String[] parsedString ) {
		ConsoleCommand command = new ConsoleCommand();
		command.setCommand( parsedString[ AppConstanst.COMMAND_IDX ] );
		int idx = 0; 
		StringBuilder sb = new StringBuilder();
		for( Integer hashCode: POOL_INST.getFutures().keySet() ){
			if ( !POOL_INST.getFutures().get(hashCode).isCancelled() ){
				sb.append(AppConstanst.THREAD_ID +hashCode+"\n");
				idx++;
			}
		}
		System.out.println( AppConstanst.RUNNING_THREADS+idx );
		System.out.println( sb.toString() );
		return command;
		
	}

}
