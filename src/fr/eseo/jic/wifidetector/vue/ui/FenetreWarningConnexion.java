package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.eseo.jic.wifidetector.controller.MesureWifi;

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
	private static final int LARGEUR_FENETRE_MIN = 650;
	private static final int HAUTEUR_FENETRE_MIN = 400;
	public static FenetreWarningConnexion instance;
	private JLabel labelSSIDGet;
	private JLabel labelMenuImage;

	private FenetreWarningConnexion() {

		// Création de la fenetre
		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setMinimumSize(new Dimension(LARGEUR_FENETRE_MIN, HAUTEUR_FENETRE_MIN));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Warning");

		// Label texte
		labelInfoReseau = new JLabel("Attention");
		labelInfoReseau.setForeground(Color.BLUE);
		labelInfoReseau.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 50));
		labelInfoReseau.setBounds(225, 30, 208, 46);
		this.getContentPane().add(labelInfoReseau);
		// Label Info
		labelInfoWarning = new JLabel("<html>Vous n'etes pas connecté au réseau.</html>");
		labelInfoWarning.setForeground(Color.BLUE);
		labelInfoWarning.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 30));
		labelInfoWarning.setBounds(80, 70, 503, 46);
		this.getContentPane().add(labelInfoWarning);

		this.labelMenuImage = new JLabel("");
		labelMenuImage.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelMenuImage.setForeground(Color.BLACK);
		this.labelMenuImage.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 50));
		this.labelMenuImage.setIcon(new ImageIcon(getClass().getResource("/no-wifi.png")));
		this.labelMenuImage.setBounds(250, 87, 152, 169);
		this.getContentPane().add(this.labelMenuImage);

		// Label texte SSID
		labelSSID = new JLabel("Nom du réseau:");
		labelSSID.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		labelSSID.setBounds(80, 305, 152, 29);
		this.getContentPane().add(labelSSID);

		JLabel lblVeuillezVousConnecter = new JLabel(
				"<html>Veuillez vous connecter à un réseau et ensuite relancer le programme.</html>");
		lblVeuillezVousConnecter.setForeground(Color.BLACK);
		lblVeuillezVousConnecter.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 20));
		lblVeuillezVousConnecter.setBounds(80, 235, 503, 46);
		getContentPane().add(lblVeuillezVousConnecter);

		// Boutton fermer fenetre
		btnFermer = new JButton(new ActionMenu());
		btnFermer.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		btnFermer.setBounds(521, 298, 96, 44);
		btnFermer.setText("Fermer");
		btnFermer.setActionCommand(ActionMenu.NOM_ACTION_CLOSE);
		this.getContentPane().add(btnFermer);

		System.out.println("Signal : " + MesureWifi.getSignal("WIFI_ETUDIANTS"));
		String nomSignal = MesureWifi.getCurrentSsid();
		labelSSIDGet = new JLabel(nomSignal);
		labelSSIDGet.setHorizontalAlignment(SwingConstants.CENTER);
		labelSSIDGet.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		labelSSIDGet.setBounds(310, 305, 152, 29);
		getContentPane().add(labelSSIDGet);

	}

	public static FenetreWarningConnexion getInstance() {
		if (instance == null) {
			FenetreWarningConnexion.instance = new FenetreWarningConnexion();
		}
		return FenetreWarningConnexion.instance;
	}

}
