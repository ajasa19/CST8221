package othello;
/********************************************************************************
Filename:			OthelloViewController.java
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
Purpose:			Set up all panels and items relating to othello screen, start up and build/display main orthello screen, hold controller class to listin to user imputs
					hold panels: [Bottom] [Center -> left panel & board panel] [Center -> right panel & Pieces panel -> text panel & counter panel] 
 ********************************************************************************/
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;


public class OthelloViewController extends JFrame implements ActionListener {

	JPanel board = new JPanel();
	String image1 = "black_s.png"; /*player 1 name of img file to be loaded as ImgaeIcon*/
	String image2 = "white_s.png"; /*player 2 name of img file to be loaded as ImgaeIcon*/
	ImageIcon imageIconBlack = new ImageIcon(image1); /*black piece is loaded as ImageIcon and then made Jlabel*/
	JLabel imageLabelBlack = new JLabel(imageIconBlack); /*black piece can now be called*/
	ImageIcon imageIconWhite = new ImageIcon(image2); /*white piece is loaded as ImageIcon and then made Jlabel*/
	JLabel imageLabelWhite = new JLabel(imageIconWhite); /*white piece can now be called*/
	ImageIcon imageIconCheck = new ImageIcon("checkmark.png"); /*check mark is loaded as ImageIcon and then made Jlabel*/
	JLabel imageLabelCheck = new JLabel(imageIconCheck); /*check mark can now be called*/
	JTextArea pinkLabel = new JTextArea("Player 1 initialized with 2 pieces.\n"); /*pink label to show player acctions, capture, etc*/
	JPanel textPanel = new JPanel(); /*used to resize textpanel when pieces icon switched*/
	JPanel counterPanel = new JPanel(); /*used to update pieces counter*/
	controller controller = new controller(); /*listiner class controller deals with all button inputs*/
	JButton buttonArray[] = new JButton[19]; /*array to hold all axis board buttons*/
	JLabel tilesArray[][] = new JLabel[8][8]; /*2d array made to hold tiles, also initialized them in for loop to prevent null pointer error*/
	JCheckBox chckbxNewCheckBox = new JCheckBox("Show Valid Moves"); /*checkbox to see valid move*/

	int Xinput; /*players x input*/
	int Yinput; /*players y input*/
	int playerTurn = 1; /*decide who's turn it is (1 or 2)*/
	int blackScorePre = 2; /*player 1 score before caputres*/
	int whiteScorePre = 2; /*player 2 score before caputres*/
	int blackScore = 2; /*player 1 score after caputres*/
	int whiteScore = 2; /*player 2 score after caputres*/
	int xButtonCheck = 8; /*used to determin which x axis button should turn white*/
	int yButtonCheck = 8; /*used to determin which y axis button should turn white*/
	int mode = 0; /*what game setup are we in (default, corner test, etc)*/
	int seeValid = 0; /*check if valid moves are being displayed to user*/
	int noMoves = 0; /*check if valid move is possible*/
	
	String Adress = "";
	int Port = 0;
	

	JLabel piecesCounterP1 = new JLabel(String.valueOf(blackScore));
	JLabel piecesCounterP2 = new JLabel(String.valueOf(whiteScore));

	private static final long serialVersionUID = 6248477390124803341L;
	private JFrame frame; /*The main frame of program, all panels are held in here*/

