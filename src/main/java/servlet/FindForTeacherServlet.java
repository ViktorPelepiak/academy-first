package servlet;

import dao.LessonDao;
import dao.TeacherDao;
import dto.LessonDto;
import enums.WeekParity;
import model.Teacher;
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
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/for_teacher")
public class FindForTeacherServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FindForGroupsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeacherDao td = new TeacherDao();
        LessonDao ld = new LessonDao();

        int teacherId;

        List<Teacher> teachers;
        List<LessonDto> today;
        List<LessonDto> tomorrow;
        List<LessonDto> I_week;
        List<LessonDto> II_week;
        try {
            teachers = td.getAll();

            LocalDate date;
            LocalDate now = LocalDate.now();

            if (req.getParameter("teacherId") == null) {
                teacherId = Math.toIntExact(teachers.get(0).getId());
            } else {
                teacherId = Integer.parseInt(req.getParameter("teacherId"));
            }

            I_week = ld.getAllForTeacher(teacherId, WeekParity.UNPAIR_WEEK);
            II_week = ld.getAllForTeacher(teacherId, WeekParity.PAIR_WEEK);
            today = Methods.selectTodayLessons(I_week, II_week);
            tomorrow = Methods.selectTomorrowLessons(I_week, II_week);

            req.setAttribute("todayDay", now.getDayOfWeek().toString());
            req.setAttribute("tomorrowDay", DayOfWeek.values()[(Arrays.asList(DayOfWeek.values()).indexOf(now.getDayOfWeek()) + 1) % 7].toString());
            req.setAttribute("teacherId", teacherId);
            req.setAttribute("teachers", teachers);
            req.setAttribute("today", today);
            req.setAttribute("tomorrow", tomorrow);
            req.setAttribute("I_week", I_week);
            req.setAttribute("II_week", II_week);
            req.setAttribute("error", null);
        } catch (SQLException | ParseException e) {
            req.setAttribute("error", e.getMessage());
            LOGGER.error(e);
//            e.printStackTrace();
        }finally {
            req.getRequestDispatcher("teacher.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
