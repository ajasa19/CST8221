/********************************************************************************
Filename:			OthelloNetworkModalViewController.java
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
Purpose:			display dialog ontop of frame to get inputs from user that will be used to connect to a server
 ********************************************************************************/

package othello;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import othello.OthelloViewController.controller;

import javax.swing.JPanel;


public class OthelloNetworkModalViewController extends JDialog
{

    /** Whether the user pressed the Connect button. */
    private Boolean hasConnected=false;
    
    /** A reference to the private inner Controller class for use by the two buttons. */
    private Controller handler = new Controller();
    
    /** The CombobBox you will need to create.*/
    //NOTE:  You're free to rename it, but as there are some references to it in this stub,
    //you'll need to be consistent when renaming them all.
    private JComboBox portList;
    
    /** The text field where the user will enter the hostname to connect to.*/
    private JTextField adressField;
    
    /** The text field where the user will enter the user name.*/
    private JTextField nameField;
    
    OthelloViewController ViewControllerPub = new OthelloViewController(); /*obj used to see global vars and tilearray in OthelloViewController*/
    
    /** object for View controller.*/
	public void loadObject(OthelloViewController ViewController) {
		ViewControllerPub = ViewController;
	}
	
	/** global dialog that holds all content.*/
	JDialog dialog = new JDialog();
	
	/** tells ViewController which button pressed.*/
	String pressedChar = "Conn";

	/********************************************************************************
	Function name:		OthelloNetworkModalViewController
	purpose				This is a function that displays and sets up components for dialog 
	@version			1.0
	@param				mainView parent frame used to display dialog on 
	@author 			Asim Jasarevic
	 ********************************************************************************/
public void OthelloNetworkModalViewController (JFrame mainView)
    {        
	
		
        dialog = new JDialog(mainView, true);
        dialog.setUndecorated(true);
        
        /** main flow panel to store labels and data prompts.*/
        JPanel innerDialogPanel = new JPanel();
        innerDialogPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        innerDialogPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,5));
        
        /** button flow panel to store buttons.*/ 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setPreferredSize(new Dimension(280, 35));
        
        /** text field for address.*/
        adressField =new JTextField(); 
        adressField.setColumns(20);
        
        /** array with strings for combobox list .*/
        String[] portStrings = {"", "31000", "41000", "51000"};
        
        /** editable combobox with strings defined in array.*/
        portList = new JComboBox(portStrings);
        portList.setEditable(true);
        portList.setPreferredSize(new Dimension(200, 20));
        
        /** text field for name.*/
        nameField =new JTextField(); 
        nameField.setColumns(20);
        
        /** label to display "Adrdress".*/
        JLabel addressLabel = new JLabel("<HTML><U>A</U>ddress:</HTML>");
        addressLabel.setPreferredSize(new Dimension(50, 30));
        addressLabel.setDisplayedMnemonic('A');
        addressLabel.setLabelFor(adressField);
        
        /** label to display "Port".*/
        JLabel portLabel = new JLabel("<HTML><U>P</U>ort:</HTML>");
        portLabel.setPreferredSize(new Dimension(50, 30));
        portLabel.setDisplayedMnemonic('P');
        portLabel.setLabelFor(portList);
        
        /** label to display "Status".*/
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setPreferredSize(new Dimension(200, 30));
        
        /** label to display "Name".*/
        JLabel nameLabel = new JLabel("Name:");
        portLabel.setPreferredSize(new Dimension(70, 30));
        
        /** connect button.*/
        JButton buttonConn = new JButton("Connect");
        buttonConn.addActionListener(handler);
        /** cancel button.*/
        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(handler);
        
        /*add all componets to dialog*/
        dialog.add(innerDialogPanel);
        
        innerDialogPanel.add(addressLabel);
        innerDialogPanel.add(adressField);
        innerDialogPanel.add(portLabel);
        innerDialogPanel.add(portList);
        innerDialogPanel.add(nameLabel);
        innerDialogPanel.add(nameField);
        
        innerDialogPanel.add(buttonPanel);
        buttonPanel.add(buttonConn);
        buttonPanel.add(buttonCancel);

        /*put dialog in center of program screen by geting x and y frome parent frame and div by 2 to get center x and y*/
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        int x = mainView.getWidth() / 2;
        int y = mainView.getHeight() / 2;
        dialog.setLocation(x,y);
        dialog.setModal(true);
        dialog.setSize(320, 165);
        dialog.setVisible(true);
        
        setUndecorated(true); 

        pack();
    }
        


