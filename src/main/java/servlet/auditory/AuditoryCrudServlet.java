package servlet.auditory;

import dao.AuditoryDao;
import model.Auditory;
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

@WebServlet(urlPatterns = "/auditory")
public class AuditoryCrudServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FindForGroupsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Auditory> auditories = new AuditoryDao().getAll();
            req.setAttribute("size", auditories.size());
            req.setAttribute("auditory", auditories);
            req.getRequestDispatcher("auditory_list.jsp").forward(req, resp);
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new AuditoryDao().deleteById(Long.valueOf(req.getParameter("del_id")));
        } catch (SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
        doGet(req, resp);
    }


}
