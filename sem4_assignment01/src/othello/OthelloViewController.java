package othello;
/********************************************************************************
Filename:			OthelloViewController.java
Version:			1.0
Author:				Asim Jasarevic
StudentNo:			040922815
Course Name/Number:	Java Application 20F_CST8221–300
Lab Sect:			303
Professor:			Daniel Cormier
Assignment #:		1
Assignment name:	Othello Part 1
Due Date:			2020, Oct, 16
Submission Date:	2020, Oct, 08
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
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.Font;


public class OthelloViewController extends JFrame implements ActionListener {

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

		/*black piece is loaded as ImageIcon and then made Jlabel*/
		ImageIcon imageIconBlack = new ImageIcon("black_s.png");
		JLabel imageLabelBlack = new JLabel(imageIconBlack);
		
		/*white piece is loaded as ImageIcon and then made Jlabel*/
		ImageIcon imageIconWhite = new ImageIcon("white_s.png");
		JLabel imageLabelWhite = new JLabel( imageIconWhite);

		/*main frame to hold all items*/
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBackground(Color.GRAY);
		frame.setBounds(100, 100, 1010, 620);
		frame.setResizable(false);
		frame.setTitle("Asim's Othello Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		

		/*Bottom panel*/
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.GRAY);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		///////////////////////////////////////////////////////////////////
		JTextArea textArea = new JTextArea( 1, 90); /*textArea made for bottom of screen*/
		bottomPanel.add(textArea);

		JButton btnSubmit = createButton("Submit", "S", Color.RED, Color.BLACK, ""); /*JButton made for bottom of screen*/
		btnSubmit.addActionListener(this);
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
		JPanel board = new JPanel();
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
		charArray[8] = "Move";

		JLabel tilesArray[][] = new JLabel[8][8]; /*2d array made to hold tiles, also initialized them in for loop to prevent null pointer error*/
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
						JButton button = createButton(charArray[x], "M", Color.BLACK, Color.WHITE, ""); /*make A-H new button get text value from charArray*/
						   Font font = new Font("Default", Font.PLAIN, 10);
						   button.setFont(font);
						button.addActionListener(this);
						board.add(button);
						button.setBorder(new LineBorder(Color.GRAY, 1));
					} else {
						JButton button = createButton(charArray[x], charArray[x], Color.BLACK, Color.LIGHT_GRAY, ""); /*make Move new button get text value from charArray*/
						button.addActionListener(this);
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
						JButton button = createButton(String.valueOf(i+1), String.valueOf(i+1), Color.BLACK, Color.LIGHT_GRAY, ""); /*make 1-8 new button get text value from charArray*/
						button.addActionListener(this);
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
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Valid Moves");
		chckbxNewCheckBox.setActionCommand("V");
		chckbxNewCheckBox.addActionListener(this);
		chckbxNewCheckBox.setBackground(Color.WHITE);
		rightPanel.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setPreferredSize( new Dimension(430, 25) );
		

		/*Center panel -> Right panel -> [Pink label]*/
		JLabel pinkLabel = new JLabel("<html>Player 1 initialized with 2 pieces.<br/>Player 2 initialized with 2 pieces.</html>");
		rightPanel.add(pinkLabel);
		pinkLabel.setPreferredSize( new Dimension(430, 420) );
		pinkLabel.setOpaque(true);
		pinkLabel.setBackground(Color.PINK);
		//*******************************************

		//*******************************************
		/*End of Center panel -> Right panel -> [Pink panel]*/

		
		
		
		
		/*Center panel -> Right panel -> [Pieces panel]*/
		JPanel piecesPanel = new JPanel();
		piecesPanel.setBackground(Color.WHITE);
		rightPanel.add(piecesPanel);
		piecesPanel.setPreferredSize( new Dimension(430, 90) );
		piecesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//*******************************************
		
		/*Center panel -> Right panel -> Pieces panel -> [Text panel]*/
		JPanel textPanel = new JPanel();
		piecesPanel.add(textPanel, BorderLayout.WEST);
		textPanel.setBackground(Color.WHITE);
		textPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
		textPanel.setPreferredSize( new Dimension(360, 90) );

		//********************************
		JLabel piecesLabelP1 = new JLabel("Player 1 pieces:");
		JLabel piecesLabelP2 = new JLabel("Player 2 pieces:");

		piecesLabelP1.setPreferredSize( new Dimension(300, 40) );
		piecesLabelP2.setPreferredSize( new Dimension(300, 30) );

		textPanel.add(piecesLabelP1);
		textPanel.add(piecesLabelP2);
		//********************************
		/*End of Center panel -> Right panel -> Pieces panel -> [Text panel]*/


		/*Center panel -> Right panel -> Pieces panel -> [Counter panel]*/
		JPanel counterPanel = new JPanel();
		piecesPanel.add(counterPanel, BorderLayout.EAST);
		counterPanel.setBackground(Color.WHITE);
		counterPanel.setPreferredSize( new Dimension(60, 90) );
		counterPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 1, 1));
		//********************************
		JLabel piecesCounterP1 = new JLabel("2");
		JLabel piecesCounterP2 = new JLabel("2");


		counterPanel.add(imageLabelWhite);
		counterPanel.add(piecesCounterP1);
		counterPanel.add(imageLabelBlack);
		counterPanel.add(piecesCounterP2);
		//********************************
		/*End of Center panel -> Right panel -> Pieces panel -> [Counter panel]*/
		
		//*******************************************
		/*End of Center panel -> Right panel -> [Pieces panel]*/
		
		
		//*****************************************************************
		/*End of Center panel -> [Right panel]*/

		
		///////////////////////////////////////////////////////////////////
		/*[End of Center panel]*/

	}  // end of initialize

	/********************************************************************************
	Function name:		createButton
	Purpose				This function creates all buttons in program, button detail are set depending on whats been pased by call.
						Each made button should have a unique action command
	@param				String text, String ac, Color fgc, Color bgc, String handler
	@return				return button
	@version			1.0
	@author				Asim Jasarevic
	********************************************************************************/
	static JButton createButton(String text, String ac, Color fgc, Color bgc, String handler) {
		JButton button = new JButton(text);

		button.setText(text);
		button.setActionCommand(ac);
		button.setForeground(fgc);
		button.setBackground(bgc);

		return button;
	} // end of createButton

	/********************************************************************************
	Function name:		actionPerformed
	purpose				This is an override function for all the buttons and checkmark action listeners
						It gets the action event passed by listiner and prints what was pressed by user
	@param				ActionEvent ae	
	@version			1.0
	@author 			Asim Jasarevic
	********************************************************************************/
	@Override
	public void actionPerformed(ActionEvent ae) {

		String action = ae.getActionCommand();
		System.out.println("Button " + action + " pressed");

	} // end of actionPerformed



} // end of OthelloViewController
