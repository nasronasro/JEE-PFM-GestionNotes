package controllers.Notes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import dao.NoteDao;

@WebServlet("/delete-note")
public class DeleteNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NoteDao noteDao;
    public DeleteNote() throws ClassNotFoundException, SQLException {
        super();
        noteDao = new NoteDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id_note") == null) {
			response.sendRedirect(request.getContextPath() + "/list-note");	
			return;
		}
		
		String id = request.getParameter("id_note");
		try {
			if(!noteDao.VerifierNote(id)) {
				response.sendRedirect(request.getContextPath() + "/list-note");	
				return;
			}
			noteDao.DeleteNote(id);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/list-note");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
