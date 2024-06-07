package topi.prove;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import topi.elementi.Elemento;
import topi.elementi.Moneta;
import topi.strutture.PannelloBase;

public class ElementiTest {

	private static Elemento elem;
	private static PannelloBase panel1 = new PannelloBase(0,0);
	private static PannelloBase panel2 = new PannelloBase(0,0);

    @BeforeClass
    public static void setUp() {
    	elem = new Moneta(5);
    	panel1 = new PannelloBase(0,0);
    	panel2 = new PannelloBase(0,0);
    }

    @Test
    public void testAddElemento() {
    	panel1.addElemento(elem);
		System.out.println(elem.getPannello());
		System.out.println(panel1.getElementi() + "\n");
		assertNotNull(elem.getPannello());
		assertTrue(panel1.getElementi().contains(elem));
    }
    
    @Test
    public void testRemoveElemento() {
    	panel2.addElemento(elem);
		System.out.println(elem.getPannello());
		System.out.println(panel2.getElementi());
		panel2.removeElemento(elem);
		System.out.println(elem.getPannello());
		System.out.println(panel2.getElementi());
		assertNull(elem.getPannello());
		assertFalse(panel2.getElementi().contains(elem));
    }
}