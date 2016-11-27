package com.silver.game.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChatClient {
	
	private BufferedReader in;
    private PrintWriter out;
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
			in = new BufferedReader(
			        new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			System.out.println("Waiting to read data");
			// Consume the initial welcoming messages from the server
			for (int i = 0; i < 1; i++) {
			    System.out.println(in.readLine() + "\n");
			}
		} catch (Exception e) {
			System.out.println(e);
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e1) {
				System.out.print("Couldn't close socket");
			}
		}
	}
}
