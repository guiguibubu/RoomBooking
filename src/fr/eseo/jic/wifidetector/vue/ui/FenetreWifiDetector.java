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

	public static FenetreWifiDetector instance;
	private JLabel labelMoyenneDebitDescendant;
	private JLabel labelSSIDGet;
	private JLabel labelQualiteSignalGet;
	private JLabel labelDebitEntrantGet;
	private JLabel labelMoyenneDebitDescendantGet;

	private FenetreWifiDetector() {

		this.setResizable(false);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setBounds(300, 300, 710, 320);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Informations sur le réseau");
		// Label texte titre
		labelInfoReseau = new JLabel("Informations sur le réseau:");
		labelInfoReseau.setForeground(Color.BLUE);
		labelInfoReseau.setFont(new Font("Helvetica", Font.PLAIN, 38));
		labelInfoReseau.setBounds(35, 0, 608, 46);
		this.getContentPane().add(labelInfoReseau);
		// Label texte SSID
		labelSSID = new JLabel("Nom du réseau:");
		labelSSID.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelSSID.setBounds(35, 105, 152, 29);
		this.getContentPane().add(labelSSID);
		// Label texte Qualite signal
		lblQualiteSignal = new JLabel("Qualité du signal (en %):");
		lblQualiteSignal.setFont(new Font("Helvetica", Font.PLAIN, 19));
		lblQualiteSignal.setBounds(35, 139, 222, 29);
		this.getContentPane().add(lblQualiteSignal);
		// Label texte Debit entrant
		JLabel labelDebitEntrant = new JLabel("Débit entrant (en kb):");
		labelDebitEntrant.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelDebitEntrant.setBounds(35, 168, 222, 29);
		getContentPane().add(labelDebitEntrant);

		// Label texte Moyenne débit descendant
		labelMoyenneDebitDescendant = new JLabel("Moyenne débit descendant (en kb): ");
		labelMoyenneDebitDescendant.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelMoyenneDebitDescendant.setBounds(35, 198, 320, 29);
		getContentPane().add(labelMoyenneDebitDescendant);
		// Label version en bas
		lblVersion = new JLabel("Version 1.0 2018. Tous droits réservés.");
		lblVersion.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblVersion.setBounds(224, 271, 229, 22);
		this.getContentPane().add(lblVersion);

		// Boutton fermer fenetre
		btnFermer = new JButton(new ActionMenu());
		btnFermer.setHorizontalAlignment(SwingConstants.LEFT);
		btnFermer.setFont(new Font("Helvetica", Font.PLAIN, 17));
		btnFermer.setBounds(638, 249, 66, 44);
		btnFermer.setText("Fermer");
		btnFermer.setActionCommand(ActionMenu.NOM_ACTION_FERMER);
		this.getContentPane().add(btnFermer);

		System.out.println("Signal : " + MesureWifi.getSignal("Livebox-jeroboam"));
		String nomSignal = MesureWifi.getCurrentSsid();
		labelSSIDGet = new JLabel(nomSignal);
		labelSSIDGet.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelSSIDGet.setBounds(389, 105, 152, 29);
		getContentPane().add(labelSSIDGet);

		int qualiteSignal = MesureWifi.getSignal("Livebox-jeroboam");
		labelQualiteSignalGet = new JLabel("" + qualiteSignal);
		labelQualiteSignalGet.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelQualiteSignalGet.setBounds(389, 139, 114, 29);
		getContentPane().add(labelQualiteSignalGet);

		int debitEntrant = 0;
		try {
			debitEntrant = MesureWifi.getSpeedDownFixedTime();
		} catch (NoConnectedWifi e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		labelDebitEntrantGet = new JLabel("" + debitEntrant);
		labelDebitEntrantGet.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelDebitEntrantGet.setBounds(389, 168, 114, 29);
		getContentPane().add(labelDebitEntrantGet);

		int moyenneDebitDescendantGet = 0;
		try {
			moyenneDebitDescendantGet = MesureWifi.getAverageSpeed(1);
		} catch (NoConnectedWifi e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		labelMoyenneDebitDescendantGet = new JLabel("" + moyenneDebitDescendantGet);
		labelMoyenneDebitDescendantGet.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelMoyenneDebitDescendantGet.setBounds(389, 198, 114, 29);
		getContentPane().add(labelMoyenneDebitDescendantGet);

	}

	public static FenetreWifiDetector getInstance() {
		if (instance == null) {
			FenetreWifiDetector.instance = new FenetreWifiDetector();
		}
		return FenetreWifiDetector.instance;
	}
}
