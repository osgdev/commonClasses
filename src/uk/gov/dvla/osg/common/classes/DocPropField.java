package uk.gov.dvla.osg.common.classes;

public class DocPropField {
	private String attibuteValue, attibuteName;
	private boolean isRequiredInInputFile;
	
	public DocPropField(String attibuteValue, String attibuteName, boolean isRequiredInInputFile){
		this.attibuteValue=attibuteValue;
		this.attibuteName=attibuteName;
		this.isRequiredInInputFile=isRequiredInInputFile;
	}
	
	public String getAttibuteValue() {
		return attibuteValue;
	}
	public void setAttibuteValue(String attibuteValue) {
		this.attibuteValue = attibuteValue;
	}
	public String getAttibuteName() {
		return attibuteName;
	}
	public void setAttibuteName(String attibuteName) {
		this.attibuteName = attibuteName;
	}
	public boolean isRequiredInInputFile() {
		return isRequiredInInputFile;
	}
	public void setRequiredInInputFile(boolean isRequiredInInputFile) {
		this.isRequiredInInputFile = isRequiredInInputFile;
	}	
}
