package othello;

/********************************************************************************
Filename:			OthelloModel.java
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
Purpose:			Hold a bunch of methods relating to the logic of an Othello game, logic such as capturing pieces seting up icons on tiles
					and seing valid moves. All methods to be called in controller.
 ********************************************************************************/

import javax.swing.JLabel;


public class OthelloModel {

	OthelloViewController ViewControllerPub = new OthelloViewController(); /*obj used to see global vars and tilearray in OthelloViewController*/
	int tilesArrayInt[][] = new int[8][8]; /*tilesArrayInt is a 2d array representing board with 1/2/0*/
	int tempTilesArrayInt[][] = new int[8][8]; /*tempTilesArrayInt is a 2d array representing board with 1/2/0 used to test reloaded boards*/
	int tempValidTilesArrayInt[][] = new int[8][8]; /*tempTilesArrayInt is a 2d array representing board with 1/2/0 used to test valid moves*/
	
	public static final int NORMAL=0;
	public static final int CORNER_TEST=1;
	public static final int OUTER_TEST=2;
	public static final int TEST_CAPTURE=3;
	public static final int TEST_CAPTURE2=4;
	public static final int UNWINNABLE=5;
	public static final int INNER_TEST=6;

	public static final int EMPTY=0;
	public static final int BLACK=1;
	public static final int WHITE=2;

