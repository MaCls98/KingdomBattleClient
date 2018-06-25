package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Controller;
import controller.EVENTS;

public class JPanelLogin extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField fieldName;
	private JTextField fieldIPAddress;
	private JTextField fieldIPPort;
	private JButton btnLogin;
	
	public JPanelLogin(Controller controller) {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		
		setSize(400, 500);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		
		for (int i = 0; i < 1; i++) {
			c.gridx = i;
			add(new JLabel(""), c);
		}
		
		c.insets = new Insets(2, 10, 10, 10);
		
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		JLabel lblTitle = new JLabel("Welcome to Kingdom Battle");
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setIcon(new ImageIcon(getClass().getResource("/img/icon.png")));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		add(lblTitle, c);
		
		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		fieldName = new JTextField();
		fieldName.setBorder(BorderFactory.createTitledBorder("Player Name"));
		fieldName.setFont(new Font("Arial", Font.BOLD, 20));
		add(fieldName, c);
		
		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 1;
		fieldIPAddress = new JTextField();
		fieldIPAddress.setBorder(BorderFactory.createTitledBorder("IP Address"));
		fieldIPAddress.setFont(new Font("Arial", Font.BOLD, 20));
		add(fieldIPAddress, c);
		
		c.gridy = 4;
		c.gridx = 0;
		c.gridwidth = 1;
		fieldIPPort = new JTextField();
		fieldIPPort.setBorder(BorderFactory.createTitledBorder("IP Port"));
		fieldIPPort.setFont(new Font("Arial", Font.BOLD, 20));
		add(fieldIPPort, c);
		
		c.gridy = 5;
		c.gridx = 0;
		c.gridwidth = 1;
		btnLogin = new JButton("Connect and Play!");
		btnLogin.addActionListener(controller);
		btnLogin.setActionCommand(EVENTS.LOGIN.toString());
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setIcon(new ImageIcon(getClass().getResource("/img/battle.png")));
		btnLogin.setFont(new Font("Arial", Font.BOLD, 20));
		add(btnLogin, c);
	}

	public String getFieldName() {
		return fieldName.getText();
	}

	public int getFieldIPAddress() {
		return Integer.parseInt(fieldIPAddress.getText());
	}

	public int getFieldIPPort() {
		return Integer.parseInt(fieldIPPort.getText());
	}
}
