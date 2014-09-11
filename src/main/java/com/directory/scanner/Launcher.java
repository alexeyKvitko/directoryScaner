/**
 * 
 */
package com.directory.scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.directory.scanner.console.BufferReaderConsole;
import com.directory.scanner.console.ScannerConsole;

/**
 *  Main Class to launch application
 *
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-04-29 
 * 
 */
public class Launcher {
	
	private static final Logger LOGGER = LogManager.getLogger( Launcher.class.getName() );

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Verify what class will use for controller java.util.Scanner or java.io.BufferedReader
		// if args[0] == "-s" we will use Scanner
		if ( args.length > 0 && AppConstanst.USE_SCANNER.equals( args[0]) ){
			LOGGER.info( AppConstanst.NOW_USE_SCANER );
			ScannerConsole scannerConsole = new ScannerConsole();
			scannerConsole.runScanner();
		} else {
			LOGGER.info( AppConstanst.NOW_USE_BUFFER_READER );
			BufferReaderConsole bufferReaderConsole = new BufferReaderConsole();
			bufferReaderConsole.runBufferReader();
		}
	}

}
