package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import otherTypes.Importance;
public class MatiereDao {
	Connection cnx ;
	public MatiereDao() throws ClassNotFoundException, SQLException  {
		cnx = ConnectingLink.Connecter();
	}
	public List<Matiere> GetAllMatieres() throws SQLException{
		List<Matiere> matieres= new ArrayList<Matiere>();
		PreparedStatement ps = cnx.prepareStatement("SELECT m.Id_matiere, m.Label, m.Importance, p.id_proffesseur, p.nom, p.prenom FROM matiere m Join proffesseurs p on p.id_Proffesseur = m.id_Proffesseur");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String id = rs.getString(1);
			String label = rs.getString(2);
			int impInt = rs.getInt(3);
			String profId = rs.getString(4);
			String profNom = rs.getString(5);
			String profPrenom = rs.getString(6);
			matieres.add(new Matiere(id,label,impInt,new Proffesseur(profId,profNom,profPrenom)));	
		}
		return matieres;
	}
	public boolean VerifyMatiere(String id) throws SQLException {
		
		PreparedStatement ps = cnx.prepareStatement("SELECT count(Id_matiere) FROM matiere where Id_matiere = ?");
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			if(rs.getInt(1) == 1)
				return true;
		}
		return false;
	}
	public boolean insertMatiere(Matiere matiere) throws SQLException {
        String query = "INSERT INTO Matiere (Id_matiere, Label, Importance, Id_proffesseur) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(query);

        ps.setString(1, matiere.getId_matiere());
        ps.setString(2, matiere.getLabel());
        ps.setInt(3, matiere.getImportance(matiere.getImportance()));
        if(matiere.getProffesseur() != null)
        	ps.setString(4, matiere.getProffesseur().getId_proffesseur());
        else
        	ps.setString(4, "null");

        if (ps.executeUpdate() == 1) {
            return true;
        }
        return false;
	}
	public boolean UpdatetMatiere(String idmodifier,Matiere matiere) throws SQLException {
        String queryUpdate = "UPDATE matiere SET Label = ? , Importance = ?, Id_proffesseur = ? where Id_matiere = ?";
        PreparedStatement ps = cnx.prepareStatement(queryUpdate);

        ps.setString(1, matiere.getLabel());
        ps.setInt(2, matiere.getImportance(matiere.getImportance()));
        ps.setString(3, matiere.getProffesseur().getId_proffesseur());
        ps.setString(4, matiere.getId_matiere());

        if (ps.executeUpdate() == 1) {
            return true;
        }
        return false;
	}
	 public int CurrentIncrementValue() throws SQLException {
			PreparedStatement psUser = cnx.prepareStatement("Select Max(id_auto) from matiereAI");
			ResultSet resultSet = psUser.executeQuery();
			int res = 0;
			while(resultSet.next()) {
				res = resultSet.getInt("Max(id_auto)");	
			}
			
			PreparedStatement psInsertAI = cnx.prepareStatement("insert into matiereAI value()");
			psInsertAI.executeUpdate();
			return res;
		}
	public Matiere GetMatiere(String id_matiere) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement("SELECT Id_matiere, Label,Importance, Id_proffesseur FROM matiere where id_matiere = ?");
		ps.setString(1, id_matiere);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String id = rs.getString(1);
			String label = rs.getString(2);
			int impInt = rs.getInt(3);
			String idProf = rs.getString(4);
			return new Matiere(id,label,impInt,new Proffesseur(idProf));
		}
		return null;
	}
}