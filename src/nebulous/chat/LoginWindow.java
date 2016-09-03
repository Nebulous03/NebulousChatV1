package nebulous.chat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import nebulous.networking.ClientConnection;
import nebulous.networking.ServerConnection;

import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class LoginWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private String username = "";
	private String remoteIp = "0.0.0.0";
	private short remotePort = 23000;

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField ipField;

	public static void main(String[] args) {}

	public LoginWindow() {
	
		try {
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			  System.out.println("Error setting native LAF: " + e);
		}
		
		setResizable(false);
		setTitle("Nebulous Chat - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 326);
		
		JMenuBar menuMain = new JMenuBar();
		setJMenuBar(menuMain);
		
		JMenu mnOptions = new JMenu("Options");
		menuMain.add(mnOptions);
		
		JMenu mnHelp = new JMenu("Help");
		menuMain.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameField.setBounds(10, 35, 274, 29);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Display Name:");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameLabel.setBounds(11, 3, 92, 29);
		contentPane.add(usernameLabel);
		
		JLabel ipLabel = new JLabel("Remote IP:");
		ipLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ipLabel.setBounds(10, 86, 92, 29);
		contentPane.add(ipLabel);
		
		ipField = new JTextField();
		ipField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ipField.setColumns(10);
		ipField.setBounds(10, 117, 168, 29);
		contentPane.add(ipField);
		
		JFormattedTextField portField = new JFormattedTextField();
		portField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		portField.setBounds(50, 160, 128, 29);
		contentPane.add(portField);
		
		JLabel portLabel = new JLabel("Port:");
		portLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		portLabel.setBounds(10, 159, 92, 29);
		contentPane.add(portLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 79, 274, 2);
		contentPane.add(separator);
		
		JButton joinButton = new JButton("Join");
		joinButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				username = usernameField.getText();
				remoteIp = ipField.getText();
				remotePort = Short.parseShort(portField.getText());
				
				login(username, remoteIp, remotePort);
			}
			
		});
		
		joinButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		joinButton.setBounds(10, 221, 274, 44);
		contentPane.add(joinButton);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 206, 274, 2);
		contentPane.add(separator2);
		
		JCheckBox thingOne = new JCheckBox("Thing One");
		thingOne.setBounds(190, 112, 97, 23);
		contentPane.add(thingOne);
		
		JCheckBox thingTwo = new JCheckBox("Thing Two");
		thingTwo.setBounds(190, 131, 97, 23);
		contentPane.add(thingTwo);
		
		JCheckBox thingThree = new JCheckBox("Thing Three");
		thingThree.setBounds(190, 161, 97, 23);
		contentPane.add(thingThree);
		
		this.setVisible(true);
	}
	
	public void login(String user, String ip, short port){
		dispose();
		
		User client = new User(user, new ClientConnection(new ServerConnection(ip, port))); // TODO: FIX LOCAL IP;
		NebulousChat.openChatWindow(client);
	}
	
	public String getUsername() {
		return username;
	}

	public String getIp() {
		return remoteIp;
	}

	public short getPort() {
		return remotePort;
	}
}
