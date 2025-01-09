package dao;

public class Proffesseur {
	private String Id_proffesseur;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	
	public Proffesseur(String id) {
		Id_proffesseur = id;
	}
	
	public Proffesseur(String id,String nom, String prenom) {
		this.Id_proffesseur = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Proffesseur(String id,String nom,String prenom,String email, String telephone) {
		Id_proffesseur = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
	}
	public Proffesseur(String nom,String prenom,String email, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
	}
	public String getId_proffesseur() {
		return Id_proffesseur;
	}
	public void setId_proffesseur(String id_proffesseur) {
		Id_proffesseur = id_proffesseur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
