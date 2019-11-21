package servlet.auditory;

import dao.AuditoryDao;
import model.Auditory;
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

@WebServlet(urlPatterns = "/auditory_change")
public class AuditoryAddOrEditItem extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FindForGroupsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id")==null) {
            req.setAttribute("id", "");
            req.setAttribute("building", "");
            req.setAttribute("floor", "");
            req.setAttribute("aud", "");
        }else{
            Long id = Long.valueOf(req.getParameter("id"));
            AuditoryDao ad = new AuditoryDao();
            Auditory a;
            try {
                a = ad.get(id).get();
                req.setAttribute("id", a.getId());
                req.setAttribute("building", a.getBuildingNumber());
                req.setAttribute("floor", a.getFloor());
                req.setAttribute("aud", a.getAuditoryNumber());
                req.setAttribute("error", null);
            } catch (SQLException e) {
                req.setAttribute("error", e.getMessage());
                LOGGER.error(e);
//                e.printStackTrace();
            }
        }

        req.getRequestDispatcher("auditory_edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuditoryDao ad = new AuditoryDao();
        Auditory a = new Auditory()
                .setBuildingNumber(Integer.parseInt(req.getParameter("building")))
                .setFloor(Integer.parseInt(req.getParameter("floor")))
                .setAuditoryNumber(req.getParameter("aud"));
        try {
            if (req.getParameter("id")==null || req.getParameter("id").equals("")){
                ad.save(a);
            }else{
                a.setId(Long.valueOf(req.getParameter("id")));
                ad.update(a);
            }
            new AuditoryCrudServlet().doGet(req, resp);
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
            LOGGER.error(e);
//            e.printStackTrace();
        }
    }
}
