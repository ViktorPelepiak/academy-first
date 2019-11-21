package servlet;

import dao.*;
import enums.LessonType;
import enums.WeekParity;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger LOGGER = LogManager.getLogger(FindForGroupsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("lessonTime", new LessonTimeDao().getAll());
            req.setAttribute("groups", new GroupDao().getAll());
            req.setAttribute("teachers", new TeacherDao().getAll());
            req.setAttribute("auditories", new AuditoryDao().getAll());
            req.setAttribute("subjects", new SubjectDao().getAll());
            req.setAttribute("error", null);
        } catch (SQLException e) {
            LOGGER.error(e);
            req.setAttribute("error", e.getMessage());
//            e.printStackTrace();
        }
        req.getRequestDispatcher("add_lesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new LessonDao().save(new Lesson()
                    .setLessonTime(new LessonTime().setLessonNumber(Integer.parseInt(req.getParameter("lessonTime"))))
                    .setGroup(new Group().setId(Long.valueOf(req.getParameter("group"))))
                    .setType(LessonType.values()[Integer.parseInt(req.getParameter("type"))])
                    .setTeacher(new Teacher().setId(Long.valueOf(req.getParameter("teacher"))))
                    .setAuditory(new Auditory().setId(Long.valueOf(req.getParameter("auditory"))))
                    .setWeekParity(WeekParity.values()[Integer.parseInt(req.getParameter("weekParity"))])
                    .setDayOfWeek(DayOfWeek.values()[Integer.parseInt(req.getParameter("day"))])
                    .setSubject(new Subject().setId(Long.valueOf(req.getParameter("subject"))))
            );
            req.setAttribute("error", null);
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
            LOGGER.error(e);
//            e.printStackTrace();
        }
        finally {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
