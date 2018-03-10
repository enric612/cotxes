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
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private CotxeLoader cotxeLoader;
	private DefaultTableModel model;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				int response = JOptionPane.showConfirmDialog(Principal.this, "Segur que vols eixir?");
				if(response == JOptionPane.OK_OPTION){
					System.exit(0);
				}
				
			}
		});
		// Forcem el icono perque Window Builder no te ganes de vore el folder fora del src i no es adecuat.
		ImageIcon img = new ImageIcon("recursos/icon.png");
	    setIconImage(img.getImage());
	    setFont(new Font("Arial", Font.PLAIN, 14));
	    
	    // La classe Jframe no deixa sobreescriute la font del titol, aso ho controla el propi sistema operatiu... Es podria fer una reimplementació de la classe principal
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
					int response = JOptionPane.showConfirmDialog(Principal.this, "Si crees un element nou pedras la informació actual, estas segur?");
					if (response == JOptionPane.OK_OPTION) {
						tancarCotxeLoader();
					}
				}else {
					tancarCotxeLoader();
				}
				
				
			}
		});
		mntmNou.setFont(new Font("Arial", Font.PLAIN, 14));
		mnFitxer.add(mntmNou);
		
		mnFitxer.addSeparator();
		
		JMenuItem mntmEixir = new JMenuItem("Eixir");
		mntmEixir.setFont(new Font("Arial", Font.PLAIN, 14));
		mnFitxer.add(mntmEixir);
		
		JMenu mnEditar = new JMenu("Selecci\u00F3");
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
		model = new DefaultTableModel(null, new String[] {"Matricula", "Marca", "Model", "Color", "Nombre de portes"});
		model.addRow(new String[] {"123456ABC", "BMW", "Serie 1", "Roig", "4"});
		
		table = new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 14));		
		scrollPane.setViewportView(table);
	}
	
	private void tancarCotxeLoader()
	{
		// Evitem multiples finestres i ens asegurem que crer una finestra buida
		cotxeLoader.dispose();
		cotxeLoader = new CotxeLoader(model);
		cotxeLoader.setVisible(true);
		cotxeLoader.setLocationRelativeTo(Principal.this);
	}
	
	

}
