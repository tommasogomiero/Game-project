package topi.gioco;

import java.util.LinkedList;

import topi.strutture.Direzione;
import topi.strutture.Posizione;
import topi.strutture.Scenario;
import topi.strutture.Stato;
import topi.strutture.PannelloAleatorio;
import topi.strutture.PannelloBase;
import topi.strutture.PannelloDebole;
import topi.strutture.PannelloResistente;
import topi.strutture.Porta;
import topi.strutture.Lucchetto;
import topi.strutture.PannelloMenu;
import topi.strutture.Blocco;
import topi.strutture.Flecha1;
import topi.strutture.Flecha2;
import topi.elementi.Moneta;
import topi.elementi.Nota;
import topi.elementi.Munizioni;
import topi.elementi.Pistola;
import topi.elementi.Tesoro;
import topi.elementi.ChiaveMaestra;
import topi.elementi.Chiave;
import topi.elementi.TalpaCieca;
import topi.elementi.TalpaGoffa;
import topi.elementi.TalpaIntelligente;
import topi.elementi.Topo;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Partita {
	/* Costanti */
	private final static String IMAGE_BERSAGLIO = "immagini/bersaglio.png";
	private final static String RUTA_FILE = "gameSaved/game_saved.txt";
	private final static String IMAGE_INIZIO = "immagini/inizio.jpg";
	private final static String IMAGE_EXIT = "immagini/exit.png";
	static double doubleRandomNumber = Math.random()*10000;
	static int doubleConv = (int)doubleRandomNumber;
	static String password = "" + doubleConv;
	Scanner in = new Scanner(System.in);

	/* Variabili */
	private Scenario sc;
	private Scenario sc_menu;
	private Posizione bersaglio;
	private Posizione bersaglio_ult;
	private boolean exit;
	private boolean win;
	private boolean pistola;
	private boolean monete;
	private boolean chiave_maestra;
	private boolean munizioni;
	private boolean chiave1;
	private boolean chiave2;
	private boolean nota;
	private int zaino;
	private Munizioni pruebaMunicion;
	private Pistola provaPistola;
	private Topo provaTopo;
	private Topo provaTopo1;
	private TalpaGoffa provaTalpaGoffa;
	private TalpaCieca provaTalpaCieca;
	private Moneta provaMoneta;
	private TalpaIntelligente provaTalpaIntelligente;
	private ChiaveMaestra provaChiaveMaestra;
	private Chiave provaChiave1;
	private Chiave provaChiave2;
	private Nota provaNota;

	/* Costruttori */

	public Partita(int larghezza, int altezza){
		this.sc = new Scenario(larghezza, altezza);
		this.sc_menu = new Scenario(1,1);
		this.bersaglio = new Posizione(0,0);
		this.bersaglio_ult = new Posizione(0,0);
		this.exit = false;
		this.win = false;
		this.pistola = false;
		this.monete = false;
		this.chiave_maestra = false;
		this.munizioni = false;
		this.chiave1 = false;
		this.chiave2 = false;
		this.nota = false;
		this.zaino = 0;
		this.provaMoneta = new Moneta(0);
		this.provaTalpaCieca = new TalpaCieca();
		this.provaTalpaGoffa = new TalpaGoffa();
		this.provaTopo = new Topo();
		this.provaTopo1 = new Topo();
		this.provaPistola = new Pistola(0);
		this.pruebaMunicion = new Munizioni(0);
		this.provaChiaveMaestra = new ChiaveMaestra(0);
		this.provaChiave1 = new Chiave(0);
		this.provaChiave2 = new Chiave(0);
		this.provaNota = new Nota(0);
		this.provaTalpaIntelligente = new TalpaIntelligente();
	}

	public boolean spostareBersaglioUlt(){
		Posizione aux = new Posizione(bersaglio_ult.getX(), bersaglio_ult.getY());
		this.bersaglio_ult = bersaglio;
		this.bersaglio = aux;
		return true;
	}

	public boolean spostareBersaglio(Direzione vicina){
		if(this.sc.isValida(bersaglio.getVicina(vicina))) {
			this.bersaglio_ult = bersaglio;
			this.bersaglio = bersaglio.getVicina(vicina);
			return true;
		}else {
			//pasadizo secreto
			if(bersaglio.getX()==1 && bersaglio.getY()==sc.getAltezza()-1 && vicina==Direzione.SOTTO){
				this.bersaglio_ult = bersaglio;
				this.bersaglio = new Posizione(sc.getLarghezza()-2,0);
				return true;
			}
			else if(bersaglio.getX()==sc.getLarghezza()-2 && bersaglio.getY()==0 && vicina==Direzione.SOPRA){
				this.bersaglio_ult = bersaglio;
				this.bersaglio = new Posizione(1,sc.getAltezza()-1);
				return true;
			}
			else return false;
		}
	}

	public void sparare() {
		if(this.isInGioco()) {
			if(!this.sc.isVisibile(this.bersaglio)) {
				this.sc.abbatterePannello(this.bersaglio);
			}else {
				LinkedList<ContributoGioco> listaCG = sc.catturare(this.bersaglio);
				for(ContributoGioco cg : listaCG) {
					//this.punti = this.punti + cg.getPunti();
					//this.secondi = this.secondi + cg.getTempo();
					//this.vita = this.vita + cg.getVita();
					//this.colpiRimanenti = this.colpiRimanenti + cg.getColpi();
				}
			}
			//this.colpiRimanenti--;
			//if(this.colpiRimanenti > MAX_COLPI) this.colpiRimanenti = MAX_COLPI;
		}
	}

	public void aggiornare() {
		this.sc.aggiornare();
	}

	public void addPannello(PannelloBase pannello) {
		this.sc.setPannello(pannello);
	}

	public void addPannelloMenu(PannelloBase pannello) {
		this.sc_menu.setPannello(pannello);
	}

	//Getters
	public Scenario getSc() {
		return this.sc;
	}

	public Posizione getBersaglio() {
		return this.bersaglio;
	}

	public String getImageBersaglio() {
		return IMAGE_BERSAGLIO;
	}

	public String getImageInizio(){
		return IMAGE_INIZIO;
	}

	public String getImageWin(){
		return this.sc_menu.getPannello(0,0).getImage();
	}

	public String getImageExit(){
		return IMAGE_EXIT;
	}

	public boolean getExit(){
		return this.exit;
	}

	//Setters
	public void setBersaglio(int x, int y) {
		this.bersaglio = new Posizione(x,y);
	}

	public void setBersaglioUlt(int x, int y) {
		this.bersaglio_ult = new Posizione(x,y);
	}

	public LinkedList<Immagine> getImmaginePartita(){
		LinkedList<Immagine> listaImmagini = new LinkedList<Immagine>();
		for(int i=0; i<this.sc.getLarghezza(); i++) {
			for(int j=0; j<this.sc.getAltezza(); j++) {
				listaImmagini.addAll(this.sc.getPannello(i, j).getImmaginiPannello());
			}
		}
		listaImmagini.add(new Immagine(this.getImageBersaglio(),this.bersaglio.getX(),this.bersaglio.getY()));
		return listaImmagini;
	}

	//save
	public void salvaStato() {
		Posizione p1 = new Posizione(6,0);
		Posizione p2 = new Posizione(0,6);
		Posizione p3 = new Posizione(3,0);
		Posizione p4 = new Posizione(3,6);
		Posizione p5 = new Posizione(6,6);
		boolean topo1 = sc.getPannello(p1).hayElemento(provaTalpaGoffa);
		boolean topo2 = sc.getPannello(p2).hayElemento(provaTalpaCieca);
		boolean rata1 = sc.getPannello(p3).hayElemento(provaTopo);
		boolean rata2 = sc.getPannello(p4).hayElemento(provaTopo1);
		boolean topo3 = sc.getPannello(p5).hayElemento(provaTalpaIntelligente);

		int pistola_x;
		int pistola_y;
		if(pistola){
			pistola_x = -1;
			pistola_y = -1;
		}
		else{
			pistola_x = provaPistola.getPannello().getPosi().getX();
			pistola_y = provaPistola.getPannello().getPosi().getY();
		}
		int munizioni_x;
		int munizioni_y;
		if(munizioni){
			munizioni_x = -1;
			munizioni_y = -1;
		}
		else{
			munizioni_x = pruebaMunicion.getPannello().getPosi().getX();
			munizioni_y = pruebaMunicion.getPannello().getPosi().getY();
		}
		int monete_x;
		int monete_y;
		if(monete || rata2 || sc.getPannello(6,5).getStato()==Stato.ABBATTUTO){
			monete_x = -1;
			monete_y = -1;
		}
		else{
			monete_x = provaMoneta.getPannello().getPosi().getX();
			monete_y = provaMoneta.getPannello().getPosi().getY();
		}
		int chiave_maestra_x;
		int chiave_maestra_y;
		if(chiave_maestra || rata1){
			chiave_maestra_x = -1;
			chiave_maestra_y = -1;
		}
		else{
			chiave_maestra_x = provaChiaveMaestra.getPannello().getPosi().getX();
			chiave_maestra_y = provaChiaveMaestra.getPannello().getPosi().getY();
		}
		int chiave2_x;
		int chiave2_y;
		if(chiave2 || topo1){
			chiave2_x = -1;
			chiave2_y = -1;
		}
		else{
			chiave2_x = provaChiave2.getPannello().getPosi().getX();
			chiave2_y = provaChiave2.getPannello().getPosi().getY();
		}
		int chiave1_x;
		int chiave1_y;
		if(chiave1 || topo2){
			chiave1_x = -1;
			chiave1_y = -1;
		}
		else{
			chiave1_x = provaChiave1.getPannello().getPosi().getX();
			chiave1_y = provaChiave1.getPannello().getPosi().getY();
		}
		int nota_x;
		int nota_y;
		if(nota || topo3){
			nota_x = -1;
			nota_y = -1;
		}
		else{
			nota_x = provaNota.getPannello().getPosi().getX();
			nota_y = provaNota.getPannello().getPosi().getY();
		}

		boolean[][] paneles = new boolean[sc.getLarghezza()][sc.getLarghezza()];
		for(int l = 0; l<sc.getLarghezza(); l++) {
			for(int h = 0; h<sc.getAltezza(); h++) {
				if(sc.getPannello(l,h).getStato()==Stato.SOLLEVATO) paneles[l][h]=true;
				else paneles[l][h] = false;
			}
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_FILE))) {
			writer.write("bersaglioX=" + this.bersaglio.getX());
			writer.newLine();
			writer.write("bersaglioY=" + this.bersaglio.getY());
			writer.newLine();
			writer.write("bersaglioUltX=" + this.bersaglio_ult.getX());
			writer.newLine();
			writer.write("bersaglioUltY=" + this.bersaglio_ult.getY());
			writer.newLine();
			writer.write("zaino=" + this.zaino);
			writer.newLine();
			writer.write("pistola=" + this.pistola);
			writer.newLine();
			writer.write("monete=" + this.monete);
			writer.newLine();
			writer.write("chiaveMaestra=" + this.chiave_maestra);
			writer.newLine();
			writer.write("munizioni=" + this.munizioni);
			writer.newLine();
			writer.write("chiave1=" + this.chiave1);
			writer.newLine();
			writer.write("chiave2=" + this.chiave2);
			writer.newLine();
			writer.write("nota=" + this.nota);
			writer.newLine();
			writer.write("pistolaX=" + pistola_x);
			writer.newLine();
			writer.write("pistolaY=" + pistola_y);
			writer.newLine();
			writer.write("moneteX=" + monete_x);
			writer.newLine();
			writer.write("moneteY=" + monete_y);
			writer.newLine();
			writer.write("chiaveMaestraX=" + chiave_maestra_x);
			writer.newLine();
			writer.write("chiaveMaestraY=" + chiave_maestra_y);
			writer.newLine();
			writer.write("munizioniX=" + munizioni_x);
			writer.newLine();
			writer.write("munizioniY=" + munizioni_y);
			writer.newLine();
			writer.write("chiave1X=" + chiave1_x);
			writer.newLine();
			writer.write("chiave1Y=" + chiave1_y);
			writer.newLine();
			writer.write("chiave2X=" + chiave2_x);
			writer.newLine();
			writer.write("chiave2Y=" + chiave2_y);
			writer.newLine();
			writer.write("notaX=" + nota_x);
			writer.newLine();
			writer.write("notaY=" + nota_y);
			writer.newLine();
			writer.write("topo1=" + topo1);
			writer.newLine();
			writer.write("topo2=" + topo2);
			writer.newLine();
			writer.write("topo3=" + topo3);
			writer.newLine();
			writer.write("rata1=" + rata1);
			writer.newLine();
			writer.write("rata2=" + rata2);
			writer.newLine();

			for (int i = 0; i < sc.getLarghezza(); i++) {
			    for (int j = 0; j < sc.getAltezza(); j++) {
				writer.write(i + " " + j + "=" + paneles[i][j]);
				writer.newLine();
			    }
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
    	}

	//load
	public void caricaStato() {

		int bersaglioX=0;
		int bersaglioY=0;
		int bersaglioUltX=0;
		int bersaglioUltY=0;
		int getZaino=0;
		boolean isPistola=false;
		boolean isMonete=false;
		boolean isChiaveMaestra=false;
		boolean isMunizioni=false;
		boolean isChiave1=false;
		boolean isChiave2=false;
		boolean isNota=false;
		int pistolaX=0;
		int moneteX=-1;
		int chiaveMaestraX=-1;
		int munizioniX=0;
		int chiave1X=-1;
		int chiave2X=-1;
		int notaX=-1;
	        int pistolaY=0;
		int moneteY=-1;
		int chiaveMaestraY=-1;
		int munizioniY=1;
		int chiave1Y=-1;
		int chiave2Y=-1;
		int notaY=-1;
		boolean isTopo1=true;
		boolean isTopo2=true;
		boolean isTopo3=true;
		boolean isRata1=true;
		boolean isRata2=true;
		boolean[][] pannelli = new boolean[sc.getLarghezza()][sc.getLarghezza()];
		for(int l = 0; l<sc.getLarghezza(); l++) {
			for(int h = 0; h<sc.getAltezza(); h++) {
				pannelli[l][h]=true;
			}
		}
		pannelli[0][0]=false;
		pannelli[1][0]=false;
		pannelli[0][1]=false;

		try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_FILE))) {

			String line;
			while ((line = reader.readLine()) != null) {
			    	String[] partes = line.split("=");
			    	if (partes.length == 2) {
				       	String clave = partes[0];
					String valor = partes[1];
					switch (clave) {
						case "bersaglioX":
							bersaglioX = Integer.parseInt(valor);
							break;
						case "bersaglioY":
							bersaglioY = Integer.parseInt(valor);
							break;
						case "bersaglioUltX":
							bersaglioUltX = Integer.parseInt(valor);
							break;
						case "bersaglioUltY":
							bersaglioUltY = Integer.parseInt(valor);
							break;
						case "zaino":
							getZaino = Integer.parseInt(valor);
							break;
						case "pistola":
							isPistola = Boolean.parseBoolean(valor);
							break;
						case "monete":
							isMonete = Boolean.parseBoolean(valor);
							break;
						case "chiaveMaestra":
							isChiaveMaestra = Boolean.parseBoolean(valor);
							break;
						case "munizioni":
							isMunizioni = Boolean.parseBoolean(valor);
							break;
						case "chiave1":
							isChiave1 = Boolean.parseBoolean(valor);
							break;
						case "chiave2":
							isChiave2 = Boolean.parseBoolean(valor);
							break;
						case "nota":
							isNota = Boolean.parseBoolean(valor);
							break;
						case "pistolaX":
							pistolaX = Integer.parseInt(valor);
							break;
						case "pistolaY":
							pistolaY = Integer.parseInt(valor);
							break;
						case "moneteX":
							moneteX = Integer.parseInt(valor);
							break;
						case "moneteY":
							moneteY = Integer.parseInt(valor);
							break;
						case "chiaveMaestraX":
							chiaveMaestraX = Integer.parseInt(valor);
							break;
						case "chiaveMaestraY":
							chiaveMaestraY = Integer.parseInt(valor);
							break;
						case "munizioniX":
							munizioniX = Integer.parseInt(valor);
							break;
						case "munizioniY":
							munizioniY = Integer.parseInt(valor);
							break;
						case "chiave1X":
							chiave1X = Integer.parseInt(valor);
							break;
						case "chiave1Y":
							chiave1Y = Integer.parseInt(valor);
							break;
						case "chiave2X":
							chiave2X = Integer.parseInt(valor);
							break;
						case "chiave2Y":
							chiave2Y = Integer.parseInt(valor);
							break;
						case "notaX":
							notaX = Integer.parseInt(valor);
							break;
						case "notaY":
							notaY = Integer.parseInt(valor);
							break;
						case "topo1":
							isTopo1 = Boolean.parseBoolean(valor);
							break;
						case "topo2":
							isTopo2 = Boolean.parseBoolean(valor);
							break;
						case "topo3":
							isTopo3 = Boolean.parseBoolean(valor);
							break;
						case "rata1":
							isRata1 = Boolean.parseBoolean(valor);
							break;
						case "rata2":
							isRata2 = Boolean.parseBoolean(valor);
							break;
						case "0 0":
							pannelli[0][0] = Boolean.parseBoolean(valor);
							break;
						case "0 1":
							pannelli[0][1] = Boolean.parseBoolean(valor);
							break;
						case "0 2":
							pannelli[0][2] = Boolean.parseBoolean(valor);
							break;
						case "0 3":
							pannelli[0][3] = Boolean.parseBoolean(valor);
							break;
						case "0 4":
							pannelli[0][4] = Boolean.parseBoolean(valor);
							break;
						case "0 5":
							pannelli[0][5] = Boolean.parseBoolean(valor);
							break;
						case "0 6":
							pannelli[0][6] = Boolean.parseBoolean(valor);
							break;
						case "1 0":
							pannelli[1][0] = Boolean.parseBoolean(valor);
							break;
						case "1 1":
							pannelli[1][1] = Boolean.parseBoolean(valor);
							break;
						case "1 2":
							pannelli[1][2] = Boolean.parseBoolean(valor);
							break;
						case "1 3":
							pannelli[1][3] = Boolean.parseBoolean(valor);
							break;
						case "1 4":
							pannelli[1][4] = Boolean.parseBoolean(valor);
							break;
						case "1 5":
							pannelli[1][5] = Boolean.parseBoolean(valor);
							break;
						case "1 6":
							pannelli[1][6] = Boolean.parseBoolean(valor);
							break;
						case "2 0":
							pannelli[2][0] = Boolean.parseBoolean(valor);
							break;
						case "2 1":
							pannelli[2][1] = Boolean.parseBoolean(valor);
							break;
						case "2 2":
							pannelli[2][2] = Boolean.parseBoolean(valor);
							break;
						case "2 3":
							pannelli[2][3] = Boolean.parseBoolean(valor);
							break;
						case "2 4":
							pannelli[2][4] = Boolean.parseBoolean(valor);
							break;
						case "2 5":
							pannelli[2][5] = Boolean.parseBoolean(valor);
							break;
						case "2 6":
							pannelli[2][6] = Boolean.parseBoolean(valor);
							break;
						case "3 0":
							pannelli[3][0] = Boolean.parseBoolean(valor);
							break;
						case "3 1":
							pannelli[3][1] = Boolean.parseBoolean(valor);
							break;
						case "3 2":
							pannelli[3][2] = Boolean.parseBoolean(valor);
							break;
						case "3 3":
							pannelli[3][3] = Boolean.parseBoolean(valor);
							break;
						case "3 4":
							pannelli[3][4] = Boolean.parseBoolean(valor);
							break;
						case "3 5":
							pannelli[3][5] = Boolean.parseBoolean(valor);
							break;
						case "3 6":
							pannelli[3][6] = Boolean.parseBoolean(valor);
							break;
						case "4 0":
							pannelli[4][0] = Boolean.parseBoolean(valor);
							break;
						case "4 1":
							pannelli[4][1] = Boolean.parseBoolean(valor);
							break;
						case "4 2":
							pannelli[4][2] = Boolean.parseBoolean(valor);
							break;
						case "4 3":
							pannelli[4][3] = Boolean.parseBoolean(valor);
							break;
						case "4 4":
							pannelli[4][4] = Boolean.parseBoolean(valor);
							break;
						case "4 5":
							pannelli[4][5] = Boolean.parseBoolean(valor);
							break;
						case "4 6":
							pannelli[4][6] = Boolean.parseBoolean(valor);
							break;
						case "5 0":
							pannelli[5][0] = Boolean.parseBoolean(valor);
							break;
						case "5 1":
							pannelli[5][1] = Boolean.parseBoolean(valor);
							break;
						case "5 2":
							pannelli[5][2] = Boolean.parseBoolean(valor);
							break;
						case "5 3":
							pannelli[5][3] = Boolean.parseBoolean(valor);
							break;
						case "5 4":
							pannelli[5][4] = Boolean.parseBoolean(valor);
							break;
						case "5 5":
							pannelli[5][5] = Boolean.parseBoolean(valor);
							break;
						case "5 6":
							pannelli[5][6] = Boolean.parseBoolean(valor);
							break;
						case "6 0":
							pannelli[6][0] = Boolean.parseBoolean(valor);
							break;
						case "6 1":
							pannelli[6][1] = Boolean.parseBoolean(valor);
							break;
						case "6 2":
							pannelli[6][2] = Boolean.parseBoolean(valor);
							break;
						case "6 3":
							pannelli[6][3] = Boolean.parseBoolean(valor);
							break;
						case "6 4":
							pannelli[6][4] = Boolean.parseBoolean(valor);
							break;
						case "6 5":
							pannelli[6][5] = Boolean.parseBoolean(valor);
							break;
						case "6 6":
							pannelli[6][6] = Boolean.parseBoolean(valor);
							break;
					}
				}
			}
        	} catch (IOException e) {
            		e.printStackTrace();
        	}

		this.bersaglio = new Posizione(bersaglioX, bersaglioY);
		this.bersaglio_ult = new Posizione(bersaglioUltX, bersaglioUltY);

		Posizione p1 = new Posizione(6,0);
		Posizione p2 = new Posizione(0,6);
		Posizione p3 = new Posizione(3,0);
		Posizione p4 = new Posizione(3,6);
		Posizione p5 = new Posizione(6,6);

		if(!isTopo1) sc.getPannello(p1).removeElemento(provaTalpaGoffa);
		else if(!sc.getPannello(p1).hayElemento(provaTalpaGoffa)){
			PannelloBase provaPannelloTalpaGoffa = sc.getPannello(6, 0);
			provaPannelloTalpaGoffa.addElemento(provaTalpaGoffa);
			addPannello(provaPannelloTalpaGoffa);
		}
		if(!isTopo2) sc.getPannello(p2).removeElemento(provaTalpaCieca);
		else if(!sc.getPannello(p2).hayElemento(provaTalpaCieca)){
			PannelloBase provaPannelloTalpaCieca = sc.getPannello(0, 6);
			provaPannelloTalpaCieca.addElemento(provaTalpaCieca);
			addPannello(provaPannelloTalpaCieca);
		}
		if(!isRata1) sc.getPannello(p3).removeElemento(provaTopo);
		else if(!sc.getPannello(p3).hayElemento(provaTopo)){
			PannelloBase provaPannelloTopo = sc.getPannello(3, 0);
			provaPannelloTopo.addElemento(provaTopo);
			addPannello(provaPannelloTopo);
		}
		if(!isRata2) sc.getPannello(p4).removeElemento(provaTopo1);
		else if(!sc.getPannello(p4).hayElemento(provaTopo1)){
			PannelloBase provaPannelloTopo1 = sc.getPannello(3, 6);
			provaPannelloTopo1.addElemento(provaTopo1);
			addPannello(provaPannelloTopo1);
		}
		if(!isTopo3) sc.getPannello(p5).removeElemento(provaTalpaIntelligente);
		else if(!sc.getPannello(p5).hayElemento(provaTalpaIntelligente)){
			PannelloBase provaPannelloTalpaIntelligente = sc.getPannello(6, 6);
			provaPannelloTalpaIntelligente.addElemento(provaTalpaIntelligente);
			addPannello(provaPannelloTalpaIntelligente);
		}

		Posizione p;

		if(!this.pistola) sc.getPannello(provaPistola.getPannello().getPosi()).removeElemento(provaPistola);
		if(!isPistola){
			p = new Posizione(pistolaX, pistolaY);
			PannelloBase pannelloPistola = sc.getPannello(p);
			pannelloPistola.addElemento(provaPistola);
			addPannello(pannelloPistola);
		}

		if(!this.munizioni) sc.getPannello(pruebaMunicion.getPannello().getPosi()).removeElemento(pruebaMunicion);
		if(!isMunizioni){
			p = new Posizione(munizioniX, munizioniY);
			PannelloBase pannelloMunizioni = sc.getPannello(p);
			pannelloMunizioni.addElemento(pruebaMunicion);
			addPannello(pannelloMunizioni);
		}

		if(!this.monete){
			if(provaMoneta.getPannello()!=null) sc.getPannello(provaMoneta.getPannello().getPosi()).removeElemento(provaMoneta);
		}
		if(!isMonete){
			if(moneteX!=-1 && moneteY!=-1){
				p = new Posizione(moneteX, moneteY);
				PannelloBase pannelloMoneta = sc.getPannello(p);
				pannelloMoneta.addElemento(provaMoneta);
				addPannello(pannelloMoneta);
			}
		}


		if(!this.nota){
			if(provaNota.getPannello()!=null) sc.getPannello(provaNota.getPannello().getPosi()).removeElemento(provaNota);
		}
		if(!isNota){
			if(notaX!=-1 && notaY!=-1){
				p = new Posizione(notaX, notaY);
				PannelloBase pannelloNota = sc.getPannello(p);
				pannelloNota.addElemento(provaNota);
				addPannello(pannelloNota);
			}
		}

		if(!this.chiave_maestra){
			if(provaChiaveMaestra.getPannello()!=null) sc.getPannello(provaChiaveMaestra.getPannello().getPosi()).removeElemento(provaChiaveMaestra);
		}
		if(!isChiaveMaestra){
			if(chiaveMaestraX!=-1 && chiaveMaestraY!=-1){
				p = new Posizione(chiaveMaestraX, chiaveMaestraY);
				PannelloBase pannelloChiaveMaestra = sc.getPannello(p);
				pannelloChiaveMaestra.addElemento(provaChiaveMaestra);
				addPannello(pannelloChiaveMaestra);
			}
		}

		if(!this.chiave1){
			if(provaChiave1.getPannello()!=null) sc.getPannello(provaChiave1.getPannello().getPosi()).removeElemento(provaChiave1);
		}
		if(!isChiave1){
			if(chiave1X!=-1 && chiave1Y!=-1){
				p = new Posizione(chiave1X, chiave1Y);
				PannelloBase pannelloChiave1 = sc.getPannello(p);
				pannelloChiave1.addElemento(provaChiave1);
				addPannello(pannelloChiave1);
			}
		}

		if(!this.chiave2){
			if(provaChiave2.getPannello()!=null) sc.getPannello(provaChiave2.getPannello().getPosi()).removeElemento(provaChiave2);
		}
		if(!isChiave2){
			if(chiave2X!=-1 && chiave2Y!=-1){
				p = new Posizione(chiave2X, chiave2Y);
				PannelloBase pannelloChiave2 = sc.getPannello(p);
				pannelloChiave2.addElemento(provaChiave2);
				addPannello(pannelloChiave2);
			}
		}

		for(int l = 0; l<sc.getLarghezza(); l++) {
			for(int h = 0; h<sc.getAltezza(); h++) {
				p = new Posizione(l,h);
				if(!pannelli[l][h]){
					while(sc.getPannello(l,h).getStato()==Stato.SOLLEVATO){
						sc.abbatterePannello(p);
					}
				}
				else sc.sollevarePannello(p);
			}
		}

		this.zaino = getZaino;
		this.pistola = isPistola;
		this.monete = isMonete;
		this.chiave_maestra = isChiaveMaestra;
		this.munizioni = isMunizioni;
		this.chiave1 = isChiave1;
		this.chiave2 = isChiave2;
		this.nota = isNota;
    	}

	public boolean isInGioco() {
		return !this.exit && !this.win;
	}

	public void exit(){
		this.exit = true;
	}

	public void win(){
		this.win = true;
	}

	// Prepare game
	public void prepare(){
		int  n, i, j;

		// Anadimos paneles de forma aleatoria
		for(int l = 0; l<sc.getLarghezza(); l++) {
			for(int h = 0; h<sc.getAltezza(); h++) {
				int lmax = sc.getLarghezza();
				int hmax = sc.getAltezza();
				if((l==1 && h!=0 && h!=4 && h!=6 && h!=5) || (l==2 && h!=2 && h!=3 && h!=4) || (l==4 && h!=2 && h!=3 && h!=4) || (l==5 && h!=0 && h!=2 && h!=6 && h!=1)) n=8;
				else if((l==0 && h==6) || (l==6 && h==0)) n=2;
				else if((l==6 && h==6)) n=0;
				else if((l==3 && h==0) || (l==3 && h==6)) n=1;
				else if((l==3 && h==1) || (l==3 && h==5) || (l==6 && h==5)) n=4;
				else if(l==5 && h==6) n=5;
				else if(l==1 && h==5) n=6;
				else if(l==5 && h==1) n=7;
				else n=3;
				switch(n) {
				case 0 :
					PannelloResistente pannelloResistente = new PannelloResistente(l,h);
					addPannello(pannelloResistente);
					break;
				case 1 :
					PannelloAleatorio pannelloAleatorio = new PannelloAleatorio(l,h);
					addPannello(pannelloAleatorio);
					break;
				case 2 :
					PannelloDebole pannelloDebole = new PannelloDebole(l,h);
					addPannello(pannelloDebole);
					break;
				case 3 :
					PannelloBase pannelloBase = new PannelloBase(l,h);
					addPannello(pannelloBase);
					break;
				case 4:
					Porta porta = new Porta(l,h);
					addPannello(porta);
					break;
				case 5:
					Lucchetto candado = new Lucchetto(l,h);
					addPannello(candado);
					break;
				case 6:
					Flecha1 flecha1 = new Flecha1(l,h);
					addPannello(flecha1);
					break;
				case 7:
					Flecha2 flecha2 = new Flecha2(l,h);
					addPannello(flecha2);
					break;
				default:
					Blocco blocco = new Blocco(l,h);
					addPannello(blocco);
					break;
				}
			}
		}

		PannelloMenu menu = new PannelloMenu(0,0);
		addPannelloMenu(menu);

		/*----------------------------------------------------------*/
		// ELEMENTOS PASIVOS

		// Anadimos Municiones
		PannelloBase provaPannelloMunizioni = sc.getPannello(0, 1);
		provaPannelloMunizioni.addElemento(pruebaMunicion);
		addPannello(provaPannelloMunizioni);

		// Anadimos pistola
		PannelloBase provaPannelloPistola = sc.getPannello(0, 0);
		provaPannelloPistola.addElemento(provaPistola);
		addPannello(provaPannelloPistola);

		// Anadimos tesoro
		Tesoro provaTesoro = new Tesoro(0);
		PannelloBase provaPannelloTesoro = sc.getPannello(5, 6);
		provaPannelloTesoro.addElemento(provaTesoro);
		addPannello(provaPannelloTesoro);

		// ELEMENTOS ACTIVOS
		//Anadimos 2 Topos
		i = sc.getLarghezza()/2;
		j = 0;
		PannelloBase provaPannelloTopo = sc.getPannello(i, j);
		provaPannelloTopo.addElemento(provaTopo);
		addPannello(provaPannelloTopo);
		i = sc.getLarghezza()/2;
		j = sc.getAltezza()-1;
		PannelloBase provaPannelloTopo1 = sc.getPannello(i, j);
		provaPannelloTopo1.addElemento(provaTopo1);
		addPannello(provaPannelloTopo1);

		// Anadimos TalpaGoffa
		i = sc.getLarghezza()-1;
		j = 0;
		PannelloBase provaPannelloTalpaGoffa = sc.getPannello(i, j);
		provaPannelloTalpaGoffa.addElemento(provaTalpaGoffa);
		addPannello(provaPannelloTalpaGoffa);

		// Anadimos TalpaCieca
		i = 0;
		j = sc.getAltezza()-1;
		PannelloBase provaPannelloTalpaCieca = sc.getPannello(i, j);
		provaPannelloTalpaCieca.addElemento(provaTalpaCieca);
		addPannello(provaPannelloTalpaCieca);

		// Anadimos TalpaIntelligente
		i = sc.getLarghezza()-1;
		j = sc.getAltezza()-1;
		PannelloBase provaPannelloTalpaIntelligente = sc.getPannello(i, j);
		provaPannelloTalpaIntelligente.addElemento(provaTalpaIntelligente);
		addPannello(provaPannelloTalpaIntelligente);

		sc.abbatterePannello(this.bersaglio);
		sc.abbatterePannello(this.bersaglio.getVicina(Direzione.SOPRA));
		sc.abbatterePannello(this.bersaglio.getVicina(Direzione.DESTRA));
	}

	/* Stories */
	public void start(){
		System.out.println();
		System.out.println("Anno 20XX.");
		System.out.println();
		System.out.println("Quello delle talpe romane e' il piu' grande impero nel mondo delle talpe.");
		System.out.println();
		System.out.println("E' riuscito ad imporre il suo dominio in tutto il continente europtalpeo soppraffacendo, nel corso dei secoli, tutti coloro che hanno cercato di abbatterlo.");
		System.out.println();
		System.out.println("L'Impero delle talpe Mongole comandato da Gengis Talp, il Sacro Talpo Impero guidato da Talpo magno e il Regno delle Due Talpilie sono solo alcuni dei potenti nemici che sono riusciti ad abbattere.");
		System.out.println();
		System.out.println("Tuttavia, dopo cinquanta anni di pace, il malvagio Giorgio Talponi con un colpo di stato, aiutato da un esercito di topi mercenari, e' riuscito a prendere il potere marciando sulla capitale.");
		System.out.println();
		System.out.println("Da allora sono passati vent'anni durante i quali Giorgio Talponi ha imposto la sua dittatura spietata.");
		System.out.println();
		System.out.println("Ma e' nei momenti piu' bui che l'indomabile spirito di liberta' delle talpe si manifesta prorompente negli individui piu speciali!");
		System.out.println();
		System.out.println("Sei pronto a riportare la speranza nei cuori delle talpe di tutto l'impero? S/N");
		boolean done = false;
		while (!done)
		{
			String cmd = in.nextLine();
			if (cmd.equalsIgnoreCase("n"))
				System.out.println("(non fare il fifone). Scrivi S");
			else if (cmd.equalsIgnoreCase("s")) {
				System.out.println("Dopo molte fatiche riesci ad intrufolarti nella base di Giorgio Talponi ma sei sfinito. Ti arrendi? S/N");
				String cmd2 = in.nextLine();
				boolean fatto = false;
				while (!fatto) {

					if (cmd2.equalsIgnoreCase("n")) {
						System.out.println("Come immaginavo. Coraggio eroe, riporta la liberta' al tuo popolo!");
						fatto = true;
					}
					else if (cmd2.equalsIgnoreCase("s")) {
						System.out.println("---stai per arrenderti ma il pensiero dei tuoi cari che soffrono ti riempie di adrenalina---");
						System.out.println("Ti vuoi ancora arrendere?");
						cmd2 = in.nextLine();
					}
					else {
						System.out.println("Comando non riconosciuto! Inserire S/N");
						cmd2 = in.nextLine();
					}
				}
				done = true;
			}
			else System.out.println("Comando non riconosciuto! Inserire S/N");

		}
	}

	public void interagire(){

		System.out.println();
		System.out.println("///////////////////////////////////////////////");
		System.out.println("................................................");
		System.out.println();
		if(!this.sc.isVisibile(this.bersaglio)){
			if(this.bersaglio.getX()==6 && this.bersaglio.getY()==6)
				System.out.println("QUESTO E' UN PANNELLO EXTRA RESISTENTE. Chissa' cosa si nasconde dietro...");
			else if(this.bersaglio.getX()==0 && this.bersaglio.getY()==6)
				System.out.println("QUESTO E UN PANNELLO PIU' RESISTENTE. Chissa' cosa si nasconde dietro...");
			else if(this.bersaglio.getX()==6 && this.bersaglio.getY()==0)
				System.out.println("QUESTO E UN PANNELLO PIU' RESISTENTE. Chissa' cosa si nasconde dietro...");
			else if(this.bersaglio.getX()==3 && this.bersaglio.getY()==0)
				System.out.println("QUESTO E UN PANNELLO CON RESISTENZA ALEATORIA. Chissa' cosa si nasconde dietro...");
			else if(this.bersaglio.getX()==3 && this.bersaglio.getY()==6)
				System.out.println("QUESTO E' UN PANNELLO CON RESISTENZA ALEATORIA. Chissa' cosa si nasconde dietro...");
			else System.out.println("QUESTO E' UN PANNELLO DEBOLE. VIENE ABBATTUTO CON UN SOLO COLPO. Non dovrebbe esserci molto dietro...");
		}
		else{
			if(sc.getPannello(this.bersaglio).hayElemento(provaTopo) || sc.getPannello(this.bersaglio).hayElemento(provaTopo1))
				System.out.println("ATTENZIONE!!! Hai trovato un topo nemico!!!");
			if(sc.getPannello(this.bersaglio).hayElemento(provaTalpaIntelligente)) {
				System.out.println("ATTENZIONE!!! Hai trovato la GIORGIO TALPONI!!!");
				System.out.println();
				System.out.println("GIORGIO TALPONI: 'M...Ma...Ma come e' p-possibile??? TU chi sei? Come hai fatto a trovarmi? Comunque sia, non riuscirai a sconfiggermi!'");
				System.out.println();
				System.out.println("Sconfiggila, dovrebbe sapere come si apre la cassaforte!");
			}
			if(sc.getPannello(this.bersaglio).hayElemento(provaTalpaGoffa) || sc.getPannello(this.bersaglio).hayElemento(provaTalpaCieca))
				System.out.println("Sotto al pannello che hai appena distrutto era intrappolata una talpa. Speriamo che ti sia riconoscente...");
			if(sc.getPannello(this.bersaglio).hayElemento(provaPistola))
			  System.out.println("A terra sembra esserci un'arma, potrebbe servirti...");
		  if(sc.getPannello(this.bersaglio).hayElemento(pruebaMunicion))
			  System.out.println("Sembra che ci siano delle munizioni per terra...");
			if(sc.getPannello(this.bersaglio).hayElemento(provaMoneta))
				System.out.println("Hai visto uno strano luccichio per terra... come se ci fossere delle monete d'oro...");
			if(sc.getPannello(this.bersaglio).hayElemento(provaNota))
     	  System.out.println("Sembra ci sia un foglio di carta stroppicciato a terra...");
			if(sc.getPannello(this.bersaglio).hayElemento(provaChiaveMaestra))
				System.out.println("A terra si trova una chiave molto sfarzosa...");//provaChiave1
			if(sc.getPannello(this.bersaglio).hayElemento(provaChiave1) || sc.getPannello(this.bersaglio).hayElemento(provaChiave2))
				System.out.println("Per terra si trova una chiave d'argento...");
		}

		System.out.println("Cosa vuoi fare?");
		if(this.sc.isValida(bersaglio.getVicina(Direzione.DESTRA)))
			System.out.println("d - ANDARE A DESTRA");
		if(this.sc.isValida(bersaglio.getVicina(Direzione.SINISTRA)))
			System.out.println("a - ANDARE A SINISTRA");
		if(this.sc.isValida(bersaglio.getVicina(Direzione.SOPRA)))
			System.out.println("w - ANDARE SOPRA");
		if(this.bersaglio.getX()==5 && this.bersaglio.getY()==0)
			System.out.println("w -PERCORRERE IL PASSAGGIO SEGRETO");
		if(this.sc.isValida(bersaglio.getVicina(Direzione.SOTTO)))
			System.out.println("s - ANDARE SOTTO");
		if(this.bersaglio.getX()==1 && this.bersaglio.getY()==6)
			System.out.println("s - PERCORRERE IL PASSAGGIO SEGRETO");
		if(this.bersaglio.getX()==5 && this.bersaglio.getY()==6 && this.sc.isVisibile(this.bersaglio) && this.chiave_maestra)
			System.out.println("usare chiave maestra - PER APRIRE IL FORZIERE");
		if(this.sc.isVisibile(this.bersaglio) && (sc.getPannello(this.bersaglio).hayElemento(provaTalpaGoffa) || sc.getPannello(this.bersaglio).hayElemento(provaTalpaCieca)))
			System.out.println("parlare - PER INTERAGIRE");
		if(this.pistola==true && munizioni==true && (!this.sc.isVisibile(this.bersaglio) || sc.getPannello(this.bersaglio).hayElemento(provaTalpaIntelligente) || sc.getPannello(this.bersaglio).hayElemento(provaTopo) || sc.getPannello(this.bersaglio).hayElemento(provaTopo1)))
			System.out.println("f - PER SPARARE");
		if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(pruebaMunicion))
			System.out.println("prendere munizioni - PER RACCOGLIERE LE MUNIZIONI");
		if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaPistola))
			System.out.println("prendere pistola - PER RACCOGLIERE LA PISTOLA");
		if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaMoneta))
			System.out.println("prendere monete - PER PRENDERE LE MONETE");
		if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaNota))
			System.out.println("prendere nota - PER PRENDERE LA NOTA");
		if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaChiaveMaestra))
			System.out.println("prendere chiave maestra - PER PRENDERE LA CHIAVE MAESTRA");
		if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaChiave1))
			System.out.println("prendere chiave1 - PER PRENDERE LA CHIAVE 1");
		if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaChiave2))
			System.out.println("prendere chiave2 - PER PRENDERE LA CHIAVE 2");
		if(this.munizioni==true && this.sc.isVisibile(this.bersaglio)) System.out.println("lasciare munizioni - PER LASCIARE LE MUNIZIONI");
		if(this.pistola==true && this.sc.isVisibile(this.bersaglio)) System.out.println("lasciare pistola - PER LASCIARE LA PISTOLA");
		if(this.monete==true) System.out.println("lasciare monete - PER LASCIARE LE MONETE");
		if(this.nota==true) System.out.println("lasciare nota - PER LASCIARE LA NOTA");
		if(this.chiave_maestra==true) System.out.println("lasciare chiave maestra - PER LASCIARE LA CHIAVE MAESTRA");
		if(this.chiave1==true) System.out.println("lasciare chiave1 - PER LASCIARE LA CHIAVE 1");
		if(this.chiave2==true) System.out.println("lasciare chiave2 - PER LASCIARE LA CHIAVE 2");
		if(this.nota==true) System.out.println("leggere nota - PER LEGGERE LA NOTA");
		System.out.println("b - PER TORNARE ALL'ULTIMA CASELLA VISITATA");
		System.out.println("menu - PER METTERE IN PAUSA IL GIOCO");
		System.out.println();

		/*QUITAR QUE SE PUEDA ESCRIBIR LO QUE SEA EN TODO MOMENTO*/

		boolean done = false;
		boolean done2;

		while (!done){
			if (in.hasNext()) {
				String cmd = in.nextLine();
				if (cmd.equalsIgnoreCase("d") && this.sc.isValida(bersaglio.getVicina(Direzione.DESTRA))){
					spostareBersaglio(Direzione.DESTRA);
					done = true;
				}
				else if (cmd.equalsIgnoreCase("a") && this.sc.isValida(bersaglio.getVicina(Direzione.SINISTRA))){
					spostareBersaglio(Direzione.SINISTRA);
					if(this.bersaglio.getX()==5 && this.bersaglio.getY()==6 && !this.sc.isVisibile(this.bersaglio)){
						System.out.println("Inserisci una password:");
						String cmd2 = in.next();
						if (cmd2.equalsIgnoreCase(password)){
							System.out.println("Password corretta!!");
							System.out.println();
							sc.abbatterePannello(this.bersaglio);
							System.out.println("Hmm... all'interno della cassaforte c'e' un forziere... quante precauzioni...");
							System.out.println();
							System.out.println("Comunque sia, sembra che ci voglia una chiave diversa dalle precedenti per aprire il forziere...");
						}
						else {
							System.out.println("Password errata!! Ritenta.");
							spostareBersaglio(Direzione.DESTRA);
						}
					}
					done = true;
				}
				else if (cmd.equalsIgnoreCase("w") && (this.sc.isValida(bersaglio.getVicina(Direzione.SOPRA)) || (this.bersaglio.getX()==5 && this.bersaglio.getY()==0))){
					boolean farlo = true;
					if(this.bersaglio.getX()==6 && this.bersaglio.getY()==4 && sc.getPannello(this.bersaglio.getX(),this.bersaglio.getY()+1).getStato()==Stato.SOLLEVATO){
						System.out.println("Ti trovi davanti ad una porta blindata");
						System.out.println("Da dietro senti una voce:");
						System.out.println("TOPO GUARDIA: Hey so cosa vuoi fare, ma questa porta e' inespugnabile... però la mia mano potrebbe scivolare e aprirla per il giusto prezzo eheheh");
						System.out.println();


						if(monete){
							System.out.println("Cosa vuoi fare?");
							System.out.println("pagare - PER APRIRE LA PORTA, PAGANDO");
							System.out.println("annulla - PER TORNARE INDIETRO");
							System.out.println();
							String cmd2 = in.nextLine();
							done2 = false;
							while (!done2){
								if (cmd2.equalsIgnoreCase("pagare")){
									sc.abbatterePannello(this.bersaglio.getVicina(Direzione.SOPRA));
									this.monete=false;
									zaino--;
									done2 = true;
								}
								else if(cmd2.equalsIgnoreCase("annulla")){
									farlo = false;
									done2 = true;
								}
								else{
									System.out.println("Comando non riconosciuto!");
									System.out.println("Cosa vuoi fare?");
									System.out.println("pagare - PER APRIRE LA PORTA, PAGANDO");
									System.out.println("annulla - PER TORNARE INDIETRO");
									System.out.println();
									cmd2 = in.nextLine();
								}
							}
						}
						else {
							System.out.println("Non hai soldi con te, ma potresti prenderli a qualche guardia...");
							farlo = false;
						}
					}
					else if(this.bersaglio.getX()==3 && this.bersaglio.getY()==4 && sc.getPannello(this.bersaglio.getX(),this.bersaglio.getY()+1).getStato()==Stato.SOLLEVATO){
						if(chiave1){
							System.out.println("C'e una porta davanti a te con un numero 1 disegnato sopra, cosa vuoi fare?");
							System.out.println("usare chiave1 - PER APRIRE LA PORTA CON LA CHIAVE 1");
							System.out.println("annulla - PER TORNARE INDIETRO");
							System.out.println();

							done2 = false;
							String cmd2 = in.nextLine();
							while (!done2){
								if (cmd2.equalsIgnoreCase("usare chiave1")){
									System.out.println("La porta si apre...");
									sc.abbatterePannello(this.bersaglio.getVicina(Direzione.SOPRA));
									done2 = true;
								}
								else if(cmd2.equalsIgnoreCase("annulla")){
									farlo = false;
									done2 = true;
								}
								else{
									System.out.println("Comando non riconosciuto!");
									System.out.println("Cosa vuoi fare?");
									System.out.println("usare chiave1 - PER APRIRE LA PORTA CON LA CHIAVE 1");
									System.out.println("annulla - PER TORNARE INDIETRO");
									System.out.println();
									cmd2 = in.nextLine();
								}
							}
						}
						else {
							System.out.println("davanti a te c'e' una porta, ma sembra essere chiusa a chiave... ha un numero 1 disegnato sopra");
							farlo = false;
						}
					}

					if(farlo) spostareBersaglio(Direzione.SOPRA);
					done = true;
				}
				else if (cmd.equalsIgnoreCase("s") && (this.sc.isValida(bersaglio.getVicina(Direzione.SOTTO)) || (this.bersaglio.getX()==1 && this.bersaglio.getY()==6))){
					boolean farlo = true;
					if(this.bersaglio.getX()==3 && this.bersaglio.getY()==2 && sc.getPannello(this.bersaglio.getX(),this.bersaglio.getY()-1).getStato()==Stato.SOLLEVATO){
						if(chiave2){
							System.out.println("C'e una porta davanti a te con un numero 2 disegnato sopra, cosa vuoi fare?");
							System.out.println("usare chiave2 - PER APRIRE LA PORTA CON LA CHIAVE 2");
							System.out.println("annulla - PER TORNARE INDIETRO");
							System.out.println();

							done2 = false;
							String cmd2 = in.nextLine();
							while (!done2){
								if (cmd2.equalsIgnoreCase("usare chiave2")){
									System.out.println("La porta si apre...");
									sc.abbatterePannello(this.bersaglio.getVicina(Direzione.SOTTO));
									done2 = true;
								}
								else if(cmd2.equalsIgnoreCase("annulla")){
									farlo = false;
									done2 = true;
								}
								else{
									System.out.println("Comando non riconosciuto!");
									System.out.println("Cosa vuoi fare?");
									System.out.println("usare chiave2 - PER APRIRE LA PORTA CON LA CHIAVE 2");
									System.out.println("annulla - PER TORNARE INDIETRO");
									System.out.println();
									cmd2 = in.nextLine();
								}
							}
						}
						else {
							System.out.println("davanti a te c'e' una porta, ma sembra essere chiusa a chiave... ha un numero 2 disegnato sopra");
							farlo = false;
						}
					}
					if(farlo) spostareBersaglio(Direzione.SOTTO);
					done = true;
				}
				else if (cmd.equalsIgnoreCase("usare chiave maestra") && this.bersaglio.getX()==5 && this.bersaglio.getY()==6 && this.sc.isVisibile(this.bersaglio) && this.chiave_maestra){
					win();
					done = true;
				}
				else if (cmd.equalsIgnoreCase("parlare") && this.sc.isVisibile(this.bersaglio) && ((this.bersaglio.getY()==6 && this.bersaglio.getX()==0 && chiave1==false) || (this.bersaglio.getX()==6 && this.bersaglio.getY()==0 && chiave2==false))){
					if(this.bersaglio.getX()==0 && this.bersaglio.getY()==6){
						sparare();
						System.out.println("TALPA: 'Grazie per avermi liberato dal pannello! Ti prego, liberaci da GIORGIO TALPONI e dai suoi scagnozzi. Questa potra' servirti.'");
						System.out.println("~La talpa se ne e' andata, lasciandoti qualcosa~");
						PannelloBase pannelloChiave1 = sc.getPannello(this.bersaglio);
						pannelloChiave1.addElemento(provaChiave1);
						addPannello(pannelloChiave1);
					}
					else if(this.bersaglio.getX()==6 && this.bersaglio.getY()==0){
						sparare();
						System.out.println("TALPA: 'Grazie per avermi liberato dal pannello! Ti prego, liberaci da GIORGIO TALPONI e dai suoi scagnozzi. Questa potra' servirti.'");
						System.out.println("~La talpa se ne e' andata, lasciandoti qualcosa~");
						PannelloBase pannelloChiave2 = sc.getPannello(this.bersaglio);
						pannelloChiave2.addElemento(provaChiave2);
						addPannello(pannelloChiave2);
					}
					done = true;
				}
				else if (cmd.equalsIgnoreCase("f") && this.pistola==true && munizioni==true && ((this.sc.isVisibile(this.bersaglio) && this.bersaglio.getX()==3 && this.bersaglio.getY()==6 && sc.getPannello(this.bersaglio).hayElemento(provaTopo1)) || (this.sc.isVisibile(this.bersaglio) && this.bersaglio.getX()==3 && this.bersaglio.getY()==0 && sc.getPannello(this.bersaglio).hayElemento(provaTopo)) || (this.sc.isVisibile(this.bersaglio) && this.bersaglio.getX()==6 && this.bersaglio.getY()==6) || (!this.sc.isVisibile(this.bersaglio)))){
					if(this.sc.isVisibile(this.bersaglio) && this.bersaglio.getX()==3 && this.bersaglio.getY()==6 && sc.getPannello(this.bersaglio).hayElemento(provaTopo1)){
						sparare();
						System.out.println("Hai sconfitto il topo. inoltre sembra che abbia lasciato cadere qualcosa!");
						// Anadimos Moneda
						PannelloBase pannelloMoneta = sc.getPannello(this.bersaglio);
						pannelloMoneta.addElemento(provaMoneta);
						addPannello(pannelloMoneta);
						done = true;
					}
					else if(this.sc.isVisibile(this.bersaglio) && this.bersaglio.getX()==3 && this.bersaglio.getY()==0 && sc.getPannello(this.bersaglio).hayElemento(provaTopo)){
						sparare();
						System.out.println("Hai sconfitto il topo. Qualcosa di appariscente attira il tuo sguardo.");
						//Anadimos Chiave maestra
						PannelloBase pannelloChiaveMaestra = sc.getPannello(this.bersaglio);
						pannelloChiaveMaestra.addElemento(provaChiaveMaestra);
						addPannello(pannelloChiaveMaestra);
						done = true;
					}
					else if(this.sc.isVisibile(this.bersaglio) && this.bersaglio.getX()==6 && this.bersaglio.getY()==6){
						sparare();
						System.out.println("TU: 'Beh, e' stato facile, in realta'...");
						System.out.println();
						System.out.println("Hai sconfitto la GIORGIO TALPONI, qualcosa gli cade dalla tasca...");
						// Anadimos nota
						PannelloBase pannelloNota = sc.getPannello(this.bersaglio);
						pannelloNota.addElemento(provaNota);
						addPannello(pannelloNota);
						done = true;
					}
					else if (!this.sc.isVisibile(this.bersaglio)){
						sparare();
						done = true;
					}
				}
				else if (cmd.equalsIgnoreCase("prendere munizioni") && this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(pruebaMunicion)){
					if(zaino==3){
					 	System.out.println("Lo zaino è pieno. Non puoi tenerle...");
					}
					else{
						munizioni = true;
						zaino++;
						sc.getPannello(this.bersaglio).removeElemento(pruebaMunicion);
					}
					done = true;
				}
				else if (cmd.equalsIgnoreCase("prendere chiave maestra") && this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaChiaveMaestra)){
					if(zaino==3){
					 	System.out.println("Lo zaino è pieno. Non puoi prenderla...");
					}
					else{
						chiave_maestra = true;
						zaino++;
						sc.getPannello(this.bersaglio).removeElemento(provaChiaveMaestra);
					}
					done = true;
				}
				else if (cmd.equalsIgnoreCase("prendere chiave1") && this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaChiave1)){
					if(zaino==3){
					 	System.out.println("Lo zaino è pieno. Non puoi prenderla...");
					}
					else{
						chiave1 = true;
						zaino++;
						sc.getPannello(this.bersaglio).removeElemento(provaChiave1);
					}
					done = true;
				}
				else if (cmd.equalsIgnoreCase("prendere chiave2") && this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaChiave2)){
					if(zaino==3){
					 	System.out.println("Lo zaino è pieno. Non puoi prenderla...");
					}
					else{
						chiave2 = true;
						zaino++;
						sc.getPannello(this.bersaglio).removeElemento(provaChiave2);
					}
					done = true;
				}
				else if(cmd.equalsIgnoreCase("prendere monete") && this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaMoneta)){
					if(zaino==3){
					 	System.out.println("Lo zaino è pieno. Le monete non ci stanno...");
					}
					else{
						monete = true;
						zaino++;
						sc.getPannello(this.bersaglio).removeElemento(provaMoneta);
					}
					done = true;
				}
				else if (cmd.equalsIgnoreCase("prendere pistola") && this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaPistola)){
					if(zaino==3){
					 	System.out.println("Lo zaino è pieno. La pistola non entra...");
					}
					else{
						pistola = true;
						zaino++;
						sc.getPannello(this.bersaglio).removeElemento(provaPistola);
					}
					done = true;
				}
				else if (cmd.equalsIgnoreCase("prendere nota") && this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaNota)){
					if(zaino==3){
					 	System.out.println("Lo zaino è pieno. Non c'e' spazio per la nota...");
					}
					else{
						nota = true;
						zaino++;
						sc.getPannello(this.bersaglio).removeElemento(provaNota);
					}
					done = true;
				}
				else if (cmd.equalsIgnoreCase("lasciare munizioni") && (this.munizioni==true) && this.sc.isVisibile(this.bersaglio)){
					munizioni = false;
					zaino--;
					PannelloBase pannelloMunizioni = sc.getPannello(this.bersaglio);
					pannelloMunizioni.addElemento(pruebaMunicion);
					addPannello(pannelloMunizioni);
					done = true;
				}
				else if (cmd.equalsIgnoreCase("lasciare pistola") && (this.pistola==true) && this.sc.isVisibile(this.bersaglio)){
					pistola = false;
					zaino--;
					PannelloBase pannelloPistola = sc.getPannello(this.bersaglio);
					pannelloPistola.addElemento(provaPistola);
					addPannello(pannelloPistola);
					done = true;
				}
				else if (cmd.equalsIgnoreCase("lasciare monete") && (this.monete==true)){
					monete = false;
					zaino--;
					PannelloBase pannelloMoneta = sc.getPannello(this.bersaglio);
					pannelloMoneta.addElemento(provaMoneta);
					addPannello(pannelloMoneta);
					if(!this.sc.isVisibile(this.bersaglio)) System.out.println("Hai lasciato le monete sotto il pannello. Non dimenticartele!");
					done = true;
				}
				else if (cmd.equalsIgnoreCase("lasciare chiave maestra") && (this.chiave_maestra==true)){
					chiave_maestra = false;
					zaino--;
					PannelloBase pannelloChiaveMaestra = sc.getPannello(this.bersaglio);
					pannelloChiaveMaestra.addElemento(provaChiaveMaestra);
					addPannello(pannelloChiaveMaestra);
					if(!this.sc.isVisibile(this.bersaglio)) System.out.println("Hai lasciato la chiave maestra sotto il pannello. Non dimenticartela!");
					done = true;
				}
				else if (cmd.equalsIgnoreCase("lasciare chiave1") && (this.chiave1==true)){
					chiave1 = false;
					zaino--;
					PannelloBase pannelloChiave1 = sc.getPannello(this.bersaglio);
					pannelloChiave1.addElemento(provaChiave1);
					addPannello(pannelloChiave1);
					if(!this.sc.isVisibile(this.bersaglio)) System.out.println("Hai lasciato la chiave 1 sotto il pannello. Non dimenticartela!");
					done = true;
				}
				else if (cmd.equalsIgnoreCase("lasciare chiave2") && (this.chiave2==true)){
					chiave2 = false;
					zaino--;
					PannelloBase pannelloChiave2 = sc.getPannello(this.bersaglio);
					pannelloChiave2.addElemento(provaChiave2);
					addPannello(pannelloChiave2);
					if(!this.sc.isVisibile(this.bersaglio)) System.out.println("Hai lasciato la chiave 2 sotto il pannello. Non dimenticartela!");
					done = true;
				}
				else if (cmd.equalsIgnoreCase("lasciare nota") && (this.nota==true)){
					nota = false;
					zaino--;
					PannelloBase pannelloNota = sc.getPannello(this.bersaglio);
					pannelloNota.addElemento(provaNota);
					addPannello(pannelloNota);
					if(!this.sc.isVisibile(this.bersaglio)) System.out.println("Hai lasciato la nota sotto il pannello. Non dimenticartela!");
					done = true;
				}
				else if (cmd.equalsIgnoreCase("leggere nota") && this.nota==true){
					System.out.println("Password segreta: " + password);
					done = true;
				}
				else if (cmd.equalsIgnoreCase("b")){
					spostareBersaglioUlt();
					done = true;
				}
				else if (cmd.equalsIgnoreCase("menu")){
					System.out.println();
					System.out.println("MENU DI PAUSA:");
					System.out.println("save - PER SALVARE IL GIOCO");
					System.out.println("load - PER CARICARE IL GIOCO SALVATO");
					System.out.println("exit - PER USCIRE");
					System.out.println("annulla - PER TORNARE INDIETRO");
					System.out.println();

					done2 = false;
					String cmd2 = in.nextLine();
					while (!done2){
						if (cmd2.equalsIgnoreCase("save")){
							salvaStato();
							done2 = true;
						}
						else if(cmd2.equalsIgnoreCase("load")){
							caricaStato();
							done2 = true;
						}
						else if(cmd2.equalsIgnoreCase("exit")){
							exit();
							done2 = true;
						}
						else if(cmd2.equalsIgnoreCase("annulla")){
							done2 = true;
						}
						else{
							System.out.println("Comando non riconosciuto!");
							System.out.println("MENU DI PAUSA:");
							System.out.println("save - PER SALVARE IL GIOCO");
							System.out.println("load - PER CARICARE IL GIOCO SALVATO");
							System.out.println("exit - PER USCIRE");
							System.out.println("annulla - PER TORNARE INDIETRO");
							System.out.println();
							cmd2 = in.nextLine();
						}
					}
					done = true;
				}
				else {
					System.out.println("Comando non riconosciuto!");
					System.out.println();
					System.out.println("///////////////////////////////////////////////");
					System.out.println("................................................");
					System.out.println();
					if(!this.sc.isVisibile(this.bersaglio)){
						if(this.bersaglio.getX()==6 && this.bersaglio.getY()==6)
							System.out.println("QUESTO E' UN PANNELLO EXTRA RESISTENTE. Chissa cosa si nasconde dietro...");
						else if(this.bersaglio.getX()==0 && this.bersaglio.getY()==6)
							System.out.println("QUESTO E' UN PANNELLO PIU RESISTENTE. Chissa cosa si nasconde dietro...");
						else if(this.bersaglio.getX()==6 && this.bersaglio.getY()==0)
							System.out.println("QUESTO E' UN PANNELLO PIU RESISTENTE. Chissa cosa si nasconde dietro...");
						else if(this.bersaglio.getX()==3 && this.bersaglio.getY()==0)
							System.out.println("QUESTO E' UN PANNELLO CON RESISTENZA ALEATORIA. Chissa cosa si nasconde dietro...");
						else if(this.bersaglio.getX()==3 && this.bersaglio.getY()==6)
							System.out.println("QUESTO E' UN PANNELLO CON RESISTENZA ALEATORIA. Chissa' cosa si nasconde dietro...");
						else System.out.println("QUESTO E' UN PANNELLO DEBOLE. VIENE ABBATTUTO CON UN SOLO COLPO. Non dovrebbe esserci molto dietro...");
					}
					else{
						if(sc.getPannello(this.bersaglio).hayElemento(provaTopo) || sc.getPannello(this.bersaglio).hayElemento(provaTopo1))
							System.out.println("ATTENZIONE!!! Hai trovato un topo nemico!!!");
						if(sc.getPannello(this.bersaglio).hayElemento(provaTalpaIntelligente)) {
							System.out.println("ATTENZIONE!!! Hai trovato GIORGIO TALPONI!!!");
							System.out.println();
							System.out.println("GIORGIO TALPONI: 'M...Ma...Ma come e' p-possibile??? TU chi sei? Come hai fatto a trovarmi? Comunque sia, non riuscirai a sconfiggermi!");
							System.out.println();
							System.out.println("Sconfiggila, dovrebbe sapere come si apre la cassaforte!");
						}
						if(sc.getPannello(this.bersaglio).hayElemento(provaTalpaGoffa) || sc.getPannello(this.bersaglio).hayElemento(provaTalpaCieca))
							System.out.println("Sotto al pannello che hai distrutto c'e' una talpa. Speriamo che ti sia riconoscente per averla liberata...");
					}

					System.out.println("Cosa vuoi fare?");
					if(this.sc.isValida(bersaglio.getVicina(Direzione.DESTRA)))
						System.out.println("d - PER ANDARE A DESTRA");
					if(this.sc.isValida(bersaglio.getVicina(Direzione.SINISTRA)))
						System.out.println("a - PER ANDARE A SINISTRA");
					if(this.sc.isValida(bersaglio.getVicina(Direzione.SOPRA)))
						System.out.println("w - PER ANDARE SOPRA");
					if(this.bersaglio.getX()==5 && this.bersaglio.getY()==0)
						System.out.println("w - PER PERCORRERE IL PASSAGGIO SEGRETO");
					if(this.sc.isValida(bersaglio.getVicina(Direzione.SOTTO)))
						System.out.println("s - PER ANDARE SOTTO");
					if(this.bersaglio.getX()==1 && this.bersaglio.getY()==6)
						System.out.println("s - PER PERCORRERE IL PASSAGGIO SEGRETO");
					if(this.bersaglio.getX()==5 && this.bersaglio.getY()==6 && this.sc.isVisibile(this.bersaglio) && this.chiave_maestra)
						System.out.println("usare chiave maestra - PER APRIRE IL FORZIERE");
					if(this.sc.isVisibile(this.bersaglio) && (sc.getPannello(this.bersaglio).hayElemento(provaTalpaGoffa) || sc.getPannello(this.bersaglio).hayElemento(provaTalpaCieca)))
						System.out.println("parlare - PER INTERAGIRE");
					if(this.pistola==true && munizioni==true && (!this.sc.isVisibile(this.bersaglio) || sc.getPannello(this.bersaglio).hayElemento(provaTalpaIntelligente) || sc.getPannello(this.bersaglio).hayElemento(provaTopo) || sc.getPannello(this.bersaglio).hayElemento(provaTopo1)))
						System.out.println("f - PER SPARARE");
					if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(pruebaMunicion))
						System.out.println("prendere munizioni - PER PRENDERE LE MUNIZIONI");
					if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaPistola))
						System.out.println("prendere pistola - PER PRENDERE LA PISTOLA");
					if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaMoneta))
						System.out.println("prendere monete - PER PRENDERE LE MONETE");
					if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaNota))
						System.out.println("prendere nota - PER PRENDERE LA NOTA");
					if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaChiaveMaestra))
						System.out.println("prendere chiave maestra - PER PRENDERE LA CHIAVE MAESTRA");
					if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaChiave1))
						System.out.println("prendere chiave1 - PER PRENDERE LA CHIAVE 1");
					if(this.sc.isVisibile(this.bersaglio) && sc.getPannello(this.bersaglio).hayElemento(provaChiave2))
						System.out.println("prendere chiave2 - PER PRENDERE LA CHIAVE 2");
					if(this.munizioni==true && this.sc.isVisibile(this.bersaglio)) System.out.println("lasciare munizioni - PER LASCIARE LE MUNIZIONI");
					if(this.pistola==true && this.sc.isVisibile(this.bersaglio)) System.out.println("lasciare pistola - PER LASCIARE LA PISTOLA");
					if(this.monete==true) System.out.println("lasciare monete - PER LASCIARE LE MONETE");
					if(this.nota==true) System.out.println("lasciare nota - PER LASCIARE LA NOTA");
					if(this.chiave_maestra==true) System.out.println("lasciare chiave maestra - PER LASCIARE LA CHIAVE MAESTRA");
					if(this.chiave1==true) System.out.println("lasciare chiave1 - PER LASCIARE LA CHIAVE 1");
					if(this.chiave2==true) System.out.println("lasciare chiave2 - PER LASCIARE LA CHIAVE 2");
					if(this.nota==true) System.out.println("leggere nota - PER LEGGERE LA NOTA");
					System.out.println("b - PER TORNARE ALL'ULTIMA CASELLA VISITATA");
					System.out.println("menu - PER METTERE IN PAUSA IL GIOCO");
					System.out.println();
				}
			}
		}
	}

}
