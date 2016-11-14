package com.silver.game.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatGUI extends JFrame implements ActionListener{
	
	/**
	 * Auto generated serialized ID
	 */
	private static final long serialVersionUID = 2866367246115027827L;
	
	private int frameHeight = 600;
	private int frameWidth = 500;
	private int chatRowSize = 4;
	private String title = "Chat GUI";
	
	private JTextArea chatArea;
	private JTextArea messageArea;
	
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
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(frameWidth, frameHeight);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String chatText = chatArea.getText();
		chatArea.setText("");
		messageArea.append(chatText+System.lineSeparator());
	}
}
