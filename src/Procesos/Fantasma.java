package Procesos;

import Main.Mapa;
import Main.Simulador;

public class Fantasma extends Thread {
	private int speed;
	private Mapa vill;
	private int id;
	private int coldDown;
	private Coords coords;
	private int direccion = 0;
	private PseudoInteligencia AI;
	private boolean iHaveToSleep = false;
	private boolean keepRunning = true;

	public Fantasma(int id, int speed, Mapa mapa) {
		this.vill = mapa;
		this.coords = new Coords(0, 0, '.');
		this.id = id;
		this.speed = speed;
		AI = new PseudoInteligencia();
	}

	public int getIdZ() {
		return this.id;
	}

	public Coords getCoords() {
		return this.coords;
	}

	public char getHold() {
		return coords.getHold();
	}

	@Override
	public void run() {
		try {
			while (keepRunning) {
				
				direccion = AI.nextStep(coords,
						Simulador.pacmanCoords());
				vill.moverFantasma(id, direccion);
				Thread.sleep(speed);
			}
		} catch (InterruptedException e) {
			this.interrupt();
		}
	}

	public void setCoords(int i, int j) {
		coords.setCords(i, j);
	}

	public void setHold(char hold) {
		coords.setHold(hold);
	}

	public void stopRunning() {
		this.keepRunning = false;
	}

	public void youHaveToSleep(int millis) {
		coldDown = millis;
		iHaveToSleep = true;
	}

	public int getDireccion() {
		return this.direccion;
	}

	public boolean isFrozen() {
		return iHaveToSleep;
	}
}
