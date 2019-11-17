package model;

import enums.LessonType;
import enums.WeekParity;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.Objects;

public class Lesson {
    private Long id;
    @NotNull(message = "Lesson time must be not null")
    private LessonTime lessonTime;
    @NotNull(message = "Group must be not null")
    private Group group;
    @NotNull(message = "Lesson type must be not null")
    private LessonType type;
    @NotNull(message = "Teacher must be not null")
    private Teacher teacher;
    @NotNull(message = "Auditory must be not null")
    private Auditory auditory;
    @NotNull(message = "Week parity must be not null")
    private WeekParity weekParity;
    @NotNull(message = "Day of week must be not null")
    private DayOfWeek dayOfWeek;
    @NotNull(message = "Subject must be not null")
    private Subject subject;

    public Long getId() {
        return id;
    }

    public LessonTime getLessonTime() {
        return lessonTime;
    }

    public Group getGroup() {
        return group;
    }

    public LessonType getType() {
        return type;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Auditory getAuditory() {
        return auditory;
    }

    public WeekParity getWeekParity() {
        return weekParity;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Subject getSubject() {
        return subject;
    }

    public Lesson setId(Long id) {
        this.id = id;
        return this;
    }

    public Lesson setLessonTime(LessonTime lessonTime) {
        this.lessonTime = lessonTime;
        return this;
    }

    public Lesson setGroup(Group group) {
        this.group = group;
        return this;
    }

    public Lesson setType(LessonType type) {
        this.type = type;
        return this;
    }

    public Lesson setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public Lesson setAuditory(Auditory auditory) {
        this.auditory = auditory;
        return this;
    }

    public Lesson setWeekParity(WeekParity weekParity) {
        this.weekParity = weekParity;
        return this;
    }

    public Lesson setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public Lesson setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonTime=" + lessonTime.getLessonNumber() +
                ", group=" + group.getGroupNumber() +
                ", type=" + type +
                ", teacher=" + teacher +
                ", auditory=" + auditory.getAuditoryNumber() +
                ", weekParity=" + weekParity +
                ", dayOfWeek=" + dayOfWeek +
                ", subject=" + subject.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(lessonTime, lesson.lessonTime) &&
                Objects.equals(group, lesson.group) &&
                type == lesson.type &&
                Objects.equals(teacher, lesson.teacher) &&
                Objects.equals(auditory, lesson.auditory) &&
                weekParity == lesson.weekParity &&
                dayOfWeek == lesson.dayOfWeek &&
                Objects.equals(subject, lesson.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonTime, group, type, teacher, auditory, weekParity, dayOfWeek, subject);
    }
}
