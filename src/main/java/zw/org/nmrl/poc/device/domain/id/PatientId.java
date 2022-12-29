package zw.org.nmrl.poc.device.domain.id;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class PatientId implements Serializable {
    private String patientId;

    private String patientUid;

    public PatientId() {}

    public PatientId(String patientId, String patientUid) {
        this.patientId = patientId;
        this.patientUid = patientUid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientId patient = (PatientId) o;
        return patientId.equals(patient.patientId) && patientUid.equals(patient.patientUid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, patientUid);
    }
}
