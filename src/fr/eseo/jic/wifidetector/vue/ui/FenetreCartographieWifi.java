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

		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setBounds(300, 300, 710, 580);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Cartographie wifi");

		// Label texte titre
		this.labelInfoReseau = new JLabel("Cartographie du réseau wifi:");
		this.labelInfoReseau.setForeground(Color.BLUE);
		this.labelInfoReseau.setFont(new Font("Helvetica", Font.PLAIN, 38));
		this.labelInfoReseau.setBounds(35, 0, 608, 46);
		this.getContentPane().add(this.labelInfoReseau);

		// Label texte Debit entrant
		JLabel labelInformation = new JLabel(
				"Entrez la dimension de la salle afin de savoir où effectuer les relevés.");
		labelInformation.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelInformation.setBounds(6, 58, 648, 29);
		this.getContentPane().add(labelInformation);

		// Label texte Moyenne débit descendant
		this.labelDimensionSalle = new JLabel("Dimension de la salle:");
		this.labelDimensionSalle.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.labelDimensionSalle.setBounds(6, 98, 189, 29);
		this.getContentPane().add(this.labelDimensionSalle);

		// Label version en bas
		this.lblVersion = new JLabel("Version 1.0 2018. Tous droits réservés.");
		this.lblVersion.setFont(new Font("Helvetica", Font.PLAIN, 13));
		this.lblVersion.setBounds(256, 530, 229, 22);
		this.getContentPane().add(this.lblVersion);

		this.lblLongueur = new JLabel("Longueur (en m):");
		this.lblLongueur.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.lblLongueur.setBounds(214, 98, 148, 29);
		this.getContentPane().add(this.lblLongueur);

		this.labelLargeur = new JLabel("Largeur (en m):");
		this.labelLargeur.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.labelLargeur.setBounds(456, 99, 120, 29);
		this.getContentPane().add(this.labelLargeur);

		// Boutton fermer fenetre
		this.btnFermer = new JButton(new ActionMenu());
		this.btnFermer.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnFermer.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.btnFermer.setBounds(638, 508, 66, 44);
		this.btnFermer.setText("Retour");
		this.btnFermer.setActionCommand(ActionMenu.NOM_ACTION_RETOUR_MENU);
		this.getContentPane().add(this.btnFermer);

		// Boutton exporter résultat
		// Pas de commande attribuée pour le moment
		this.buttonExporterResultat = new JButton((Action) null);
		this.buttonExporterResultat.setText("Exporter résultat");
		this.buttonExporterResultat.setHorizontalAlignment(SwingConstants.LEFT);
		this.buttonExporterResultat.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.buttonExporterResultat.setActionCommand("Fermer fenetre");
		this.buttonExporterResultat.setBounds(6, 508, 169, 44);
		this.getContentPane().add(this.buttonExporterResultat);

		// label texte
		this.labelLongueurSalle = new JLabel("Longueur");
		this.labelLongueurSalle.setFont(new Font("Helvetica", Font.PLAIN, 20));
		this.labelLongueurSalle.setBounds(269, 479, 93, 21);
		this.getContentPane().add(this.labelLongueurSalle);

		this.lblLargeurSalle = new JLabel("Largeur");
		this.lblLargeurSalle.setBounds(6, 294, 74, 21);
		this.getContentPane().add(this.lblLargeurSalle);
		this.lblLargeurSalle.setFont(new Font("Helvetica", Font.PLAIN, 20));

		// Champ pour remplir inofrmations sur la salle
		this.textFieldLongueur = new JTextField();
		this.textFieldLongueur.setBounds(353, 99, 74, 26);
		this.getContentPane().add(this.textFieldLongueur);
		this.textFieldLongueur.setColumns(10);

		this.textFieldLargeur = new JTextField();
		this.textFieldLargeur.setColumns(10);
		this.textFieldLargeur.setBounds(577, 99, 66, 26);
		this.getContentPane().add(this.textFieldLargeur);

		// Ajout du rectangle dans le panel crée specialement pour ca
		this.panelSalle = new VueSalle();
		this.panelSalle.setBounds(52, 141, 591, 336);
		this.getContentPane().add(this.panelSalle);
		/**
		 * JPanel panel = new VueSalle(); System.out.println("Je réalise ");
		 *
		 * this.setContentPane(panel); System.out.println("Je réalise2 ");
		 */

		this.textFieldLongueur.addActionListener(new actionListener());
		this.textFieldLargeur.addActionListener(new actionListener());

	}

	public static FenetreCartographieWifi getInstance() {
		if (instance == null) {
			FenetreCartographieWifi.instance = new FenetreCartographieWifi();
		}
		return FenetreCartographieWifi.instance;
	}

	class actionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Field1 " + FenetreCartographieWifi.this.textFieldLongueur.getText());
			System.out.println("Field2 " + FenetreCartographieWifi.this.textFieldLargeur.getText());
		}
	}
}
