package topi.elementi;

import topi.gioco.ContributoGioco;
import topi.strutture.PannelloBase;
import topi.strutture.Scenario;

public abstract class Elemento {
	
	private PannelloBase pannello;
	
	/* Costruttori */
	public Elemento(){
		pannello=null;
	}
	
	/* Metodi */
	@Override
	public String toString() {
		return "Elemento [pannello=" + pannello + "]";
	}
	
	public abstract void aggiornare(Scenario sc);
	
	/* Getters and Setters */
	public PannelloBase getPannello() {
		if(pannello!=null) {
			return new PannelloBase(pannello);
		}
		else {
			return null;
		}
	}

	public void setPannello(PannelloBase pannello) {
		this.pannello = pannello;
	}
	
	public abstract String getImage();
	
	public abstract ContributoGioco getContributoGioco();
}
