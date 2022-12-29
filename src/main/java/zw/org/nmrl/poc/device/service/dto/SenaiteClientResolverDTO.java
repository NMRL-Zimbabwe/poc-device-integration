package zw.org.nmrl.poc.device.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = SenaiteClientResolverDTO.class)
public class SenaiteClientResolverDTO {
    private String country;

    private String clientId;

    private String province;

    private String clientName;

    private String district;

    public SenaiteClientResolverDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SenaiteClientResolverDTO(String country, String clientId, String province, String clientName, String district) {
        super();
        this.country = country;
        this.clientId = clientId;
        this.province = province;
        this.clientName = clientName;
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return (
            "SenaiteClientResolverDTO [country = " +
            country +
            ", clientId = " +
            clientId +
            ", province = " +
            province +
            ", clientName = " +
            clientName +
            ", district = " +
            district +
            "]"
        );
    }
}
