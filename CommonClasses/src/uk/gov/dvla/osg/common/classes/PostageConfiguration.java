package uk.gov.dvla.osg.common.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PostageConfiguration {
	private static final Logger LOGGER = LogManager.getLogger();
	
	private String filename;
	private String ukmMAcc, ukmFAcc, ukmResourcePath, ukmItemIdLookupFile, ukmMTrayLookupFile,
		ukmFTrayLookupFile, ukmManifestDestination, ukmManifestArchive, ukmSoapDestination,
		ukmSoapArchive, ukmBatchTypes, ukmConsignorFileDestination, ukmConsignorDestinationDepartment;
	private int ukmMinimumTrayVolume, ukmMinimumCompliance;

	private String unsortedAccountNo, unsortedService, unsortedProduct, unsortedFormat;
	private String ocrProduct, ocrFormat;
	private String mmScid, mmClass, mmXmlProduct, mmXmlFormat, mmProduct, mmFormat, mmUpuCountryId,
		mmInfoType, mmVersionId, mmMailType, mmReturnMailFlag, mmReturnMailPc, mmReserved, 
		mmMachineable, mmAppname;
	
	public PostageConfiguration(String filename){
		this.filename=filename;
		LOGGER.debug("Validating file '{}'",filename);
		parseConfig(filename);
	}
	
	private void parseConfig(String filename){
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
		    while (  ((line = br.readLine()) != null) ) {
		    	if( !(line.startsWith("#")) ){
		    		String[] split = line.split("=");
			    	String attribute = split[0];
			    	String value = split[1];
			    	if( "ukm.m.accNo".equalsIgnoreCase(attribute) ){
			    		this.ukmMAcc=value;
			    	} else if( "ukm.f.accNo".equalsIgnoreCase(attribute) ){
			    		this.ukmFAcc=value;
			    	} else if( "unsort.accNo".equalsIgnoreCase(attribute) ){
			    		this.unsortedAccountNo=value;
			    	} else if( "unsort.service".equalsIgnoreCase(attribute) ){
			    		this.unsortedService=value;
			    	} else if( "unsort.product".equalsIgnoreCase(attribute) ){
			    		this.unsortedProduct=value;
			    	} else if( "unsort.format".equalsIgnoreCase(attribute) ){
			    		this.unsortedFormat=value;
			    	} else if( "ocr.product".equalsIgnoreCase(attribute) ){
			    		this.ocrProduct=value;
			    	} else if( "ocr.format".equalsIgnoreCase(attribute) ){
			    		this.ocrFormat=value;
			    	} else if( "mm.scid".equalsIgnoreCase(attribute) ){
			    		this.mmScid=value;
			    	} else if( "mm.class".equalsIgnoreCase(attribute) ){
			    		this.mmClass=value;
			    	} else if( "mm.xmlProduct".equalsIgnoreCase(attribute) ){
			    		this.mmXmlProduct=value;
			    	} else if( "mm.xmlFormat".equalsIgnoreCase(attribute) ){
			    		this.mmXmlFormat=value;
			    	} else if( "mm.product".equalsIgnoreCase(attribute) ){
			    		this.mmProduct=value;
			    	} else if( "mm.format".equalsIgnoreCase(attribute) ){
			    		this.mmFormat=value;
			    	} else if( "mm.upuCountryId".equalsIgnoreCase(attribute) ){
			    		this.mmUpuCountryId=value;
			    	} else if( "mm.infoType".equalsIgnoreCase(attribute) ){
			    		this.mmInfoType=value;
			    	} else if( "mm.versionId".equalsIgnoreCase(attribute) ){
			    		this.mmVersionId=value;
			    	} else if( "mm.mailType".equalsIgnoreCase(attribute) ){
			    		this.mmMailType=value;
			    	} else if( "mm.returnMailFlag".equalsIgnoreCase(attribute) ){
			    		this.mmReturnMailFlag=value;
			    	} else if( "mm.returnMailPc".equalsIgnoreCase(attribute) ){
			    		this.mmReturnMailPc=value;
			    	} else if( "mm.reserved".equalsIgnoreCase(attribute) ){
			    		this.mmReserved=value;
			    	} else if( "ukm.resourcePath".equalsIgnoreCase(attribute) ){
			    		this.ukmResourcePath=value;
			    	} else if( "ukm.itemIDLookupFilename".equalsIgnoreCase(attribute) ){
			    		this.ukmItemIdLookupFile=value;
			    	} else if( "ukm.m.trayLookupFilename".equalsIgnoreCase(attribute) ){
			    		this.ukmMTrayLookupFile=value;
			    	} else if( "ukm.f.trayLookupFilename".equalsIgnoreCase(attribute) ){
			    		this.ukmFTrayLookupFile=value;
			    	} else if( "ukm.manifest.DestinationPath".equalsIgnoreCase(attribute) ){
			    		this.ukmManifestDestination=value;
			    	} else if( "ukm.manifest.ArchivePath".equalsIgnoreCase(attribute) ){
			    		this.ukmManifestArchive=value;
			    	} else if( "ukm.soapfile.DestinationPath".equalsIgnoreCase(attribute) ){
			    		this.ukmSoapDestination=value;
			    	} else if( "ukm.soapfile.ArchivePath".equalsIgnoreCase(attribute) ){
			    		this.ukmSoapArchive=value;
			    	} else if( "ukm.minimumTrayVolume".equalsIgnoreCase(attribute) ){
			    		this.ukmMinimumTrayVolume=Integer.parseInt(value);
			    	} else if( "ukm.minimumCompliance".equalsIgnoreCase(attribute) ){
			    		this.ukmMinimumCompliance=Integer.parseInt(value);
			    	} else if( "ukm.batchTypes".equalsIgnoreCase(attribute) ){
			    		this.ukmBatchTypes=value;
			    	} else if( "ukm.consignorFileDestination".equalsIgnoreCase(attribute) ){
			    		ukmConsignorFileDestination=value;
			    	} else if( "ukm.consignorDestinationDepartment".equalsIgnoreCase(attribute) ){
			    		ukmConsignorDestinationDepartment=value;
			    	} else if( "mm.machineable".equalsIgnoreCase(attribute) ){
			    		mmMachineable=value;
			    	} else if( "mm.appName".equalsIgnoreCase(attribute) ){
			    		mmAppname=value;
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

	public String getUkmMAcc() {
		return ukmMAcc;
	}

	public void setUkmMAcc(String ukmMAcc) {
		this.ukmMAcc = ukmMAcc;
	}

	public String getUkmFAcc() {
		return ukmFAcc;
	}

	public void setUkmFAcc(String ukmFAcc) {
		this.ukmFAcc = ukmFAcc;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUnsortedAccountNo() {
		return unsortedAccountNo;
	}

	public void setUnsortedAccountNo(String unsortedAccountNo) {
		this.unsortedAccountNo = unsortedAccountNo;
	}

	public String getUnsortedService() {
		return unsortedService;
	}

	public void setUnsortedService(String unsortedService) {
		this.unsortedService = unsortedService;
	}

	public String getUnsortedProduct() {
		return unsortedProduct;
	}

	public void setUnsortedProduct(String unsortedProduct) {
		this.unsortedProduct = unsortedProduct;
	}

	public String getUnsortedFormat() {
		return unsortedFormat;
	}

	public void setUnsortedFormat(String unsortedFormat) {
		this.unsortedFormat = unsortedFormat;
	}

	public String getOcrProduct() {
		return ocrProduct;
	}

	public void setOcrProduct(String ocrProduct) {
		this.ocrProduct = ocrProduct;
	}

	public String getOcrFormat() {
		return ocrFormat;
	}

	public void setOcrFormat(String ocrFormat) {
		this.ocrFormat = ocrFormat;
	}

	public String getMmScid() {
		return mmScid;
	}

	public void setMmScid(String mmScid) {
		this.mmScid = mmScid;
	}

	public String getMmClass() {
		return mmClass;
	}

	public void setMmClass(String mmClass) {
		this.mmClass = mmClass;
	}

	public String getMmXmlProduct() {
		return mmXmlProduct;
	}

	public void setMmXmlProduct(String mmXmlProduct) {
		this.mmXmlProduct = mmXmlProduct;
	}

	public String getMmXmlFormat() {
		return mmXmlFormat;
	}

	public void setMmXmlFormat(String mmXmlFormat) {
		this.mmXmlFormat = mmXmlFormat;
	}

	public String getMmProduct() {
		return mmProduct;
	}

	public void setMmProduct(String mmProduct) {
		this.mmProduct = mmProduct;
	}

	public String getMmFormat() {
		return mmFormat;
	}

	public void setMmFormat(String mmFormat) {
		this.mmFormat = mmFormat;
	}

	public String getMmUpuCountryId() {
		return mmUpuCountryId;
	}

	public void setMmUpuCountryId(String mmUpuCountryId) {
		this.mmUpuCountryId = mmUpuCountryId;
	}

	public String getMmInfoType() {
		return mmInfoType;
	}

	public void setMmInfoType(String mmInfoType) {
		this.mmInfoType = mmInfoType;
	}

	public String getMmVersionId() {
		return mmVersionId;
	}

	public void setMmVersionId(String mmVersionId) {
		this.mmVersionId = mmVersionId;
	}

	public String getMmMailType() {
		return mmMailType;
	}

	public void setMmMailType(String mmMailType) {
		this.mmMailType = mmMailType;
	}

	public String getMmReturnMailFlag() {
		return mmReturnMailFlag;
	}

	public void setMmReturnMailFlag(String mmReturnMailFlag) {
		this.mmReturnMailFlag = mmReturnMailFlag;
	}

	public String getMmReturnMailPc() {
		return mmReturnMailPc;
	}

	public void setMmReturnMailPc(String mmReturnMailPc) {
		this.mmReturnMailPc = mmReturnMailPc;
	}

	public String getMmReserved() {
		return mmReserved;
	}

	public void setMmReserved(String mmReserved) {
		this.mmReserved = mmReserved;
	}

	public String getUkmResourcePath() {
		return ukmResourcePath;
	}

	public void setUkmResourcePath(String ukmResourcePath) {
		this.ukmResourcePath = ukmResourcePath;
	}

	public String getUkmItemIdLookupFile() {
		return ukmItemIdLookupFile;
	}

	public void setUkmItemIdLookupFile(String ukmItemIdLookupFile) {
		this.ukmItemIdLookupFile = ukmItemIdLookupFile;
	}

	public String getUkmMTrayLookupFile() {
		return ukmMTrayLookupFile;
	}

	public void setUkmMTrayLookupFile(String ukmMTrayLookupFile) {
		this.ukmMTrayLookupFile = ukmMTrayLookupFile;
	}

	public String getUkmFTrayLookupFile() {
		return ukmFTrayLookupFile;
	}

	public void setUkmFTrayLookupFile(String ukmFTrayLookupFile) {
		this.ukmFTrayLookupFile = ukmFTrayLookupFile;
	}

	public String getUkmManifestDestination() {
		return ukmManifestDestination;
	}

	public void setUkmManifestDestination(String ukmManifestDestination) {
		this.ukmManifestDestination = ukmManifestDestination;
	}

	public String getUkmManifestArchive() {
		return ukmManifestArchive;
	}

	public void setUkmManifestArchive(String ukmManifestArchive) {
		this.ukmManifestArchive = ukmManifestArchive;
	}

	public String getUkmSoapDestination() {
		return ukmSoapDestination;
	}

	public void setUkmSoapDestination(String ukmSoapDestination) {
		this.ukmSoapDestination = ukmSoapDestination;
	}

	public String getUkmSoapArchive() {
		return ukmSoapArchive;
	}

	public void setUkmSoapArchive(String ukmSoapArchive) {
		this.ukmSoapArchive = ukmSoapArchive;
	}

	public String getUkmBatchTypes() {
		return ukmBatchTypes;
	}

	public void setUkmBatchTypes(String ukmBatchTypes) {
		this.ukmBatchTypes = ukmBatchTypes;
	}

	public int getUkmMinimumTrayVolume() {
		return ukmMinimumTrayVolume;
	}

	public void setUkmMinimumTrayVolume(int ukmMinimumTrayVolume) {
		this.ukmMinimumTrayVolume = ukmMinimumTrayVolume;
	}

	public int getUkmMinimumCompliance() {
		return ukmMinimumCompliance;
	}

	public void setUkmMinimumCompliance(int ukmMinimumCompliance) {
		this.ukmMinimumCompliance = ukmMinimumCompliance;
	}

	public String getUkmConsignorFileDestination() {
		return ukmConsignorFileDestination;
	}

	public void setUkmConsignorFileDestination(String ukmConsignorFileDestination) {
		this.ukmConsignorFileDestination = ukmConsignorFileDestination;
	}

	public String getUkmConsignorDestinationDepartment() {
		return ukmConsignorDestinationDepartment;
	}

	public void setUkmConsignorDestinationDepartment(
			String ukmConsignorDestinationDepartment) {
		this.ukmConsignorDestinationDepartment = ukmConsignorDestinationDepartment;
	}

	public String getMmMachineable() {
		return mmMachineable;
	}

	public void setMmMachineable(String mmMachineable) {
		this.mmMachineable = mmMachineable;
	}

	public String getMmAppname() {
		return mmAppname;
	}

	public void setMmAppname(String mmAppname) {
		this.mmAppname = mmAppname;
	}
	
	
}
