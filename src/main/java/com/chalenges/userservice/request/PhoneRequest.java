package com.chalenges.userservice.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

/**
 * @author lady Cuizara
 */
public class PhoneRequest {
    @NotNull(message = "La numero no debe ser nulo")
    private Integer number;

    @JsonProperty("citycode")
    private Integer cityCode;

    @JsonProperty("countrycode")
    private Integer countryCode;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
