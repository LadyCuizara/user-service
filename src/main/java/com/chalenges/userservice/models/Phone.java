package com.chalenges.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author lady Cuizara
 */
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "cityCode")
    private Integer cityCode;

    @Column(name = "countryCode")
    private Integer countryCode;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Phone() {
    }

    public Phone(PhoneBuilder builder) {
        this.number = builder.number;
        this.cityCode = builder.cityCode;
        this.countryCode = builder.countryCode;
        this.user = builder.user;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class PhoneBuilder {

        private Integer number;

        private Integer cityCode;

        private Integer countryCode;

        private User user;

        public PhoneBuilder setNumber(Integer number) {
            this.number = number;
            return this;
        }

        public PhoneBuilder setCityCode(Integer cityCode) {
            this.cityCode = cityCode;
            return this;
        }

        public PhoneBuilder setCountryCode(Integer countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public PhoneBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }
    }
}
