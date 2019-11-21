package servlet.group;

import dao.GroupDao;
import model.Group;
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

@WebServlet(urlPatterns = "/group")
public class GroupCrudServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FindForGroupsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Group> groups = new GroupDao().getAll();
            req.setAttribute("size", groups.size());
            req.setAttribute("groups", groups);
            req.setAttribute("error", null);
            req.getRequestDispatcher("group_list.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
            LOGGER.error(e);
//            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("error", null);
            new GroupDao().deleteById(Long.valueOf(req.getParameter("del_id")));
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
            LOGGER.error(e);
//            e.printStackTrace();
        }
        doGet(req, resp);
    }


}
