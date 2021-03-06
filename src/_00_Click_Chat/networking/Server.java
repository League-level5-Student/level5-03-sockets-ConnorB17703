package _00_Click_Chat.networking;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import _02_Chat_Application.MessageManager;

public class Server {
	private int port;

	private ServerSocket server;
	private Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;
	
	MessageManager mm;

	public Server(int port, MessageManager mm) {
		this.port = port;
		this.mm = mm;
	}

	public void start(){
		try {
			server = new ServerSocket(port, 100);

			System.out.println("no connection");
			JOptionPane.showMessageDialog(null, "Server started at: " + getIPAddress() + "\nPort: " + getPort());
			connection = server.accept();
			System.out.println("connected");
			
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();
			//System.out.println("Server created");

			messageListener();
				
			while (connection.isConnected()) {
				try {
					//JOptionPane.showMessageDialog(null, is.readObject());
					//System.out.println(is.readObject());
					String res = JOptionPane.showInputDialog(is.readObject());
					sendMess(res);
					
					
				}catch(EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendClick() {
		try {
			if (os != null) {
				os.writeObject("CLICK SENT FROM SERVER");
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMess(String message){
		
		try{
//			if(os != null){
				os.writeObject(message);
				//System.err.println("sent message");
				os.flush();
//			}
		}catch(IOException e){
			e.printStackTrace();
			
		}
		
	}
//	public void input(){
//		while (connection.isConnected()) {
//			try {
//				String response = JOptionPane.showInputDialog("Say something!");
//				sendMess(response);
//				
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//	}
//}
	
	public void messageListener(){
		while (connection.isConnected()) {
			try {
				Object in = is.readObject();
				//JOptionPane.showMessageDialog(null, in);
				System.err.println(in);
				mm.receiveMess((String)in);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
		System.err.println("loop exited");



}
}
