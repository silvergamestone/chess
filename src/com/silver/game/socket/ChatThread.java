package com.silver.game.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.silver.game.gui.ChatGUI;

public class ChatThread extends Thread{
	private Socket socket;
	private int clientNumber;
	private PrintWriter out;
	private ChatGUI chatGUI;
	
	
	public ChatThread(Socket socket, int clientNumber, ChatGUI chatGUI) {
		super();
		this.socket = socket;
		this.clientNumber = clientNumber;
		this.chatGUI = chatGUI;
		chatGUI.sendToGUI("Connected to " + clientNumber);
	}
	
	public void sendMessage(String message){
		out.println(message);
	}
	
	@Override
	public void run(){
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			
			//out.println("Hey dude");
			
			while (true) {
				String input = in.readLine();
				/*if (input == null || input.equals(".")) {
					break;
				}*/
				//out.println(input.toUpperCase());
				chatGUI.sendToGUI(input);
			}
		} catch (IOException e) {
			chatGUI.sendToGUI("Error handling client# " + clientNumber + ": " + e);
		} finally {
			 try {
                 socket.close();
                 chatGUI.sendToGUI("Connection with client " + clientNumber + " closed");
             } catch (IOException e) {
            	 chatGUI.sendToGUI("Couldn't close socket with client" + clientNumber);
             }
		}
	}
}
