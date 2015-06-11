package com.goeuro.datatransformer.logger;

import org.apache.log4j.Logger;

/**
 * Logger for the JASON to CSV conversion tool.
 * @author Kovalan
 *
 */
public class GoEuroLogger {

	private final static Logger logger = Logger.getLogger(GoEuroLogger.class);
	
	public static void logInfo(String msg){
		logger.info(msg);
	}
	
	public static void logDebug(String msg){
		logger.debug(msg);
	}
	
	public static void logError(String msg){
		logger.error(msg);
	}
	
	public static void logError(String msg, Throwable throwable){
		logger.error(msg, throwable);
	}
	
	public static void logError(Throwable throwable){
		logError("Unexpected Error", throwable);
	}
}
