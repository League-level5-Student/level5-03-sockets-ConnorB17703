package _02_Chat_Application;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
	boolean clientSent;
	boolean serverSent;
	
	
	JFrame frame;
	JPanel panel;
	JButton button;
	JTextField textField;
	JTextArea textArea;
	MessageManager MM = new MessageManager() {
		
		@Override
		public void receiveMess(String message) {
			// TODO Auto-generated method stub
			if(frame.getTitle().equals("Chat App: Server")){
				textArea.append("Client: " + message + "\n");
			}else{
				textArea.append("Server: " + message + "\n");
			}
			
		}
	};
	
public static void main(String[] args) {
	new ChatApp(); 
	//fix that bug with the log
	
}
	
	public ChatApp(){
		
		frame = new JFrame("Chat App");
		panel = new JPanel();
		button = new JButton("Send Message"); 
		textField = new JTextField(15);
		textArea = new JTextArea();
		
		button.addActionListener(this);
		frame.add(panel, BorderLayout.NORTH);
		panel.add(button);
		panel.add(textField);
		frame.add(textArea, BorderLayout.CENTER);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(false);
		
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Chat", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			frame.setVisible(true);
			frame.setTitle("Chat App: Server");
			
			server = new Server(8080, MM);
			server.start();
			
//			if(messageSent == false){
//				severInput = textField.getText();
//				server.sendMess(severInput);
//				messageSent = true;
//			}else{
//				severInput = JOptionPane.showInputDialog(clientInput);
//				server.sendMess(severInput);
//				messageSent = true;
//			}
			
		
			
		}else{
			String ipSTR = JOptionPane.showInputDialog("Enter IP address: ");
			String portSTR = JOptionPane.showInputDialog("Enter Port Number: ");
			frame.setVisible(true);
			frame.setTitle("Chat App: Client");
			
			int port = Integer.parseInt(portSTR);
			client = new Client(ipSTR, port, MM);
			client.start();
			//System.out.println("Client created");
//			if(messageSent == false){
//				clientInput = textField.getText();
//				client.sendMess(clientInput);
//				messageSent = true;
//			}else{
//				clientInput = JOptionPane.showInputDialog(severInput);
//				client.sendMess(clientInput);
//				messageSent =true;
//			}
//			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
			
			if(frame.getTitle().equals("Chat App: Server")){
			textArea.append("Server: " + textField.getText() + "\n");
			severInput = textField.getText();
			server.sendMess(severInput);
			serverSent = true;
				//textArea.append("Server: " + textField.getText() + "\n");
			
			}else{
			textArea.append("Client: " + textField.getText() + "\n");
			clientInput = textField.getText();
			client.sendMess(clientInput);
			clientSent = true;
				//textArea.append("Client: " + textField.getText() + "\n");
			}
			
			//messageSent = true;
			
		
			//System.out.println("second else block");
			
//			if(frame.getTitle().equals("Chat App: Server")){
//			textArea.append("Server: " + textField.getText() + "\n");
//			severInput = textArea.getText();
//			server.sendMess(severInput);
//			//client.sendMess(severInput);
//
//			
//		
//			
//			textArea.append("Client: " + textField.getText() + "\n");
//			clientInput = textArea.getText();
//			client.sendMess(clientInput);
			//server.sendMess(clientInput);
			
			}
		}
		
	
		
		

