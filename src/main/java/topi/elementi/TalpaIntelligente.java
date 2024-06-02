package topi.elementi;

import java.util.LinkedList;
import java.util.Random;

import topi.gioco.ContributoGioco;
import topi.strutture.Direzione;
import topi.strutture.Scenario;
import topi.strutture.Posizione;

public class TalpaIntelligente extends Talpa{
	/* Costanti */
	private static final String IMAGE_TALPA_INTELLIGENTE = "immagini/talpa-intelligente.png";
	
	/* Metodi */
	public boolean puoMoversi(Scenario sc) {
		/*
		Posizione posi = this.getPannello().getPosi();
		return !sc.isVisibile(posi);	
		*/
		return false;	
	}
	
	public Direzione getDirSpostamento(Scenario sc) {
		LinkedList<Direzione> dirRialzate = new LinkedList<Direzione>();
		Direzione[] listaDirs = Direzione.values();
		for(Direzione dir : listaDirs) {
			if(!sc.isVisibile(this.getPannello().getPosi().getVicina(dir))) {
				dirRialzate.add(dir);
			}
		}
		
		Direzione dirSoluzione = null;
		if(dirRialzate.size()>0) {
			Random rand = new Random();
			int i = rand.nextInt(dirRialzate.size());
			dirSoluzione = dirRialzate.get(i);
		}
		if(dirSoluzione != null) {
			return dirSoluzione;
		}else {
			return this.getDirezioneUltMovimento();
		}
	}
	
	public ContributoGioco getContributoGioco() {
		return new ContributoGioco(3,0,0);
	}
	
	/* Getters */
	@Override
	public String getImage() {
		return IMAGE_TALPA_INTELLIGENTE;
	}
}
