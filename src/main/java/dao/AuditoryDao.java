package dao;

import db.DBConnection;
import model.Auditory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AuditoryDao implements Dao<Auditory> {
    private final String GET = "select * from auditories where auditory_id = ?";
    private final String GET_ALL = "select * from auditories";
    private final String SAVE = "insert into auditories (building_number, floor, auditory_number) values (?, ?, ?)";
    private final String UPDATE = "update auditories set building_number = ?, floor = ?, auditory_number = ? where auditory_id = ?";
    private final String DELETE = "delete from auditories where auditory_id = ?";

    @Override
    public Optional<Auditory> get(long id) throws SQLException {
        Auditory auditory;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            res.next();
            auditory = new Auditory()
                    .setId(res.getLong("auditory_id"))
                    .setBuildingNumber(res.getInt("building_number"))
                    .setFloor(res.getInt("floor"))
                    .setAuditoryNumber(res.getString("auditory_number"));
            statement.close();
            connection.close();
        }
        return Optional.ofNullable(auditory);
    }

    @Override
    public List<Auditory> getAll() throws SQLException {
        List<Auditory> auditories = new LinkedList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(GET_ALL);
            while (res.next()) {
                auditories.add(new Auditory()
                        .setId(res.getLong("auditory_id"))
                        .setBuildingNumber(res.getInt("building_number"))
                        .setFloor(res.getInt("floor"))
                        .setAuditoryNumber(res.getString("auditory_number")));
            }
            statement.close();
            connection.close();
        }
        return auditories;
    }

    @Override
    public Auditory save(Auditory auditory) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, auditory.getBuildingNumber());
            statement.setInt(2, auditory.getFloor());
            statement.setString(3, auditory.getAuditoryNumber());
            int id = statement.executeUpdate();

            if (id == 0) {
                throw new SQLException("Creating auditory failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    auditory.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating auditory failed, no ID obtained.");
                }
            }
            statement.close();
            connection.close();
        }
        return auditory;
    }

    @Override
    public Auditory update(Auditory auditory) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, auditory.getBuildingNumber());
            statement.setInt(2, auditory.getFloor());
            statement.setString(3, auditory.getAuditoryNumber());
            statement.setLong(4, auditory.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        return auditory;
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
