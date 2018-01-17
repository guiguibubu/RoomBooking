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
		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setBounds(300, 300, 650, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle(TITRE_PAR_DEFAUT);

		this.btnInfoReseau = new JButton(new ActionMenu());
		this.btnInfoReseau.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnInfoReseau.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.btnInfoReseau.setForeground(Color.BLACK);
		this.btnInfoReseau.setBounds(16, 152, 273, 45);
		this.btnInfoReseau.setActionCommand(ActionMenu.NOM_ACTION_INFO_RESEAU);
		this.btnInfoReseau.setText("Obtenir informations sur le réseau");
		this.getContentPane().add(this.btnInfoReseau);

		this.btnCartographier = new JButton(new ActionMenu());
		this.btnCartographier.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnCartographier.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.btnCartographier.setBounds(454, 152, 171, 45);
		this.btnCartographier.setActionCommand(ActionMenu.NOM_ACTION_CARTOGRAPHIER);
		this.btnCartographier.setText("Cartographier la salle");
		this.btnCartographier.setBackground(Color.BLACK);
		this.getContentPane().add(this.btnCartographier);

		this.btnParametre = new JButton(new ActionMenu());
		this.btnParametre.setActionCommand(ActionMenu.NOM_ACTION_SETTING);
		this.btnParametre.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnParametre.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.btnParametre.setBounds(16, 209, 98, 45);
		this.btnParametre.setText("Paramètres");
		this.getContentPane().add(this.btnParametre);

		this.btnQuitter = new JButton(new ActionMenu());
		this.btnQuitter.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnQuitter.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.btnQuitter.setBounds(564, 209, 61, 44);
		this.btnQuitter.setText("Quitter");
		this.btnQuitter.setActionCommand(ActionMenu.NOM_ACTION_CLOSE);
		this.getContentPane().add(this.btnQuitter);

		this.labelImageFondMenu = new JLabel("");
		this.labelImageFondMenu
		.setIcon(new ImageIcon("/Users/dimitrijarneau/workspace/WifiDetection/img/FenetreAccueil/wifi-4.png"));
		this.labelImageFondMenu.setBounds(70, 6, 70, 64);
		this.getContentPane().add(this.labelImageFondMenu);

		this.labelBasFenetre = new JLabel("Version 1.0 2018. Tous droits réservés.");
		this.labelBasFenetre.setFont(new Font("Helvetica", Font.PLAIN, 13));
		this.labelBasFenetre.setBounds(250, 250, 229, 22);
		this.getContentPane().add(this.labelBasFenetre);

		this.labelTextBehindTittle = new JLabel(
				"Cette application permet de cartographier la qualité du réseau wifi dans une salle.");
		this.labelTextBehindTittle.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelTextBehindTittle.setForeground(Color.BLACK);
		this.labelTextBehindTittle.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.labelTextBehindTittle.setBackground(Color.WHITE);
		this.labelTextBehindTittle.setBounds(6, 68, 633, 72);
		this.getContentPane().add(this.labelTextBehindTittle);

		this.labelTitreFenetreMenu = new JLabel("WifiDetector");
		this.labelTitreFenetreMenu.setForeground(Color.BLUE);
		this.labelTitreFenetreMenu.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelTitreFenetreMenu.setText("WifiDetector");
		this.labelTitreFenetreMenu.setFont(new Font("Helvetica", Font.PLAIN, 60));
		this.labelTitreFenetreMenu.setBounds(191, 6, 448, 72);
		this.labelTitreFenetreMenu.setBackground(Color.WHITE);
		this.getContentPane().add(this.labelTitreFenetreMenu);

	}

	public static FenetreMenu getInstance() {
		if (instance == null) {
			FenetreMenu.instance = new FenetreMenu();
		}
		return FenetreMenu.instance;
	}
}
