package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {
	Connection cnx;
	UtilisateurDao userDao;
	public NoteDao() throws ClassNotFoundException, SQLException {	
		cnx = ConnectingLink.Connecter();
		userDao = new UtilisateurDao();
	}
	
	public boolean VerifierNote(String id) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement("Select Count(Id_Note) from notes where Id_Note like ?");
		ps.setString(1, id);
		ResultSet resultSet = ps.executeQuery();
		while(resultSet.next()) {
			if(resultSet.getInt(1) == 1)
				return true;
		}
		return false;
	}
	public List<Note> GetListNotes(Utilisateur user) throws SQLException, ClassNotFoundException{
		PreparedStatement ps= cnx.prepareStatement("Select Id_Note,Title,Content,Id_matiere from notes where id_utilisateur = ?");
		ps.setString(1, user.getId_utilisateur());
		ResultSet resultSet = ps.executeQuery();
		List<Note> notes = new ArrayList<Note>();
		while(resultSet.next()) {
			String noteId = resultSet.getString("Id_Note");
			String titre = resultSet.getString("Title");
			String content = resultSet.getString("Content");
			String Id_matiere = resultSet.getString("Id_matiere");
			Note note = new Note(noteId,titre,content,Id_matiere);
			notes.add(note);
		}
		return notes;
	}
	public List<Note> GetListNotesByMatiere(Utilisateur user,String id_matiere) throws SQLException, ClassNotFoundException{
		PreparedStatement ps= cnx.prepareStatement("Select Id_Note,Title,Content,Id_matiere from notes where id_utilisateur = ? and id_matiere = ?");
		ps.setString(1, user.getId_utilisateur());
		ps.setString(2, id_matiere);
		ResultSet resultSet = ps.executeQuery();
		List<Note> notes = new ArrayList<Note>();
		while(resultSet.next()) {
			String noteId = resultSet.getString("Id_Note");
			String titre = resultSet.getString("Title");
			String content = resultSet.getString("Content");
			String Id_matiere = resultSet.getString("Id_matiere");
			Note note = new Note(noteId,titre,content,Id_matiere);
			notes.add(note);
		}
		return notes;
	}
	public boolean InsertNote(Note note, String user) throws SQLException, ClassNotFoundException
	{
		String userId = userDao.GetCurrentUser(user);
	
		if(userId == null) return false;
		
		PreparedStatement ps = cnx.prepareStatement("INSERT INTO notes(`Id_Note`,`Title`,`Content`,`Id_matiere`,`Id_Utilisateur`) VALUES ( ?, ? , ? , ?, ? )");
		ps.setString(1, note.getId_note());
		ps.setString(2, note.getTitre());
		ps.setString(3, note.getContenue());
		ps.setString(4, note.getId_matiere());
		ps.setString(5, userId);
		if(ps.executeUpdate() == 1 ) {
			System.out.println("Insertion Done");
			return true;
		}
		return false;
	}
	
	public boolean UpdateNote(Note note) throws SQLException,ClassNotFoundException {

		PreparedStatement ps = cnx.prepareStatement("UPDATE notes SET Title = ? , Content = ?, Id_matiere = ? where Id_Note = ?");
		ps.setString(1, note.getTitre());
		ps.setString(2, note.getContenue());
		ps.setString(3, note.getId_matiere());
		ps.setString(4, note.getId_note());
		if(ps.executeUpdate() == 1 ) {
			System.out.println("Modification Done");
			return true;
		}
		return false;
	}
	
	public boolean DeleteNote(String Id_note) throws SQLException, ClassNotFoundException {
		
		PreparedStatement ps = cnx.prepareStatement("Delete from notes where  Id_Note = ?");
		ps.setString(1, Id_note);
		if(ps.executeUpdate() == 1 ) {
			System.out.println("Modification Done");
			return true;
		}
		return false;
	}
	
	public Note Search(Note n) throws SQLException{
		List<Note> notes = new ArrayList<Note>(); 
		PreparedStatement ps = cnx.prepareStatement("SELECT Id_Note,Title,Content,Id_matiere FROM notes");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String id_note = rs.getString("Id_Note");
			String title = rs.getString("Title");
			String content = rs.getString("Content");
			String id_matiere = rs.getString("Id_matiere");
			Note note = new Note(id_note,title,content,id_matiere); 
			notes.add(note);	
		}
		for(Note note : notes) {
			if(note.getId_note() == n.getId_note()) {
				return note;
			}
		}
		return null;
	}
	public int CurrentIncrementValue() throws SQLException {
		PreparedStatement psUser = cnx.prepareStatement("Select Max(id_auto) from noteAI");
		ResultSet resultSet = psUser.executeQuery();
		int res = 0;
		while(resultSet.next()) {
			res = resultSet.getInt("Max(id_auto)");	
		}
		
		PreparedStatement psInsertAI = cnx.prepareStatement("insert into noteAI value()");
		psInsertAI.executeUpdate();
		return res;
	}

	public Note getNote(String id) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement("SELECT Id_Note,Title,Content,Id_matiere FROM notes where Id_Note = ?");
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String id_note = rs.getString("Id_Note");
			String title = rs.getString("Title");
			String content = rs.getString("Content");
			String id_matiere = rs.getString("Id_matiere");
			return new Note(id_note,title,content,id_matiere); 	
		}
		return null;
	}
}
