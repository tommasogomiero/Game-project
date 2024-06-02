package topi.elementi;

import topi.gioco.ContributoGioco;
import topi.strutture.Direzione;
import topi.strutture.Scenario;

public class Topo extends ElementoAttivo{
	/* Costanti */
	private static final String IMAGE_TOPO = "immagini/topo.png";
	
	/* Metodi */
	public void aggiornare(Scenario sc) {
		/*
		if(!sc.isVisibile(this.getPannello().getPosi())) {
			Direzione dir = this.getPannelloValido(sc);
			if(dir != null) {
				this.spostare(dir, sc);
			}
		}
		*/
	}
	
	private Direzione getPannelloValido(Scenario sc) {
		if((sc.isValida(this.getPannello().getPosi().getVicina(Direzione.DESTRA)))&&(sc.isVisibile(this.getPannello().getPosi().getVicina(Direzione.DESTRA)))) {
			return Direzione.DESTRA;
		}else if(sc.isValida(this.getPannello().getPosi().getVicina(Direzione.SOPRA))&&(sc.isVisibile(this.getPannello().getPosi().getVicina(Direzione.SOPRA)))) {
			return Direzione.SOPRA;
		}else if(sc.isValida(this.getPannello().getPosi().getVicina(Direzione.SINISTRA))&&(sc.isVisibile(this.getPannello().getPosi().getVicina(Direzione.SINISTRA)))) {
			return Direzione.SINISTRA;
		}else if(sc.isValida(this.getPannello().getPosi().getVicina(Direzione.SOTTO))&&(sc.isVisibile(this.getPannello().getPosi().getVicina(Direzione.SOTTO)))){
			return Direzione.SOTTO; 
		}else {
			return null;
		}
	}
	
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(0,0,-1);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_TOPO;
	}
}
