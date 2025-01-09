package controllers.Profile;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import dao.UtilisateurDao;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	private UtilisateurDao userDao;
    public Login() throws ClassNotFoundException, SQLException {
        super();
        userDao = new UtilisateurDao();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Profile/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean hasError = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// Validation for 'username'
		if (username == null || username.trim().isEmpty()) {
		    request.setAttribute("usernameError", "Le champ 'username' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (username.length() > 30) {
		    request.setAttribute("usernameError", "Le champ 'username' ne peut pas contenir plus de 30 caractères.");
		} else if (!username.matches("^[A-Za-z0-9_]+$")) {
		    request.setAttribute("usernameError", "Le champ 'username' ne peut contenir que des caractères alphanumériques et des underscores.");
		    hasError = true;
		}
		
		// Validation for password
		if (password == null || password.trim().isEmpty()) {
		    request.setAttribute("mdpError", "Le champ 'mot de passe' est requis et ne peut pas être vide.");
		    hasError = true;
		}
		
		if(hasError)
		{
			doGet(request,response);
			return;
		}
		try {
			if(userDao.VerifierUtilisateur(username, password)) {
				session.setAttribute("username", username );

				response.sendRedirect(request.getContextPath() + "/list-note");
				return;
			}
			else {
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
	}
}