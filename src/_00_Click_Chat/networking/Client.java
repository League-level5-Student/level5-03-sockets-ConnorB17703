package _00_Click_Chat.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start(){
		try {
			
			connection = new Socket(ip, port);
			System.out.println("client started");
			
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();
			//System.out.println("Client created");
//			Thread thread = new Thread(()-> input());
//			thread.start();
			
			messageListener();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void sendClick() {
		try {
			if (os != null) {
				os.writeObject("CLICK SENT FROM CLIENT");
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
//				//JOptionPane.showMessageDialog(null, is.readObject());
//				//System.out.println(is.readObject());
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
			

				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
		System.err.println("loop exited");
}



}
