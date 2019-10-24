package model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Objects;

public class LessonTime {
    @Min(value = 1, message = "Lesson number must be for 1 to 6")
    @Max(value = 8, message = "Lesson number must be for 1 to 6")
    private int lessonNumber;
    @NotNull(message = "Time of lesson begin must be not null")
    private LocalTime beginTime;
    @NotNull(message = "Time of lesson begin must be not null")
    private LocalTime endTime;

    public int getLessonNumber() {
        return lessonNumber;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LessonTime setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
        return this;
    }

    public LessonTime setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public LessonTime setEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonTime that = (LessonTime) o;
        return lessonNumber == that.lessonNumber &&
                beginTime.equals(that.beginTime) &&
                endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonNumber, beginTime, endTime);
    }
}
