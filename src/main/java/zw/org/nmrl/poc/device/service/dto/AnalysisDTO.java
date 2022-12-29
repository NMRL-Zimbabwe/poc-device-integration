package zw.org.nmrl.poc.device.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

import zw.org.nmrl.poc.device.domain.AnalysisService;

/**
 * A DTO for the {@link zw.org.nmrl.domain.Analysis} entity.
 */
public class AnalysisDTO implements Serializable {
    private String analysisId;

    private String analysisUid;

    private String analysisRequestId;

    private String analysisRequestUid;

    private String result;

    private String dateTested;

    private String labId;

    private String status;

    private String reviewState;

    private String analyst;

    private String reviewer;

    private String remarks;

    private String method;

    private String methodName;

    private String instrumentName;

    private String instrument;

    private String submitter;

    private Boolean hidden;

    private Boolean retest;

    private String retestOf;

    private String worksheetNumber;

    private String laboratoryRequestId;

    private AnalysisService analysisService;

    private String analysisServiceName;

    private String title;

    private LocalDate modified;

    private String unit;

    private String analysisName;

    private String interpretedResult;

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDateTested() {
        return dateTested;
    }

    public void setDateTested(String dateTested) {
        this.dateTested = dateTested;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewState() {
        return reviewState;
    }

    public void setReviewState(String reviewState) {
        this.reviewState = reviewState;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getRetest() {
        return retest;
    }

    public void setRetest(Boolean retest) {
        this.retest = retest;
    }

    public String getRetestOf() {
        return retestOf;
    }

    public void setRetestOf(String retestOf) {
        this.retestOf = retestOf;
    }

    public String getWorksheetNumber() {
        return worksheetNumber;
    }

    public void setWorksheetNumber(String worksheetNumber) {
        this.worksheetNumber = worksheetNumber;
    }

    public String getLaboratoryRequestId() {
        return laboratoryRequestId;
    }

    public void setLaboratoryRequestId(String laboratoryRequestId) {
        this.laboratoryRequestId = laboratoryRequestId;
    }

    public AnalysisService getAnalysisService() {
        return analysisService;
    }

    public void setAnalysisService(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    public String getAnalysisUid() {
        return analysisUid;
    }

    public void setAnalysisUid(String analysisUid) {
        this.analysisUid = analysisUid;
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

    public String getAnalysisServiceName() {
        return analysisServiceName;
    }

    public void setAnalysisServiceName(String analysisServiceName) {
        this.analysisServiceName = analysisServiceName;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
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

    public String getAnalysisName() {
        return analysisName;
    }

    public void setAnalysisName(String analysisName) {
        this.analysisName = analysisName;
    }

    public String getInterpretedResult() {
        return interpretedResult;
    }

    public void setInterpretedResult(String interpretedResult) {
        this.interpretedResult = interpretedResult;
    }

    @Override
    public String toString() {
        return (
            "AnalysisDTO [analysisId=" +
            analysisId +
            ", analysisUid=" +
            analysisUid +
            ", analysisRequestId=" +
            analysisRequestId +
            ", analysisRequestUid=" +
            analysisRequestUid +
            ", result=" +
            result +
            ", dateTested=" +
            dateTested +
            ", labId=" +
            labId +
            ", status=" +
            status +
            ", reviewState=" +
            reviewState +
            ", analyst=" +
            analyst +
            ", reviewer=" +
            reviewer +
            ", remarks=" +
            remarks +
            ", method=" +
            method +
            ", methodName=" +
            methodName +
            ", instrumentName=" +
            instrumentName +
            ", instrument=" +
            instrument +
            ", submitter=" +
            submitter +
            ", hidden=" +
            hidden +
            ", retest=" +
            retest +
            ", retestOf=" +
            retestOf +
            ", worksheetNumber=" +
            worksheetNumber +
            ", laboratoryRequestId=" +
            laboratoryRequestId +
            ", analysisService=" +
            analysisService +
            ", analysisServiceName=" +
            analysisServiceName +
            ", title=" +
            title +
            ", modified=" +
            modified +
            ", unit=" +
            unit +
            ", analysisName=" +
            analysisName +
            ", interpretedResult=" +
            interpretedResult +
            "]"
        );
    }
}
