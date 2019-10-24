package model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Group {
    @NotNull(message = "Faculty must be not null")
    private String faculty;
    @NotNull(message = "Specialisation must be not null")
    private String specialisation;
    @NotNull(message = "Group number must be not null")
    private String groupNumber;
    @Min(value = 1, message = "Course number must be for 1 to 6")
    @Max(value = 6, message = "Course number must be for 1 to 6")
    private short course;

    public String getFaculty() {
        return faculty;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public short getCourse() {
        return course;
    }

    public Group setFaculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    public Group setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
        return this;
    }

    public Group setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }

    public Group setCourse(short course) {
        this.course = course;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return course == group.course &&
                faculty.equals(group.faculty) &&
                specialisation.equals(group.specialisation) &&
                groupNumber.equals(group.groupNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faculty, specialisation, groupNumber, course);
    }
}
