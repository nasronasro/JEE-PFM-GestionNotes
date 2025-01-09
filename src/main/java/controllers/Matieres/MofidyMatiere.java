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

@WebServlet("/modify-matiere")
public class MofidyMatiere extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	private MatiereDao matDao;
	ProffesseurDao profDao;
	List<Proffesseur> listProfs;
	
    public MofidyMatiere() throws ClassNotFoundException, SQLException {
        super();  
        matDao = new MatiereDao();
        profDao = new ProffesseurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_matiere = request.getParameter("id_matiere");
		System.out.println("matiere : " + id_matiere);
		try {
			if(!matDao.VerifyMatiere(id_matiere)) {
				response.sendRedirect(request.getContextPath() + "/list-matiere");
				return;
			}
			
			listProfs = profDao.GetAllProffessors();
			request.setAttribute("ListProfs", listProfs);
			request.setAttribute("id_matiere", id_matiere);
			
			Matiere mat = matDao.GetMatiere(id_matiere);
			request.setAttribute("label", mat.getLabel());
			request.setAttribute("ImpInt", mat.getImportance(mat.getImportance()));
			request.setAttribute("ProfesseurId", mat.getProffesseur().getId_proffesseur());
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Matieres/modify-matiere.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean hasError = false;

		String id = request.getParameter("id_matiere");
		String label = request.getParameter("label");
		int importanceInt = Integer.parseInt( request.getParameter("importance"));
		String profStr = request.getParameter("proffesseur");

		if (id == null || id.trim().isEmpty()) {
			request.setAttribute("labelError", "Le champ 'id' est requis et ne peut pas être vide.");
			hasError = true;
		}

		// Validation for 'label'
		if (label == null || label.trim().isEmpty()) {
		    request.setAttribute("idError", "Le champ 'label' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (label.length() > 50) {
		    request.setAttribute("labelError", "Le champ 'label' ne peut pas contenir plus de 50 caractères.");
		}

	    if (importanceInt < 1 || importanceInt > 3) {
	        request.setAttribute("importanceError", "Le champ 'importance' doit être low, medium ou High.");
	        hasError = true;
	    }
		// Validation for 'proffesseur'
		if (profStr == null || profStr.trim().isEmpty()) {
		    request.setAttribute("proffesseurError", "Le champ 'professeur' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (profStr.length() > 61) {
		    request.setAttribute("proffesseurError", "Le champ 'professeur' ne peut pas contenir plus de 50 caractères.");
		    hasError = true;
		}

		if (hasError) {
			doGet(request,response);
		    return;
		}
		Importance importance = Importance.fromCode(importanceInt);
		

		try {
			Proffesseur professeur = profDao.GetProfessor(profStr);
			if( professeur == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Matieres/modify-matiere.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			Matiere mat = new Matiere(id,label,importance,professeur);

			if(matDao.UpdatetMatiere(id,mat)) {
				System.out.println("matiere added!");
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/list-matiere");
	}
}