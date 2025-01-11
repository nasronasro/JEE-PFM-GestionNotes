package controllers.Notes;

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

@WebServlet("/list-note")
public class ListNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteDao noteDao;
	private MatiereDao matiereDao;
	private UtilisateurDao userDao;
	
    public ListNote() throws ClassNotFoundException, SQLException {
        super();
        matiereDao = new MatiereDao();
        noteDao = new NoteDao();
        userDao = new UtilisateurDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			List<Note> notes;
			List<Matiere> matieres;
			String currentUser = session.getAttribute("username").toString();
			Utilisateur user = userDao.GetUserByUsername(currentUser);
			
			matieres = matiereDao.GetAllMatieres(user);
			String id_matiere = null;
			
			if(request.getParameter("id_mat") != null)
				id_matiere = request.getParameter("id_mat");
			
			if(id_matiere == null) 
				notes = noteDao.GetListNotes(user);
			else
				notes = noteDao.GetListNotesByMatiere(user, id_matiere);
			
			request.setAttribute("MatiereList", matieres);
			request.setAttribute("NoteList", notes);
			
			if(request.getAttribute("idError") != null) {
				request.setAttribute("idError", request.getAttribute("idError"));
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Notes/list-note.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(); return;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		if(request.getParameter("searchBox-matiere") == null) {
			doGet(request,response);
			return;
		}
		String matiereSearched = request.getParameter("searchBox-matiere");
		String currentUser = request.getSession().getAttribute("username").toString();
		try {
			List<Note> notes;
			List<Matiere> matieres;
			Utilisateur user = userDao.GetUserByUsername(currentUser);
			matieres = matiereDao.GetFiltrerMatieres(user, matiereSearched);
			notes = noteDao.GetListNotes(user);
			request.setAttribute("MatiereList", matieres);
			request.setAttribute("NoteList", notes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Notes/list-note.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {		
			e.printStackTrace();		
		}		
	}
}