package topi.strutture;

public class PannelloDebole extends PannelloBase{
	/* Costanti */
	private final static String IMAGE_PANNELLO = "immagini/pannello-debole.gif";
	private final static int DUREZZA_DEFAULT = 1;
	private final static int CERO = 0;
	
	/* Attributi */
	private int impatti;
	private final int durezza;

	/* Costruttori */
	public PannelloDebole(int x, int y, int durezza){
		super(x,y);
		this.durezza = durezza;
		this.impatti = CERO;
	}
	
	public PannelloDebole(int x, int y) {
		super(x,y);
		this.durezza = DUREZZA_DEFAULT;
		this.impatti = CERO;
	}
	
	@Override
	public void abbattere() {
		if(this.impatti >= this.durezza) {
			super.abbattere();
		}
		else{
			this.impatti++;
		}
	}
	
	@Override
	public void aggiornare(Scenario sc) {
		super.aggiornare(sc);
		if(this.isVisibile()) {
			impatti = CERO;
		}
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_PANNELLO;
	}
}
