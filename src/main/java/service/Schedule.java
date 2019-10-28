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

    public Schedule(){
        schedule = new LinkedList<>();
    }

    public Schedule(List<Lesson> schedule){
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

    public List<Lesson> weekDayGroupSchedule(Group group, DayOfWeek dayOfWeek){
        return schedule.stream()
                .filter(lesson -> lesson.getDayOfWeek().equals(dayOfWeek))
                .filter(lesson -> lesson.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    public List<Lesson> groupSchedule(Group group){
        return schedule.stream()
                .filter(lesson -> lesson.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    public List<Lesson> teacherSchedule(Teacher teacher){
        return schedule.stream()
                .filter(lesson -> lesson.getTeacher().equals(teacher))
                .collect(Collectors.toList());
    }

    public List<Lesson> auditorySchedule(Auditory auditory){
        return schedule.stream()
                .filter(lesson -> lesson.getAuditory().equals(auditory))
                .collect(Collectors.toList());
    }

    public List<Lesson> subjectSchedule(Subject subject){
        return schedule.stream()
                .filter(lesson -> lesson.getSubject().equals(subject))
                .collect(Collectors.toList());
    }

    public Schedule addLesson(Lesson lesson) throws AddLessonException {
        if (schedule.stream()
                .filter(coup -> coup.getDayOfWeek().equals(lesson.getDayOfWeek()))
                .filter(coup -> coup.getLessonTime().equals(lesson.getLessonTime()))
                .filter(coup -> coup.getGroup().equals(lesson.getGroup()))
                .anyMatch(coup -> coup.getWeekParity().equals(lesson.getWeekParity()) || coup.getWeekParity().equals(WeekParity.ALL_WEEKS)))
            throw new AddLessonException("Can`t set current lesson, cause group already have lesson at this time");
        if (teacherSchedule(lesson.getTeacher()).stream()
                .filter(coup -> coup.getDayOfWeek().equals(lesson.getDayOfWeek()))
                .filter(coup -> coup.getLessonTime().equals(lesson.getLessonTime()))
                .anyMatch(coup -> coup.getWeekParity().equals(lesson.getWeekParity()) || coup.getWeekParity().equals(WeekParity.ALL_WEEKS)))
            throw new AddLessonException("Can`t set current lesson, cause teacher already have lesson at this time");
        if (auditorySchedule(lesson.getAuditory()).stream()
                .filter(coup -> coup.getDayOfWeek().equals(lesson.getDayOfWeek()))
                .filter(coup -> coup.getLessonTime().equals(lesson.getLessonTime()))
                .anyMatch(coup -> coup.getWeekParity().equals(lesson.getWeekParity()) || coup.getWeekParity().equals(WeekParity.ALL_WEEKS)))
            throw new AddLessonException("Can`t set current lesson, cause auditory already busy at this time");
        schedule.add(lesson);
        return this;
    }

    public void save(String path) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            long start = System.currentTimeMillis();
            try {
                JSONConverter.serialise(path, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;
            System.out.println("to JSON -> " + timeConsumedMillis + "ms");
            return timeConsumedMillis;
        });
        executorService.shutdown();
    }

    public void load(String path) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(()-> {
            long start = System.currentTimeMillis();
            try {
//                schedule = JSONConverter.deserialize("ScheduleInTest.json");
                this.schedule = JSONConverter.deserialize(path).schedule;
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }
            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;
            System.out.println("from JSON -> " + timeConsumedMillis + "ms");
            return timeConsumedMillis;
        });
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
