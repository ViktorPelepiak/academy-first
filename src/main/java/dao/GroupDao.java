package dao;

import db.DBConnection;
import model.Group;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GroupDao implements Dao<Group> {
    private final String GET     = "select * from groups where group_id = ?";
    private final String GET_ALL = "select * from groups;";
    private final String SAVE    = "insert into groups (faculty, specialisation, group_number, course) values (?, ?, ?, ?)";
    private final String UPDATE  = "update groups set faculty = ?, specialisation = ?, group_number = ?, course = ? where group_id = ?";
    private final String DELETE  = "delete from groups where group_id = ?";


    @Override
    public Optional<Group> get(long id) throws SQLException {
        Group group;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET)){
            statement.setLong(1,id);
            ResultSet res = statement.executeQuery();
            group = new Group()
                    .setId(res.getLong("group_id"))
                    .setFaculty(res.getString("faculty"))
                    .setSpecialisation(res.getString("specialisation"))
                    .setGroupNumber(res.getString("group_number"))
                    .setCourse(res.getInt("course"));
        }
        return Optional.ofNullable(group);
    }

    @Override
    public List<Group> getAll() throws SQLException {
        List<Group> groups = new LinkedList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(GET_ALL);
            while (res.next()){
                groups.add(new Group()
                        .setId(res.getLong("group_id"))
                        .setFaculty(res.getString("faculty"))
                        .setSpecialisation(res.getString("specialisation"))
                        .setGroupNumber(res.getString("group_number"))
                        .setCourse(res.getInt("course")));
            }
        }
        return groups;
    }

    @Override
    public void save(Group group) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE)) {
            statement.setString(1, group.getFaculty());
            statement.setString(2,group.getSpecialisation());
            statement.setString(3, group.getGroupNumber());
            statement.setInt(4, group.getCourse());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Group group) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, group.getFaculty());
            statement.setString(2,group.getSpecialisation());
            statement.setString(3, group.getGroupNumber());
            statement.setInt(4, group.getCourse());
            statement.setLong(5,group.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1,id);
            statement.executeUpdate();
        }
    }
}
