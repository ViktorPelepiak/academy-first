package servlet.subject;

import dao.GroupDao;
import dao.SubjectDao;
import dao.TeacherDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/subjects")
public class SubjectCrudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("subjects", new SubjectDao().getAll());
            req.getRequestDispatcher("subject_list.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new SubjectDao().deleteById(Long.valueOf(req.getParameter("del_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }


}
