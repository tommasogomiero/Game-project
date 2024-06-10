package topi.elementi;

import topi.strutture.Direzione;
import topi.strutture.Posizione;
import topi.strutture.Scenario;

public abstract class ElementoAttivo extends Elemento{

	/* Metodi */
	protected boolean spostare(Direzione dir, Scenario sc) {
		if(dir != null) {
			Posizione origine = this.getPannello().getPosi();
			Posizione destinazione = origine.getVicina(dir);
			
			boolean isPosiValida = sc.isValida(destinazione);
			if(isPosiValida) {
				// Togliamo l'elemento attuale dal suo vecchio pannello
				this.getPannello().removeElemento(this);
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	protected abstract void dialogo();
}