	/********************************************************************************
	Function name:		main
	Purpose				set up window and frame 
	@param				String[] args	
	@version			1.0
	@author				Asim Jasarevic
	 ********************************************************************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			/********************************************************************************
			Function name:			run
			Purpose					make new window for program and set the frame to be visable	
			@version				1.0
			@author					Asim Jasarevic
			 ********************************************************************************/
			public void run() {

				OthelloViewController window = new OthelloViewController(); /*new object to call constructor*/
				window.frame.setVisible(true);

			}
		});
	}

	/********************************************************************************
	Function name:		OthelloViewController
	Purpose				start in main and call initialize function to start the building of frame	
	@version			1.0
	@author				Asim Jasarevic
	 ********************************************************************************/
	public OthelloViewController() {
		this.board = new JPanel();
		this.tilesArray = new JLabel[8][8]; /*2d array made to hold tiles, also initialized them in for loop to prevent null pointer error*/
		this.Xinput = -1; /*players x input*/
		this.Yinput = -1; /*players y input*/
		this.blackScore = 2; /*player 1 score*/
		this.whiteScore = 2; /*player 2 score*/
		this.mode = 0; /*what game setup are we in (default, corner test, etc)*/
		this.controller = new controller(); /*listiner class controller deals with all button inputs*/
		initialize(); /*call initialize function and add content to frame*/
	}

	/********************************************************************************
	Function name:		initialize
	Purpose				Set up all details about items and load them into frame.
						Most of the heavy lifting is done here all panels and items in panels are made, defined, and added here.	
	@version			1.0
	@author				Asim Jasarevic
	 ********************************************************************************/
	private void initialize() {
		/*main frame to hold all items*/
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBackground(Color.GRAY);
		frame.setBounds(100, 100, 1010, 645);
		frame.setResizable(false);
		frame.setTitle("Asim's Othello Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		/*Bottom panel*/
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.GRAY);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		///////////////////////////////////////////////////////////////////
		JTextArea textArea = new JTextArea( 1, 75); /*textArea made for bottom of screen*/
		bottomPanel.add(textArea);

		JButton btnSubmit = createButton("Submit", "S", Color.RED, Color.BLACK, "", buttonArray, 18); /*JButton made for bottom of screen*/
		btnSubmit.addActionListener(controller);
		bottomPanel.add(btnSubmit);
		///////////////////////////////////////////////////////////////////






		/*[Center panel]*/
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.GRAY);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		///////////////////////////////////////////////////////////////////

		/*Center panel -> [Left panel]*/
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.GRAY);
		centerPanel.add(leftPanel);
		leftPanel.setPreferredSize( new Dimension(550, 550) );
		//*****************************************************************


		/*Center panel -> Left panel -> [Board panel]*/

		board.setBackground(Color.GRAY);
		leftPanel.add(board);
		board.setLayout(new GridLayout(9,9));
		board.setPreferredSize( new Dimension(540, 540) );
		board.setBounds(0, 0, 540, 540);
		//*******************************************

		String[] charArray = new String[9]; /*array to hold strings [A - H] and [Move] for button names*/
		charArray[0] = "A";
		charArray[1] = "B";
		charArray[2] = "C";
		charArray[3] = "D";
		charArray[4] = "E";
		charArray[5] = "F";
		charArray[6] = "G";
		charArray[7] = "H";
		charArray[8] = "move";


		for (int i = 0 ; i < 8 ; i++){
			for (int j = 0; j < 8; j++) {
				tilesArray[i][j]=new JLabel("", JLabel.CENTER);
			} }

		boolean rowNum = true;

		/********************************************************************************
		 For loop explination.

		 first check: if we are at the bottom row if yes then run a new for loop to set up bottom buttons
		 first else: we are not at bottom row so now lets ethier make tile or right column button
		 second check: if we are at the right most column then make a new button based off i
		 second else: we are not at bottom row or right most column so lets make a tile bassed i and j

		 main loop runs 9[i] x 9[j] times total: 81
		 ********************************************************************************/
		for (int i = 0 ; i < 9 ; i++){

			/*first check*/
			if (i == 8) {		
				for (int x = 0 ; x < 9 ; x++) {				
					if(x == 8) {
						JButton button = createButton(charArray[x], "M", Color.BLACK, Color.WHITE, "", buttonArray, x); /*make A-H new button get text value from charArray*/
						Font font = new Font("Default", Font.PLAIN, 10);
						button.setFont(font);
						button.addActionListener(controller);
						board.add(button);
						button.setBorder(new LineBorder(Color.GRAY, 1));
					} else {
						JButton button = createButton(charArray[x], charArray[x], Color.BLACK, Color.LIGHT_GRAY, "", buttonArray, x); /*make Move new button get text value from charArray*/
						button.addActionListener(controller);
						board.add(button);
						button.setBorder(new LineBorder(Color.GRAY, 1));
					}
				}
			}

			/*first else*/
			else {
				for (int j = 0; j < 9; j++) {
					/*second check*/
					if (j == 8) {
						JButton button = createButton(String.valueOf(i+1), String.valueOf(i+1), Color.BLACK, Color.LIGHT_GRAY, "", buttonArray, (i+9)); /*make 1-8 new button get text value from charArray*/
						button.addActionListener(controller);
						board.add(button);
						button.setBorder(new LineBorder(Color.GRAY, 1));
					} 
					/*second else*/
					else {
						board.add( tilesArray[i][j] ); /*make new tile from tilesArray*/
						tilesArray[i][j].setOpaque(true);
					}
				}
			}

		}


		/********************************************************************************
		 For loop explination agian.

		 This loop is for seting the colors of tiles (B or W) and does it using boolean rowNum
		 rowNum is used to alternate between rows so if first box at row1 is black first box at row2 is white and so on for 8 rows
		 rowNum also switches each time a color is made so it goes (B W B W B W B W ...) unless we are at last box in a row.

		 main loop runs 8[i] x 8[j] times total: 64
		 ********************************************************************************/
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (rowNum == false) {
					tilesArray[i][j].setBackground(Color.white);
					rowNum = true;
				}
				else if (rowNum == true) {
					tilesArray[i][j].setBackground(Color.black);
					rowNum = false;
				}

				if (j == 7) {
					if(rowNum == true)
						rowNum = false;
					else
						rowNum = true;
				}
			}
		} 

		tilesArray[3][4].setIcon(imageIconBlack); /*place pieces on tile*/
		tilesArray[4][3].setIcon(imageIconBlack);
		tilesArray[3][3].setIcon(imageIconWhite);
		tilesArray[4][4].setIcon(imageIconWhite);

		//*******************************************
		/*End of Center panel -> Left panel -> [Board panel]*/

		//*****************************************************************
		/*End of Center panel -> [Left panel]*/



		/*Center panel -> [Right panel]*/
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.GRAY);
		centerPanel.add(rightPanel);
		rightPanel.setPreferredSize( new Dimension(430, 550) );
		//*****************************************************************
		chckbxNewCheckBox.setActionCommand("V");
		chckbxNewCheckBox.addActionListener(controller);
		chckbxNewCheckBox.setBackground(Color.WHITE);
		rightPanel.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setPreferredSize( new Dimension(430, 25) );


		/*Center panel -> Right panel -> [Pink label]*/
		pinkLabel.append("Player 2 initialized with " + blackScore + " pieces.\n");
		rightPanel.add(pinkLabel);

		pinkLabel.setOpaque(true);
		pinkLabel.setBackground(Color.PINK);
		
		JScrollPane sp = new JScrollPane(pinkLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		rightPanel.add(sp);
		sp.setPreferredSize( new Dimension(430, 380) );
		//*******************************************

		//*******************************************
		/*End of Center panel -> Right panel -> [Pink panel]*/





		/*Center panel -> Right panel -> [Pieces panel]*/
		JPanel piecesPanel = new JPanel();
		piecesPanel.setBackground(Color.WHITE);
		rightPanel.add(piecesPanel);
		piecesPanel.setPreferredSize( new Dimension(440, 190) );
		piecesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//*******************************************

		/*Center panel -> Right panel -> Pieces panel -> [Text panel]*/
		piecesPanel.add(textPanel, BorderLayout.WEST);
		textPanel.setBackground(Color.WHITE);
		textPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
		textPanel.setPreferredSize( new Dimension(360, 190) );

		//********************************
		JLabel piecesLabelP1 = new JLabel("Player 1 pieces:");
		JLabel piecesLabelP2 = new JLabel("Player 2 pieces:");

		piecesLabelP1.setPreferredSize( new Dimension(200, 40) );
		piecesLabelP2.setPreferredSize( new Dimension(200, 30) );

		textPanel.add(piecesLabelP1);
		textPanel.add(piecesLabelP2);
		//********************************
		/*End of Center panel -> Right panel -> Pieces panel -> [Text panel]*/


		/*Center panel -> Right panel -> Pieces panel -> [Counter panel]*/
		piecesPanel.add(counterPanel, BorderLayout.EAST);
		counterPanel.setBackground(Color.WHITE);
		counterPanel.setPreferredSize( new Dimension(60, 190) );
		counterPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 1, 1));
		//********************************


		counterPanel.add(imageLabelBlack);
		counterPanel.add(piecesCounterP1);
		counterPanel.add(imageLabelWhite);
		counterPanel.add(piecesCounterP2);
		//********************************
		/*End of Center panel -> Right panel -> Pieces panel -> [Counter panel]*/

		//*******************************************
		/*End of Center panel -> Right panel -> [Pieces panel]*/


		//*****************************************************************
		/*End of Center panel -> [Right panel]*/


		///////////////////////////////////////////////////////////////////
		/*[End of Center panel]*/

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mnFile.add(mntmNewGame);
		mntmNewGame.addActionListener(controller);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		mntmLoad.setEnabled(false);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		mntmSave.setEnabled(false);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(controller);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenu mnReskinPieces = new JMenu("Reskin Pieces");
		mnGame.add(mnReskinPieces);


		JRadioButtonMenuItem rdbtnmntmNormalPiecesblack = new JRadioButtonMenuItem("Normal Pieces (black and white)");
		mnReskinPieces.add(rdbtnmntmNormalPiecesblack);
		rdbtnmntmNormalPiecesblack.addActionListener(controller);

		JRadioButtonMenuItem rdbtnmntmCatsVsDogs = new JRadioButtonMenuItem("Cats vs. Dogs");
		mnReskinPieces.add(rdbtnmntmCatsVsDogs);
		rdbtnmntmCatsVsDogs.addActionListener(controller);

		JRadioButtonMenuItem rdbtnmntmPumpkisVsBats = new JRadioButtonMenuItem("Pumpkis vs. Bats");
		mnReskinPieces.add(rdbtnmntmPumpkisVsBats);
		rdbtnmntmPumpkisVsBats.addActionListener(controller);

		ButtonGroup groupPiecePic = new ButtonGroup();
		groupPiecePic.add(rdbtnmntmNormalPiecesblack);
		groupPiecePic.add(rdbtnmntmCatsVsDogs);
		groupPiecePic.add(rdbtnmntmPumpkisVsBats);

		JMenu mnDebugScenarios = new JMenu("Debug Scenarios");
		mnGame.add(mnDebugScenarios);

		JRadioButtonMenuItem rdbtnmntmNormalGame = new JRadioButtonMenuItem("Normal Game");
		mnDebugScenarios.add(rdbtnmntmNormalGame);
		rdbtnmntmNormalGame.addActionListener(controller);

		JRadioButtonMenuItem rdbtnmntmCornerTest = new JRadioButtonMenuItem("Corner Test");
		mnDebugScenarios.add(rdbtnmntmCornerTest);
		rdbtnmntmCornerTest.addActionListener(controller);

		JRadioButtonMenuItem rdbtnmntmSideTest = new JRadioButtonMenuItem("Side Tests");
		mnDebugScenarios.add(rdbtnmntmSideTest);
		rdbtnmntmSideTest.addActionListener(controller);

		JRadioButtonMenuItem rdbtnmntm1xCaptureTest = new JRadioButtonMenuItem("1x Capture Test");
		mnDebugScenarios.add(rdbtnmntm1xCaptureTest);
		rdbtnmntm1xCaptureTest.addActionListener(controller);

		JRadioButtonMenuItem rdbtnmntm2xCaptureTest = new JRadioButtonMenuItem("2x Capture Test");
		mnDebugScenarios.add(rdbtnmntm2xCaptureTest);
		rdbtnmntm2xCaptureTest.addActionListener(controller);

		JRadioButtonMenuItem rdbtnmntmEmptyBoard = new JRadioButtonMenuItem("Empty Board");
		mnDebugScenarios.add(rdbtnmntmEmptyBoard);
		rdbtnmntmEmptyBoard.addActionListener(controller);

		JRadioButtonMenuItem rdbtnmntmInnerSquareTest = new JRadioButtonMenuItem("Inner Square Test");
		mnDebugScenarios.add(rdbtnmntmInnerSquareTest);
		rdbtnmntmInnerSquareTest.addActionListener(controller);

		ButtonGroup groupDebug = new ButtonGroup();
		groupDebug.add(rdbtnmntmNormalGame);
		groupDebug.add(rdbtnmntmCornerTest);
		groupDebug.add(rdbtnmntmSideTest);
		groupDebug.add(rdbtnmntm1xCaptureTest);
		groupDebug.add(rdbtnmntm2xCaptureTest);
		groupDebug.add(rdbtnmntmEmptyBoard);
		groupDebug.add(rdbtnmntmInnerSquareTest);
		
		JMenu mnNetwork = new JMenu("Network");
		menuBar.add(mnNetwork);
		
		JMenuItem mntmNewConnection = new JMenuItem("New Connection");
		mnNetwork.add(mntmNewConnection);
		mntmNewConnection.addActionListener(controller);
		
		JMenuItem mntmDisconnect = new JMenuItem("Disconnect ");
		mnNetwork.add(mntmDisconnect);
		mntmDisconnect.addActionListener(controller);


		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(controller);



	}  // end of initialize

	/********************************************************************************
	Function name:		createButton
	Purpose				This function creates all buttons in program, button detail are set depending on whats been pased by call.
						Each made button should have a unique action command
	@param				text (set button text), ac (set action command), fgc (set foreground color), bgc (set background color), handler
	@return				return button
	@version			1.0
	@author				Asim Jasarevic
	 ********************************************************************************/
	public static JButton createButton(String text, String ac, Color fgc, Color bgc, String handler, JButton buttonArray[], int index) {
		buttonArray[index] = new JButton(text);

		buttonArray[index].setText(text);
		buttonArray[index].setActionCommand(ac);
		buttonArray[index].setForeground(fgc);
		buttonArray[index].setBackground(bgc);

		return buttonArray[index];
	} // end of createButton

	/********************************************************************************
	Function name:		getArray
	purpose				This is a function used for OthelloModel so it can see the board array to see where pieces are placed	
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public JLabel[][] getArray(){
		return this.tilesArray;
	}

	/********************************************************************************
	Function name:		getPlayerTurn
	purpose				This is a function used for OthelloModel so it can see which turn it is	
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public int getPlayerTurn(){
		return this.playerTurn;
	}

	/********************************************************************************
	Function name:		ybuttonColorSwitch
	purpose				This is a function used so when controller gets a y axis button pressed it passes a specific button to be 
						turned white or grey and set which y axis the user wants to mave to.
	@param				button(tells which button was pressed)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void ybuttonColorSwitch(int button) {

		if (yButtonCheck != button) {
			buttonArray[button].setBackground(Color.WHITE);
			if (yButtonCheck != 8) {
				buttonArray[yButtonCheck].setBackground(Color.LIGHT_GRAY);
			}
			yButtonCheck = button;
		}
		
		if (button == 8) {
			for (int i = 0; i < 8; i++) {
			buttonArray[i].setBackground(Color.LIGHT_GRAY);
			}
		}
	}

	/********************************************************************************
	Function name:		xbuttonColorSwitch
	purpose				This is a function used so when controller gets a x axis button pressed it passes a specific button to be 
						turned white or grey and set which x axis the user wants to mave to.
	@param				button (tells which button was pressed)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void xbuttonColorSwitch(int button) {
		if (xButtonCheck != button) {
			buttonArray[button].setBackground(Color.WHITE);
			if (xButtonCheck != 8) {
				buttonArray[xButtonCheck].setBackground(Color.LIGHT_GRAY);
			}
			xButtonCheck = button;
		}
		
		if (button == 8) {
			for (int i = 0; i < 8; i++) {
			buttonArray[i].setBackground(Color.LIGHT_GRAY);
			}
		}
	}

	/********************************************************************************
	Class name:			controller
	purpose				This is a private class used to take user imputs and decide what to do/call when user button imput occers
						this works in tandem with model to change the UI
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public class controller implements ActionListener  {

		/********************************************************************************
		Function name:		actionPerformed
		purpose				This is an action preformed handler which calls methods and sets global vars depending on what 
							events are called
		@param				ActionEvent arg0
		@version			1.0
		@author 			Asim Jasarevic
		 ********************************************************************************/
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			OthelloModel OthelloModel = new OthelloModel(); /*new obj used to call methods in model class*/
			OthelloNetworkModalViewController networkDialog = new OthelloNetworkModalViewController(this);
			String action = arg0.getActionCommand(); /*string used to see what event called*/

			/*if event about called display about pane with info*/
			if (action == "About") {
				JOptionPane.showMessageDialog(frame, "<html>Othello Game <br>by Asim Jasarevic<br>October 2020 </html>");
			}

			/*if event Cats vs. Dogs called change all global ImageIcons to cat or dog img*/
			else if (action == "Cats vs. Dogs") {
				image1 = "cat.png";
				imageIconBlack = new ImageIcon(image1);
				imageLabelBlack.setIcon(imageIconBlack);
				image2 = "dog.png";
				imageIconWhite = new ImageIcon(image2);
				imageLabelWhite.setIcon(imageIconWhite);
				counterPanel.setPreferredSize( new Dimension(90, 190) );
				textPanel.setPreferredSize( new Dimension(330, 190) );

				for (int i = 0; i < 8 ; i++) {
					for (int n = 0; n < 8 ; n++) {
						if (String.valueOf(tilesArray[i][n]).contains("white_s") || String.valueOf(tilesArray[i][n]).contains("pumpkin") ) {
							tilesArray[i][n].setIcon(imageIconWhite);
						}
						else if (String.valueOf(tilesArray[i][n]).contains("black_s") || String.valueOf(tilesArray[i][n]).contains("bat") ) {
							tilesArray[i][n].setIcon(imageIconBlack);
						}
					}
				}
			}

			/*if event Pumpkis vs. Bats called change all global ImageIcons to pumpkin or bat img*/
			else if (action == "Pumpkis vs. Bats") {
				image1 = "pumpkin.png";
				imageIconBlack = new ImageIcon(image1);
				imageLabelBlack.setIcon(imageIconBlack);
				image2 = "bat.png";
				imageIconWhite = new ImageIcon(image2);
				imageLabelWhite.setIcon(imageIconWhite);
				counterPanel.setPreferredSize( new Dimension(90, 190) );
				textPanel.setPreferredSize( new Dimension(330, 190) );

				for (int i = 0; i < 8 ; i++) {
					for (int n = 0; n < 8 ; n++) {
						if (String.valueOf(tilesArray[i][n]).contains("white_s") || String.valueOf(tilesArray[i][n]).contains("cat") ) {
							tilesArray[i][n].setIcon(imageIconBlack);
						}
						else if (String.valueOf(tilesArray[i][n]).contains("black_s") || String.valueOf(tilesArray[i][n]).contains("dog") ) {
							tilesArray[i][n].setIcon(imageIconWhite);
						}
					}
				}
			}

			/*if event Normal Pieces (black and white) called change all global ImageIcons to black or white img*/
			else if (action == "Normal Pieces (black and white)") {
				image1 = "black_s.png";
				imageIconBlack = new ImageIcon(image1);
				imageLabelBlack.setIcon(imageIconBlack);
				image2 = "white_s.png";
				imageIconWhite = new ImageIcon(image2);
				imageLabelWhite.setIcon(imageIconWhite);
				counterPanel.setPreferredSize( new Dimension(60, 190) );
				textPanel.setPreferredSize( new Dimension(360, 190) );

				for (int i = 0; i < 8 ; i++) {
					for (int n = 0; n < 8 ; n++) {
						if (String.valueOf(tilesArray[i][n]).contains("bat") || String.valueOf(tilesArray[i][n]).contains("dog") ) {
							tilesArray[i][n].setIcon(imageIconWhite);
						}
						else if (String.valueOf(tilesArray[i][n]).contains("pumpkin") || String.valueOf(tilesArray[i][n]).contains("cat") ) {
							tilesArray[i][n].setIcon(imageIconBlack);
						}
					}
				}
			}

			/*if event Normal Game called set mode to 0 and wait for new game to be called*/
			else if (action == "Normal Game") {
				mode = 0;
			}
			
			/*if event Corner Test called set mode to 1 and wait for new game to be called*/
			else if (action == "Corner Test") {
				mode = 1;
			}

			/*if event Side Test called set mode to 2 and wait for new game to be called*/
			else if (action == "Side Tests") {
				mode = 2;
			}

			/*if event 1x Capture Test called set mode to 3 and wait for new game to be called*/
			else if (action == "1x Capture Test") {
				mode = 3;
			}

			/*if event 2x Capture Test called set mode to 5 and wait for new game to be called*/
			else if (action == "2x Capture Test") {
				mode = 4;
			}

			/*if event Empty Board called set mode to 5 and wait for new game to be called*/
			else if (action == "Empty Board") {
				mode = 5;
			}

			/*if event Inner Square Test called set mode to 6 and wait for new game to be called*/
			else if (action == "Inner Square Test") {
				mode = 6;
			}

			/*this is a big if statment. It main job is to see if a move is possible and then call methods and set vars so 
			 a valid move is processed */
			else if (action == "M") {	

				blackScorePre = blackScore;
				whiteScorePre = whiteScore;

				/*if a valid x,y coordinate and moves are possible start proces*/
				if (Xinput != -1 && Yinput != -1 && noMoves == 0) {
					OthelloModel.loadObject(OthelloViewController.this);

					OthelloModel.getBoard(); /*let model class load a board model*/
					/*see if method is valid return true*/
					if (OthelloModel.isValid(Xinput, Yinput, playerTurn)) {
						/*run the move and apply move logic (piece img logic also takes place)*/
						OthelloModel.move(Xinput, Yinput, playerTurn);

						/*if player = 1/2 change player turn and show how many pieces where captured in pinkLabel*/
						if (playerTurn == 1) {
							pinkLabel.append("Player 1 has captured " + ((blackScore - blackScorePre)-1) + " piece.\n");
							playerTurn = 2;
						} else {
							pinkLabel.append("Player 2 has captured " + ((whiteScore - whiteScorePre)-1)  + " piece.\n");
							playerTurn = 1;
						}
					}

					/*call getChips and change piece counters*/
					piecesCounterP1.setText(String.valueOf(OthelloModel.getChips(1)));
					piecesCounterP2.setText(String.valueOf(OthelloModel.getChips(2)));
					OthelloModel.loadObject(OthelloViewController.this);

					/*if no moves available at all by both players end the game and display some end text*/
					if (OthelloModel.canMove(1) == false && OthelloModel.canMove(2) == false) {
						pinkLabel.append("END OF GAME\n"); 
						pinkLabel.append("Player 1 finishes with " + blackScore + " pieces. \n"); 
						pinkLabel.append("Player 2 finishes with " + whiteScore + " pieces. \n"); 
						/*see who has more points and declare who won or draw*/
						if (blackScore > whiteScore) {
							pinkLabel.append("Player 1 won!\n"); 
						} else if (blackScore < whiteScore) {
							pinkLabel.append("Player 2 won!\n"); 
						} else {
							pinkLabel.append("It's a draw.\n"); 
						}
						noMoves = 1;
					}
					/*if only 1 player can't move change move button to skip and output some text telling player to skip*/
					else if (OthelloModel.canMove(playerTurn) == false) {
						if (playerTurn == 1) {
							pinkLabel.append("Player 1 has no valid moves. Press skip.\n"); 
						} else {
							pinkLabel.append("Player 2 has no valid moves. Press skip.\n"); 
						}
						buttonArray[8].setText("skip");
						buttonArray[8].setActionCommand("Skip");
						noMoves = 1;
					}

					Xinput = -1;
					Yinput = -1;
					xbuttonColorSwitch(8);
					ybuttonColorSwitch(8);
		
					/*update frame to see changes*/
					SwingUtilities.updateComponentTreeUI(frame);
				}
			}
			
			/*if Skip event then check if moves valid*/
			else if (action == "Skip") {
				/*give model a new object*/
				OthelloModel.loadObject(OthelloViewController.this);
				
				/*if player can't move switch player turn and show player has skiped*/
				if (OthelloModel.canMove(playerTurn) == false) {
					if (playerTurn == 1) {
						pinkLabel.append("Player 1 has skiped.\n"); 
						playerTurn = 2;
					} else {
						pinkLabel.append("Player 2 has skiped.\n"); 
						playerTurn = 1;
					}
					/*set skip button back to move and allow move to run*/
					buttonArray[8].setText("move");
					buttonArray[8].setActionCommand("M");
					noMoves = 0;
				}
				
				Xinput = -1;
				Yinput = -1;
				xbuttonColorSwitch(8);
				ybuttonColorSwitch(8);
			}

			/*if button 1 set x input, run xbuttonColorSwitch button to update colour and then update frame*/
			else if (action.contains("1")) {	
				Xinput = 1;
				xbuttonColorSwitch(9);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button 2 set x input, run xbuttonColorSwitch button to update colour and then update frame*/
			else if (action.contains("2")) {	
				Xinput = 2;
				xbuttonColorSwitch(10);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button 3 set x input, run xbuttonColorSwitch button to update colour and then update frame*/
			else if (action.contains("3")) {	
				Xinput = 3;
				xbuttonColorSwitch(11);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button 4 set x input, run xbuttonColorSwitch button to update colour and then update frame*/
			else if (action.contains("4")) {	
				Xinput = 4;
				xbuttonColorSwitch(12);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button 5 set x input, run xbuttonColorSwitch button to update colour and then update frame*/
			else if (action.contains("5")) {	
				Xinput = 5;			
				xbuttonColorSwitch(13);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button 6 set x input, run xbuttonColorSwitch button to update colour and then update frame*/
			else if (action.contains("6")) {	
				Xinput = 6;		
				xbuttonColorSwitch(14);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button 7 set x input, run xbuttonColorSwitch button to update colour and then update frame*/
			else if (action.contains("7")) {	
				Xinput = 7;		
				xbuttonColorSwitch(15);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button 8 set x input, run xbuttonColorSwitch button to update colour and then update frame*/
			else if (action.contains("8")) {	
				Xinput = 8;
				xbuttonColorSwitch(16);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button A set y input, run ybuttonColorSwitch button to update colour and then update frame*/
			else if (action == "A") {	
				Yinput = 1;
				ybuttonColorSwitch(0);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button B set y input, run ybuttonColorSwitch button to update colour and then update frame*/
			else if (action == "B") {	
				Yinput = 2;
				ybuttonColorSwitch(1);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button C set y input, run ybuttonColorSwitch button to update colour and then update frame*/
			else if (action == "C") {	
				Yinput = 3;
				ybuttonColorSwitch(2);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button D set y input, run ybuttonColorSwitch button to update colour and then update frame*/
			else if (action == "D") {	
				Yinput = 4;
				ybuttonColorSwitch(3);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button E set y input, run ybuttonColorSwitch button to update colour and then update frame*/
			else if (action == "E") {	
				Yinput = 5;
				ybuttonColorSwitch(4);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button F set y input, run ybuttonColorSwitch button to update colour and then update frame*/
			else if (action == "F") {	
				Yinput = 6;
				ybuttonColorSwitch(5);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button G set y input, run ybuttonColorSwitch button to update colour and then update frame*/
			else if (action == "G") {	
				Yinput = 7;
				ybuttonColorSwitch(6);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if button H set y input, run ybuttonColorSwitch button to update colour and then update frame*/
			else if (action == "H") {	
				Yinput = 8;
				ybuttonColorSwitch(7);
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if New Game then start proces to set up a new game*/
			else if (action == "New Game") {
				/*give model a new object*/
				OthelloModel.loadObject(OthelloViewController.this);
				/*set the model array to a mode defined board*/
				OthelloModel.initialize(mode);
				/*set tiles to a pic depepnding on model array*/
				OthelloModel.setTilesArray(OthelloViewController.this);
				/*get a new model board to intake all moved pieces*/
				OthelloModel.getBoard();

				/*reset pink label to no text*/
				pinkLabel.setText(null);

				/*reset pre scores to new default*/
				blackScorePre = blackScore;
				whiteScorePre = whiteScore;

				/*reset piece counters to new default*/
				piecesCounterP1.setText(String.valueOf(OthelloModel.getChips(1)));
				piecesCounterP2.setText(String.valueOf(OthelloModel.getChips(2)));

				/*put initial text in pinkLabel*/
				pinkLabel.append("Player 1 initialized with " + blackScore + " pieces.\n");
				pinkLabel.append("Player 2 initialized with " + whiteScore + " pieces.\n");

				/*set player turn to 1*/
				playerTurn = 1;
				/*if button was skip this var makes it set back to move*/
				noMoves = 0;
				/*give model a new object*/
				OthelloModel.loadObject(OthelloViewController.this);

				/*check to see if any valid moves by both players possible if not do the end game steps*/
				if (OthelloModel.canMove(1) == false && OthelloModel.canMove(2) == false) {
					pinkLabel.append("END OF GAME\n"); 
					pinkLabel.append("Player 1 finishes with" + blackScore + " pieces. \n"); 
					pinkLabel.append("Player 2 finishes with " + whiteScore + " pieces. \n"); 
					if (blackScore > whiteScore) {
						pinkLabel.append("Player 1 won!\n"); 
					} else if (blackScore < whiteScore) {
						pinkLabel.append("Player 2 won!\n"); 
					} else {
						pinkLabel.append("It's a draw.\n"); 
					}
					noMoves = 1;
				}
				/*check to see if any valid moves by only one player possible if not set button to skip and tell player to skip*/
				else if (OthelloModel.canMove(playerTurn) == false) {
					if (playerTurn == 1) {
						pinkLabel.append("Player 1 has no valid moves. Press skip\n"); 
					} else {
						pinkLabel.append("Player2 has no valid moves. Press skip\n"); 
					}
					buttonArray[8].setText("skip");
					buttonArray[8].setActionCommand("Skip");
					noMoves = 1;
				}
				/*check to see if any valid moves possible if yes then set button to move*/
				else {
					buttonArray[8].setText("move");
					buttonArray[8].setActionCommand("M");
					noMoves = 0;
				}

				Xinput = -1;
				Yinput = -1;
				xbuttonColorSwitch(8);
				ybuttonColorSwitch(8);
				
				/*update frame see changes*/
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if V called ethier show valid moves or hide them*/
			else if (action == "V") {
				/*if can't see valid moves show them on board*/
				if (seeValid == 0) {
					OthelloModel.loadObject(OthelloViewController.this);
					OthelloModel.seeValidMoves();
					seeValid = 1;
				} 
				/*if can see valid moves hide them on board*/
				else {
					OthelloModel.loadObject(OthelloViewController.this);
					OthelloModel.doNotSeeValidMoves();
					seeValid = 0;
				}


				SwingUtilities.updateComponentTreeUI(frame);
			}
			
			/*if New Connection called launch dialog and then print output values*/
			else if (action == "New Connection") {

				/*calling OthelloNetworkModalViewController method will display dialog*/
				networkDialog.OthelloNetworkModalViewController(frame);
				
				/*if networkDialog = "Conn" connect button presed display connect text else display cancel text*/
				if (networkDialog.pressedChar == "Conn") {
					pinkLabel.append("Connection Pressed\n"); 
				} else {
					pinkLabel.append("Cancel Pressed\n"); 
				}
				
				/*display connection values address and port*/
				pinkLabel.append("Connecting to " + networkDialog.getAddress() +"\n"); 
				pinkLabel.append("On Port " + networkDialog.getPort() +"\n");
			}
			
			/*if Disconnect called just display text (no real functions yet)*/
			else if (action == "Disconnect ") {
				
				pinkLabel.append("Disconnecting from network connection.\n"); 
				SwingUtilities.updateComponentTreeUI(frame);
			}

			/*if Exit called exit program*/
			else if (action == "Exit") {	
				frame.dispose();
			}

		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

} // end of OthelloViewController
