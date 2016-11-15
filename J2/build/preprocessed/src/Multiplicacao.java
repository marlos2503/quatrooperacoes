/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src;




import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import java.io.IOException;
import javax.microedition.lcdui.Font;

public class Multiplicacao
  extends GameCanvas implements Runnable {
// the couple image
	private Image coupleImg;
        private Image fundoImg;

	// the couple image coordinates
	private int coupleX;
	private int coupleY;

        // the couple image coordinates
	private int linhas;
	private int colunas;

        // the couple image coordinates
	private int bottomX;
	private int bottomY;


	// the distance to move in the x axis
	private int dx = 25;
        private int dy = 30;

	// the center of the screen
	public final int CENTER_X = getWidth()/2;
	public final int CENTER_Y = getHeight()/2;

        // the center of the screen
	public final int BOTTOM_X = 0;
	public final int BOTTOM_Y = 0;

	public Multiplicacao() {
	  super(true);
	}

	public void start() {

	  try {

	    // create and load the couple image
		// and then center it on screen when
		// the MIDlet starts
		coupleImg = Image.createImage("/res/objeto.png");
                fundoImg = Image.createImage("/res/fundo.png");
		coupleX = BOTTOM_X;
		coupleY = BOTTOM_Y;
                linhas  = 1;
                colunas = 1;
                              

	  } catch(IOException ioex) { System.err.println(ioex); }

	  Thread runner = new Thread(this);
	  runner.start();

	}

	public void run() {

	  // the graphics object for this canvas
	  Graphics g = getGraphics();

	  while(true) { // infinite loop

  	    // based on the structure

  		// first verify game state
  		verifyGameState();

  		// check user's input
  		checkUserInput();

  		// update screen
  		updateGameScreen(getGraphics());

		// and sleep, this controls
		// how fast refresh is done
		try {
		  Thread.currentThread().sleep(30);
		} catch(Exception e) {}

	  }

	}

	private void verifyGameState() {
	  // doesn't do anything yet
	}

	private void checkUserInput() {

	  // get the state of keys
	  int keyState = getKeyStates();

	  // calculate the position for x axis
	  calculateCoupleX(keyState);

	}

	private void updateGameScreen(Graphics g) {

	  // the next two lines clear the background
	  g.setColor(0xffffff);
	  g.fillRect(0, 0, getWidth(), getHeight() );
          g.drawImage(fundoImg, 0, 0, Graphics.TOP | Graphics.LEFT);
          Graphics fundo = g;
          int x = 0;
          int y = 0;

          for (int j = 1; j <= linhas; j++) {

            x =  0;
            for (int i = 1; i <= colunas; i++)
            {
                    
                    fundo.drawImage(coupleImg, x, y, Graphics.TOP | Graphics.LEFT);
                    x =  x + dx;

            }
            y = y + dy;

          }
          g.setColor(0x000000);
          g.setFont(Font.getFont(Font.FACE_MONOSPACE,
                    Font.STYLE_BOLD,
                    Font.SIZE_MEDIUM));
          int total = linhas * colunas;
          g.drawString("Total de Pontos "+total,
                  1, 250, Graphics.TOP | Graphics.LEFT);
     
	  // this call paints off screen buffer to screen
	  flushGraphics();

	}

	private void calculateCoupleX(int keyState) {

	  // determines which way to move and changes the
	  // x coordinate accordingly
	  if((keyState & LEFT_PRESSED) != 0)
          {
	  //  coupleX -= dx;
            colunas   = colunas - 1;
	  }
	  if((keyState & RIGHT_PRESSED) != 0)
          {
	//    coupleX += dx;
            colunas   = colunas + 1;
	  }
          if((keyState & DOWN_PRESSED) != 0)
          {
	 //   coupleY += dy;
            linhas  = linhas + 1;
          }
          if((keyState & UP_PRESSED) != 0)
          {
	 //   coupleY -= dy;
            linhas  = linhas - 1;
          }




	}

	
}