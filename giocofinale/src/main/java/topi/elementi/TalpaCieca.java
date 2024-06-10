package topi.elementi;

import java.util.Random;

import topi.gioco.Partita;
import topi.strutture.Direzione;
import topi.strutture.PannelloBase;
import topi.strutture.Scenario;

public class TalpaCieca extends Talpa{
	private static final String IMAGE_TALPA_CIECA = "immagini/talpa-cieca.png";
	/* Metodi */
	public boolean puoMoversi(Scenario sc) {
		return false;
	}
	
	@Override
	public void dialogo() {
		System.out.println("TALPA: 'Grazie per avermi liberato dal pannello! Anche se non posso vederti, sento che il tuo animo e' puro! Ti prego, liberaci da Giorgio Talponi e dai suoi scagnozzi. Questa potra' servirti.'");
	}
	
	public void lasciaOggetto(Partita game) {
		PannelloBase pannelloChiave1 = game.getSc().getPannello(game.getBersaglio());
		pannelloChiave1.addElemento(game.getChiaveUno());
		game.addPannello(pannelloChiave1);
	}
	
	public Direzione getDirSpostamento(Scenario sc) {
		Direzione[] dirs = Direzione.values();
		Random rand = new Random();
		int i = rand.nextInt(dirs.length);
		if(dirs[i] == this.getDirezioneUltMovimento()) {
			i++;
			if(i == dirs.length) {
				i = 0;
			}
		}
		return dirs[i];
	}

	/* Getters */
	@Override
	public String getImage() {
		return IMAGE_TALPA_CIECA;
	}
	
}
