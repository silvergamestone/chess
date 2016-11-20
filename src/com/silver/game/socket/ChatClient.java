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
	
	public ChatClient(JFrame frame){
		this.frame = frame;
		
	}
	
	public void connectToServer(){
		Socket socket = null;
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

			// Consume the initial welcoming messages from the server
			for (int i = 0; i < 3; i++) {
			    System.out.println(in.readLine() + "\n");
			}
		} catch (Exception e) {
			System.out.println(e);
			try {
				socket.close();
			} catch (IOException e1) {
				System.out.print("Couldn't close socket");
			}
		}
	}
}
