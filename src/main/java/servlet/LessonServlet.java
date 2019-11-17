package servlet;

import dao.*;
import enums.LessonType;
import enums.WeekParity;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;

@WebServlet(urlPatterns = "/lessons")
public class LessonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("lessonTime", new LessonTimeDao().getAll());
            req.setAttribute("groups", new GroupDao().getAll());
            req.setAttribute("teachers", new TeacherDao().getAll());
            req.setAttribute("auditories", new AuditoryDao().getAll());
            req.setAttribute("subjects", new SubjectDao().getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("add_lesson.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new LessonDao().save(new Lesson()
                    .setLessonTime(new LessonTime().setLessonNumber(Integer.parseInt(req.getParameter("lessonTime"))))
                    .setGroup(new Group().setId(Long.valueOf(req.getParameter("group"))))
                    .setType(LessonType.values()[Integer.parseInt(req.getParameter("type"))])
                    .setTeacher(new Teacher().setId(Long.valueOf(req.getParameter("teacher"))))
                    .setAuditory(new Auditory().setId(Long.valueOf(req.getParameter("auditory"))))
                    .setWeekParity(WeekParity.values()[Integer.parseInt(req.getParameter("weekParity"))])
                    .setDayOfWeek(DayOfWeek.values()[Integer.parseInt(req.getParameter("day"))-1])
                    .setSubject(new Subject().setId(Long.valueOf(req.getParameter("subject"))))
            );
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
