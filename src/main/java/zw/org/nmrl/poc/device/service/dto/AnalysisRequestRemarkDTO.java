package zw.org.nmrl.poc.device.service.dto;

import java.io.Serializable;
import javax.persistence.Column;

/**
 * A DTO for the {@link zw.org.nmrl.domain.AnalysisRequestRemark} entity.
 */
public class AnalysisRequestRemarkDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String analysisRequestRemarkId;

    private String analysisRequestId;

    private String analysisRequestUid;

    private String userId;

    private String username;

    private String labId;

    private String content;

    private String dateCreated;

    public String getAnalysisRequestRemarkId() {
        return analysisRequestRemarkId;
    }

    public void setAnalysisRequestRemarkId(String analysisRequestRemarkId) {
        this.analysisRequestRemarkId = analysisRequestRemarkId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnalysisRequestRemarkDTO)) {
            return false;
        }
        return analysisRequestRemarkId != null && analysisRequestRemarkId.equals(((AnalysisRequestRemarkDTO) o).analysisRequestRemarkId);
    }

    @Override
    public String toString() {
        return (
            "AnalysisRequestRemarkDTO [analysisRequestRemarkId=" +
            analysisRequestRemarkId +
            ", analysisRequestId=" +
            analysisRequestId +
            ", analysisRequestUid=" +
            analysisRequestUid +
            ", userId=" +
            userId +
            ", username=" +
            username +
            ", labId=" +
            labId +
            ", content=" +
            content +
            ", dateCreated=" +
            dateCreated +
            "]"
        );
    }
}
