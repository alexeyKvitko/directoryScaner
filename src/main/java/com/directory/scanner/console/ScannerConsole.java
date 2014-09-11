/**
 * 
 */
package com.directory.scanner.console;

import java.util.Scanner;

import com.directory.scanner.AppConstanst;
import com.directory.scanner.model.ConsoleCommand;
import com.directory.scanner.service.ParseCommandService;

/**
 * Class read input from console (keyboard) used java.util.Scanner
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-04-29
 * 
 */
public class ScannerConsole {

	public void runScanner() {
		Scanner in = new Scanner(System.in);
		ParseCommandService parseCommandSrv = new ParseCommandService();
		String command = null;
		while ( !AppConstanst.EXIT.equals( command ) ){
			System.out.print( AppConstanst.CONSOLE );
			String input  = in.nextLine();
			ConsoleCommand consoleCommand = parseCommandSrv.parseInputFromConsole( input );
			command = consoleCommand.getCommand();
		}
	}

}
