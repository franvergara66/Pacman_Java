package Main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CortarImagen {
	public static Image[][] Split(int tamano, String nombreImagen) throws IOException {
		File file = new File(nombreImagen);
		FileInputStream fis = new FileInputStream(file);
		BufferedImage image = ImageIO.read(fis);

		int filas = image.getHeight() / tamano;
		int columnas = image.getWidth() / tamano;

		Image result[][] = new Image[filas][columnas];

		BufferedImage matrizImagen[][] = new BufferedImage[filas][columnas];
		for (int x = 0; x < filas; x++) {
			for (int y = 0; y < columnas; y++) {
				matrizImagen[x][y] = new BufferedImage(tamano, tamano, image.getType());
				Graphics2D gr = matrizImagen[x][y].createGraphics();
				gr.drawImage(image, 0, 0, tamano, tamano, tamano * y, tamano * x, tamano * y
						+ tamano, tamano * x + tamano, null);
				gr.dispose();
				result[x][y] = Toolkit.getDefaultToolkit().createImage(
						image.getSource());
			}
		}
		return matrizImagen;
	}
}
