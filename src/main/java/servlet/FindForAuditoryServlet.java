package servlet;

import dao.AuditoryDao;
import dao.LessonDao;
import dto.LessonDto;
import enums.WeekParity;
import model.Auditory;

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

@WebServlet(urlPatterns = "/for_auditory")
public class FindForAuditoryServlet extends HttpServlet {
//    private static final Logger LOGGER = LogManager.getLogger(FindForAuditoryServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuditoryDao ad = new AuditoryDao();
        LessonDao ld = new LessonDao();

        int auditoryId;

        if (req.getParameter("auditoryId") == null) {
            auditoryId = 1;
        } else {
            auditoryId = Integer.parseInt(req.getParameter("auditoryId"));
        }

        List<Auditory> auditories;
        List<LessonDto> today;
        List<LessonDto> tomorrow;
        List<LessonDto> I_week;
        List<LessonDto> II_week;
        try {
            auditories = ad.getAll();
            LocalDate now = LocalDate.now();

            I_week = ld.getAllForAuditory(auditoryId, WeekParity.UNPAIR_WEEK);
            II_week = ld.getAllForAuditory(auditoryId, WeekParity.PAIR_WEEK);
            today = Methods.selectTodayLessons(I_week, II_week);
            tomorrow = Methods.selectTomorrowLessons(I_week, II_week);

            req.setAttribute("todayDay", now.getDayOfWeek().toString());
            req.setAttribute("tomorrowDay", DayOfWeek.values()[(Arrays.asList(DayOfWeek.values()).indexOf(now.getDayOfWeek()) + 1) % 7].toString());
            req.setAttribute("auditoryId", auditoryId);
            req.setAttribute("auditories", auditories);
            req.setAttribute("today", today);
            req.setAttribute("tomorrow", tomorrow);
            req.setAttribute("I_week", I_week);
            req.setAttribute("II_week", II_week);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("auditory.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException | ParseException e) {
//            LOGGER.error(e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
