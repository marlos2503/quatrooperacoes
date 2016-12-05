/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src;


import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class Divisao  extends MIDlet implements CommandListener
{

   DivCanvas DivCanvas; // Contains a custom Canvas class

   private static final Command CMD_EXIT = new Command("Exit", Command.EXIT, 1);
   private static final Command CMD_START = new Command("Restart", Command.ITEM, 2);


   public Divisao()
   {
      DivCanvas = new DivCanvas();    // COnstruct the canvas
   }

   public void startApp()
   {
      Display display = Display.getDisplay(this);

      DivCanvas.addCommand(CMD_EXIT);        // Add commands to the canvas
      DivCanvas.addCommand(CMD_START);
      DivCanvas.setCommandListener(this);

      display.setCurrent(DivCanvas);         // Add canvas to screen
      DivCanvas.repaint();
      DivCanvas.start();
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
       DivCanvas.start();
       }
     }
}

