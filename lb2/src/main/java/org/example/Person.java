package org.example;

import org.apache.commons.codec.digest.DigestUtils;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

abstract public class Person{
    protected int id = -1;
    protected String name;
    protected String surname;
    protected LocalDate birthDate;
    protected String hashedPassword;
    protected LocalDateTime registrationDateTime;
    protected String phoneNumber;
    protected Gender gender;
    protected String email;

    public Person() {}

    public Person(String name, String surname, String phoneNumber, Gender gender, LocalDate birthdayDate,
                  String email, String password) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthdayDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
        this.hashedPassword = DigestUtils.sha256Hex(password);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public Gender getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return  Objects.equals(name, person.name) && Objects.equals(surname, person.surname) &&
                Objects.equals(birthDate, person.birthDate) && Objects.equals(hashedPassword, person.hashedPassword) &&
                Objects.equals(phoneNumber, person.phoneNumber) && gender == person.gender && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, hashedPassword, phoneNumber, gender, email);
    }
}
