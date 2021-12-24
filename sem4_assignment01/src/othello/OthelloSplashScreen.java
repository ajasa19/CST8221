package othello;
/********************************************************************************
Filename:			OthelloSplashScreen.java
Version:			1.0
Author:				Daniel Cormier (used large chunk of code from lab02. 	P.S. thanks Dan)
Sub Author:			Asim Jasarevic
StudentNo:			040922815
Course Name/Number:	Java Application 20F_CST8221–300
Lab Sect:			303
Professor:			Daniel Cormier
Assignment #:		1
Assignment name:	Othello Part 1
Due Date:			2020, Oct, 16
Submission Date:	2020, Oct, 08
Purpose:			Set up, build, and run the best splash screen seen by man
********************************************************************************/
import java.awt.*;
import javax.swing.*;


public class OthelloSplashScreen extends JWindow {

  private static final long serialVersionUID = 6248477390124803341L;
  private final int duration;
  
/**
  Default constructor. Sets the show time of the splash screen.
*/
  public OthelloSplashScreen(int duration) {
    this.duration = duration;
  }

	/********************************************************************************
	Function name:		showSplashWindow
	Purpose			    set up splash sreen with items and launch it 
	@version			1.0
	@author				Daniel Cormier
	@author				Asim Jasarevic
	********************************************************************************/
  public void showSplashWindow() {

     JPanel content = new JPanel(new BorderLayout());
     content.setBackground(Color.GRAY);
    
    // Set the window's bounds, position the window in the center of the screen
    int width =  514+20;
    int height = 207+30;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width-width)/2;
    int y = (screen.height-height)/2;
    setBounds(x,y,width,height);

	ImageIcon ImageIcon = new ImageIcon("splashAddOn.jpg");
	JLabel label = new JLabel(ImageIcon);
  
    JLabel demo = new JLabel("Asim Jasarevic SN 040922815", JLabel.CENTER);
    demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
    content.add(label, BorderLayout.CENTER);
    content.add(demo, BorderLayout.SOUTH);   
    
    // create custom RGB color
    Color customColor = Color.GRAY;
    content.setBorder(BorderFactory.createLineBorder(customColor, 10));
    
    //replace the window content pane with the content JPanel
      setContentPane(content);

    //make the splash window visible
    setVisible(true);

    // Snooze for awhile, pretending the code is loading something awesome while
    // our splashscreen is entertaining the user.
    try {
    	
    	 Thread.sleep(duration); }
    catch (InterruptedException e) {/*log an error here?*//*e.printStackTrace();*/}
    //destroy the window and release all resources
    dispose(); 
    //You can hide the splash window. The resources will not be released.
    //setVisible(false);
  }


}// end SplashScreenDemo class
