package topi.elementi;

import topi.gioco.ContributoGioco;

public class Chiave extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_CHIAVE = "immagini/llave.png";
	
	/* Costruttori */
	public Chiave(int quantita) {
		super(quantita);
	}
	
	/* Metodi */
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(this.getQuantita(),0, 0);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_CHIAVE;
	}
}
