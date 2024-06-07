package topi.elementi;

import topi.gioco.ContributoGioco;

public class Tesoro extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_TESORO = "immagini/tesoro.jpg";
	
	/* Costruttori */
	public Tesoro(int quantita) {
		super(quantita);
	}
	
	/* Metodi */
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(this.getQuantita(),0, 0);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_TESORO;
	}
}
