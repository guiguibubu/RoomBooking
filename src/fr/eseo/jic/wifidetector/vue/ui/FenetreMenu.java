package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Font;

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
	private static FenetreMenu instance;

	private JLabel labelImageFondMenu;
	private JLabel labelBasFenetre;

	private JButton btnInfoReseau;
	private JButton btnCartographier;
	private JButton btnParametre;
	private JButton btnQuitter;
	private JLabel labelTitreFenetreMenu;
	private JLabel labelTextBehindTittle;

	private FenetreMenu() {
		this.setResizable(false);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setBounds(300, 300, 650, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle(TITRE_PAR_DEFAUT);

		btnInfoReseau = new JButton(new ActionMenu());
		btnInfoReseau.setHorizontalAlignment(SwingConstants.LEFT);
		btnInfoReseau.setFont(new Font("Helvetica", Font.PLAIN, 17));
		btnInfoReseau.setForeground(Color.BLACK);
		btnInfoReseau.setBounds(16, 152, 273, 45);
		btnInfoReseau.setActionCommand(ActionMenu.NOM_ACTION_INFO_RESEAU);
		btnInfoReseau.setText("Obtenir informations sur le réseau");
		this.getContentPane().add(btnInfoReseau);

		btnCartographier = new JButton(new ActionMenu());
		btnCartographier.setHorizontalAlignment(SwingConstants.LEFT);
		btnCartographier.setFont(new Font("Helvetica", Font.PLAIN, 17));
		btnCartographier.setBounds(454, 152, 171, 45);
		btnCartographier.setActionCommand(ActionMenu.NOM_ACTION_CARTOGRAPHIER);
		btnCartographier.setText("Cartographier la salle");
		btnCartographier.setBackground(Color.BLACK);
		this.getContentPane().add(btnCartographier);

		btnParametre = new JButton(new ActionMenu());
		btnParametre.setActionCommand(ActionMenu.NOM_ACTION_SETTING);
		btnParametre.setHorizontalAlignment(SwingConstants.LEFT);
		btnParametre.setFont(new Font("Helvetica", Font.PLAIN, 17));
		btnParametre.setBounds(16, 209, 98, 45);
		btnParametre.setText("Paramètres");
		this.getContentPane().add(btnParametre);

		btnQuitter = new JButton(new ActionMenu());
		btnQuitter.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuitter.setFont(new Font("Helvetica", Font.PLAIN, 17));
		btnQuitter.setBounds(564, 209, 61, 44);
		btnQuitter.setText("Quitter");
		btnQuitter.setActionCommand(ActionMenu.NOM_ACTION_CLOSE);
		this.getContentPane().add(btnQuitter);

		labelImageFondMenu = new JLabel("");
		labelImageFondMenu
				.setIcon(new ImageIcon("/Users/dimitrijarneau/workspace/WifiDetection/img/FenetreAccueil/wifi-4.png"));
		labelImageFondMenu.setBounds(70, 6, 70, 64);
		this.getContentPane().add(labelImageFondMenu);

		labelBasFenetre = new JLabel("Version 1.0 2018. Tous droits réservés.");
		labelBasFenetre.setFont(new Font("Helvetica", Font.PLAIN, 13));
		labelBasFenetre.setBounds(250, 250, 229, 22);
		this.getContentPane().add(labelBasFenetre);

		labelTextBehindTittle = new JLabel(
				"Cette application permet de cartographier la qualité du réseau wifi dans une salle.");
		labelTextBehindTittle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTextBehindTittle.setForeground(Color.BLACK);
		labelTextBehindTittle.setFont(new Font("Helvetica", Font.PLAIN, 17));
		labelTextBehindTittle.setBackground(Color.WHITE);
		labelTextBehindTittle.setBounds(6, 68, 633, 72);
		getContentPane().add(labelTextBehindTittle);

		labelTitreFenetreMenu = new JLabel("WifiDetector");
		labelTitreFenetreMenu.setForeground(Color.BLUE);
		labelTitreFenetreMenu.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitreFenetreMenu.setText("WifiDetector");
		labelTitreFenetreMenu.setFont(new Font("Helvetica", Font.PLAIN, 60));
		labelTitreFenetreMenu.setBounds(191, 6, 448, 72);
		labelTitreFenetreMenu.setBackground(Color.WHITE);
		this.getContentPane().add(labelTitreFenetreMenu);

	}

	public static FenetreMenu getInstance() {
		if (instance == null) {
			FenetreMenu.instance = new FenetreMenu();
		}
		return FenetreMenu.instance;
	}
}
