package fr.eseo.jic.wifidetector.modele;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.eseo.jic.wifidetector.modele.math.SurfaceRectangulaire;

public class SalleTest {

	@Test
	public void largeurNegativeTest() throws Exception {
		Salle salle = new Salle();
		try{
			salle.setLargeur(-1);
			Assert.fail("L'exception ne s'est pas levée");
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
			Assert.fail("L'exception ne s'est pas levée");
		}
		catch(Exception e){
			assertEquals(SurfaceRectangulaire.MSG_HAUTEUR_NEGATIVE, e.getMessage().trim());
		}
	}
	
	@Test
	public void calculListePointMesureTest() throws Exception {
		Salle salle = new Salle(16,16);
		List<ZoneMesure> zoneMesures = salle.getListeZoneMesure();
		if(zoneMesures.size() == 4) 
		{
			verifierZoneMesure(zoneMesures.get(0), new ZoneMesure(0, 0, 8, 8));
			verifierZoneMesure(zoneMesures.get(1), new ZoneMesure(8, 0, 8, 8));
			verifierZoneMesure(zoneMesures.get(2), new ZoneMesure(0, 8, 8, 8));
			verifierZoneMesure(zoneMesures.get(3), new ZoneMesure(8, 8, 8, 8));
		}
		else
		{
			Assert.fail("Mauvaise taille de ZoneMesure : " + zoneMesures.size() + " au lieu de 4.");
		}
		
	}
	
	public void verifierZoneMesure(ZoneMesure expected, ZoneMesure actual)
	{
		assertEquals(actual.getHauteur(), expected.getHauteur());
		assertEquals(actual.getLargeur(), expected.getLargeur());
		assertEquals(actual.getX(), expected.getX());
		assertEquals(actual.getY(), expected.getY());
	}
}
