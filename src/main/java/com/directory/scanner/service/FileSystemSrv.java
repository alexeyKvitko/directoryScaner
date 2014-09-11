package com.directory.scanner.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to work with file system
 * 
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-04-30
 * 
 */
public class FileSystemSrv {
	
	private List< File > directories;
	private List< File > sourceFiles;
	
	public FileSystemSrv(){
		this.directories =  new ArrayList< File >();
	}
	
	public List< File > getDirectories(String initialDirVal){
		File pathName = new File ( initialDirVal );
		String[] fileNames = pathName.list();
		for( int idx = 0; idx < fileNames.length; idx++){
			File file = new File( pathName.getPath(), fileNames[idx] );
			if ( file.isDirectory() ){
				directories.add( file );
				getDirectories( file.getPath() );
			}
		}
		return directories;
	}
	
	public List< File > getSourceFiles(){
		sourceFiles = new ArrayList< File >();
		for ( File dir : directories ){
			File pathName = new File( dir.getPath() );
			String[] fileNames = pathName.list();
			for( int idx = 0; idx < fileNames.length; idx++){
				File file = new File( pathName.getPath(), fileNames[idx] );
				if ( !file.isDirectory() ){
					sourceFiles.add( file );
				}
			}
		}
		return sourceFiles;
	}
	
	public boolean isDirectoryExist( String inputDirVal ){
		boolean exist = true;
		File dir = new File( inputDirVal );
		if ( !dir.exists() ){
			exist = false;
		}
		return exist;
	}
	
	public boolean makeDirectory( String outputDirVal ){
		boolean created = true;
		File dir = new File( outputDirVal );
		created = dir.mkdir();	
		return created;
	}

}
