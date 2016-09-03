package nebulous.chat;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

public class ClientWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private JPanel contentPane;
	private JTextField chatInput;
	private JTextArea chatHistory;
	private DefaultCaret caret;
	
	public ClientWindow(User user) {
		this.user = user;
		setupWindow();
		sendToChat("(CLIENT) Successfully connected to " + 
		user.getServer().getRemoteIP() + ":" + user.getServer().getRemotePort());
	}
	
	public void setupWindow(){
		setTitle("Nebulous Chat - Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout contentPaneGrid = new GridBagLayout();
		contentPaneGrid.columnWidths = new int[]{0, 0, 0};
		contentPaneGrid.rowHeights = new int[]{0, 0, 0};
		contentPaneGrid.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPaneGrid.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(contentPaneGrid);
		
		chatHistory = new JTextArea();
		chatHistory.setEditable(false);
		JScrollPane scroll = new JScrollPane(chatHistory);
		chatHistory.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		GridBagConstraints chatHistoryGrid = new GridBagConstraints();
		chatHistoryGrid.insets = new Insets(0, 0, 5, 0);
		chatHistoryGrid.fill = GridBagConstraints.BOTH;
		chatHistoryGrid.gridx = 0;
		chatHistoryGrid.gridwidth = 2;
		chatHistoryGrid.gridy = 0;
		contentPane.add(scroll, chatHistoryGrid);
		
		caret = (DefaultCaret) chatHistory.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		chatInput = new JTextField();
		chatInput.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode() == KeyEvent.VK_ENTER){
					String message = chatInput.getText();
					if(!message.equals("")){
						chatInput.setText("");
						chatInput.requestFocus();
						sendToChat(message);
						sendToServer(message);
					}
				}
			}
			
		});
		
		chatInput.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		GridBagConstraints chatInputGrid = new GridBagConstraints();
		chatInputGrid.insets = new Insets(0, 0, 0, 5);
		chatInputGrid.fill = GridBagConstraints.HORIZONTAL;
		chatInputGrid.gridx = 0;
		chatInputGrid.gridy = 1;
		contentPane.add(chatInput, chatInputGrid);
		chatInput.setColumns(10);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String message = chatInput.getText();
				if(!message.equals("")){
					chatInput.setText("");
					chatInput.requestFocus();
					sendToChat(message);
					sendToServer(message);
				}
			}
			
		});
		
		sendButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		GridBagConstraints sendButtonGrid = new GridBagConstraints();
		sendButtonGrid.gridx = 1;
		sendButtonGrid.gridy = 1;
		contentPane.add(sendButton, sendButtonGrid);
		
		this.setVisible(true);
		
		requestFocus();
		chatInput.requestFocus();
	}
	
	public void sendToChat(String message){
		chatHistory.append("[" + user.getUsername() + "] " + message + "\n");
		chatHistory.setCaretPosition(chatHistory.getDocument().getLength());
	}
	
	public void sendToServer(String message){
		Console.specialPrintln("[(CLIENT)" + user.getUsername() + "] " + message);
	}

	public User getCurrentUser() {
		return user;
	}
}
