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

import dao.Note;
import dao.NoteDao;

@WebServlet("/list-note")
public class ListNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteDao noteDao;
	private List<Note> notes;
    public ListNote() throws ClassNotFoundException, SQLException {
        super();
        noteDao = new NoteDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String currentUser = session.getAttribute("username").toString();
			
			notes = noteDao.GetListNotes(currentUser);
			
			request.setAttribute("NoteList", notes);
			if(request.getAttribute("idError") != null) {
				request.setAttribute("idError", request.getAttribute("idError"));
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Notes/list-note.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}
}
