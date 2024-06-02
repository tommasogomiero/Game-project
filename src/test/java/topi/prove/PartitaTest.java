package topi.prove;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import topi.gioco.Partita;
import topi.strutture.Direzione;
import topi.strutture.Posizione;

public class PartitaTest {

	private static Partita game1;
	private static Partita game2;

    @BeforeClass
    public static void setUp() {
    	game1 = new Partita(5,5);
    	game2 = new Partita(5,5);
    }

    @Test
    public void testAvviare() {
	System.out.println(game1.isInGioco());
	assertTrue(game1.isInGioco());
	//System.out.println("secondi = " + game1.getSecRimanenti());
	//assertEquals(game1.getSecRimanenti(), 60);
	//System.out.println("tiri = " + game1.getColpiRimanenti());
	//assertEquals(game1.getColpiRimanenti(), 3);
	//System.out.println("punti = " + game1.getPunti() + "\n");
	//assertEquals(game1.getPunti(), 0);
    }
    
    @Test
    public void testSppstareBersaglio() {
    	game1.spostareBersaglio(Direzione.DESTRA);
		System.out.println(game1.getBersaglio() + "\n");
		assertEquals(game1.getBersaglio(), new Posizione(1,0));
    }
    
    /*
    @Test
    public void testSecondi() {
    	long fin = System.currentTimeMillis() + 4000;
		while (System.currentTimeMillis() < fin);
		System.out.println(game1.getSecRimanenti() + "\n");
		assertEquals(game1.getSecRimanenti(), 56);
    }
    */
    
    @Test
    public void testSparare() {
    	game1.sparare();
		game1.sparare();
		//System.out.println(game1.getColpiRimanenti() + "\n");
		//assertEquals(game1.getColpiRimanenti(), 1);
    }
    
    
    @Test
    public void testIsFinita() {
    	System.out.println(game1.isInGioco() + "\n");
    	game1.exit();
    	assertFalse(game1.isInGioco());
    }
    
    @Test
    public void testIsEnJuego() {
    	game2.sparare();
    	game2.sparare();
    	game2.sparare();
	System.out.println(game2.isInGioco() + "\n");
	assertTrue(game2.isInGioco());
    }
}
