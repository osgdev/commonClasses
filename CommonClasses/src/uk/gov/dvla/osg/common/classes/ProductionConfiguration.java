package uk.gov.dvla.osg.common.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ProductionConfiguration {

	private String filename, englishFleet, welshFleet, englishMulti, welshMulti, englishUnsorted, welshUnsorted,
		englishSorted, welshSorted, englishClerical, welshClerical, englishReject, welshReject, englishReprint,
		welshReprint, mailingSite, minimumMailsort, mailsortProduct, englishSorting, welshSorting, envelopeType,
		envelopeEnglishUnsorted, envelopeWelshUnsorted,	envelopeEnglishOcr, envelopeWelshOcr, envelopeEnglishMm,
		envelopeWelshMm;
	private int batchMaxEnglishFleet, batchMaxWelshFleet,batchMaxEnglishMulti, batchMaxWelshMulti, 
		batchMaxEnglishUnsorted, batchMaxWelshUnsorted,batchMaxEnglishSorted, batchMaxWelshSorted, 
		batchMaxEnglishClerical, batchMaxWelshClerical, batchMaxEnglishReject, batchMaxWelshReject, 
		batchMaxEnglishReprint, batchMaxWelshReprint, batchMaxEnglishSorting , batchMaxWelshSorting,
		traySize, groupMaxEnglishMulti, groupMaxEnglishFleet, groupMaxEnglishClerical, groupMaxWelshMulti,
		groupMaxWelshFleet, groupMaxWelshClerical;
	
	private HashSet<String> requiredFields = new HashSet<String>();
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	public ProductionConfiguration(String filename){
		this.filename=filename;
		LOGGER.debug("Validating file '{}'",filename);
		
		loadRequiredFields();
		
		parseConfig(filename);
		
		if( requiredFields.size() != 0 ){
			String missingFields = "";
			for(String str : requiredFields){
				missingFields = missingFields + str + ",";
			}
			
			LOGGER.fatal("Missing values from '{}' are '{}'",filename,missingFields);
			System.exit(1);
		}
		
		if( !(isValid("SORT UNSORT X CHECK", "")) ){
			LOGGER.fatal("Sorted and unsorted destinations cannot both be set to x.");
			System.exit(1);
		}
		
	}
	
	private void loadRequiredFields() {
		requiredFields.add("site.english.fleet");
		requiredFields.add("site.welsh.fleet");
		requiredFields.add("site.english.multi");
		requiredFields.add("site.welsh.multi");
		requiredFields.add("site.english.unsorted");
		requiredFields.add("site.welsh.unsorted");
		requiredFields.add("site.english.sorted");
		requiredFields.add("site.welsh.sorted");
		requiredFields.add("site.english.clerical");
		requiredFields.add("site.welsh.clerical");
		requiredFields.add("site.english.reject");
		requiredFields.add("site.welsh.reject");
		requiredFields.add("site.english.reprint");
		requiredFields.add("site.welsh.reprint");
		requiredFields.add("site.mailing");
		requiredFields.add("minimum.mailsort");
		requiredFields.add("mailsort.preference.product");
		requiredFields.add("batchMax.english.fleet");
		requiredFields.add("batchMax.welsh.fleet");
		requiredFields.add("batchMax.english.multi");
		requiredFields.add("batchMax.welsh.multi");
		requiredFields.add("batchMax.english.unsorted");
		requiredFields.add("batchMax.welsh.unsorted");
		requiredFields.add("batchMax.english.sorted");
		requiredFields.add("batchMax.welsh.sorted");
		requiredFields.add("batchMax.english.clerical");
		requiredFields.add("batchMax.welsh.clerical");
		requiredFields.add("batchMax.english.reject");
		requiredFields.add("batchMax.welsh.reject");
		requiredFields.add("batchMax.english.reprint");
		requiredFields.add("batchMax.welsh.reprint");
		requiredFields.add("traySize");
		requiredFields.add("envelopeType");
		requiredFields.add("envelope.english.unsorted");
		requiredFields.add("envelope.welsh.unsorted");
		requiredFields.add("envelope.english.ocr");
		requiredFields.add("envelope.welsh.ocr");
		requiredFields.add("envelope.english.mm");
		requiredFields.add("envelope.welsh.mm");
		requiredFields.add("groupMax.english.multi");
		requiredFields.add("groupMax.english.fleet");
		requiredFields.add("groupMax.english.clerical");
		requiredFields.add("groupMax.welsh.multi");
		requiredFields.add("groupMax.welsh.fleet");
		requiredFields.add("groupMax.welsh.clerical");
	}

	private void parseConfig(String filename){
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
		    while (  ((line = br.readLine()) != null) ) {
		    	if( !(line.startsWith("#")) ){
		    		String attribute = null, value = null;
		    		try{
		    			String[] split = line.split("=");
				    	attribute = split[0];
				    	value = split[1];
		    		} catch(IndexOutOfBoundsException e){
		    			LOGGER.error("Index out of bounds when processing file '{}', value in error='{}'",this.filename,line);
		    		}
		    		
			    	if( "site.english.fleet".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.englishFleet=value;
			    		requiredFields.remove("site.english.fleet");
			    	} else if ( "site.welsh.fleet".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.welshFleet=value;
			    		requiredFields.remove("site.welsh.fleet");
			    	} else if ( "site.english.multi".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.englishMulti=value;
			    		requiredFields.remove("site.english.multi");
			    	} else if ( "site.welsh.multi".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.welshMulti=value;
			    		requiredFields.remove("site.welsh.multi");
			    	} else if ( "site.english.unsorted".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.englishUnsorted=value;
			    		requiredFields.remove("site.english.unsorted");
			    	} else if ( "site.welsh.unsorted".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.welshUnsorted=value;
			    		requiredFields.remove("site.welsh.unsorted");
			    	} else if ( "site.english.sorted".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.englishSorted=value;
			    		requiredFields.remove("site.english.sorted");
			    	} else if ( "site.welsh.sorted".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.welshSorted=value;
			    		requiredFields.remove("site.welsh.sorted");
			    	} else if ( "site.english.clerical".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.englishClerical=value;
			    		requiredFields.remove("site.english.clerical");
			    	} else if ( "site.welsh.clerical".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.welshClerical=value;
			    		requiredFields.remove("site.welsh.clerical");
			    	} else if ( "site.english.reject".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.englishReject=value;
			    		requiredFields.remove("site.english.reject");
			    	} else if ( "site.welsh.reject".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.welshReject=value;
			    		requiredFields.remove("site.welsh.reject");
			    	} else if ( "site.english.reprint".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.englishReprint=value;
			    		requiredFields.remove("site.english.reprint");
			    	} else if ( "site.welsh.reprint".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.welshReprint=value;
			    		requiredFields.remove("site.welsh.reprint");
			    	} else if ( "site.mailing".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.mailingSite=value;
			    		requiredFields.remove("site.mailing");
			    	} else if ( "minimum.mailsort".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.minimumMailsort=value;
			    		requiredFields.remove("minimum.mailsort");
			    	} else if ( "mailsort.preference.product".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.mailsortProduct=value;
			    		requiredFields.remove("mailsort.preference.product");
			    	} else if ( "batchMax.english.fleet".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxEnglishFleet=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.english.fleet");
			    	} else if ( "batchMax.welsh.fleet".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxWelshFleet=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.welsh.fleet");
			    	} else if ( "batchMax.english.multi".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxEnglishMulti=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.english.multi");
			    	} else if ( "batchMax.welsh.multi".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxWelshMulti=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.welsh.multi");
			    	} else if ( "batchMax.english.unsorted".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxEnglishUnsorted=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.english.unsorted");
			    	} else if ( "batchMax.welsh.unsorted".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxWelshUnsorted=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.welsh.unsorted");
			    	} else if ( "batchMax.english.sorted".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxEnglishSorted=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.english.sorted");
			    	} else if ( "batchMax.welsh.sorted".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxWelshSorted=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.welsh.sorted");
			    	} else if ( "batchMax.english.clerical".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxEnglishClerical=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.english.clerical");
			    	} else if ( "batchMax.welsh.clerical".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxWelshClerical=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.welsh.clerical");
			    	} else if ( "batchMax.english.reject".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxEnglishReject=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.english.reject");
			    	} else if ( "batchMax.welsh.reject".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxWelshReject=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.welsh.reject");
			    	} else if ( "batchMax.english.reprint".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxEnglishReprint=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.english.reprint");
			    	} else if ( "batchMax.welsh.reprint".equalsIgnoreCase(attribute) && isValid(attribute, value) ){
			    		this.batchMaxWelshReprint=Integer.parseInt(value);
			    		requiredFields.remove("batchMax.welsh.reprint");
			    	} else if ( "traySize".equalsIgnoreCase(attribute) ){
			    		this.traySize=Integer.parseInt(value);
			    		requiredFields.remove("traySize");
			    	} else if ( "envelopeType".equalsIgnoreCase(attribute) ){
			    		this.envelopeType=value;
			    		requiredFields.remove("envelopeType");
			    	} else if ( "envelope.english.unsorted".equalsIgnoreCase(attribute) ){
			    		this.envelopeEnglishUnsorted=value;
			    		requiredFields.remove("envelope.english.unsorted");
			    	} else if ( "envelope.welsh.unsorted".equalsIgnoreCase(attribute) ){
			    		envelopeWelshUnsorted=value;
			    		requiredFields.remove("envelope.welsh.unsorted");
			    	} else if ( "envelope.english.ocr".equalsIgnoreCase(attribute) ){
			    		envelopeEnglishOcr=value;
			    		requiredFields.remove("envelope.english.ocr");
			    	} else if ( "envelope.welsh.ocr".equalsIgnoreCase(attribute) ){
			    		envelopeWelshOcr=value;
			    		requiredFields.remove("envelope.welsh.ocr");
			    	} else if ( "envelope.english.mm".equalsIgnoreCase(attribute) ){
			    		envelopeEnglishMm=value;
			    		requiredFields.remove("envelope.english.mm");
			    	} else if ( "envelope.welsh.mm".equalsIgnoreCase(attribute) ){
			    		envelopeWelshMm=value;
			    		requiredFields.remove("envelope.welsh.mm");
			    	} else if ( "groupMax.english.multi".equalsIgnoreCase(attribute) ){
			    		groupMaxEnglishMulti=Integer.parseInt(value);
			    		requiredFields.remove("groupMax.english.multi");
			    	} else if ( "groupMax.english.fleet".equalsIgnoreCase(attribute) ){
			    		groupMaxEnglishFleet=Integer.parseInt(value);
			    		requiredFields.remove("groupMax.english.fleet");
			    	} else if ( "groupMax.english.clerical".equalsIgnoreCase(attribute) ){
			    		groupMaxEnglishClerical=Integer.parseInt(value);
			    		requiredFields.remove("groupMax.english.clerical");
			    	} else if ( "groupMax.welsh.multi".equalsIgnoreCase(attribute) ){
			    		groupMaxWelshMulti=Integer.parseInt(value);
			    		requiredFields.remove("groupMax.welsh.multi");
			    	} else if ( "groupMax.welsh.fleet".equalsIgnoreCase(attribute) ){
			    		groupMaxWelshFleet=Integer.parseInt(value);
			    		requiredFields.remove("groupMax.welsh.fleet");
			    	} else if ( "groupMax.welsh.clerical".equalsIgnoreCase(attribute) ){
			    		groupMaxWelshClerical=Integer.parseInt(value);
			    		requiredFields.remove("groupMax.welsh.clerical");
			    	}
		    	}
		    }
		} catch (FileNotFoundException e) {
			LOGGER.fatal("Lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.fatal("Lookup file error: '{}'",e.getMessage());
			System.exit(1);
		}
	}
	
	private boolean isValid(String att, String val){
		boolean result = true;
		if( att.startsWith("site") || att.startsWith("SITE") ){
			if( isNumeric(val) ){
				int value = Integer.parseInt(val);
				if( (value > 100) || (value < 0) ){
					result = false;
				}
			}else{
				if( !("M".equalsIgnoreCase(val)) && 
						!("F".equalsIgnoreCase(val)) && 
						!("X".equalsIgnoreCase(val)) &&
						!("XX".equalsIgnoreCase(val))){
					result = false;
				}
			}
		}
		if( att.startsWith("batchMax") || att.startsWith("batchmax") || att.startsWith("BATCHMAX") ){
			if( !(isNumeric(val)) ){
				result = false;
			}
		}

		if( "minimum.mailsort".equalsIgnoreCase(att) && !(isNumeric(val)) ){
			result = false;
		}
		if( "mailsort.preference.product".equalsIgnoreCase(att) && 
				!("OCR".equalsIgnoreCase(val)) &&
				!("MM".equalsIgnoreCase(val)) &&
				!("UNSORTED".equalsIgnoreCase(val)) ){
			result=false;
	
		}
		if( "SORT UNSORT X CHECK".equalsIgnoreCase(att) ){
			if( "X".equalsIgnoreCase(this.englishSorted) && "X".equalsIgnoreCase(this.englishUnsorted) ){
				result=false;
			}else if ( "X".equalsIgnoreCase(this.welshSorted) && "X".equalsIgnoreCase(this.welshUnsorted) ){
				result=false;
			}
		}
		
		return result;
	}
	private boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getEnglishFleet() {
		return englishFleet;
	}

	public void setEnglishFleet(String englishFleet) {
		this.englishFleet = englishFleet;
	}

	public String getWelshFleet() {
		return welshFleet;
	}

	public void setWelshFleet(String welshFleet) {
		this.welshFleet = welshFleet;
	}

	public String getEnglishMulti() {
		return englishMulti;
	}

	public void setEnglishMulti(String englishMulti) {
		this.englishMulti = englishMulti;
	}

	public String getWelshMulti() {
		return welshMulti;
	}

	public void setWelshMulti(String welshMulti) {
		this.welshMulti = welshMulti;
	}

	public String getEnglishUnsorted() {
		return englishUnsorted;
	}

	public void setEnglishUnsorted(String englishUnsorted) {
		this.englishUnsorted = englishUnsorted;
	}

	public String getWelshUnsorted() {
		return welshUnsorted;
	}

	public void setWelshUnsorted(String welshUnsorted) {
		this.welshUnsorted = welshUnsorted;
	}

	public String getEnglishSorted() {
		return englishSorted;
	}

	public void setEnglishSorted(String englishSorted) {
		this.englishSorted = englishSorted;
	}

	public String getWelshSorted() {
		return welshSorted;
	}

	public void setWelshSorted(String welshSorted) {
		this.welshSorted = welshSorted;
	}

	public String getEnglishClerical() {
		return englishClerical;
	}

	public void setEnglishClerical(String englishClerical) {
		this.englishClerical = englishClerical;
	}

	public String getWelshClerical() {
		return welshClerical;
	}

	public void setWelshClerical(String welshClerical) {
		this.welshClerical = welshClerical;
	}

	public String getEnglishReject() {
		return englishReject;
	}

	public void setEnglishReject(String englishReject) {
		this.englishReject = englishReject;
	}

	public String getWelshReject() {
		return welshReject;
	}

	public void setWelshReject(String welshReject) {
		this.welshReject = welshReject;
	}

	public String getEnglishReprint() {
		return englishReprint;
	}

	public void setEnglishReprint(String englishReprint) {
		this.englishReprint = englishReprint;
	}

	public String getWelshReprint() {
		return welshReprint;
	}

	public void setWelshReprint(String welshReprint) {
		this.welshReprint = welshReprint;
	}

	public String getMailingSite() {
		return mailingSite;
	}

	public void setMailingSite(String mailingSite) {
		this.mailingSite = mailingSite;
	}

	public String getMinimumMailsort() {
		return minimumMailsort;
	}

	public void setMinimumMailsort(String minimumMailsort) {
		this.minimumMailsort = minimumMailsort;
	}

	public String getMailsortProduct() {
		return mailsortProduct;
	}

	public void setMailsortProduct(String mailsortProduct) {
		this.mailsortProduct = mailsortProduct;
	}

	public int getBatchMaxEnglishFleet() {
		return batchMaxEnglishFleet;
	}

	public void setBatchMaxEnglishFleet(int batchMaxEnglishFleet) {
		this.batchMaxEnglishFleet = batchMaxEnglishFleet;
	}

	public int getBatchMaxWelshFleet() {
		return batchMaxWelshFleet;
	}

	public void setBatchMaxWelshFleet(int batchMaxWelshFleet) {
		this.batchMaxWelshFleet = batchMaxWelshFleet;
	}

	public int getBatchMaxEnglishMulti() {
		return batchMaxEnglishMulti;
	}

	public void setBatchMaxEnglishMulti(int batchMaxEnglishMulti) {
		this.batchMaxEnglishMulti = batchMaxEnglishMulti;
	}

	public int getBatchMaxWelshMulti() {
		return batchMaxWelshMulti;
	}

	public void setBatchMaxWelshMulti(int batchMaxWelshMulti) {
		this.batchMaxWelshMulti = batchMaxWelshMulti;
	}

	public int getBatchMaxEnglishUnsorted() {
		return batchMaxEnglishUnsorted;
	}

	public void setBatchMaxEnglishUnsorted(int batchMaxEnglishUnsorted) {
		this.batchMaxEnglishUnsorted = batchMaxEnglishUnsorted;
	}

	public int getBatchMaxWelshUnsorted() {
		return batchMaxWelshUnsorted;
	}

	public void setBatchMaxWelshUnsorted(int batchMaxWelshUnsorted) {
		this.batchMaxWelshUnsorted = batchMaxWelshUnsorted;
	}

	public int getBatchMaxEnglishSorted() {
		return batchMaxEnglishSorted;
	}

	public void setBatchMaxEnglishSorted(int batchMaxEnglishSorted) {
		this.batchMaxEnglishSorted = batchMaxEnglishSorted;
	}

	public int getBatchMaxWelshSorted() {
		return batchMaxWelshSorted;
	}

	public void setBatchMaxWelshSorted(int batchMaxWelshSorted) {
		this.batchMaxWelshSorted = batchMaxWelshSorted;
	}

	public int getBatchMaxEnglishClerical() {
		return batchMaxEnglishClerical;
	}

	public void setBatchMaxEnglishClerical(int batchMaxEnglishClerical) {
		this.batchMaxEnglishClerical = batchMaxEnglishClerical;
	}

	public int getBatchMaxWelshClerical() {
		return batchMaxWelshClerical;
	}

	public void setBatchMaxWelshClerical(int batchMaxWelshClerical) {
		this.batchMaxWelshClerical = batchMaxWelshClerical;
	}

	public int getBatchMaxEnglishReject() {
		return batchMaxEnglishReject;
	}

	public void setBatchMaxEnglishReject(int batchMaxEnglishReject) {
		this.batchMaxEnglishReject = batchMaxEnglishReject;
	}

	public int getBatchMaxWelshReject() {
		return batchMaxWelshReject;
	}

	public void setBatchMaxWelshReject(int batchMaxWelshReject) {
		this.batchMaxWelshReject = batchMaxWelshReject;
	}

	public int getBatchMaxEnglishReprint() {
		return batchMaxEnglishReprint;
	}

	public void setBatchMaxEnglishReprint(int batchMaxEnglishReprint) {
		this.batchMaxEnglishReprint = batchMaxEnglishReprint;
	}

	public int getBatchMaxWelshReprint() {
		return batchMaxWelshReprint;
	}

	public void setBatchMaxWelshReprint(int batchMaxWelshReprint) {
		this.batchMaxWelshReprint = batchMaxWelshReprint;
	}

	public String getEnglishSorting() {
		return englishSorting;
	}

	public void setEnglishSorting(String englishSorting) {
		this.englishSorting = englishSorting;
	}

	public String getWelshSorting() {
		return welshSorting;
	}

	public void setWelshSorting(String welshSorting) {
		this.welshSorting = welshSorting;
	}

	public int getBatchMaxEnglishSorting() {
		return batchMaxEnglishSorting;
	}

	public void setBatchMaxEnglishSorting(int batchMaxEnglishSorting) {
		this.batchMaxEnglishSorting = batchMaxEnglishSorting;
	}

	public int getBatchMaxWelshSorting() {
		return batchMaxWelshSorting;
	}

	public void setBatchMaxWelshSorting(int batchMaxWelshSorting) {
		this.batchMaxWelshSorting = batchMaxWelshSorting;
	}

	public int getTraySize() {
		return traySize;
	}

	public void setTraySize(int traySize) {
		this.traySize = traySize;
	}

	public String getEnvelopeType() {
		return envelopeType;
	}

	public void setEnvelopeType(String envelopeType) {
		this.envelopeType = envelopeType;
	}

	public String getEnvelopeEnglishUnsorted() {
		return envelopeEnglishUnsorted;
	}

	public void setEnvelopeEnglishUnsorted(String envelopeEnglishUnsorted) {
		this.envelopeEnglishUnsorted = envelopeEnglishUnsorted;
	}

	public String getEnvelopeWelshUnsorted() {
		return envelopeWelshUnsorted;
	}

	public void setEnvelopeWelshUnsorted(String envelopeWelshUnsorted) {
		this.envelopeWelshUnsorted = envelopeWelshUnsorted;
	}

	public String getEnvelopeEnglishOcr() {
		return envelopeEnglishOcr;
	}

	public void setEnvelopeEnglishOcr(String envelopeEnglishOcr) {
		this.envelopeEnglishOcr = envelopeEnglishOcr;
	}

	public String getEnvelopeWelshOcr() {
		return envelopeWelshOcr;
	}

	public void setEnvelopeWelshOcr(String envelopeWelshOcr) {
		this.envelopeWelshOcr = envelopeWelshOcr;
	}

	public String getEnvelopeEnglishMm() {
		return envelopeEnglishMm;
	}

	public void setEnvelopeEnglishMm(String envelopeEnglishMm) {
		this.envelopeEnglishMm = envelopeEnglishMm;
	}

	public String getEnvelopeWelshMm() {
		return envelopeWelshMm;
	}

	public void setEnvelopeWelshMm(String envelopeWelshMm) {
		this.envelopeWelshMm = envelopeWelshMm;
	}

	public int getGroupMaxEnglishMulti() {
		return groupMaxEnglishMulti;
	}

	public void setGroupMaxEnglishMulti(int groupMaxEnglishMulti) {
		this.groupMaxEnglishMulti = groupMaxEnglishMulti;
	}

	public int getGroupMaxEnglishFleet() {
		return groupMaxEnglishFleet;
	}

	public void setGroupMaxEnglishFleet(int groupMaxEnglishFleet) {
		this.groupMaxEnglishFleet = groupMaxEnglishFleet;
	}

	public int getGroupMaxEnglishClerical() {
		return groupMaxEnglishClerical;
	}

	public void setGroupMaxEnglishClerical(int groupMaxEnglishClerical) {
		this.groupMaxEnglishClerical = groupMaxEnglishClerical;
	}

	public int getGroupMaxWelshMulti() {
		return groupMaxWelshMulti;
	}

	public void setGroupMaxWelshMulti(int groupMaxWelshMulti) {
		this.groupMaxWelshMulti = groupMaxWelshMulti;
	}

	public int getGroupMaxWelshFleet() {
		return groupMaxWelshFleet;
	}

	public void setGroupMaxWelshFleet(int groupMaxWelshFleet) {
		this.groupMaxWelshFleet = groupMaxWelshFleet;
	}

	public int getGroupMaxWelshClerical() {
		return groupMaxWelshClerical;
	}

	public void setGroupMaxWelshClerical(int groupMaxWelshClerical) {
		this.groupMaxWelshClerical = groupMaxWelshClerical;
	}
	
	
}
