package uk.gov.dvla.osg.common.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StationeryLookup {
	private static final Logger LOGGER = LogManager.getLogger();
	private String filePath, ref;
	private float thickness, weight;
	private HashMap<String, StationeryLookup> lookup = new HashMap<String, StationeryLookup>();
	
	public StationeryLookup(String filePath){
		LOGGER.info("Creating Stationery Lookup..");
		this.filePath=filePath;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	String[] array = line.split(",");
		    	if( !("REF".equals(array[0].trim())) ){
		    		StationeryLookup ins = new StationeryLookup(filePath, array[0].trim(), Float.parseFloat(array[1].trim()),Float.parseFloat(array[2].trim()) );
		    		lookup.put(array[0].trim(), ins);
		    	}
		    }
		    br.close();
		} catch (FileNotFoundException e) {
			LOGGER.fatal("Stationery lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.fatal("Stationery lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (NullPointerException e){
			LOGGER.fatal("Stationery lookup file error: '{}'",e.getMessage());
			System.exit(1);
		}
	}

	public StationeryLookup(String filePath, String ref, float thickness, float weight){
		this.filePath=filePath;
		this.ref =ref;
		this.thickness=thickness;
		this.weight=weight;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public float getThickness() {
		return thickness;
	}

	public void setThickness(float thickness) {
		this.thickness = thickness;
	}

	public HashMap<String, StationeryLookup> getLookup() {
		return lookup;
	}

	public void setLookup(HashMap<String, StationeryLookup> lookup) {
		this.lookup = lookup;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
}
