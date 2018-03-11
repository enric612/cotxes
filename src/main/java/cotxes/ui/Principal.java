package cotxes.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cotxes.excepcions.CotxeJaExisteixException;
import cotxes.excepcions.CotxeNoEliminatException;
import cotxes.excepcions.CotxeNoExisteixException;
import cotxes.models.Cotxe;

import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private CotxeLoader cotxeLoader;
	private SobreMi sm;
	private CotxeTableModel model;
	private JMenu mnEditar;
	private JMenuItem mntmEditar;
	private JMenuItem mntmEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {

		cotxeLoader = new CotxeLoader(model);
		sm = new SobreMi();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				eixir();

			}
		});
		// Forcem el icono perque Window Builder no te ganes de vore el folder fora del
		// src i no es adecuat.
		ImageIcon img = new ImageIcon("recursos/icon.png");
		setIconImage(img.getImage());
		setFont(new Font("Arial", Font.PLAIN, 14));

		// La classe Jframe no deixa sobreescriute la font del titol, aso ho controla el
		// propi sistema operatiu... Es podria fer una reimplementació de la classe
		// principal
		// pero no ho veig relevant per este treball.
		setTitle("Gesti\u00F3 de Cotxes");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 731, 439);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFitxer = new JMenu("Fitxer");
		mnFitxer.setFont(new Font("Arial", Font.PLAIN, 14));
		menuBar.add(mnFitxer);

		JMenuItem mntmNou = new JMenuItem("Nou");
		mntmNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Si es visible pot tindre dades
				if (cotxeLoader.isVisible()) {
					int response = JOptionPane.showConfirmDialog(Principal.this,
							"Si crees un element nou pedras la informació actual, estas segur?");
					if (response == JOptionPane.OK_OPTION) {
						tancarCotxeLoader();
					}
				} else {
					tancarCotxeLoader();
				}

			}
		});
		mntmNou.setFont(new Font("Arial", Font.PLAIN, 14));
		mnFitxer.add(mntmNou);

		mnFitxer.addSeparator();

		JMenuItem mntmEixir = new JMenuItem("Eixir");
		mntmEixir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eixir();
			}
		});
		mntmEixir.setFont(new Font("Arial", Font.PLAIN, 14));
		mnFitxer.add(mntmEixir);

		mnEditar = new JMenu("Selecci\u00F3");
		mnEditar.setEnabled(false);
		mnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		menuBar.add(mnEditar);

		mntmEditar = new JMenuItem("Editar");
		mntmEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = Principal.this.table.getSelectedRow();
				if (i >= 0) {
					ActionPerformer.obrirSeleccio(i, Principal.this);
				} else {
					JOptionPane.showMessageDialog(Principal.this,
							"Per favor selecciona un element a editar de la taula.");
				}
			}
		});
		mntmEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		// Per defecte deshabilitem esta opció fins que l'usuari selecciona un element
		mntmEditar.setEnabled(false);
		mnEditar.add(mntmEditar);

		mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = Principal.this.table.getSelectedRow();
				if (i >= 0) {
					int response = JOptionPane.showConfirmDialog(Principal.this,
							"Vas a eliminar este element, estas segur?");
					if (response == JOptionPane.OK_OPTION) {
						try {
							Principal.this.model.eliminarFila(i);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
						// Si ens quedem sense elements desactivem els menus de selecció
						if (Principal.this.table.getRowCount() == 0) {
							Principal.this.mnEditar.setEnabled(false);
							Principal.this.mntmEditar.setEnabled(false);
							Principal.this.mntmEliminar.setEnabled(false);
						}
					}
				} else {
					JOptionPane.showMessageDialog(Principal.this,
							"Per favor selecciona un element a eliminar de la taula.");
				}

			}
		});
		mntmEliminar.setFont(new Font("Arial", Font.PLAIN, 14));
		// Per defecte deshabilitem esta opció fins que l'usuari selecciona un element
		mntmEliminar.setEnabled(false);
		mnEditar.add(mntmEliminar);

		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setFont(new Font("Arial", Font.PLAIN, 14));
		menuBar.add(mnAjuda);

		JMenuItem mntmSobreGestiDe = new JMenuItem("Sobre Gesti\u00F3 de Cotxes");
		mntmSobreGestiDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sm.isVisible()) {
					sm.dispose();
				}
				sm = new SobreMi();
				sm.setVisible(true);
				sm.setLocationRelativeTo(Principal.this);

			}
		});
		mntmSobreGestiDe.setFont(new Font("Arial", Font.PLAIN, 14));
		mnAjuda.add(mntmSobreGestiDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);

		JLabel lblPanelPrincipal = new JLabel("Panel principal");
		lblPanelPrincipal.setFont(new Font("Arial", Font.BOLD, 16));
		contentPane.add(lblPanelPrincipal, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// Hardcodegem la tabla per comoditat, lo seu seria generar una tabla dinamica
		// que a partir de un diccionari (etiqueta,valor) generara este objecte.
		// Este diccionari el podem crear a partir de la classe model Cotxe pero aquesta
		// aplicació es molt senzilla i especifica i no es necesari ja que estem
		// lligats a una tabla predefinida en BD. En cas de voler aprofitar una mateixa
		// tabla per a diferents models, per exemple tindre cotxes i camions en funció
		// de un menu o altre (Fitxer carregar dades cotxes, Fitxer carregar dades
		// camions) si seria interessant.
		model = new CotxeTableModel();
		try {
			model.addCotxe(new Cotxe("12345DAB", "BMW", "Serie 1", "Roig", 4));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		model.reload();

		// Evitem que els usuaris puguen editar els elements de la tabla fora del nostre
		// sistema.
		table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Principal.this.table.getSelectedRowCount() == 1) {
					Principal.this.mnEditar.setEnabled(true);
					Principal.this.mntmEditar.setEnabled(true);
					Principal.this.mntmEliminar.setEnabled(true);
				}

			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
	}

	// Helper methods, millor fero desde asi que utilitzant elements protected

	public void obrirCotxeLoader(Cotxe c) {			
		cotxeLoader = new CotxeLoader(model);
		if (c != null) {
			cotxeLoader.replaceElement(c);
		}
		cotxeLoader.setVisible(true);
		cotxeLoader.setLocationRelativeTo(Principal.this);
		// Mentre treballem en l'altra finestra desactivem este menu per si acas
		this.mnEditar.setEnabled(false);
		this.table.clearSelection();
	}

	public Cotxe getCotxe(int i) {
		return this.model.getFila(i);
	}

	public void tancarCotxeLoader() {
		// Evitem multiples finestres i ens asegurem que crer una finestra buida
		cotxeLoader.dispose();
		this.obrirCotxeLoader(null);
	}

	public void eixir() {
		int response = JOptionPane.showConfirmDialog(Principal.this, "Segur que vols eixir?");
		if (response == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}

}
