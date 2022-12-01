package zw.org.nmrl.poc.device.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link zw.org.nmrl.poc.device.domain.SamplePatient} entity. This class is used
 * in {@link zw.org.nmrl.poc.device.web.rest.SamplePatientResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sample-patients?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SamplePatientCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter firstName;

    private StringFilter lastName;

    private LocalDateFilter dob;

    private StringFilter age;

    private StringFilter gender;

    private StringFilter primaryReferrer;

    private StringFilter clientPatientId;

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

    public SamplePatientCriteria() {}

    public SamplePatientCriteria(SamplePatientCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.dob = other.dob == null ? null : other.dob.copy();
        this.age = other.age == null ? null : other.age.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.primaryReferrer = other.primaryReferrer == null ? null : other.primaryReferrer.copy();
        this.clientPatientId = other.clientPatientId == null ? null : other.clientPatientId.copy();
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
    public SamplePatientCriteria copy() {
        return new SamplePatientCriteria(this);
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

    public StringFilter getFirstName() {
        return firstName;
    }

    public StringFilter firstName() {
        if (firstName == null) {
            firstName = new StringFilter();
        }
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public StringFilter lastName() {
        if (lastName == null) {
            lastName = new StringFilter();
        }
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public LocalDateFilter getDob() {
        return dob;
    }

    public LocalDateFilter dob() {
        if (dob == null) {
            dob = new LocalDateFilter();
        }
        return dob;
    }

    public void setDob(LocalDateFilter dob) {
        this.dob = dob;
    }

    public StringFilter getAge() {
        return age;
    }

    public StringFilter age() {
        if (age == null) {
            age = new StringFilter();
        }
        return age;
    }

    public void setAge(StringFilter age) {
        this.age = age;
    }

    public StringFilter getGender() {
        return gender;
    }

    public StringFilter gender() {
        if (gender == null) {
            gender = new StringFilter();
        }
        return gender;
    }

    public void setGender(StringFilter gender) {
        this.gender = gender;
    }

    public StringFilter getPrimaryReferrer() {
        return primaryReferrer;
    }

    public StringFilter primaryReferrer() {
        if (primaryReferrer == null) {
            primaryReferrer = new StringFilter();
        }
        return primaryReferrer;
    }

    public void setPrimaryReferrer(StringFilter primaryReferrer) {
        this.primaryReferrer = primaryReferrer;
    }

    public StringFilter getClientPatientId() {
        return clientPatientId;
    }

    public StringFilter clientPatientId() {
        if (clientPatientId == null) {
            clientPatientId = new StringFilter();
        }
        return clientPatientId;
    }

    public void setClientPatientId(StringFilter clientPatientId) {
        this.clientPatientId = clientPatientId;
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
        final SamplePatientCriteria that = (SamplePatientCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(dob, that.dob) &&
            Objects.equals(age, that.age) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(primaryReferrer, that.primaryReferrer) &&
            Objects.equals(clientPatientId, that.clientPatientId) &&
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
            firstName,
            lastName,
            dob,
            age,
            gender,
            primaryReferrer,
            clientPatientId,
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
        return "SamplePatientCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (firstName != null ? "firstName=" + firstName + ", " : "") +
            (lastName != null ? "lastName=" + lastName + ", " : "") +
            (dob != null ? "dob=" + dob + ", " : "") +
            (age != null ? "age=" + age + ", " : "") +
            (gender != null ? "gender=" + gender + ", " : "") +
            (primaryReferrer != null ? "primaryReferrer=" + primaryReferrer + ", " : "") +
            (clientPatientId != null ? "clientPatientId=" + clientPatientId + ", " : "") +
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
