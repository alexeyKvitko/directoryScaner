package com.directory.scanner;

import java.util.HashMap;
import java.util.Map;

/**
 *  Class which contains all application constants
 *
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-04-29 
 * 
 */
public class AppConstanst {
	
	//PACKAGE
	public static final String PACKAGE= "com.directory.scanner.service.impl.";
	
	// CONSOLE SYMBOL
	public static final String CONSOLE =">";
	
	// Log4j2 KEY
	public static final String CTX_KEY="ROUTINGKEY";
	
	// LAUNCHER ARGUMENT
	public static final String USE_SCANNER = "-s";
	
	// LOGGER MASSAGE
	public static final String NOW_USE_SCANER = "Now using java.util.Scanner";
	public static final String NOW_USE_BUFFER_READER = "Now using java.io.BufferedReader";
	public static final String CLASS_NOT_FOUND_EXCPT = "ClassNotFoundException: ";
	public static final String INSTANTIATION_EXCPT = "InstantiationException: ";
	public static final String ILLEGALACCESS_EXCPT = "IllegalAccessException: ";
	public static final String ITERATION = "Iteration: ";
	public static final String SOURCE_DIRS = "Source directories: ";
	public static final String SOURCE_FILES_TO_COPY = "Source files to copy: ";
	public static final String CAN_NOT_COPY = "Cannot copy file [ ";
	public static final String COPPING_FILE = "Copying file [ ";
	public static final String COPY_TO = " ] to [ ";
	public static final String CAN_NOT_COPY_REASON = " ] reason:";
	public static final String COPY_SUCCESS = " ] success";
	public static final String CAN_NOT_CLOSE_CHANNEL = "Cannot close FileChannel: ";
	
	// COMMANDS
	public static final String SCAN = "scan";
	public static final String HELP = "help";
	public static final String INFO = "info";
	public static final String STOP = "stop";
	public static final String EXIT = "exit";
	public static final String UNKNOWN = "unknown";
	
	// SCAN DIRECTIVE
	public static final String SCAN_INPUT_DIR="-inputDir";
	public static final String SCAN_OUTPUT_DIR="-outputDir";
	public static final String SCAN_MASK="-mask";
	public static final String SCAN_WAIT_INTERVAL="-waitInterval";
	public static final String SCAN_INCLUDE_SUB="-includeSubfolders";
	public static final String SCAN_AUTO_DELETE="-autoDelete";
	
	//DEFAULT POOL SIZE
	public static final Integer DEF_POOL_SIZE = 100;
	
	//MIN SCAN REQ PARAM
	public static final Integer MIN_SCAN_PARAM = 8;
	
	//SCAN INDEXES
	public static final Integer COMMAND_IDX = 0;
	public static final Integer INPUT_DIR_IDX = 1;
	public static final Integer INPUT_DIR_VALUE_IDX = 2;
	public static final Integer OUTPUT_DIR_IDX = 3;
	public static final Integer OUTPUT_DIR_VALUE_IDX = 4;
	public static final Integer MASK_IDX = 5;
	public static final Integer MASK_VALUE_IDX = 6;
	public static final Integer WAIT_INTERVAL_IDX = 7;
	public static final Integer WAIT_INTERVAL_VALUE_IDX = 8;
	public static final Integer INCLUDE_SUBS_IDX = 9;
	public static final Integer INCLUDE_SUBS_VALUE_IDX = 10;
	public static final Integer AUTO_DELETE_IDX = 11;
	public static final Integer AUTO_DELETE_VALUE_IDX= 12;
	
	//STOP INDEX
	
	public static final Integer THREAD_ID_IDX = 1;
			
	//PARAMS
	public static final String TRUE="true";
	public static final String FALSE="false";
	
	//METHOD FOR COMMANDS
	@SuppressWarnings("serial")
	public static final Map< String,String > AVAILABLE_COMMANDS = new HashMap< String, String>(){{
		put( SCAN, "RunScanConsoleCommand" );
		put( HELP, "RunHelpConsoleCommand" );
		put( INFO, "RunInfoConsoleCommand" );
		put( STOP, "RunStopConsoleCommand" );
		put( EXIT, "RunExitConsoleCommand" );
	}};
	
	// HELP DESCRIPTION	
	public static final String[] HELP_COMMAND =  new String[]
			{"scan    Scan the specified directory and copies files that meet a certain condition in to the output directory.",
			 "         Example: scan -inputDir c:/test/in -outputDir c:/test/out -mask *.* -waitInterval 30000 -includeSubFolders true -autoDelete false",
			 "        -inputDir            Input Directory (required)",
			 "        -outputDir           Output Directory (required)",
			 "        -mask                Mask for files to be copied (required)",
			 "        -waitInterval        Interval with which the scanner scans the specified directory (required)",
			 "        -includeSubFolders  Or may not include the processing of subdirectories (not required)",
			 "        -autoDelete          Delete or not delete, the files after copying (not required)",
			 "help    Provides Help information.",
			 "info    Provides information about running processes.",
			 "stop    Stop scan process by Id.",
			 "        parameter:   id of running thread",
			 "        Example: stop 1252356",
			 "exit    Quits the DirectoryScanner program."
			};
	
	
	// REGEX
	public static final String SPACE=" ";
	public static final String ABS_PATH="([a-zA-Z]:)?(/[a-zA-Z0-9_.-]+)+/?";
	
	//MESSAGES
	public static final String UNKNOWN_COMMAND = "is not recognized as an internal or external command";
	public static final String WRONG_NUM_PARAM = "Wrong number of parameters";
	public static final String CONSOLE_INPUT_ERROR = "Error: Can not get input from console";
	public static final String WRONG_REQ_COMMAND = "Wrong required command: ";
	public static final String NOT_ALL_REQ_COMMAND_SET = "Error: Not all required command sets, please use 'help' command";
	public static final String NOT_RECOGNISE_COMMAND = "Not recognise command: ";
	public static final String ILLEGAL_INPUT_PATH = "Illegal symbols in input path: ";
	public static final String ILLEGAL_OUTPUT_PATH = "Illegal symbols in output path: ";
	public static final String ILLEGAL_MASK = "Illegal symbols in mask: ";
	public static final String INCORECT_WAIT_INTERVAL = "Incorrect value for waitInterval: ";
	public static final String INCORECT_THREAD_ID = "Incorrect value for thread id: ";
	public static final String INCORECT_INCLUDE_SUBFOLDERS = "Invalid value for includeSubfolders: ";
	public static final String INCORECT_AUTODELETE = "Invalid value for autoDelete: ";
	public static final String INPUT_DIR_NOT_EXISTS = "Input directory does not exist, and cannot be created: ";
	public static final String OUTPUT_DIR_NOT_EXISTS = "Output directory does not exist, and cannot be created: ";
	public static final String TRUE_OR_FALSE = " ( must be true or false ) ";
	public static final String POSITIVE_INT = " ( must be positive int value ) ";
	public static final String MUST_BE = ", must be: ";
	public static final String ERROR_IN_SCAN_COMMAND = "Error: Scanner was not started due to following reasons:\n";
	public static final String ERROR_IN_STOP_COMMAND = "Error: Thread was not stoped due to following reasons:\n";
	public static final String RUNNING_THREADS = "Running threads: ";
	public static final String THREAD_ID = "Thread id: ";
	public static final String RUNNING_THREADS_ID = "Scanner was successfully started, thread id: ";
	
	
	

}
