package controllers.Notes;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.Matiere;
import dao.MatiereDao;
import dao.Note;
import services.ChifrageService;
import services.NoteService;

import java.util.ArrayList;
import java.util.List;

import dao.NoteDao;
import dao.Utilisateur;
import dao.UtilisateurDao;

@WebServlet("/add-note")
@SuppressWarnings("unused")
public class AddNote extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	private UtilisateurDao userDao;
	private MatiereDao matiereDao;
	private List<Matiere> listMatieres;
	private NoteDao noteDao;
	private NoteService noteService;
	private ChifrageService chifrageService;
	
    public AddNote() throws ClassNotFoundException, SQLException  { 
    	super();
    	userDao = new UtilisateurDao();
    	matiereDao = new MatiereDao();
    	noteService = new NoteService();
    	noteDao = new NoteDao();
    	chifrageService = new ChifrageService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			String username = request.getSession().getAttribute("username").toString();
			Utilisateur user = userDao.GetUserByUsername(username);
			listMatieres = matiereDao.GetAllMatieres(user);
			request.setAttribute("ListMatieres", listMatieres);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Notes/add-note.jsp");
		dispatcher.forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
			
		String currentUser = session.getAttribute("username").toString();	
		if(request.getParameter("titre") == null)
			response.sendRedirect("add-note");
		if(request.getParameter("contenue") == null)
			response.sendRedirect("add-note");
		
		int id = 0;
	
		try {
			id = noteDao.CurrentIncrementValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String label = request.getParameter("titre");
		String contenue = request.getParameter("contenue");
		String idmatiere = request.getParameter("matiere");;
		
		boolean hasError = false;

		if (label == null || label.trim().isEmpty()) {
		    request.setAttribute("titreError", "Le champ 'titre' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (label.length() > 50) {
		    request.setAttribute("titreError", "Le champ 'titre' ne peut pas contenir plus de 50 caractères.");
		    hasError = true;
		}

		if (idmatiere == null || idmatiere.trim().isEmpty()) {
		    request.setAttribute("matiereError", "Le champ 'matière' est requis et ne peut pas être vide.");
		    hasError = true;
		}

		if (hasError) {
		    doGet(request,response);
		    return;
		}
		
		Note note = new Note(chifrageService.ChifreId(id, "note"),label,contenue,idmatiere);
		
		try {
			noteDao.InsertNote(note, currentUser);
		} catch (SQLException | ClassNotFoundException e) {				
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/list-note");
	}
}