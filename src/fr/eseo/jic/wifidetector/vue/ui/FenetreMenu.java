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

public class FenetreMenu extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;

	private static final int LARGEUR_FENETRE_MIN = 650;
	private static final int HAUTEUR_FENETRE_MIN = 400;

	private static FenetreMenu instance;

	private JLabel labelImageFondMenu;
	private JLabel labelBasFenetre;

	private JButton btnInfoReseau;
	private JButton btnCartographier;
	private JButton btnQuitter;
	private JLabel labelTitreFenetreMenu;
	private JLabel labelTextBehindTittle;

	private FenetreMenu() {
		// Création de la fenetre
		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setMinimumSize(new Dimension(LARGEUR_FENETRE_MIN, HAUTEUR_FENETRE_MIN));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle(TITRE_PAR_DEFAUT);

		// Création du bouton pour aller sur la fenetre info réseau
		this.btnInfoReseau = new JButton(new ActionMenu());
		this.btnInfoReseau.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		this.btnInfoReseau.setForeground(Color.BLACK);
		this.btnInfoReseau.setBounds(25, 209, 299, 72);
		this.btnInfoReseau.setActionCommand(ActionMenu.NOM_ACTION_INFO_RESEAU);
		this.btnInfoReseau.setText("Obtenir informations sur le réseau");
		this.getContentPane().add(this.btnInfoReseau);

		// Création du bouton pour aller sur la fenetre cartographier
		this.btnCartographier = new JButton(new ActionMenu());
		this.btnCartographier.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		this.btnCartographier.setBounds(395, 209, 229, 72);
		this.btnCartographier.setActionCommand(ActionMenu.NOM_ACTION_CARTOGRAPHIER);
		this.btnCartographier.setText("Cartographier la salle");
		this.btnCartographier.setBackground(Color.BLACK);
		this.getContentPane().add(this.btnCartographier);

		// Création du bouton pour quitter l'appli
		this.btnQuitter = new JButton(new ActionMenu());
		this.btnQuitter.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		this.btnQuitter.setBounds(540, 301, 89, 61);
		this.btnQuitter.setText("Quitter");
		this.btnQuitter.setActionCommand(ActionMenu.NOM_ACTION_CLOSE);
		this.getContentPane().add(this.btnQuitter);

		// Ajout de l'image
		this.labelImageFondMenu = new JLabel("");
		// this.labelImageFondMenu.setIcon(new
		// ImageIcon("/Users/dimitrijarneau/workspace/WifiDetection/img/FenetreAccueil/wifi-4.png"));
		this.labelImageFondMenu.setIcon(new ImageIcon(getClass().getResource("/wifi-5.png")));
		this.labelImageFondMenu.setBounds(25, 25, 135, 93);
		this.getContentPane().add(this.labelImageFondMenu);

		// Texte
		this.labelBasFenetre = new JLabel("Version 1.0 2018. Tous droits réservés.");
		this.labelBasFenetre.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
		this.labelBasFenetre.setBounds(230, 340, 229, 22);
		this.getContentPane().add(this.labelBasFenetre);

		// Texte description
		this.labelTextBehindTittle = new JLabel(
				"<html>Cette application permet d'obtenir des informations sur le réseau auquel vous etes connecté ou alors de cartographier la qualité du réseau wifi dans une salle.</html>");
		labelTextBehindTittle.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelTextBehindTittle.setForeground(Color.BLACK);
		this.labelTextBehindTittle.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		this.labelTextBehindTittle.setBackground(Color.WHITE);
		this.labelTextBehindTittle.setBounds(25, 125, 604, 102);
		this.getContentPane().add(this.labelTextBehindTittle);

		// Titre
		this.labelTitreFenetreMenu = new JLabel("WifiDetector");
		this.labelTitreFenetreMenu.setForeground(Color.BLUE);
		this.labelTitreFenetreMenu.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelTitreFenetreMenu.setText("WifiDetector");
		this.labelTitreFenetreMenu.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 50));
		this.labelTitreFenetreMenu.setBounds(172, 46, 448, 72);
		this.labelTitreFenetreMenu.setBackground(Color.WHITE);
		this.getContentPane().add(this.labelTitreFenetreMenu);

		// on centre la fenetre
		Toolkit tool = this.getToolkit();
		int largeurEcran = (int) tool.getScreenSize().getWidth();
		int hauteurEcran = (int) tool.getScreenSize().getHeight();
		this.pack();
		this.setLocation((largeurEcran - this.getWidth()) / 2, (hauteurEcran - this.getHeight()) / 2);

	}

	public static FenetreMenu getInstance() {
		if (instance == null) {
			FenetreMenu.instance = new FenetreMenu();
		}
		return FenetreMenu.instance;
	}
}
