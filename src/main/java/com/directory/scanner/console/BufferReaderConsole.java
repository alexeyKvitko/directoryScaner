package com.directory.scanner.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.directory.scanner.AppConstanst;
import com.directory.scanner.model.ConsoleCommand;
import com.directory.scanner.service.ParseCommandService;

/**
 * Class read input from console (keyboard) used java.io.BufferedReader
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-04-29
 * 
 */
public class BufferReaderConsole {
	
	private static final Logger LOGGER = LogManager.getLogger( BufferReaderConsole.class.getName() );

	public void runBufferReader() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ParseCommandService parseCommandSrv = new ParseCommandService();
		String command = null;
		while ( !AppConstanst.EXIT.equals( command ) ){
			System.out.print( AppConstanst.CONSOLE );
			String input = null;
			try {
				input = reader.readLine();
				ConsoleCommand consoleCommand = parseCommandSrv.parseInputFromConsole( input );
				command = consoleCommand.getCommand();
			} catch (IOException e) {
				LOGGER.error(  AppConstanst.CONSOLE_INPUT_ERROR  );
			}
		}
	}

}
