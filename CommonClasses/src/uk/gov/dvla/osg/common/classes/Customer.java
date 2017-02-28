package uk.gov.dvla.osg.common.classes;

public class Customer {

	private String groupId, docRef, selectorRef, lang, site, stationery, batchType, subBatch, jid, 
	fleetNo, paperSize, msc, sortField, eog, dps, name1, name2, add1, add2, add3, add4, add5,
	postcode, insertRef, envelope, mmBarcodeContent, eot;
	private int sequence, batchSequence, noOfPages;
	float weight, size;
	private Integer presentationPriority;
	
	public Customer(String docRef, String sortField, String ref, String lang, String stationery, String batchType, String subBatch, String fleetNo, String groupId, String paperSize, String msc){
		this.docRef=docRef;
		this.sortField=sortField;
		this.selectorRef=ref;
		this.lang=lang;
		this.stationery=stationery;
		this.batchType=batchType;
		this.subBatch=subBatch;
		this.fleetNo=fleetNo;
		this.groupId=groupId;
		this.paperSize=paperSize;
		this.msc = msc;
		this.eog="";
		this.eot="";
	};
	
	public String getEnvelope() {
		return envelope;
	}

	public void setEnvelope(String envelope) {
		this.envelope = envelope;
	}

	public String getInsertRef(){
		return insertRef;
	}
	public void setInsertRef(String insertRef){
		this.insertRef=insertRef;
	}
	public Integer getPresentationPriority() {
		return presentationPriority;
	}
	public void setPresentationPriority(Integer presentationPriority) {
		this.presentationPriority = presentationPriority;
	}
	public String getMsc() {
		return msc;
	}
	public void setMsc(String msc) {
		this.msc = msc;
	}
	public int getBatchSequence() {
		return batchSequence;
	}
	public void setBatchSequence(int batchSequence) {
		this.batchSequence = batchSequence;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getFleetNo() {
		return fleetNo;
	}
	public void setFleetNo(String ref) {
		this.fleetNo = ref;
	}
	public String getSelectorRef() {
		return selectorRef;
	}
	public void setSelectorRef(String ref) {
		this.selectorRef = ref;
	}
	public String getDocRef() {
		return docRef;
	}
	public void setDocRef(String ref) {
		this.docRef = ref;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getStationery() {
		return stationery;
	}
	public void setStationery(String stationery) {
		this.stationery = stationery;
	}
	public String getBatchType() {
		return batchType;
	}
	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}
	public String getSubBatch() {
		return subBatch;
	}
	public void setSubBatch(String subBatch) {
		this.subBatch = subBatch;
	}
	public String getJid() {
		return jid;
	}
	public void setJid(String jid) {
		this.jid = jid;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public String getPaperSize() {
		return paperSize;
	}

	public void setPaperSize(String paperSize) {
		this.paperSize = paperSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	

	public String getEog() {
		return eog;
	}

	public void setEog(String eog) {
		this.eog = eog;
	}

	public int getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getAdd3() {
		return add3;
	}

	public void setAdd3(String add3) {
		this.add3 = add3;
	}

	public String getAdd4() {
		return add4;
	}

	public void setAdd4(String add4) {
		this.add4 = add4;
	}

	public String getAdd5() {
		return add5;
	}

	public void setAdd5(String add5) {
		this.add5 = add5;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getDps() {
		return dps;
	}

	public void setDps(String dps) {
		this.dps = dps;
	}

	public String[] print(){
		String[] str = {this.docRef,this.site,this.jid};
		return str;
	}
	
	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getMmBarcodeContent() {
		return mmBarcodeContent;
	}

	public void setMmBarcodeContent(String mmBarcodeContent) {
		this.mmBarcodeContent = mmBarcodeContent;
	}

	public String getEot() {
		return eot;
	}

	public void setEot(String eot) {
		this.eot = eot;
	}

	@Override
	public String toString(){
		
		/*String str = docRef + "," + lang + "," + batchType + "," + subBatch + "," + site + "," + 
				fleetNo + "," + msc + "," + batchSequence + "," + sequence + "," + 
				groupId  + "," + noOfPages + "," + eog + "," + weight + "," + size + "," +
				mmBarcodeContent + "," + eot;*/
		
		String str = docRef  + "," + lang + "," + batchSequence + "," + sequence + "," + msc + "," + batchType + "," + subBatch + "," + site + "," + eog + "," + eot; 
		
		
		return str;
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((batchType == null) ? 0 : batchType.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result
				+ ((paperSize == null) ? 0 : paperSize.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result
				+ ((stationery == null) ? 0 : stationery.hashCode());
		result = prime * result
				+ ((subBatch == null) ? 0 : subBatch.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (batchType == null) {
			if (other.batchType != null)
				return false;
		} else if (!batchType.equals(other.batchType))
			return false;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (paperSize == null) {
			if (other.paperSize != null)
				return false;
		} else if (!paperSize.equals(other.paperSize))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (stationery == null) {
			if (other.stationery != null)
				return false;
		} else if (!stationery.equals(other.stationery))
			return false;
		if (subBatch == null) {
			if (other.subBatch != null)
				return false;
		} else if (!subBatch.equals(other.subBatch))
			return false;
		return true;
	}
}
