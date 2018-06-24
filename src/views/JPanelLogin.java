package views;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class JPanelLogin extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField fieldName;
	private JTextField fieldIPAddress;
	private JTextField fieldIPPort;
	
	public JPanelLogin() {
		setLayout(new GridLayout(1, 3));
		
		fieldName = new JTextField();
		fieldName.setBorder(BorderFactory.createTitledBorder("Player Name"));
		add(fieldName);
		
		fieldIPAddress = new JTextField();
		fieldIPAddress.setBorder(BorderFactory.createTitledBorder("IP Address"));
		add(fieldIPAddress);
		
		fieldIPPort = new JTextField();
		fieldIPPort.setBorder(BorderFactory.createTitledBorder("IP Port"));
		add(fieldIPPort);
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
