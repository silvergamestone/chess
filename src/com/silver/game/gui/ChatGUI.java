package com.silver.game.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.silver.game.socket.ChatClient;
import com.silver.game.socket.ChatServer;

public class ChatGUI extends JFrame implements ActionListener{
	
	/**
	 * Auto generated serialized ID
	 */
	private static final long serialVersionUID = 2866367246115027827L;
	
	private int frameHeight = 600;
	private int frameWidth = 500;
	private int chatRowSize = 4;
	private String title = "Chat GUI";
	private JMenuItem serverMenuItem;
	private JMenuItem clientMenuItem;
	private JMenuItem stopMenuItem;
	private JTextArea chatArea;
	private JTextArea messageArea;
	private ChatServer chatServer;
	private ChatClient chatClient;
	private JButton chatButton;
	
	public ChatGUI(){
		super();
		this.setTitle(title);
		
		//Create the South Panel
		JPanel southPanel = new JPanel(new BorderLayout());
		
		//Chat Area for typing messages
		chatArea = new JTextArea();
		chatArea.setRows(chatRowSize);
		chatArea.setLineWrap(true);
		JScrollPane chatScrollPane = new JScrollPane(chatArea);
		//this.add(chatScrollPane, BorderLayout.SOUTH);
		southPanel.add(chatScrollPane);
		
		chatButton = new JButton("Send");
		chatButton.addActionListener(this);
		
		southPanel.add(chatButton, BorderLayout.EAST);
		this.add(southPanel, BorderLayout.SOUTH);
		
		//Message Area - contains all previous messages
		messageArea = new JTextArea();
		messageArea.setEditable(false);
		JScrollPane messageScrollPane = new JScrollPane(messageArea);
		this.add(messageScrollPane);
		
		//menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu connectMenu = new JMenu("Connect");
		serverMenuItem = new JMenuItem("Start As Server");
		serverMenuItem.addActionListener(this);
		clientMenuItem = new JMenuItem("Start As Client");
		clientMenuItem.addActionListener(this);
		stopMenuItem = new JMenuItem("Stop");
		stopMenuItem.addActionListener(this);
		stopMenuItem.setEnabled(false);
		connectMenu.add(serverMenuItem);
		connectMenu.add(clientMenuItem);
		connectMenu.add(stopMenuItem);
		
		menuBar.add(connectMenu);
		this.setJMenuBar(menuBar);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(frameWidth, frameHeight);
		this.setVisible(true);
	}
	
	private void enableConnection(boolean enabled){
		serverMenuItem.setEnabled(enabled);
		clientMenuItem.setEnabled(enabled);
		stopMenuItem.setEnabled(!enabled);
	}
	public void sendToGUI(String message){
		messageArea.append(message+System.lineSeparator());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == chatButton){
			String chatText = chatArea.getText();
			//makes sure that the chat box is empty, don't send blank messages
			if (!chatText.isEmpty()){ 
				chatArea.setText("");
				messageArea.append(chatText+System.lineSeparator());
				
				if(chatServer != null){
					chatServer.sendMessage(chatText);
				}else if(chatClient != null){
					chatClient.sendMessage(chatText);
				}
			}
		}else if(e.getSource() == clientMenuItem){
			chatClient = new ChatClient(this);
			chatClient.connectToServer();
			enableConnection(false);
			sendToGUI("Started as client...");
		}else if(e.getSource() == serverMenuItem){
			chatServer = new ChatServer(this);
			enableConnection(false);
			chatServer.start();
			sendToGUI("Started as server...");
		}else if(e.getSource() == stopMenuItem){
			enableConnection(true);
			if(chatServer != null){
				chatServer.stopServer();
				chatServer = null;
			} else if (chatClient != null){
				chatClient.stopClient();
				chatClient = null;
			}
		}
	}
}
