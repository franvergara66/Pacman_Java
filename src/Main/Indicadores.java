package Main;

public class Indicadores {
	private int vida = 100;
	private int puntuacion = 0;

	public synchronized int getPuntuacion() {
		return puntuacion;
	}
        
	public synchronized int getVida() {
		return vida;
	}

	public synchronized void setInd(int vida, int puntuacion) {
		this.puntuacion = this.puntuacion + puntuacion;
                this.vida= this.vida+vida;
                if (this.vida>=100){this.vida=100;}
                Simulador.getOut().getProgressBar().setValue(this.vida);
		Simulador.getOut().getPuntuacionText().setText(String.valueOf(this.puntuacion));
	}
}
