package topi.elementi;

import topi.strutture.Direzione;
import topi.strutture.PannelloBase;
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
				// Eliminamos el Elemento Actual de su antiguo Panel
				this.getPannello().removeElemento(this);
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
}
