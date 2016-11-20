package com.silver.game.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatThread extends Thread{
	private Socket socket;
	private int clientNumber;
	
	
	public ChatThread(Socket socket, int clientNumber) {
		super();
		this.socket = socket;
		this.clientNumber = clientNumber;
		System.out.println("Connected to " + clientNumber);
	}
	
	@Override
	public void run(){
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			out.println("Hey dude");
			
			while (true) {
				String input = in.readLine();
				if (input == null || input.equals(".")) {
					break;
				}
				out.println(input.toUpperCase());
			}
		} catch (IOException e) {
			System.out.println("Error handling client# " + clientNumber + ": " + e);
		} finally {
			 try {
                 socket.close();
                 System.out.println("Connection with client " + clientNumber + " closed");
             } catch (IOException e) {
            	 System.out.println("Couldn't close socket with client" + clientNumber);
             }
		}
	}
}
