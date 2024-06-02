package topi.prove;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import topi.strutture.PannelloBase;
import topi.strutture.Stato;

public class PannelloBaseTest {

	private static PannelloBase pannello;

    @BeforeClass
    public static void setUp() {
    	pannello = new PannelloBase(0,0);
    }

    @Test
    public void testSollevato() {
    	pannello.mostra();
    	assertEquals(pannello.getStato(), Stato.SOLLEVATO);
    }
    
    @Test
    public void testAbbattuto() {
    	pannello.abbattere();
		pannello.mostra();
		assertEquals(pannello.getStato(), Stato.ABBATTUTO);
    }
}
