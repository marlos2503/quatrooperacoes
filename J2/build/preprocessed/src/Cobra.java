/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src;




import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import java.io.IOException;

public class Cobra
  extends GameCanvas implements Runnable {

	public Cobra() {
	  super(true);
	}

	public void start() {

	  try {

	    // create and load the couple image
		// and then center it on screen when
		// the MIDlet starts
		coupleImg = Image.createImage("/res/objeto.png");
		coupleX = CENTER_X;
		coupleY = CENTER_Y;
                bottomX = BOTTOM_X;
                bottomY = BOTTOM_Y;
              

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
       
	  // draws the couple image according to current
	  // desired positions
	  g.drawImage(
	    coupleImg, coupleX,
		coupleY, Graphics.HCENTER | Graphics.BOTTOM);
         
	  // this call paints off screen buffer to screen
	  flushGraphics();

	}

	private void calculateCoupleX(int keyState) {

	  // determines which way to move and changes the
	  // x coordinate accordingly

	  if((keyState & LEFT_PRESSED) != 0)
          {
	    coupleX -= dx;
            //
             ultDIR = 0;
             ultESQ = 1;
             ultCIM = 0;
             ultBAX = 0;
	  }
          else
          {
	    if((keyState & RIGHT_PRESSED) != 0)
            {
	            coupleX += dx;
                    //
             ultDIR = 1;
             ultESQ = 0;
             ultCIM = 0;
             ultBAX = 0;
	     }
             else
            {
              if((keyState & DOWN_PRESSED) != 0)
              {
	             coupleY += dy;
                     //
                     ultDIR = 0;
                     ultESQ = 0;
                     ultCIM = 0;
                     ultBAX = 1;

               }
               else
               {
                if((keyState & UP_PRESSED) != 0)
                {
	          coupleY -= dy;                     //
                  ultDIR = 0;
                  ultESQ = 0;
                  ultCIM = 1;
                  ultBAX = 0;
                 }
                 else
                 {
                     if (ultESQ == 1) coupleX -= dx;
                     if (ultDIR == 1) coupleX += dx;
                     if (ultBAX == 1) coupleY += dy;
                     if (ultCIM == 1) coupleY -= dy;
                 }
                }
              }
            }






	}

	// the couple image
	private Image coupleImg;

	// the couple image coordinates
	private int coupleX;
	private int coupleY;

        // the couple image coordinates
	private int bottomX;
	private int bottomY;

     
	// the distance to move in the x axis
	private int dx = 1;
        private int dy = 1;

        //  Ultimos Comandos
        private int ultDIR = 0;
        private int ultESQ = 0;
        private int ultCIM = 0;
        private int ultBAX = 0;

	// the center of the screen
	public final int CENTER_X = getWidth()/2;
	public final int CENTER_Y = getHeight()/2;

        // the center of the screen
	public final int BOTTOM_X = getWidth();
	public final int BOTTOM_Y = 0;

}