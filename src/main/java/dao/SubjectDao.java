package dao;

import db.DBConnection;
import model.Subject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SubjectDao implements Dao<Subject> {
    private final String GET = "select * from public.subjects as s where s.subject_id = ?";
    private final String GET_ALL = "select * from public.subjects";
    private final String CREATE = "insert into public.subjects (name) values (?)";
    private final String UPDATE = "update public.subjects set name  = ? where subject_id = ?";
    private final String DELETE = "delete from subjects where subject_id = ?";

    @Override
    public Optional<Subject> get(long id) throws SQLException {
        Subject subject;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            res.next();
            subject = new Subject()
                    .setId(res.getLong("subject_id"))
                    .setName(res.getString("name"));
        }
        return Optional.ofNullable(subject);
    }

    @Override
    public List<Subject> getAll() throws SQLException {
        List<Subject> subjects = new LinkedList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(GET_ALL);
            while (res.next()) {
                subjects.add(new Subject()
                        .setId(res.getLong("subject_id"))
                        .setName(res.getString("name")));
            }
        }
        return subjects;
    }

    @Override
    public Subject save(Subject subject) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, subject.getName());
            int id = statement.executeUpdate();

            if (id == 0) {
                throw new SQLException("Creating subject failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subject.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating subject failed, no ID obtained.");
                }
            }
        }

        return subject;
    }

    @Override
    public Subject update(Subject subject) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, subject.getName());
            statement.setLong(2, subject.getId());
            statement.executeUpdate();
        }
        return subject;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public static void main(String[] args) throws SQLException {
        SubjectDao sd = new SubjectDao();
        Subject subject = new Subject().setId(1L).setName("subject");
        sd.update(subject);
//        sd.save(subject);
        //sd.update(subject.setName("math"));
        //sd.deleteById(subject);
    }
}
