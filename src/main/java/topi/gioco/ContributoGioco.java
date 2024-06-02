package topi.gioco;

public class ContributoGioco {
	/* Attributo */
	private final int punti;
	//private final int tempo;
	private final int colpi;
	private final int vita;
	
	public ContributoGioco(int p, int c, int v) {
		this.punti = p;
		//this.tempo = t;
		this.colpi = c;
		this.vita = v;
	}

	/* Getters */
	public int getPunti() {
		return punti;
	}
	/*
	public int getTempo() {
		return tempo;
	}
	*/
	public int getColpi() {
		return colpi;
	}
	
	public int getVita() {
		return vita;
	}
}
