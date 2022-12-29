package zw.org.nmrl.poc.device.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link zw.org.nmrl.domain.AnalysisProfile} entity.
 */
public class AnalysisProfileDTO implements Serializable {
    private String analysisProfileId;

    private String name;

    private String description;

    private String api_url;

    private String uid;

    private String path;

    private String effective;

    private String parent_uid;

    public String getAnalysisProfileId() {
        return analysisProfileId;
    }

    public void setAnalysisProfileId(String analysisProfileId) {
        this.analysisProfileId = analysisProfileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    public String getParent_uid() {
        return parent_uid;
    }

    public void setParent_uid(String parent_uid) {
        this.parent_uid = parent_uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnalysisProfileDTO)) {
            return false;
        }

        return analysisProfileId != null && analysisProfileId.equals(((AnalysisProfileDTO) o).analysisProfileId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
	@Override
	public String toString() {
		return "AnalysisProfileDTO [analysisProfileId=" + analysisProfileId + ", name=" + name + ", description="
				+ description + ", api_url=" + api_url + ", uid=" + uid + ", path=" + path + ", effective=" + effective
				+ ", parent_uid=" + parent_uid + "]";
	}
}
