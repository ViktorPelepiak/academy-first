package servlet;

import dao.GroupDao;
import dao.LessonDao;
import dto.LessonDto;
import enums.WeekParity;
import model.Group;
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

@WebServlet(urlPatterns = "/for_group")
public class FindForGroupsServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FindForGroupsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupDao gd = new GroupDao();
        LessonDao ld = new LessonDao();

        int groupId;

        if (req.getParameter("groupId") == null) {
            groupId = 1;
        } else {
            groupId = Integer.parseInt(req.getParameter("groupId"));
        }

        List<Group> groups;
        List<LessonDto> today;
        List<LessonDto> tomorrow;
        List<LessonDto> I_week;
        List<LessonDto> II_week;
        try {
            groups = gd.getAll();
            LocalDate now = LocalDate.now();

            I_week = ld.getAllForGroup(groupId, WeekParity.UNPAIR_WEEK);
            II_week = ld.getAllForGroup(groupId, WeekParity.PAIR_WEEK);
            today = Methods.selectTodayLessons(I_week, II_week);
            tomorrow = Methods.selectTomorrowLessons(I_week, II_week);

            req.setAttribute("todayDay", now.getDayOfWeek().toString());
            req.setAttribute("tomorrowDay", DayOfWeek.values()[(Arrays.asList(DayOfWeek.values()).indexOf(now.getDayOfWeek()) + 1) % 7].toString());
            req.setAttribute("groupId", groupId);
            req.setAttribute("groups", groups);
            req.setAttribute("today", today);
            req.setAttribute("tomorrow", tomorrow);
            req.setAttribute("I_week", I_week);
            req.setAttribute("II_week", II_week);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("group.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException | ParseException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
