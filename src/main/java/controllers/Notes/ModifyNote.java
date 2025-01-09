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

@WebServlet("/modify-note")
public class ModifyNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NoteDao noteDao;
	MatiereDao matiereDao;
	List<Matiere> listMatieres;
    public ModifyNote() throws ClassNotFoundException, SQLException {
        super();
        noteDao = new NoteDao();
        matiereDao = new MatiereDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(request.getParameter("id_note") == null) {
			response.sendRedirect(request.getContextPath() + "/list-note");	
			return;
		}
		
		String id = request.getParameter("id_note");
		try {
			
			if(!noteDao.VerifierNote(id)) {				
				request.setAttribute("idError", "Le value de champ 'id' n'exist pas.");
				response.sendRedirect(request.getContextPath() + "/list-note?errorMessage=\" + URLEncoder.encode(errorMessage, \"UTF-8\")");
			    return;
			}
			
			listMatieres = matiereDao.GetAllMatieres();
			request.setAttribute("ListMatieres", listMatieres);
			Note note = noteDao.getNote(id);
			request.setAttribute("title", note.getTitre());
			request.setAttribute("content", note.getContenue());
			request.setAttribute("id_matiere", note.getId_matiere());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("id_note", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Notes/modify-note.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id_note");
		String title = request.getParameter("title");
		String content = request.getParameter("content");	
		String id_material = request.getParameter("matiere");
		Note noteModifier = new Note(id,title,content,id_material);
		boolean hasError = false;
		
		
		try {
			if(!noteDao.VerifierNote(id)) {				
				String idError ="Le value de champ 'id' n'exist pas.";
				response.sendRedirect(request.getContextPath() + "/list-note?idError=\" + URLEncoder.encode(idError, \"UTF-8\")");
			    return;
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		if (id == null || id.trim().isEmpty()) {
		    request.setAttribute("idError", "Le champ 'id' est requis et ne peut pas être vide.");
		    hasError = true;
		}else if (id.length() > 10) {
		    request.setAttribute("idErrorSize", "Le champ 'id' ne peut pas contenir plus de 10 caractères.");
		    hasError = true;
		}
		if (title == null || title.trim().isEmpty()) {
		    request.setAttribute("titreError", "Le champ 'titre' est requis et ne peut pas être vide.");
		    hasError = true;
		} else if (title.length() > 50) {
		    request.setAttribute("titreError", "Le champ 'titre' ne peut pas contenir plus de 50 caractères.");
		    hasError = true;
		}

		if (id_material == null || id_material.trim().isEmpty()) {
		    request.setAttribute("matiereError", "Le champ 'matière' est requis et ne peut pas être vide.");
		    hasError = true;
		}

		if (hasError) {
		    doGet(request,response);
		    return;
		}
		
		
		try {
			noteDao.UpdateNote(noteModifier);
		} catch ( SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
		response.sendRedirect(request.getContextPath() + "/list-note");
	}
}