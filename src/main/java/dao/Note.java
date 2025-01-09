package dao;

public class Note {
	private String id_note;
	private String titre;
	private String contenue;
	private String id_matiere;
	public Note(String ttl,String cnt, String id_mat) {
		titre = ttl;
		contenue = cnt;
		setId_matiere(id_mat);
	}
	public Note(String id,String ttl,String cnt, String id_mat) {
		id_note = id;
		titre = ttl;
		contenue = cnt;
		setId_matiere(id_mat);
	}
	// --- getteurs and setteurs ---
	public String getId_note() {
		return id_note;
	}
	public void setId_note(String id_note) {
		this.id_note = id_note;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String title) {
		this.titre = title;
	}
	public String getContenue() {
		return contenue;
	}
	public void setContenue(String contenue) {
		this.contenue = contenue;
	}
	public String getId_matiere() {
		return id_matiere;
	}
	public void setId_matiere(String id_matiere) {
		this.id_matiere = id_matiere;
	}
	
	public void Display() {
		System.out.println(id_note + "\n "+titre + "\n "+ contenue + "\n " + id_matiere );
	}
}
