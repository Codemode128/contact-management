package com.shivam.cms.dto;

import jakarta.validation.constraints.*;

public class ContactDTO {

    private int id;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]+",message = "First name should contain only letters")
    private String firstName;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]+",message = "Last name should contain only letters")
    private String lastName;

    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Phone number should be minimum 10 digits")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
