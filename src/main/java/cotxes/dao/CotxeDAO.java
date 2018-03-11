/**
 * 
 */
package cotxes.dao;

import java.util.ArrayList;

import cotxes.excepcions.CotxeJaExisteixException;
import cotxes.excepcions.CotxeNoEliminatException;
import cotxes.excepcions.CotxeNoExisteixException;
import cotxes.models.Cotxe;

/**
 * @author ecliment
 * Interface per a operacions CRUD sobre la classe Cotxe
 */
public interface CotxeDAO {
	
	public void insertCotxe(Cotxe c) throws CotxeJaExisteixException;
	public Cotxe getCotxeByMatricula(String matricula) throws CotxeNoExisteixException;
	public void updateCotxe(Cotxe c);
	public void deleteCotxe(String matricula) throws CotxeNoEliminatException;
	public ArrayList<Cotxe> getAll();
	public void deleteAll();
	

}
