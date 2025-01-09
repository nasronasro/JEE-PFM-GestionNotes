package dao;

import otherTypes.Importance;

public class Matiere {
	private String id_matiere;
	private String label;
	private Importance importance;
	private Proffesseur proffesseur;
	
	public Matiere(String id,String lbl,Importance imp, Proffesseur prof) {
		id_matiere = id;
		label = lbl;
		importance = imp;
		proffesseur = prof;
	}
	public Matiere(String id,String lbl,int imp, Proffesseur prof) {
		id_matiere = id;
		label = lbl;
		importance = getImportance(imp);
		proffesseur = prof;
	}

	public String getId_matiere() {
		return id_matiere;
	}
	public void setId_matiere(String id_matiere) {
		this.id_matiere = id_matiere;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Importance getImportance() {
		return importance;
	}
	public Importance getImportance(int impInt) {
		switch (impInt) {
			case 3: importance = Importance.Low;break;
			case 2: importance = Importance.Medium;break;
			case 1: importance = Importance.High;break;
		}
		return importance;
	}
	public int getImportance(Importance importance) {
		if (importance == Importance.Low) 
			return 3;
		else if(importance == Importance.Medium)
			return 2;
		else if(importance == Importance.High)
			return 1;
		else
			return 0;
	}
	public void setImportance(Importance importance) {
		this.importance = importance;
	}
	public Proffesseur getProffesseur() {
		return proffesseur;
	}
	public void setProffesseur(Proffesseur proffesseur) {
		this.proffesseur = proffesseur;
	}
	
}
