package com.directory.scanner.service.impl;

import com.directory.scanner.AppConstanst;
import com.directory.scanner.facade.ScanThreadPool;
import com.directory.scanner.model.ConsoleCommand;
import com.directory.scanner.service.RunConsoleCommand;

/**
 * Class which implements RunConsoleCommand interface to run "help" command 
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-05-04
 * 
 */
public class RunExitConsoleCommand implements RunConsoleCommand{
	
	private static final ScanThreadPool POOL_INST = ScanThreadPool.getInstance();

	@Override
	public ConsoleCommand runCommandFromJavaConsole( String[] parsedString ) {
		ConsoleCommand command = new ConsoleCommand();
		command.setCommand( parsedString[ AppConstanst.COMMAND_IDX ] ); 
		POOL_INST.shutdownPool();
		return command;
	}

}
