package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProffesseurDao {
	Connection cnx ;
	public ProffesseurDao() throws ClassNotFoundException, SQLException  {
		cnx = ConnectingLink.Connecter();
	}
	public boolean VerifierProfesseur(String id) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement("SELECT count(Id_proffesseur) FROM proffesseurs where Id_proffesseur like ?");
		ps.setString(1, id);
		ResultSet resultSet = ps.executeQuery();
		while(resultSet.next()) {
			if(resultSet.getInt(1) == 1)
				return true;
		}
		return false;
	}
	public List<Proffesseur> GetAllProffessors(Utilisateur user) throws SQLException{
		List<Proffesseur> listProfs= new ArrayList<Proffesseur>();
		
		PreparedStatement ps = cnx.prepareStatement("SELECT Id_proffesseur,Nom,Prenom,Email,Telephone "
				+ "FROM Proffesseurs  WHERE id_utilisateur = ? ");
		ps.setString(1, user.getId_utilisateur() );
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String id = rs.getString(1);
			String nom = rs.getString(2);
			String prenom = rs.getString(3);
			String email = rs.getString(4);
			String telephone = rs.getString(5);
			
			listProfs.add(new Proffesseur(id,nom,prenom,email,telephone,user));	
		}
			
		return listProfs;
	}
	
	public boolean InsertProfesseur(Proffesseur prof) throws SQLException, ClassNotFoundException
	{
		
		PreparedStatement ps = cnx.prepareStatement("INSERT INTO proffesseurs(`Id_proffesseur`,`nom`,`prenom`,`email`,`telephone`,id_utilisateur) VALUES ( ?, ? , ? , ?, ?,?)");
		ps.setString(1, prof.getId_proffesseur());
		ps.setString(2, prof.getNom());
		ps.setString(3, prof.getPrenom());
		ps.setString(4, prof.getEmail());
		ps.setString(5, prof.getTelephone());
		ps.setString(6, prof.getUser().getId_utilisateur());
		if(ps.executeUpdate() == 1 ) {
			System.out.println("Insertion Done");
			return true;
		}
		return false;
	}
	
	public boolean UpdateProfesseur(Proffesseur prof,String id_user) throws SQLException,ClassNotFoundException {

		PreparedStatement ps = cnx.prepareStatement("UPDATE proffesseurs SET nom = ? , prenom = ?, email = ?,telephone = ? where Id_proffesseur = ? and id_utilisateur = ?");
		ps.setString(1, prof.getNom());
		ps.setString(2, prof.getPrenom());
		ps.setString(3, prof.getEmail());
		ps.setString(4, prof.getTelephone());
		ps.setString(5, prof.getId_proffesseur());
		ps.setString(5, prof.getUser().getId_utilisateur());
		if(ps.executeUpdate() == 1 ) {
			System.out.println("Modification Done");
			return true;
		}
		return false;
	}
	public Proffesseur GetProfessor(String id_searched,Utilisateur user) throws SQLException{

		PreparedStatement ps = cnx.prepareStatement("SELECT Id_proffesseur,Nom,Prenom,Email,Telephone FROM Proffesseurs where id_proffesseur = ? and id_utilisateur = ?");
		ps.setString(1, id_searched);
		ps.setString(2, user.getId_utilisateur());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String id = rs.getString(1);
			String nom = rs.getString(2);
			String prenom = rs.getString(3);
			String email = rs.getString(4);
			String telephone = rs.getString(5);
			
			return new Proffesseur(id,nom,prenom,email,telephone,user);	
		}
		return null;
	}
	public int GetCurrentProfesseur() throws SQLException {
		PreparedStatement ps = cnx.prepareStatement("SELECT max(Id_auto) from profAI");
		ResultSet resultSet = ps.executeQuery();
		int res = 0;
		while(resultSet.next()) {
			res = resultSet.getInt("max(id_auto)");	
		}
		
		PreparedStatement psInsertAI = cnx.prepareStatement("insert into profAI value()");
		psInsertAI.executeUpdate();
		return res;
	}
}