/********************************************************************************
Function name:		OthelloNetworkModalViewController
purpose				This is a Auto-generated constructor stub
@version			1.0
@param				controller used to listen to handlers and do actions
@author 			Asim Jasarevic
 ********************************************************************************/
    public OthelloNetworkModalViewController(controller controller) {
	// TODO Auto-generated constructor stub
}


    /********************************************************************************
    Function name:		getAddress
    purpose				This method returns the value the user has entered.
    @version			1.0
    @author 			Asim Jasarevic
    @return The actual value, unless there was an error or the user pressed the cancel button.
     ********************************************************************************/
    public String getAddress()
    {
        if (hasConnected && validate(adressField.getText()) && getPort() != -1 || hasConnected && adressField.getText().contains("localhost"))
        {
            return (adressField.getText());
        }
        else
        {
            //You can return whatever error message you like.  This isn't cast in stone.
            return ("Error:  Invalid Address or attempt cancelled.");
        }
    }


    /********************************************************************************
    Function name:		getPort
    purpose				This method returns the port the user has selected OR ENTERED in the Combo Box.
    @version			1.0
    @author 			Asim Jasarevic
    @return The port selected.  Returns -1 on invalid port or cancel pressed.
     ********************************************************************************/
    public int getPort()
    {
        int portnum;
        if (hasConnected)
        {
            //Ensure the user has entered digits.
            //You should probably also ensure the port numbers are in the correct range.
            //I did not.  That's from 0 to 65535, by the way.  Treat it like invalid input.
            try
            {
                portnum = Integer.parseInt((String)portList.getSelectedItem());
            }
                catch(NumberFormatException nfe)
            {
                //I've been using a negative portnum as an error state in my main code.
                portnum = -1;
            }
            
            if (portnum < 0 || portnum > 65535) {
            	portnum = -1;
            }

            return portnum;
        }
        return -1;
    }
    
    public String getName()
    {
        String name; 
        if (hasConnected)
        {

            	name = nameField.getText();

            return name;
        }
        return null;
    }
    
    /********************************************************************************
    Function name:		hideModal
    purpose				Responsible for final cleanup and hiding the modal. 
    @version			1.0
    @author 			Asim Jasarevic
     ********************************************************************************/
    public void hideModal()
    { 
        //Add any code that you may want to do after the user input has been processed
        //and you're figuratively closing up the shop.
    	dialog.setVisible(false);
        
    }
    

    /********************************************************************************
    Function name:		pressedConnect
    purpose				Returns whether or not the user had pressed connect.
    @version			1.0
    @author 			Asim Jasarevic
    @return True if the user pressed Connect, false if the user backed out with cancel.
     ********************************************************************************/
    public boolean pressedConnect()
    {
        return hasConnected;
    }
    

    /********************************************************************************
    Function name:		Controller
    purpose				The Controller for managing user input in the network dialogue.
    @version			1.0
    @author 			Asim Jasarevic
    @see OthelloViewController
     ********************************************************************************/ 
    private class Controller implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String s = evt.getActionCommand();
            
            //I set the action command on my connect button to "C".
            if ("Connect".equals(s))
            {
                //In Assignment 2-2, we will be making revisions here.
                //This would be a great place to update the "Status" portion of the UI.
                hasConnected=true;
                hideModal();
                pressedChar = "Conn";
                
            }
            else //My "Cancel" button has an action command of "X" and gets called here.
            {
                hasConnected=false;
                hideModal();
                pressedChar = "Canc";
            }
            //Hide the modal. For part 2, we may not want to hide the modal right away.
            hideModal();
        }
        
    }
    

    /********************************************************************************
    Function name:		validate
    purpose				Regexe used to see if address input is valid
    @version			1.0
    @param				ip is the user inpur for address
    @return				ip.matches(PATTERN)
    @author 			samthebest(stackoverflow)
     ********************************************************************************/ 
    public static boolean validate(final String ip) {
    	/*pattern for a valid ip address (ex. 1.1.1.1)*/
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

        return ip.matches(PATTERN);
    }
    
}
        

        

