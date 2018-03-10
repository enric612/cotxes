package cotxes.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
		// Forcem el icono perque Window Builder no te ganes de vore el folder fora del src i no es adecuat.
		ImageIcon img = new ImageIcon("recursos/icon.png");
	    setIconImage(img.getImage());
	    setFont(new Font("Arial", Font.PLAIN, 14));
	    
	    // La classe Jframe no deixa sobreescriute la font del titol, aso ho controla el propi sistema operatiu... Es podria fer una reimplementació de la classe principal
	    // pero no ho veig relevant per este treball. 
		setTitle("Gesti\u00F3 de Cotxes");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 439);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFitxer = new JMenu("Fitxer");
		mnFitxer.setFont(new Font("Arial", Font.PLAIN, 14));
		menuBar.add(mnFitxer);
		
		JMenuItem mntmNou = new JMenuItem("Nou");
		mntmNou.setFont(new Font("Arial", Font.PLAIN, 14));
		mnFitxer.add(mntmNou);
		
		mnFitxer.addSeparator();
		
		JMenuItem mntmEixir = new JMenuItem("Eixir");
		mntmEixir.setFont(new Font("Arial", Font.PLAIN, 14));
		mnFitxer.add(mntmEixir);
		
		JMenu mnEditar = new JMenu("Editar");
		mnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		menuBar.add(mnEditar);
		
		JMenuItem mntmEditar = new JMenuItem("Editar");
		mntmEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		// Per defecte deshabilitem esta opció fins que l'usuari selecciona un element
		mntmEditar.setEnabled(false);
		mnEditar.add(mntmEditar);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.setFont(new Font("Arial", Font.PLAIN, 14));
		// Per defecte deshabilitem esta opció fins que l'usuari selecciona un element
		mntmEliminar.setEnabled(false);
		mnEditar.add(mntmEliminar);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setFont(new Font("Arial", Font.PLAIN, 14));
		menuBar.add(mnAjuda);
		
		JMenuItem mntmSobreGestiDe = new JMenuItem("Sobre Gesti\u00F3 de Cotxes");
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
		
		// Hardcodegem la tabla per comoditat, lo seu seria generar una tabla dinamica que a partir de un diccionari (etiqueta,valor) generara este objecte.
		// Este diccionari el podem crear a partir de la classe model Cotxe pero aquesta aplicació es molt senzilla i especifica i no es necesari ja que estem
		// lligats a una tabla predefinida en BD. En cas de voler aprofitar una mateixa tabla per a diferents models, per exemple tindre cotxes i camions en funció 
		// de un menu o altre (Fitxer carregar dades cotxes, Fitxer carregar dades camions) si seria interessant. 
		DefaultTableModel model = new DefaultTableModel(null, new String[] {"Matricula", "Marca", "Model", "Color", "Nombre de portes"});
		
		table = new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 14));		
		scrollPane.setViewportView(table);
	}

}
