package topi.elementi;

import topi.gioco.Partita;

public class Pistola extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_PISTOLA = "immagini/pistola.jpg";
	
	/* Costruttori */
	public Pistola(int quantita) {
		super(quantita);
	}
	
	/* Metodi */
	public void sparare(Partita game) {
		if(game.isInGioco()) {
			if(!game.getSc().isVisibile(game.getBersaglio())) {
				game.getSc().abbatterePannello(game.getBersaglio());
			}
			else game.getSc().catturare(game.getBersaglio());
		}
	}
	/* Getters */
	public String getImage() {
		return IMAGE_PISTOLA;
	}
}
