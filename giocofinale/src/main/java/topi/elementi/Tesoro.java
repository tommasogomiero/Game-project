package topi.elementi;

public class Tesoro extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_TESORO = "immagini/tesoro.png";
	
	/* Costruttori */
	public Tesoro(int quantita) {
		super(quantita);
	}

	/* Getters */
	public String getImage() {
		return IMAGE_TESORO;
	}
}
