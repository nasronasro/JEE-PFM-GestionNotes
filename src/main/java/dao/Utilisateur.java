package dao;

public class Utilisateur {
	private String id_utilisateur;
	private String username;
	private String nom;
	private String prenom;
	private String email;
	private String mot_de_pass;
	private String addresse;
	private String phone;
	
	

	public Utilisateur(String id,String username,String nom,String prenom,String email,String mot_de_pass,String addresse,String phone) {
		
		id_utilisateur = id;
		this.username = username;
		this.addresse = addresse;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mot_de_pass = mot_de_pass;
		this.phone = phone;
	}

	// --- getteurs and setteurs ---
	public String getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(String id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getMot_de_pass() {
		return mot_de_pass;
	}
	public void setMot_de_pass(String mot_de_pass) {
		this.mot_de_pass = mot_de_pass;
	}
	public String getAddresse() {
		return addresse;
	}
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void Display() {
		System.out.println(id_utilisateur+"\n"+username+ " \n"+ addresse + " \n "+email+ "\n "+nom+ "\n "+prenom+ "\n "+email+ "\n "+mot_de_pass+ "\n "+phone);
	}
}
