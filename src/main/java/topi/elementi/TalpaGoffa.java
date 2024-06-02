package topi.elementi;

import java.util.Random;

import topi.gioco.ContributoGioco;
import topi.strutture.Direzione;
import topi.strutture.Posizione;
import topi.strutture.Scenario;

public class TalpaGoffa extends Talpa{
	/* Attributi */
	private static final String IMAGE_TALPA_GOFFA = "immagini/talpa-goffa.png";
	
	/* Metodi */
	@Override
	public boolean puoMoversi(Scenario sc) {
		/*
		Posizione posi = this.getPannello().getPosi();
		return sc.isVisibile(posi);
		*/
		return false;
	}
	
	@Override
	public Direzione getDirSpostamento(Scenario sc) {
		Direzione[] dirs = Direzione.values();
		Random rand = new Random();
		int i = rand.nextInt(dirs.length);
		return dirs[i];
	}
	 
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(1,0,0);
	}
	
	@Override
	public String getImage() {
		return IMAGE_TALPA_GOFFA;
	}
}
