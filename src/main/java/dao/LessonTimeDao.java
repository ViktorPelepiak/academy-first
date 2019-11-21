package dao;

import db.DBConnection;
import model.LessonTime;

import java.sql.*;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LessonTimeDao implements Dao<LessonTime> {
    private final String GET = "select * from lessons_time where lesson_number = ?";
    private final String GET_ALL = "select * from lessons_time";
    private final String SAVE = "insert into lessons_time (lesson_number, begin_time, end_time) values (?, ?, ?)";
    private final String UPDATE = "update lessons_time set begin_time = ?, end_time = ? where lesson_number = ?";
    private final String DELETE = "delete from lessons_time where lesson_number = ?";

    @Override
    public Optional<LessonTime> get(long id) throws SQLException {
        LessonTime lessonTime;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setInt(1, (int) id);
            ResultSet res = statement.executeQuery();
            lessonTime = new LessonTime()
                    .setLessonNumber(res.getInt("lesson_number"))
                    .setBeginTime(LocalTime.of(res.getTime("begin_time").getHours(), res.getTime("begin_time").getMinutes()))
                    .setEndTime(LocalTime.of(res.getTime("end_time").getHours(), res.getTime("end_time").getMinutes()));
        }
        return Optional.ofNullable(lessonTime);
    }

    @Override
    public List<LessonTime> getAll() throws SQLException {
        List<LessonTime> lessonTimes = new LinkedList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(GET_ALL);
            while (res.next()) {
                lessonTimes.add(new LessonTime()
                        .setLessonNumber(res.getInt("lesson_number"))
                        .setBeginTime(LocalTime.of(res.getTime("begin_time").getHours(), res.getTime("begin_time").getMinutes()))
                        .setEndTime(LocalTime.of(res.getTime("end_time").getHours(), res.getTime("end_time").getMinutes())));
            }
        }
        return lessonTimes;
    }

    @Override
    public LessonTime save(LessonTime lessonTime) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            statement.setInt(1, lessonTime.getLessonNumber());
            statement.setTime(2, Time.valueOf(lessonTime.getBeginTime()));
            statement.setTime(3, Time.valueOf(lessonTime.getEndTime()));
            statement.executeUpdate();
        }
        return lessonTime;
    }

    @Override
    public LessonTime update(LessonTime lessonTime) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setTime(1, Time.valueOf(lessonTime.getBeginTime()));
            statement.setTime(2, Time.valueOf(lessonTime.getEndTime()));
            statement.setInt(3, lessonTime.getLessonNumber());
            statement.executeUpdate();
        }
        return lessonTime;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, Math.toIntExact(id));
            statement.executeUpdate();
        }
    }
}
