package topi.gioco;

public class ContributoGioco {
	/* Attributi */
	private final int punti;
	private final int colpi;
	private final int vita;
	
	public ContributoGioco(int p, int c, int v) {
		this.punti = p;
		this.colpi = c;
		this.vita = v;
	}

	/* Getters */
	public int getPunti() {
		return punti;
	}
	
	public int getColpi() {
		return colpi;
	}
	
	public int getVita() {
		return vita;
	}
}
