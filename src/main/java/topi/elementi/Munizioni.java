package topi.elementi;

import topi.gioco.ContributoGioco;

public class Munizioni extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_MUNIZIONI = "immagini/munizioni.png";
	
	/* Attributi */
	public Munizioni(int quantita) {
		super(quantita);
	}

	
	/* Metodi */
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(0,this.getQuantita(), 0);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_MUNIZIONI;
	}
}
