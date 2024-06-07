package topi.elementi;

import topi.gioco.ContributoGioco;

public class Moneta extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_MONETA = "immagini/monete.png";
	
	/* Costruttori */
	public Moneta(int quantita) {
		super(quantita);
	}
	
	/* Metodi */
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(this.getQuantita(),0, 0);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_MONETA;
	}
}
