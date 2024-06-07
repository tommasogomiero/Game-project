package topi.strutture;

import java.util.Objects;

public class Posizione {	
	/* Attributi */
	private int x;
	private int y;
	
	/* Costruttori */
	public Posizione(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Posizione(Posizione punto) {
		this(punto.getX(),punto.getY());
		//this.x = 0;
		//this.y = 0;
	}
	
	/* get/set */
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Posizione getVicina(Direzione dir) {
		Posizione vicina = new Posizione(this);
		vicina.spostare(dir);
		return vicina;
	}
	/* Metodi */
	
	public void spostare(int sposX, int sposY) {
		this.x = this.x + sposX;
		this.y = this.y + sposY;
	}
	
	public void spostare(Direzione dir) {
		switch(dir) {
		case SOPRA:
			this.spostare(0,1);
			break;
		case SOTTO:
			this.spostare(0,-1);
			break;
		case SINISTRA:
			this.spostare(-1,0);
			break;
		case DESTRA:
			this.spostare(1,0);
			break;
		}
	}
	
	public void mostra() {
		System.out.printf("(%d,%d)\n",this.x,this.y);
	}
	
	

	@Override
	public String toString() {
		return "Posicion [x=" + x + ", y=" + y + "]";
	}

	private Posizione copiaSuperficiale() {
		try {
			Posizione copiaSuperficiale = (Posizione) super.clone();
			return copiaSuperficiale;
		}
		catch (CloneNotSupportedException e) {
			System.err.println("La clase no es cloneable");
		}
		return null; // non si e' potuto ottenere le copia
	}
	
	@Override
	public Posizione clone() {
		Posizione copia = copiaSuperficiale();		
		return copia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posizione other = (Posizione) obj;
		return x == other.x && y == other.y;
	}
}
