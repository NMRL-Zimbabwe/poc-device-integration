package zw.org.nmrl.poc.device.domain.id;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class AnalysisId implements Serializable {
    private String analysisId;

    private String analysisUid;

    public AnalysisId() {}

    public AnalysisId(String analysisId, String analysisUid) {
        this.analysisId = analysisId;
        this.analysisUid = analysisUid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisId patient = (AnalysisId) o;
        return analysisId.equals(patient.analysisId) && analysisUid.equals(patient.analysisUid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(analysisId, analysisUid);
    }

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getAnalysisUid() {
        return analysisUid;
    }

    public void setAnalysisUid(String analysisUid) {
        this.analysisUid = analysisUid;
    }
}
