package topi.strutture;

public class Flecha1 extends PannelloBase{
	/* Costanti */
	private final static String IMAGE_FLECHA = "immagini/freccia1.png";

	public Flecha1(int x, int y) {
		super(x,y);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_FLECHA;
	}
}
