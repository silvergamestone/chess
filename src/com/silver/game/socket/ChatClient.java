package com.silver.game.socket;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.silver.game.gui.ChatGUI;

public class ChatClient {
	
	private ChatThread chatThread;
	private JFrame frame;
	private Socket socket;
	private ChatGUI chatGUI;
	
	public ChatClient(JFrame frame, ChatGUI chatGUI){
		this.frame = frame;
		this.chatGUI = chatGUI;
		
	}
	public void stopClient(){
		if(socket.isConnected()){
			try {
				socket.close();
			} catch (IOException e) {
				//We know it will throw exception
				chatGUI.sendToGUI("Client was closed");
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
        	chatThread = new ChatThread(socket, -1, chatGUI);
        	chatThread.start();
        	
		} catch (Exception e) {
			chatGUI.sendToGUI(e.getMessage());
			try {
				socket.close();
			} catch (IOException e1) {
				chatGUI.sendToGUI("Couldn't close socket");
			}
		}
	}
	public void sendMessage(String message){
		if(chatThread != null){
			chatThread.sendMessage(message);
		}
	}
}
