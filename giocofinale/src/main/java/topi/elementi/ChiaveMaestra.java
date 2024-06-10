package topi.elementi;

import topi.gioco.Partita;

public class ChiaveMaestra extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_CHIAVE = "immagini/chiave_maestra.png";
	
	/* Costruttori */
	public ChiaveMaestra(int quantita) {
		super(quantita);
	}
	
	/*Metodi*/
	public void aprireForziere(Partita game) {
		game.win();
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_CHIAVE;
	}
}
