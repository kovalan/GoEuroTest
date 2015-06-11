package com.goeuro.datatransformer.main;

import com.goeuro.datatransformer.action.CSVWriter;
import com.goeuro.datatransformer.action.JSONClient;
import com.goeuro.datatransformer.domainObj.LocationDO;
import com.goeuro.datatransformer.logger.GoEuroLogger;

/**
 * 
 * @author Kovalan
 * Main class for the JSON to CSV transformation tool
 *
 */
public class TransformerMain {

	public static void main(String[] args){
		
		try {
			
			String urlParam = null;
			if(args !=null && args.length !=0) {
				urlParam = args[0];
			}
			
			GoEuroLogger.logInfo("Start reading JSON Array from the url");
			LocationDO[] locationDOArray = JSONClient.readJSON(urlParam);
			CSVWriter.writeCSV(locationDOArray, "GoEuroFiles/csv/location.csv");
	 
		  } catch (Exception e) {
			  GoEuroLogger.logError("Error occured when converting JSON to CSV: "+e.getMessage());
		  }
	 
		}
}
