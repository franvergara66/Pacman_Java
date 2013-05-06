package Main;

import Procesos.Fantasma;
import Procesos.Pacman;
import java.awt.event.KeyEvent;

public class MovCalculator {

	public void moverPacman(int Direccion, int limit, Pacman pacman) {
		if (Direccion == KeyEvent.VK_LEFT) {
			pacman.getCoords().movLeft();
		}
		if (Direccion == KeyEvent.VK_UP) {
			pacman.getCoords().movUp();
		}
		if (Direccion == KeyEvent.VK_RIGHT) {
			pacman.getCoords().movRight(limit);
		}
		if (Direccion == KeyEvent.VK_DOWN) {
			pacman.getCoords().movDown(limit);
		}
	}

	public void moverFantasma(int Direccion, int limit, Fantasma Fantasma) {
		if (Direccion == KeyEvent.VK_LEFT) {
			Fantasma.getCoords().movLeft();
		}
		if (Direccion == KeyEvent.VK_UP) {
			Fantasma.getCoords().movUp();
		}
		if (Direccion == KeyEvent.VK_RIGHT) {
			Fantasma.getCoords().movRight(limit);
		}
		if (Direccion == KeyEvent.VK_DOWN) {
			Fantasma.getCoords().movDown(limit);
		}
	}

}
