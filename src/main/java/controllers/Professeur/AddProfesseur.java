package controllers.Professeur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ChifrageService;

import java.io.IOException;
import java.sql.SQLException;

import dao.Proffesseur;
import dao.ProffesseurDao;

/**
 * Servlet implementation class AddProfesseur
 */
@WebServlet("/add-professeur")
public class AddProfesseur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProffesseurDao profDao;
	private ChifrageService chifrageService;

    public AddProfesseur() throws ClassNotFoundException, SQLException {
        super();
        profDao = new ProffesseurDao();
        chifrageService = new ChifrageService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Professeurs/add-professeur.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		boolean hasError = false;

		// Validation for 'nom'
		if (nom == null || nom.trim().isEmpty()) {
		    request.setAttribute("nomError", "Le champ 'nom' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (nom.length() > 30) {
		    request.setAttribute("nomError", "Le champ 'nom' ne peut pas contenir plus de 30 caractères.");
		    hasError = true;
		} else if (!nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s-]+$")) {
		    request.setAttribute("nomError", "Le champ 'nom' ne peut contenir que des lettres.");
		    hasError = true;
		}

		// Validation for 'prenom'
		if (prenom == null || prenom.trim().isEmpty()) {
		    request.setAttribute("prenomError", "Le champ 'prénom' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (prenom.length() > 30) {
		    request.setAttribute("prenomError", "Le champ 'prénom' ne peut pas contenir plus de 30 caractères.");
		    hasError = true;
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

		// Validation for 'telephone'
		if (telephone == null || telephone.trim().isEmpty()) {
		    request.setAttribute("telephoneError", "Le champ 'téléphone' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (!telephone.matches("^\\+?[0-9]{10,15}$")) {
		    request.setAttribute("telephoneError", "Veuillez fournir un numéro de téléphone valide.");
		    hasError = true;
		}

		// If there are errors, forward to the form page
		if (hasError) {
		    doGet(request,response);
		    return;
		}
		try {
			String id = chifrageService.ChifreId(profDao.GetCurrentProfesseur(), "prof" );
			Proffesseur prof = new Proffesseur(id,nom,prenom,email,telephone);
			if(profDao.InsertProfesseur(prof)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Professeurs/add-professeur.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
