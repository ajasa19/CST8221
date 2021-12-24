package server;
/********************************************************************************
Filename:			othelloServer.java
Version:			1.0
Author:				Asim Jasarevic
StudentNo:			040922815
Course Name/Number:	Java Application 20F_CST8221–300
Lab Sect:			303
Professor:			Daniel Cormier
Assignment #:		2-2
Assignment name:	Othello 2 part 2
Due Date:			2020, Dec, 13
Submission Date:	2020, Dec, 13
Purpose:			server that listens to ports for users and messages. 
 ********************************************************************************/

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;


public class othelloServer {

	ServerSocket server; /*socket*/
	int connNumber = 0; /*number position of new users*/
	static int port; /*port number*/
	static List<User> clients; /*list of users on open ports*/


	/********************************************************************************
	class name:		main
	purpose				get user input for port number and also run server
	@param				String[] args 
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Port number >>");
		port = scanner.nextInt();

		new othelloServer(port).run();
	}


	/*constructor to assign port and add new user to list*/
	public othelloServer(int port) {
		this.port = port;
		this.clients = new ArrayList<User>();
	}

	/********************************************************************************
	Function name:		run
	purpose				used to run server and accept new users, assign attributes, and accept inbound messages
	@param				port (passed to be used)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void run() throws IOException {

		/*make new port for user to server*/
		server = new ServerSocket(port);

		System.out.println("Using port: " + port);

		/*while server running accept new users*/
		while (true) {

			/*accept new client*/
			Socket client = server.accept();
			connNumber++;

			/*get user username*/
			String userName = (new Scanner ( client.getInputStream())).nextLine(); /*there is probably a better way to get inbound text but this works*/
			
			System.out.println("Inbound connection #" + connNumber);
			System.out.println(userName + " has connected.\n");

			/*create new user*/
			User newUser = new User(client, userName);
			/*add user to list*/
			this.clients.add(newUser);

			sendServerMessagesAway(newUser.toString() + "has joined the server");

			/*create new thread for new user incoming messages handling*/
			new Thread(new UserOnPort(this, newUser)).start();
		}
	}

	/********************************************************************************
	Function name:		sendMessagesAway
	purpose				send message ny a user to all users on open ports
	@param				msg (message to be passed)
	@param				User (User to be passed to)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void sendMessagesAway(String msg, User userSender) {
		for (User client : this.clients) {
			client.passMessage().println(userSender + ": " + msg);
		}
	}

	/********************************************************************************
	Function name:		sendServerMessagesAway
	purpose				send message SERVER message to all users on open ports
	@param				msg (message to be passed)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void sendServerMessagesAway(String message) {
		for (User client : this.clients) {
			client.passMessage().println("SERVER: " + message);
		}
	}

	/********************************************************************************
	Function name:		sendAllUsers
	purpose				send users list of other users
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void sendAllUsers(){
		for (User client : this.clients) {
			client.passMessage().println(this.clients);
		}
	}
	
	/********************************************************************************
	Function name:		removeUser
	purpose				remove user from list
	@param				user (user to be deleted)
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void removeUser(User user){
		this.clients.remove(user);
	}

}

/********************************************************************************
class name:		User
purpose				user class hold methods to change user fields along with all info on user and message data
@version			1.0
@author 			Asim Jasarevic
 ********************************************************************************/
class User {
	 PrintStream streamOut; /*messages comming from server to ports*/
	 InputStream streamIn; /*messages coming from ports to server*/
	 String userName;


	/*constructor to send and recive messages on right socket*/
	public User(Socket client, String name) throws IOException {
		this.streamOut = new PrintStream(client.getOutputStream());
		this.streamIn = client.getInputStream();
		this.userName = name;
	}

	/********************************************************************************
	Function name:		changeName
	purpose				change name of user
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void changeName(String name){

		String preName = this.userName;
		String parsedName = name;
		parsedName = parsedName.replace("/name (","");
		parsedName = parsedName.replace(")","");

		this.userName = parsedName;
		this.passMessage().println(preName + " name changed to " + parsedName);
		//othelloServer.sendServerMessagesAway(parsedName + "has joined the server");
		System.out.println(preName + " has been changed to " + parsedName);
		return;

	}

	/********************************************************************************
	Function name:		DisconnectUser
	purpose				print out user has been diconected (kick action happens in run())
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void DisconnectUser(){

		String preName = this.userName;
		System.out.println(preName + " has disconnected");
		return;

	}

	/********************************************************************************
	Function name:		passMessage
	purpose				get messages and pass it to gui
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public PrintStream passMessage(){
		return this.streamOut;
	}

	/********************************************************************************
	Function name:		InputStream
	purpose				get text in text field in gui
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public InputStream getTextField(){
		return this.streamIn;
	}

	/********************************************************************************
	Function name:		getUsername
	purpose				get username of user
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public String getUsername(){
		return this.userName;
	}

	/********************************************************************************
	Function name:		toString
	purpose				print username besides message
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public String toString(){
		return (this.getUsername());
	}
}

/********************************************************************************
class name:		UserHandler
purpose				thread to handle user intake and out take of messages to all users on all ports
@version			1.0
@author 			Asim Jasarevic
 ********************************************************************************/
class UserOnPort implements Runnable {

	private othelloServer server;
	private User user;

	/*constructor to assign user fields*/
	public UserOnPort(othelloServer server, User user) {
		this.server = server;
		this.user = user;
		this.server.sendAllUsers();
	}


	/********************************************************************************
	Function name:		run
	purpose				run user thread so messages can be passed and sent to all users
	@version			1.0
	@author 			Asim Jasarevic
	 ********************************************************************************/
	public void run() {
		
		String message;
		Scanner scanner = new Scanner(this.user.getTextField());

		/*when new message is sent parse*/
		while (scanner.hasNextLine()) {
			message = scanner.nextLine();

			/*if name command found call methode to change user name*/
			if (message.contains("/name (") && message.contains(")") ){
				user.changeName(message);			
			} 
			/*if bye command found kick user and remove server*/
			else if (message.contains("/bye")){ 

				user.DisconnectUser();
				server.removeUser(user);
				this.server.sendAllUsers();
			}
			/*if no command found just send message*/
			else{
				server.sendMessagesAway(message, user);
			}
		}
		
		server.removeUser(user);
		this.server.sendAllUsers();
	}
}

