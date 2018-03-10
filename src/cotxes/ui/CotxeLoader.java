package cotxes.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CotxeLoader extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CotxeLoader() {
		// Forcem el icono perque Window Builder no te ganes de vore el folder fora del
		// src i no es adecuat.
		ImageIcon img = new ImageIcon("recursos/edit.png");
		setIconImage(img.getImage());
	    setFont(new Font("Arial", Font.PLAIN, 14));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
