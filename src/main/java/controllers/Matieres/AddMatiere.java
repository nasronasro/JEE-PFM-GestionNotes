package controllers.Matieres;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import otherTypes.Importance;
import services.ChifrageService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.Matiere;
import dao.MatiereDao;
import dao.Proffesseur;
import dao.ProffesseurDao;
import dao.Utilisateur;
import dao.UtilisateurDao;

@WebServlet("/add-matiere")
public class AddMatiere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChifrageService chifreService;
	private List<Proffesseur> listProfs;
	private ProffesseurDao profDao;
	private UtilisateurDao userDao;
	private MatiereDao matDao;
    public AddMatiere() throws ClassNotFoundException, SQLException {
        super();
        chifreService = new ChifrageService();
        profDao = new ProffesseurDao();
		matDao = new MatiereDao();
		userDao = new UtilisateurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProffesseurDao profDao;
		try {
			
			profDao = new ProffesseurDao();
			String username = request.getSession().getAttribute("username").toString();
			Utilisateur user = userDao.GetUserByUsername(username);
			listProfs = profDao.GetAllProffessors(user);
			request.setAttribute("ListProfs", listProfs);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Matieres/add-matiere.jsp");
		dispatcher.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = "";

		String label = request.getParameter("label");
		int importanceInt = Integer.parseInt( request.getParameter("importance"));
		String profStr = request.getParameter("proffesseur");
		boolean hasError = false;

		// Validation for 'label'
		if (label == null || label.trim().isEmpty()) {
		    request.setAttribute("labelError", "Le champ 'label' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (label.length() > 50) {
		    request.setAttribute("labelError", "Le champ 'label' ne peut pas contenir plus de 50 caractères.");
		}

	    if (importanceInt < 1 || importanceInt > 3) {
	        request.setAttribute("importanceError", "Le champ 'importance' doit être low, medium ou High.");
	        hasError = true;
	    }
		// Validation for 'proffesseur'
		 if (profStr.length() > 61) {
		    request.setAttribute("proffesseurError", "Le champ 'professeur' ne peut pas contenir plus de 60 caractères.");
		    hasError = true;
		}

		if (hasError) {
			doGet(request,response);
		    return;
		}
		Importance importance = Importance.fromCode(importanceInt);
	
		try {
			Proffesseur professeur = null;
			String username = request.getSession().getAttribute("username").toString();
			Utilisateur user = userDao.GetUserByUsername(username);
			if(profDao.GetProfessor(profStr,user) != null) {
				 professeur= profDao.GetProfessor(profStr,user);				
			}
			id = chifreService.ChifreId(matDao.CurrentIncrementValue(), "mate");
			Matiere mat = new Matiere(id,label,importance,professeur,user);

			if(matDao.insertMatiere(mat)) {
				System.out.println("matiere added!");
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		} 
		
		response.sendRedirect(request.getContextPath() + "/list-matiere");
	}
	
}
