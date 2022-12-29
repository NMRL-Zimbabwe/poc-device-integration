package zw.org.nmrl.poc.device.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Patient.
 */
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "id")
	private Long id;

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

	@Column(name = "source")
	private String source;

	// jhipster-needle-entity-add-field - JHipster will add fields here

	public Long getId() {
		return this.id;
	}

	public Patient id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Patient firstName(String firstName) {
		this.setFirstName(firstName);
		return this;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Patient lastName(String lastName) {
		this.setLastName(lastName);
		return this;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return this.dob;
	}

	public Patient dob(LocalDate dob) {
		this.setDob(dob);
		return this;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAge() {
		return this.age;
	}

	public Patient age(String age) {
		this.setAge(age);
		return this;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return this.gender;
	}

	public Patient gender(String gender) {
		this.setGender(gender);
		return this;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPrimaryReferrer() {
		return this.primaryReferrer;
	}

	public Patient primaryReferrer(String primaryReferrer) {
		this.setPrimaryReferrer(primaryReferrer);
		return this;
	}

	public void setPrimaryReferrer(String primaryReferrer) {
		this.primaryReferrer = primaryReferrer;
	}

	public String getClientPatientId() {
		return this.clientPatientId;
	}

	public Patient clientPatientId(String clientPatientId) {
		this.setClientPatientId(clientPatientId);
		return this;
	}

	public void setClientPatientId(String clientPatientId) {
		this.clientPatientId = clientPatientId;
	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here

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
		if (!(o instanceof Patient)) {
			return false;
		}
		return id != null && id.equals(((Patient) o).id);
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
		return "Patient{" + "id=" + getId() + ", firstName='" + getFirstName() + "'" + ", lastName='" + getLastName()
				+ "'" + ", dob='" + getDob() + "'" + ", age='" + getAge() + "'" + ", gender='" + getGender() + "'"
				+ ", primaryReferrer='" + getPrimaryReferrer() + "'" + ", clientPatientId='" + getClientPatientId()
				+ "'" + "}";
	}
}
