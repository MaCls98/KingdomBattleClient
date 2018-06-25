package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import controller.Controller;

public class JDialogIntro extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	private JLabel txtIntro;
	private ImageIcon loading = new ImageIcon(getClass().getResource("/img/loading.gif"));
	private ImageIcon axe = new ImageIcon(getClass().getResource("/img/icon.png"));
	
	public JDialogIntro(Controller controller) {
		setLayout(new BorderLayout());
		setSize(750, 300);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		setBackground(Color.WHITE);
		
		lblTitle = new JLabel("Esperando por nuevos jugadores");
		lblTitle.setFont(new Font("Arial", Font.ITALIC, 30));
		lblTitle.setIcon(loading);
		lblTitle.setBackground(Color.WHITE);
		add(lblTitle, BorderLayout.NORTH);
		
		txtIntro = new JLabel();
		txtIntro.setText("<html><body>Bienvenido, tu y 4 caballeros mas lucharan por la mano de la princesa <br> el ganador se llevara el maximo honor... <br> Tienes 10 monedas, cada compra cuesta 10 monedas, decide cuidadosamente </body></html>");
		txtIntro.setFont(new Font("Arial", Font.BOLD, 20));
		txtIntro.setHorizontalAlignment(JLabel.CENTER);
		txtIntro.setBackground(Color.white);
		add(txtIntro, BorderLayout.CENTER);
		
		JLabel lblIcon = new JLabel(axe);
		lblIcon.setBackground(Color.WHITE);
		add(lblIcon, BorderLayout.LINE_END);
	}
}
