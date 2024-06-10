package topi.strutture;

public class Lucchetto extends PannelloBase{
	/* Costanti */
	private final static String IMAGE_CANDADO = "immagini/cassaforte.png";
	private final static int DUREZZA_DEFAULT = 0;
	private final static int CERO = 0;
	
	/* Attributi */
	private final int durezza;
	private int impatti;
	
	/* Costruttori */
	public Lucchetto(int x, int y, int durezza){
		super(x,y);
		this.durezza = durezza;
		this.impatti = CERO;
	}
	
	public Lucchetto(int x, int y) {
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
		return IMAGE_CANDADO;
	}
}
