package com.directory.scanner.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.directory.scanner.AppConstanst;

/**
 * Class to work with file system
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-05-06
 * 
 */
public class FileSystemService {
	
	private static final Logger LOGGER = LogManager.getLogger( FileSystemService.class.getName() );
	
	private List< File > directories;
	private Map< File, File > copyFiles;
	
	/**
	 *  Constructor
	 */
	public FileSystemService(){
		this.directories =  new ArrayList< File >();
	}
	
	/**
	 *  Method return all directories and subdirectories 
	 *  
	 *  @param String initialDirPath -  directory path from which searching begin
	 *  @param String includeSubDir -  define include subdirectories or not
	 *  
	 *  @return List<File> - List of directories
	 */
	public List< File > getDirectories(String initialDirPath, boolean includeSubDir, boolean recur){
		File mainDir = new File ( initialDirPath );
		if ( !recur ){
			directories.add( mainDir );
		}
		String[] fileNames = mainDir.list();
		for( int idx = 0; idx < fileNames.length; idx++){
			File file = new File( mainDir.getPath(), fileNames[idx] );
			if ( file.isDirectory() && includeSubDir ) {
				directories.add( file );
				getDirectories( file.getPath(), includeSubDir, true);
			}
		}
		return directories;
	}
	
	/**
	 *  Returns all files to be copied 
	 *  
	 *  @param String mainInputPath -  main input directory path
	 *  @param String mainOutputPath -  main output directory path
	 *  
	 *  @return Map< File,File > - map of files : key is source file, value is output file
	 */
	@SuppressWarnings("rawtypes")
	public Map< File,File > getFilesToCopy( String mainInputPath,String mainOutputPath, String mask){
		copyFiles = new HashMap< File,File >();
		mainInputPath = (new File ( mainInputPath )).getPath();
		mainOutputPath = (new File ( mainOutputPath )).getPath();
		for ( File dir : directories ){
			String outputDir = mainOutputPath+dir.getPath().substring(mainInputPath.length(), dir.getPath().length());
			if ( !isDirectoryExist( outputDir ) ){
				makeDirectory( outputDir );
			}
			//String[] pathList = dir.list();
			Collection filesPath = FileUtils.listFiles( dir, new WildcardFileFilter( mask ), null);
			//String[] pathList = FileUtils.listFiles( dir, new WildcardFileFilter( mask ), null).toArray();
			//for( int idx = 0; idx < pathList.length; idx++){
			 for( Object filePath : filesPath){
				//File sourceFile = new File( dir.getPath(), pathList[idx] );
				File sourceFile =  (File) filePath ;
				if ( !sourceFile.isDirectory() ){
					// Check is file exist in output directory
					// if exist compare crc
					String sourceFilePath = sourceFile.getPath();
					String outputFilePath = mainOutputPath+sourceFilePath.substring(mainInputPath.length(), sourceFilePath.length());
					File outputFile = new File( outputFilePath);
					try {
						if ( !outputFile.exists() || !isEqualCRC(sourceFile, outputFile) ){
							outputFile.createNewFile();
							copyFiles.put( sourceFile,outputFile );	
						}
					} catch (IOException e) {
						LOGGER.error( e.getMessage() );
					}
				}
			}
		}
		return copyFiles;
	}
	
	/**
	 *  Check is directory exist or not
	 *  
	 *  @param String dirPath -  directory path
	 *  
	 *  @return boolean - true if exist false if not
	 */
	public boolean isDirectoryExist( String dirPath ){
		boolean exist = true;
		File dir = new File( dirPath );
		if ( !dir.exists() ){
			exist = false;
		}
		return exist;
	}
	
	/**
	 *  Try make not exist directory
	 *  
	 *  @param String dirPath -  directory path
	 *  
	 *  @return boolean - true if make new directory false if not
	 */
	public boolean makeDirectory( String dirPath ){
		boolean created = true;
		File dir = new File( dirPath );
		created = dir.mkdir();	
		return created;
	}
	
	/**
	 *  If output file exist, checked  source and output file crñ
	 *  
	 *  @param File sourceFile -  source file
	 *  @param File outputFile -  output file
	 *  
	 *  @return boolean - true if make new directory false if not
	 */
	private boolean isEqualCRC(File sourceFile, File outputFile)
			throws IOException {
		boolean equal = false;
		FileChannel sourceChannel = null;
		FileChannel outputChannel = null;
		FileInputStream sourceIS = null;
		FileInputStream outputIS = null;
		try {
			sourceIS = new FileInputStream(sourceFile);
			outputIS = new FileInputStream(outputFile);
			sourceChannel = (sourceIS).getChannel();
			outputChannel = (outputIS).getChannel();
			long sourceCRC = getCRCForChannel(sourceChannel);
			long outputCRC = getCRCForChannel(outputChannel);
			equal = sourceCRC == outputCRC;
		} finally {
			sourceIS.close();
			outputIS.close();
		}
		return equal;
	}
	
	/**
	 *  Calculate CRC for FileChannel
	 *  
	 *  @param FileChannel channel -  FileChannel to calculate CRC
	 *  
	 *  @return long - CRC value
	 */
	private long getCRCForChannel(FileChannel channel) throws IOException {
		CRC32 crc = new CRC32();
		long length = channel.size();
		MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0,
				length);
		for (long p = 0; p < length; p++) {
			int c = buffer.get((int) p);
			crc.update(c);
		}
		return crc.getValue();
	}
	
	
	/**
	 *  Copy source file to output file
	 *  
	 *  @param File sourceFile - source file
	 *  @param File outputFile - output file
	 *  
	 *  @return long - CRC value
	 */
	public  synchronized String copyFile( File sourceFile,File outputFile ){
		String result = null;
		FileChannel sourceChannel = null;
		FileChannel outputChannel = null;
		try {
			sourceChannel = (new FileInputStream(sourceFile)).getChannel();
			outputChannel = (new FileOutputStream(outputFile)).getChannel();
			sourceChannel.transferTo(0, sourceChannel.size(), outputChannel);
		} catch (Exception e) {
			result = AppConstanst.CAN_NOT_COPY+sourceFile.getPath()+AppConstanst.COPY_TO+outputFile.getPath()+AppConstanst.CAN_NOT_COPY_REASON+
					e.getMessage();	
		} finally {
			try {
				sourceChannel.close();
				outputChannel.close();
			} catch (Exception ioExp) {
				result = AppConstanst.CAN_NOT_CLOSE_CHANNEL+ioExp.getMessage();
			}
		}
		if ( result == null ){
			result = AppConstanst.COPPING_FILE+sourceFile.getPath()+AppConstanst.COPY_TO+outputFile.getPath()+AppConstanst.COPY_SUCCESS;	
		}
		return result;
	}
	
	/**
	 *  Delete sources file and directories
	 *  @param String sourceDirPath - main source directory path which not be deleted
	 */
	public  synchronized String deleteFilesAndDirectories( String mainSourceDirPath){
		String result = null;
		File mainSourceDir = new File( mainSourceDirPath ); 
		for ( File sourceFile: copyFiles.keySet() ){
			sourceFile.delete();
		}
		for ( File dir : directories ){
			if ( !mainSourceDir.getPath().equals( dir.getPath() ) ){
				dir.delete();
			}
		}
		return result;
	}
}
