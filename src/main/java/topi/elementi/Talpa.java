package topi.elementi;

import topi.strutture.Direzione;
import topi.strutture.Scenario;

public abstract class Talpa extends ElementoAttivo{
	/* Costanti */
	private static final long TEMPO_ATTESA = 1000;
	
	/* Attributi */
	private long tempoUltMovimento;
	private Direzione direzioneUltMovimento;
	
	/* Metodi */
	public void aggiornare(Scenario sc) {
		if(this.puoMoversi(sc)) {
			long tempoAttuale = System.currentTimeMillis();
			long tempoTrascorso = tempoAttuale - this.tempoUltMovimento;
			if(tempoTrascorso >= TEMPO_ATTESA) {
				Direzione ind = this.getDirSpostamento(sc);
				boolean isSpostato;
				isSpostato = this.spostare(ind, sc);
				if(isSpostato) {
					this.direzioneUltMovimento = ind;
					this.tempoUltMovimento = tempoAttuale;
				}
			}
		}
	}
	
	protected abstract boolean puoMoversi(Scenario sc);
	protected abstract Direzione getDirSpostamento(Scenario sc);
	
	/* Getters */
	public Direzione getDirezioneUltMovimento() {
		return direzioneUltMovimento;
	}
}