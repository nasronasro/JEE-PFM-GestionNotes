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

/**
 * Servlet implementation class ListProfesseur
 */
@WebServlet("/list-professeur")
public class ListProfesseur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProffesseurDao profDao;
    public ListProfesseur() throws ClassNotFoundException, SQLException {
        super();
        profDao = new ProffesseurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {	
			request.setAttribute("profList", profDao.GetAllProffessors());
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
