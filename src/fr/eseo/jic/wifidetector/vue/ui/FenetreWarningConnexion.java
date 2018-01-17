package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.eseo.jic.wifidetector.controller.MesureWifi;
import fr.eseo.jic.wifidetector.controller.NoConnectedWifi;

public class FenetreWarningConnexion extends JFrame {
	/**
	 * Pop up warning si connecté aucun réseau 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelInfoReseau;
	private JLabel labelInfoWarning;
	private JLabel labelSSID;
	private JButton btnFermer;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;

	public static FenetreWarningConnexion instance;
	private JLabel labelSSIDGet;
	private JLabel labelDebitEntrantGet;

	private FenetreWarningConnexion() {

		// Création de la fenetre 
		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setBounds(300, 300, 710, 300);
		//Commande qui ferme tout le programme 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Warning");

		// Label texte 
		labelInfoReseau = new JLabel("Warning");
		labelInfoReseau.setForeground(Color.BLUE);
		labelInfoReseau.setFont(new Font("Helvetica", Font.PLAIN, 38));
		labelInfoReseau.setBounds(284, 6, 152, 46);
		this.getContentPane().add(labelInfoReseau);

		labelInfoWarning = new JLabel("Vous n'etes pas connecté au réseau.");
		labelInfoWarning.setForeground(Color.BLUE);
		labelInfoWarning.setFont(new Font("Helvetica", Font.PLAIN, 38));
		labelInfoWarning.setBounds(29, 47, 675, 46);
		this.getContentPane().add(labelInfoWarning);

		// Label texte SSID
		labelSSID = new JLabel("Nom du réseau:");
		labelSSID.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelSSID.setBounds(31, 141, 152, 29);
		this.getContentPane().add(labelSSID);
		
		JLabel lblVeuillezVousConnecter = new JLabel("Veuillez vous connecter à un réseau et ensuite relancer le programme.");
		lblVeuillezVousConnecter.setForeground(Color.BLACK);
		lblVeuillezVousConnecter.setFont(new Font("Helvetica", Font.PLAIN, 20));
		lblVeuillezVousConnecter.setBounds(29, 88, 658, 46);
		getContentPane().add(lblVeuillezVousConnecter);

		// Boutton fermer fenetre
		btnFermer = new JButton(new ActionMenu());
		btnFermer.setHorizontalAlignment(SwingConstants.LEFT);
		btnFermer.setFont(new Font("Helvetica", Font.PLAIN, 17));
		btnFermer.setBounds(591, 228, 96, 44);
		btnFermer.setText("Fermer");
		btnFermer.setActionCommand(ActionMenu.NOM_ACTION_CLOSE);
		this.getContentPane().add(btnFermer);


		System.out.println("Signal : " + MesureWifi.getSignal("WIFI_ETUDIANTS"));
		String nomSignal = MesureWifi.getCurrentSsid();
		labelSSIDGet = new JLabel(nomSignal);
		labelSSIDGet.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelSSIDGet.setBounds(399, 141, 152, 29);
		getContentPane().add(labelSSIDGet);

	}

	public static FenetreWarningConnexion getInstance() {
		if (instance == null) {
			FenetreWarningConnexion.instance = new FenetreWarningConnexion();
		}
		return FenetreWarningConnexion.instance;
	}
}
