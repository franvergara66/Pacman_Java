package Main;

public class Status {
	private int vida = 100;
	private int puntuacion = 0;
	private int Lifes = 0;
	private int totalLifes = 0;


	public synchronized int getPuntuacion() {
		return puntuacion;
	}

	public synchronized int getVida() {
		return vida;
	}

	public synchronized void setInd(int vida, int puntuacion, int Lifes) {
		this.vida = this.vida + vida;
		
		this.puntuacion = this.puntuacion + puntuacion;
		this.Lifes = this.Lifes + Lifes;
		this.totalLifes = this.totalLifes + Lifes;
		if (this.Lifes < 0) {
                    this.Lifes = 0;
                }
		if (this.totalLifes < -1) {
                    this.totalLifes = -1;
                }

		Simulador.getOut().getProgressBar().setValue(this.vida);
		Simulador.getOut().getPuntuacionText().setText(String.valueOf(this.puntuacion));
	}
}
