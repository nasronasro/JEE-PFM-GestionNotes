package controllers.Professeur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.ProffesseurDao;

@WebServlet("/delete-professeur")
public class DeleteProfesseur extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProffesseurDao profDao;
    public DeleteProfesseur() throws ClassNotFoundException, SQLException {
        super();
       profDao = new ProffesseurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id_professeur") == null) {
			response.sendRedirect("list-professeur");	
			return;
		}
		
		String id = request.getParameter("id_professeur");
		try {
			if(!profDao.VerifierProfesseur(id)) {
				response.sendRedirect("list-professeur");	
				return;
			}
			profDao.DeleteProfesseur(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("list-professeur");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
