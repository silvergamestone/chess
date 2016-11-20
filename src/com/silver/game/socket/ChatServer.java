package com.silver.game.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {
	public ChatServer(){
		
	}
	//Starts listening for connections
	public void startServer() {
		try {
			System.out.println("Listening for connections...");
			    int clientNumber = 0;
			    ServerSocket listener = new ServerSocket(9898);
			    try {
			        while (true) {
			            new ChatThread(listener.accept(), clientNumber++).start();
			        }
			    } finally {
			        listener.close();
			    }
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
