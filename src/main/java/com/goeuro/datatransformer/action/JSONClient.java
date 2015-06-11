package com.goeuro.datatransformer.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.goeuro.datatransformer.domainObj.LocationDO;
import com.goeuro.datatransformer.logger.GoEuroLogger;

/**
 * 
 * @author Kovalan
 * RESTFul JSON Client.  Renders the JSON from the url and returns the domain object.
 *
 */
public class JSONClient {
	
	private static final String url = "http://api.goeuro.com/api/v2/position/suggest/en";

	/**
	 * Reads the JSON Array and constructs array of domain objects LocationDO.
	 * @param urlParam urlParam will be appended to the url.
	 * @return LocationDO array
	 */
	public static LocationDO[] readJSON(String urlParam){
		try {
			
			String fullUrl = url;
			if(urlParam!=null){
				fullUrl = url+"/"+urlParam;
			}
			
			RestTemplate restTemplate = configureRestTemplate();
			ResponseEntity<LocationDO[]> resultLocation = restTemplate.exchange(fullUrl, HttpMethod.GET, null, LocationDO[].class);
			if(HttpStatus.OK == resultLocation.getStatusCode()) {
				LocationDO[] locationDOArray = resultLocation.getBody();
				return locationDOArray;
			} else {
				GoEuroLogger.logError("JSON retrival is unsuccessful with status code: " + resultLocation.getStatusCode());
			}
	 
		  } catch (Exception e) {
			  GoEuroLogger.logError("Error when reading JSON Array: "+e.getMessage());
		  }
	 
		return null;
		
	}
	
	private static RestTemplate configureRestTemplate() {
		 
	    RestTemplate restTemplate = new RestTemplate();
	 
	    List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
	 
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>(messageConverters);
	 
	    MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
	 
	    converters.add(jsonConverter);
	 
	    restTemplate.setMessageConverters(converters);
	 
	    return restTemplate;
	 
	}
}
