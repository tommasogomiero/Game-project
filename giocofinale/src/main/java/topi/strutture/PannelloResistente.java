package topi.strutture;

public class PannelloResistente extends PannelloBase{
	/* Costanti */
	private final static String IMAGE_PANNELLO = "immagini/pannello-resistente.gif";
	private final static int DUREZZA_DEFAULT = 2;
	private final static int CERO = 0;
	
	/* Attributi */
	private final int durezza;
	private int impatti;
	
	/* Costruttori */
	public PannelloResistente(int x, int y, int durezza){
		super(x,y);
		this.durezza = durezza;
		this.impatti = CERO;
	}
	
	public PannelloResistente(int x, int y) {
		super(x,y);
		this.durezza = DUREZZA_DEFAULT;
		this.impatti = CERO;
	}
	
	/* Metodi */
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
