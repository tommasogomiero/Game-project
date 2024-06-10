package topi.elementi;

public class Nota extends ElementoPassivo{
	/* Costanti */
	private static final String IMAGE_NOTA = "immagini/nota.png";
	
	/* Costruttori */
	public Nota(int quantita) {
		super(quantita);
	}
	
	/* Getters */
	public String getImage() {
		return IMAGE_NOTA;
	}
}
