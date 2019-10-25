import collection.Schedule;
import exception.GeneralValidationException;
import io.JSONConverter;
import model.Teacher;
import validation.Validator;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Schedule schedule = new Schedule();

//        Teacher teacher1 = new Teacher()
//                .setLastName("Filipchuk")
//                .setFirstName("Mykola")
//                .setFatherName("Petrovych")
//                .setInfo("some teacher")
//                .setDateOfBirth(LocalDate.now());
//        Teacher teacher2 = new Teacher()
//                .setLastName("Bihun")
//                .setFirstName("Yaroslav")
//                .setFatherName("Yosypovych")
//                .setInfo("cool teacher")
//                .setDateOfBirth(LocalDate.now());
//
//        LessonTime bellFirst = new LessonTime()
//                .setLessonNumber(1)
//                .setBeginTime(LocalTime.of(8,20))
//                .setEndTime(LocalTime.of(9,40));
//
//        Subject subject1 = new Subject()
//                .setName("WEB-design");
//
//        Auditory auditory1 = new Auditory()
//                .setBuildingNumber((short) 1)
//                .setFloor((short) 1)
//                .setAuditoryNumber("1");
//        Auditory auditory2 = new Auditory()
//                .setBuildingNumber((short) 1)
//                .setFloor((short) 1)
//                .setAuditoryNumber("2");
//
//        Group group1 = new Group()
//                .setGroupNumber("402")
//                .setCourse((short) 4)
//                .setFaculty("FMI")
//                .setSpecialisation("Applied math");
//        Group group2 = new Group()
//                .setGroupNumber("422")
//                .setCourse((short) 4)
//                .setFaculty("FMI")
//                .setSpecialisation("Applied math(short form)");
//
//        Lesson couple1 = new Lesson()
//                .setAuditory(auditory1)
//                .setLessonTime(bellFirst)
//                .setDayOfWeek(DayOfWeek.MONDAY)
//                .setWeekParity(WeekParity.UNPAIR_WEEK)
//                .setGroup(group1)
//                .setSubject(subject1)
//                .setTeacher(teacher1)
//                .setType(LessonType.LECTURE);
//        Lesson couple2 = new Lesson()
//                .setAuditory(auditory2)
//                .setLessonTime(bellFirst)
//                .setDayOfWeek(DayOfWeek.MONDAY)
//                .setWeekParity(WeekParity.PAIR_WEEK)
//                .setGroup(group1)
//                .setSubject(subject1)
//                .setTeacher(teacher2)
//                .setType(LessonType.LECTURE);
//
//        try {
//            schedule.addLesson(couple1);
//            System.out.println(schedule.teacherSchedule(teacher1));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            JSONConverter.serialise("ScheduleOutTest.json", schedule);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println("try open file");
            schedule = JSONConverter.deserialize("ScheduleInTest.json");

            System.out.println("try print data");
            System.out.println(schedule.getSchedule());

            System.out.println("program finish");
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        Teacher teacher1 = new Teacher()
                .setLastName(null)
                .setFirstName(null)
                .setFatherName("Petrovych")
                .setInfo("some teacher")
                .setDateOfBirth(LocalDate.of(1999,1,1));

        try {
            Validator.validate(teacher1);
        } catch (GeneralValidationException e) {
            System.out.println(e.getValidationErrors());
        }
    }

}
