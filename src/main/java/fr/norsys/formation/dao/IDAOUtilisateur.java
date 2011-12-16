package fr.norsys.formation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.norsys.formation.dto.DtoUtilisateur;

public interface IDAOUtilisateur {
	public int createUtilisateur(DtoUtilisateur u) throws SQLException;
	public ResultSet findUtilisateurById(int id) throws SQLException;
	public ResultSet findAllUtilisateur() throws SQLException;
	public int updateUtilisateur(DtoUtilisateur u) throws SQLException;
	int deleteUtilisateur(int id) throws SQLException;
	
}
