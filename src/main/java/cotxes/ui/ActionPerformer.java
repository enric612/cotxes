package cotxes.ui;

import java.util.Vector;

import javax.swing.JOptionPane;

public class ActionPerformer {

	public static boolean addCotxe(CotxeLoader cl) {

		Vector fila = new Vector();

		String matricula = cl.textMatricula.getText();

		if (matricula.length() > 0) {

			fila.add(matricula);
		} else {
			JOptionPane.showMessageDialog(cl, "Matricula obligatoria");
			return false;
		}

		String marca = cl.textMarca.getText();

		if (marca.length() > 0) {

			fila.add(marca);
		} else {
			JOptionPane.showMessageDialog(cl, "Marca obligatoria");
			return false;
		}

		String model = cl.textModel.getText();

		if (model.length() > 0) {

			fila.add(model);
		} else {
			JOptionPane.showMessageDialog(cl, "Model obligatori");
			return false;
		}

		String color = cl.textColor.getText();

		if (color.length() > 0) {

			fila.add(color);
		} else {
			JOptionPane.showMessageDialog(cl, "Color obligatoria");
			return false;
		}

		String s = cl.textNumPortes.getText();
		if (util.Strings.isInteger(s)) {
			fila.add(Integer.parseInt(s));
		}
		else {
			JOptionPane.showMessageDialog(cl, "Numero de portes deu ser un enter");
			return false;
		}
		
		cl.model.addRow(fila);

		return true;

	}

}
