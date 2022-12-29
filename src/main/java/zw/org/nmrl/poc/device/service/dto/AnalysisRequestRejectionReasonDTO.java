package zw.org.nmrl.poc.device.service.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * A DTO for the {@link zw.org.nmrl.domain.AnalysisRequestRejectionReason}
 * entity.
 */
public class AnalysisRequestRejectionReasonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String analysisRequestRejectedReasonId;

    private List<String> reasons;

    private String other;

    public List<String> getReasons() {
        return reasons;
    }

    private String labId;

    private String analysisRequestId;

    private String analysisRequestUid;

    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getAnalysisRequestRejectedReasonId() {
        return analysisRequestRejectedReasonId;
    }

    public void setAnalysisRequestRejectedReasonId(String analysisRequestRejectedReasonId) {
        this.analysisRequestRejectedReasonId = analysisRequestRejectedReasonId;
    }

    @Override
    public String toString() {
        return (
            "AnalysisRequestRejectionReasonDTO [analysisRequestRejectedReasonId=" +
            analysisRequestRejectedReasonId +
            ", reasons=" +
            reasons +
            ", other=" +
            other +
            ", labId=" +
            labId +
            ", analysisRequestId=" +
            analysisRequestId +
            ", analysisRequestUid=" +
            analysisRequestUid +
            "]"
        );
    }
}
