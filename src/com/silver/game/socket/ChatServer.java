package com.silver.game.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer extends Thread{
	
	private boolean serverRunning;
	private ServerSocket listener;
	private ChatThread chatThread;
	
	public ChatServer(){
		super();
	}
	
	@Override
	public void run(){
		startServer();
	}
	public void stopServer(){
		serverRunning = false;
		try {
			if(!listener.isClosed()){
				listener.close();
			}
		} catch (IOException e) {
			//No action since we  know an error is thrown when forcing a close
		}
	}
	//Starts listening for connections
	private void startServer() {
		serverRunning = true;

		System.out.println("Listening for connections...");
	    int clientNumber = 0;
	    try {
			listener = new ServerSocket(9898);	
		} catch (IOException e1) {
			System.out.println("Could not start the server...");
			serverRunning = false;
		}
	    
        while (serverRunning) {
            try {
            	//Any new connections will replace a preexisting connection
            	chatThread = new ChatThread(listener.accept(), clientNumber++);
            	chatThread.start();
			} catch (IOException e) {
				serverRunning = false;
				System.out.println("Something else went wrong ...?");
			}
        }
        System.out.println("The server doesn't want to talk anymore!");
	}
	
	public void sendMessage(String message){
		if(chatThread != null){
			chatThread.sendMessage(message);
		}
	}
}
