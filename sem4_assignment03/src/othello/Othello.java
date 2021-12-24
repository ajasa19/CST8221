package othello;
/********************************************************************************
Filename:			Othello.java
Version:			1.0
Author:				Asim Jasarevic
StudentNo:			040922815
Course Name/Number:	Java Application 20F_CST8221–300
Lab Sect:			303
Professor:			Daniel Cormier
Assignment #:		2-1
Assignment name:	Othello 2 part 1
Due Date:			2020, Nov, 22
Submission Date:	2020, Nov, 018
Purpose:			To be ran and used to call on to other class objects to first run splashScreen and then viewController
All files:			Othello.java, OthelloSplashScreen.java, OthelloViewController.java
********************************************************************************/

public class Othello  {
  
  /*new splashScreen object sets up and launches splash screen and timer for duration*/
  static OthelloSplashScreen splashScreen = new OthelloSplashScreen(3000); 

	/********************************************************************************
	Function name:		main
	Purpose			    set up window and frame 
	@param				String[] args	
	@version			1.0
	@author				Asim Jasarevic
	********************************************************************************/
  public static void main(String[] args) {

    splashScreen.showSplashWindow(); /*call showSplashWindow() function in OthelloSplashScreen.java*/
    OthelloViewController.main(args); /*call main(args) function in viewController.java*/
  
  }//end main
  
}// end SplashScreenDemo class
