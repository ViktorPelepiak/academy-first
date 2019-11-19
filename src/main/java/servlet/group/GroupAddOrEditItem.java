package servlet.group;

import dao.GroupDao;
import model.Group;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/group_change")
public class GroupAddOrEditItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id")==null) {
            req.setAttribute("id", "");
            req.setAttribute("fac", "");
            req.setAttribute("spec", "");
            req.setAttribute("num", "");
            req.setAttribute("course", "");
        }else{
            Long id = Long.valueOf(req.getParameter("id"));
            GroupDao gd = new GroupDao();
            Group g;
            try {
                g = gd.get(id).get();
                req.setAttribute("id", g.getId());
                req.setAttribute("fac", g.getFaculty());
                req.setAttribute("spec", g.getSpecialisation());
                req.setAttribute("num", g.getGroupNumber());
                req.setAttribute("course", g.getCourse());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("group_edit.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupDao gd = new GroupDao();
        Group g = new Group()
                .setFaculty(req.getParameter("faculty"))
                .setSpecialisation(req.getParameter("specialisation"))
                .setGroupNumber(req.getParameter("group_number"))
                .setCourse(Integer.parseInt(req.getParameter("courses")));
        try {
            if (req.getParameter("id")==null || req.getParameter("id").equals("")){
                gd.save(g);
            }else{
                g.setId(Long.valueOf(req.getParameter("id")));
                gd.update(g);
            }
            new GroupCrudServlet().doGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
