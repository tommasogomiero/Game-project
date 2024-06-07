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
    }
    
    @Test
    public void testSpostareBersaglio() {
    	game1.spostareBersaglio(Direzione.DESTRA);
		System.out.println(game1.getBersaglio() + "\n");
		assertEquals(game1.getBersaglio(), new Posizione(1,0));
    }
    
    @Test
    public void testSpostareBersaglioUlt() {
    	game1.spostareBersaglioUlt();
		System.out.println(game1.getBersaglio() + "\n");
		assertEquals(game1.getBersaglio(), new Posizione(0,0));
    }
    
    @Test
    public void win() {
    	game1.win();
    	assertFalse(game1.isInGioco());
    }
    
    @Test
    public void exit() {
    	game2.exit();
	System.out.println(game2.isInGioco() + "\n");
	assertFalse(game2.isInGioco());
    }
}