	/********************************************************************************
	Function name:		loadObject
	purpose				This is a methode used to load a new obj so gobal vars can be read from controller
	@param				ViewController (reloaded object holds global and constructor values and vars)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void loadObject(OthelloViewController ViewController) {
		ViewControllerPub = ViewController;
	}

	/********************************************************************************
	Function name:		initialize
	purpose				This is a methode used to set a model 2d array to a predetermined board used for debuging
	@param				mode (board setup)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void initialize(int mode)
	{
		switch (mode)
		{
		case CORNER_TEST: 
			tilesArrayInt=new int[][]{
				{2, 0, 0, 0, 0, 0, 0, 1},
				{0, 1, 0, 0, 0, 0, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 1, 0},
				{2, 0, 0, 0, 0, 0, 0, 2}};

				break;
		case OUTER_TEST:
			tilesArrayInt = new int[][] {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 2, 2, 2, 2, 2, 2, 0},
				{0, 2, 1, 1, 1, 1, 2, 0},
				{0, 2, 1, 0, 0, 1, 2, 0},
				{0, 2, 1, 0, 0, 1, 2, 0},
				{0, 2, 1, 1, 1, 1, 2, 0},
				{0, 2, 2, 2, 2, 2, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
				break;
		case INNER_TEST:
			tilesArrayInt = new int[][] {
				{2, 2, 2, 2, 2, 2, 2, 2},
				{2, 0, 0, 0, 0, 0, 0, 2},
				{2, 0, 2, 2, 2, 2, 0, 2},
				{2, 0, 2, 1, 1, 2, 0, 2},
				{2, 0, 2, 1, 1, 2, 0, 2},
				{2, 0, 2, 2, 2, 2, 0, 2},
				{2, 0, 0, 0, 0, 0, 0, 2},
				{2, 2, 2, 2, 2, 2, 2, 2}};
				break;
		case UNWINNABLE:
			tilesArrayInt = new int[][] {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
				break;
		case TEST_CAPTURE:
			tilesArrayInt=new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 2, 2, 2, 1, 1, 0},
				{0, 1, 2, 0, 2, 1, 1, 0},
				{0, 1, 2, 2, 2, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
				break;

		case TEST_CAPTURE2:
			tilesArrayInt=new int[][]{
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 2, 2, 2, 1, 2, 1, 1},
				{1, 2, 2, 2, 2, 2, 1, 1},
				{1, 2, 2, 0, 2, 2, 1, 1},
				{1, 2, 2, 2, 2, 1, 1, 1},
				{1, 2, 1, 2, 2, 2, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1}};
				break;
		default:
			tilesArrayInt = new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 2, 1, 0, 0, 0},
				{0, 0, 0, 1, 2, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};

		}	
	}

	/********************************************************************************
	Function name:		setTilesArray
	purpose				This is a methode used to set icons of tiles of tilesArray in view class to what model array reflects
	@param				ViewController (called to update imageIcon values)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void setTilesArray(OthelloViewController ViewController) {
		/*run through 2 for loops and see if tilesArrayInt == 1/2/0 depending on int set icon to black, white, or nothing*/
		for (int i = 0; i < 8 ; i++) {
			for (int n = 0; n < 8 ; n++) {
				if (tilesArrayInt[i][n] == 1) {
					ViewController.tilesArray[i][n].setIcon(ViewController.imageIconBlack);
				}
				if (tilesArrayInt[i][n] == 2) {
					ViewController.tilesArray[i][n].setIcon(ViewController.imageIconWhite);
				}
				if (tilesArrayInt[i][n] == 0) {
					ViewController.tilesArray[i][n].setIcon(null);
				}
			}
		}
	}

	/********************************************************************************
	Function name:		printBoard
	purpose				This is a methode used for debugging to see tilesArrayInt in console
	@param				tilesArrayInt[][] (2d array model of board)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void printBoard(int tilesArrayInt[][]) {
		/*run through 2 for loops and print content of array*/
		for (int i = 0; i < 8 ; i++) {
			for (int n = 0; n < 8 ; n++) {
				System.out.print(tilesArrayInt[i][n] + " ");
			}
			System.out.print("\n");
		}
	}

	/********************************************************************************
	Function name:		getBoard
	purpose				This is a methode used for setting tilesArrayInt either 1/2/0 depending on what icon is set on a tile in tilesArray
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void getBoard() {

		int blacks = 0, whites = 0; /*used to set how many black and white pieces are on board*/

		/*run through 2 for loops and set tilesArrayInt to 1/2 */
		for (int i = 0; i < 8 ; i++) {
			for (int n = 0; n < 8 ; n++) {

				/*set tilesArrayInt to 2 if tile array contains imageIconWhite */
				if (ViewControllerPub.tilesArray[i][n].getIcon() == ViewControllerPub.imageIconWhite) {
					tilesArrayInt[i][n] = 2;
					whites++;
				}
				/*set tilesArrayInt to 1 if tile array contains imageIconBlack */
				else if (ViewControllerPub.tilesArray[i][n].getIcon() == ViewControllerPub.imageIconBlack) {
					tilesArrayInt[i][n] = 1;
					blacks++;
				}
			}
		}

		ViewControllerPub.blackScore = blacks;
		ViewControllerPub.whiteScore = whites;

	}

	/********************************************************************************
	Function name:		isValid
	purpose				This is a methode used to see if a move a player want to make is valid
	@return 			int x, int y, int player
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public boolean isValid(int x, int y, int player) {

		x = x - 1;
		y = y - 1;

		/*set a 2d tempTilesArrayInt to tilesArrayInt*/
		for(int i=0; i<tilesArrayInt.length; i++)
			for(int j=0; j<tilesArrayInt[i].length; j++) {
				tempTilesArrayInt[i][j]=tilesArrayInt[i][j];
				tempValidTilesArrayInt[i][j]=tilesArrayInt[i][j];
			}

		/*call methode to see if move is valid on horiz and vert plains*/
		checkValid(player);
		/*call methode to see if move is valid on diag plains*/
		checkValidZ(player);


		tempTilesArrayInt[x][y] = player;

		/*see if piece is already on tile*/
		if (this.tilesArrayInt[x][y] != 0) {
			return false;
		}

		/*see if 3 (represents potetial move) is present in array*/
		if (tempValidTilesArrayInt[x][y] == 3) {
			return true;
		}

		/*false by default*/
		return false;
	}

	/********************************************************************************
	Function name:		move
	purpose				This is a methode used to move player piece and set up captures
	@param	 			x (players x position), y (players x position) , player (player turn)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void move(int x, int y, int player) {		
		x = x - 1;
		y = y - 1;

		/*set tilesArrayInt to tempTilesArrayInt*/
		for(int i=0; i<tilesArrayInt.length; i++)
			for(int j=0; j<tilesArrayInt[i].length; j++)
				tilesArrayInt[i][j]=tempTilesArrayInt[i][j];

		/*check captures on horiz plain*/
		checkYAxis(x, y, player);
		/*check captures on vetr plain*/
		checkXAxis(x, y, player);
		/*check captures on diag plain*/
		checkZAxis(x, y, player);

		/*set board up agian to reflect updates*/
		getBoard();

	}

	/********************************************************************************
	Function name:		seeValidMoves
	purpose				This is a methode used to display valid moves on board
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void seeValidMoves() {

		int player = ViewControllerPub.getPlayerTurn(); /*current player move*/

		/*set board up agian to reflect updates*/
		getBoard();
		/*for loop to set tempTilesArrayInt and tempValidTilesArrayInt to tilesArrayInt*/
		for(int i=0; i<tilesArrayInt.length; i++)
			for(int j=0; j<tilesArrayInt[i].length; j++) {
				tempTilesArrayInt[i][j]=tilesArrayInt[i][j];
				tempValidTilesArrayInt[i][j]=tilesArrayInt[i][j];
			}

		/*get valid spots for x and y plain*/
		checkValid(player);
		/*get valid spots for diag plain*/
		checkValidZ(player);


		/*for loop to set tilesArray icons to checkmarks where ever a valid move spot was placed in tempValidTilesArrayInt*/
		for (int n = 0; n < 8 ; n++) {
			for (int i = 0; i < 8 ; i++) {
				if (tempValidTilesArrayInt[n][i] == 3) {
					ViewControllerPub.tilesArray[n][i].setIcon(ViewControllerPub.imageIconCheck);
				}
			}

		}
	}

	/********************************************************************************
	Function name:		doNotSeeValidMoves
	purpose				This is a methode used to hide valid moves on board
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void doNotSeeValidMoves() {
		/*set board up agian to reflect updates*/
		getBoard();

		/*for loop to set setIcon to null where icon = imageIconCheck*/
		for (int n = 0; n < 8 ; n++) {
			for (int i = 0; i < 8 ; i++) {
				if (ViewControllerPub.tilesArray[n][i].getIcon() == ViewControllerPub.imageIconCheck) {
					ViewControllerPub.tilesArray[n][i].setIcon(null);
				}
			}

		}
	}

	/********************************************************************************
	Function name:		canMove
	purpose				This is a methode used to see if a potential player move can be made at any point
	@param				player (player turn)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public boolean canMove(int player) {
		/*set board up agian to reflect updates*/
		getBoard();

		/*for loop to set tempTilesArrayInt and tempValidTilesArrayInt to tilesArrayInt*/
		for(int i=0; i<tilesArrayInt.length; i++)
			for(int j=0; j<tilesArrayInt[i].length; j++) {
				tempTilesArrayInt[i][j]=tilesArrayInt[i][j];
				tempValidTilesArrayInt[i][j]=tilesArrayInt[i][j];
			}

		/*get valid spots for x and y plain*/
		checkValid(player);
		/*get valid spots for diag plain*/
		checkValidZ(player);

		/*if a valid move int 3 is found in array valid move is posible*/
		for (int n = 0; n < 8 ; n++) {
			for (int i = 0; i < 8 ; i++) {
				if (tempValidTilesArrayInt[n][i] == 3) {
					return true;
				}
			}

		}
		return false;
	}

	/********************************************************************************
	Function name:		getChips
	purpose				This is a methode used to get chips and set chip counter
	@param				player (player turn)
	@return				chipAmmount
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public int getChips(int player) {

		int chips = player; /*set chip to whatever layer is*/
		int chipAmmount = 0; /*chip counter*/

		/*for loop to find all instances of a piece in tilesArrayInt and add to counter*/
		for (int i = 0; i < 8 ; i++) {
			for (int n = 0; n < 8 ; n++) {

				if (tilesArrayInt[i][n] == chips) {
					chipAmmount++;
				}

			}
		}

		return chipAmmount;
	}


	/********************************************************************************
	Function name:		checkXAxis
	purpose				This is a methode used to see if a capture can be made on the x axis of where a player piece is placed
						and then update icons depending on where captures are made
	@param				x (players x position), y (players x position) , player (player turn)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void checkXAxis(int x, int y, int player) {		
		int blackPieceStart = -1; /*y point where a capture starts*/
		int blackPieceEnd = -1; /*y point where a capture ends*/
		int playerCor = 1; /*current player piece*/
		int opponentCor = 2; /*current opponent piece*/

		/*reverse vars if player 2*/
		if (player ==  2) {
			playerCor = 2;
			opponentCor = 1;
		}

		/*for loop to start checking row*/
		for (int n = 0; n < 8 ; n++) {

			/*if a player piece is here and the next piece is an opponent piece set blackPieceStart*/
			if (n != 7 && blackPieceStart == -1 && tilesArrayInt[x][n] == playerCor && tilesArrayInt[x][n+1] == opponentCor) {
				blackPieceStart = n;
			}
			/*if blackPieceStart is set and we come acoss an empty tile reset blackPieceStart*/
			else if ( n != 0 && tilesArrayInt[x][n] == 0 && blackPieceStart != -1) {
				blackPieceStart = -1;
			}
			/*if blackPieceStart is set and we come acoss an opponent piece and the next tile has a player piece set blackPieceEnd*/
			else if ( n != 7 && tilesArrayInt[x][n] == opponentCor && tilesArrayInt[x][n+1] == playerCor && blackPieceStart != -1) {
				blackPieceEnd = n;

			}

			/*if both blackPieceStart and blackPieceEnd are set we have a capture so now loop through the start location and 
				  and end location and set icons after done loop reset blackPieceEnd*/
			if (blackPieceStart != -1 && blackPieceEnd != -1) {
				for (int i = blackPieceStart ; i <= blackPieceEnd+1; i++) {
					if (player == 1) {
						ViewControllerPub.tilesArray[x][i].setIcon(ViewControllerPub.imageIconBlack);
						ViewControllerPub.tilesArray[x][y].setIcon(ViewControllerPub.imageIconBlack);
					} else {
						ViewControllerPub.tilesArray[x][i].setIcon(ViewControllerPub.imageIconWhite);
						ViewControllerPub.tilesArray[x][y].setIcon(ViewControllerPub.imageIconWhite);
					}
				}
				blackPieceStart = blackPieceEnd;
				blackPieceEnd = -1;
			}
		}
	}

	/********************************************************************************
	Function name:		checkYAxis
	purpose				This is a methode used to see if a capture can be made on the y axis of where a player piece is placed
						and then update icons depending on where captures are made
	@param				x (players x position), y (players x position) , player (player turn)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void checkYAxis(int x, int y, int player) {		

		int blackPieceStart = -1; /*y point where a capture starts*/
		int blackPieceEnd = -1; /*y point where a capture ends*/
		int playerCor = 1; /*current player piece*/
		int opponentCor = 2; /*current opponent piece*/

		/*reverse vars if player 2*/
		if (player ==  2) {
			playerCor = 2;
			opponentCor = 1;
		}

		/*for loop to start checking columns*/
		for (int n = 0; n < 8 ; n++) {

			/*if a player piece is here and the next piece is an opponent piece set blackPieceStart*/
			if (n != 7 && blackPieceStart == -1 && tilesArrayInt[n][y] == playerCor && tilesArrayInt[n+1][y] == opponentCor) {
				blackPieceStart = n;
			}
			/*if blackPieceStart is set and we come acoss an empty tile reset blackPieceStart*/
			else if ( n != 0 && tilesArrayInt[n][y] == 0 && blackPieceStart != -1) {
				blackPieceStart = -1;
			}
			/*if blackPieceStart is set and we come acoss an opponent piece and the next tile has a player piece set blackPieceEnd*/
			else if (  n < 7 && tilesArrayInt[n][y] == opponentCor && tilesArrayInt[n+1][y] == playerCor && blackPieceStart != -1) {
				blackPieceEnd = n;
			}

			/*if both blackPieceStart and blackPieceEnd are set we have a capture so now loop through the start location and 
				  and end location and set icons after done loop reset blackPieceEnd*/
			if (blackPieceEnd != -1 && blackPieceStart != -1) {
				for (int i = blackPieceStart ; i < blackPieceEnd + 1; i++) {

					if (player == 1) {
						ViewControllerPub.tilesArray[i][y].setIcon(ViewControllerPub.imageIconBlack);
						ViewControllerPub.tilesArray[x][y].setIcon(ViewControllerPub.imageIconBlack);
					} else {
						ViewControllerPub.tilesArray[i][y].setIcon(ViewControllerPub.imageIconWhite);
						ViewControllerPub.tilesArray[x][y].setIcon(ViewControllerPub.imageIconWhite);
					}
				}
				blackPieceStart = blackPieceEnd;
				blackPieceEnd = -1;
			}
		}


	}

	/********************************************************************************
	Function name:		checkZAxis
	purpose				This is a methode used to see if a capture can be made on the horizontal axis of where a player piece is placed
						and then update icons depending on where captures are made
	@param				x (players x position), y (players x position) , player (player turn)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void checkZAxis(int x, int y, int player) {
		int blackPieceStartX = -1; /*x point where a capture starts*/
		int blackPieceStartY = -1; /*y point where a capture starts*/
		int blackPieceEndX = -1; /*x point where a capture end*/
		int blackPieceEndY = -1; /*y point where a capture end*/
		int playerCor = 1; /*current player piece*/
		int opponentCor = 2; /*current opponent piece*/

		/*reverse vars if player 2*/
		if (player ==  2) {
			playerCor = 2;
			opponentCor = 1;
		}

		/*set start piece at location of move*/
		blackPieceStartX = x;
		blackPieceStartY = y;

		/*for loop to start checking left up portion of X pattern*/
		int i = y - 1;
		for (int n = x - 1 ; n >= 1 && i >= 1 ; n--, i--) {

			/*if blackPieceStart is set and we come acoss an empty tile or another player piece reset blackPieceStart*/
			if ( blackPieceStartX != -1 && blackPieceEndX == -1 && tilesArrayInt[n][i] == playerCor || tilesArrayInt[n][i] == 0) {
				blackPieceStartX = -1;
				blackPieceStartY = -1;
				n = 1;
			}
			/*if blackPieceStart is set and we come acoss an opponent piece and the next tile has a player piece set blackPieceEnd*/
			else if (blackPieceStartX != -1 && blackPieceEndX == -1 && tilesArrayInt[n][i] == opponentCor && tilesArrayInt[n-1][i-1] == playerCor) {
				blackPieceEndX = n-1;
				blackPieceEndY = i-1;
			}

			/*if both blackPieceStart and blackPieceEnd are set we have a capture so now loop through the start location and 
				  and end location and set icons after done loop reset all location values and exit loop*/
			if (blackPieceStartX != -1 && blackPieceEndX != -1) {

				int m = blackPieceStartY;
				for (int j = blackPieceStartX ; j >= blackPieceEndX ; j--) {
					if (player == 1) {
						ViewControllerPub.tilesArray[j][m].setIcon(ViewControllerPub.imageIconBlack);
					} else {
						ViewControllerPub.tilesArray[j][m].setIcon(ViewControllerPub.imageIconWhite);
					}
					m--;
				}

			}
		}
		/*reset all points*/
		blackPieceStartX = -1;
		blackPieceStartY = -1;
		blackPieceEndX = -1;
		blackPieceEndY = -1;

		/*set start piece at location of move*/
		blackPieceStartX = x;
		blackPieceStartY = y;

		/*for loop to start checking right down portion of X pattern*/
		i = y + 1;
		for (int n = x + 1 ; n < 7 && i < 7 ; n++, i++) {

			/*if blackPieceStart is set and we come acoss an empty tile or another player piece reset blackPieceStart*/
			if (blackPieceStartX != -1 && blackPieceEndX == -1 && tilesArrayInt[n][i] == playerCor || tilesArrayInt[n][i] == 0) {
				blackPieceStartX = -1;
				blackPieceStartY = -1;
				n = 8;
			}

			/*if blackPieceStart is set and we come acoss an opponent piece and the next tile has a player piece set blackPieceEnd*/
			else if (blackPieceStartX != -1 && blackPieceEndX == -1 && tilesArrayInt[n][i] == opponentCor && tilesArrayInt[n+1][i+1] == playerCor) {
				blackPieceEndX = n+1;
				blackPieceEndY = i+1;
			}

			/*if both blackPieceStart and blackPieceEnd are set we have a capture so now loop through the start location and 
				  and end location and set icons after done loop reset all location values and exit loop*/
			if (blackPieceStartX != -1 && blackPieceEndX != -1) {

				int m = blackPieceStartY;
				for (int j = blackPieceStartX ; j < blackPieceEndX ; j++) {
					if (player == 1) {
						ViewControllerPub.tilesArray[j][m].setIcon(ViewControllerPub.imageIconBlack);
					} else {
						ViewControllerPub.tilesArray[j][m].setIcon(ViewControllerPub.imageIconWhite);
					}
					m++;
				}

			}
		}
		/*reset all points*/
		blackPieceStartX = -1;
		blackPieceStartY = -1;
		blackPieceEndX = -1;
		blackPieceEndY = -1;

		/*set start piece at location of move*/
		blackPieceStartX = x;
		blackPieceStartY = y;

		/*for loop to start checking right up portion of X pattern*/
		i = y + 1;
		for (int n = x - 1 ; n >= 1 && i < 7 ; n--, i++) {

			/*if blackPieceStart is set and we come acoss an empty tile or another player piece reset blackPieceStart*/
			if ( blackPieceStartX != -1 && blackPieceEndX == -1 && tilesArrayInt[n][i] == playerCor || tilesArrayInt[n][i] == 0) {
				blackPieceStartX = -1;
				blackPieceStartY = -1;

				n = 1;
			}

			/*if blackPieceStart is set and we come acoss an opponent piece and the next tile has a player piece set blackPieceEnd*/
			else if (blackPieceStartX != -1 && blackPieceEndX == -1 && tilesArrayInt[n][i] == opponentCor && tilesArrayInt[n-1][i+1] == playerCor) {
				blackPieceEndX = n-1;
				blackPieceEndY = i+1;

			}

			/*if both blackPieceStart and blackPieceEnd are set we have a capture so now loop through the start location and 
			  and end location and set icons after done loop reset all location values and exit loop*/
			if (blackPieceStartX != -1 && blackPieceEndX != -1) {

				int m = blackPieceStartY;
				for (int j = blackPieceStartX ; j >= blackPieceEndX ; j--) {
					if (player == 1) {
						ViewControllerPub.tilesArray[j][m].setIcon(ViewControllerPub.imageIconBlack);
					} else {
						ViewControllerPub.tilesArray[j][m].setIcon(ViewControllerPub.imageIconWhite);
					}

					m++;
				}

			}
		}
		/*reset all points*/
		blackPieceStartX = -1;
		blackPieceStartY = -1;
		blackPieceEndX = -1;
		blackPieceEndY = -1;

		/*set start piece at location of move*/
		blackPieceStartX = x;
		blackPieceStartY = y;

		/*for loop to start checking left down portion of X pattern*/
		i = y - 1;
		for (int n = x + 1 ; n < 7 && i >= 1 ; n++, i--) {

			/*if blackPieceStart is set and we come acoss an empty tile or another player piece reset blackPieceStart*/
			if ( blackPieceStartX != -1 && blackPieceEndX == -1 && tilesArrayInt[n][i] == playerCor || tilesArrayInt[n][i] == 0) {
				blackPieceStartX = -1;
				blackPieceStartY = -1;

				n = 1;
			}

			/*if blackPieceStart is set and we come acoss an opponent piece and the next tile has a player piece set blackPieceEnd*/
			else if (blackPieceStartX != -1 && blackPieceEndX == -1 && tilesArrayInt[n][i] == opponentCor && tilesArrayInt[n+1][i-1] == playerCor) {
				blackPieceEndX = n+1;
				blackPieceEndY = i-1;

			}

			/*if both blackPieceStart and blackPieceEnd are set we have a capture so now loop through the start location and 
			  and end location and set icons after done loop reset all location values and exit loop*/
			if (blackPieceStartX != -1 && blackPieceEndX != -1) {

				int m = blackPieceStartY;
				for (int j = blackPieceStartX ; j < blackPieceEndX ; j++) {
					if (player == 1) {
						ViewControllerPub.tilesArray[j][m].setIcon(ViewControllerPub.imageIconBlack);
					} else {
						ViewControllerPub.tilesArray[j][m].setIcon(ViewControllerPub.imageIconWhite);
					}

					m--;
				}

			}
		}
		/*reset all points*/
		blackPieceStartX = -1;
		blackPieceStartY = -1;
		blackPieceEndX = -1;
		blackPieceEndY = -1;

	}

	/********************************************************************************
	Function name:		checkValid
	purpose				This is a methode used to see if a valid move can be made on the horizontal or vertical axis 
						and then update tempValidTilesArrayInt where movements are possible
	@param				player (player turn)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void checkValid(int player) {
		int blackPieceStart = -1; /*x or y point where a capture starts*/
		int blackPieceEnd = 0; /*x or y point where a capture ends*/
		int playerCor = 1; /*current player piece*/
		int opponentCor = 2; /*current opponent piece*/

		/*reverse vars if player 2*/
		if (player ==  2) {
			playerCor = 2;
			opponentCor = 1;
		}

		/*for loop to start checking all columns to capture*/
		for (int n = 0; n < 8 ; n++) {
			for (int i = 0; i < 8 ; i++) {

				/*if player piece found and next piece is an opponent piece capture can start*/
				if (i != 7 && tilesArrayInt[n][i] == playerCor && tilesArrayInt[n][i+1] == opponentCor) {
					blackPieceStart = i;
				}

				/*if blackPieceStart set and blackPieceEnd not set and piece at point is a player piece break capture reset blackPieceStart*/
				else if (blackPieceStart != -1 && blackPieceEnd == 0 && tilesArrayInt[n][i] == playerCor) {
					blackPieceStart = -1;
				}

				/*if blackPieceStart set and blackPieceEnd not set and piece at point is a null capture can stop set blackPieceEnd*/
				else if (blackPieceStart != -1 && blackPieceEnd == 0 && tilesArrayInt[n][i] == 0 ) {
					blackPieceEnd = i;
				}

				/*if blackPieceStart and blackPieceEnd set set all points between start and end point to 3*/
				if (blackPieceStart != -1 && blackPieceEnd != 0) {
					tempValidTilesArrayInt[n][blackPieceEnd] = 3;
					blackPieceStart = -1;
					blackPieceEnd = 0;

				}
			}
		}
		/*reset points*/
		blackPieceStart = -1;
		blackPieceEnd = 7;

		/*for loop to start checking all columns to capture*/
		for (int n = 7; n >= 0 ; n--) {
			for (int i = 7; i >= 0 ; i--) {

				/*if player piece found and next piece is an opponent piece capture can start*/
				if (i != 0 && tilesArrayInt[n][i] == playerCor && tilesArrayInt[n][i-1] == opponentCor) {
					blackPieceStart = i;
				}

				/*if blackPieceStart set and blackPieceEnd not set and piece at point is a player piece break capture reset blackPieceStart*/
				else if (blackPieceStart != -1 && blackPieceEnd == 7 && tilesArrayInt[n][i] == playerCor) {
					blackPieceStart = -1;					
				}

				/*if blackPieceStart set and blackPieceEnd not set and piece at point is a null capture can stop set blackPieceEnd*/
				else if (blackPieceStart != -1 && blackPieceEnd == 7 && tilesArrayInt[n][i] == 0 ) {
					blackPieceEnd = i;
				}

				/*if blackPieceStart and blackPieceEnd set set all points between start and end point to 3*/
				if (blackPieceStart != -1 && blackPieceEnd != 7) {
					tempValidTilesArrayInt[n][blackPieceEnd] = 3;
					blackPieceStart = -1;
					blackPieceEnd = 7;

				}
			}
		}
		/*reset points*/
		blackPieceStart = -1;
		blackPieceEnd = 0;

		/*for loop to start checking all rows to capture*/
		for (int n = 0; n < 8 ; n++) {
			for (int i = 0; i < 8 ; i++) {

				/*if player piece found and next piece is an opponent piece capture can start*/
				if (n != 7 && tilesArrayInt[n][i] == playerCor && tilesArrayInt[n+1][i] == opponentCor) {
					blackPieceStart = n;
				}

				/*for loop to start looking at points above start point*/
				for (int j = n+1 ; j < 8 ; j++) {

					/*if blackPieceStart set and blackPieceEnd not set and piece at point is a player piece break capture reset blackPieceStart*/
					if (blackPieceStart != -1 && blackPieceEnd == 0 && tilesArrayInt[j][i] == playerCor) {
						blackPieceStart = -1;
					}

					/*if blackPieceStart set and blackPieceEnd not set and piece at point is a null capture can stop set blackPieceEnd*/
					else if (blackPieceStart != -1 && blackPieceEnd == 0 && tilesArrayInt[j][i] == 0 ) {
						blackPieceEnd = j;
					}
				}

				/*if blackPieceStart and blackPieceEnd set set all points between start and end point to 3*/
				if (blackPieceStart != -1 && blackPieceEnd != 0) {
					tempValidTilesArrayInt[blackPieceEnd][i] = 3;
					blackPieceStart = -1;
					blackPieceEnd = 0;					

				} 
			}
		}
		/*reset points*/
		blackPieceStart = -1;
		blackPieceEnd = 7;

		/*for loop to start checking all rows to capture*/
		for (int n = 7; n >= 0 ; n--) {
			for (int i = 7; i >= 0 ; i--) {

				/*if player piece found and next piece is an opponent piece capture can start*/
				if (n != 0 && i != 0 && tilesArrayInt[n][i] == playerCor && tilesArrayInt[n-1][i] == opponentCor) {
					blackPieceStart = n;
				}

				/*for loop to start looking at points below start point*/
				for (int j = n-1 ; j >= 0 ; j--) {

					/*if blackPieceStart set and blackPieceEnd not set and piece at point is a player piece break capture reset blackPieceStart*/
					if (blackPieceStart != -1 && blackPieceEnd == 7 && tilesArrayInt[j][i] == playerCor) {
						blackPieceStart = -1;
					}

					/*if blackPieceStart set and blackPieceEnd not set and piece at point is a null capture can stop set blackPieceEnd*/
					else if (blackPieceStart != -1 && blackPieceEnd == 7 && tilesArrayInt[j][i] == 0 ) {
						blackPieceEnd = j;
					}
				}

				/*if blackPieceStart and blackPieceEnd set set all points between start and end point to 3*/
				if (blackPieceStart != -1 && blackPieceEnd != 7) {
					tempValidTilesArrayInt[blackPieceEnd][i] = 3;
					blackPieceStart = -1;
					blackPieceEnd = 7;					

				} 

			}
		} 

	}

	/********************************************************************************
	Function name:		checkValidZ
	purpose				This is a methode used to see if a valid move can be made on the diagonal axis 
						and then update tempValidTilesArrayInt where movements are possible
	@param				player (player turn)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void checkValidZ(int player) {
		int blackPieceStart = -1; /*x point where a capture starts*/
		int blackPieceEndX = -1; /*x point where a capture ends*/
		int blackPieceEndY = -1; /*y point where a capture ends*/
		int playerCor = 1; /*current player piece*/
		int opponentCor = 2; /*current opponent piece*/
		int j = 0; /*used for y point to loop diagonal points*/

		/*reverse vars if player 2*/
		if (player ==  2) {
			playerCor = 2;
			opponentCor = 1;
		}

		/*for loop to start checking all top left rows of X patern*/
		for (int n = 0; n < 8 ; n++) {
			for (int i = 0; i < 8 ; i++) {

				/*if piece at point is a player piece and next piece is opponent piece set blackPieceStart*/
				if (n != 0 && i != 0 && tilesArrayInt[n][i] == playerCor && tilesArrayInt[n-1][i-1] == opponentCor) {
					blackPieceStart = i;
					j = i-1;
				}

				/*check to see if blackPieceEndX can be set in top left rows of X patern*/
				for (int m = n-1; m >= 0 ; m--) {

					/*if blackPieceStart is set and piece at point is a player piece capture broken reset blackPieceStart*/
					if ( j > -1 && blackPieceStart != -1 && blackPieceEndX == -1 && tilesArrayInt[m][j] == playerCor) {
						blackPieceStart = -1;
					}

					/*if blackPieceStart is set and piece at point is a player piece capture closed set blackPieceEndX/blackPieceEndY*/
					else if (j > -1 && blackPieceStart != -1 && blackPieceEndX == -1 && tilesArrayInt[m][j] == 0 ) {
						blackPieceEndX = m;
						blackPieceEndY = j;
					}
					j--;
				}

				/*if blackPieceStart and blackPieceEndX is set capture is posible loop through points set tempValidTilesArrayInt to 3 at points
				  reset points*/
				if (blackPieceStart != -1 && blackPieceEndX != -1) {
					tempValidTilesArrayInt[blackPieceEndX][blackPieceEndY] = 3;
					blackPieceStart = -1;
					blackPieceEndX = -1;
					blackPieceEndY = -1;

				}
			}
		}
		/*reset points*/
		blackPieceStart = -1;
		blackPieceEndX = -1;
		blackPieceEndY = -1;

		/*for loop to start checking all bottom right rows of X patern*/
		for (int n = 0; n < 8 ; n++) {
			for (int i = 0; i < 8 ; i++) {

				/*if piece at point is a player piece and next piece is opponent piece set blackPieceStart*/
				if (n != 7 && i != 7 && tilesArrayInt[n][i] == playerCor && tilesArrayInt[n+1][i+1] == opponentCor) {
					blackPieceStart = i;
					j = i+1;
				}

				/*check to see if blackPieceEndX can be set in bottom right rows of X patern*/
				for (int m = n+1; m < 8 ; m++) {
					
					/*if blackPieceStart is set and piece at point is a player piece capture broken reset blackPieceStart*/
					if ( j < 8 && blackPieceStart != -1 && blackPieceEndX == -1 && tilesArrayInt[m][j] == playerCor) {
						blackPieceStart = -1;
					}

					/*if blackPieceStart is set and piece at point is a player piece capture closed set blackPieceEndX/blackPieceEndY*/
					else if (j < 8 && blackPieceStart != -1 && blackPieceEndX == -1 && tilesArrayInt[m][j] == 0 ) {
						blackPieceEndX = m;
						blackPieceEndY = j;
					}
					j++;
				}
				
				/*if blackPieceStart and blackPieceEndX is set capture is posible loop through points set tempValidTilesArrayInt to 3 at points
				  reset points*/
				if (blackPieceStart != -1 && blackPieceEndX != -1) {
					tempValidTilesArrayInt[blackPieceEndX][blackPieceEndY] = 3;
					blackPieceStart = -1;
					blackPieceEndX = -1;
					blackPieceEndY = -1;

				}
			}
		}
		/*reset points*/
		blackPieceStart = -1;
		blackPieceEndX = -1;
		blackPieceEndY = -1;

		/*for loop to start checking all top left rows of X patern*/
		for (int n = 0; n < 8 ; n++) {
			for (int i = 0; i < 8 ; i++) {

				/*if piece at point is a player piece and next piece is opponent piece set blackPieceStart*/
				if (n != 0 && i != 7 && tilesArrayInt[n][i] == playerCor && tilesArrayInt[n-1][i+1] == opponentCor) {
					blackPieceStart = i;
					j = i+1;
				}

				/*check to see if blackPieceEndX can be set in top left rows of X patern*/
				for (int m = n-1; m >= 0 ; m--) {

					/*if blackPieceStart is set and piece at point is a player piece capture broken reset blackPieceStart*/
					if ( j < 8 && blackPieceStart != -1 && blackPieceEndX == -1 && tilesArrayInt[m][j] == playerCor) {
						blackPieceStart = -1;
					}

					/*if blackPieceStart is set and piece at point is a player piece capture closed set blackPieceEndX/blackPieceEndY*/
					else if (j < 8 && blackPieceStart != -1 && blackPieceEndX == -1 && tilesArrayInt[m][j] == 0 ) {
						blackPieceEndX = m;
						blackPieceEndY = j;
					}
					j++;
				}

				/*if blackPieceStart and blackPieceEndX is set capture is posible loop through points set tempValidTilesArrayInt to 3 at points
				  reset points*/
				if (blackPieceStart != -1 && blackPieceEndX != -1) {
					tempValidTilesArrayInt[blackPieceEndX][blackPieceEndY] = 3;
					blackPieceStart = -1;
					blackPieceEndX = -1;
					blackPieceEndY = -1;

				}
			}
		}
		/*reset points*/
		blackPieceStart = -1;
		blackPieceEndX = -1;
		blackPieceEndY = -1;

		/*for loop to start checking all top right rows of X patern*/
		for (int n = 0; n < 8 ; n++) {
			for (int i = 0; i < 8 ; i++) {

				/*if piece at point is a player piece and next piece is opponent piece set blackPieceStart*/
				if (n != 7 && i != 0 && tilesArrayInt[n][i] == playerCor && tilesArrayInt[n+1][i-1] == opponentCor) {
					blackPieceStart = i;
					j = i-1;
				}

				/*check to see if blackPieceEndX can be set in top right rows of X patern*/
				for (int m = n+1; m < 8 ; m++) {

					/*if blackPieceStart is set and piece at point is a player piece capture broken reset blackPieceStart*/
					if ( j > -1 && blackPieceStart != -1 && blackPieceEndX == -1 && tilesArrayInt[m][j] == playerCor) {
						blackPieceStart = -1;
					}

					/*if blackPieceStart is set and piece at point is a player piece capture closed set blackPieceEndX/blackPieceEndY*/
					else if (j > -1 && blackPieceStart != -1 && blackPieceEndX == -1 && tilesArrayInt[m][j] == 0 ) {
						blackPieceEndX = m;
						blackPieceEndY = j;
					}
					j--;
				}

				/*if blackPieceStart and blackPieceEndX is set capture is posible loop through points set tempValidTilesArrayInt to 3 at points
				  reset points*/
				if (blackPieceStart != -1 && blackPieceEndX != -1) {
					tempValidTilesArrayInt[blackPieceEndX][blackPieceEndY] = 3;
					blackPieceStart = -1;
					blackPieceEndX = -1;
					blackPieceEndY = -1;

				}
			}
		}

	}

}