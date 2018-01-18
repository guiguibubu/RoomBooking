package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.eseo.jic.wifidetector.controller.MesureWifi;

public class FenetreAffichageAcquisitions extends JFrame {
	/**
	 * Fenetre affichage résultat cartographie 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelInfoReseau;
	private JLabel labelSSID;
	private JButton btnFermer;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;
	private static final int LARGEUR_FENETRE_MIN = 650;
	private static final int HAUTEUR_FENETRE_MIN = 410;

	public static FenetreAffichageAcquisitions instance;
	private JLabel labelSSIDGet;
	private JLabel lblQualiteSignal;
	private JLabel labelMoyenneDebitDescendant;
	private JLabel lblVersion;
	private JLabel labelQualitéSignalInfo;
	private JLabel labelMoyenneDebitDescendantInfo;

	private FenetreAffichageAcquisitions() {

		// Création de la fenetre
		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setMinimumSize(new Dimension(LARGEUR_FENETRE_MIN, HAUTEUR_FENETRE_MIN));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Informations");

		// Label texte
		this.labelInfoReseau = new JLabel("<html>Informations sur les différentes acquisitions</html>");
		this.labelInfoReseau.setForeground(Color.BLUE);
		this.labelInfoReseau.setFont(new Font("Helvetica", Font.PLAIN, 30));
		this.labelInfoReseau.setBounds(32, 6, 579, 74);
		this.getContentPane().add(this.labelInfoReseau);
		// Label texte Qualite signal
		this.lblQualiteSignal = new JLabel("Qualité du signal (en %):");
		this.lblQualiteSignal.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblQualiteSignal.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.lblQualiteSignal.setBounds(80, 140, 222, 30);
		this.getContentPane().add(this.lblQualiteSignal);
		// Label texte Debit entrant
		JLabel labelDebitEntrant = new JLabel("Débit entrant (en kb):");
		labelDebitEntrant.setHorizontalAlignment(SwingConstants.LEFT);
		labelDebitEntrant.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		labelDebitEntrant.setBounds(80, 180, 222, 30);
		this.getContentPane().add(labelDebitEntrant);

		// Label texte Moyenne débit descendant
		this.labelMoyenneDebitDescendant = new JLabel("Moyenne débit descendant (en kb): ");
		this.labelMoyenneDebitDescendant.setHorizontalAlignment(SwingConstants.LEFT);
		this.labelMoyenneDebitDescendant.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelMoyenneDebitDescendant.setBounds(80, 220, 320, 30);
		this.getContentPane().add(this.labelMoyenneDebitDescendant);

		// Label texte SSID
		this.labelSSID = new JLabel("Nom du réseau:");
		this.labelSSID.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelSSID.setBounds(80, 100, 152, 29);
		this.getContentPane().add(this.labelSSID);

		// Boutton fermer fenetre
		this.btnFermer = new JButton(new ActionMenu());
		this.btnFermer.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		this.btnFermer.setBounds(515, 320, 96, 44);
		this.btnFermer.setText("Fermer");
		this.btnFermer.setActionCommand(ActionMenu.NOM_ACTION_CLOSE);
		this.getContentPane().add(this.btnFermer);

		// System.out.println("Signal : " +
		// MesureWifi.getSignal(MesureWifi.WIFI_ETUDIANT));
		String nomSignal = MesureWifi.WIFI_ETUDIANT;
		this.labelSSIDGet = new JLabel(nomSignal);
		this.labelSSIDGet.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelSSIDGet.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelSSIDGet.setBounds(457, 100, 152, 29);
		this.getContentPane().add(this.labelSSIDGet);

		/**
		 * Mettre à partir d'ici les info que l'on veut afficher dans les
		 * différents label
		 */
		labelMoyenneDebitDescendantInfo = new JLabel("3");
		labelMoyenneDebitDescendantInfo.setHorizontalAlignment(SwingConstants.CENTER);
		labelMoyenneDebitDescendantInfo.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelMoyenneDebitDescendantInfo.setBounds(457, 221, 152, 29);
		getContentPane().add(labelMoyenneDebitDescendantInfo);

		JLabel labelDebitEntrantInfo = new JLabel("2");
		labelDebitEntrantInfo.setHorizontalAlignment(SwingConstants.CENTER);
		labelDebitEntrantInfo.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelDebitEntrantInfo.setBounds(457, 181, 152, 29);
		getContentPane().add(labelDebitEntrantInfo);

		labelQualitéSignalInfo = new JLabel("1");
		labelQualitéSignalInfo.setHorizontalAlignment(SwingConstants.CENTER);
		labelQualitéSignalInfo.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelQualitéSignalInfo.setBounds(457, 141, 152, 29);
		getContentPane().add(labelQualitéSignalInfo);

		/**
		 * Fin des info que l'on veut afficher
		 */

		// Label version en bas
		this.lblVersion = new JLabel("Version 1.0 2018. Tous droits réservés.");
		this.lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVersion.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
		this.lblVersion.setBounds(223, 342, 229, 22);
		this.getContentPane().add(this.lblVersion);
		// on centre la fenetre
		Toolkit tool = this.getToolkit();
		int largeurEcran = (int) tool.getScreenSize().getWidth();
		int hauteurEcran = (int) tool.getScreenSize().getHeight();
		this.pack();
		this.setLocation((largeurEcran - this.getWidth()) / 2, (hauteurEcran - this.getHeight()) / 2);

	}

	public static FenetreAffichageAcquisitions getInstance() {
		if (instance == null) {
			FenetreAffichageAcquisitions.instance = new FenetreAffichageAcquisitions();
		}
		return FenetreAffichageAcquisitions.instance;
	}
}
