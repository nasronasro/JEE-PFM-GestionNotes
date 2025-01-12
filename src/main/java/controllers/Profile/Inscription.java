package controllers.Profile;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.ChifrageService;

import java.io.IOException;
import java.sql.SQLException;

import dao.Utilisateur;
import dao.UtilisateurDao;

@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurDao userDao;
	ChifrageService chifrage;
    public Inscription() throws ClassNotFoundException, SQLException {
        super();  
        userDao = new UtilisateurDao();
        chifrage = new ChifrageService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Profile/inscription.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean hasError = false;

		String mdp = request.getParameter("mdp");
		String confirmMdp = request.getParameter("cmdp");
		// Validation for 'mdp'
		if (mdp == null || mdp.trim().isEmpty()) {
		    request.setAttribute("mdpError", "Le champ 'mot de passe' est requis et ne peut pas être vide.");
		    hasError = true;
		} 

		// Validation for 'cmdp'
		if (confirmMdp == null || confirmMdp.trim().isEmpty()) {
		    request.setAttribute("cmdpError", "Le champ 'confirmation du mot de passe' est requis.");
		    hasError = true;
		} else if (!confirmMdp.equals(mdp)) {
		    request.setAttribute("cmdpError", "Les mots de passe ne correspondent pas.");
		    hasError = true;
		}

		int id = 0;
		try {
			id = userDao.CurrentIncrementValue();
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		if(userDao == null) return;

		String username = request.getParameter("username");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String addresse = request.getParameter("addresse");
		String phone = request.getParameter("telephone");

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

		// Validation for 'nom'
		if (nom == null || nom.trim().isEmpty()) {
		    request.setAttribute("nomError", "Le champ 'nom' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (nom.length() > 50) {
		    request.setAttribute("nomError", "Le champ 'nom' ne peut pas contenir plus de 50 caractères.");
		} else if (!nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s-]+$")) {
		    request.setAttribute("nomError", "Le champ 'nom' ne peut contenir que des lettres.");
		    hasError = true;
		}

		// Validation for 'prenom'
		if (prenom == null || prenom.trim().isEmpty()) {
		    request.setAttribute("prenomError", "Le champ 'prénom' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (prenom.length() > 50) {
		    request.setAttribute("prenomError", "Le champ 'prénom' ne peut pas contenir plus de 50 caractères.");
		} else if (!prenom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s-]+$")) {
		    request.setAttribute("prenomError", "Le champ 'prénom' ne peut contenir que des lettres.");
		    hasError = true;
		}

		// Validation for 'email'
		if (email == null || email.trim().isEmpty()) {
		    request.setAttribute("emailError", "Le champ 'email' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
		    request.setAttribute("emailError", "Veuillez fournir une adresse email valide.");
		    hasError = true;
		}

		// Validation for 'addresse'
		if (addresse == null || addresse.trim().isEmpty()) {
		    request.setAttribute("addresseError", "Le champ 'adresse' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (addresse.length() > 100) {
		    request.setAttribute("addresseError", "Le champ 'adresse' ne peut pas contenir plus de 100 caractères.");
		}

		// Validation for 'telephone'
		if (phone == null || phone.trim().isEmpty()) {
		    request.setAttribute("telephoneError", "Le champ 'téléphone' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (!phone.matches("^\\+?[0-9]{10,15}$")) {
		    request.setAttribute("telephoneError", "Veuillez fournir un numéro de téléphone valide (10 à 15 chiffres).");
		    hasError = true;
		}

		// If there are errors, forward back to the form page
		if (hasError) {
		    doGet(request, response);
		    return;
		}
		
		Utilisateur user = new Utilisateur(chifrage.ChifreId(id,"user"),username, nom, prenom, email, mdp, addresse, phone);
		user.Display();
		
		try {
			if(userDao.InsererUtilisateur(user)) {
				response.sendRedirect(request.getContextPath() + "/list-note");
				return;
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
