package uk.gov.dvla.osg.common.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsertLookup {
	private static final Logger LOGGER = LogManager.getLogger();
	private String filePath, ref;
	private float thickness, weight;
	private int hopperCode;
	private HashMap<String, InsertLookup> lookup = new HashMap<String, InsertLookup>();
	
	public InsertLookup(String filePath){
		LOGGER.info("Creating Insert Lookup..");
		this.filePath=filePath;
		LOGGER.info("File '{}' exists",filePath);
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	String[] array = line.split(",");
		    	if( !("INSERT REF".equals(array[0].trim())) ){
		    		InsertLookup ins = new InsertLookup(filePath, array[0].trim(), Float.parseFloat(array[1].trim()), Float.parseFloat(array[2].trim()), Integer.parseInt(array[3].trim()) );
		    		lookup.put(array[0].trim(), ins);
		    	}
		    }
		    br.close();
		}catch (IndexOutOfBoundsException e){
			LOGGER.fatal("Insert lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (FileNotFoundException e) {
			LOGGER.fatal("Insert lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.fatal("Insert lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (NullPointerException e){
			LOGGER.fatal("Insert lookup file error: '{}'",e.getMessage());
			System.exit(1);
		}
	}

	public InsertLookup(String filePath, String ref, float thickness, float weight, int hopperCode){
		this.filePath=filePath;
		this.ref =ref;
		this.thickness=thickness;
		this.weight=weight;
		this.hopperCode=hopperCode;
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

	public HashMap<String, InsertLookup> getLookup() {
		return lookup;
	}

	public void setLookup(HashMap<String, InsertLookup> lookup) {
		this.lookup = lookup;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getHopperCode() {
		return hopperCode;
	}

	public void setHopperCode(int hopperCode) {
		this.hopperCode = hopperCode;
	}
	
}
