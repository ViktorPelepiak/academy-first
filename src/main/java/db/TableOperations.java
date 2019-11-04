package db;

import org.postgresql.jdbc2.optional.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableOperations {
    public void createAuditoriesTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute("create table auditories (" +
                    "auditory_id integer not null, " +
                    "building_number integer not null," +
                    "floor integer not null," +
                    "auditory_number character varying(10) not null," +
                    "primary key (auditory_id));");
        }
    }

    public void dropAuditoriesTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate("drop table public.auditories;");
        }
    }

    public void createSubjectsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute("create table subjects (" +
                    "subject_id serial not null," +
                    "name character varying(15) not null," +
                    "primary key (subject_id));");
        }
    }

    public void dropSubjectsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate("drop table public.subjects;");
        }
    }

    public void createLessonsTimeTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute("create table lessons_time (" +
                    "lesson_number integer not null," +
                    "begin_time time without time zone not null," +
                    "end_time time without time zone not null," +
                    "primary key (lesson_number));");
        }
    }

    public void dropLessonsTimeTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate("drop table public.lessons_time;");
        }
    }

    public void createTeachersTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute("create table teachers (" +
                    "teacher_id serial not null," +
                    "first_name character varying(15) not null ," +
                    "last_name character varying (15) not null ," +
                    "father_name character varying (15) not null ," +
                    "date_of_birth date not null ," +
                    "info character varying (200)," +
                    "primary key (teacher_id));");
        }
    }

    public void dropTeahersTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate("drop table public.teachers;");
        }
    }

    public void createGroupsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute("create table groups (" +
                    "group_id serial not null," +
                    "faculty character varying(15) not null," +
                    "specialisation character varying(20) not null," +
                    "group_number character varying(10) not null," +
                    "course integer not null," +
                    "primary key (group_id));");
        }
    }

    public void dropGroupsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate("drop table public.groups;");
        }
    }

    public void createLessonsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute("create table lessons (" +
                    "lesson_id bigserial not null," +
                    "lesson_time_id integer not null," +
                    "group_id integer not null," +
                    "lesson_type integer not null," +
                    "teacher_id integer not null," +
                    "auditory_id integer not null," +
                    "week_parity_id integer not null," +
                    "day_of_week_id integer not null," +
                    "subject_id integer not null," +
                    "primary key (lesson_id)," +
                    "foreign key (lesson_time_id) references public.lessons_time(lesson_number)," +
                    "foreign key (group_id) references public.groups(group_id)," +
                    "foreign key (teacher_id) references public.teachers(teacher_id)," +
                    "foreign key (auditory_id) references public.auditories(building_number)," +
                    "foreign key (subject_id) references public.subjects(subject_id));");
        }
    }

    public void dropLessonsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate("drop table public.lessons;");
        }
    }

    public void createAllTable() throws SQLException, ClassNotFoundException {
        createAuditoriesTable();
        createGroupsTable();
        createLessonsTimeTable();
        createSubjectsTable();
        createTeachersTable();
        createLessonsTable();
    }

    public void dropAllTables() throws SQLException, ClassNotFoundException {
        dropLessonsTable();
        dropAuditoriesTable();
        dropGroupsTable();
        dropLessonsTimeTable();
        dropSubjectsTable();
        dropTeahersTable();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TableOperations to = new TableOperations();
        to.createAllTable();
        //to.dropAllTables();
    }
}
