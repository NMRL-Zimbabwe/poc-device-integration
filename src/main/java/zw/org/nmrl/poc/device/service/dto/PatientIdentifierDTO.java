package zw.org.nmrl.poc.device.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = PatientIdentifierDTO.class)
public class PatientIdentifierDTO {
    private String patientIdentifierId;

    private String patientId;

    private String patientUid;

    private String identifier; // number e.g 2016

    private String type; // type eg cohort

    private String typeId;

    private String value;

    private String labId;

    public PatientIdentifierDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PatientIdentifierDTO(
        String patientIdentifierId,
        String patientId,
        String patientUid,
        String identifier,
        String type,
        String typeId,
        String value,
        String labId
    ) {
        super();
        this.patientIdentifierId = patientIdentifierId;
        this.patientId = patientId;
        this.patientUid = patientUid;
        this.identifier = identifier;
        this.type = type;
        this.typeId = typeId;
        this.value = value;
        this.labId = labId;
    }

    public String getPatientIdentifierId() {
        return patientIdentifierId;
    }

    public void setPatientIdentifierId(String patientIdentifierId) {
        this.patientIdentifierId = patientIdentifierId;
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    @Override
    public String toString() {
        return (
            "PatientIdentifierDTO [patientIdentifierId=" +
            patientIdentifierId +
            ", patientId=" +
            patientId +
            ", patientUid=" +
            patientUid +
            ", identifier=" +
            identifier +
            ", type=" +
            type +
            ", typeId=" +
            typeId +
            ", value=" +
            value +
            ", labId=" +
            labId +
            "]"
        );
    }
}
