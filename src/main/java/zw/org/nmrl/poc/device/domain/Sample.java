package zw.org.nmrl.poc.device.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Sample.
 */
@Entity
@Table(name = "sample")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Sample implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "patient_id")
    private String patientId;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Sample id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientId() {
        return this.patientId;
    }

    public Sample patientId(String patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getClientSampleId() {
        return this.clientSampleId;
    }

    public Sample clientSampleId(String clientSampleId) {
        this.setClientSampleId(clientSampleId);
        return this;
    }

    public void setClientSampleId(String clientSampleId) {
        this.clientSampleId = clientSampleId;
    }

    public String getClientContact() {
        return this.clientContact;
    }

    public Sample clientContact(String clientContact) {
        this.setClientContact(clientContact);
        return this;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public String getSampleTypeId() {
        return this.sampleTypeId;
    }

    public Sample sampleTypeId(String sampleTypeId) {
        this.setSampleTypeId(sampleTypeId);
        return this;
    }

    public void setSampleTypeId(String sampleTypeId) {
        this.sampleTypeId = sampleTypeId;
    }

    public String getSampleTypeName() {
        return this.sampleTypeName;
    }

    public Sample sampleTypeName(String sampleTypeName) {
        this.setSampleTypeName(sampleTypeName);
        return this;
    }

    public void setSampleTypeName(String sampleTypeName) {
        this.sampleTypeName = sampleTypeName;
    }

    public String getAnalysisServiceId() {
        return this.analysisServiceId;
    }

    public Sample analysisServiceId(String analysisServiceId) {
        this.setAnalysisServiceId(analysisServiceId);
        return this;
    }

    public void setAnalysisServiceId(String analysisServiceId) {
        this.analysisServiceId = analysisServiceId;
    }

    public String getAnalysisServiceName() {
        return this.analysisServiceName;
    }

    public Sample analysisServiceName(String analysisServiceName) {
        this.setAnalysisServiceName(analysisServiceName);
        return this;
    }

    public void setAnalysisServiceName(String analysisServiceName) {
        this.analysisServiceName = analysisServiceName;
    }

    public LocalDate getDateCollected() {
        return this.dateCollected;
    }

    public Sample dateCollected(LocalDate dateCollected) {
        this.setDateCollected(dateCollected);
        return this;
    }

    public void setDateCollected(LocalDate dateCollected) {
        this.dateCollected = dateCollected;
    }

    public LocalDate getDateRegistered() {
        return this.dateRegistered;
    }

    public Sample dateRegistered(LocalDate dateRegistered) {
        this.setDateRegistered(dateRegistered);
        return this;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public LocalDate getDateTested() {
        return this.dateTested;
    }

    public Sample dateTested(LocalDate dateTested) {
        this.setDateTested(dateTested);
        return this;
    }

    public void setDateTested(LocalDate dateTested) {
        this.dateTested = dateTested;
    }

    public String getResult() {
        return this.result;
    }

    public Sample result(String result) {
        this.setResult(result);
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUnit() {
        return this.unit;
    }

    public Sample unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDate getDatePublished() {
        return this.datePublished;
    }

    public Sample datePublished(LocalDate datePublished) {
        this.setDatePublished(datePublished);
        return this;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public String getState() {
        return this.state;
    }

    public Sample state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sample)) {
            return false;
        }
        return id != null && id.equals(((Sample) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sample{" +
            "id=" + getId() +
            ", patientId='" + getPatientId() + "'" +
            ", clientSampleId='" + getClientSampleId() + "'" +
            ", clientContact='" + getClientContact() + "'" +
            ", sampleTypeId='" + getSampleTypeId() + "'" +
            ", sampleTypeName='" + getSampleTypeName() + "'" +
            ", analysisServiceId='" + getAnalysisServiceId() + "'" +
            ", analysisServiceName='" + getAnalysisServiceName() + "'" +
            ", dateCollected='" + getDateCollected() + "'" +
            ", dateRegistered='" + getDateRegistered() + "'" +
            ", dateTested='" + getDateTested() + "'" +
            ", result='" + getResult() + "'" +
            ", unit='" + getUnit() + "'" +
            ", datePublished='" + getDatePublished() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
