package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.eseo.jic.wifidetector.vue.forme.VueSalle;

public class FenetreCartographieWifi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel labelInfoReseau;
	private JLabel lblVersion;
	private JButton btnFermer;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;

	public static FenetreCartographieWifi instance;
	private JLabel labelDimensionSalle;
	private JButton buttonExporterResultat;
	private JLabel lblLargeurSalle;

	private JPanel panelSalle;
	private JLabel labelLongueurSalle;
	private JTextField textFieldLongueur;
	private JTextField textFieldLargeur;
	private JLabel lblLongueur;
	private JLabel labelLargeur;

	private FenetreCartographieWifi() {

		this.setResizable(false);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setBounds(300, 300, 710, 580);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Cartographie wifi");

		// Label texte titre
		labelInfoReseau = new JLabel("Cartographie du réseau wifi:");
		labelInfoReseau.setForeground(Color.BLUE);
		labelInfoReseau.setFont(new Font("Helvetica", Font.PLAIN, 38));
		labelInfoReseau.setBounds(35, 0, 608, 46);
		this.getContentPane().add(labelInfoReseau);

		// Label texte Debit entrant
		JLabel labelInformation = new JLabel(
				"Entrez la dimension de la salle afin de savoir où effectuer les relevés.");
		labelInformation.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelInformation.setBounds(6, 58, 648, 29);
		getContentPane().add(labelInformation);

		// Label texte Moyenne débit descendant
		labelDimensionSalle = new JLabel("Dimension de la salle:");
		labelDimensionSalle.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelDimensionSalle.setBounds(6, 98, 189, 29);
		getContentPane().add(labelDimensionSalle);

		// Label version en bas
		lblVersion = new JLabel("Version 1.0 2018. Tous droits réservés.");
		lblVersion.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblVersion.setBounds(256, 530, 229, 22);
		this.getContentPane().add(lblVersion);

		lblLongueur = new JLabel("Longueur (en m):");
		lblLongueur.setFont(new Font("Helvetica", Font.PLAIN, 17));
		lblLongueur.setBounds(214, 98, 148, 29);
		getContentPane().add(lblLongueur);

		labelLargeur = new JLabel("Largeur (en m):");
		labelLargeur.setFont(new Font("Helvetica", Font.PLAIN, 17));
		labelLargeur.setBounds(456, 99, 120, 29);
		getContentPane().add(labelLargeur);

		// Boutton fermer fenetre
		btnFermer = new JButton(new ActionMenu());
		btnFermer.setHorizontalAlignment(SwingConstants.LEFT);
		btnFermer.setFont(new Font("Helvetica", Font.PLAIN, 17));
		btnFermer.setBounds(638, 508, 66, 44);
		btnFermer.setText("Retour");
		btnFermer.setActionCommand(ActionMenu.NOM_ACTION_RETOUR_MENU);
		this.getContentPane().add(btnFermer);

		// Boutton exporter résultat
		// Pas de commande attribuée pour le moment
		buttonExporterResultat = new JButton((Action) null);
		buttonExporterResultat.setText("Exporter résultat");
		buttonExporterResultat.setHorizontalAlignment(SwingConstants.LEFT);
		buttonExporterResultat.setFont(new Font("Helvetica", Font.PLAIN, 17));
		buttonExporterResultat.setActionCommand("Fermer fenetre");
		buttonExporterResultat.setBounds(6, 508, 169, 44);
		getContentPane().add(buttonExporterResultat);

		// label texte
		labelLongueurSalle = new JLabel("Longueur");
		labelLongueurSalle.setFont(new Font("Helvetica", Font.PLAIN, 20));
		labelLongueurSalle.setBounds(269, 479, 93, 21);
		getContentPane().add(labelLongueurSalle);

		lblLargeurSalle = new JLabel("Largeur");
		lblLargeurSalle.setBounds(6, 294, 74, 21);
		getContentPane().add(lblLargeurSalle);
		lblLargeurSalle.setFont(new Font("Helvetica", Font.PLAIN, 20));

		// Champ pour remplir inofrmations sur la salle
		textFieldLongueur = new JTextField();
		textFieldLongueur.setBounds(353, 99, 74, 26);
		getContentPane().add(textFieldLongueur);
		textFieldLongueur.setColumns(10);

		textFieldLargeur = new JTextField();
		textFieldLargeur.setColumns(10);
		textFieldLargeur.setBounds(577, 99, 66, 26);
		getContentPane().add(textFieldLargeur);

		// Ajout du rectangle dans le panel crée specialement pour ca
		panelSalle = new VueSalle();
		panelSalle.setBounds(52, 141, 591, 336);
		getContentPane().add(panelSalle);
		/**
		 * JPanel panel = new VueSalle(); System.out.println("Je réalise ");
		 * 
		 * this.setContentPane(panel); System.out.println("Je réalise2 ");
		 */

		textFieldLongueur.addActionListener(new actionListener());
		textFieldLargeur.addActionListener(new actionListener());

	}

	public static FenetreCartographieWifi getInstance() {
		if (instance == null) {
			FenetreCartographieWifi.instance = new FenetreCartographieWifi();
		}
		return FenetreCartographieWifi.instance;
	}

	class actionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Field1 " + textFieldLongueur.getText());
			System.out.println("Field2 " + textFieldLargeur.getText());
		}
	}
}
