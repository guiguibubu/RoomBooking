package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.eseo.jic.wifidetector.modele.MesureWifiModel;
import fr.eseo.jic.wifidetector.vue.forme.VueSalle;

public class FenetreCartographieWifi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel labelInfoReseau;
	private JLabel lblVersion;
	private JButton btnFermer;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;

	private static final int LARGEUR_FENETRE_MIN = 800;
	private static final int HAUTEUR_FENETRE_MIN = 1000;

	public static FenetreCartographieWifi instance;
	private JLabel labelDimensionSalle;
	private JButton buttonAfficherResultat;
	private JLabel lblHauteurSalle;

	private VueSalle panelSalle;
	private JLabel labelLargeurSalle;
	private JTextField textFieldLargeur;
	private JTextField textFieldHauteur;
	private JLabel lblLargeur;
	private JLabel labelHauteur;
	private JButton buttonValiderDimensions;
	private JButton buttonMesurer;

	private List<MesureWifiModel> listeMesureWifiModel;

	public FenetreCartographieWifi() {

		this.listeMesureWifiModel = new ArrayList<MesureWifiModel>();
		// Création de la fenetre
		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		// this.setBounds(300, 300, 710, 580);
		this.setMinimumSize(new Dimension(LARGEUR_FENETRE_MIN, HAUTEUR_FENETRE_MIN));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Cartographie wifi");

		// Label texte
		this.labelInfoReseau = new JLabel("Cartographie du réseau wifi:");
		this.labelInfoReseau.setForeground(Color.BLUE);
		this.labelInfoReseau.setFont(new Font("Helvetica", Font.PLAIN, 40));
		this.labelInfoReseau.setBounds(126, 25, 608, 46);
		this.getContentPane().add(this.labelInfoReseau);

		// Label texte
		JLabel labelInformation = new JLabel(
				"Entrez les dimensions de la salle afin de savoir où effectuer les relevés.");
		labelInformation.setFont(new Font("Helvetica", Font.PLAIN, 19));
		labelInformation.setBounds(30, 79, 648, 29);
		this.getContentPane().add(labelInformation);

		// Label texte dimension de la salle
		this.labelDimensionSalle = new JLabel("Dimension de la salle:");
		this.labelDimensionSalle.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.labelDimensionSalle.setBounds(30, 120, 189, 29);
		this.getContentPane().add(this.labelDimensionSalle);

		// Label version en bas
		this.lblVersion = new JLabel("Version 1.0 2018. Tous droits réservés.");
		this.lblVersion.setFont(new Font("Helvetica", Font.PLAIN, 13));
		this.lblVersion.setBounds(297, 930, 229, 22);
		this.getContentPane().add(this.lblVersion);

		this.lblLargeur = new JLabel("Largeur (en m):");
		this.lblLargeur.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.lblLargeur.setBounds(231, 120, 148, 29);
		this.getContentPane().add(this.lblLargeur);

		this.labelHauteur = new JLabel("Hauteur (en m):");
		this.labelHauteur.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.labelHauteur.setBounds(231, 157, 120, 29);
		this.getContentPane().add(this.labelHauteur);

		// Boutton fermer fenetre
		this.btnFermer = new JButton(new ActionMenu());
		this.btnFermer.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.btnFermer.setBounds(667, 883, 87, 40);
		this.btnFermer.setText("Retour");
		this.btnFermer.setActionCommand(ActionMenu.NOM_ACTION_RETOUR_MENU);
		this.getContentPane().add(this.btnFermer);

		this.buttonValiderDimensions = new JButton(new ActionMenu());
		this.buttonValiderDimensions.setText("Valider");
		this.buttonValiderDimensions.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.buttonValiderDimensions.setActionCommand(ActionMenu.NOM_ACTION_VALIDER_DIMENSION);
		this.buttonValiderDimensions.setBounds(545, 120, 189, 51);
		this.getContentPane().add(this.buttonValiderDimensions);

		// Boutton exporter résultat
		// Pas de commande attribuée pour le moment
		this.buttonAfficherResultat = new JButton(new ActionMenu());
		this.buttonAfficherResultat.setText("Afficher résultat");
		this.buttonAfficherResultat.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.buttonAfficherResultat.setActionCommand(ActionMenu.NOM_ACTION_AFFICHER_RESULTAT);
		this.buttonAfficherResultat.setBounds(30, 883, 189, 40);
		this.getContentPane().add(this.buttonAfficherResultat);

		// label texte
		this.labelLargeurSalle = new JLabel("Largeur");
		this.labelLargeurSalle.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelLargeurSalle.setFont(new Font("Helvetica", Font.PLAIN, 20));
		this.labelLargeurSalle.setBounds(370, 846, 93, 34);
		this.getContentPane().add(this.labelLargeurSalle);

		this.lblHauteurSalle = new JLabel("Hauteur");
		this.lblHauteurSalle.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblHauteurSalle.setBounds(20, 500, 82, 29);
		this.getContentPane().add(this.lblHauteurSalle);
		this.lblHauteurSalle.setFont(new Font("Helvetica", Font.PLAIN, 20));

		// Champ pour remplir inofrmations sur la salle
		this.textFieldLargeur = new JTextField();
		this.textFieldLargeur.setBounds(381, 120, 66, 26);
		this.getContentPane().add(this.textFieldLargeur);
		this.textFieldLargeur.setColumns(10);

		this.textFieldHauteur = new JTextField();
		this.textFieldHauteur.setColumns(10);
		this.textFieldHauteur.setBounds(381, 158, 66, 26);
		this.getContentPane().add(this.textFieldHauteur);

		// Ajout du rectangle dans le panel crée specialement pour ca
		this.panelSalle = new VueSalle();
		this.panelSalle.setBounds(114, 214, 620, 620);
		this.panelSalle.setBackground(COLOR_PAR_DEFAUT);
		this.getContentPane().add(this.panelSalle);

		ActionListenerEnter actionListener = new ActionListenerEnter(this.panelSalle, this.textFieldLargeur, this.textFieldHauteur);
		this.textFieldLargeur.addActionListener(actionListener);
		this.textFieldHauteur.addActionListener(actionListener);

		// Bouttons pour mesurer
		this.buttonMesurer = new JButton(new ActionMenu());
		this.buttonMesurer.setText("Mesurer");
		this.buttonMesurer.setFont(new Font("Helvetica", Font.PLAIN, 17));
		this.buttonMesurer.setActionCommand(ActionMenu.NOM_ACTION_ACQUERIR_INFO_RESEAUX);
		this.buttonMesurer.setBounds(360, 883, 110, 40);
		this.getContentPane().add(this.buttonMesurer);

		// Bouttons pour terminer les mesures

		// on centre la fenetre
		Toolkit tool = this.getToolkit();
		int largeurEcran = (int) tool.getScreenSize().getWidth();
		int hauteurEcran = (int) tool.getScreenSize().getHeight();
		this.pack();
		this.setLocation((largeurEcran - this.getWidth()) / 2, (hauteurEcran - this.getHeight()) / 2);
	}

	public static FenetreCartographieWifi getInstance() {
		if (instance == null) {
			FenetreCartographieWifi.instance = new FenetreCartographieWifi();
		}
		return FenetreCartographieWifi.instance;
	}

	class ActionListenerEnter implements ActionListener {
		VueSalle vueSalle;
		JTextField textFieldLargeur;
		JTextField textFieldHauteur;

		protected ActionListenerEnter(VueSalle vueSalle, JTextField textFieldLargeur, JTextField textFieldHauteur) {
			super();
			this.vueSalle = vueSalle;
			this.textFieldLargeur = textFieldLargeur;
			this.textFieldHauteur = textFieldHauteur;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String largeurStr = this.textFieldLargeur.getText();
			String hauteurStr = this.textFieldHauteur.getText();
			try {
				int largeur = new Integer(largeurStr);
				int hauteur = new Integer(hauteurStr);
				this.vueSalle.getSalle().setHauteur(hauteur);
				this.vueSalle.getSalle().setLargeur(largeur);
				this.vueSalle.getSalle().calculListePointMesure();
				this.vueSalle.repaint();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Merci de saisir des chiffres", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
	}

	public VueSalle getPanelSalle() {return this.panelSalle;}
	public void setPanelSalle(VueSalle panelSalle) {this.panelSalle = panelSalle;}

	public JTextField getTextFieldLargeur() {return this.textFieldLargeur;}
	public void setTextFieldLargeur(JTextField textFieldLargeur) {this.textFieldLargeur = textFieldLargeur;}

	public JTextField getTextFieldHauteur() {return this.textFieldHauteur;}
	public void setTextFieldHauteur(JTextField textFieldHauteur) {this.textFieldHauteur = textFieldHauteur;}

	public List<MesureWifiModel> getListeMesureWifiModel() {return this.listeMesureWifiModel;}
	public void setListeMesureWifiModel(List<MesureWifiModel> listeMesureWifiModel) {this.listeMesureWifiModel = listeMesureWifiModel;}
}
