package zw.org.nmrl.poc.device.domain.id;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class AnalysisRequestId implements Serializable {
    private String analysisRequestId;

    private String analysisRequestUid;

    public AnalysisRequestId() {}

    public AnalysisRequestId(String analysisRequestId, String analysisRequestUid) {
        this.analysisRequestId = analysisRequestId;
        this.analysisRequestUid = analysisRequestUid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisRequestId patient = (AnalysisRequestId) o;
        return analysisRequestId.equals(patient.analysisRequestId) && analysisRequestUid.equals(patient.analysisRequestUid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(analysisRequestId, analysisRequestUid);
    }

    public String getAnalysisRequestId() {
        return analysisRequestId;
    }

    public void setAnalysisRequestId(String analysisRequestId) {
        this.analysisRequestId = analysisRequestId;
    }

    public String getAnalysisRequestUid() {
        return analysisRequestUid;
    }

    public void setAnalysisRequestUid(String analysisRequestUid) {
        this.analysisRequestUid = analysisRequestUid;
    }
}
