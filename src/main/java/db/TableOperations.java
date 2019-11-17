package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableOperations {
    public final String CREATE_AUDITORY = "create table if not exists auditories (auditory_id serial not null, building_number integer not null, floor integer not null, auditory_number character varying(10) not null, primary key (auditory_id));";
    public final String CREATE_SUBJECTS = "create table if not exists  subjects (subject_id serial not null, name character varying(15) not null, primary key (subject_id));";
    public final String CREATE_LESSON_TIME = "create table if not exists  lessons_time (lesson_number integer not null, begin_time time without time zone not null, end_time time without time zone not null, primary key (lesson_number));";
    public final String CREATE_TEACHERS = "create table if not exists  teachers (teacher_id serial not null, first_name character varying(15) not null , last_name character varying (15) not null , father_name character varying (15) not null , date_of_birth date not null , info character varying (200), primary key (teacher_id));";
    public final String CREATE_GROUPS = "create table if not exists  groups (group_id serial not null, faculty character varying(15) not null, specialisation character varying(20) not null, group_number character varying(10) not null, course integer not null, primary key (group_id));";
    public final String CREATE_LESSONS = "create table if not exists  lessons (lesson_id bigserial not null, lesson_time_id integer not null, group_id integer not null, lesson_type integer not null, teacher_id integer not null, auditory_id integer not null, week_parity_id integer not null, day_of_week_id integer not null, subject_id integer not null," +
            " primary key (lesson_id), foreign key (lesson_time_id) references public.lessons_time(lesson_number), foreign key (group_id) references public.groups(group_id), foreign key (teacher_id) references public.teachers(teacher_id), foreign key (auditory_id) references public.auditories(auditory_id), foreign key (subject_id) references public.subjects(subject_id)," +
            "constraint kGroup unique (lesson_time_id, day_of_week_id, group_id), " +
            "constraint kTeacher unique (lesson_time_id, day_of_week_id, teacher_id), " +
            "constraint kAuditory unique (lesson_time_id, day_of_week_id, auditory_id));";
    public final String DROP_AUDITORY = "drop table public.auditories;";
    public final String DROP_SUBJECTS = "drop table public.subjects;";
    public final String DROP_LESSON_TIME = "drop table public.lessons_time;";
    public final String DROP_TEACHERS = "drop table public.teachers;";
    public final String DROP_GROUPS = "drop table public.groups;";
    public final String DROP_LESSONS = "drop table public.lessons;";

    public void createAuditoriesTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(CREATE_AUDITORY);
            statement.close();
            connection.close();
        }
    }

    public void dropAuditoriesTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(DROP_AUDITORY);
            statement.close();
            connection.close();
        }
    }

    public void createSubjectsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(CREATE_SUBJECTS);
            statement.close();
            connection.close();
        }
    }

    public void dropSubjectsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(DROP_SUBJECTS);
            statement.close();
            connection.close();
        }
    }

    public void createLessonsTimeTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(CREATE_LESSON_TIME);
            statement.close();
            connection.close();
        }
    }

    public void dropLessonsTimeTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(DROP_LESSON_TIME);
            statement.close();
            connection.close();
        }
    }

    public void createTeachersTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(CREATE_TEACHERS);
            statement.close();
            connection.close();
        }
    }

    public void dropTeahersTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(DROP_TEACHERS);
            statement.close();
            connection.close();
        }
    }

    public void createGroupsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(CREATE_GROUPS);
            statement.close();
            connection.close();
        }
    }

    public void dropGroupsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(DROP_GROUPS);
            statement.close();
            connection.close();
        }
    }

    public void createLessonsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(CREATE_LESSONS);
            statement.close();
            connection.close();
        }
    }

    public void dropLessonsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(DROP_LESSONS);
            statement.close();
            connection.close();
        }
    }

    public void createAllTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(CREATE_AUDITORY);
            statement.executeUpdate(CREATE_SUBJECTS);
            statement.executeUpdate(CREATE_LESSON_TIME);
            statement.executeUpdate(CREATE_TEACHERS);
            statement.executeUpdate(CREATE_GROUPS);
            statement.executeUpdate(CREATE_LESSONS);
            statement.close();
            connection.close();
        }
    }

    public void dropAllTables() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(DROP_LESSONS);
            statement.executeUpdate(DROP_AUDITORY);
            statement.executeUpdate(DROP_SUBJECTS);
            statement.executeUpdate(DROP_LESSON_TIME);
            statement.executeUpdate(DROP_TEACHERS);
            statement.executeUpdate(DROP_GROUPS);
            statement.close();
            connection.close();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TableOperations to = new TableOperations();
        to.dropAllTables();
        to.createAllTable();
    }
}
