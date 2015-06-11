package com.goeuro.datatransformer.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.goeuro.datatransformer.domainObj.GeoPositionDO;
import com.goeuro.datatransformer.domainObj.LocationDO;
import com.goeuro.datatransformer.logger.GoEuroLogger;

/**
 * 
 * @author Kovalan
 * Writes the Location domain object array to a CSV file.
 *
 */
public class CSVWriter {

	private static final String csvHeader = "_id, name, type, latitude, longitude";
	
	/**
	 * Write the locationDO objects to the CSV file.
	 * @param locationDOList
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public static void writeCSV(LocationDO[] locationDOList, String fileName) throws FileNotFoundException{
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(csvHeader + "\n");
		
		if(locationDOList !=null && locationDOList.length !=0) {
			for(LocationDO locationDO : locationDOList) {
				stringBuffer.append(getCommaSeperatedLocationEntry(locationDO) + "\n");
			}
			writeToFile(stringBuffer.toString(), fileName);
		} else {
			GoEuroLogger.logInfo("Empty JSON returned");
		}
	}
	
	private static void writeToFile(String output, String fileName) throws FileNotFoundException {
		if(createCSVIfNotExists(fileName)) {
	        BufferedWriter writer = null;
	        try {
	            writer = new BufferedWriter(new FileWriter(fileName));
	            writer.write(output);
	            GoEuroLogger.logInfo("CSV file successfully created in: " + fileName);
	        } catch (IOException e) {
	        	GoEuroLogger.logError("Error when writting to CSV file: "+e.getMessage());
	        } finally {
	            close(writer);
	        }
		}
    }
	
	private static boolean createCSVIfNotExists(String fileName) {
		File file = new File(fileName);
		boolean b = true;
		if (!file.exists()) {
			try {
				if(file.getParentFile().exists() || file.getParentFile().mkdirs()) {
					b = file.createNewFile();
				} else {
					return false;
				}
			} catch (IOException e) {
				GoEuroLogger.logError("Error creating CSV file: "+e.getMessage());
				return false;
			}
		}
		return b;
	}

    private static void close(BufferedWriter writer) {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
        	GoEuroLogger.logError("Error closing CSV file: "+e.getMessage());
        }
    }
    
    private static String getCommaSeperatedLocationEntry(LocationDO locationDO){
    	
    	String latitude = null;
    	String longitude = null;
    	GeoPositionDO geoPosition = locationDO.getGeo_position();
    	if(geoPosition != null){
    		latitude = geoPosition.getLatitude();
    		longitude = geoPosition.getLongitude();
    	}
    	return getValue(locationDO.get_id()) +","+ getValue(locationDO.getName()) +","+ getValue(locationDO.getType()) +","+ getValue(latitude) +","+ getValue(longitude);
    }
    
    private static String getValue(String value){
    	if(value == null){
    		return "";
    	}
    	return value;
    }
	
}
