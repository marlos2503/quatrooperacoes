/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src;



import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class Multiplicacao  extends MIDlet implements CommandListener
{

   MultiCanvas MultiCanvas; // Contains a custom Canvas class

   private static final Command CMD_EXIT = new Command("Exit", Command.EXIT, 1);
   private static final Command CMD_START = new Command("Restart", Command.ITEM, 2);


   public Multiplicacao()
   {
      MultiCanvas = new MultiCanvas();    // COnstruct the canvas
   }

   public void startApp()
   {
      Display display = Display.getDisplay(this);

      MultiCanvas.addCommand(CMD_EXIT);        // Add commands to the canvas
      MultiCanvas.addCommand(CMD_START);
      MultiCanvas.setCommandListener(this);

      display.setCurrent(MultiCanvas);         // Add canvas to screen
      MultiCanvas.repaint();
      MultiCanvas.start();
   }

   public void pauseApp()
   {
   }

   public void destroyApp(boolean unconditional)
   {
   }

   // Handle softkeys
   public void commandAction(Command c, Displayable d)
   {
     if (c == CMD_EXIT) {
       destroyApp(false);
       notifyDestroyed();
       }
     if (c == CMD_START) {
       MultiCanvas.start();
       }
     }
}


// This internal class is a customized Canvas, that displays the game. It also
// implemnts the Runnable interface (which means it needs a run method) to start
// separate threads.

