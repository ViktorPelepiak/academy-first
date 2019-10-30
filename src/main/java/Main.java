import enums.LessonType;
import enums.WeekParity;
import exception.AddLessonException;
import model.*;
import service.Schedule;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static final int GROUP_QUANTITY = 5;
    public static final int TEACHER_QUANTITY = 5;
    public static final int SUBJECT_QUANTITY = 5;
    public static final int AUDITORY_QUANTITY = 10;

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, JAXBException {
        Schedule schedule = new Schedule();
        
        List<Teacher> teachers = new LinkedList<>();
        for (int i = 0; i < TEACHER_QUANTITY; i++) {
            teachers.add(new Teacher()
                    .setLastName("LN_" + i)
                    .setFirstName("FN_" + i)
                    .setFatherName("FthN_" + i)
                    .setInfo("some teacher")
                    .setDateOfBirth(LocalDate.of(1980,i+1,1))
            );
        }
        
        List<LessonTime> lessonTimes= new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            lessonTimes.add(new LessonTime()
                    .setLessonNumber(i)
                    .setBeginTime(LocalTime.of(8,20))
                    .setEndTime(LocalTime.of(9,40))
            );
        }
        
        List<Subject> subjects = new LinkedList<>();
        for (int i = 0; i < SUBJECT_QUANTITY; i++) {
            subjects.add(new Subject()
                    .setName("Sub_" + i)
            );
        }
        
        List<Auditory> auditories = new LinkedList<>();
        for (int i = 0; i < AUDITORY_QUANTITY; i++) {
            auditories.add(new Auditory()
                .setBuildingNumber(1)
                .setFloor(i/5 + 1)
                .setAuditoryNumber("" + i)
            );

        }

        List<Group> groups = new LinkedList<>();
        for (int i = 0; i < GROUP_QUANTITY; i++) {
            groups.add(new Group()
                    .setGroupNumber("40" + i)
                    .setCourse(4)
                    .setFaculty("FMI")
                    .setSpecialisation("Spec_" + i)
            );
        }

        for (int i = 0; i < GROUP_QUANTITY; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 1; k < 6; k++) {
                    try {
                        schedule.addLesson(new Lesson()
                                .setAuditory(auditories.get(i))
                                .setLessonTime(lessonTimes.get(j))
                                .setDayOfWeek(DayOfWeek.of(k))
                                .setWeekParity(WeekParity.UNPAIR_WEEK)
                                .setGroup(groups.get(i))
                                .setSubject(subjects.get(i))
                                .setTeacher(teachers.get(i))
                                .setType(LessonType.LECTURE)
                        );
                    } catch (AddLessonException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        schedule.save("ScheduleOutTest.json");
        System.out.println("save");

        schedule.load("ScheduleInTest.json");
        System.out.println("load");
    }

}
