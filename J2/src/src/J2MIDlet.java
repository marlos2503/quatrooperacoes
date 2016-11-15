/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class J2MIDlet extends MIDlet implements CommandListener {

     Multiplicacao gCanvas;
     Cobra   gCobra;



    // display manager
    Display display = null;

    // a menu with items
    List menu = null; // main menu

    // textbox
    TextBox input = null;

    // command
    static final Command backCommand = new Command("Back", Command.BACK, 0);
    static final Command mainMenuCommand = new Command("Main", Command.SCREEN, 1);
    static final Command exitCommand = new Command("Exit", Command.STOP, 2);
    String currentMenu = null;

    // constructor.
    public J2MIDlet() {

		gCanvas = new Multiplicacao();
                gCobra  = new Cobra();

    }

    /**
     * Start the MIDlet by creating a list of items and associating the
     * exit command with it.
     */
    public void startApp() throws MIDletStateChangeException {
      display = Display.getDisplay(this);

      menu = new List("Menu Items", Choice.IMPLICIT);
      menu.append("Multiplicacao", null);
      menu.append("Cobra", null);
      menu.append("Enviar Dados", null);
      menu.addCommand(exitCommand);
      menu.setCommandListener(this);

      mainMenu();
    }

    public void pauseApp() {
      display = null;
      menu = null;
      input = null;
    }

    public void destroyApp(boolean unconditional) {
      notifyDestroyed();
    }

    // main menu
    void mainMenu() {
      display.setCurrent(menu);
      currentMenu = "Main";
    }

    /**
     * a generic method that will be called when selected any of
     * the items on the list.
     */
    public void prepare() {
       input = new TextBox("Enter some text: ", "", 5, TextField.ANY);
       input.addCommand(backCommand);
       input.setCommandListener(this);
       input.setString("");
       display.setCurrent(input);
    }

    /**
     * Test item1.
     */
    public void testItem1() {
       Display display = Display.getDisplay(this);
	gCanvas.start();
	display.setCurrent(gCanvas);
      
      currentMenu = "item1";
    }

    /**
     * Test item2.
     */
    public void testItem2() {
       prepare();
       Display display = Display.getDisplay(this);
	gCobra.start();
	display.setCurrent(gCobra);
      

       currentMenu = "item2";
    }



    /**
     * Test item2.
     */
    public void testItem3() {
       prepare();
       currentMenu = "item3";
    }

   
   /**
    * Handle events.
    */
   public void commandAction(Command c, Displayable d) {
      String label = c.getLabel();
      if (label.equals("Exit")) {
         destroyApp(true);
      } else if (label.equals("Back")) {
          if(currentMenu.equals("item1") || currentMenu.equals("item2")
                  || currentMenu.equals("item3"))
          {
            // go back to menu
            mainMenu();
          }

      } else {
         List down = (List)display.getCurrent();
         switch(down.getSelectedIndex()) {
           case 0: testItem1();break;
           case 1: testItem2();break;
           case 2: testItem3();break;
    
         }

      }
  }
} 
