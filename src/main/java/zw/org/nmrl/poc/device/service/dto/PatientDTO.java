package zw.org.nmrl.poc.device.service.dto;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

import zw.org.nmrl.poc.device.domain.Client;
import zw.org.nmrl.poc.device.domain.id.PatientId;
import zw.org.nmrl.poc.device.management.enumeration.Gender;

/**
 * A DTO representing a patient, with his authorities.
 */
public class PatientDTO {
    private String patientId;

    private String patientUid;

    private Boolean anonymous;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate birthDate;

    private Boolean consentToSMS;

    private Boolean birthDateEstimated;

    private String source;

    private String labId;

    private Gender gender;

    private String clientPatientId;

    private String primaryReferrerId;

    private String primaryReferrer;

    @JsonProperty("patientIdentifiers")
    private PatientIdentifierDTO[] patientIdentifiers;

    private SenaiteClientResolverDTO senaiteClient;

    public PatientDTO(
        String patientId,
        String patientUid,
        Boolean anonymous,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthDate,
        Boolean consentToSMS,
        Boolean birthDateEstimated,
        String source,
        String labId,
        Gender gender,
        String clientPatientId,
        String primaryReferrerId,
        String primaryReferrer,
        PatientIdentifierDTO[] patientIdentifiers,
        SenaiteClientResolverDTO senaiteClient
    ) {
        super();
        this.patientId = patientId;
        this.patientUid = patientUid;
        this.anonymous = anonymous;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.consentToSMS = consentToSMS;
        this.birthDateEstimated = birthDateEstimated;
        this.source = source;
        this.labId = labId;
        this.gender = gender;
        this.clientPatientId = clientPatientId;
        this.primaryReferrerId = primaryReferrerId;
        this.primaryReferrer = primaryReferrer;
        this.patientIdentifiers = patientIdentifiers;
        this.senaiteClient = senaiteClient;
    }

    public PatientDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PatientDTO(
        PatientId patientId,
        String firstName,
        String lastName,
        LocalDate birthDate,
        Gender gender,
        String primaryReferrerId
    ) {
        this.patientId = patientId.getPatientId();
        this.patientUid = patientId.getPatientUid();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.primaryReferrerId = primaryReferrerId;
    }

    public Boolean getConsentToSMS() {
        return consentToSMS;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean isConsentToSMS() {
        return consentToSMS;
    }

    public void setConsentToSMS(Boolean consentToSMS) {
        this.consentToSMS = consentToSMS;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getClientPatientId() {
        return clientPatientId;
    }

    public void setClientPatientId(String clientPatientId) {
        this.clientPatientId = clientPatientId;
    }

    public String getPrimaryReferrerId() {
        return primaryReferrerId;
    }

    public void setPrimaryReferrerId(String primaryReferrerId) {
        this.primaryReferrerId = primaryReferrerId;
    }

    public String getPrimaryReferrer() {
        return primaryReferrer;
    }

    public void setPrimaryReferrer(String primaryReferrer) {
        this.primaryReferrer = primaryReferrer;
    }

    public Boolean getBirthDateEstimated() {
        return birthDateEstimated;
    }

    public void setBirthDateEstimated(Boolean birthDateEstimated) {
        this.birthDateEstimated = birthDateEstimated;
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

    public PatientIdentifierDTO[] getPatientIdentifiers() {
        return patientIdentifiers;
    }

    public void setPatientIdentifiers(PatientIdentifierDTO[] patientIdentifiers) {
        this.patientIdentifiers = patientIdentifiers;
    }

    public SenaiteClientResolverDTO getSenaiteClient() {
        return senaiteClient;
    }

    public void setSenaiteClient(SenaiteClientResolverDTO senaiteClient) {
        this.senaiteClient = senaiteClient;
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
        if (!(o instanceof PatientDTO)) {
            return false;
        }

        return patientId != null && patientId.equals(((PatientDTO) o).patientId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return (
            "PatientDTO [patientId=" +
            patientId +
            ", patientUid=" +
            patientUid +
            ", anonymous=" +
            anonymous +
            ", firstName=" +
            firstName +
            ", lastName=" +
            lastName +
            ", email=" +
            email +
            ", phoneNumber=" +
            phoneNumber +
            ", birthDate=" +
            birthDate +
            ", consentToSMS=" +
            consentToSMS +
            ", birthDateEstimated=" +
            birthDateEstimated +
            ", source=" +
            source +
            ", labId=" +
            labId +
            ", gender=" +
            gender +
            ", clientPatientId=" +
            clientPatientId +
            ", primaryReferrerId=" +
            primaryReferrerId +
            ", primaryReferrer=" +
            primaryReferrer +
            ", patientIdentifiers=" +
            Arrays.toString(patientIdentifiers) +
            ", senaiteClient=" +
            senaiteClient +
            "]"
        );
    }
}
