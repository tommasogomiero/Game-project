package topi.elementi;

import java.util.LinkedList;
import java.util.Random;

import topi.gioco.Partita;
import topi.strutture.Direzione;
import topi.strutture.PannelloBase;
import topi.strutture.Scenario;

public class TalpaIntelligente extends Talpa{
	/* Costanti */
	private static final String IMAGE_TALPA_INTELLIGENTE = "immagini/talpa-intelligente.png";
	
	/* Metodi */
	public boolean puoMoversi(Scenario sc) {
		return false;	
	}
	
	public void dialogo() {
		System.out.println("GIORGIO TALPONI: 'M...Ma...Ma come e' p-possibile??? TU chi sei? Come hai fatto a trovarmi? Comunque sia, non riuscirai a sconfiggermi!'");
	}
	
	public void lasciaOggetto(Partita game) {
		PannelloBase pannelloNota = game.getSc().getPannello(game.getBersaglio());
		pannelloNota.addElemento(game.getNota());
		game.addPannello(pannelloNota);
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
	

	/* Getters */
	@Override
	public String getImage() {
		return IMAGE_TALPA_INTELLIGENTE;
	}
}
