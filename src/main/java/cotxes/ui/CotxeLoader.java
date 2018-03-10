package cotxes.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CotxeLoader extends JFrame {

	protected final JPanel contentPane;
	protected final JTextField textMatricula;
	protected final JTextField textMarca;
	protected final JTextField textModel;
	protected final JTextField textNumPortes;	
	protected final JTextField textColor;
	protected final DefaultTableModel model;

	/**
	 * Create the frame.
	 */
	public CotxeLoader(DefaultTableModel model) {
		this.model = model;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				int response = JOptionPane.showConfirmDialog(CotxeLoader.this, "Si tanques pedras els canvis, estas segur?");
				if (response ==JOptionPane.OK_OPTION) {
					CotxeLoader.this.dispose();
				}
			}
		});
		// Forcem el icono perque Window Builder no te ganes de vore el folder fora del
		// src i no es adecuat.
		ImageIcon img = new ImageIcon("recursos/edit.png");
		setIconImage(img.getImage());
	    setFont(new Font("Arial", Font.PLAIN, 14));

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblNombreDePortes = new JLabel("Nombre de portes");
		lblNombreDePortes.setFont(new Font("Arial", Font.PLAIN, 14));
		
		textMatricula = new JTextField();
		textMatricula.setFont(new Font("Arial", Font.PLAIN, 14));
		textMatricula.setColumns(10);
		
		textMarca = new JTextField();
		textMarca.setFont(new Font("Arial", Font.PLAIN, 14));
		textMarca.setColumns(10);
		
		textModel = new JTextField();
		textModel.setFont(new Font("Arial", Font.PLAIN, 14));
		textModel.setColumns(10);
		
		textNumPortes = new JTextField();
		textNumPortes.setFont(new Font("Arial", Font.PLAIN, 14));
		textNumPortes.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(CotxeLoader.this, "Si tanques pedras els canvis, estas segur?");
				if (response ==JOptionPane.OK_OPTION) {
					CotxeLoader.this.dispose();
				}
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		ImageIcon imgCancelar = new ImageIcon("recursos/cancel_16.png");
		btnCancelar.setIcon(imgCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 14));
		ImageIcon imgGuardar = new ImageIcon("recursos/save_16.png");
		btnGuardar.setIcon(imgGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					ActionPerformer.addCotxe(CotxeLoader.this);
					dispose();
				
				
			}
		});
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Arial", Font.PLAIN, 14));
		
		textColor = new JTextField();
		textColor.setFont(new Font("Arial", Font.PLAIN, 14));
		textColor.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMatricula)
								.addComponent(lblMarca)
								.addComponent(lblModel)
								.addComponent(lblNombreDePortes)
								.addComponent(lblColor, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textColor, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textNumPortes)
									.addComponent(textModel)
									.addComponent(textMarca)
									.addComponent(textMatricula, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMatricula)
						.addComponent(textMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMarca)
						.addComponent(textMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModel)
						.addComponent(textModel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColor, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textColor, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreDePortes)
						.addComponent(textNumPortes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnGuardar))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
