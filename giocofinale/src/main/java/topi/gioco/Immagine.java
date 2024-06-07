package topi.gioco;

public class Immagine {
	/* Attributi */
	private final String image;
	private final int x;
	private final int y;
	
	/* Costruttori */
	public Immagine(String image, int x, int y){
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	/* Getters */
	public String getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
