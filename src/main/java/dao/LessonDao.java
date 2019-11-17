package dao;

import db.DBConnection;
import enums.LessonType;
import enums.WeekParity;
import model.*;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LessonDao implements Dao<Lesson> {
    private final String GET_ALL = "select * from lessons l " +
            "left join lessons_time lt on lt.lesson_number = l.lesson_time_id " +
            "left join groups g on l.group_id = g.group_id " +
            "left join teachers t on l.teacher_id = t.teacher_id " +
            "left join auditories a on l.auditory_id = a.building_number " +
            "left join subjects s on l.subject_id = s.subject_id ";
    private final String GET = GET_ALL +"where l.lesson_id = ?";
    private final String SAVE = "insert into lessons (lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "update lessons set lesson_time_id = ?, group_id = ?, lesson_type = ?, teacher_id = ?, auditory_id = ?, week_parity_id = ?, day_of_week_id = ?, subject_id = ? where lesson_id = ?";
    private final String DELETE = "delete from lessons where lesson_id = ?";

    private Lesson buildLesson(ResultSet res) throws SQLException {
        Lesson lesson = new Lesson()
                .setId(res.getLong("lesson_id"))
                .setLessonTime(new LessonTime()
                        .setLessonNumber(res.getInt("lesson_number"))
                        .setBeginTime(LocalTime.of(res.getTime("begin_time").getHours(), res.getTime("begin_time").getMinutes()))
                        .setEndTime(LocalTime.of(res.getTime("end_time").getHours(), res.getTime("end_time").getMinutes())))
                .setGroup(new Group()
                        .setId(res.getLong("group_id"))
                        .setFaculty(res.getString("faculty"))
                        .setSpecialisation(res.getString("specialisation"))
                        .setGroupNumber(res.getString("group_number"))
                        .setCourse(res.getInt("course")))
                .setType(LessonType.values()[res.getByte("lesson_type")])
                .setTeacher(new Teacher()
                        .setId(res.getLong("teacher_id"))
                        .setFirstName(res.getString("first_name"))
                        .setLastName(res.getString("last_name"))
                        .setFatherName(res.getString("father_name"))
                        .setDateOfBirth(LocalDate.of(res.getDate("date_of_birth").getYear(), res.getDate("date_of_birth").getMonth(), res.getDate("date_of_birth").getDay()))
                        .setInfo(res.getString("info")))
                .setAuditory(new Auditory()
                        .setId(res.getLong("auditory_id"))
                        .setBuildingNumber(res.getInt("building_number"))
                        .setFloor(res.getInt("floor"))
                        .setAuditoryNumber(res.getString("auditory_number")))
                .setWeekParity(WeekParity.values()[res.getByte("week_parity_id")])
                .setDayOfWeek(DayOfWeek.values()[res.getByte("day_of_week_id")])
                .setSubject(new Subject()
                        .setId(res.getLong("subject_id"))
                        .setName(res.getString("name")));
        return lesson;
    }

    @Override
    public Optional<Lesson> get(long id) throws SQLException {
        Lesson lesson;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            lesson = buildLesson(res);
        }
        return Optional.ofNullable(lesson);
    }

    @Override
    public List<Lesson> getAll() throws SQLException {
        List<Lesson> lessons = new LinkedList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            ResultSet res = statement.executeQuery(GET_ALL);
            while (res.next()){
                lessons.add(buildLesson(res));
            }
        }
        return lessons;
    }

    @Override
    public void save(Lesson lesson) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE)){
            fillLessonBody(lesson, statement);
            statement.executeUpdate();
        }
    }

    private void fillLessonBody(Lesson lesson, PreparedStatement statement) throws SQLException {
        statement.setInt(1, lesson.getLessonTime().getLessonNumber());
        statement.setInt(2, Math.toIntExact(lesson.getGroup().getId()));
        statement.setInt(3, Arrays.asList(LessonType.values()).indexOf(lesson.getType()));
        statement.setInt(4, Math.toIntExact(lesson.getTeacher().getId()));
        statement.setInt(5, Math.toIntExact(lesson.getAuditory().getId()));
        statement.setInt(6, Arrays.asList(WeekParity.values()).indexOf(lesson.getWeekParity()));
        statement.setInt(7, Arrays.asList(DayOfWeek.values()).indexOf(lesson.getDayOfWeek()));
        statement.setInt(8, Math.toIntExact(lesson.getSubject().getId()));
    }

    @Override
    public void update(Lesson lesson) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)){
            fillLessonBody(lesson, statement);
            statement.setLong(9, lesson.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
