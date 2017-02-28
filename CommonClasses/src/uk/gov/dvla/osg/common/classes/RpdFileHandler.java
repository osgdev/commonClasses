package uk.gov.dvla.osg.common.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RpdFileHandler {
	
	private static final Logger LOGGER = LogManager.getLogger();
	private String inputFile = "";
	private String outputFile = "";
	private HashMap<String,Integer> results;
	private List<String> headList;
	private PrintWriter pw;
	private BufferedReader br;
	
	public RpdFileHandler(String inputFile, String outputFile){
		LOGGER.info("Running RpdFileHandler");
		this.inputFile=inputFile;
		this.outputFile=outputFile;
		results = new HashMap<String,Integer>();
		headList = new ArrayList<String>();
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFile,false)));
			br = new BufferedReader(new FileReader(inputFile));
		} catch (IOException e) {
			LOGGER.fatal("Error when creating output file '{}', error:{}",outputFile,e.getMessage());
			System.exit(1);
		}
		process();
	}
	
	private void process(){
		try {
			String head = br.readLine();
			LOGGER.info("Read headers '{}'",head);
			String[] heads = head.split("\\t");
			LOGGER.info("Found {} records in header",heads.length);
			for(int i = 0; i < heads.length; i++){
				results.put(heads[i], i);
				headList.add(heads[i]);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			LOGGER.fatal("File '{}' not found.",inputFile);
			System.exit(1);
		} catch (IOException e1) {
			LOGGER.fatal("IO Exception when processing file '{}', error: '{}'",inputFile,e1.getMessage());
			System.exit(1);
		}
	}

	
	public HashMap<String,Integer> getMapping(){
		return results;
	}
	
	public List<String> getHeaders(){
		return headList;
	}
	
	public void write(String str){
		pw.write(str);
	}
	
	public void write(List<String> arr){
		String res = "";
		String delim = "" + (char)Integer.parseInt("09",16);
		for(String str : arr){
			res = res + str + delim;
		}
		pw.println(res);
	}
	
	public void closeFile(){
		pw.close();
	}

}
