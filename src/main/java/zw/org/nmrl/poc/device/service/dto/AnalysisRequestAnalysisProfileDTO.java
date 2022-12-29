package zw.org.nmrl.poc.device.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import zw.org.nmrl.poc.device.domain.SampleType;

/**
 * A DTO for the {@link zw.org.nmrl.domain.AnalysisRequest} entity.
 */
public class AnalysisRequestAnalysisProfileDTO implements Serializable {

	private String analysisRequestId;

	private String analysisRequestUid;

	private String patientId;

	private String patientUid;

	private String sampleId;

	private String clientSampleId;

	private String labId;

	private String registrationClient;

	private String registrationClientId;

	private String clientId;

	private String clientContactId;

	private String clientContactName;

	private String labReferenceNumber;

	private LocalDate dateCollected;

	private String reviewState;

	private String status;

	private String dispatched;

	private String sampleTypeName;

	private SampleType sampleType;

	private String sentToLims;

	private String sync;

	private String creator;

	private LocalDate dateRegistered;

	private String retest;

	private String province;

	private LocalDate modificationDate;

	private LocalDate dateReceived;

	private String invalidated;

	private LocalDate dateSampled;

	private LocalDate dateTested;

	private Boolean printed;

	private LocalDate datePublished;

	private LocalDate datePrinted;

	private AnalysisRequestRejectionReasonDTO rejectionReasons;

	private String analyst;

	private SenaiteClientResolverDTO senaiteClient;

	private String dateReceivedAtHub;

	private String source;

	@JsonProperty("remarks")
	private List<AnalysisRequestRemarkDTO> remarks;

	@JsonProperty("analysisProfileNames")
	private List<String> analysisProfileNames;

	private List<AnalysisProfileDTO> analysisProfiles;

	private List<AnalysisDTO> analyses;

	private String priority;

	private Boolean internalUse;

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getClientSampleId() {
		return clientSampleId;
	}

	public void setClientSampleId(String clientSampleId) {
		this.clientSampleId = clientSampleId;
	}

	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	public String getRegistrationClient() {
		return registrationClient;
	}

	public void setRegistrationClient(String registrationClient) {
		this.registrationClient = registrationClient;
	}

	public String getRegistrationClientId() {
		return registrationClientId;
	}

