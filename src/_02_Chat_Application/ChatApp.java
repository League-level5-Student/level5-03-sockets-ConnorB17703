package _02_Chat_Application;

import javax.swing.JOptionPane;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	Server server;
	Client client; 
	boolean messageSent;
	
	
	public ChatApp(){
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Chat", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new Server(8080);
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			
			
		}else{
			String ipSTR = JOptionPane.showInputDialog("Enter IP address: ");
			String portSTR = JOptionPane.showInputDialog("Enter Port Number: ");
			int port = Integer.parseInt(portSTR);
			client = new Client(ipSTR, port);
			
		}
		
	}
	
	
}
