package topi.elementi;

import topi.gioco.ContributoGioco;
import topi.strutture.Scenario;

public class Topo extends ElementoAttivo{
	/* Costanti */
	private static final String IMAGE_TOPO = "immagini/topo.png";
	
	/* Metodi */
	public void aggiornare(Scenario sc) {
	}
	
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(0,0,-1);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_TOPO;
	}
}
