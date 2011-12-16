/**
 * 
 */
package fr.norsys.formation.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.norsys.formation.dao.IDAOAdresse;
import fr.norsys.formation.dao.IDAOUtilisateur;
import fr.norsys.formation.dao.impl.DAOAdresseImpl;
import fr.norsys.formation.dao.impl.DAOUtilisateurImpl;
import fr.norsys.formation.dto.DtoAdresse;
import fr.norsys.formation.dto.DtoUtilisateur;
import fr.norsys.formation.util.ApplicationContext;

/**
 * @author technomaker09
 *
 */
public class UtilisateurService {
	
	public List<DtoUtilisateur> getListUtilisateur() throws SQLException {
		List<DtoUtilisateur> listUtilisateur = new ArrayList<DtoUtilisateur>();
		Connection conn = ApplicationContext.getConnexion();
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurImpl(conn);
		ResultSet resultSet = daoUtilisateur.findAllUtilisateur();
		conn.close();
		while(resultSet.next()){
			DtoUtilisateur utilisateur = new DtoUtilisateur();
			utilisateur.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
			utilisateur.setNom(resultSet.getString("nom"));
			utilisateur.setPrenom(resultSet.getString("prenom"));
			listUtilisateur.add(utilisateur);
		}
		return listUtilisateur;
	}
	
	public int ajouterUtilisateur(DtoUtilisateur u) throws SQLException{
		Connection conn = ApplicationContext.getConnexion();
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurImpl(conn);
		return daoUtilisateur.createUtilisateur(u);
	}
	
	public DtoUtilisateur rechercherUtilisateur(int id) throws SQLException{
		Connection conn = ApplicationContext.getConnexion();
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurImpl(conn);
		DtoUtilisateur utilisateur = new DtoUtilisateur();
		ResultSet r = daoUtilisateur.findUtilisateurById(id);
		while(r.next()){
			utilisateur.setIdUtilisateur(r.getInt(1));
			utilisateur.setNom(r.getString(2));
			utilisateur.setPrenom(r.getString(3));
		}
		return utilisateur;
	}
	
	public int modifierUtilisateur(DtoUtilisateur u) throws SQLException{
		Connection conn = ApplicationContext.getConnexion();
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurImpl(conn);
		return daoUtilisateur.updateUtilisateur(u);
	}
	
	public int supprimerUtilisateur(int id) throws SQLException{
		Connection conn = ApplicationContext.getConnexion();
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurImpl(conn);
		return daoUtilisateur.deleteUtilisateur(id);
	}
	
	public List<DtoAdresse> getListAdresse() throws SQLException {
		List<DtoAdresse> listAdresse = new ArrayList<DtoAdresse>();
		Connection conn = ApplicationContext.getConnexion();
		IDAOAdresse daoAdresse = new DAOAdresseImpl(conn);
		ResultSet resultSet = daoAdresse.findAllAdresse();
		conn.close();
		while(resultSet.next()){
			DtoAdresse adresse = new DtoAdresse();
			adresse.setIdAdresse(resultSet.getInt("id_adresse"));
			adresse.setPays(resultSet.getString("pays"));
			adresse.setVille(resultSet.getString("ville"));
			adresse.setCodePostal(resultSet.getInt("code_postal"));
			adresse.setRue(resultSet.getString("rue"));
			adresse.setNumero(resultSet.getInt("numero"));
			listAdresse.add(adresse);
		}
		return listAdresse;
	}
	
	public int ajouterAdresse(DtoAdresse a, int idUtilisateur) throws SQLException{
		Connection conn = ApplicationContext.getConnexion();
		IDAOAdresse daoAdresse = new DAOAdresseImpl(conn);
		return daoAdresse.createAdresse(a, idUtilisateur);
	}
	
	public int modifierAdresse(DtoAdresse u) throws SQLException{
		Connection conn = ApplicationContext.getConnexion();
		IDAOAdresse daoAdresse = new DAOAdresseImpl(conn);
		return daoAdresse.updateAdresse(u);
	}
	
	public int supprimerAdresse(int id) throws SQLException{
		Connection conn = ApplicationContext.getConnexion();
		IDAOAdresse daoAdresse = new DAOAdresseImpl(conn);
		return daoAdresse.deleteAdresse(id);
	}
	
	public List<DtoAdresse> getListAdresseByUtilisateur(DtoUtilisateur u) throws SQLException {
		List<DtoAdresse> listAdresse = new ArrayList<DtoAdresse>();
		Connection conn = ApplicationContext.getConnexion();
		IDAOAdresse daoAdresse = new DAOAdresseImpl(conn);
		ResultSet resultSet = daoAdresse.findAdressesByUtilisateur(u);
		conn.close();
		while(resultSet.next()){
			DtoAdresse adresse = new DtoAdresse();
			adresse.setIdAdresse(resultSet.getInt("id_adresse"));
			adresse.setPays(resultSet.getString("pays"));
			adresse.setVille(resultSet.getString("ville"));
			adresse.setCodePostal(resultSet.getInt("code_postal"));
			adresse.setRue(resultSet.getString("rue"));
			adresse.setNumero(resultSet.getInt("numero"));
			listAdresse.add(adresse);
		}
		return listAdresse;
	}
	
	public DtoAdresse rechercherAdresse(int id) throws SQLException{
		Connection conn = ApplicationContext.getConnexion();
		IDAOAdresse daoAdresse = new DAOAdresseImpl(conn);
		DtoAdresse adresse = new DtoAdresse();
		ResultSet r = daoAdresse.findAdresseById(id);
		while(r.next()){
			adresse.setIdAdresse(r.getInt(1));
			adresse.setPays(r.getString(2));
			adresse.setVille(r.getString(3));
		}
		return adresse;
	}
	
	public int supprimerAdresseByUtilisateur(int id) throws SQLException{
		Connection conn = ApplicationContext.getConnexion();
		IDAOAdresse daoAdresse = new DAOAdresseImpl(conn);
		return daoAdresse.deleteAdresseByUtilisateur(id);
	}
}
