package _02_Chat_Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp implements ActionListener {
	Server server;
	String severInput;
	Client client; 
	String clientInput;
	boolean messageSent;
	
	JFrame frame;
	JPanel panel;
	JButton button;
	JTextField textField;
	
public static void main(String[] args) {
	new ChatApp(); 
	//you were able to create a window for both the server and client. Now you need to write code to clean up the format
	
}
	
	public ChatApp(){
		
		frame = new JFrame("Chat App");
		panel = new JPanel();
		button = new JButton("Send Message"); 
		textField = new JTextField();
		
		
		frame.add(panel);
		panel.add(button);
		panel.add(textField);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(false);
		
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Chat", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			frame.setVisible(true);
			frame.setTitle("Chat App: Server");
			
			server = new Server(8080);
			server.start();
			if(messageSent == false){
				severInput = JOptionPane.showInputDialog("Send a message:");
				server.sendMess(severInput);
				messageSent = true;
			}else{
				severInput = JOptionPane.showInputDialog(clientInput);
				server.sendMess(severInput);
				messageSent = true;
			}
			
		
			
		}else{
			String ipSTR = JOptionPane.showInputDialog("Enter IP address: ");
			String portSTR = JOptionPane.showInputDialog("Enter Port Number: ");
			frame.setVisible(true);
			frame.setTitle("Chat App: Client");
			
			int port = Integer.parseInt(portSTR);
			client = new Client(ipSTR, port);
			client.start();
			//System.out.println("Client created");
			if(messageSent == false){
				//clientInput = JOptionPane.showInputDialog("Send a message:");
				client.sendMess(clientInput);
				messageSent = true;
			}else{
				clientInput = JOptionPane.showInputDialog(severInput);
				client.sendMess(clientInput);
				messageSent =true;
			}
			
			
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
