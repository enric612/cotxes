package cotxes.ui;

import java.util.Vector;

import javax.swing.JOptionPane;

import cotxes.excepcions.CotxeJaExisteixException;
import cotxes.excepcions.CotxeNoExisteixException;
import cotxes.models.Cotxe;

public class ActionPerformer {

	public static boolean addCotxe(CotxeLoader cl) {

		Cotxe c = new Cotxe();

		String matricula = cl.textMatricula.getText();

		if (matricula.length() > 0) {

			c.setMatricula(matricula);
		} else {
			JOptionPane.showMessageDialog(cl, "Matricula obligatoria");
			cl.textMatricula.requestFocus();
			return false;
		}

		String marca = cl.textMarca.getText();

		if (marca.length() > 0) {

			c.setMarca(marca);
		} else {
			JOptionPane.showMessageDialog(cl, "Marca obligatoria");
			cl.textMarca.requestFocus();
			return false;
		}

		String model = cl.textModel.getText();

		if (model.length() > 0) {

			c.setModel(model);
		} else {
			JOptionPane.showMessageDialog(cl, "Model obligatori");
			cl.textModel.requestFocus();
			return false;
		}

		String color = cl.textColor.getText();

		if (color.length() > 0) {

			c.setColor(color);
		} else {
			JOptionPane.showMessageDialog(cl, "Color obligatoria");
			cl.textColor.requestFocus();
			return false;
		}

		String s = cl.textNumPortes.getText();
		if (util.Strings.isInteger(s)) {
			c.setNum_portes(Integer.parseInt(s));
		}
		else {
			JOptionPane.showMessageDialog(cl, "Numero de portes deu ser un enter");
			cl.textNumPortes.requestFocus();
			return false;
		}
		
		try {
			cl.model.addCotxe(c);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} 

		return true;

	}
	
	// Fer cast esta be, pero personalment m'agrada controla millor el flux de dades que vaig a insertar. Esta funcio pot modificarse lleugerament en un futur i afegir
	// un parametre aixi : Sting classe=null i amb aixo fem esta funció polimorfica en dos linees o tres mes. Pero aso queda fora de l'objectiu del curs.
	public static void obrirSeleccio(int i, Principal p) {
			
		Cotxe c = p.getCotxe(i);
		p.obrirCotxeLoader(c);
		
		
	}

}
