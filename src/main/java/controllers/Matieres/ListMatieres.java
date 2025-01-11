package controllers.Matieres;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.Matiere;
import dao.MatiereDao;
import dao.Note;
import dao.NoteDao;
import dao.Utilisateur;
import dao.UtilisateurDao;

@WebServlet("/list-matiere")
public class ListMatieres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao userDao;
	private MatiereDao matDao;
	private List<Matiere> matieres;
    public ListMatieres() throws ClassNotFoundException, SQLException {
        super();
        userDao = new UtilisateurDao();
        matDao = new MatiereDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			String username = request.getSession().getAttribute("username").toString();
			Utilisateur user = userDao.GetUserByUsername(username);
			matieres = matDao.GetAllMatieres(user);			
			request.setAttribute("MatiereList", matieres);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Matieres/list-matiere.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
