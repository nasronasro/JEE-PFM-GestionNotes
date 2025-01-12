package controllers.Professeur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.ProffesseurDao;
import dao.Utilisateur;
import dao.UtilisateurDao;

@WebServlet("/list-professeur")
public class ListProfesseur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProffesseurDao profDao;
	private UtilisateurDao userDao;
    public ListProfesseur() throws ClassNotFoundException, SQLException {
        super();
        profDao = new ProffesseurDao();
        userDao = new UtilisateurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {	
			String username = request.getSession().getAttribute("username").toString();
			Utilisateur user = userDao.GetUserByUsername(username);
			request.setAttribute("profList", profDao.GetAllProffessors(user));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Professeurs/list-professeur.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
