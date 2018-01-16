package fr.eseo.jic.wifidetector.vue.forme;

import java.awt.Graphics;

import javax.swing.JPanel;

public class VueSalle extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String name = "Porte";
	
	public void paintComponent (Graphics g){
		//Dessin du rectangle representant la salle
		g.drawRect(30, 10, 500, 300);
		System.out.println("Salle faite");
		
		//Dessin du restangle représentant la porte 
		g.drawRect(80, 295, 50, 10);
		g.drawString(name, 80, 290);
		System.out.println("Porte faite");

	}
}
