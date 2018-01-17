package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.eseo.jic.wifidetector.modele.ZoneMesure;
import fr.eseo.jic.wifidetector.vue.forme.VueSalle;

public class FenetreCartographieWifi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel labelInfoReseau;
	private JLabel lblVersion;
	private JButton btnFermer;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;

	private static final int LARGEUR_FENETRE_MIN = 710;
	private static final int HAUTEUR_FENETRE_MIN = 580;

	public static FenetreCartographieWifi instance;
	private JLabel labelDimensionSalle;
	private JButton buttonExporterResultat;
	private JLabel lblHauteurSalle;

	private VueSalle panelSalle;
	private JLabel labelLargeurSalle;
	private JTextField textFieldLargeur;
	private JTextField textFieldHauteur;
	private JLabel lblLargeur;
	private JLabel labelHauteur;

	private FenetreCartographieWifi() {

		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		//		this.setBounds(300, 300, 710, 580);
		this.setMinimumSize(new Dimension(LARGEUR_FENETRE_MIN, HAUTEUR_FENETRE_MIN));
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

		this.lblLargeur = new JLabel("Largeur (en m):");
		this.lblLargeur.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.lblLargeur.setBounds(214, 98, 148, 29);
		this.getContentPane().add(this.lblLargeur);

		this.labelHauteur = new JLabel("Hauteur (en m):");
		this.labelHauteur.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.labelHauteur.setBounds(456, 99, 120, 29);
		this.getContentPane().add(this.labelHauteur);

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
		this.labelLargeurSalle = new JLabel("Largeur");
		this.labelLargeurSalle.setFont(new Font("Helvetica", Font.PLAIN, 20));
		this.labelLargeurSalle.setBounds(269, 479, 93, 21);
		this.getContentPane().add(this.labelLargeurSalle);

		this.lblHauteurSalle = new JLabel("Hauteur");
		this.lblHauteurSalle.setBounds(6, 294, 74, 21);
		this.getContentPane().add(this.lblHauteurSalle);
		this.lblHauteurSalle.setFont(new Font("Helvetica", Font.PLAIN, 20));

		// Champ pour remplir inofrmations sur la salle
		this.textFieldLargeur = new JTextField();
		this.textFieldLargeur.setBounds(353, 99, 74, 26);
		this.getContentPane().add(this.textFieldLargeur);
		this.textFieldLargeur.setColumns(10);

		this.textFieldHauteur = new JTextField();
		this.textFieldHauteur.setColumns(10);
		this.textFieldHauteur.setBounds(577, 99, 66, 26);
		this.getContentPane().add(this.textFieldHauteur);

		// Ajout du rectangle dans le panel crée specialement pour ca
		this.panelSalle = new VueSalle();
		this.panelSalle.setBounds(VueSalle.ABSCISSE_ORIGINE, VueSalle.ORDONNE_ORIGINE, VueSalle.LARGEUR_MAX, VueSalle.HAUTEUR_MAX);
		this.getContentPane().add(this.panelSalle);

		this.textFieldLargeur.addActionListener(new actionListener(this.panelSalle));
		this.textFieldHauteur.addActionListener(new actionListener(this.panelSalle));

		// on centre la fenetre
		Toolkit tool = this.getToolkit();
		int largeurEcran = (int) tool.getScreenSize().getWidth();
		int hauteurEcran = (int) tool.getScreenSize().getHeight();
		this.pack();
		this.setLocation((largeurEcran - this.getWidth())/2, (hauteurEcran - this.getHeight())/2);
	}

	public static FenetreCartographieWifi getInstance() {
		if (instance == null) {
			FenetreCartographieWifi.instance = new FenetreCartographieWifi();
		}
		return FenetreCartographieWifi.instance;
	}

	class actionListener implements ActionListener {
		VueSalle vueSalle;
		protected actionListener(VueSalle vueSalle){
			super();
			this.vueSalle = vueSalle;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Largeur " + FenetreCartographieWifi.this.textFieldLargeur.getText());
			System.out.println("Hauteur " + FenetreCartographieWifi.this.textFieldHauteur.getText());

			try {
				this.vueSalle.getSalle().setHauteur(new Integer(FenetreCartographieWifi.this.textFieldHauteur.getText()));
				this.vueSalle.getSalle().setLargeur(new Integer(FenetreCartographieWifi.this.textFieldLargeur.getText()));
				this.vueSalle.getSalle().calculListePointMesure();
				for(ZoneMesure zoneMesure : this.vueSalle.getSalle().getListeZoneMesure()){
					System.out.println(zoneMesure);
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Merci de saisir des chiffres", "Erreur", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
	}
}
