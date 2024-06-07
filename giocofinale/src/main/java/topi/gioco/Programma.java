package topi.gioco;

import java.awt.Color;

import topi.vista.Alarma;
import topi.vista.Pantalla;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Programma{
	/* Costanti */
	public final static int LARGHEZZA_DEFAULT = 7;
	public final static int ALTEZZA_DEFAULT = 7;
	
	public final static int DIMENSIONE_QUADRO_DEFAULT = 100;
	public final static Color COLORE_DEFAULT = new Color(199, 248, 255, 20);
	
	public final static String TASTO_UNO = "1";
	public final static String TASTO_DUE = "2";
	
	private final static Lock l = new ReentrantLock();
	
	/* Programma Principale */
	public static void main(String[] args) {
		
		Partita game = new Partita(LARGHEZZA_DEFAULT, ALTEZZA_DEFAULT);
		
		game.start();
		
		game.prepare();
		
		String cadenaGame = "";
		Pantalla pantallaGame = null;
		
		boolean menu_inicial = true;
		while(game.isInGioco()) {
			if(menu_inicial){
				pantallaGame = new Pantalla(1, 1, DIMENSIONE_QUADRO_DEFAULT*game.getSc().getLarghezza(), COLORE_DEFAULT);
				cadenaGame = "NEW GAME: Press any key to continue.";
				pantallaGame.setBarraEstado(cadenaGame);
				pantallaGame.addImagen(0, 0, game.getImageInizio());
				pantallaGame.dibujar();
				l.lock();
		    		try {
					while(!pantallaGame.hayTecla()){
						Alarma.dormir(200);
					}
					pantallaGame.leerTecla();
				}
				finally{
					l.unlock();
				}
				
				pantallaGame.resetear();
				pantallaGame.dibujar();
				pantallaGame = new Pantalla(LARGHEZZA_DEFAULT, ALTEZZA_DEFAULT, DIMENSIONE_QUADRO_DEFAULT, COLORE_DEFAULT);
				menu_inicial = false;
			}
			
			for(Immagine img : game.getImmaginePartita()) {
				pantallaGame.addImagen(img.getX(), img.getY(), img.getImage());
			}
			
			pantallaGame.dibujar();
			Alarma.dormir(200);
			
			pantallaGame.resetear();
			
			game.interagire();
			game.aggiornare();
		}
		
		if(game.getExit()){
			pantallaGame.resetear();
			pantallaGame.dibujar();
			pantallaGame = new Pantalla(1, 1, DIMENSIONE_QUADRO_DEFAULT*game.getSc().getLarghezza(), COLORE_DEFAULT);
			String cadenaExit = "EXIT";
			pantallaGame.setBarraEstado(cadenaExit);
			pantallaGame.addImagen(0, 0, game.getImageExit());
			pantallaGame.dibujar();
		}
		else{
			pantallaGame.resetear();
			pantallaGame.dibujar();
			pantallaGame = new Pantalla(1, 1, DIMENSIONE_QUADRO_DEFAULT*game.getSc().getLarghezza(), COLORE_DEFAULT);
			String cadenaVictory = "HAI TROVATO IL TESORO. VITTORIA!!";
			pantallaGame.setBarraEstado(cadenaVictory);
			pantallaGame.addImagen(0, 0, game.getImageWin());
			pantallaGame.dibujar();
		}
	} 
}
