/*****************************************

 *      Mapa. ya que pueden haber o no posiciones libres.
Procesos Involucrados:
 *      Pacman
 *      Fantasmas
 * Son los unicos procesos que entran en ejecucion ya que son los que mas 
 intervienenen con el recurso critico
 * Monitor:
 *  El mapa sera nuestro monitor en el proyecto que regulara en que posiciones 
 *  esta cada Pcman o Fantasma
 ** 
 */
 

package Main;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {
       
        public static void play(String filename){
            try{
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(filename)));
                clip.start();
            }
            catch (Exception exc)
            {
            exc.printStackTrace(System.out);
            }
        }
    
	public static void main(String[] args) throws IOException {
		char mVill[][];
                char caract;
                int N, i,j, numFantasmas;
                numFantasmas = 0;
                int  numLifes, speedFantasmas, timePower, bulletSpeed;
                int pointPill, pointPill2, pointPill3,pointPillLife;
		String line;
		StringTokenizer tk;

		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream("C:/Pacman/src/Main/proyecto3.in");
		} catch (FileNotFoundException e) {
			System.exit(-1);
		}
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

	        line = br.readLine();
		tk = new StringTokenizer(line);
                N = Integer.parseInt(tk.nextToken());
                numLifes = Integer.parseInt(tk.nextToken());           
        
                line = br.readLine();
		tk = new StringTokenizer(line);
                speedFantasmas = Integer.parseInt(tk.nextToken());
		timePower = Integer.parseInt(tk.nextToken());
		
                line = br.readLine();
		tk = new StringTokenizer(line);
                pointPill= Integer.parseInt(tk.nextToken());
                pointPill2= Integer.parseInt(tk.nextToken());
                pointPill3= Integer.parseInt(tk.nextToken());
                pointPillLife= Integer.parseInt(tk.nextToken());
                int nAux=N;
                if (N<10){
                    N=10;
                    mVill = new char[N][N];                
                    for (char[] row: mVill) {
                        Arrays.fill(row, '#');
                    }     
                    for (i = 0; i <nAux; i++) {
                        for (j=0;j<nAux;j++){
                        caract = (char) br.read();
                        while (caract == '\n' || caract == '\r') {
                        caract = (char) br.read();
                        }
                        if (caract=='.'){caract='C';}
                        mVill[i][j]=caract;
                    }
                }
                  
                }else{                
                    mVill = new char[N][N];
                    for (i = 0; i <nAux; i++) {
                        for (j=0;j<nAux;j++){
                        caract = (char) br.read();
                        while (caract == '\n' || caract == '\r') {
                        caract = (char) br.read();
                        }
                        if (caract=='.'){caract='C';}
                        mVill[i][j]=caract;
                    }
                    }
               }
                
                for (i=0; i<N;i++){
                    for(j=0;j<N;j++){
                        if (mVill[i][j]=='F'){
                            numFantasmas++;
                        }
                    }
                }
                
                System.out.println(N+" "+numLifes);
                System.out.println(speedFantasmas+" "+timePower);
                System.out.println(pointPill+" "+pointPill2+" "+pointPill3+" "+pointPillLife);
                System.out.println(numFantasmas);
                               
                new Thread (new Splash()).start();
                play("C:/Pacman/src/intro.wav");
        
                try {
                Thread.sleep(4200);
                } catch (InterruptedException ie) {
                     Thread.currentThread().interrupt();
                    //Handle exception
                }
               
              	new Simulador(mVill, N, numFantasmas, speedFantasmas,timePower, pointPill,
                        pointPill2, pointPill3,pointPillLife, numLifes);
	
        }
}
