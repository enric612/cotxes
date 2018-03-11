/**
 * 
 */
package cotxes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cotxes.excepcions.CotxeJaExisteixException;
import cotxes.excepcions.CotxeNoEliminatException;
import cotxes.excepcions.CotxeNoExisteixException;
import cotxes.models.Cotxe;

/**
 * @author ecliment
 *
 */
public class CotxeDAOHSQLDB implements CotxeDAO {

	// JDBC especific, esta zona variara si volem fer una conexió a mongodb per
	// exemple.
	// No estic gastant SpringData pero... mos apanyarem.
	// Podria canviar la BD modificant els fitxers pero com en la tasca poses que no
	// els toquem, els traduirem en codi ;)

	private final String fontURL = "jdbc:hsqldb:hsql://localhost/coches";
	private Connection conexio;

	// No tenim injecció de dependencies ni gestio de singletons pero mos apanyarem
	// aixina.
	private static CotxeDAOHSQLDB manager;

	public static CotxeDAOHSQLDB getCurrentInstance() {
		if (manager == null)
			manager = new CotxeDAOHSQLDB();
		return manager;
	}

	public CotxeDAOHSQLDB() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conexio = DriverManager.getConnection(fontURL);
			conexio.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cotxes.dao.CotxeDAO#insertCotxe(cotxes.models.Cotxe)
	 */
	@Override
	public void insertCotxe(Cotxe c) throws CotxeJaExisteixException {
		String insertarCotxeQuery = "INSERT INTO coches VALUES(?, ?, ?, ?, ?)";

		try {
			PreparedStatement st = conexio.prepareStatement(insertarCotxeQuery);
			st.setString(1, c.getMatricula());
			st.setString(2, c.getMarca());
			st.setString(3, c.getModel());
			st.setString(4, c.getColor());
			st.setInt(5, c.getNum_portes());
			st.executeUpdate();
			st.close();

		} catch (SQLException e) {
			throw new CotxeJaExisteixException();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cotxes.dao.CotxeDAO#getCotxe(cotxes.models.Cotxe)
	 */
	@Override
	public Cotxe getCotxeByMatricula(String matricula) throws CotxeNoExisteixException{
		Cotxe c = null;
		String getByMatriculaQuery = "SELECT * FROM COCHES WHERE matricua=?";
		
				
		try {
			PreparedStatement st = conexio.prepareStatement(getByMatriculaQuery);
			st.setString(1, matricula);
						
			ResultSet coches = st.executeQuery();
			if(coches.next())
			{
				c = new Cotxe();
				c.setMatricula(coches.getString("matricula"));
				c.setMarca(coches.getString("marca"));
				c.setModel(coches.getString("modelo"));
				c.setColor(coches.getString("color"));
				c.setNum_portes(coches.getInt("num_puertas"));
			}
			
			st.close();
			coches.close();
			
			
		} catch (SQLException e) {
			throw new CotxeNoExisteixException();
		}

		
		
		
		return c;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cotxes.dao.CotxeDAO#updateCotxe(cotxes.models.Cotxe)
	 */
	@Override
	public void updateCotxe(Cotxe c) {
		String updateCotxeQuery = "UPDATE coches SET marca=?, modelo=?, color=?, num_puertas=? WHERE matricula=?";
		
		try {
			PreparedStatement st = conexio.prepareStatement(updateCotxeQuery);
			st.setString(1, c.getMarca());
			st.setString(2, c.getModel());
			st.setString(3, c.getColor());
			st.setInt(4, c.getNum_portes());
			st.setString(5, c.getMatricula());
			st.executeUpdate();
			st.close();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cotxes.dao.CotxeDAO#deleteCotxe(cotxes.models.Cotxe)
	 */
	@Override
	public void deleteCotxe(String matricula) throws CotxeNoEliminatException {
		String deleteCotxeQuery = "DELETE FROM coches WHERE matricula=?";

		try {
			PreparedStatement st = conexio.prepareStatement(deleteCotxeQuery);
			st.setString(1, matricula);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			throw new CotxeNoEliminatException();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cotxes.dao.CotxeDAO#getAll()
	 */
	@Override
	public ArrayList<Cotxe> getAll() {

		String queryAll = "SELECT * FROM coches";
		Cotxe c;
		ArrayList<Cotxe> cochesList = new ArrayList<Cotxe>();

		try {
			Statement st = conexio.createStatement();

			ResultSet coches = st.executeQuery(queryAll);

			while (coches.next()) {

				c = new Cotxe();

				c.setMatricula(coches.getString("matricula"));
				c.setMarca(coches.getString("marca"));
				c.setModel(coches.getString("modelo"));
				c.setColor(coches.getString("color"));
				c.setNum_portes(coches.getInt("num_puertas"));

				cochesList.add(c);
			}

			// TODO Cierro Statement y ResultSet
			st.close();
			coches.close();

			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cochesList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cotxes.dao.CotxeDAO#deleteAll()
	 */
	@Override
	public void deleteAll() {
		String deleteAllQuery = "DELETE FROM coches";
		
		try {
			Statement st = conexio.createStatement();
			st.executeQuery(deleteAllQuery);
			st.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}

	}

	// Per a fer custom queries gastarem el manager singleton i nececitarem aso
	public void confirmarCanvis() {
		try {
			conexio.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void desferCanvis() {
		try {
			conexio.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tancarConexio() {
		try {
			conexio.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

