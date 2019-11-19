package servlet.auditory;

import dao.AuditoryDao;
import dao.GroupDao;
import dao.TeacherDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/auditory")
public class AuditoryCrudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("auditory", new AuditoryDao().getAll());
            req.getRequestDispatcher("auditory_list.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new AuditoryDao().deleteById(Long.valueOf(req.getParameter("del_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }


}
