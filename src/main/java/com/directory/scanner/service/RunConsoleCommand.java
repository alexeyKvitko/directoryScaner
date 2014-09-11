/**
 * 
 */
package com.directory.scanner.service;

import com.directory.scanner.model.ConsoleCommand;

/**
 * Interface to run different  console command 
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-05-04
 * 
 */
public interface RunConsoleCommand {
	
	/**
     * This method will be use in implementation of this interface to run command from console  
     * @param CansoleCommand consoleCommand - entity which describe console command 
     * @param String[] parsedString - array of parsed strings
     * @return ConsoleCommand 
     */
	public ConsoleCommand runCommandFromJavaConsole( String[] parsedString );

}
