package servlet.teacher;

import dao.TeacherDao;
import model.Teacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import servlet.FindForGroupsServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/teacher_change")
public class TeacherAddOrEditItem extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(FindForGroupsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            req.setAttribute("id", "");
            req.setAttribute("f_name", "");
            req.setAttribute("l_name", "");
            req.setAttribute("fth_name", "");
            req.setAttribute("dob", "");
            req.setAttribute("info", "");
        } else {
            Long id = Long.valueOf(req.getParameter("id"));
            TeacherDao td = new TeacherDao();
            Teacher t;
            try {
                t = td.get(id).get();
                req.setAttribute("id", t.getId());
                req.setAttribute("f_name", t.getFirstName());
                req.setAttribute("l_name", t.getLastName());
                req.setAttribute("fth_name", t.getFatherName());
                req.setAttribute("dob", t.getDateOfBirth());
                req.setAttribute("info", t.getInfo());

                req.setAttribute("error", null);
            } catch (SQLException e) {
                req.setAttribute("error", e.getMessage());
                LOGGER.error(e);
//                e.printStackTrace();
            }
        }

        req.getRequestDispatcher("teacher_edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeacherDao td = new TeacherDao();
        Teacher t = new Teacher()
                .setFirstName(req.getParameter("f_name"))
                .setLastName(req.getParameter("l_name"))
                .setFatherName(req.getParameter("fth_name"))
                .setDateOfBirth(LocalDate.parse(req.getParameter("dob")))
                .setInfo(req.getParameter("info"));
        try {
            if (req.getParameter("id") == null || req.getParameter("id").equals("")) {
                td.save(t);
            } else {
                t.setId(Long.valueOf(req.getParameter("id")));
                td.update(t);
            }
            req.setAttribute("error", null);
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
            LOGGER.error(e);
//            e.printStackTrace();
        }
        new servlet.teacher.TeacherCrudServlet().doGet(req, resp);
    }
}
