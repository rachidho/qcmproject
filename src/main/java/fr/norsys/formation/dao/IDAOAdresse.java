/**
 * 
 */
package fr.norsys.formation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.norsys.formation.dto.DtoAdresse;
import fr.norsys.formation.dto.DtoUtilisateur;

/**
 * @author technomaker09
 *
 */


// ca marche rachid
public interface IDAOAdresse {
	public int createAdresse(DtoAdresse a, int idUtilisateur) throws SQLException;
	public ResultSet findAdresseById(int id) throws SQLException;
	public ResultSet findAdressesByUtilisateur(DtoUtilisateur u) throws SQLException;
	public ResultSet findAllAdresse() throws SQLException;
	public int updateAdresse(DtoAdresse a) throws SQLException;
	public int deleteAdresse(int id) throws SQLException;
	public int deleteAdresseByUtilisateur(int id) throws SQLException;
}
