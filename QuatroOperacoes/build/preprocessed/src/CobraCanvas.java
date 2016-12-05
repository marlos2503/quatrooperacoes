/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

import javax.microedition.lcdui.game.GameCanvas;
import com.sun.perseus.model.Time;
import gov.nist.siplite.Timeout;
import java.io.IOException;
import java.util.Random;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author marlos
 */
public class CobraCanvas  extends GameCanvas implements Runnable {

       // the couple image

        int[][] snake = new int[200][2];
        int snakeNum;
        int direction;
        private final int DIRECTION_UP = 0;
        private final int DIRECTION_DOWN = 1;
        private final int DIRECTION_LEFT = 2;
        private final int DIRECTION_RIGHT = 3;
        private Image fundoImg;
        private Image coupleImg;
        int width;
        int height;

        private final byte SNAKEWIDTH = 4;


        boolean isPaused = false;

        boolean isRun = true;

        private final int SLEEP_TIME = 300;


        int foodX;
        int foodY;
        boolean b = true;

        Random random = new Random();
        
        public CobraCanvas() {
	  super(true);
	}
        public void start()
	{

          //  System.out.println("Start ");
             try {
             fundoImg = Image.createImage("/res/fundo.png");
             coupleImg = Image.createImage("/res/objeto.png");
             init();
             width = fundoImg.getWidth();
             height = fundoImg.getHeight();
             new Thread(this).start();
            }
             catch(IOException ioex) { System.err.println(ioex); }
        }

        private void init(){
        //    System.out.println("Init ");
            snakeNum = 7;
            for(int i = 0;i < snakeNum;i++){
                    snake[i][0] = 100 - SNAKEWIDTH * i;
                    snake[i][1] = 40;
            }

            direction =  DIRECTION_DOWN;

            foodX = 100;
            foodY = 100;
        }

        private void updateGameScreen(Graphics g) {
        //    System.out.println("Update  ");

             // the next two lines clear the background
            g.setColor(0xffffff);
            g.fillRect(0, 0, getWidth(), getHeight() );
            g.drawImage(fundoImg, 0, 0, Graphics.TOP | Graphics.LEFT);
            Graphics fundo = g;



            for(int i = 0;i < snakeNum;i++){
                 fundo.drawImage(coupleImg, snake[i][0],snake[i][1], Graphics.TOP | Graphics.LEFT);
            }
            if(b){
             fundo.drawImage(coupleImg, foodX,foodY,Graphics.TOP | Graphics.LEFT);
            }
             flushGraphics();
        }

        private void move(int direction){
           // System.out.println("Move  ");
            for(int i = snakeNum - 1;i > 0;i--){
                snake[i][0] = snake[i - 1][0];
                snake[i][1] = snake[i - 1][1];
            }

       //     System.out.println(" T "+
         //                      snake[0][0] +" -- "+
        //                       snake[0][1]);

            switch(direction){
            case DIRECTION_UP:
                snake[0][1] = snake[0][1] - SNAKEWIDTH;
                break;
            case DIRECTION_DOWN:
                snake[0][1] = snake[0][1] + SNAKEWIDTH;
                break;
            case DIRECTION_LEFT:
                snake[0][0] = snake[0][0] - SNAKEWIDTH;
                break;
            case DIRECTION_RIGHT:
                snake[0][0] = snake[0][0] + SNAKEWIDTH;
                break;
            }
        }

        private void eatFood(){
         if(snake[0][0] == foodX && snake[0][1] == foodY){
          snakeNum++;
          generateFood();
         }
        }


        private void generateFood(){
         while(true){
          foodX = Math.abs(random.nextInt() % (width - SNAKEWIDTH + 1))
                            / SNAKEWIDTH * SNAKEWIDTH;
          foodY = Math.abs(random.nextInt() % (height - SNAKEWIDTH + 1))
                            / SNAKEWIDTH * SNAKEWIDTH;
          boolean b = true;
          for(int i = 0;i < snakeNum;i++){
           if(foodX == snake[i][0] && snake[i][1] == foodY){
            b = false;
            break;
           }
          }
          if(b){
           break;
          }
         }
        }


        private boolean isGameOver(){
         if(snake[0][0] < 0 || snake[0][0] > (width - SNAKEWIDTH) ||
            snake[0][1] < 0 || snake[0][1] > (height - SNAKEWIDTH)){
          return true;
         }
         for(int i = 4;i < snakeNum;i++){
          if(snake[0][0] == snake[i][0]
             && snake[0][1] == snake[i][1]){
           return true;
          }
         }

         return false;
        }


        private void  verteclado() {

	  // get the state of keys
	  int keyState = getKeyStates();

	  // calculate the position for x axis
	  calculaTecla(keyState);

	}

        public void calculaTecla(int keyCode){
        //     System.out.println("tecla");
            int action = keyCode;

            switch(action){
            case UP_PRESSED:
                if(direction != DIRECTION_DOWN){
                    direction = DIRECTION_UP;
                }
                break;
            case DOWN_PRESSED:
                if(direction != DIRECTION_UP){
                    direction = DIRECTION_DOWN;
                }
                break;
            case LEFT_PRESSED:
                if(direction != DIRECTION_RIGHT){
                    direction = DIRECTION_LEFT;
                }
                break;
            case RIGHT_PRESSED:
                if(direction != DIRECTION_LEFT){
                    direction = DIRECTION_RIGHT;
                }
                break;
            case FIRE:

                isPaused = !isPaused;
                break;
            }
        }


        public void run(){
            Graphics g = getGraphics();
            try{
                while (isRun) {
                    verteclado();
                //    System.out.println("Volta");
                 long start = System.currentTimeMillis();


                    if(!isPaused){

                        eatFood();
                        move(direction);

                        if(isGameOver()){
                    //     System.out.println("Fim ");
                         break;
                        }


                        b = !b;
                    }

                    updateGameScreen(g);

                    long end = System.currentTimeMillis();
                    if(end - start < SLEEP_TIME){
                     Thread.sleep(SLEEP_TIME - (end - start));
                    }
                }
            }catch(Exception e){}



}

}
