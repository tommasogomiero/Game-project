package topi.strutture;

public class Blocco extends PannelloBase{
	/* Costanti */
	private final static String IMAGE_BLOCCO = "immagini/blocco.jpg";

	public Blocco(int x, int y) {
		super(x,y);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_BLOCCO;
	}
}
