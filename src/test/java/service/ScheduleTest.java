package service;

import enums.LessonType;
import enums.WeekParity;
import exception.AddLessonException;
import model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ScheduleTest {
    private Schedule schedule;
    private Teacher teacher1;
    private Teacher teacher2;
    private LessonTime firstLesson;
    private Subject subject1;
    private Auditory auditory1;
    private Auditory auditory2;
    private Group group1;
    private Group group2;
    private Lesson lesson1;
    private Lesson lesson2;
    private Lesson lesson3;

    @BeforeMethod
    public void setUp(){
        schedule = new Schedule();

        teacher1 = new Teacher()
                .setLastName("Filipchuk")
                .setFirstName("Mykola")
                .setFatherName("Petrovych")
                .setInfo("some teacher")
                .setDateOfBirth(LocalDate.now());
        teacher2 = new Teacher()
                .setLastName("Bihun")
                .setFirstName("Yaroslav")
                .setFatherName("Yosypovych")
                .setInfo("cool teacher")
                .setDateOfBirth(LocalDate.now());

        firstLesson = new LessonTime()
                .setLessonNumber(1)
                .setBeginTime(LocalTime.of(8,20))
                .setBeginTime(LocalTime.of(9,40));

        subject1 = new Subject()
                .setName("WEB-design");

        auditory1 = new Auditory()
                .setBuildingNumber((short) 1)
                .setFloor((short) 1)
                .setAuditoryNumber("1");
        auditory2 = new Auditory()
                .setBuildingNumber((short) 1)
                .setFloor((short) 1)
                .setAuditoryNumber("2");

        group1 = new Group()
                .setGroupNumber("402")
                .setCourse((short) 4)
                .setFaculty("FMI")
                .setSpecialisation("Applied math");
        group2 = new Group()
                .setGroupNumber("422")
                .setCourse((short) 4)
                .setFaculty("FMI")
                .setSpecialisation("Applied math(short form)");

        lesson1 = new Lesson()
                .setAuditory(auditory1)
                .setLessonTime(firstLesson)
                .setDayOfWeek(DayOfWeek.MONDAY)
                .setWeekParity(WeekParity.UNPAIR_WEEK)
                .setGroup(group1)
                .setSubject(subject1)
                .setTeacher(teacher1)
                .setType(LessonType.LECTURE);
        lesson2 = new Lesson()
                .setAuditory(auditory2)
                .setLessonTime(firstLesson)
                .setDayOfWeek(DayOfWeek.MONDAY)
                .setWeekParity(WeekParity.PAIR_WEEK)
                .setGroup(group2)
                .setSubject(subject1)
                .setTeacher(teacher2)
                .setType(LessonType.LECTURE);
        lesson3 = new Lesson()
                .setAuditory(auditory1)
                .setLessonTime(firstLesson)
                .setDayOfWeek(DayOfWeek.FRIDAY)
                .setWeekParity(WeekParity.UNPAIR_WEEK)
                .setGroup(group1)
                .setSubject(subject1)
                .setTeacher(teacher1)
                .setType(LessonType.LECTURE);
    }

    @Test
    public void successAddLessonTest(){
        try {
            assertTrue(schedule.addLesson(lesson1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expectedExceptions = AddLessonException.class)
    public void successAddLessonExceptionTest() throws AddLessonException {
        schedule.addLesson(lesson1);
        schedule.addLesson(lesson1);
    }

    @Test
    public void successWeekDayGroupScheduleTest(){
        try {
            schedule.addLesson(lesson1);
            schedule.addLesson(lesson2);
            schedule.addLesson(lesson3);
        } catch (AddLessonException e) {
            e.printStackTrace();
        }

        List<Lesson> lessons = schedule.weekDayGroupSchedule(group1,DayOfWeek.MONDAY);
        assertEquals(lessons,Arrays.asList(lesson1));
    }

    @Test
    public void successGroupScheduleTest(){
        try {
            schedule.addLesson(lesson1);
            schedule.addLesson(lesson2);
            schedule.addLesson(lesson3);
        } catch (AddLessonException e) {
            e.printStackTrace();
        }

        List<Lesson> lessons = schedule.groupSchedule(group1);
        assertEquals(lessons, Arrays.asList(lesson1,lesson3));
    }

    @Test
    public void successTeacherScheduleTest(){
        try {
            schedule.addLesson(lesson1);
            schedule.addLesson(lesson2);
            schedule.addLesson(lesson3);
        } catch (AddLessonException e) {
            e.printStackTrace();
        }

        List<Lesson> lessons = schedule.teacherSchedule(teacher2);
        assertEquals(lessons, Arrays.asList(lesson2));
    }

    @Test
    public void successAuditoryScheduleTest(){
        try {
            schedule.addLesson(lesson1);
            schedule.addLesson(lesson2);
            schedule.addLesson(lesson3);
        } catch (AddLessonException e) {
            e.printStackTrace();
        }

        List<Lesson> lessons = schedule.auditorySchedule(auditory2);
        assertEquals(lessons, Arrays.asList(lesson2));
    }

    @Test
    public void successSubjectScheduleTest(){
        try {
            schedule.addLesson(lesson1);
            schedule.addLesson(lesson2);
            schedule.addLesson(lesson3);
        } catch (AddLessonException e) {
            e.printStackTrace();
        }

        List<Lesson> lessons = schedule.subjectSchedule(subject1);
        assertEquals(lessons, Arrays.asList(lesson1,lesson2,lesson3));
    }
}
