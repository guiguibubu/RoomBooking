package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Font;

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
	private JButton btnFermer;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;

	public static final String WIFI_ETUDIANT = "Wifi_etudiants";

	public static FenetreWifiDetector instance;
	private JLabel labelMoyenneDebitDescendant;
	private JLabel labelSSIDGet;
	private JLabel labelQualiteSignalGet;
	private JLabel labelDebitEntrantGet;
	private JLabel labelMoyenneDebitDescendantGet;

	private FenetreWifiDetector() {

		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setBounds(300, 300, 710, 320);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Informations sur le réseau");
		// Label texte titre
		this.labelInfoReseau = new JLabel("Informations sur le réseau:");
		this.labelInfoReseau.setForeground(Color.BLUE);
		this.labelInfoReseau.setFont(new Font("Helvetica", Font.PLAIN, 38));
		this.labelInfoReseau.setBounds(35, 0, 608, 46);
		this.getContentPane().add(this.labelInfoReseau);
		// Label texte SSID
		this.labelSSID = new JLabel("Nom du réseau:");
		this.labelSSID.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.labelSSID.setBounds(35, 105, 152, 29);
		this.getContentPane().add(this.labelSSID);
		// Label texte Qualite signal
		this.lblQualiteSignal = new JLabel("Qualité du signal (en %):");
		this.lblQualiteSignal.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.lblQualiteSignal.setBounds(35, 139, 222, 29);
		this.getContentPane().add(this.lblQualiteSignal);
		// Label texte Debit entrant
		JLabel labelDebitEntrant = new JLabel("Débit entrant (en kb):");
		labelDebitEntrant.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelDebitEntrant.setBounds(35, 168, 222, 29);
		this.getContentPane().add(labelDebitEntrant);

		// Label texte Moyenne débit descendant
		this.labelMoyenneDebitDescendant = new JLabel("Moyenne débit descendant (en kb): ");
		this.labelMoyenneDebitDescendant.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.labelMoyenneDebitDescendant.setBounds(35, 198, 320, 29);
		this.getContentPane().add(this.labelMoyenneDebitDescendant);
		// Label version en bas
		this.lblVersion = new JLabel("Version 1.0 2018. Tous droits réservés.");
		this.lblVersion.setFont(new Font("Helvetica", Font.PLAIN, 13));
		this.lblVersion.setBounds(224, 271, 229, 22);
		this.getContentPane().add(this.lblVersion);

		// Boutton fermer fenetre
		this.btnFermer = new JButton(new ActionMenu());
		this.btnFermer.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnFermer.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.btnFermer.setBounds(638, 249, 66, 44);
		this.btnFermer.setText("Fermer");
		this.btnFermer.setActionCommand(ActionMenu.NOM_ACTION_FERMER);
		this.getContentPane().add(this.btnFermer);

		//		System.out.println("Signal : " + MesureWifi.getSignal("Livebox-jeroboam"));
		//		System.out.println("Signal : " + MesureWifi.getSignal(MesureWifi.getCurrentSsid()));
		System.out.println("Signal : " + MesureWifi.getSignal(WIFI_ETUDIANT));
		//		String nomSignal = MesureWifi.getCurrentSsid();
		String nomSignal = WIFI_ETUDIANT;
		this.labelSSIDGet = new JLabel(nomSignal);
		this.labelSSIDGet.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.labelSSIDGet.setBounds(389, 105, 152, 29);
		this.getContentPane().add(this.labelSSIDGet);

		//		int qualiteSignal = MesureWifi.getSignal("Livebox-jeroboam");
		//		int qualiteSignal = MesureWifi.getSignal(MesureWifi.getCurrentSsid());
		int qualiteSignal = MesureWifi.getSignal(WIFI_ETUDIANT);
		this.labelQualiteSignalGet = new JLabel("" + qualiteSignal);
		this.labelQualiteSignalGet.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.labelQualiteSignalGet.setBounds(389, 139, 114, 29);
		this.getContentPane().add(this.labelQualiteSignalGet);

		int debitEntrant = 0;
		try {
			debitEntrant = MesureWifi.getSpeedDownFixedTime();
		} catch (NoConnectedWifi e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.labelDebitEntrantGet = new JLabel("" + debitEntrant);
		this.labelDebitEntrantGet.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.labelDebitEntrantGet.setBounds(389, 168, 114, 29);
		this.getContentPane().add(this.labelDebitEntrantGet);

		int moyenneDebitDescendantGet = 0;
		try {
			moyenneDebitDescendantGet = MesureWifi.getAverageSpeed(1);
		} catch (NoConnectedWifi e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.labelMoyenneDebitDescendantGet = new JLabel("" + moyenneDebitDescendantGet);
		this.labelMoyenneDebitDescendantGet.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.labelMoyenneDebitDescendantGet.setBounds(389, 198, 114, 29);
		this.getContentPane().add(this.labelMoyenneDebitDescendantGet);

	}

	public static FenetreWifiDetector getInstance() {
		if (instance == null) {
			FenetreWifiDetector.instance = new FenetreWifiDetector();
		}
		return FenetreWifiDetector.instance;
	}
}
