package fr.eseo.jic.wifidetector.modele;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.eseo.jic.wifidetector.modele.math.SurfaceRectangulaire;

public class SalleTest {

	@Test
	public void largeurNegativeTest() throws Exception {
		Salle salle = new Salle();
		try{
			salle.setLargeur(-1);
		}
		catch(Exception e){
			assertEquals(SurfaceRectangulaire.MSG_LARGEUR_NEGATIVE, e.getMessage().trim());
		}
	}

	@Test
	public void hauteurNegativeTest() throws Exception {
		Salle salle = new Salle();
		try{
			salle.setHauteur(-1);
		}
		catch(Exception e){
			assertEquals(SurfaceRectangulaire.MSG_HAUTEUR_NEGATIVE, e.getMessage().trim());
		}
	}
}
