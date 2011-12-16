/**
 * 
 */
package fr.norsys.formation.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;

/**
 * @author technomaker09
 *
 */
public class ApplicationContext {
	private static final String URL 	= "jdbc:hsqldb:file:data/utilisateur_db";
	private static final String USER	= "TOTO";
	private static final String PWD		= "toto";
	private static JDBCDataSource DATASOURCE;
	private static DataSource getApplicationDataSource() {
		if (DATASOURCE == null) {
			DATASOURCE = new JDBCDataSource();
			DATASOURCE.setUrl(URL);
			DATASOURCE.setUser(USER);
			DATASOURCE.setPassword(PWD);
		}
		return DATASOURCE;
	}


	public static Connection getConnexion() throws SQLException{
		DataSource ds = getApplicationDataSource();
		return ds.getConnection();
	}
}
