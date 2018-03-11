package cotxes.ui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import cotxes.models.Cotxe;

public class CotxeTableModel extends DefaultTableModel {
	
	private ArrayList<Cotxe> cotxesList;
	
	public CotxeTableModel(){
		super(null, new String[] {"Matricula", "Marca", "Model", "Color", "Nombre de portes"});
		this.cotxesList = new ArrayList<Cotxe>();
	}
	
	public Cotxe getFila(int i) {
		return this.cotxesList.get(i);
	}
	
	public void eliminarFila(int i) {
		this.removeRow(i);
	}
	
	public void reload(){
		this.getDataVector().clear();
		for(Cotxe c: this.cotxesList){
			this.addFila(c);
		}
	}
	
	
	public void addCotxe(Cotxe c)
	{
		if (this.cotxesList.contains(c)) {
			this.cotxesList.set(this.cotxesList.indexOf(c), c);
			this.reload();
		}else {
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
