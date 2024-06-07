package topi.strutture;

public class PannelloMenu extends PannelloBase{
	/* Costanti */
	private final static String IMAGE_WIN = "immagini/win.jpg";

	public PannelloMenu(int x, int y) {
		super(x,y);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_WIN;
	}
}
