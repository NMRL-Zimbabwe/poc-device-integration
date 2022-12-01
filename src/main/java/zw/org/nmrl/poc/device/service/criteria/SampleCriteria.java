package zw.org.nmrl.poc.device.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link zw.org.nmrl.poc.device.domain.Sample} entity. This class is used
 * in {@link zw.org.nmrl.poc.device.web.rest.SampleResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /samples?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SampleCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter patientId;

    private StringFilter clientSampleId;

    private StringFilter clientContact;

    private StringFilter sampleTypeId;

    private StringFilter sampleTypeName;

    private StringFilter analysisServiceId;

    private StringFilter analysisServiceName;

    private LocalDateFilter dateCollected;

    private LocalDateFilter dateRegistered;

    private LocalDateFilter dateTested;

    private StringFilter result;

    private StringFilter unit;

    private LocalDateFilter datePublished;

    private StringFilter state;

    private Boolean distinct;

    public SampleCriteria() {}

    public SampleCriteria(SampleCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.patientId = other.patientId == null ? null : other.patientId.copy();
        this.clientSampleId = other.clientSampleId == null ? null : other.clientSampleId.copy();
        this.clientContact = other.clientContact == null ? null : other.clientContact.copy();
        this.sampleTypeId = other.sampleTypeId == null ? null : other.sampleTypeId.copy();
        this.sampleTypeName = other.sampleTypeName == null ? null : other.sampleTypeName.copy();
        this.analysisServiceId = other.analysisServiceId == null ? null : other.analysisServiceId.copy();
        this.analysisServiceName = other.analysisServiceName == null ? null : other.analysisServiceName.copy();
        this.dateCollected = other.dateCollected == null ? null : other.dateCollected.copy();
        this.dateRegistered = other.dateRegistered == null ? null : other.dateRegistered.copy();
        this.dateTested = other.dateTested == null ? null : other.dateTested.copy();
        this.result = other.result == null ? null : other.result.copy();
        this.unit = other.unit == null ? null : other.unit.copy();
        this.datePublished = other.datePublished == null ? null : other.datePublished.copy();
        this.state = other.state == null ? null : other.state.copy();
        this.distinct = other.distinct;
    }

    @Override
    public SampleCriteria copy() {
        return new SampleCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPatientId() {
        return patientId;
    }

    public StringFilter patientId() {
        if (patientId == null) {
            patientId = new StringFilter();
        }
        return patientId;
    }

    public void setPatientId(StringFilter patientId) {
        this.patientId = patientId;
    }

    public StringFilter getClientSampleId() {
        return clientSampleId;
    }

    public StringFilter clientSampleId() {
        if (clientSampleId == null) {
            clientSampleId = new StringFilter();
        }
        return clientSampleId;
    }

    public void setClientSampleId(StringFilter clientSampleId) {
        this.clientSampleId = clientSampleId;
    }

    public StringFilter getClientContact() {
        return clientContact;
    }

    public StringFilter clientContact() {
        if (clientContact == null) {
            clientContact = new StringFilter();
        }
        return clientContact;
    }

    public void setClientContact(StringFilter clientContact) {
        this.clientContact = clientContact;
    }

    public StringFilter getSampleTypeId() {
        return sampleTypeId;
    }

    public StringFilter sampleTypeId() {
        if (sampleTypeId == null) {
            sampleTypeId = new StringFilter();
        }
        return sampleTypeId;
    }

    public void setSampleTypeId(StringFilter sampleTypeId) {
        this.sampleTypeId = sampleTypeId;
    }

    public StringFilter getSampleTypeName() {
        return sampleTypeName;
    }

    public StringFilter sampleTypeName() {
        if (sampleTypeName == null) {
            sampleTypeName = new StringFilter();
        }
        return sampleTypeName;
    }

    public void setSampleTypeName(StringFilter sampleTypeName) {
        this.sampleTypeName = sampleTypeName;
    }

    public StringFilter getAnalysisServiceId() {
        return analysisServiceId;
    }

    public StringFilter analysisServiceId() {
        if (analysisServiceId == null) {
            analysisServiceId = new StringFilter();
        }
        return analysisServiceId;
    }

    public void setAnalysisServiceId(StringFilter analysisServiceId) {
        this.analysisServiceId = analysisServiceId;
    }

    public StringFilter getAnalysisServiceName() {
        return analysisServiceName;
    }

    public StringFilter analysisServiceName() {
        if (analysisServiceName == null) {
            analysisServiceName = new StringFilter();
        }
        return analysisServiceName;
    }

    public void setAnalysisServiceName(StringFilter analysisServiceName) {
        this.analysisServiceName = analysisServiceName;
    }

    public LocalDateFilter getDateCollected() {
        return dateCollected;
    }

    public LocalDateFilter dateCollected() {
        if (dateCollected == null) {
            dateCollected = new LocalDateFilter();
        }
        return dateCollected;
    }

    public void setDateCollected(LocalDateFilter dateCollected) {
        this.dateCollected = dateCollected;
    }

    public LocalDateFilter getDateRegistered() {
        return dateRegistered;
    }

    public LocalDateFilter dateRegistered() {
        if (dateRegistered == null) {
            dateRegistered = new LocalDateFilter();
        }
        return dateRegistered;
    }

    public void setDateRegistered(LocalDateFilter dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public LocalDateFilter getDateTested() {
        return dateTested;
    }

    public LocalDateFilter dateTested() {
        if (dateTested == null) {
            dateTested = new LocalDateFilter();
        }
        return dateTested;
    }

    public void setDateTested(LocalDateFilter dateTested) {
        this.dateTested = dateTested;
    }

    public StringFilter getResult() {
        return result;
    }

    public StringFilter result() {
        if (result == null) {
            result = new StringFilter();
        }
        return result;
    }

    public void setResult(StringFilter result) {
        this.result = result;
    }

    public StringFilter getUnit() {
        return unit;
    }

    public StringFilter unit() {
        if (unit == null) {
            unit = new StringFilter();
        }
        return unit;
    }

    public void setUnit(StringFilter unit) {
        this.unit = unit;
    }

    public LocalDateFilter getDatePublished() {
        return datePublished;
    }

    public LocalDateFilter datePublished() {
        if (datePublished == null) {
            datePublished = new LocalDateFilter();
        }
        return datePublished;
    }

    public void setDatePublished(LocalDateFilter datePublished) {
        this.datePublished = datePublished;
    }

    public StringFilter getState() {
        return state;
    }

    public StringFilter state() {
        if (state == null) {
            state = new StringFilter();
        }
        return state;
    }

    public void setState(StringFilter state) {
        this.state = state;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SampleCriteria that = (SampleCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(patientId, that.patientId) &&
            Objects.equals(clientSampleId, that.clientSampleId) &&
            Objects.equals(clientContact, that.clientContact) &&
            Objects.equals(sampleTypeId, that.sampleTypeId) &&
            Objects.equals(sampleTypeName, that.sampleTypeName) &&
            Objects.equals(analysisServiceId, that.analysisServiceId) &&
            Objects.equals(analysisServiceName, that.analysisServiceName) &&
            Objects.equals(dateCollected, that.dateCollected) &&
            Objects.equals(dateRegistered, that.dateRegistered) &&
            Objects.equals(dateTested, that.dateTested) &&
            Objects.equals(result, that.result) &&
            Objects.equals(unit, that.unit) &&
            Objects.equals(datePublished, that.datePublished) &&
            Objects.equals(state, that.state) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            patientId,
            clientSampleId,
            clientContact,
            sampleTypeId,
            sampleTypeName,
            analysisServiceId,
            analysisServiceName,
            dateCollected,
            dateRegistered,
            dateTested,
            result,
            unit,
            datePublished,
            state,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SampleCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (patientId != null ? "patientId=" + patientId + ", " : "") +
            (clientSampleId != null ? "clientSampleId=" + clientSampleId + ", " : "") +
            (clientContact != null ? "clientContact=" + clientContact + ", " : "") +
            (sampleTypeId != null ? "sampleTypeId=" + sampleTypeId + ", " : "") +
            (sampleTypeName != null ? "sampleTypeName=" + sampleTypeName + ", " : "") +
            (analysisServiceId != null ? "analysisServiceId=" + analysisServiceId + ", " : "") +
            (analysisServiceName != null ? "analysisServiceName=" + analysisServiceName + ", " : "") +
            (dateCollected != null ? "dateCollected=" + dateCollected + ", " : "") +
            (dateRegistered != null ? "dateRegistered=" + dateRegistered + ", " : "") +
            (dateTested != null ? "dateTested=" + dateTested + ", " : "") +
            (result != null ? "result=" + result + ", " : "") +
            (unit != null ? "unit=" + unit + ", " : "") +
            (datePublished != null ? "datePublished=" + datePublished + ", " : "") +
            (state != null ? "state=" + state + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
