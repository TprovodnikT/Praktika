package View;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DataBase.CreateDatabase;

public class AuthorizationWindow {
	static boolean isAuthorized=false;
	private JFrame frameAuthorization;
	private JPanel panel1;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField userInput;
	private JPasswordField passwordInput;
	private JButton authorizationAcceptButon;

	public AuthorizationWindow() {
		frameAuthorization = new JFrame("Autorizācija");
		userLabel = new JLabel();
		passwordLabel = new JLabel();
		userInput = new JTextField();
		passwordInput = new JPasswordField();
		authorizationAcceptButon = new JButton("Ieiet");
		
		authorizationAcceptButon.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
//				checkAuthorization();
				JOptionPane.showMessageDialog(frameAuthorization, "Nepareizs lietotājvārds un/vai parole");				
			}
		});

		userLabel.setText("Lietotājvārds");
		passwordLabel.setText("Parole");
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel1 = new JPanel();
		panel1.setLayout(gbl);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbl.setConstraints(userLabel, gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbl.setConstraints(userInput, gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbl.setConstraints(passwordLabel, gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.weightx = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbl.setConstraints(passwordInput, gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbl.setConstraints(authorizationAcceptButon, gbc);

		panel1.add(userLabel);
		panel1.add(userInput);
		panel1.add(passwordLabel);
		panel1.add(passwordInput);
		panel1.add(authorizationAcceptButon);

		// gbc.anchor=GridBagConstraints.CENTER;
		frameAuthorization.add(panel1);
		frameAuthorization.setPreferredSize(new Dimension(300, 100));
		frameAuthorization.setResizable(false);
		frameAuthorization.setLocationRelativeTo(null);
		frameAuthorization.pack();
		frameAuthorization.setVisible(true);
		frameAuthorization.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		CreateDatabase createDatabase = new CreateDatabase();
		createDatabase.insertFromDBIntoStaticList();
		
		new AuthorizationWindow();
		new DatabaseView().view();
	}

}
