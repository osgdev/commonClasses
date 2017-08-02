package uk.gov.dvla.osg.common.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvelopeLookup {
	private static final Logger LOGGER = LogManager.getLogger();
	private String filePath, ref;
	private float thickness, weight;
	private int foldMultiplier;
	private HashMap<String, EnvelopeLookup> lookup = new HashMap<String, EnvelopeLookup>();
	
	public EnvelopeLookup(String filePath){
		LOGGER.info("Creating Envelope Lookup..");
		this.filePath=filePath;
		LOGGER.info("File '{}' exists",filePath);
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	String[] array = line.split(",");
		    	if( !("REF".equals(array[0].trim())) ){
		    		EnvelopeLookup ins = new EnvelopeLookup(filePath, array[0].trim(), Float.parseFloat(array[1].trim()), Integer.parseInt(array[2].trim()),Float.parseFloat(array[3].trim()) );
		    		lookup.put(array[0].trim(), ins);
		    	}
		    }
		    br.close();
		} catch (FileNotFoundException e) {
			LOGGER.fatal("Envelope lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.fatal("Envelope lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (NullPointerException e){
			LOGGER.fatal("Envelope lookup file error: '{}'",e.getMessage());
			System.exit(1);
		}
	}

	public EnvelopeLookup(String filePath, String ref, float thickness, int foldMultiplier, float weight){
		this.filePath=filePath;
		this.ref =ref;
		this.thickness=thickness;
		this.foldMultiplier=foldMultiplier;
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
	

	public int getFoldMultiplier() {
		return foldMultiplier;
	}

	public void setFoldMultiplier(int foldMultiplier) {
		this.foldMultiplier = foldMultiplier;
	}

	public HashMap<String, EnvelopeLookup> getLookup() {
		return lookup;
	}

	public void setLookup(HashMap<String, EnvelopeLookup> lookup) {
		this.lookup = lookup;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
}
