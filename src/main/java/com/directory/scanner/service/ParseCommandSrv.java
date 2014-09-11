/**
 * 
 */
package com.directory.scanner.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.directory.scanner.AppConstanst;
import com.directory.scanner.model.ConsoleCommand;

/**
 * Class to parsing string,  from console
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-04-30
 * 
 */
public class ParseCommandSrv {
	
	private static final Logger LOGGER = LogManager.getLogger( ParseCommandSrv.class.getName() );
	
	/**
	 * Parse input string from console
	 * 
	 * @param input -  string which will be parse
	 * @return ConsoleCommand
	 */
	public ConsoleCommand parseInputFromConsole( String input ){
		String[] parsedString = input.split( AppConstanst.SPACE );
		ConsoleCommand consoleCommand = new ConsoleCommand();
		consoleCommand.setCommand( AppConstanst.UNKNOWN );
		if ( AppConstanst.AVAILABLE_COMMANDS.get( parsedString[ AppConstanst.COMMAND_IDX ] ) == null ){
			System.out.println( "'"+parsedString[ AppConstanst.COMMAND_IDX ]+"' "+ AppConstanst.UNKNOWN_COMMAND );
			} else  if ( parsedString.length > 2 && parsedString.length % 2 == 0 ){
				System.out.println( AppConstanst.ERROR_IN_SCAN_COMMAND );
				System.out.println( AppConstanst.WRONG_NUM_PARAM );
				} else {
					String classFullName = AppConstanst.PACKAGE + AppConstanst.AVAILABLE_COMMANDS.get( parsedString[ AppConstanst.COMMAND_IDX ] );
					Class<?> runConsoleCommandClass;
					RunConsoleCommand runConsoleCommandInst = null;
					try {
						runConsoleCommandClass = Class.forName( classFullName );
						runConsoleCommandInst = (RunConsoleCommand) runConsoleCommandClass.newInstance();
						consoleCommand = runConsoleCommandInst.runCommandFromJavaConsole ( parsedString );
					} catch (ClassNotFoundException e) {
						LOGGER.error( AppConstanst.CLASS_NOT_FOUND_EXCPT, e );
					} catch (InstantiationException e) {
						LOGGER.error(AppConstanst.INSTANTIATION_EXCPT, e );
					} catch (IllegalAccessException e) {
						LOGGER.error(AppConstanst.ILLEGALACCESS_EXCPT, e );
					}
					
				}
		return consoleCommand;
	}
	
}
