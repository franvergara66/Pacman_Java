package Procesos;

import Main.Mapa;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pacman extends Thread {
	private Mapa vill;
	private Coords coords;
	private int direccion = 0;

	public Pacman(int coldWeaponTime, Mapa mapa) {
		this.vill = mapa;
		this.coords = new Coords(0, 0, '.');
	}

	public Coords getCoords() {
		return this.coords;
	}

	@Override
	public void run() {
		vill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int dir = e.getKeyCode();
				if (dir != KeyEvent.VK_SPACE) {
					vill.moverPacman(e.getKeyCode());
					direccion = dir;
				} 
			}
		});
	}

	public void setCoords(int i, int j) {
		coords.setCords(i, j);
	}

	public int getDireccion() {
		return this.direccion;
	}
}
