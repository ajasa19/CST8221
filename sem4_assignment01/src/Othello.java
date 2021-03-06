/********************************************************************************
Filename:			Othello.java
Version:			1.0
Author:				Asim Jasarevic
StudentNo:			040922815
Course Name/Number:	Java Application 20F_CST8221?300
Lab Sect:			303
Assignment #:		1
Assignment name:	Othello Part 1
Due Date:			2020, Oct, 16
Submission Date:	2020, Oct, 08
Purpose:			To be ran and used to call on to other class objects to first run splashScreen and then viewController
All files:			Othello.java, OthelloSplashScreen.java, OthelloViewController.java
********************************************************************************/

public class Othello  {
  
  /*new splashScreen object sets up and launches splash screen and timer for duration*/
  static OthelloSplashScreen splashScreen = new OthelloSplashScreen(3000); 
  /*new viewController object main screen build and launch main screen*/
  static OthelloViewController viewController = new OthelloViewController(); 

  public static void main(String[] args) {

    splashScreen.showSplashWindow(); /*call showSplashWindow() function in OthelloSplashScreen.java*/
    viewController.main(args); /*call main(args) function in viewController.java*/
    //viewController.main(args); /*call main(args) function in viewController.java*/
  
  }//end main
  
}// end SplashScreenDemo class
