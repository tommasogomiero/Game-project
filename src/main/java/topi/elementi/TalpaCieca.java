package topi.elementi;

import java.util.Random;

import topi.gioco.ContributoGioco;
import topi.strutture.Direzione;
import topi.strutture.Scenario;

public class TalpaCieca extends Talpa{
	private static final String IMAGE_TALPA_CIECA = "immagini/talpa-cieca.png";
	/* Metodi */
	public boolean puoMoversi(Scenario sc) {
		//return true;
		return false;
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
	
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(2,0,0);
	}

	/* Getters */
	@Override
	public String getImage() {
		return IMAGE_TALPA_CIECA;
	}
	
}
