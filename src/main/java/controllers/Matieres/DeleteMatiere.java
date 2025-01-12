package controllers.Matieres;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.MatiereDao;
import dao.Utilisateur;
import dao.UtilisateurDao;

/**
 * Servlet implementation class DeleteMatiere
 */
@WebServlet("/delete-matiere")
public class DeleteMatiere extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MatiereDao matDao;
    private UtilisateurDao userDao;
    public DeleteMatiere() throws ClassNotFoundException, SQLException {
        super();
        matDao = new MatiereDao();
        userDao = new UtilisateurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id_matiere") == null) {
			response.sendRedirect("list-matiere");	
			return;
		}
		String username = request.getSession().getAttribute("username").toString();
		String id = request.getParameter("id_matiere");
		try {
			Utilisateur user = userDao.GetUserByUsername(username);
			if(!matDao.VerifyMatiere(id,user)) {
				System.out.println("Delete failed");
				response.sendRedirect("list-matiere");	
				return;
			}
			if(matDao.DeleteMatiere(id,user)) {
				System.out.println("Delete Succeded");				
			}else {
				System.out.println("something seems wrong!");	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("list-matiere");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
