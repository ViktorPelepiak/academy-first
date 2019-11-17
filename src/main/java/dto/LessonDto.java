package dto;

public class LessonDto {
    private int day;
    private int lessonTime;
    private String groupNumber;
    private int lessonType;
    private String teacher;
    private String auditory;
    private String subject;

    public LessonDto(){}

    public int getDay() {
        return day;
    }

    public int getLessonTime() {
        return lessonTime;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public int getLessonType() {
        return lessonType;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getAuditory() {
        return auditory;
    }

    public String getSubject() {
        return subject;
    }

    public LessonDto setDay(int day) {
        this.day = day;
        return this;
    }

    public LessonDto setLessonTime(int lessonTime) {
        this.lessonTime = lessonTime;
        return this;
    }

    public LessonDto setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }

    public LessonDto setLessonType(int lessonType) {
        this.lessonType = lessonType;
        return this;
    }

    public LessonDto setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public LessonDto setAuditory(String auditory) {
        this.auditory = auditory;
        return this;
    }

    public LessonDto setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public String toString() {
        return "LessonDto{" +
                "day=" + day +
                ", lessonTime=" + lessonTime +
                ", groupNumber='" + groupNumber + '\'' +
                ", lessonType=" + lessonType +
                ", teacher='" + teacher + '\'' +
                ", auditory='" + auditory + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
