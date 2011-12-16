/**
 * 
 */
package fr.norsys.formation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.norsys.formation.dao.IDAOUtilisateur;
import fr.norsys.formation.dto.DtoUtilisateur;

/**
 * @author technomaker09
 *
 */
public class DAOUtilisateurImpl implements IDAOUtilisateur {
	private String findAllUtilisateurQuery 	= "SELECT ID_UTILISATEUR, NOM, PRENOM FROM UTILISATEUR ";
	private String addUtilisateurQuery		= "INSERT INTO UTILISATEUR VALUES(? , ? , ?)";
	private String byIdQuery 				= "WHERE ID_UTILISATEUR=?";
	private String modifyUtilisateurQuery	= "UPDATE UTILISATEUR SET NOM= ?, PRENOM= ? WHERE ID_UTILISATEUR= ?";
	private String deleteUtilisateurQuery	= "DELETE FROM UTILISATEUR WHERE ID_UTILISATEUR= ?";
	private Connection conn;

	public DAOUtilisateurImpl(Connection conn) {
		this.conn = conn;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOUtilisateur#createUtilisateur(fr.norsys.formation.dto.DtoUtilisateur)
	 */
	public int createUtilisateur(DtoUtilisateur u) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(addUtilisateurQuery);
		pstmt.setInt(1, u.getIdUtilisateur());
		pstmt.setString(2, u.getNom());
		pstmt.setString(3, u.getPrenom());
		int maj = pstmt.executeUpdate();
		pstmt.close();
		return maj;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOUtilisateur#findUtilisateurById(long)
	 */
	public ResultSet findUtilisateurById(int id) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(findAllUtilisateurQuery + byIdQuery);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		pstmt.close();
		return rs;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOUtilisateur#findAllUtilisateur()
	 */
	public ResultSet findAllUtilisateur() throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(findAllUtilisateurQuery);
		ResultSet rs = pstmt.executeQuery();
		pstmt.close();
		return rs;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOUtilisateur#updateUtilisateur(long)
	 */
	public int updateUtilisateur(DtoUtilisateur u) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(modifyUtilisateurQuery);
		pstmt.setInt(3, u.getIdUtilisateur());
		pstmt.setString(1, u.getNom());
		pstmt.setString(2, u.getPrenom());
		int maj = pstmt.executeUpdate();
		pstmt.close();
		return maj;
	}

	/* (non-Javadoc)
	 * @see fr.norsys.formation.dao.IDAOUtilisateur#deleteUtilisateur(long)
	 */
	public int deleteUtilisateur(int id) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = conn.prepareStatement(deleteUtilisateurQuery);
		pstmt.setInt(1, id);
		int maj = pstmt.executeUpdate();
		pstmt.close();
		return maj;
	}

}
