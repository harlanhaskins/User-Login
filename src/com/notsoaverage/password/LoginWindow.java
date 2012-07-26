package com.notsoaverage.password;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.notsoaverage.colormanager.*;

public class LoginWindow extends JFrame implements ActionListener {
	static LoginWindow window = new LoginWindow();
	private JButton addButton = new JButton("Add User");
	private JButton changePasswordButton = new JButton("Change Password");
	private JButton loginButton = new JButton("Log In");
	private JButton colorsButton = new JButton("Colors");
	static JComboBox usernameBox = new JComboBox();
	static JPasswordField passwordField = new JPasswordField();
	static JPanel buttonPanel = new JPanel();
	static GradientPanel entryPanel = new GradientPanel(230, 230, 230, 175, 175, 175);
	public static void showWindow() {
		window.setTitle("Log In");
		window.setLayout(new BorderLayout());
		window.setContents(window);
		window.setSize(320, 195);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}

	private void setContents(LoginWindow window) {
		JLabel usernameLabel = new JLabel("Username:");
		JLabel passwordLabel = new JLabel("Password:");
		usernameBox.setEditable(false);
		passwordField.setEditable(false);
		entryPanel.setLayout(null);
		entryPanel.setBorder(BorderFactory.createTitledBorder("Enter your login information:"));
		buttonPanel.setBackground(entryPanel.getColor(2));
		usernameLabel.setBounds(20, 27, 100, 20);
		passwordLabel.setBounds(20, 57, 100, 20);
		usernameBox.setBounds(90, 27, 200, 20);
		passwordField.setBounds(90, 57, 200, 20);
		loginButton.setBounds(130, 86, 68, 30);
		addButton.setPreferredSize(new Dimension(86, 25));
		changePasswordButton.setPreferredSize(new Dimension(138, 25));
		colorsButton.setPreferredSize(new Dimension(71, 25));
		addButton.addActionListener(this);
		colorsButton.addActionListener(this);
		changePasswordButton.addActionListener(this);
		passwordField.addActionListener(this);
		loginButton.addActionListener(this);
		loginButton.setEnabled(false);
		changePasswordButton.setEnabled(false);
		buttonPanel.add(addButton);
		buttonPanel.add(changePasswordButton);
		buttonPanel.add(colorsButton);
		entryPanel.add(usernameLabel);
		entryPanel.add(passwordLabel);
		entryPanel.add(usernameBox);
		entryPanel.add(passwordField);
		entryPanel.add(loginButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(entryPanel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			UserDialogs.addUser();
			loginButton.setEnabled(true);
			changePasswordButton.setEnabled(true);
		}
		else if (e.getSource() == changePasswordButton)
			UserDialogs.changePassword();
		else if (e.getSource() == loginButton || e.getSource() == passwordField)
			Login.checkCredentials();
		else if (e.getSource() == colorsButton) {
			ColorSetter.setParentComponents(window, entryPanel);
			ColorSetter.colorSetterWindow();
		}
	}

	public static void setAndShowPanelColors(Color color1, Color color2) {
		entryPanel.setColors(color1, color2);
		buttonPanel.setBackground(color2);
		entryPanel.repaint();
	}
}