package servlet.subject;

import dao.SubjectDao;
import model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import servlet.FindForGroupsServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/subjects")
public class SubjectCrudServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FindForGroupsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Subject> subjects = new SubjectDao().getAll();
            req.setAttribute("size", subjects.size());
            req.setAttribute("subjects", subjects);
            req.setAttribute("error", null);
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
            LOGGER.error(e);
//            e.printStackTrace();
        } finally {
            req.getRequestDispatcher("subject_list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("error", null);
            new SubjectDao().deleteById(Long.valueOf(req.getParameter("del_id")));
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
            LOGGER.error(e);
//            e.printStackTrace();
        }
        doGet(req, resp);
    }


}
