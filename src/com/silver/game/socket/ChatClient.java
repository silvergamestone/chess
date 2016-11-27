package com.silver.game.socket;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChatClient {
	
	private ChatThread chatThread;
	private JFrame frame;
	private Socket socket;
	public ChatClient(JFrame frame){
		this.frame = frame;
		
	}
	public void stopClient(){
		if(socket.isConnected()){
			try {
				socket.close();
			} catch (IOException e) {
				//We know it will throw exception
				System.out.println("Client was closed");
			}
		}
	}
	
	public void connectToServer(){
	       try {
			// Get the server address from a dialog box.
			String serverAddress = JOptionPane.showInputDialog(
			    frame,
			    "Enter IP Address of the Server:",
			    "Welcome to the Chat Program",
			    JOptionPane.QUESTION_MESSAGE);

			// Make connection and initialize streams
			socket = new Socket(serverAddress, 9898);
			//-1 is saying that this is a client socket
        	chatThread = new ChatThread(socket, -1);
        	chatThread.start();
        	
		} catch (Exception e) {
			System.out.println(e);
			try {
				socket.close();
			} catch (IOException e1) {
				System.out.print("Couldn't close socket");
			}
		}
	}
	public void sendMessage(String message){
		if(chatThread != null){
			chatThread.sendMessage(message);
		}
	}
}
