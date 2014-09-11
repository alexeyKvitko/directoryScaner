package com.directory.scanner.service.impl;

import com.directory.scanner.AppConstanst;
import com.directory.scanner.AppUtils;
import com.directory.scanner.facade.ScanThreadPool;
import com.directory.scanner.model.ConsoleCommand;
import com.directory.scanner.service.FileSystemService;
import com.directory.scanner.service.RunConsoleCommand;

/**
 * Class which implements RunConsoleCommand interface to run scan command 
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-05-04
 * 
 */
public class RunScanConsoleCommand implements RunConsoleCommand{
	
	private static final ScanThreadPool POOL_INST = ScanThreadPool.getInstance();

	@Override
	public ConsoleCommand runCommandFromJavaConsole( String[] parsedString ) {
		ConsoleCommand consoleCommand = mapConsoleCommand ( parsedString );
		if ( !AppConstanst.UNKNOWN.equals(consoleCommand.getCommand() ) ){
			    // CREATE NEW THREAD
				int hashCode = POOL_INST.createNewThread( consoleCommand );
				consoleCommand.setThreadId( hashCode );
				System.out.println( AppConstanst.RUNNING_THREADS_ID+hashCode);
		}
		return consoleCommand;
	}
	
	/**
	 * Map from command from parsed Strign[] to ConsoleCommand model
	 * 
	 * @param ConsoleCommand consoleCommand - model for console command
	 * @param String[] parsedCommand - Array of string
	 */
	private ConsoleCommand mapConsoleCommand( String[] parsedString ){
		
		FileSystemService fileSysSrv = new FileSystemService();
		StringBuilder errorSB = new StringBuilder();
		ConsoleCommand consoleCommand = new ConsoleCommand();
		
		consoleCommand.setCommand( parsedString[ AppConstanst.COMMAND_IDX ] );
				
		if ( parsedString.length < AppConstanst.MIN_SCAN_PARAM ){
			errorSB.append( AppConstanst.NOT_ALL_REQ_COMMAND_SET ) ;
		} else {
			// VALIDATE INPUTDIR COMMAND
			consoleCommand.setInputDir( parsedString[ AppConstanst.INPUT_DIR_IDX ] );
			if ( !AppConstanst.SCAN_INPUT_DIR.equals( consoleCommand.getInputDir() ) ){
				errorSB.append( AppConstanst.WRONG_REQ_COMMAND+" "+consoleCommand.getInputDir()+AppConstanst.MUST_BE+AppConstanst.SCAN_INPUT_DIR+"\n" );
			}
			consoleCommand.setInputDirValue( parsedString[ AppConstanst.INPUT_DIR_VALUE_IDX ] );
			if ( ! consoleCommand.getInputDirValue().matches( AppConstanst.ABS_PATH) ){
				errorSB.append( AppConstanst.ILLEGAL_INPUT_PATH + consoleCommand.getInputDirValue()+"\n");
			}
			//CHECK IS INPUT DIR EXIST, IF NOT TRY CREATE IT
		    if ( !fileSysSrv.isDirectoryExist( consoleCommand.getInputDirValue() ) ){
		    	if ( !fileSysSrv.makeDirectory( consoleCommand.getInputDirValue() ) ){
		    		errorSB.append( AppConstanst.INPUT_DIR_NOT_EXISTS + consoleCommand.getInputDirValue()+"\n");
		    	};
		    }
			// VALIDATE OUTPUTDIR COMMAND
			consoleCommand.setOutPutDir(  parsedString[ AppConstanst.OUTPUT_DIR_IDX ] );
			if ( !AppConstanst.SCAN_OUTPUT_DIR.equals( consoleCommand.getOutPutDir() ) ){
				errorSB.append( AppConstanst.WRONG_REQ_COMMAND+" "+consoleCommand.getOutPutDir()+AppConstanst.MUST_BE+AppConstanst.SCAN_OUTPUT_DIR+"\n" );
			}
			consoleCommand.setOutPutDirValue(  parsedString[ AppConstanst.OUTPUT_DIR_VALUE_IDX ] );
			if ( ! consoleCommand.getOutPutDirValue().matches( AppConstanst.ABS_PATH) ){
				errorSB.append( AppConstanst.ILLEGAL_OUTPUT_PATH + consoleCommand.getOutPutDirValue()+"\n" );
			}
			//CHECK IS OUTPUT DIR EXIST, IF NOT TRY CREATE IT
		    if ( !fileSysSrv.isDirectoryExist( consoleCommand.getOutPutDirValue() ) ){
		    	if ( !fileSysSrv.makeDirectory( consoleCommand.getOutPutDirValue() ) ){
		    		errorSB.append( AppConstanst.OUTPUT_DIR_NOT_EXISTS + consoleCommand.getOutPutDirValue()+"\n");
		    	};
		    }
			// VALIDATE MASK COMMAND
			consoleCommand.setMask( parsedString[ AppConstanst.MASK_IDX ] );
			if ( !AppConstanst.SCAN_MASK.equals( consoleCommand.getMask() ) ){
				errorSB.append( AppConstanst.WRONG_REQ_COMMAND+" "+consoleCommand.getMask()+AppConstanst.MUST_BE+AppConstanst.SCAN_MASK+"\n" );
			}
			consoleCommand.setMaskValue( parsedString[ AppConstanst.MASK_VALUE_IDX ]);
			if ( ! AppUtils.isMaskCorrect( consoleCommand.getMaskValue() ) ){
				errorSB.append( AppConstanst.ILLEGAL_MASK + consoleCommand.getMaskValue()+"\n" );
			}
			// VALIDATE WAIT INTERVAL COMMAND
			consoleCommand.setWaitInterval( parsedString[ AppConstanst.WAIT_INTERVAL_IDX ] );
			if ( !AppConstanst.SCAN_WAIT_INTERVAL.equals( consoleCommand.getWaitInterval() ) ){
				errorSB.append( AppConstanst.WRONG_REQ_COMMAND+" "+consoleCommand.getWaitInterval()+AppConstanst.MUST_BE+AppConstanst.SCAN_WAIT_INTERVAL+"\n" );
			}
			Long interval= null;
			try{
				interval = Long.parseLong( parsedString[ AppConstanst.WAIT_INTERVAL_VALUE_IDX ] );
			} catch (NumberFormatException e) {} 
			consoleCommand.setWaitIntervalValue( interval );
			if ( interval == null || interval < 0){
				errorSB.append( AppConstanst.INCORECT_WAIT_INTERVAL + parsedString[ AppConstanst.WAIT_INTERVAL_VALUE_IDX ]+AppConstanst.POSITIVE_INT+"\n" );
			}
			// VALIDATE NOT REQUIRED COMMAND
			
				for (int idx = AppConstanst.MIN_SCAN_PARAM+1; idx < parsedString.length; idx += 2){
					if ( AppConstanst.SCAN_INCLUDE_SUB.equals(  parsedString[ idx ] ) ){
						consoleCommand.setIncludeSubs( parsedString[ idx ] );
						consoleCommand.setIncludeSubsValue( parsedString[ idx+1 ] );
						if ( !AppConstanst.TRUE.equals( parsedString[ idx+1 ]) && !AppConstanst.FALSE.equals( parsedString[ idx+1 ] ) ){
							errorSB.append( AppConstanst.INCORECT_INCLUDE_SUBFOLDERS+" "+parsedString[ idx+1 ]+AppConstanst.TRUE_OR_FALSE+"\n" );
						}
						
					}
					if ( AppConstanst.SCAN_AUTO_DELETE.equals(  parsedString[ idx ] ) ){
						consoleCommand.setAutoDelete( parsedString[ idx ] );
						consoleCommand.setAutoDeleteValue( parsedString[ idx+1 ] );
						if ( !AppConstanst.TRUE.equals( parsedString[ idx+1 ]) && !AppConstanst.FALSE.equals( parsedString[ idx+1 ] ) ){
							errorSB.append( AppConstanst.INCORECT_AUTODELETE+" "+parsedString[ idx+1 ]+AppConstanst.TRUE_OR_FALSE+"\n" );
						}
					}
				}
		}
		if ( errorSB.length() > 0 ){
			System.out.println(AppConstanst.ERROR_IN_SCAN_COMMAND+ errorSB.toString());
			consoleCommand.setCommand( AppConstanst.UNKNOWN);
		} 
		return consoleCommand;
	}

}
