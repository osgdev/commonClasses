package uk.gov.dvla.osg.common.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PapersizeLookup {
	private static final Logger LOGGER = LogManager.getLogger();
	private String filePath, ref;
	private float multiplier;
	private HashMap<String, PapersizeLookup> lookup = new HashMap<String, PapersizeLookup>();
	
	public PapersizeLookup(String filePath){
		LOGGER.info("Creating Papersize Lookup..");
		this.filePath=filePath;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	if( !(line.startsWith("#")) ){
		    		String[] array = line.split(",",-1);
		    		PapersizeLookup ins = new PapersizeLookup(filePath, array[0].trim(), Float.parseFloat(array[1].trim()));
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

	public PapersizeLookup(String filePath, String ref, float multiplier){
		this.filePath=filePath;
		this.ref =ref;
		this.multiplier=multiplier;
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

	public float getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}
	
	public HashMap<String, PapersizeLookup> getLookup() {
		return lookup;
	}

	public void setLookup(HashMap<String, PapersizeLookup> lookup) {
		this.lookup = lookup;
	}
}
