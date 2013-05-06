package Main;

public class MapaPos {
	private char myItem;
	private int i;
	private int j;

	public MapaPos(char item, int i, int j) {
		this.i = i;
		this.j = j;
		this.myItem = item;
	}

	public char get() {
		return myItem;
	}

	public synchronized char getAndCold() {
		if (myItem == 'F') {
                    Simulador.dormirFantasmaThread(i, j,Simulador.getColdTime());
                }
		return myItem;
	}

	public synchronized char getAndKill() {
		if (myItem == 'F') {
			Simulador.interruptFantasmaThread(i, j);
		}
		return myItem;
	}

	public char getAttack(boolean powerOn) {
		if (powerOn) {
                    return getAndCold();
                }
		else {
                    return getAndKill();
                }
	}

	public synchronized void set(char id) {
		myItem = id;
	}

}
