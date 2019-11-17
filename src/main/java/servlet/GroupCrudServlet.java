package servlet;

import dao.GroupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/group")
public class GroupCrudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            req.setAttribute("groups", new GroupDao().getAll());
            req.getRequestDispatcher("group_list.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DO_POST");
        try {
            System.out.println("del_id -> " + req.getParameter("del_id"));
            new GroupDao().deleteById(Long.valueOf(req.getParameter("del_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }


}
