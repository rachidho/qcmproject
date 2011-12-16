/**
 * 
 */
package fr.norsys.formation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.norsys.formation.dao.IDAOAdresse;
import fr.norsys.formation.dto.DtoAdresse;
import fr.norsys.formation.dto.DtoUtilisateur;

/**
 * @author technomaker09
 *
 */
public class DAOAdresseImpl implements IDAOAdresse {
	private String findAllAdresseQuery 				= "SELECT ID_ADRESSE, PAYS, VILLE, CODE_POSTAL, RUE, NUMERO FROM ADRESSE ";
	private String byUtilisateurQuery				= "WHERE ID_UTILISATEUR= ?";
	private String byIdQuery 						= "WHERE ID_ADRESSE=?";
	private String addAdresseQuery					= "INSERT INTO ADRESSE VALUES(? , ? , ? , ? , ? , ?, ? )";
	private String modifyAdresseQuery				= "UPDATE ADRESSE SET PAYS= ?, VILLE= ?, CODE_POSTAL= ?, RUE= ?, NUMERO= ? WHERE ID_ADRESSE= ?";
	private String deleteAdresseQuery				= "DELETE FROM ADRESSE WHERE ID_ADRESSE= ?";
	private String deleteAdresseByUtilisateurQuery	= "DELETE FROM ADRESSE WHERE ID_UTILISATEUR= ?";
	private Connection conn;

	public DAOAdresseImpl(Connection conn) {
		this.conn = conn;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOAdresse#createAdresse(fr.norsys.formation.dto.DtoAdresse, fr.norsys.formation.dto.DtoUtilisateur)
	 */
	public int createAdresse(DtoAdresse a, int idUtilisateur)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(addAdresseQuery);
		pstmt.setInt(1, a.getIdAdresse());
		pstmt.setString(2, a.getPays());
		pstmt.setString(3, a.getVille());
		pstmt.setInt(4, a.getCodePostal());
		pstmt.setString(5, a.getRue());
		pstmt.setInt(6, a.getNumero());
		pstmt.setInt(7, idUtilisateur);
		int maj = pstmt.executeUpdate();
		pstmt.close();
		return maj;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOAdresse#findAdresseById(long)
	 */
	public ResultSet findAdresseById(int id) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(findAllAdresseQuery + byIdQuery);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		pstmt.close();
		return rs;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOAdresse#findAdressesByUtilisateur(fr.norsys.formation.dto.DtoUtilisateur)
	 */
	public ResultSet findAdressesByUtilisateur(DtoUtilisateur u)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(findAllAdresseQuery + byUtilisateurQuery);
		pstmt.setInt(1, u.getIdUtilisateur());
		ResultSet rs = pstmt.executeQuery();
		pstmt.close();
		return rs;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOAdresse#findAllAdresse()
	 */
	public ResultSet findAllAdresse() throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(findAllAdresseQuery);
		ResultSet rs = pstmt.executeQuery();
		pstmt.close();
		return rs;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOAdresse#updateAdresse(long)
	 */
	public int updateAdresse(DtoAdresse a) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(modifyAdresseQuery);
		pstmt.setInt(6, a.getIdAdresse());
		pstmt.setString(1, a.getPays());
		pstmt.setString(2, a.getVille());
		pstmt.setInt(3, a.getCodePostal());
		pstmt.setString(4, a.getRue());
		pstmt.setInt(5, a.getNumero());
		int maj = pstmt.executeUpdate();
		pstmt.close();
		return maj;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOAdresse#deleteAdresse(long)
	 */
	public int deleteAdresse(int id) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(deleteAdresseQuery);
		pstmt.setInt(1, id);
		int maj = pstmt.executeUpdate();
		pstmt.close();
		return maj;
	}

	public int deleteAdresseByUtilisateur(int id) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(deleteAdresseByUtilisateurQuery);
		pstmt.setInt(1, id);
		int maj = pstmt.executeUpdate();
		pstmt.close();
		return maj;
	}

}
