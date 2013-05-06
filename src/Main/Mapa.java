package Main;

import Procesos.Coords;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.util.Vector;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Mapa {
	private MapaPos mVillPos[][];
	private char mVill[][];
	private int X;
	private Coords pacman;
	private Coords Fantasma[];
	private Vector<Coords> power;
	private int nItems = 0;
        private int pPill;
        private int pPill2;
        private int pEatFant;
        private int pForLife;
        private int numLifes;
	private boolean gano = false;
	private boolean perdio = false;
        private boolean comerFantasma=false;
        
	private OutputScreen out;

	public Mapa(char[][] mVill, int X, int nphantoms, int pointPill,int pointPill2,
                        int pointEatPhantom,int pointsForLife, int numLifes) {
		this.pacman = new Coords(0, 0, '.');
		this.power = new Vector<Coords>();
                this.pPill = pointPill;
                this.pPill2= pointPill2;
                this.pEatFant=pointEatPhantom;
                this.pForLife=pointsForLife;
                this.numLifes=numLifes;
		this.setmVill(new char[X][X]);
		this.setmVill(mVill);
		this.Fantasma = new Coords[nphantoms + 1];
		this.X = X;
		this.setmVillPos();
		this.setDisplay();
	}

	public void addKeyListener(KeyAdapter ka) {
		getOut().getFrmphantoms().addKeyListener(ka);
	}

	public OutputScreen getOut() {
		return out;
	}

	int getX() {
		return X;
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
	public void moverPacman(int Direccion) {
		if (!gano && !perdio) {

			Coords ini = new Coords(pacman.getI(), pacman.getJ(), '.');
			mVillPos[ini.getI()][ini.getJ()].set('.');
			pacman = Simulador.moverPacman(Direccion, X);
                        int dismLife = 100/numLifes;
				
			char nextItem = mVillPos[pacman.getI()][pacman.getJ()].get();
			if (nextItem == '#') {
				Simulador.getIndicadores().setInd(0,0, 0);
				pacman.setCords(ini.getI(), ini.getJ());
			} else if (nextItem =='*') {
				int mn = 1;
                                play("C:/Pacman/src/ate_fruit.wav");
                                comerFantasma=true;
                                                                       
                                Simulador.getIndicadores().setInd(dismLife, pPill2 , mn);
				nItems--;
                                
			} else if (nextItem == 'C') {
                                play("C:/Pacman/src/ate_pellot.wav");
                                Simulador.getIndicadores().setInd(0, pPill, 0);
				nItems--;
			} else if (nextItem == 'F' && comerFantasma==true) {
                                System.out.println("fantasma comido, thread muerto");
                                play("C:/Pacman/src/ate_ghost.wav");
                                mVillPos[pacman.getI()][pacman.getJ()].set('.');
                                Simulador.interruptFantasmaThread(pacman.getI(), pacman.getJ());
                                Simulador.getIndicadores().setInd(0,pEatFant, 0);
				comerFantasma=false;
				pacman.setCords(ini.getI(), ini.getJ());
			}

			mVillPos[pacman.getI()][pacman.getJ()].set('P');
			Simulador.pacmanSetCord(pacman.getI(), pacman.getJ());
			printMapa(ini, pacman);

			if (nItems == 0 ) {
                                JDialog dialogo2 = new JDialog(out.getFrmphantoms());
                                dialogo2.setSize(100, 100);
                                dialogo2.setLocationRelativeTo(out.getFrmphantoms());
                                JOptionPane.showMessageDialog(out.getFrmphantoms(), "GANASTE",
                                "GANASTE",JOptionPane.WARNING_MESSAGE);
                                Simulador.finish();
			}
                        if (getOut().getProgressBar().getValue() == 0) {
                                mVillPos[pacman.getI()][pacman.getJ()].set('.');
				Fantasma[Fantasma.length - 1] = new Coords(pacman);
				
                                JDialog dialogo = new JDialog(out.getFrmphantoms());
                                dialogo.setSize(100, 100);
                                dialogo.setLocationRelativeTo(out.getFrmphantoms());
                                
                                play("C:/Pacman/src/died.wav");
                                JOptionPane.showMessageDialog(out.getFrmphantoms(), "PERDISTE...GAME OVER =( !!",
                                "GAME OVER",JOptionPane.WARNING_MESSAGE);
                                
				out.getFrmphantoms().setTitle("GAME OVER!!!");
				perdio = true;
                                
                                //convierto a pacman en pacman muerto
                                
				Simulador.gameOver();
				Simulador.interruptpacmanThread();
			}
                        
                        
                        
		}
	}

	public void moverFantasma(int id, int Direccion) {
		if (!gano) {
			Coords ini = new Coords(Fantasma[id].getI(), Fantasma[id].getJ(),
					Fantasma[id].getHold());

			Fantasma[id] = Simulador.moverFantasma(id, Direccion, X);
			
			char nextItem = mVillPos[Fantasma[id].getI()][Fantasma[id].getJ()].get();
			if (nextItem == '#') {
				Fantasma[id].setCords(ini.getI(), ini.getJ());
			} else if (nextItem == 'F') {
				Fantasma[id].setCords(ini.getI(), ini.getJ());
			} else if (nextItem == 'P') {
                                int dismLife = 100/numLifes;
				Fantasma[id].setCords(ini.getI(), ini.getJ());
				Simulador.getIndicadores().setInd(-dismLife, -10, 0);
			} else {
				mVillPos[ini.getI()][ini.getJ()].set(Fantasma[id].getHold());
				if (nextItem == '�') {
                                    nextItem = '.';
                                }
				Fantasma[id].setHold(nextItem);
			}
			mVillPos[Fantasma[id].getI()][Fantasma[id].getJ()].set('F');
			Simulador.FantasmaSetCord(id, Fantasma[id].getI(),
					Fantasma[id].getJ());
			printMapa(ini, Fantasma[id]);
		}
	}

	public char itemAt(int i, int j) {
		char posicion = '~';
            try{
                posicion =mVillPos[i][j].get();
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println(" i es " + i + " j es " + j);
            }
            return posicion; 
	}

	public synchronized void printMapa(Coords ini, Coords end) {
		out.updateScreen(ini, end);
	}

	public void setDataValues() {
		int i, j, idZ = 0;
		for (i = 0; i < this.getX(); i++) {

			for (j = 0; j < this.getX(); j++) {

				if (mVillPos[i][j].get() == 'P') {
					Simulador.pacmanSetCord(i, j);
					pacman.setCords(i, j);
				} else if (mVillPos[i][j].get() == 'F') {
					Simulador.FantasmaSetCord(idZ, i, j);
					Fantasma[idZ] = new Coords(i, j, '.');
					idZ++;
				} else if (mVillPos[i][j].get() != '#' && mVillPos[i][j].get() != '.') {
					nItems++;
				}
			}
		}
	}

	public void setDisplay() {
		Runnable drun = new Runnable() {
			OutputScreen disp;

			@Override
			public void run() {
				try {
					disp = new OutputScreen();
					disp.getFrmphantoms().setVisible(true);
					setOut(disp);
				} catch (Exception e) {
				}
			}
		};
		drun.run();
	}

	private void setmVill(char mVill[][]) {
		this.mVill = mVill;
	}

	private void setmVillPos() {
		this.mVillPos = new MapaPos[X][X];
		int i, j;
		for (i = 0; i < this.getX(); i++) {
			for (j = 0; j < this.getX(); j++) {
				mVillPos[i][j] = new MapaPos(mVill[i][j], i, j);
			}
		}
	}

	private void setOut(OutputScreen out) {
		this.out = out;
	}
}
