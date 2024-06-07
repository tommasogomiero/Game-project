package topi.prove;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import topi.strutture.PannelloBase;
import topi.strutture.Posizione;
import topi.strutture.Scenario;


public class ScenarioTest {

	private static Scenario scenario;

    @BeforeClass
    public static void setUp() {
    	scenario = new Scenario(5,6);
    	System.out.println(scenario.toString() + "\n");
    }

    @Test
    public void testIsValida() {
    	System.out.println(scenario.isValida(1,2));
    	assertTrue(scenario.isValida(1,2));
		System.out.println(scenario.isValida(5,1) + "\n");
		assertFalse(scenario.isValida(5,1));
    }
    
    @Test
    public void testSetPannello() {
    	PannelloBase pannello = new PannelloBase(2,4);
		scenario.setPannello(pannello);
		System.out.println(scenario.getPannello(2,4));
		System.out.println(pannello + "\n");
		assertEquals(scenario.getPannello(2,4), pannello);
    }
    
    @Test
    public void testIsVisibile() {
    	Posizione punto = new Posizione(2,4);
		System.out.println(scenario.isVisibile(punto));
		assertFalse(scenario.isVisibile(punto));
		scenario.abbatterePannello(punto);
		System.out.println(scenario.isVisibile(punto) + "\n");
		assertTrue(scenario.isVisibile(punto));
    }
}
