package zw.org.nmrl.poc.device.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link zw.org.nmrl.domain.Batch} entity.
 */
public class BatchDTO implements Serializable {
    private String batchId;

    private String batchUid;

    private String labId;

    private Boolean breastFeeding;

    private Boolean pregnant;

    private String reasonForVlTest;

    private LocalDate batchDate;

    private String poorAdherence;

    private String enhancedSessions;

    private String lastResults;

    private String treatmentHistory;

    private String treatmentHistoryValue;

    private String laboratoryRequestId;

    private LocalDate creationDate;

    private String CaseType;

    private String title;

    private String EIDHIVResult;

    private String reasonForEidTest;

    private String eidMotherHivTested;

    private LocalDate modificationDate;

    private String symptoms;

    private String clientBatchId;

    private String reasonForVlTestOther;

    private String eidEntryPointOther;

    private String menstrualStatus;

    private String reasonForEidTestOther;

    private String eidMotherAnc;

    private String eidModeOfDelivery;

    public Boolean getBreastFeeding() {
        return breastFeeding;
    }

    public void setBreastFeeding(Boolean breastFeeding) {
        this.breastFeeding = breastFeeding;
    }

    public Boolean getPregnant() {
        return pregnant;
    }

    public void setPregnant(Boolean pregnant) {
        this.pregnant = pregnant;
    }

    public String getReasonForVlTest() {
        return reasonForVlTest;
    }

    public void setReasonForVlTest(String reasonForVlTest) {
        this.reasonForVlTest = reasonForVlTest;
    }

    public LocalDate getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(LocalDate batchDate) {
        this.batchDate = batchDate;
    }

    public String getPoorAdherence() {
        return poorAdherence;
    }

    public void setPoorAdherence(String poorAdherence) {
        this.poorAdherence = poorAdherence;
    }

    public String getEnhancedSessions() {
        return enhancedSessions;
    }

    public void setEnhancedSessions(String enhancedSessions) {
        this.enhancedSessions = enhancedSessions;
    }

    public String getLastResults() {
        return lastResults;
    }

    public void setLastResults(String lastResults) {
        this.lastResults = lastResults;
    }

    public String getTreatmentHistory() {
        return treatmentHistory;
    }

    public void setTreatmentHistory(String treatmentHistory) {
        this.treatmentHistory = treatmentHistory;
    }

    public String getTreatmentHistoryValue() {
        return treatmentHistoryValue;
    }

    public void setTreatmentHistoryValue(String treatmentHistoryValue) {
        this.treatmentHistoryValue = treatmentHistoryValue;
    }

    public String getLaboratoryRequestId() {
        return laboratoryRequestId;
    }

    public void setLaboratoryRequestId(String laboratoryRequestId) {
        this.laboratoryRequestId = laboratoryRequestId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchUid() {
        return batchUid;
    }

    public void setBatchUid(String batchUid) {
        this.batchUid = batchUid;
    }

    public String getCaseType() {
        return CaseType;
    }

    public void setCaseType(String caseType) {
        CaseType = caseType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEIDHIVResult() {
        return EIDHIVResult;
    }

    public void setEIDHIVResult(String eIDHIVResult) {
        EIDHIVResult = eIDHIVResult;
    }

    public String getReasonForEidTest() {
        return reasonForEidTest;
    }

    public void setReasonForEidTest(String reasonForEidTest) {
        this.reasonForEidTest = reasonForEidTest;
    }

    public String getEidMotherHivTested() {
        return eidMotherHivTested;
    }

    public void setEidMotherHivTested(String eidMotherHivTested) {
        this.eidMotherHivTested = eidMotherHivTested;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BatchDTO)) {
            return false;
        }

        return batchUid != null && batchUid.equals(((BatchDTO) o).batchUid);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return (
            "BatchDTO [batchId=" +
            batchId +
            ", batchUid=" +
            batchUid +
            ", labId=" +
            labId +
            ", breastFeeding=" +
            breastFeeding +
            ", pregnant=" +
            pregnant +
            ", reasonForVlTest=" +
            reasonForVlTest +
            ", batchDate=" +
            batchDate +
            ", poorAdherence=" +
            poorAdherence +
            ", enhancedSessions=" +
            enhancedSessions +
            ", lastResults=" +
            lastResults +
            ", treatmentHistory=" +
            treatmentHistory +
            ", treatmentHistoryValue=" +
            treatmentHistoryValue +
            ", laboratoryRequestId=" +
            laboratoryRequestId +
            ", creationDate=" +
            creationDate +
            ", CaseType=" +
            CaseType +
            ", title=" +
            title +
            ", EIDHIVResult=" +
            EIDHIVResult +
            ", reasonForEidTest=" +
            reasonForEidTest +
            ", eidMotherHivTested=" +
            eidMotherHivTested +
            ", modificationDate=" +
            modificationDate +
            ", symptoms=" +
            symptoms +
            "]"
        );
    }
}
