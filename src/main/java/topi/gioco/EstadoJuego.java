package topi.gioco;

import java.io.Serializable;

public class EstadoJuego implements Serializable {
    private static final long serialVersionUID = 1L;

    private int bersaglioX;
    private int bersaglioY;
    private int bersaglioUltX;
    private int bersaglioUltY;
    private int getZaino;
    private boolean isPistola;
    private boolean isMonete;
    private boolean isChiaveMaestra;
    private boolean isMunizioni;
    private boolean isChiave1;
    private boolean isChiave2;
    private boolean isNota;
    private int pistolaX;
    private int moneteX;
    private int chiaveMaestraX;
    private int munizioniX;
    private int chiave1X;
    private int chiave2X;
    private int notaX;
    private int pistolaY;
    private int moneteY;
    private int chiaveMaestraY;
    private int munizioniY;
    private int chiave1Y;
    private int chiave2Y;
    private int notaY;
    private boolean isTopo1;
    private boolean isTopo2;
    private boolean isTopo3;
    private boolean isRata1;
    private boolean isRata2;
    private boolean[][] pannelli;

    public EstadoJuego(int bersaglioX, int bersaglioY, int bersaglioUltX, int bersaglioUltY, int getZaino, boolean isPistola,
                       boolean isMonete, boolean isChiaveMaestra, boolean isMunizioni, boolean isChiave1, boolean isChiave2,
                       boolean isNota, int pistolaX, int moneteX, int chiaveMaestraX, int munizioniX, int chiave1X,
                       int chiave2X, int notaX, int pistolaY, int moneteY, int chiaveMaestraY, int munizioniY,
                       int chiave1Y, int chiave2Y, int notaY, boolean isTopo1, boolean isTopo2, boolean isTopo3,
                       boolean isRata1, boolean isRata2, boolean[][] pannelli) {
        this.bersaglioX = bersaglioX;
        this.bersaglioY = bersaglioY;
        this.bersaglioUltX = bersaglioUltX;
        this.bersaglioUltY = bersaglioUltY;
        this.getZaino = getZaino;
        this.isPistola = isPistola;
        this.isMonete = isMonete;
        this.isChiaveMaestra = isChiaveMaestra;
        this.isMunizioni = isMunizioni;
        this.isChiave1 = isChiave1;
        this.isChiave2 = isChiave2;
        this.isNota = isNota;
        this.pistolaX = pistolaX;
        this.moneteX = moneteX;
        this.chiaveMaestraX = chiaveMaestraX;
        this.munizioniX = munizioniX;
        this.chiave1X = chiave1X;
        this.chiave2X = chiave2X;
        this.notaX = notaX;
        this.pistolaY = pistolaY;
        this.moneteY = moneteY;
        this.chiaveMaestraY = chiaveMaestraY;
        this.munizioniY = munizioniY;
        this.chiave1Y = chiave1Y;
        this.chiave2Y = chiave2Y;
        this.notaY = notaY;
        this.isTopo1 = isTopo1;
        this.isTopo2 = isTopo2;
        this.isTopo3 = isTopo3;
        this.isRata1 = isRata1;
        this.isRata2 = isRata2;
        this.pannelli = pannelli;
    }

    public int getBersaglioX() { return bersaglioX; }
    public int getBersaglioY() { return bersaglioY; }
    public int getBersaglioUltX() { return bersaglioUltX; }
    public int getBersaglioUltY() { return bersaglioUltY; }
    public int getGetZaino() { return getZaino; }
    public boolean isPistola() { return isPistola; }
    public boolean isMonete() { return isMonete; }
    public boolean isChiaveMaestra() { return isChiaveMaestra; }
    public boolean isMunizioni() { return isMunizioni; }
    public boolean isChiave1() { return isChiave1; }
    public boolean isChiave2() { return isChiave2; }
    public boolean isNota() { return isNota; }
    public int getPistolaX() { return pistolaX; }
    public int getMoneteX() { return moneteX; }
    public int getChiaveMaestraX() { return chiaveMaestraX; }
    public int getMunizioniX() { return munizioniX; }
    public int getChiave1X() { return chiave1X; }
    public int getChiave2X() { return chiave2X; }
    public int getNotaX() { return notaX; }
    public int getPistolaY() { return pistolaY; }
    public int getMoneteY() { return moneteY; }
    public int getChiaveMaestraY() { return chiaveMaestraY; }
    public int getMunizioniY() { return munizioniY; }
    public int getChiave1Y() { return chiave1Y; }
    public int getChiave2Y() { return chiave2Y; }
    public int getNotaY() { return notaY; }
    public boolean isTopo1() { return isTopo1; }
    public boolean isTopo2() { return isTopo2; }
    public boolean isTopo3() { return isTopo3; }
    public boolean isRata1() { return isRata1; }
    public boolean isRata2() { return isRata2; }
    public boolean[][] getPannelli() { return pannelli; }
}


