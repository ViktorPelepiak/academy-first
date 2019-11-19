package servlet.subject;

import dao.SubjectDao;
import dao.TeacherDao;
import model.Subject;
import model.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/subject_change")
public class SubjectAddOrEditItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id")==null) {
            req.setAttribute("id", "");
            req.setAttribute("name", "");
        }else{
            Long id = Long.valueOf(req.getParameter("id"));
            SubjectDao sd = new SubjectDao();
            Subject s;
            try {
                s = sd.get(id).get();
                req.setAttribute("id", s.getId());
                req.setAttribute("name", s.getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("subject_edit.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubjectDao sd = new SubjectDao();
        Subject s = new Subject()
                .setName(req.getParameter("name"));
        try {
            if (req.getParameter("id")==null || req.getParameter("id").equals("")){
                sd.save(s);
            }else{
                s.setId(Long.valueOf(req.getParameter("id")));
                sd.update(s);
            }
            new SubjectCrudServlet().doGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
