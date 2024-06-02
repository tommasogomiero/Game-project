package topi.strutture;

import java.util.Random;

public class PannelloAleatorio extends PannelloBase{
	/* Constantes */
	private final static String IMAGE_PANNELLO = "immagini/pannello-aleatorio.png";
	/* Constructores */
	public PannelloAleatorio(int x, int y) {
		super(x,y);
	}
	
	/* Metodos */
	@Override
	public void abbattere() {
		Random rand = new Random();
		boolean bool = rand.nextBoolean();
		if(bool) {
			super.abbattere();
		}
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_PANNELLO;
	}
}
