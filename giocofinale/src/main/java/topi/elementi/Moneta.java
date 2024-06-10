package topi.elementi;

public class Moneta extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_MONETA = "immagini/monete.png";
	
	/* Costruttori */
	public Moneta(int quantita) {
		super(quantita);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_MONETA;
	}
}
