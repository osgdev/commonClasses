package uk.gov.dvla.osg.common.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

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
	
	private HashSet<String> requiredFields = new HashSet<String>();
	
	public PostageConfiguration(String filename){
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
	}
	
	private void loadRequiredFields() {
		requiredFields.add("ukm.m.accNo");
		requiredFields.add("ukm.f.accNo");
		requiredFields.add("unsort.accNo");
		requiredFields.add("unsort.service");
		requiredFields.add("unsort.product");
		requiredFields.add("unsort.format");
		requiredFields.add("ocr.product");
		requiredFields.add("ocr.format");
		requiredFields.add("mm.scid");
		requiredFields.add("mm.class");
		requiredFields.add("mm.xmlProduct");
		requiredFields.add("mm.xmlFormat");
		requiredFields.add("mm.product");
		requiredFields.add("mm.format");
		requiredFields.add("mm.upuCountryId");
		requiredFields.add("mm.infoType");
		requiredFields.add("mm.versionId");
		requiredFields.add("mm.mailType");
		requiredFields.add("mm.returnMailFlag");
		requiredFields.add("mm.returnMailPc");
		requiredFields.add("mm.reserved");
		requiredFields.add("ukm.resourcePath");
		requiredFields.add("ukm.itemIDLookupFilename");
		requiredFields.add("ukm.m.trayLookupFilename");
		requiredFields.add("ukm.f.trayLookupFilename");
		//requiredFields.add("ukm.manifest.DestinationPath");
		//requiredFields.add("ukm.manifest.ArchivePath");
		requiredFields.add("ukm.soapfile.DestinationPath");
		requiredFields.add("ukm.soapfile.ArchivePath");
		requiredFields.add("ukm.minimumTrayVolume");
		requiredFields.add("ukm.minimumCompliance");
		requiredFields.add("ukm.batchTypes");
		requiredFields.add("ukm.consignorFileDestination");
		requiredFields.add("ukm.consignorDestinationDepartment");
		requiredFields.add("mm.machineable");
		requiredFields.add("mm.appName");
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
			    		requiredFields.remove("ukm.m.accNo");
			    	} else if( "ukm.f.accNo".equalsIgnoreCase(attribute) ){
			    		this.ukmFAcc=value;
			    		requiredFields.remove("ukm.f.accNo");
			    	} else if( "unsort.accNo".equalsIgnoreCase(attribute) ){
			    		this.unsortedAccountNo=value;
			    		requiredFields.remove("unsort.accNo");
			    	} else if( "unsort.service".equalsIgnoreCase(attribute) ){
			    		this.unsortedService=value;
			    		requiredFields.remove("unsort.service");
			    	} else if( "unsort.product".equalsIgnoreCase(attribute) ){
			    		this.unsortedProduct=value;
			    		requiredFields.remove("unsort.product");
			    	} else if( "unsort.format".equalsIgnoreCase(attribute) ){
			    		this.unsortedFormat=value;
			    		requiredFields.remove("unsort.format");
			    	} else if( "ocr.product".equalsIgnoreCase(attribute) ){
			    		this.ocrProduct=value;
			    		requiredFields.remove("ocr.product");
			    	} else if( "ocr.format".equalsIgnoreCase(attribute) ){
			    		this.ocrFormat=value;
			    		requiredFields.remove("ocr.format");
			    	} else if( "mm.scid".equalsIgnoreCase(attribute) ){
			    		this.mmScid=value;
			    		requiredFields.remove("mm.scid");
			    	} else if( "mm.class".equalsIgnoreCase(attribute) ){
			    		this.mmClass=value;
			    		requiredFields.remove("mm.class");
			    	} else if( "mm.xmlProduct".equalsIgnoreCase(attribute) ){
			    		this.mmXmlProduct=value;
			    		requiredFields.remove("mm.xmlProduct");
			    	} else if( "mm.xmlFormat".equalsIgnoreCase(attribute) ){
			    		this.mmXmlFormat=value;
			    		requiredFields.remove("mm.xmlFormat");
			    	} else if( "mm.product".equalsIgnoreCase(attribute) ){
			    		this.mmProduct=value;
			    		requiredFields.remove("mm.product");
			    	} else if( "mm.format".equalsIgnoreCase(attribute) ){
			    		this.mmFormat=value;
			    		requiredFields.remove("mm.format");
			    	} else if( "mm.upuCountryId".equalsIgnoreCase(attribute) ){
			    		this.mmUpuCountryId=value;
			    		requiredFields.remove("mm.upuCountryId");
			    	} else if( "mm.infoType".equalsIgnoreCase(attribute) ){
			    		this.mmInfoType=value;
			    		requiredFields.remove("mm.infoType");
			    	} else if( "mm.versionId".equalsIgnoreCase(attribute) ){
			    		this.mmVersionId=value;
			    		requiredFields.remove("mm.versionId");
			    	} else if( "mm.mailType".equalsIgnoreCase(attribute) ){
			    		this.mmMailType=value;
			    		requiredFields.remove("mm.mailType");
			    	} else if( "mm.returnMailFlag".equalsIgnoreCase(attribute) ){
			    		this.mmReturnMailFlag=value;
			    		requiredFields.remove("mm.returnMailFlag");
			    	} else if( "mm.returnMailPc".equalsIgnoreCase(attribute) ){
			    		this.mmReturnMailPc=value;
			    		requiredFields.remove("mm.returnMailPc");
			    	} else if( "mm.reserved".equalsIgnoreCase(attribute) ){
			    		this.mmReserved=value;
			    		requiredFields.remove("mm.reserved");
			    	} else if( "ukm.resourcePath".equalsIgnoreCase(attribute) ){
			    		this.ukmResourcePath=value;
			    		requiredFields.remove("ukm.resourcePath");
			    	} else if( "ukm.itemIDLookupFilename".equalsIgnoreCase(attribute) ){
			    		this.ukmItemIdLookupFile=value;
			    		requiredFields.remove("ukm.itemIDLookupFilename");
			    	} else if( "ukm.m.trayLookupFilename".equalsIgnoreCase(attribute) ){
			    		this.ukmMTrayLookupFile=value;
			    		requiredFields.remove("ukm.m.trayLookupFilename");
			    	} else if( "ukm.f.trayLookupFilename".equalsIgnoreCase(attribute) ){
			    		this.ukmFTrayLookupFile=value;
			    		requiredFields.remove("ukm.f.trayLookupFilename");
//			    	} else if( "ukm.manifest.DestinationPath".equalsIgnoreCase(attribute) ){
//			    		this.ukmManifestDestination=value;
//			    		requiredFields.remove("ukm.manifest.DestinationPath");
//			    	} else if( "ukm.manifest.ArchivePath".equalsIgnoreCase(attribute) ){
//			    		this.ukmManifestArchive=value;
//			    		requiredFields.remove("ukm.manifest.ArchivePath");
			    	} else if( "ukm.soapfile.DestinationPath".equalsIgnoreCase(attribute) ){
			    		this.ukmSoapDestination=value;
			    		requiredFields.remove("ukm.soapfile.DestinationPath");
			    	} else if( "ukm.soapfile.ArchivePath".equalsIgnoreCase(attribute) ){
			    		this.ukmSoapArchive=value;
			    		requiredFields.remove("ukm.soapfile.ArchivePath");
			    	} else if( "ukm.minimumTrayVolume".equalsIgnoreCase(attribute) ){
			    		this.ukmMinimumTrayVolume=Integer.parseInt(value);
			    		requiredFields.remove("ukm.minimumTrayVolume");
			    	} else if( "ukm.minimumCompliance".equalsIgnoreCase(attribute) ){
			    		this.ukmMinimumCompliance=Integer.parseInt(value);
			    		requiredFields.remove("ukm.minimumCompliance");
			    	} else if( "ukm.batchTypes".equalsIgnoreCase(attribute) ){
			    		this.ukmBatchTypes=value;
			    		requiredFields.remove("ukm.batchTypes");
			    	} else if( "ukm.consignorFileDestination".equalsIgnoreCase(attribute) ){
			    		ukmConsignorFileDestination=value;
			    		requiredFields.remove("ukm.consignorFileDestination");
			    	} else if( "ukm.consignorDestinationDepartment".equalsIgnoreCase(attribute) ){
			    		ukmConsignorDestinationDepartment=value;
			    		requiredFields.remove("ukm.consignorDestinationDepartment");
			    	} else if( "mm.machineable".equalsIgnoreCase(attribute) ){
			    		mmMachineable=value;
			    		requiredFields.remove("mm.machineable");
			    	} else if( "mm.appName".equalsIgnoreCase(attribute) ){
			    		mmAppname=value;
			    		requiredFields.remove("mm.appName");
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

//	public String getUkmManifestDestination() {
//		return ukmManifestDestination;
//	}

//	public void setUkmManifestDestination(String ukmManifestDestination) {
//		this.ukmManifestDestination = ukmManifestDestination;
//	}

//	public String getUkmManifestArchive() {
//		return ukmManifestArchive;
//	}

//	public void setUkmManifestArchive(String ukmManifestArchive) {
//		this.ukmManifestArchive = ukmManifestArchive;
//	}

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
