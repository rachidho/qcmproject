/**
 * 
 */
package fr.norsys.formation.test;

import java.sql.SQLException;
import java.util.List;

import fr.norsys.formation.dto.DtoAdresse;
import fr.norsys.formation.dto.DtoUtilisateur;
import fr.norsys.formation.service.UtilisateurService;

/**
 * @author technomaker09
 * 
 */
public class UtilisateurServiceTest {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			listerUtilisateur();
			// ajouterUtilisateur();
			// modifierUtilisateur();
			// supprimerUtilisateur();
//			 listerUtilisateur();
			// ajouterAdresse();
//			modifierAdresse();
//			supprimerAdresse();
			listerAdresse();
//			listerAdresseParUtilisateur();
//			rechercherUtilisateur();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listerUtilisateur() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		for (DtoUtilisateur u : us.getListUtilisateur()) {
			System.out.println("id : " + u.getIdUtilisateur() + " ,nom : "
					+ u.getNom() + " ,prenom : " + u.getPrenom());
		}
	}

	private static void ajouterUtilisateur() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		DtoUtilisateur utilisateur = new DtoUtilisateur();
		utilisateur.setIdUtilisateur(5);
		utilisateur.setNom("nom 5");
		utilisateur.setPrenom("prenom 5");
		if (1 == us.ajouterUtilisateur(utilisateur)) {
			System.out.println("utilisateur ajouté");
		} else {
			System.out.println("utilisateur non ajouté");
		}
	}

	private static void rechercherUtilisateur() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		DtoUtilisateur utilisateur = us.rechercherUtilisateur(10);
		if (0 != utilisateur.getIdUtilisateur()) {
			System.out.println("l'utilisateur est de : ");
			System.out.println("id : " + utilisateur.getIdUtilisateur());
			System.out.println("nom : " + utilisateur.getNom());
			System.out.println("prenom : " + utilisateur.getPrenom());
		} else {
			System.out.println("utilisateur non trouvé");
		}
	}

	private static void modifierUtilisateur() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		DtoUtilisateur utilisateur = new DtoUtilisateur();
		utilisateur.setIdUtilisateur(5);
		utilisateur.setNom("nom 4");
		utilisateur.setPrenom("prenom 4");
		if (1 == us.modifierUtilisateur(utilisateur)) {
			System.out.println("utilisateur modifié");
		} else {
			System.out.println("utilisateur non modifié");
		}
	}

	private static void supprimerUtilisateur() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		if (1 == us.supprimerUtilisateur(5)) {
			System.out.println("utilisateur supprimé");
		} else {
			System.out.println("utilisateur non supprimé");
		}
	}

	private static void listerAdresse() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		for (DtoAdresse a : us.getListAdresse()) {
			System.out.println("id : " + a.getIdAdresse() + "pays : "
					+ a.getPays() + " ,ville : " + a.getVille()
					+ " ,code postal : " + a.getCodePostal() + " ,rue : "
					+ a.getRue() + " ,numero : " + a.getNumero());
		}
	}

	private static void ajouterAdresse() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		DtoAdresse adresse = new DtoAdresse();
		adresse.setIdAdresse(5);
		adresse.setPays("pays 5");
		adresse.setVille("ville 5");
		adresse.setCodePostal(5000);
		adresse.setRue("rue 5");
		adresse.setNumero(50);
		if (1 == us.ajouterAdresse(adresse, 1)) {
			System.out.println("adresse ajouté");
		} else {
			System.out.println("adresse non ajouté");
		}
	}

	private static void modifierAdresse() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		DtoAdresse adresse = new DtoAdresse();
		adresse.setIdAdresse(5);
		adresse.setPays("pays 5x");
		adresse.setVille("ville 5x");
		adresse.setCodePostal(5000);
		adresse.setRue("rue 5x");
		adresse.setNumero(50);
		if (1 == us.modifierAdresse(adresse)) {
			System.out.println("adresse modifié");
		} else {
			System.out.println("adresse non modifié");
		}
	}

	private static void supprimerAdresse() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		if (1 == us.supprimerAdresse(5)) {
			System.out.println("adresse supprimé");
		} else {
			System.out.println("adresse non supprimé");
		}
	}

	private static void listerAdresseParUtilisateur() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		DtoUtilisateur utilisateur = new DtoUtilisateur();
		utilisateur.setIdUtilisateur(1);
		List<DtoAdresse> liste = us.getListAdresseByUtilisateur(utilisateur);
		if(0 != liste.size()){
			for (DtoAdresse a : liste) {
			System.out.println("id : " + a.getIdAdresse() + "pays : " + a.getPays() + " ,ville : " + a.getVille()
					+ " ,code postal : " + a.getCodePostal()+ " ,rue : " + a.getRue()+ " ,numero : " + a.getNumero());
			}
		}else {
			System.out.println("pas d'adresse pour cet utilisateur");
		}
	}
	private static void rechercherAdresse() throws SQLException {
		UtilisateurService us = new UtilisateurService();
		DtoAdresse adresse = us.rechercherAdresse(10);
		if (0 != adresse.getIdAdresse()) {
			System.out.println("l'utilisateur est de : ");
			System.out.println("id : " + adresse.getIdAdresse());
			System.out.println("pays : " + adresse.getPays());
			System.out.println("ville : " + adresse.getVille());
		} else {
			System.out.println("adresse non trouvé");
		}
	}
	private static void supprimerAdrsseByUtilisateur() throws SQLException{
		UtilisateurService us = new UtilisateurService();
		int maj = us.supprimerAdresseByUtilisateur(10);
	}
}
