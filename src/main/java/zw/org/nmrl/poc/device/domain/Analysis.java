package zw.org.nmrl.poc.device.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import zw.org.nmrl.poc.device.domain.id.AnalysisId;

/**
 * A Analysis.
 */
@Entity
@Table(name = "analysis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Analysis extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "analysis_id", unique = true, nullable = false)
	private String analysisId;

	@Column(name = "analysis_uid")
	private String analysisUid;

	@Column(name = "analysis_request_id")
	private String analysisRequestId;

	@Column(name = "analysis_request_uid")
	private String analysisRequestUid;

	@Column(name = "lab_id")
	private String labId;

	@Column(name = "result")
	private String result;

	@Column(name = "date_tested")
	private String dateTested;

	@Column(name = "status")
	private String status;

	@Column(name = "review_state")
	private String reviewState;

	@Column(name = "analyst")
	private String analyst;

	@Column(name = "reviewer")
	private String reviewer;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "method")
	private String method;

	@Column(name = "instrument")
	private String instrument;

	@Column(name = "submitter")
	private String submitter;

	@Column(name = "hidden")
	private Boolean hidden;

	@Column(name = "retest")
	private Boolean retest;

	@Column(name = "retest_of")
	private String retestOf;

	@Column(name = "worksheet_number")
	private String worksheetNumber;

	private String title;

	private LocalDate modified;

	private String unit;

	@Column(name = "result_capture_date")
	private LocalDate resultCaptureDate;

	@JoinColumn(name = "analysis_service_id")
	private String analysisServiceId;

	@Column(name = "interpreted_result")
	private String interpretedResult;

	@Column(name = "source")
	private String source;

	public String getResult() {
		return result;
	}

	public String getAnalysisId() {
		return analysisId;
	}

	public void setAnalysisId(String analysisId) {
		this.analysisId = analysisId;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public Boolean getRetest() {
		return retest;
	}

	public Analysis result(String result) {
		this.result = result;
		return this;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDateTested() {
		return dateTested;
	}

	public Analysis dateTested(String dateTested) {
		this.dateTested = dateTested;
		return this;
	}

	public void setDateTested(String dateTested) {
		this.dateTested = dateTested;
	}

	public String getStatus() {
		return status;
	}

	public Analysis status(String status) {
		this.status = status;
		return this;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReviewState() {
		return reviewState;
	}

	public Analysis reviewState(String reviewState) {
		this.reviewState = reviewState;
		return this;
	}

	public void setReviewState(String reviewState) {
		this.reviewState = reviewState;
	}

	public String getAnalyst() {
		return analyst;
	}

	public Analysis analyst(String analyst) {
		this.analyst = analyst;
		return this;
	}

	public void setAnalyst(String analyst) {
		this.analyst = analyst;
	}

	public String getReviewer() {
		return reviewer;
	}

	public Analysis reviewer(String reviewer) {
		this.reviewer = reviewer;
		return this;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getRemarks() {
		return remarks;
	}

	public Analysis remarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMethod() {
		return method;
	}

	public Analysis method(String method) {
		this.method = method;
		return this;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getInstrument() {
		return instrument;
	}

	public Analysis instrument(String instrument) {
		this.instrument = instrument;
		return this;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getSubmitter() {
		return submitter;
	}

	public Analysis submitter(String submitter) {
		this.submitter = submitter;
		return this;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public Boolean isHidden() {
		return hidden;
	}

	public Analysis hidden(Boolean hidden) {
		this.hidden = hidden;
		return this;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Boolean isRetest() {
		return retest;
	}

	public Analysis retest(Boolean retest) {
		this.retest = retest;
		return this;
	}

	public void setRetest(Boolean retest) {
		this.retest = retest;
	}

	public String getRetestOf() {
		return retestOf;
	}

	public Analysis retestOf(String retestOf) {
		this.retestOf = retestOf;
		return this;
	}

	public void setRetestOf(String retestOf) {
		this.retestOf = retestOf;
	}

	public String getWorksheetNumber() {
		return worksheetNumber;
	}

	public Analysis worksheetNumber(String worksheetNumber) {
		this.worksheetNumber = worksheetNumber;
		return this;
	}

	public void setWorksheetNumber(String worksheetNumber) {
		this.worksheetNumber = worksheetNumber;
	}

	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	public String getAnalysisRequestUid() {
		return analysisRequestUid;
	}

	public void setAnalysisRequestUid(String analysisRequestUid) {
		this.analysisRequestUid = analysisRequestUid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getModified() {
		return modified;
	}

	public void setModified(LocalDate modified) {
		this.modified = modified;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public LocalDate getResultCaptureDate() {
		return resultCaptureDate;
	}

	public void setResultCaptureDate(LocalDate resultCaptureDate) {
		this.resultCaptureDate = resultCaptureDate;
	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here

	public String getAnalysisServiceId() {
		return analysisServiceId;
	}

	public void setAnalysisServiceId(String analysisServiceId) {
		this.analysisServiceId = analysisServiceId;
	}

	public String getAnalysisRequestId() {
		return analysisRequestId;
	}

	public void setAnalysisRequestId(String analysisRequestId) {
		this.analysisRequestId = analysisRequestId;
	}

	public String getInterpretedResult() {
		return interpretedResult;
	}

	public void setInterpretedResult(String interpretedResult) {
		this.interpretedResult = interpretedResult;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAnalysisUid() {
		return analysisUid;
	}

	public void setAnalysisUid(String analysisUid) {
		this.analysisUid = analysisUid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Analysis)) {
			return false;
		}
		return analysisId != null && analysisId.equals(((Analysis) o).analysisId);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Analysis [analysisId=" + analysisId + ", analysisUid=" + analysisUid + ", analysisRequestId="
				+ analysisRequestId + ", analysisRequestUid=" + analysisRequestUid + ", labId=" + labId + ", result="
				+ result + ", dateTested=" + dateTested + ", status=" + status + ", reviewState=" + reviewState
				+ ", analyst=" + analyst + ", reviewer=" + reviewer + ", remarks=" + remarks + ", method=" + method
				+ ", instrument=" + instrument + ", submitter=" + submitter + ", hidden=" + hidden + ", retest="
				+ retest + ", retestOf=" + retestOf + ", worksheetNumber=" + worksheetNumber + ", title=" + title
				+ ", modified=" + modified + ", unit=" + unit + ", resultCaptureDate=" + resultCaptureDate
				+ ", analysisServiceId=" + analysisServiceId + ", interpretedResult=" + interpretedResult + ", source="
				+ source + "]";
	}

}