	public void setRegistrationClientId(String registrationClientId) {
		this.registrationClientId = registrationClientId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientContactId() {
		return clientContactId;
	}

	public void setClientContactId(String clientContactId) {
		this.clientContactId = clientContactId;
	}

	public String getLabReferenceNumber() {
		return labReferenceNumber;
	}

	public void setLabReferenceNumber(String labReferenceNumber) {
		this.labReferenceNumber = labReferenceNumber;
	}

	public LocalDate getDateCollected() {
		return dateCollected;
	}

	public void setDateCollected(LocalDate dateCollected) {
		this.dateCollected = dateCollected;
	}

	public String getReviewState() {
		return reviewState;
	}

	public void setReviewState(String reviewState) {
		this.reviewState = reviewState;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDispatched() {
		return dispatched;
	}

	public void setDispatched(String dispatched) {
		this.dispatched = dispatched;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public List<String> getAnalysisProfileNames() {
		return analysisProfileNames;
	}

	public void setAnalysisProfileNames(List<String> analysisProfileNames) {
		this.analysisProfileNames = analysisProfileNames;
	}

	public List<AnalysisProfileDTO> getAnalysisProfiles() {
		return analysisProfiles;
	}

	public void setAnalysisProfiles(List<AnalysisProfileDTO> analysisProfiles) {
		this.analysisProfiles = analysisProfiles;
	}

	public SampleType getSampleType() {
		return sampleType;
	}

	public void setSampleType(SampleType sampleType) {
		this.sampleType = sampleType;
	}

	public String getSentToLims() {
		return sentToLims;
	}

	public void setSentToLims(String sentToLims) {
		this.sentToLims = sentToLims;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public String getSampleTypeName() {
		return sampleTypeName;
	}

	public void setSampleTypeName(String sampleTypeName) {
		this.sampleTypeName = sampleTypeName;
	}

	public String getAnalysisRequestId() {
		return analysisRequestId;
	}

	public void setAnalysisRequestId(String analysisRequestId) {
		this.analysisRequestId = analysisRequestId;
	}

	public String getAnalysisRequestUid() {
		return analysisRequestUid;
	}

	public void setAnalysisRequestUid(String analysisRequestUid) {
		this.analysisRequestUid = analysisRequestUid;
	}

	public String getPatientUid() {
		return patientUid;
	}

	public void setPatientUid(String patientUid) {
		this.patientUid = patientUid;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public LocalDate getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(LocalDate dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public String getRetest() {
		return retest;
	}

	public void setRetest(String retest) {
		this.retest = retest;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public LocalDate getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDate modificationDate) {
		this.modificationDate = modificationDate;
	}

	public LocalDate getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(LocalDate dateReceived) {
		this.dateReceived = dateReceived;
	}

	public String getInvalidated() {
		return invalidated;
	}

	public void setInvalidated(String invalidated) {
		this.invalidated = invalidated;
	}

	public LocalDate getDateSampled() {
		return dateSampled;
	}

	public void setDateSampled(LocalDate dateSampled) {
		this.dateSampled = dateSampled;
	}

	public Boolean getPrinted() {
		return printed;
	}

	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}

	public LocalDate getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(LocalDate datePublished) {
		this.datePublished = datePublished;
	}

	public LocalDate getDatePrinted() {
		return datePrinted;
	}

	public void setDatePrinted(LocalDate datePrinted) {
		this.datePrinted = datePrinted;
	}

	public String getAnalyst() {
		return analyst;
	}

	public void setAnalyst(String analyst) {
		this.analyst = analyst;
	}

	public List<AnalysisDTO> getAnalyses() {
		return analyses;
	}

	public void setAnalyses(List<AnalysisDTO> analyses) {
		this.analyses = analyses;
	}

	public String getClientContactName() {
		return clientContactName;
	}

	public void setClientContactName(String clientContactName) {
		this.clientContactName = clientContactName;
	}

	public SenaiteClientResolverDTO getSenaiteClient() {
		return senaiteClient;
	}

	public void setSenaiteClient(SenaiteClientResolverDTO senaiteClient) {
		this.senaiteClient = senaiteClient;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Boolean getInternalUse() {
		return internalUse;
	}

	public void setInternalUse(Boolean internalUse) {
		this.internalUse = internalUse;
	}

	public List<AnalysisRequestRemarkDTO> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<AnalysisRequestRemarkDTO> remarks) {
		this.remarks = remarks;
	}

	public AnalysisRequestRejectionReasonDTO getRejectionReasons() {
		return rejectionReasons;
	}

	public void setRejectionReasons(AnalysisRequestRejectionReasonDTO rejectionReasons) {
		this.rejectionReasons = rejectionReasons;
	}

	public String getDateReceivedAtHub() {
		return dateReceivedAtHub;
	}

	public LocalDate getDateTested() {
		return dateTested;
	}

	public void setDateTested(LocalDate dateTested) {
		this.dateTested = dateTested;
	}

	public void setDateReceivedAtHub(String dateReceivedAtHub) {
		this.dateReceivedAtHub = dateReceivedAtHub;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AnalysisRequestAnalysisProfileDTO)) {
			return false;
		}

		return analysisRequestUid != null
				&& analysisRequestUid.equals(((AnalysisRequestAnalysisProfileDTO) o).analysisRequestUid);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "AnalysisRequestAnalysisProfileDTO [analysisRequestId=" + analysisRequestId + ", analysisRequestUid="
				+ analysisRequestUid + ", patientId=" + patientId + ", patientUid=" + patientUid + ", sampleId="
				+ sampleId + ", clientSampleId=" + clientSampleId + ", labId=" + labId + ", registrationClient="
				+ registrationClient + ", registrationClientId=" + registrationClientId + ", clientId=" + clientId
				+ ", clientContactId=" + clientContactId + ", clientContactName=" + clientContactName
				+ ", labReferenceNumber=" + labReferenceNumber + ", dateCollected=" + dateCollected + ", reviewState="
				+ reviewState + ", status=" + status + ", dispatched=" + dispatched + ", sampleTypeName="
				+ sampleTypeName + ", sampleType=" + sampleType + ", sentToLims=" + sentToLims + ", sync=" + sync
				+ ", creator=" + creator + ", dateRegistered=" + dateRegistered + ", retest=" + retest + ", province="
				+ province + ", modificationDate=" + modificationDate + ", dateReceived=" + dateReceived
				+ ", invalidated=" + invalidated + ", dateSampled=" + dateSampled + ", dateTested=" + dateTested
				+ ", printed=" + printed + ", datePublished=" + datePublished + ", datePrinted=" + datePrinted
				+ ", rejectionReasons=" + rejectionReasons + ", analyst=" + analyst + ", senaiteClient=" + senaiteClient
				+ ", dateReceivedAtHub=" + dateReceivedAtHub + ", source=" + source + ", remarks=" + remarks
				+ ", analysisProfileNames=" + analysisProfileNames + ", analysisProfiles=" + analysisProfiles
				+ ", analyses=" + analyses + ", priority=" + priority + ", internalUse=" + internalUse + "]";
	}

}
