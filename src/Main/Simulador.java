package Main;

import Procesos.Coords;
import Procesos.Fantasma;
import Procesos.Pacman;
import java.io.File;
import java.util.Vector;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Simulador {
	private static Vector<Fantasma> fantasmas;
	private static Pacman pacman;
	private static Mapa village;
	private static MovCalculator mov;
	private static Status ind;
	private static int timePower;
	private static int speedfantasmaes;
	private static int numfantasmaes;
	private static int bulletSpeed;
        private static int pointPill;
	private static int pointPill2;
        private static int pointEatPhantom;
        private static int pointsForLife;
        private static int numLifes; 
        private static int limit;
	private static boolean started = false;
	public static String[] evalDir = { "Oeste", "Norte", "Este", "Sur" };
	private static boolean gameOver;

	public static void dormirFantasmaThread(int idZ, int millis) {
		fantasmas.get(idZ).youHaveToSleep(millis);
	}

	public static void dormirFantasmaThread(int x, int y, int millis) {
		for (int i = 0; i < getNumfantasmaes(); i++) {
			if (fantasmas.get(i).getCoords().getI() == x && fantasmas.get(i).getCoords().getJ() == y) {
				fantasmas.get(i).youHaveToSleep(
						Simulador.getColdTime());
				return;
			}
		}
	}

	public static int findFantasmaID(int x, int y) {
		for (int i = 0; i < getNumfantasmaes(); i++) {
			if (fantasmas.get(i).getCoords().getI() == x && fantasmas.get(i).getCoords().getJ() == y) {
                            return i;
                    }
		}
		return -1;
	}

	public static void finish() {
                Simulador.terminatefantasmaesThreads();
		Simulador.interruptpacmanThread();
	}

	public static int getColdTime() {
		return timePower;
	}

	public static Status getIndicadores() {
		return ind;
	}

	public static int getNumfantasmaes() {
		return numfantasmaes;
	}

	public static OutputScreen getOut() {
		return village.getOut();
	}

	public static int getSpeedfantasmaes() {
		return speedfantasmaes;
	}

	public static Mapa getMap() {
		return village;
	}
              
	public static void pacmanSetCord(int i, int j) {
		pacman.setCoords(i, j);
	}

	public static void interruptpacmanThread() {
		pacman.interrupt();
	}

	public static void interruptFantasmaThread(int idZ) {
		fantasmas.get(idZ).interrupt();
	}

	public static void interruptFantasmaThread(int x, int y) {
		for (int i = 0; i < getNumfantasmaes(); i++) {
			if (fantasmas.get(i).getCoords().getI() == x
					&& fantasmas.get(i).getCoords().getJ() == y) {
				fantasmas.get(i).interrupt();
				return;
			}
		}
	}
        
        public static void play(String filename){
            try{
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(filename)));
                clip.start();
            }
            catch (Exception exc)
            {
            exc.printStackTrace(System.out);
            }
        }
        

	public static Coords moverPacman(int Direccion, int limit) {
		mov.moverPacman(Direccion, limit, pacman);
		return pacman.getCoords();
	}

	public static Coords moverFantasma(int idZ, int Direccion, int limit) {
		mov.moverFantasma(Direccion, limit, fantasmas.get(idZ));
		return fantasmas.get(idZ).getCoords();
	}

	public static void start() {
		if (!started) {
			village.setDataValues();
			village.printMapa(null, null);
			pacman.start();
			for (int i = 0; i < getNumfantasmaes(); i++) {
				fantasmas.get(i).start();
				started = true;
			}
		}
	}

	public static void terminatefantasmaesThreads() {
		for (int i = 0; i < getNumfantasmaes(); i++) {
			if (fantasmas.get(i).isAlive()) {
                        fantasmas.get(i).stopRunning();
                    }
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////
	public static void FantasmaSetCord(int idZ, int i, int j) {
		fantasmas.get(idZ).setCoords(i, j);
	}

	public static int getLimit() {
		return limit;
	}

	public static boolean ispacmanAt(Coords x) {
		return pacman.getCoords() == x;
	}

	public static int pacmanDireccion() {
		return pacman.getDireccion();
	}

	public static int fantasmaDireccion(int idZ) {
		return fantasmas.get(idZ).getDireccion();
	}

	public static boolean isfantasmaFrozen(int idZ) {
		return fantasmas.get(idZ).isFrozen();
	}

	public Simulador(char[][] mVill, int X, int numfantasmaes,
		int speedfantasmaes, int timePower,int pointPill,
                        int pointPill2, int pointEatPhantom,int pointsForLife, int numLifes) {
                
                play("C:/Pacman/src/pacman.wav");
            
                Simulador.mov = new MovCalculator();
		Simulador.timePower = timePower;
		Simulador.speedfantasmaes = speedfantasmaes;
		Simulador.numfantasmaes = numfantasmaes;
		Simulador.limit = X;
                Simulador.pointPill=pointPill;
                Simulador.pointPill2=pointPill2;
                Simulador.pointEatPhantom=pointEatPhantom;
                Simulador.pointsForLife=pointsForLife;
                Simulador.numLifes=numLifes;
		Simulador.fantasmas = new Vector<Fantasma>();
		Simulador.village = new Mapa(mVill, X, numfantasmaes, pointPill,pointPill2,
                        pointEatPhantom,pointsForLife, numLifes);
		Simulador.pacman = new Pacman(timePower,
				village);
		Simulador.ind = new Status();
		Simulador.gameOver = false;

		for (int i = 0; i < numfantasmaes; i++) {
			Simulador.fantasmas.add(new Fantasma(i, speedfantasmaes, village));
		}
		start();
	}

	public static Coords pacmanCoords() {
		return pacman.getCoords();
	}

	public static void gameOver() {
		if (!gameOver) {

			fantasmas.add(new Fantasma(numfantasmaes, speedfantasmaes, village));
			fantasmas.get(numfantasmaes).setCoords(pacman.getCoords().getI(),
					pacman.getCoords().getJ());
			pacman.setCoords(-10, -10);
			fantasmas.get(numfantasmaes).start();
			numfantasmaes++;
		}
		gameOver = true;
	}
}
