package model;

import validation.ValidAge;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class Teacher {
    private Long id;
    @NotNull(message = "First name must be not null")
    private String firstName;
    @NotNull(message = "Last name must be not null")
    private String lastName;
    @NotNull(message = "Father name must be not null")
    private String fatherName;
    @NotNull(message = "Date of birth must be not null")
    @ValidAge(min = 21)
    private LocalDate dateOfBirth;
    private String info;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getInfo() {
        return info;
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }

    public Teacher setId(Long id) {
        this.id = id;
        return this;
    }

    public Teacher setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Teacher setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Teacher setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public Teacher setInfo(String info) {
        this.info = info;
        return this;
    }

    public Teacher setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return firstName.equals(teacher.firstName) &&
                lastName.equals(teacher.lastName) &&
                fatherName.equals(teacher.fatherName) &&
                dateOfBirth.equals(teacher.dateOfBirth) &&
                info.equals(teacher.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, fatherName);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", info='" + info + '\'' +
                '}';
    }
}
