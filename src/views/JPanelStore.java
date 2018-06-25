package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import controller.EVENTS;

public class JPanelStore extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox boxHealth, boxMove, boxAttack;
	private JButton btnBuy;
	
	public JPanelStore(Controller controller) {
		setLayout(new GridLayout(2, 3));
		
		JLabel lblHealth = new JLabel(new ImageIcon(getClass().getResource("/img/health.png")));
		add(lblHealth);
		
		JLabel lblMove = new JLabel(new ImageIcon(getClass().getResource("/img/move.png")));
		add(lblMove);
		
		JLabel lblAttack = new JLabel(new ImageIcon(getClass().getResource("/img/attack.png")));
		add(lblAttack);
		
		boxHealth = new JCheckBox("Aumentar 10 puntos de vida");
		boxHealth.setFont(new Font("Arial", Font.PLAIN, 15));
		add(boxHealth);
		
		boxMove = new JCheckBox("Aumentar 10 puntos de movimiento");
		boxMove.setFont(new Font("Arial", Font.PLAIN, 15));
		add(boxMove);
		
		boxAttack = new JCheckBox("Aumentar 10 puntos de ataque");
		boxAttack.setFont(new Font("Arial", Font.PLAIN, 15));
		add(boxAttack);
		
		btnBuy = new JButton("Comprar mejora");
		btnBuy.addActionListener(controller);
		btnBuy.setActionCommand(EVENTS.BUY.toString());
		btnBuy.setBackground(Color.white);
		add(btnBuy);
	}
	
	public void removeItems(){
		boxHealth.setSelected(false);
		boxMove.setSelected(false);
		boxAttack.setSelected(false);
	}

	public boolean getHealth(){
		System.out.println(boxHealth.isSelected());
		return boxHealth.isSelected();
	}
	
	public boolean getMove(){
		System.out.println(boxMove.isSelected());
		return boxMove.isSelected();
	}
	
	public boolean getAttack(){
		System.out.println(boxAttack.isSelected());
		return boxAttack.isSelected();
	}
}
