/**
 * 
 */
package fr.norsys.formation.dto;

/**
 * @author technomaker09
 *
 */
public class DtoUtilisateur {
	private int idUtilisateur;
	private String nom;
	private String prenom;
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
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

}
