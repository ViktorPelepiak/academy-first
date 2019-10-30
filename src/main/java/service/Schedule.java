package service;

import enums.WeekParity;
import exception.AddLessonException;
import io.JSONConverter;
import model.*;

import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Schedule {
    @NotNull(message = "Schedule must be not null")
    private List<Lesson> schedule;

    public Schedule() {
        schedule = new LinkedList<>();
    }

    public Schedule(List<Lesson> schedule) {
        this.schedule = schedule;
    }

    public List<Lesson> getSchedule() {
        return schedule;
    }

    public Schedule setSchedule(List<Lesson> schedule) {
        this.schedule = schedule;
        return this;
    }

    public List<Lesson> todayGroupSchedule(Group group) {
        return schedule.stream()
                .filter(lesson -> LocalDate.now().getDayOfWeek().equals(lesson.getDayOfWeek()))
                .filter(lesson -> lesson.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    public List<Lesson> weekDayGroupSchedule(Group group, DayOfWeek dayOfWeek) {
        return schedule.stream()
                .filter(lesson -> lesson.getDayOfWeek().equals(dayOfWeek))
                .filter(lesson -> lesson.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    public List<Lesson> groupSchedule(Group group) {
        return schedule.stream()
                .filter(lesson -> lesson.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    public List<Lesson> teacherSchedule(Teacher teacher) {
        return schedule.stream()
                .filter(lesson -> lesson.getTeacher().equals(teacher))
                .collect(Collectors.toList());
    }

    public List<Lesson> auditorySchedule(Auditory auditory) {
        return schedule.stream()
                .filter(lesson -> lesson.getAuditory().equals(auditory))
                .collect(Collectors.toList());
    }

    public List<Lesson> subjectSchedule(Subject subject) {
        return schedule.stream()
                .filter(lesson -> lesson.getSubject().equals(subject))
                .collect(Collectors.toList());
    }

    public Schedule addLesson(Lesson lesson) throws AddLessonException {
        List<Lesson> selectByDayAndLessonTime = schedule.stream()
                .filter(lesson1 -> lesson1.getDayOfWeek().equals(lesson.getDayOfWeek()))
                .filter(lesson1 -> lesson1.getLessonTime().equals(lesson.getLessonTime()))
                .collect(Collectors.toList());

        if (selectByDayAndLessonTime.stream()
                .filter(lesson1 -> lesson1.getGroup().equals(lesson.getGroup()))
                .anyMatch(lesson1 -> lesson1.getWeekParity().equals(lesson.getWeekParity()) || lesson1.getWeekParity().equals(WeekParity.ALL_WEEKS)))
            throw new AddLessonException("Can`t set current lesson, cause group already have lesson at this time");
        if (selectByDayAndLessonTime.stream()
                .filter(lesson1 -> lesson1.getTeacher().equals(lesson.getTeacher()))
                .anyMatch(lesson1 -> lesson1.getWeekParity().equals(lesson.getWeekParity()) || lesson1.getWeekParity().equals(WeekParity.ALL_WEEKS)))
            throw new AddLessonException("Can`t set current lesson, cause teacher already have lesson at this time");
        if (selectByDayAndLessonTime.stream()
                .filter(lesson1 -> lesson1.getAuditory().equals(lesson.getAuditory()))
                .anyMatch(lesson1 -> lesson1.getWeekParity().equals(lesson.getWeekParity()) || lesson1.getWeekParity().equals(WeekParity.ALL_WEEKS)))
            throw new AddLessonException("Can`t set current lesson, cause auditory already busy at this time");
        schedule.add(lesson);
        return this;
    }

    public void save(String path) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            JSONConverter.serialise(path, this);
            return "File saved successful!;";
        });
        executorService.shutdown();
    }

    public void load(String path) throws IOException, JAXBException {
        this.schedule = JSONConverter.deserialize(path).schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule1 = (Schedule) o;
        return schedule.equals(schedule1.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule);
    }
}
