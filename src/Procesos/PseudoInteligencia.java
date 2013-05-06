package Procesos;

import java.awt.Rectangle;

public class PseudoInteligencia {
	Rectangle visionArea = new Rectangle();
	
	public int randomDirection() {

		return 37 + (int) (Math.random() * 4);
	}

	public int toPacmanDirection(Coords posZ, Coords posH) {
		
		int dist1, dist2, dist3, dist4, coorX1, coorX2, coorX3, coorY1, coorY2, coorY3;

		coorX1 = (posZ.getI() + 1) - posH.getI();
		coorX2 = (posZ.getI() - 1) - posH.getI();
		coorX3 = posZ.getI() - posH.getI();
		coorY1 = (posZ.getJ() + 1) - posH.getJ();
		coorY2 = (posZ.getJ() - 1) - posH.getJ();
		coorY3 = posZ.getJ() - posH.getJ();

		dist1 = coorX1 * coorX1 + coorY3 * coorY3;
		dist2 = coorX2 * coorX2 + coorY3 * coorY3;
		dist3 = coorX3 * coorX3 + coorY2 * coorY2;
		dist4 = coorX3 * coorX3 + coorY1 * coorY1;

		int menor = dist1;
		int dir = 40;
		if (dist2 < menor) {
			dir = 38;
			menor = dist2;
		}
		if (dist3 < menor) {
			dir = 37;
			menor = dist3;
		}
		if (dist4 < menor) {
			dir = 39;
			menor = dist4;
		}

		return dir;
	}
	
	public void updateVisionArea(Coords posZ, Coords posH) {
		visionArea.setBounds(posZ.getI()-2,posZ.getJ()-2,5,5);
	}
	
	public int nextStep(Coords posZ, Coords posH) {
		updateVisionArea(posZ, posH);
		
		if(visionArea.contains(posH.getI(), posH.getJ())) {
			return toPacmanDirection(posZ, posH);
		}
		else 
			return randomDirection();
	}
}
