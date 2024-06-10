package topi.elementi;

import topi.gioco.Partita;
import topi.strutture.Direzione;


public class Chiave extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_CHIAVE = "immagini/chiave.png";
	
	/* Costruttori */
	public Chiave(int quantita) {
		super(quantita);
	}
	
	/* Metodi*/
	public void aprirePortaUno(Partita game) {
		game.getSc().abbatterePannello(game.getBersaglio().getVicina(Direzione.SOPRA));
	}
	
	public void aprirePortaDue(Partita game) {
		game.getSc().abbatterePannello(game.getBersaglio().getVicina(Direzione.SOTTO));
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_CHIAVE;
	}
}
