package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.eseo.jic.wifidetector.controller.MesureWifi;
import fr.eseo.jic.wifidetector.controller.NoConnectedWifi;

public class FenetreWifiDetector extends JFrame {
	/**
	 * Pop up information du signal
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelInfoReseau;
	private JLabel lblVersion;
	private JLabel lblQualiteSignal;
	private JLabel labelSSID;
	private JLabel labelDebitEntrant;
	private JButton btnFermer;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;

	private static final int LARGEUR_FENETRE_MIN = 650;
	private static final int HAUTEUR_FENETRE_MIN = 410;

	public static FenetreWifiDetector instance;
	//	private JLabel labelMoyenneDebitDescendant;
	private JLabel labelSSIDGet;
	private JLabel labelQualiteSignalGet;
	private JLabel labelDebitEntrantGet;
	//	private JLabel labelMoyenneDebitDescendantGet;

	private FenetreWifiDetector() {
		// Création de la fenetere
		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setMinimumSize(new Dimension(LARGEUR_FENETRE_MIN, HAUTEUR_FENETRE_MIN));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Informations sur le réseau");

		// Label texte titre
		this.labelInfoReseau = new JLabel("Informations sur le réseau:");
		this.labelInfoReseau.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelInfoReseau.setForeground(Color.BLUE);
		this.labelInfoReseau.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 45));
		this.labelInfoReseau.setBounds(35, 25, 608, 46);
		this.getContentPane().add(this.labelInfoReseau);
		// Label texte SSID
		this.labelSSID = new JLabel("Nom du réseau:");
		this.labelSSID.setHorizontalAlignment(SwingConstants.LEFT);
		this.labelSSID.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelSSID.setBounds(35, 135, 152, 30);
		this.getContentPane().add(this.labelSSID);
		// Label texte Qualite signal
		this.lblQualiteSignal = new JLabel("Qualité du signal (en %):");
		this.lblQualiteSignal.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblQualiteSignal.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.lblQualiteSignal.setBounds(35, 176, 222, 30);
		this.getContentPane().add(this.lblQualiteSignal);
		// Label texte Debit entrant
		this.labelDebitEntrant = new JLabel("Débit entrant (en kb):");
		this.labelDebitEntrant.setHorizontalAlignment(SwingConstants.LEFT);
		this.labelDebitEntrant.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelDebitEntrant.setBounds(35, 217, 222, 30);
		this.getContentPane().add(this.labelDebitEntrant);

		// Label version en bas
		this.lblVersion = new JLabel("Version 1.0 2018. Tous droits réservés.");
		this.lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVersion.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
		this.lblVersion.setBounds(225, 320, 229, 22);
		this.getContentPane().add(this.lblVersion);
		// Boutton fermer fenetre
		this.btnFermer = new JButton(new ActionMenu());
		this.btnFermer.setHorizontalAlignment(SwingConstants.CENTER);
		this.btnFermer.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		this.btnFermer.setBounds(540, 301, 89, 61);
		this.btnFermer.setText("Retour");
		this.btnFermer.setActionCommand(ActionMenu.NOM_ACTION_FERMER);
		this.getContentPane().add(this.btnFermer);

		String nomSignal = MesureWifi.WIFI_ETUDIANT;
		this.labelSSIDGet = new JLabel(nomSignal);
		this.labelSSIDGet.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelSSIDGet.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelSSIDGet.setBounds(391, 135, 152, 30);
		this.getContentPane().add(this.labelSSIDGet);

		int qualiteSignal = MesureWifi.getSignal(MesureWifi.WIFI_ETUDIANT);
		this.labelQualiteSignalGet = new JLabel("" + qualiteSignal);
		this.labelQualiteSignalGet.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelQualiteSignalGet.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelQualiteSignalGet.setBounds(391, 176, 114, 30);
		this.getContentPane().add(this.labelQualiteSignalGet);

		int debitEntrant = 0;
		try {
			debitEntrant = MesureWifi.getSpeedDownFixedTime();
			System.out.println("Mesure debit terminé");
		} catch (NoConnectedWifi e) {
			e.printStackTrace();
		}
		this.labelDebitEntrantGet = new JLabel("" + debitEntrant);
		this.labelDebitEntrantGet.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelDebitEntrantGet.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelDebitEntrantGet.setBounds(391, 217, 114, 30);
		this.getContentPane().add(this.labelDebitEntrantGet);

		this.centerFenetre();
	}

	/**
	 * Centre la fenêtre au centre de l'écran
	 */
	private void centerFenetre() {
		Toolkit tool = this.getToolkit();
		int largeurEcran = (int) tool.getScreenSize().getWidth();
		int hauteurEcran = (int) tool.getScreenSize().getHeight();
		this.pack();
		this.setLocation((largeurEcran - this.getWidth()) / 2, (hauteurEcran - this.getHeight()) / 2);
	}

	public static FenetreWifiDetector getInstance() {
		if (instance == null) {
			FenetreWifiDetector.instance = new FenetreWifiDetector();
		}
		return FenetreWifiDetector.instance;
	}
}
