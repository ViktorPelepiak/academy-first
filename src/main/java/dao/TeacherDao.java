package dao;

import db.DBConnection;
import model.Teacher;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TeacherDao implements Dao<Teacher> {
    private final String GET     = "select * from public.teachers as t where t.teacher_id = ?";
    private final String GET_ALL = "select * from public.teachers";
    private final String SAVE    = "insert into public.teachers (first_name, last_name, father_name, date_of_birth, info) values (?, ?, ?, ?, ?)";
    private final String UPDATE  = "update schedule.public.teachers set first_name = ?, last_name = ?, father_name = ?, date_of_birth = ?, info = ? where teacher_id = ?";
    private final String DELETE  = "delete from teachers where teacher_id = ?";

    @Override
    public Optional<Teacher> get(long id) throws SQLException {
        Teacher teacher;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            Date date = res.getDate("date_of_birth");
            teacher = new Teacher()
                    .setId(res.getLong("teacher_id"))
                    .setFirstName(res.getString("first_name"))
                    .setLastName(res.getString("last_name"))
                    .setFatherName(res.getString("father_name"))
                    .setDateOfBirth(LocalDate.of(date.getYear(), date.getMonth(), date.getDay()))
                    .setInfo(res.getString("info"));
        }
        return Optional.ofNullable(teacher);
    }

    @Override
    public List<Teacher> getAll() throws SQLException {
        List<Teacher> teachers = new LinkedList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(GET_ALL);
            while (res.next()) {
                Date date = res.getDate("date_of_birth");
                teachers.add(new Teacher()
                        .setId(res.getLong("teacher_id"))
                        .setFirstName(res.getString("first_name"))
                        .setLastName(res.getString("last_name"))
                        .setFatherName(res.getString("father_name"))
                        .setDateOfBirth(LocalDate.of(date.getYear(), date.getMonth(), date.getDay()))
                        .setInfo(res.getString("info")));
            }
        }
        return teachers;
    }

    @Override
    public void save(Teacher teacher) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE)) {
            statement.setString(1,teacher.getFirstName());
            statement.setString(2,teacher.getLastName());
            statement.setString(3,teacher.getFatherName());
            statement.setDate(4, Date.valueOf(teacher.getDateOfBirth()));
            statement.setString(5,teacher.getInfo());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Teacher teacher) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1,teacher.getFirstName());
            statement.setString(2,teacher.getLastName());
            statement.setString(3,teacher.getFatherName());
            statement.setDate(4, Date.valueOf(teacher.getDateOfBirth()));
            statement.setString(5,teacher.getInfo());
            statement.setLong(6,teacher.getId());
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
