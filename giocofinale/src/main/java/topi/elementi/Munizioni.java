package topi.elementi;

public class Munizioni extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_MUNIZIONI = "immagini/munizioni.png";
	
	/* Attributi */
	public Munizioni(int quantita) {
		super(quantita);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_MUNIZIONI;
	}
}
