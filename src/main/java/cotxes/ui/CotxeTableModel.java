package cotxes.ui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import cotxes.models.Cotxe;

// Aso ho fem aixina per comoditat pero deuriem de gastar la interficie CotxeDAO i mitjançant injecció de dependencies configurar la implementació que nececitem.
import cotxes.dao.CotxeDAOHSQLDB;
import cotxes.excepcions.CotxeJaExisteixException;
import cotxes.excepcions.CotxeNoEliminatException;
import cotxes.excepcions.CotxeNoExisteixException;

public class CotxeTableModel extends DefaultTableModel {
	
	private ArrayList<Cotxe> cotxesList;	
	private CotxeDAOHSQLDB dao;
	
	public CotxeTableModel(){
		super(null, new String[] {"Matricula", "Marca", "Model", "Color", "Nombre de portes"});		
		// Al inici careguem la BD
		//DAO
		this.dao = new CotxeDAOHSQLDB();
		this.cotxesList = this.dao.getAll();
		this.reload();
		
	}
	
	public Cotxe getFila(int i) {
		return this.cotxesList.get(i);
	}
	
	public void eliminarFila(int i) throws CotxeNoEliminatException {
		String matricula = this.cotxesList.get(i).getMatricula();
		this.dao.deleteCotxe(matricula);
		this.cotxesList.remove(i);
		this.removeRow(i);
	}
	
	public void reload(){
		this.getDataVector().clear();
		for(Cotxe c: this.cotxesList){
			this.addFila(c);
		}
	}
	
	
	public void addCotxe(Cotxe c) throws CotxeJaExisteixException, CotxeNoExisteixException
	{
		if (this.cotxesList.contains(c)) {
			this.dao.updateCotxe(c);
			this.cotxesList.set(this.cotxesList.indexOf(c), c);
			this.reload();
		}else {
			this.dao.insertCotxe(c);
			this.cotxesList.add(c);
			this.addFila(c);
		}
		
	}
	
	public void addFila(Cotxe c){		
		Vector fila=new Vector();
		fila.add(c.getMatricula());
		fila.add(c.getMarca());
		fila.add(c.getModel());
		fila.add(c.getColor());
		fila.add(c.getNum_portes());		
		this.addRow(fila);
	}
	

}
