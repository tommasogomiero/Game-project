package topi.elementi;

import topi.gioco.ContributoGioco;

public class Pistola extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_PISTOLA = "immagini/pistola.jpg";
	
	/* Costruttori */
	public Pistola(int quantita) {
		super(quantita);
	}
	
	/* Metodi */
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(this.getQuantita(),0, 0);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_PISTOLA;
	}
}
