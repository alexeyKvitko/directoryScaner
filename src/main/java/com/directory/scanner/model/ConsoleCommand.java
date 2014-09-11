/**
 * 
 */
package com.directory.scanner.model;

import java.io.Serializable;

import com.directory.scanner.AppConstanst;

/**
 * Class which describe entity, for parsed console command from console
 * @author Created by alexey.kvitko
 * @author Last modified by alexey.kvitko
 * @author Last modified on 2014-04-30
 */
public class ConsoleCommand implements Serializable{
	
	private static final long serialVersionUID = 2101228382349636204L;
	
	private Integer threadId;
	private String command;
	private String inputDir;
	private String inputDirValue;
	private String outPutDir;
	private String outPutDirValue;
	private String mask;
	private String maskValue;
	private String waitInterval;
	private Long waitIntervalValue;
	private String includeSubs;
	private String includeSubsValue;
	private String autoDelete;
	private String autoDeleteValue;
	
	public ConsoleCommand(){
		this.includeSubsValue = AppConstanst.TRUE;
	}
	
	/**
	 * @return the threadId
	 */
	public Integer getThreadId() {
		return threadId;
	}
	/**
	 * @param threadId the threadId to set
	 */
	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}
	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}
	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}
	/**
	 * @return the inputDir
	 */
	public String getInputDir() {
		return inputDir;
	}
	/**
	 * @param inputDir the inputDir to set
	 */
	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}
	/**
	 * @return the inputDirValue
	 */
	public String getInputDirValue() {
		return inputDirValue;
	}
	/**
	 * @param inputDirValue the inputDirValue to set
	 */
	public void setInputDirValue(String inputDirValue) {
		this.inputDirValue = inputDirValue;
	}
	/**
	 * @return the outPutDir
	 */
	public String getOutPutDir() {
		return outPutDir;
	}
	/**
	 * @param outPutDir the outPutDir to set
	 */
	public void setOutPutDir(String outPutDir) {
		this.outPutDir = outPutDir;
	}
	/**
	 * @return the outPutDirValue
	 */
	public String getOutPutDirValue() {
		return outPutDirValue;
	}
	/**
	 * @param outPutDirValue the outPutDirValue to set
	 */
	public void setOutPutDirValue(String outPutDirValue) {
		this.outPutDirValue = outPutDirValue;
	}
	/**
	 * @return the mask
	 */
	public String getMask() {
		return mask;
	}
	/**
	 * @param mask the mask to set
	 */
	public void setMask(String mask) {
		this.mask = mask;
	}
	/**
	 * @return the maskValue
	 */
	public String getMaskValue() {
		return maskValue;
	}
	/**
	 * @param maskValue the maskValue to set
	 */
	public void setMaskValue(String maskValue) {
		this.maskValue = maskValue;
	}
	/**
	 * @return the waitInterval
	 */
	public String getWaitInterval() {
		return waitInterval;
	}
	/**
	 * @param waitInterval the waitInterval to set
	 */
	public void setWaitInterval(String waitInterval) {
		this.waitInterval = waitInterval;
	}
	/**
	 * @return the waitIntervalValue
	 */
	public Long getWaitIntervalValue() {
		return waitIntervalValue;
	}
	/**
	 * @param waitIntervalValue the waitIntervalValue to set
	 */
	public void setWaitIntervalValue(Long waitIntervalValue) {
		this.waitIntervalValue = waitIntervalValue;
	}
	/**
	 * @return the includeSubs
	 */
	public String getIncludeSubs() {
		return includeSubs;
	}
	/**
	 * @param includeSubs the includeSubs to set
	 */
	public void setIncludeSubs(String includeSubs) {
		this.includeSubs = includeSubs;
	}
	/**
	 * @return the includeSubsValue
	 */
	public boolean isIncludeSubsValue() {
		return AppConstanst.TRUE.equals( includeSubsValue );
	}
	/**
	 * @param includeSubsValue the includeSubsValue to set
	 */
	public void setIncludeSubsValue(String includeSubsValue) {
		this.includeSubsValue = includeSubsValue;
	}
	/**
	 * @return the autoDelete
	 */
	public String getAutoDelete() {
		return autoDelete;
	}
	/**
	 * @param autoDelete the autoDelete to set
	 */
	public void setAutoDelete(String autoDelete) {
		this.autoDelete = autoDelete;
	}
	/**
	 * @return the autoDeleteValue
	 */
	public boolean isAutoDeleteValue() {
		return AppConstanst.TRUE.equals( autoDeleteValue );
	}
	/**
	 * @param autoDeleteValue the autoDeleteValue to set
	 */
	public void setAutoDeleteValue(String autoDeleteValue) {
		this.autoDeleteValue = autoDeleteValue;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("threadId ["+ threadId +"]")
		.append("command ["+ command +"]")
		.append(" inputDir ["+ inputDir  +"]")
		.append(" inputDirValue ["+ inputDirValue +"]")
		.append(" outPutDir ["+ outPutDir +"]")
		.append(" outPutDirValue ["+ outPutDirValue +"]")
		.append(" mask ["+ mask +"]")
		.append(" maskValue ["+ maskValue +"]")
		.append(" waitInterval ["+ waitInterval +"]")
		.append(" waitIntervalValue ["+ waitIntervalValue +"]")
		.append(" includeSubs ["+ includeSubs +"]")
		.append(" includeSubsValue ["+ includeSubsValue +"]")
		.append(" autoDelete ["+ autoDelete +"]")
		.append(" autoDeleteValue ["+ autoDeleteValue +"]");
		return sb.toString();
	}

}
