package topi.strutture;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import topi.elementi.Elemento;
import topi.gioco.ContributoGioco;
import topi.gioco.Immagine;

public class PannelloBase {
	/* Costanti */
	private final static String IMAGE_PANNELLO = "immagini/pannello-base.gif";
	
	private final Lock l = new ReentrantLock();
	
	/* Attributi */
	private Posizione posi;
	private Stato stato;
	private LinkedList<Elemento> elementi;
	
	/* Costruttori */
	public PannelloBase( int x, int y){
		this.posi = new Posizione(x,y);
		this.stato = Stato.SOLLEVATO;
		this.elementi = new LinkedList<Elemento>();
	}
	
	public PannelloBase(PannelloBase pannello) {
		this.posi = pannello.getPosi();
		this.stato = pannello.getStato();
		this.elementi = new LinkedList<Elemento>();
	}
	

	/* Metodi */
	public void abbattere() {
		if(this.stato == Stato.SOLLEVATO) {
			this.stato = Stato.ABBATTUTO;
		}
	}
	
	public void sollevare() {
		if(this.stato == Stato.ABBATTUTO) {
			this.stato = Stato.SOLLEVATO;
		}
	}
	
	public void mostraPosi() {
		this.posi.mostra();
	}
	
	public void mostraStato() {
		System.out.println(this.stato);
	}
	
	public void mostra() {
		this.mostraPosi();
		this.mostraStato();
	}
	
	public boolean addElemento(Elemento elem) {
		if(!this.elementi.contains(elem)) {
			this.elementi.add(elem);
			elem.setPannello(this);
			return true;
		}
		return false;
	}
	
	public boolean removeElemento(Elemento elem) {
		if(this.elementi.contains(elem)) {
			this.elementi.remove(elem);
			elem.setPannello(null);
			return true;
		}
		return false;
	}
	
	public boolean hayElemento(Elemento elem) {
		return this.elementi.contains(elem);
	}
	
	public void aggiornare(Scenario sc) {
		for(Elemento e : this.elementi) {
			e.aggiornare(sc);
		}
	}
	
	public LinkedList<ContributoGioco> catturare(Scenario sc){
		LinkedList<ContributoGioco> listaCG = new LinkedList<ContributoGioco>();
		Iterator<Elemento> it = elementi.iterator(); 
		l.lock();
	    try {
	    	while (it.hasNext()) {
	    		Elemento e = it.next();
	    		listaCG.add(e.getContributoGioco());
	    		it.remove();
	    	}
	    } finally {
        		l.unlock();
        	}
	 
		return listaCG;
	}
	
	/* Getters */
	public Posizione getPosi() {
		return posi;
	}
	

	public Stato getStato() {
		return stato;
	}


	public LinkedList<Elemento> getElementi() {
		return elementi;
	}
	
	public String getImage() {
		return IMAGE_PANNELLO;
	}
	
	public LinkedList<Immagine> getImmaginiPannello(){
		LinkedList<Immagine> listaImmagini = new LinkedList<Immagine>();
		if(this.stato == Stato.SOLLEVATO) {
			listaImmagini.add(new Immagine(this.getImage(), this.posi.getX(), this.posi.getY()));
		}else {
			for(Elemento e : this.elementi) {
				listaImmagini.add(new Immagine(e.getImage(), e.getPannello().getPosi().getX(), e.getPannello().getPosi().getY()));
			}
		}
		return listaImmagini;
	}
	
	public boolean isVisibile() {
		return this.stato == Stato.ABBATTUTO;
	}
	
	@Override
	public String toString() {
		return "PannelloBase [posi=" + posi + ", stato=" + stato + "]";
	}
}
