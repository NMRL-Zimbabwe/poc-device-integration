package zw.org.nmrl.poc.device.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import zw.org.nmrl.poc.device.domain.id.PatientId;
import zw.org.nmrl.poc.device.management.enumeration.Gender;

/**
 * The Patient entity.
 */
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Patient extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "patient_id", unique = true, nullable = false)
	private String patientId;

	@Column(name = "patient_uid")
	private String patientUid;

	private Boolean anonymous;

	private String source;

	@Column(name = "lab_id")
	private String labId; // identification

	@NotNull
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotNull
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "phone_number")
	private String phoneNumber;

	@NotNull
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	@Column(name = "consent_to_sms")
	private Boolean consentToSMS;

	@Column(name = "birth_date_estimated")
	private Boolean birthDateEstimated;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	@NotNull
	@Column(name = "client_patient_id", nullable = false)
	private String clientPatientId;

	@Column(name = "primary_referrer_id")
	private String primaryReferrerId; // client

	@Column(name = "primary_referrer")
	private String primaryReferrer; // client

	private int retry;

	// jhipster-needle-entity-add-field - JHipster will add fields here

	public String getFirstName() {
		return firstName;
	}

	public Boolean getConsentToSMS() {
		return consentToSMS;
	}

	public Patient firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Patient lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Patient phoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Patient birthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
		return this;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean isConsentToSMS() {
		return consentToSMS;
	}

	public Patient consentToSMS(Boolean consentToSMS) {
		this.consentToSMS = consentToSMS;
		return this;
	}

	public void setConsentToSMS(Boolean consentToSMS) {
		this.consentToSMS = consentToSMS;
	}

	public Gender getGender() {
		return gender;
	}

	public Patient gender(Gender gender) {
		this.gender = gender;
		return this;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getClientPatientId() {
		return clientPatientId;
	}

	public Patient clientPatientId(String clientPatientId) {
		this.clientPatientId = clientPatientId;
		return this;
	}

	public void setClientPatientId(String clientPatientId) {
		this.clientPatientId = clientPatientId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	public Boolean getBirthDateEstimated() {
		return birthDateEstimated;
	}

	public void setBirthDateEstimated(Boolean birthDateEstimated) {
		this.birthDateEstimated = birthDateEstimated;
	}

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Patient)) {
			return false;
		}
		return patientId != null && patientId.equals(((Patient) o).patientId);
	}

	public String getPrimaryReferrer() {
		return primaryReferrer;
	}

	public void setPrimaryReferrer(String primaryReferrer) {
		this.primaryReferrer = primaryReferrer;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	public String getPrimaryReferrerId() {
		return primaryReferrerId;
	}

	public void setPrimaryReferrerId(String primaryReferrerId) {
		this.primaryReferrerId = primaryReferrerId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientUid() {
		return patientUid;
	}

	public void setPatientUid(String patientUid) {
		this.patientUid = patientUid;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientUid=" + patientUid + ", anonymous=" + anonymous
				+ ", source=" + source + ", labId=" + labId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate + ", consentToSMS=" + consentToSMS
				+ ", birthDateEstimated=" + birthDateEstimated + ", gender=" + gender + ", clientPatientId="
				+ clientPatientId + ", primaryReferrerId=" + primaryReferrerId + ", primaryReferrer=" + primaryReferrer
				+ "]";
	}

}
