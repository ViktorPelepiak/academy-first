package servlet.teacher;

import dao.GroupDao;
import dao.TeacherDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/teachers")
public class TeacherCrudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("teachers", new TeacherDao().getAll());
            req.getRequestDispatcher("teacher_list.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new TeacherDao().deleteById(Long.valueOf(req.getParameter("del_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }


}
