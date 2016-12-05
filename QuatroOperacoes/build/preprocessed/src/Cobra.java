/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

import com.sun.perseus.model.Time;
import gov.nist.siplite.Timeout;
import java.io.IOException;
import java.util.Random;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.GameCanvas;



/**
 * @author marlos
 */
public class Cobra extends MIDlet  implements CommandListener {
   CobraCanvas CobraCanvas; // Contains a custom Canvas class

   private static final Command CMD_EXIT = new Command("Exit", Command.EXIT, 1);
   private static final Command CMD_START = new Command("Restart", Command.ITEM, 2);

    public Cobra()
   {
      CobraCanvas = new CobraCanvas();    // COnstruct the canvas
   }

    public void startApp() {
      Display display = Display.getDisplay(this);

      CobraCanvas.addCommand(CMD_EXIT);        // Add commands to the canvas
      CobraCanvas.addCommand(CMD_START);
      CobraCanvas.setCommandListener(this);

      display.setCurrent(CobraCanvas);         // Add canvas to screen
      CobraCanvas.repaint();
      CobraCanvas.start();

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

     // Handle softkeys
    public void commandAction(Command c, Displayable d)
    {
     if (c == CMD_EXIT) {
       destroyApp(false);
       notifyDestroyed();
       }
     if (c == CMD_START) {
       CobraCanvas.start();
       }
     }
}

