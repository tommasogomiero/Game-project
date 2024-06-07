package topi.elementi;

import topi.strutture.Scenario;

public abstract class ElementoPassivo extends Elemento{
	/* Attributi */
	private final int quantita;

	/* Costruttori */
	public ElementoPassivo(int quantita) {
		super();
		this.quantita = quantita;
	}

	/* Metodi */
	@Override
	public void aggiornare(Scenario sc) {
		// nada
		
	}

	/* Getters */
	public int getQuantita() {
		return quantita;
	}
	
}
