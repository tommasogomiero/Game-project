package topi.prove;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import topi.strutture.Direzione;
import topi.strutture.Posizione;


public class PosizioneTest {

	private static Posizione punto1;
	private static Posizione punto2;

    @BeforeClass
    public static void setUp() {
    	punto1 = new Posizione(1,3);
    	punto2 = new Posizione(1,3);
    }

    @Test
    public void testPosizione() {
    	punto1.mostra();
    	assertEquals(punto1, new Posizione(1,3));
    }
    
    @Test
    public void testVicina() {		
		punto1.getVicina(Direzione.SOTTO).mostra();
		assertEquals(punto1.getVicina(Direzione.SOTTO), new Posizione(1,2));
		punto1.getVicina(Direzione.SINISTRA).mostra();
		assertEquals(punto1.getVicina(Direzione.SINISTRA), new Posizione(0,3));
		punto1.getVicina(Direzione.SOPRA).mostra();
		assertEquals(punto1.getVicina(Direzione.SOPRA), new Posizione(1,4));
		punto1.getVicina(Direzione.DESTRA).mostra();
		assertEquals(punto1.getVicina(Direzione.DESTRA), new Posizione(2,3));
    }
    
    @Test
    public void testspostare() {
    	punto2.spostare(Direzione.SOPRA);
		punto2.mostra();
		assertEquals(punto2, new Posizione(1,4));
		
		punto2.spostare(5,2);
		punto2.mostra();
		assertEquals(punto2, new Posizione(6,6));
    }
}
