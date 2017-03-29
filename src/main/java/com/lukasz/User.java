package com.lukasz;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Created by LKubinski on 22/03/2017.
 */

@Embeddable
public class User {

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

//    @Column(name = "LOGIN_NAME")
    private String loginName;

//    @Column(name = "AGE")
    private int age;

    @Embedded
    private Address address;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLoginName() {
        return loginName;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    private User(final Builder builder){
        this.name = builder.name;
        this.surname = builder.surname;
        this.loginName = builder.loginName;
        this.age = builder.age;
        this.address = builder.address;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String surname;
        private String loginName;
        private int age;
        private Address address;

        private Builder(){
            address = new Address();
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }

        public Builder loginName(String loginName){
            this.loginName = loginName;
            return this;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public Builder addressLine1(String addressLine1){
            this.address.setAddressLine1(addressLine1);
            return this;
        }

        public Builder addressLine2(String addressLine2){
            this.address.setAddressLine2(addressLine2);
            return this;
        }

        public Builder postCode(String postCode){
            this.address.setZipCode(postCode);
            return this;
        }

        public Builder city(String city){
            this.address.setCity(city);
            return this;
        }

        public Builder country(String country){
            this.address.setCountry(country);
            return this;
        }

        private void validate(final Builder builder) {
            if (name == null){
                throw new  IllegalArgumentException("Name field must be specified!");
            }
            if (surname == null){
                throw new  IllegalArgumentException("Surname field must be specified!");
            }
        }

        public User build(){
            validate(this);
            return new User(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        if (!surname.equals(user.surname)) return false;
        return loginName.equals(user.loginName);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + loginName.hashCode();
        return result;
    }
}
