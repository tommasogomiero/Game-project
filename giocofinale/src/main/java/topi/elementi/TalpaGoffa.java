package topi.elementi;

import java.util.Random;

import topi.gioco.Partita;
import topi.strutture.Direzione;
import topi.strutture.PannelloBase;
import topi.strutture.Scenario;

public class TalpaGoffa extends Talpa{
	/* Attributi */
	private static final String IMAGE_TALPA_GOFFA = "immagini/talpa-goffa.png";
	
	/* Metodi */
	@Override
	public boolean puoMoversi(Scenario sc) {
		return false;
	}
	
	public void dialogo() {
		System.out.println("TALPA: 'Grazie per avermi liberato! Ti prego, liberaci da Giorgio Talponi e dai suoi scagnozzi. Questa potra' servirti. OPS! Mi è caduta per terra, raccoglila tu!'");
	}
	
	public void lasciaOggetto(Partita game) {
		PannelloBase pannelloChiave2 = game.getSc().getPannello(game.getBersaglio());
		pannelloChiave2.addElemento(game.getChiaveDue());
		game.addPannello(pannelloChiave2);
	}
	
	@Override
	public Direzione getDirSpostamento(Scenario sc) {
		Direzione[] dirs = Direzione.values();
		Random rand = new Random();
		int i = rand.nextInt(dirs.length);
		return dirs[i];
	}

	@Override
	public String getImage() {
		return IMAGE_TALPA_GOFFA;
	}
}
