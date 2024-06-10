package topi.prove;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

import topi.strutture.PannelloBase;
import topi.strutture.Stato;

public class PannelloBaseTest {

	private static PannelloBase pannello;
	private static PannelloBase pannello2;

    @BeforeClass
    public static void setUp() {
    	pannello = new PannelloBase(5,2);
    	pannello2 = new PannelloBase(4,2);
    }

    @Test
    public void testSollevato() {
    	pannello.mostra();
    	assertEquals(pannello.getStato(), Stato.SOLLEVATO);
    }
    
    @Test
    public void isVisibile1() {
	assertFalse(pannello.isVisibile());
    }
    
    @Test
    public void testAbbattuto() {
    	pannello.abbattere();
	pannello.mostra();
	assertEquals(pannello.getStato(), Stato.ABBATTUTO);
    }
    
    @Test
    public void isVisibile2() {
    	pannello2.abbattere();
	assertTrue(pannello2.isVisibile());
    }
}
