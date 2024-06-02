package topi.strutture;

import java.util.LinkedList;

import topi.gioco.ContributoGioco;

public class Scenario {
	/* Atributos */
	private int larghezza;
	private int altezza;
	private PannelloBase pannelli[][];
	
	/* Constructores */
	public Scenario(int a, int b) {
		this.larghezza = a;
		this.altezza = b;
		this.pannelli = new PannelloBase[a][b];
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < b; j++) {
				this.pannelli[i][j] = new PannelloBase(i,j);
			}
		} 
	}
	
	/* Metodos */
	public boolean isValida(Posizione p) {
		int l = p.getX();
		int h = p.getY();
		if((l==1 && h!=0 && h!=4 && h!=6) || (l==2 && h!=2 && h!=3 && h!=4) || (l==4 && h!=2 && h!=3 && h!=4) || (l==5 && h!=0 && h!=2 && h!=6)) return false;
		return (l >= 0) && (l < larghezza) && (h >= 0) && (h < altezza);
	}
	
	public boolean isValida(int x, int y) {
		if((x==1 && (y==1 || y==altezza-2)) || (y==1 && (x==1 || x==larghezza-2)) || (x==larghezza/2 && y==altezza/2 || (x==larghezza-2 && y==altezza-2))) return false;
		return (x >= 0) && (x < larghezza) && (y >= 0) && (y < altezza);
	}
	
	public void setPannello(PannelloBase panel) {
		this.pannelli[panel.getPosi().getX()][panel.getPosi().getY()] = panel;
	}
	
	public boolean isVisibile(Posizione p) {
		if(this.isValida(p)) {
			return this.pannelli[p.getX()][p.getY()].isVisibile();
		}
		return false;
	}

	public void abbatterePannello(Posizione p) {
		if(this.isValida(p)) {
			this.pannelli[p.getX()][p.getY()].abbattere();
		}
	}
	
	public void sollevarePannello(Posizione p) {
		if(this.isValida(p)) {
			this.pannelli[p.getX()][p.getY()].sollevare();
		}
	}
	
	public void aggiornare() {
		for(int i=0; i<larghezza;i++) {
			for(int j=0; j<altezza; j++) {
				if(isValida(i,j)) pannelli[i][j].aggiornare(this);
			}
		}
	}
	
	public LinkedList<ContributoGioco> catturare(Posizione posi){
		LinkedList<ContributoGioco> listaCG = new LinkedList<ContributoGioco>();
		if(this.isVisibile(posi)) {
			listaCG.addAll(this.pannelli[posi.getX()][posi.getY()].catturare(this));
		}
		return listaCG;
	}
	
	/* Getters */
	public int getLarghezza() {
		return larghezza;
	}
	public int getAltezza() {
		return altezza;
	}
	
	public PannelloBase getPannello(int x, int y) {
		if((x >= 0) && (x < larghezza) && (y >= 0) && (y < altezza)){
			return this.pannelli[x][y];
		}
		else {
			return null;
		}
	}
	
	public PannelloBase getPannello(Posizione p) {
		if((p.getX() >= 0) && (p.getX() < larghezza) && (p.getY() >= 0) && (p.getY() < altezza)){
			return this.pannelli[p.getX()][p.getY()];
		}
		else {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return "Escenario [larghezza=" + this.larghezza + ", altezza=" + this.altezza + "]";
	}
} 
