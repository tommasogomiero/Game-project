package topi.elementi;

import topi.gioco.Partita;
import topi.strutture.Direzione;
import topi.strutture.PannelloBase;
import topi.strutture.Scenario;

public class Topo extends ElementoAttivo{
	/* Costanti */
	private static final String IMAGE_TOPO = "immagini/topo.png";
	
	/* Metodi */
	public void aggiornare(Scenario sc) {
	}
	
	public void lasciaMonete(Partita game) {		
		PannelloBase pannelloMonete = game.getSc().getPannello(game.getBersaglio());
		pannelloMonete.addElemento(game.getMonete());
		game.addPannello(pannelloMonete);
	}
	
	public void lasciaChiaveMaestra(Partita game) {
		PannelloBase pannelloChiaveMaestra = game.getSc().getPannello(game.getBersaglio());
		pannelloChiaveMaestra.addElemento(game.getChiaveMaestra());
		game.addPannello(pannelloChiaveMaestra);
	}
	
	public void dialogo() {
		System.out.println("TOPO: 'Squit! E tu chi saresti? Una talpa?!?! Non ti lascerò quello che è mio!'");
	}
	
	public void richiesta() {
		System.out.println("TOPO GUARDIA: 'Hey, so cosa vuoi fare, ma questa porta e' inespugnabile... però la mia mano potrebbe scivolare e aprirla per il giusto prezzo eheheh'");
	}
	
	public void rispostaPositiva() {
		System.out.println("TOPO GUARDIA: 'Hehehe, ho un buon fiuto per gli affari...'");
	}
	
	public void rispostaNegativa() {
		System.out.println("TOPO GUARDIA: 'Non vuoi pagarmi??? Beh, senza soldi non andrai da nessuna parte...'");
	}
	
	public void aprirePorta(Partita game) {
		game.getSc().abbatterePannello(game.getBersaglio().getVicina(Direzione.SOPRA));
	}
	/* Getters */
	public String getImage() {
		return IMAGE_TOPO;
	}
}
