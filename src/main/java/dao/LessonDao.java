package dao;

import db.DBConnection;
import dto.LessonDto;
import enums.LessonType;
import enums.WeekParity;
import model.*;

import java.sql.*;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LessonDao implements Dao<Lesson> {
    private final String GET_ALL = "select * from lessons l " +
            " join lessons_time lt on lt.lesson_number = l.lesson_time_id " +
            " join groups g on l.group_id = g.group_id " +
            " join teachers t on l.teacher_id = t.teacher_id " +
            " join auditories a on l.auditory_id = a.auditory_id " +
            " join subjects s on l.subject_id = s.subject_id ";
    private final String GET_ALL_GROUP_ID = "select l.lesson_time_id, g.group_number, l.lesson_type, t.first_name, t.last_name, a.auditory_number, a.building_number, s.name, l.day_of_week_id" +
            " from lessons as l, groups as g, teachers as t, auditories as a, subjects as s " +
            "where l.group_id = g.group_id and l.teacher_id = t.teacher_id and l.auditory_id = a.auditory_id and l.subject_id = s.subject_id and g.group_id = ? and (week_parity_id = ? or week_parity_id = " + Arrays.asList(WeekParity.values()).indexOf(WeekParity.ALL_WEEKS) + ")";
    private final String GET_ALL_TEACHER_ID = "select l.lesson_time_id, g.group_number, l.lesson_type, t.first_name, t.last_name, a.auditory_number, a.building_number, s.name, l.day_of_week_id" +
            " from lessons as l, groups as g, teachers as t, auditories as a, subjects as s " +
            "where l.group_id = g.group_id and l.teacher_id = t.teacher_id and l.auditory_id = a.auditory_id and l.subject_id = s.subject_id and t.teacher_id = ? and (week_parity_id = ? or week_parity_id = " + Arrays.asList(WeekParity.values()).indexOf(WeekParity.ALL_WEEKS) + ")";
    private final String GET_ALL_AUDITORY_ID = "select l.lesson_time_id, g.group_number, l.lesson_type, t.first_name, t.last_name, a.auditory_number, a.building_number, s.name, l.day_of_week_id" +
            " from lessons as l, groups as g, teachers as t, auditories as a, subjects as s " +
            "where l.group_id = g.group_id and l.teacher_id = t.teacher_id and l.auditory_id = a.auditory_id and l.subject_id = s.subject_id and a.auditory_id = ? and (week_parity_id = ? or week_parity_id = " + Arrays.asList(WeekParity.values()).indexOf(WeekParity.ALL_WEEKS) + ")";
    private final String GET = GET_ALL + "where l.lesson_id = ?";
    private final String SAVE = "insert into lessons (lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) values (?, ?, ?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "update lessons set lesson_time_id = ?, group_id = ?, lesson_type = ?, teacher_id = ?, auditory_id = ?, week_parity_id = ?, day_of_week_id = ?, subject_id = ? where lesson_id = ?";
    private final String DELETE = "delete from lessons where lesson_id = ?";

    private Lesson buildLesson(ResultSet res) throws SQLException {
        Lesson lesson = new Lesson()
                .setId(res.getLong("lesson_id"))
                .setLessonTime(new LessonTime()
                        .setLessonNumber(res.getInt("lesson_number"))
                        .setBeginTime(res.getTime("begin_time").toLocalTime())
                        .setEndTime(res.getTime("end_time").toLocalTime()))
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
                        .setDateOfBirth(res.getDate("date_of_birth").toLocalDate())
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

    private LessonDto buildLessonDto(ResultSet res) throws SQLException {
        LessonDto result = new LessonDto()
                .setLessonTime(res.getInt("lesson_time_id"))
                .setGroupNumber(res.getString("group_number"))
                .setAuditory(res.getInt("building_number") + "-" + res.getString("auditory_number"))
                .setLessonType(res.getInt("lesson_type"))
                .setSubject(res.getString("name"))
                .setTeacher(res.getString("last_name") + " " + res.getString("first_name"))
                .setDay(res.getInt("day_of_week_id"));
        return result;
    }

    @Override
    public Optional<Lesson> get(long id) throws SQLException {
        Lesson lesson;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            lesson = buildLesson(res);
            statement.close();
            connection.close();
        }
        return Optional.ofNullable(lesson);
    }

    @Override
    public List<Lesson> getAll() throws SQLException {
        List<Lesson> lessons = new LinkedList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(GET_ALL);
            while (res.next()) {
                lessons.add(buildLesson(res));
            }
            statement.close();
            connection.close();
        }
        return lessons;
    }

    public List<LessonDto> getAllForGroup(int groupId, WeekParity weekParity) throws SQLException {
        List<LessonDto> lessons = new LinkedList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_GROUP_ID)) {
            statement.setInt(1, groupId);
            statement.setInt(2, Arrays.asList(WeekParity.values()).indexOf(weekParity));
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                lessons.add(buildLessonDto(res));
            }
            statement.close();
            connection.close();
        }
        return lessons;
    }

    public List<LessonDto> getAllForTeacher(int teacherId, WeekParity weekParity) throws SQLException {
        List<LessonDto> lessons = new LinkedList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_TEACHER_ID)) {
            statement.setInt(1, teacherId);
            statement.setInt(2, Arrays.asList(WeekParity.values()).indexOf(weekParity));
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                lessons.add(buildLessonDto(res));
            }
            statement.close();
            connection.close();
        }
        return lessons;
    }

    public List<LessonDto> getAllForAuditory(int auditoryId, WeekParity weekParity) throws SQLException {
        List<LessonDto> lessons = new LinkedList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_AUDITORY_ID)) {
            statement.setInt(1, auditoryId);
            statement.setInt(2, Arrays.asList(WeekParity.values()).indexOf(weekParity));
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                lessons.add(buildLessonDto(res));
            }
            statement.close();
            connection.close();
        }
        return lessons;
    }

    @Override
    public Lesson save(Lesson lesson) throws SQLException {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);

            fillLessonBody(lesson, statement);
            int id = statement.executeUpdate();

            if (id == 0) {
                throw new SQLException("Creating lesson failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lesson.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating lesson failed, no ID obtained.");
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
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
    public Lesson update(Lesson lesson) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            fillLessonBody(lesson, statement);
            statement.setLong(9, lesson.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        return lesson;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
    }


}
