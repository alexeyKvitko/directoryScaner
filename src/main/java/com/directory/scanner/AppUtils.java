package com.directory.scanner;

/**
 * Class which contains additional methods for apllication 
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-05-05
 * 
 */
public class AppUtils {
	
	private static final String PIPE = "|";
	private static final String BACKSLASH = "\\"; 
	private static final String SLASH = "/";
	private static final String ASTERISK = "*";
	
	/**
	 *  Verify mask
	 * 
	 * @param String mask - mask which will be isCorrect
	 * @return boolean - if mask correct - true else false
	 */
	public static boolean isMaskCorrect(String mask){
		boolean isCorrect = true;
		if ( mask.contains(PIPE) || mask.contains(BACKSLASH) || mask.contains(SLASH) ){
			isCorrect = false ;
		}
		String[] parsedMask = mask.split("\\.");
		isCorrect = isCorrect && parsedMask.length == 2;
		if ( isCorrect ){
			if ( parsedMask[0].indexOf( ASTERISK ) > 0 && parsedMask[0].indexOf( ASTERISK ) != parsedMask[0].length()-1) {
				isCorrect  = false;
			} else if ( parsedMask[0].indexOf( ASTERISK ) != parsedMask[0].lastIndexOf( ASTERISK ) &&  parsedMask[0].lastIndexOf( ASTERISK ) != parsedMask[0].length()-1){
				isCorrect  = false;
			} else if ( parsedMask[1].indexOf( ASTERISK ) != -1 && parsedMask[1].length() > 1){
				isCorrect  = false;
			}
		}
		return isCorrect;
	}

}
