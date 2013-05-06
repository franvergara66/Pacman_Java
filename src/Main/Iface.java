package Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

//@SuppressWarnings("serial")
public class Iface extends JPanel {
	private int pH = 0;
	private int pB = 0;
	private Image fantasma[][];
	private Image pacman[][];
	private Image fantasma_congelado[][];
	private Image objetos[][];
	private Image cherry[][];
	private Image floor;
        private Image deadPacman; 
	private Image background;
	public Iface() throws IOException {
		super();

		fantasma = CortarImagen.Split(32, "C:/Pacman/src/fantasma.png");
		fantasma_congelado = CortarImagen.Split(32, "C:/Pacman/src/fantasma_congelado.png");
		pacman = CortarImagen.Split(32, "C:/Pacman/src/pacman.png");
		objetos = CortarImagen.Split(32, "C:/Pacman/src/objetos.png");
		cherry = CortarImagen.Split(32, "C:/Pacman/src/cherry2.png");
		floor = Toolkit.getDefaultToolkit().getImage("C:/Pacman/src/grama.png");
                deadPacman = Toolkit.getDefaultToolkit().getImage("C:/Pacman/src/imagen.ico");

		BufferedImage bg = ImageIO.read(new FileInputStream(new File("C:/Pacman/src/grama.png")));
		BufferedImage bufferedImage = new BufferedImage(
				Simulador.getLimit() * 32,
				Simulador.getLimit() * 32, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bufferedImage.createGraphics();
		for (int i = 0; i < Simulador.getLimit(); i++) {
			for (int j = 0; j < Simulador.getLimit(); j++) {
				g.drawImage(bg, i * 32, j * 32, null);
			}
		}
		background = Toolkit.getDefaultToolkit().createImage(
				bufferedImage.getSource());
	}

	private int direccionSprite(int direccion) {
		if (direccion == KeyEvent.VK_DOWN) {
                    return 0;
                }
	 	if (direccion == KeyEvent.VK_LEFT) {
                   return 1;
                } 
		if (direccion == KeyEvent.VK_RIGHT) {
                    return 2;
                }
		if (direccion == KeyEvent.VK_UP) {
                    return 3;
                }
		return 0;
	}

	public void drawBoard(final Graphics g) {
		new Runnable() {
			@Override
			public void run() {
				Rectangle s = g.getClipBounds();
				g.drawImage(background, 0, 0, null);
				for (int i = s.y / 32; i < (s.y + s.height) / 32; i++) {
					for (int j = s.x / 32; j < (s.x + s.width) / 32; j++) {
						Image element;
                                                element = floor;
						char item = Simulador.getMap().itemAt(i, j);

						if (item == 'P') {
							int direccion = Simulador.pacmanDireccion();
							element = pacman[direccionSprite(direccion)][pH];

						} else if (item == '#') {
                                                        element = objetos[0][0];
                                                }
						else if (item == 'C') {
                                                    element = objetos[1][1];
                                                }
						else if (item == 'F') {
							int idZ = Simulador.findFantasmaID(i, j);
							if (idZ != -1) {
								int direccion = Simulador.fantasmaDireccion(idZ);
								if (!Simulador.isfantasmaFrozen(idZ)) {
                                                                    element = fantasma[direccionSprite(direccion)][pH];
                                                                }
								else {
                                                                    element = fantasma_congelado[direccionSprite(direccion)][0];
                                                                }
							}
						} else if (item =='*') {
							element = cherry[0][1];
                                                        //aqui debo cambiar aparicencia de los fantasmas
                                                        
                                                        
                                                        
                                                }
						g.drawImage(element, j * 32, i * 32, null);
					}
				}
				pH = (pH + 1) % 3;
				pB = (pB + 1) % 3;
			}
		}.run();
	}

	@Override
	public void paintComponent(Graphics g) {
		drawBoard(g);
	}
}
