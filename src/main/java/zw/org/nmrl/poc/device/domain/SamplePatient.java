package zw.org.nmrl.poc.device.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A SamplePatient.
 */
@Entity
@Table(name = "sample_patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SamplePatient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "external_unique_identifer")
    private String externalUniqueIdentifer;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "age")
    private String age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "primary_referrer")
    private String primaryReferrer;

    @Column(name = "client_patient_id")
    private String clientPatientId;

    @Column(name = "client_sample_id")
    private String clientSampleId;

    @Column(name = "client_contact")
    private String clientContact;

    @Column(name = "sample_type_id")
    private String sampleTypeId;

    @Column(name = "sample_type_name")
    private String sampleTypeName;

    @Column(name = "analysis_service_id")
    private String analysisServiceId;

    @Column(name = "analysis_service_name")
    private String analysisServiceName;

    @Column(name = "date_collected")
    private LocalDate dateCollected;

    @Column(name = "date_registered")
    private LocalDate dateRegistered;

    @Column(name = "date_tested")
    private LocalDate dateTested;

    @Column(name = "result")
    private String result;

    @Column(name = "unit")
    private String unit;

    @Column(name = "date_published")
    private LocalDate datePublished;

    @Column(name = "state")
    private String state;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getExternalUniqueIdentifer() {
        return externalUniqueIdentifer;
    }

    public void setExternalUniqueIdentifer(String externalUniqueIdentifer) {
        this.externalUniqueIdentifer = externalUniqueIdentifer;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public SamplePatient firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public SamplePatient lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public SamplePatient dob(LocalDate dob) {
        this.setDob(dob);
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAge() {
        return this.age;
    }

    public SamplePatient age(String age) {
        this.setAge(age);
        return this;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public SamplePatient gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPrimaryReferrer() {
        return this.primaryReferrer;
    }

    public SamplePatient primaryReferrer(String primaryReferrer) {
        this.setPrimaryReferrer(primaryReferrer);
        return this;
    }

    public void setPrimaryReferrer(String primaryReferrer) {
        this.primaryReferrer = primaryReferrer;
    }

    public String getClientPatientId() {
        return this.clientPatientId;
    }

    public SamplePatient clientPatientId(String clientPatientId) {
        this.setClientPatientId(clientPatientId);
        return this;
    }

    public void setClientPatientId(String clientPatientId) {
        this.clientPatientId = clientPatientId;
    }

    public String getClientSampleId() {
        return this.clientSampleId;
    }

    public SamplePatient clientSampleId(String clientSampleId) {
        this.setClientSampleId(clientSampleId);
        return this;
    }

    public void setClientSampleId(String clientSampleId) {
        this.clientSampleId = clientSampleId;
    }

    public String getClientContact() {
        return this.clientContact;
    }

    public SamplePatient clientContact(String clientContact) {
        this.setClientContact(clientContact);
        return this;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public String getSampleTypeId() {
        return this.sampleTypeId;
    }

    public SamplePatient sampleTypeId(String sampleTypeId) {
        this.setSampleTypeId(sampleTypeId);
        return this;
    }

    public void setSampleTypeId(String sampleTypeId) {
        this.sampleTypeId = sampleTypeId;
    }

    public String getSampleTypeName() {
        return this.sampleTypeName;
    }

    public SamplePatient sampleTypeName(String sampleTypeName) {
        this.setSampleTypeName(sampleTypeName);
        return this;
    }

    public void setSampleTypeName(String sampleTypeName) {
        this.sampleTypeName = sampleTypeName;
    }

    public String getAnalysisServiceId() {
        return this.analysisServiceId;
    }

    public SamplePatient analysisServiceId(String analysisServiceId) {
        this.setAnalysisServiceId(analysisServiceId);
        return this;
    }

    public void setAnalysisServiceId(String analysisServiceId) {
        this.analysisServiceId = analysisServiceId;
    }

    public String getAnalysisServiceName() {
        return this.analysisServiceName;
    }

    public SamplePatient analysisServiceName(String analysisServiceName) {
        this.setAnalysisServiceName(analysisServiceName);
        return this;
    }

    public void setAnalysisServiceName(String analysisServiceName) {
        this.analysisServiceName = analysisServiceName;
    }

    public LocalDate getDateCollected() {
        return this.dateCollected;
    }

    public SamplePatient dateCollected(LocalDate dateCollected) {
        this.setDateCollected(dateCollected);
        return this;
    }

    public void setDateCollected(LocalDate dateCollected) {
        this.dateCollected = dateCollected;
    }

    public LocalDate getDateRegistered() {
        return this.dateRegistered;
    }

    public SamplePatient dateRegistered(LocalDate dateRegistered) {
        this.setDateRegistered(dateRegistered);
        return this;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public LocalDate getDateTested() {
        return this.dateTested;
    }

    public SamplePatient dateTested(LocalDate dateTested) {
        this.setDateTested(dateTested);
        return this;
    }

    public void setDateTested(LocalDate dateTested) {
        this.dateTested = dateTested;
    }

    public String getResult() {
        return this.result;
    }

    public SamplePatient result(String result) {
        this.setResult(result);
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUnit() {
        return this.unit;
    }

    public SamplePatient unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDate getDatePublished() {
        return this.datePublished;
    }

    public SamplePatient datePublished(LocalDate datePublished) {
        this.setDatePublished(datePublished);
        return this;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public String getState() {
        return this.state;
    }

    public SamplePatient state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SamplePatient)) {
            return false;
        }
        return id != null && id.equals(((SamplePatient) o).id);
    }

    @Override
    public int hashCode() {
        // see
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
	@Override
	public String toString() {
		return "SamplePatient{" + "id=" + getId() + ", firstName='" + getFirstName() + "'" + ", lastName='"
				+ getLastName() + "'" + ", dob='" + getDob() + "'" + ", age='" + getAge() + "'" + ", gender='"
				+ getGender() + "'" + ", primaryReferrer='" + getPrimaryReferrer() + "'" + ", clientPatientId='"
				+ getClientPatientId() + "'" + ", clientSampleId='" + getClientSampleId() + "'" + ", clientContact='"
				+ getClientContact() + "'" + ", sampleTypeId='" + getSampleTypeId() + "'" + ", sampleTypeName='"
				+ getSampleTypeName() + "'" + ", analysisServiceId='" + getAnalysisServiceId() + "'"
				+ ", analysisServiceName='" + getAnalysisServiceName() + "'" + ", dateCollected='" + getDateCollected()
				+ "'" + ", dateRegistered='" + getDateRegistered() + "'" + ", dateTested='" + getDateTested() + "'"
				+ ", result='" + getResult() + "'" + ", unit='" + getUnit() + "'" + ", datePublished='"
				+ getDatePublished() + "'" + ", state='" + getState() + "'" + "}";
	}
}
