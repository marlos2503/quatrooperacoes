
package src;

import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.GameCanvas;
/*
/**
 *
 * @author marlos
 */
// This internal class is a customized Canvas, that displays the game. It also
// implemnts the Runnable interface (which means it needs a run method) to start
// separate threads.
class DivCanvas extends GameCanvas implements Runnable {
    // the couple image
	private Image coupleImg;
        private Image fundoImg;
        private Display display = null;
	// the couple image coordinates
	private int coupleX;
	private int coupleY;

        // the couple image coordinates
	private int linhas;
	private int colunas;
        private int nr1   = 0;
        private int nr2   = 0;
        private int novo = 0;
        private int fim = 0;

        // the couple image coordinates
	private int bottomX;
	private int bottomY;


	// the distance to move in the x axis
	private int dx = 11;
        private int dy = 11;

	// the center of the screen
	public final int CENTER_X = getWidth()/2;
	public final int CENTER_Y = getHeight()/2;

        // the center of the screen
	public final int BOTTOM_X = 0;
	public final int BOTTOM_Y = 0;

        public DivCanvas() {
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
         // based on the structure
          //g.addCommand(exitCommand);


          while (true)
          {
            Random gerador = new Random();
            linhas = 0;
	    colunas = 0;
            nr1 = gerador.nextInt(9) + 1;
            nr2 = gerador.nextInt(9) + 1;
            novo = 0;
            while(novo == 0)
            {
  		verifyGameState();
  		checkUserInput();
  		updateGameScreen(getGraphics());
                espere(30);
                /*
		try {
		   Thread.currentThread().sleep(30);
		    }
                catch(Exception e) {}
                 * */


           }
             espere(2000);

          }


	}


        private void espere(long s)
        {
        try {
        Thread.currentThread().sleep(s);
            }
            catch (InterruptedException e)
            {
            e.printStackTrace();
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
          colunas = nr1;

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

          if (novo != 1)
          {
            if (total  != 0)
            {
                g.drawString((nr1 * nr2)+" / "+nr1+ "=" + linhas,
                          1, 145, Graphics.TOP | Graphics.LEFT);
            }
            else
            {
                g.drawString((nr1 * nr2)+" / "+nr1,
                      1, 145, Graphics.TOP | Graphics.LEFT);
            }
          }
         else
         {

           String Texto = "";
          if ((nr1 * nr2)  == (linhas * colunas))
          {
              Texto = "Parabéns !";
              g.drawString(Texto,
              1, 145, Graphics.TOP | Graphics.LEFT);
          }
          else
          {
              Texto = "A Divisão é "+(nr1 * nr2) / nr1;
              g.drawString(Texto,
              1, 135, Graphics.TOP | Graphics.LEFT);
              Texto =  "Tente de novo !";
              g.drawString(Texto,
              1, 145, Graphics.TOP | Graphics.LEFT);
          }


         }

	  // this call paints off screen buffer to screen
	  flushGraphics();

	}

	private void calculateCoupleX(int keyState) {


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

           if((keyState & FIRE_PRESSED) != 0)
           {
               novo = 1;



           }


	}

}