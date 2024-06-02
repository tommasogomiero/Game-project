package topi.elementi;

import topi.gioco.ContributoGioco;

public class Nota extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_NOTA = "immagini/nota.png";
	
	/* Costruttori */
	public Nota(int quantita) {
		super(quantita);
	}
	
	/* Metodi */
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(this.getQuantita(),0, 0);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_NOTA;
	}
}
