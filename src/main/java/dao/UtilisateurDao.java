package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDao  {
	Connection cnx;
	public UtilisateurDao() throws ClassNotFoundException, SQLException{	
		cnx = ConnectingLink.Connecter();
	}
	public Utilisateur GetUserByUsername(String username) throws SQLException {
		PreparedStatement psUser = cnx.prepareStatement("Select Id_Utilisateur, Nom, Prenom, Addresse, Phone, Email from utilisateurs where Username = ?");
		psUser.setString(1, username);
	
		ResultSet resultSet = psUser.executeQuery();

		while(resultSet.next()) {
			String Id = resultSet.getString(1);
			String nom = resultSet.getString(2);
			String prenom = resultSet.getString(3);
			String addresse = resultSet.getString(4);
			String phone  = resultSet.getString(5);
			String email = resultSet.getString(6);
			return new Utilisateur(Id,username,nom,prenom,email,null,addresse,phone);
		}
		
		return null;
	}
	
	public boolean InsererUtilisateur(Utilisateur user) throws SQLException{
		PreparedStatement ps = cnx.prepareStatement("INSERT INTO Utilisateurs(Id_Utilisateur, Nom, Prenom, Username, Addresse, Phone, Email, Mot_de_pass) "
				+ "VALUE (?,?,?,?,?,?,?,?)");
		ps.setString(1, user.getId_utilisateur());
		ps.setString(2, user.getNom());
		ps.setString(3, user.getPrenom());
		ps.setString(4, user.getUsername());
		ps.setString(5, user.getAddresse());
		ps.setString(6, user.getPhone());
		ps.setString(7, user.getEmail());
		ps.setString(8, user.getMot_de_pass());
		
		if(ps.executeUpdate() == 1) {
			if (cnx != null) cnx.close();
				System.out.println("Insert user was successful");
			return true;			
		}
		System.out.println("Inser user failed");
		return false;
	}
	
	public boolean VerifierUtilisateur(String username,String pass) throws SQLException {
		PreparedStatement psUser = cnx.prepareStatement("Select Count(Username) from utilisateurs where Username = ? and Mot_de_pass = ?");
		psUser.setString(1, username);
		psUser.setString(2, pass);
	
		ResultSet resultSet = psUser.executeQuery();

		while(resultSet.next()) {
			if(resultSet.getInt("Count(Username)") == 1) {
				return true;
			}
		}
		
		return false;
	}
	public String GetCurrentUser(String user) throws SQLException {
		PreparedStatement psUser = cnx.prepareStatement("Select id_utilisateur from utilisateurs where username = ?");
		psUser.setString(1, user);
		ResultSet resultSet = psUser.executeQuery();
		while(resultSet.next()) {
			return resultSet.getString("id_utilisateur");
		}
		
		return null;
	}
	public int CurrentIncrementValue() throws SQLException {
		PreparedStatement psUser = cnx.prepareStatement("Select Max(id_auto) from utilsateurAI");
		ResultSet resultSet = psUser.executeQuery();
		
		int res = 0;
		while(resultSet.next()) {
			res = resultSet.getInt("Max(id_auto)");	
		}
		
		PreparedStatement psInsertAI = cnx.prepareStatement("insert into utilsateurAI value()");
		int resultSet2 = psInsertAI.executeUpdate();
		return res;
	}
}
