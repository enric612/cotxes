/**
 * 
 */
package cotxes.dao;

import cotxes.models.Cotxe;

/**
 * @author ecliment
 * Interface per a operacions CRUD sobre la classe Cotxe
 */
public interface CotxeDAO {
	
	public void insertCotxe(Cotxe c);
	public void getCotxe(Cotxe c);
	public void updateCotxe(Cotxe c);
	public void deleteCotxe(Cotxe c);
	public void getAll();
	public void deleteAll();
	

}
