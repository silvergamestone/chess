package com.silver.game.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatGUI extends JFrame{
	
	/**
	 * Auto generated serialized ID
	 */
	private static final long serialVersionUID = 2866367246115027827L;
	
	private int frameHeight = 600;
	private int frameWidth = 500;
	private int chatRowSize = 4;
	private String title = "Chat GUI";
	
	private JTextArea chatArea;
	
	public ChatGUI(){
		super();
		this.setTitle(title);
		
		chatArea = new JTextArea();
		chatArea.setRows(chatRowSize);
		chatArea.setLineWrap(true);
		JScrollPane chatScrollPane = new JScrollPane(chatArea);
		this.add(chatScrollPane, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameWidth, frameHeight);
		setVisible(true);
	}
}
