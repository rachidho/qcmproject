/**
 * 
 */
package fr.norsys.formation.dto;

/**
 * @author technomaker09
 *
 */
public class DtoAdresse {
	private int idAdresse;
	private String pays;
	private String ville;
	private int codePostal;
	private String rue;
	private int numero;
	private DtoUtilisateur utilisateur;
	
	public int getIdAdresse() {
		return idAdresse;
	}
	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public DtoUtilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(DtoUtilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
