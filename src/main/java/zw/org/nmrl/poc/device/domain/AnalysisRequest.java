package zw.org.nmrl.poc.device.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import zw.org.nmrl.poc.device.domain.id.AnalysisRequestId;

/**
 * A LaboratoryRequest AKA Sample.
 */
@Entity
@Table(name = "analysis_request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AnalysisRequest extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "analysis_request_id", unique = true, nullable = false)
	private String analysisRequestId;

	@Column(name = "analysis_request_uid")
	private String analysisRequestUid;

	@Column(name = "patient_id")
	private String patientId;

	@Column(name = "patient_uid")
	private String patientUid;

	@Column(name = "lab_id")
	private String labId;

	@Column(name = "client_sample_id")
	private String clientSampleId;

	@Column(name = "registration_client_id") // POC
	private String registrationClientId;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "client_contact_id")
	private String clientContactId;

	@Column(name = "client_contact_name")
	private String clientContactName;

	private String creator;

	private String retest;

	private String province;

	@Column(name = "modification_date")
	private LocalDate modificationDate;

	private String analyst;

	private String dispatched;

	@Column(name = "date_collected")
	private LocalDate dateCollected;

	@Column(name = "date_registered")
	private LocalDate dateRegistered;

	@Column(name = "date_received")
	private LocalDate dateReceived;

	private String invalidated;

	@Column(name = "date_sampled")
	private LocalDate dateSampled;

	@Column(name = "review_state")
	private String reviewState;

	@Column(name = "status")
	private String status;

	@Column(name = "printed")
	private Boolean printed; // add to liquibase

	@Column(name = "date_published")
	private LocalDate datePublished;

	@Column(name = "date_printed")
	private LocalDate datePrinted;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "batch_uid")
	private String batchUid;

	@Column(name = "source")
	private String source;

	@Column(name = "sample_type_id")
	private String sampleTypeId;

	@Column(name = "sample_type_alias")
	private String sampleTypeIdAlias;

	private String sync;

	private int retry;

	@Column(name = "error_reason")
	private String errorReason;

	@Column(name = "sent_to_lims")
	private String sentToLims;

	// jhipster-needle-entity-add-field - JHipster will add fields here

	public String getClientSampleId() {
		return clientSampleId;
	}

	public AnalysisRequest clientSampleId(String clientSampleId) {
		this.clientSampleId = clientSampleId;
		return this;
	}

	public void setClientSampleId(String clientSampleId) {
		this.clientSampleId = clientSampleId;
	}

	public String getLabId() {
		return labId;
	}

	public AnalysisRequest labId(String labId) {
		this.labId = labId;
		return this;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	public String getRegistrationClientId() {
		return registrationClientId;
	}

	public AnalysisRequest registrationClientId(String registrationClientId) {
		this.registrationClientId = registrationClientId;
		return this;
	}

	public void setRegistrationClientId(String registrationClientId) {
		this.registrationClientId = registrationClientId;
	}

	public String getClientId() {
		return clientId;
	}

	public AnalysisRequest clientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientContactId() {
		return clientContactId;
	}

	public AnalysisRequest clientContactId(String clientContactId) {
		this.clientContactId = clientContactId;
		return this;
	}

	public void setClientContactId(String clientContactId) {
		this.clientContactId = clientContactId;
	}

	public LocalDate getDateCollected() {
		return dateCollected;
	}

	public AnalysisRequest dateCollected(LocalDate dateCollected) {
		this.dateCollected = dateCollected;
		return this;
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

	public AnalysisRequest status(String status) {
		this.status = status;
		return this;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public AnalysisRequest remarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public String getSentToLims() {
		return sentToLims;
	}

	public void setSentToLims(String sentToLims) {
		this.sentToLims = sentToLims;
	}

	public String getSampleTypeId() {
		return sampleTypeId;
	}

	public void setSampleType(String sampleType) {
		this.sampleTypeId = sampleType;
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

	public String getAnalyst() {
		return analyst;
	}

	public void setAnalyst(String analyst) {
		this.analyst = analyst;
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

	public String getPatientUid() {
		return patientUid;
	}

	public void setPatientUid(String patientUid) {
		this.patientUid = patientUid;
	}

	public String getBatchUid() {
		return batchUid;
	}

	public void setBatchUid(String batchUid) {
		this.batchUid = batchUid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDispatched() {
		return dispatched;
	}

	public void setDispatched(String dispatched) {
		this.dispatched = dispatched;
	}

	public String getAnalysisRequestId() {
		return analysisRequestId;
	}

	public void setAnalysisRequestId(String analysisRequestId) {
		this.analysisRequestId = analysisRequestId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getClientContactName() {
		return clientContactName;
	}

	public void setClientContactName(String clientContactName) {
		this.clientContactName = clientContactName;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSampleTypeIdAlias() {
		return sampleTypeIdAlias;
	}

	public void setSampleTypeIdAlias(String sampleTypeIdAlias) {
		this.sampleTypeIdAlias = sampleTypeIdAlias;
	}

	public void setSampleTypeId(String sampleTypeId) {
		this.sampleTypeId = sampleTypeId;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public String getAnalysisRequestUid() {
		return analysisRequestUid;
	}

	public void setAnalysisRequestUid(String analysisRequestUid) {
		this.analysisRequestUid = analysisRequestUid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AnalysisRequest)) {
			return false;
		}
		return analysisRequestId != null && analysisRequestId.equals(((AnalysisRequest) o).analysisRequestId);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	

}
