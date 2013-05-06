package Main;

import Procesos.Coords;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class OutputScreen {

	private JFrame frmphantoms;
	private JTextField puntuacionText;
	private Iface canvas;
	private JProgressBar progressBar;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_1;
	private JPanel panel_4;
        public OutputScreen() throws IOException {
		initialize();
        }

	public JFrame getFrmphantoms() {
		return frmphantoms;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JTextField getPuntuacionText() {
		return puntuacionText;
	}
                 
	private void initialize() throws IOException {
		setFrmphantoms(new JFrame());
		getFrmphantoms().setTitle("Pacman v1.0");
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
                Point newLocation = new Point(middle.x - (getFrmphantoms().getWidth() / 2), middle.y - (getFrmphantoms().getHeight() / 2));
                getFrmphantoms().setLocation(newLocation);
                
                getFrmphantoms().setBounds(235, 7,235 + Simulador.getLimit() * 32,80 + Simulador.getLimit() * 32);
		getFrmphantoms().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 534, 0 };
		gridBagLayout.rowHeights = new int[] { 375, 45, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		frmphantoms.getContentPane().setLayout(gridBagLayout);

		panel_1 = new JPanel();
		panel_1.setFocusable(false);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		frmphantoms.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 320, 222, 0 };
		gbl_panel_1.rowHeights = new int[] { 361, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		panel_4 = new JPanel();
		panel_4.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setFocusable(false);
		panel_4.setMinimumSize(new Dimension(Simulador.getLimit() * 32,Simulador.getLimit() * 32));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel_1.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 320, 0 };
		gbl_panel_4.rowHeights = new int[] { 320, 0 };
		gbl_panel_4.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		setCanvasArea(new Iface());
		canvas.setFont(new Font("Consolas", Font.PLAIN, 13));
                JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.EAST;
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panel_1.add(panel_3, gbc_panel_3);
		panel_3.setFocusable(false);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 202, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 320, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);
                /*
		panel_2 = new JPanel();
                
                panel_3.add(panel_2);
                JLabel img = new JLabel(" "); 

                ImageIcon image2; 
                image2 = new ImageIcon("C:/Pacman/src/hola.gif");
                img.setIcon(image2);     
                img.setSize(10,10);
                img.setVisible(true);     
		
                panel_2.add(img); // "dibujar" es mi panel ok...
                */
		{
			panel = new JPanel();
			panel.setFocusable(false);
			panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,229)), "Statics", TitledBorder.LEADING,
			TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			frmphantoms.getContentPane().add(panel, gbc_panel);
			panel.setLayout(null);
                       
			setProgressBar(new JProgressBar());
			getProgressBar().setValue(100);
                        getProgressBar().setStringPainted(true);
                        
                        getProgressBar().setForeground(Color.green);
                        getProgressBar().setBackground(Color.red);
                                          
                        
			JLabel lblVida = new JLabel("Vida:");
			lblVida.setFocusable(false);
			lblVida.setBounds(5, 24, 29, 14);
			panel.add(lblVida);

			JLabel lblPuntuacion = new JLabel("Puntuacion:");
			lblPuntuacion.setFocusable(false);
			lblPuntuacion.setBounds(350, 24, 80, 14);
			panel.add(lblPuntuacion);

			setPuntuacionText(new JTextField());
			getPuntuacionText().setEditable(false);
			getPuntuacionText().setColumns(10);
		}
	}

	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					OutputScreen window = new OutputScreen();
                                        window.getFrmphantoms().pack();
					window.getFrmphantoms().setLocationRelativeTo(null);
					window.getFrmphantoms().setVisible(true);
				} catch (Exception e) {
				}
			}
		});
	}

	public void setFrmphantoms(JFrame frmphantoms) {
		this.frmphantoms = frmphantoms;
		frmphantoms.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Simulador.finish();
                        }
		});
		frmphantoms.setResizable(false);
                frmphantoms.setLocationRelativeTo(null);
		frmphantoms.getContentPane().setFocusable(false);
		frmphantoms.getContentPane().setFocusTraversalKeysEnabled(false);
		frmphantoms.setMinimumSize(new Dimension(556, 410));
	}
        
	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
		progressBar.setFocusable(false);
		progressBar.setBounds(44, 24, 158, 14);
		panel.add(progressBar);
	}

	public void setPuntuacionText(JTextField puntuacionText) {
		this.puntuacionText = puntuacionText;
		puntuacionText.setText("000");
		puntuacionText.setFocusable(false);
		puntuacionText.setBounds(458, 18, 70, 20);
		panel.add(puntuacionText);
	}

	public void setCanvasArea(Iface canvas1) {
		this.canvas = canvas1;
		GridBagConstraints gbc_canvas = new GridBagConstraints();
		gbc_canvas.fill = GridBagConstraints.BOTH;
		gbc_canvas.gridx = 0;
		gbc_canvas.gridy = 0;
		panel_4.add(canvas, gbc_canvas);
		canvas.setVisible(false);
		canvas.setFocusable(false);
	}

	
	public void updateScreen(Coords ini, Coords end) {
		if (ini == null && end == null) {
			canvas.setVisible(true);
			canvas.repaint();
		} else {
			if (end.getJ() > ini.getJ()) {
				canvas.repaint(ini.getJ() * 32, ini.getI() * 32, 64, 32);
			} else if (end.getI() > ini.getI()) {
				canvas.repaint(ini.getJ() * 32, ini.getI() * 32, 32, 64);
			} else if (end.getJ() < ini.getJ()) {
				canvas.repaint(end.getJ() * 32, end.getI() * 32, 64, 32);
			} else if (end.getI() < ini.getI()) {
				canvas.repaint(end.getJ() * 32, end.getI() * 32, 32, 64);
			} else if (end.equals(ini)) {
				canvas.repaint(ini.getJ() * 32, end.getI() * 32, 32, 32);
			}
		}
	}
}
