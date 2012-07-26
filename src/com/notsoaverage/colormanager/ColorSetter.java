package com.notsoaverage.colormanager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.notsoaverage.student.*;
import com.notsoaverage.password.*;

@SuppressWarnings({"serial"})
public class ColorSetter extends JFrame implements ActionListener {
	static ColorSetter win;
	static JFrame parentWindow;
	JPanel southPanel;
	GradientPanel mainPanel;
	static GradientPanel gradientPanel;
	Color color1;
	Color color2;
	JTextField[] color1Fields, color2Fields;
	JButton color1Button, color2Button, okButton;
	JLabel setLabel, redLabel, blueLabel, greenLabel;

	public static void setParentComponents(JFrame window, GradientPanel panel) {
		//Get the components a)from which to get color values, and b) to which to open relative.
		gradientPanel = panel;
		parentWindow = window;
	}

	public static void colorSetterWindow() {
		//Set window's properties
		win = new ColorSetter();
		win.setSize(230, 170);
		win.setLayout(new BorderLayout());
		win.setResizable(false);
		win.setLocationRelativeTo(parentWindow);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setContents(win);
		win.setVisible(true);
	}

	public void setContents(JFrame win) {
		//Initializes and sets all visible components of the window
		color1 = gradientPanel.getColor(1);
		color2 = gradientPanel.getColor(2);
		color1Fields = new JTextField[3];
		color2Fields = new JTextField[3];
		color1Button = new JButton("Set");
		color2Button = new JButton("Set");
		okButton = new JButton("Ok");
		setLabel = new JLabel("Set your colors");
		redLabel = new JLabel("Red");
		greenLabel = new JLabel("Green");
		blueLabel = new JLabel("Blue");
		mainPanel = new GradientPanel(color1, color2);
		southPanel = new JPanel();
		southPanel.setBackground(color2);
		mainPanel.setLayout(null);
		mainPanel.setBorder(BorderFactory.createTitledBorder("Set your background colors:"));
		for (int i = 0; i < 3; i++) {
			color1Fields[i] = new JTextField();
			color2Fields[i] = new JTextField();
			mainPanel.add(color1Fields[i]);
			mainPanel.add(color2Fields[i]);
		}
		redLabel.setBounds(27, 21, 40, 21);
		greenLabel.setBounds(62, 21, 40, 21);
		blueLabel.setBounds(109, 21, 40, 21);
		color1Fields[0].setBounds(20, 41, 40, 21);
		color1Fields[1].setBounds(61, 41, 40, 21);
		color1Fields[2].setBounds(102, 41, 40, 21);
		color2Fields[0].setBounds(20, 69, 40, 21);
		color2Fields[1].setBounds(61, 69, 40, 21);
		color2Fields[2].setBounds(102, 69, 40, 21);
		color1Button.setBounds(150, 41, 53, 20);
		color2Button.setBounds(150, 69, 53, 20);
		okButton.setPreferredSize(new Dimension(50, 25));
		okButton.setEnabled(false);
		color1Button.addActionListener(this);
		color2Button.addActionListener(this);
		okButton.addActionListener(this);
		//Add these components
		mainPanel.add(color1Button);
		mainPanel.add(color2Button);
		southPanel.add(okButton);
		mainPanel.add(setLabel);
		mainPanel.add(redLabel);
		mainPanel.add(greenLabel);
		mainPanel.add(blueLabel);
		this.add(mainPanel);
		this.add(southPanel, BorderLayout.SOUTH);
	}

	public Color getColor1() {
		return color1;
	}

	public Color getColor2() {
		return color2;
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		//Sets color1 (the lower color in the gradient)
		if (a.getSource() == color1Button) {
			try {
				color1 = new Color(Integer.parseInt(color1Fields[0].getText()), Integer.parseInt(color1Fields[1].getText()), Integer.parseInt(color1Fields[2].getText()));
				mainPanel.setColor(1, color1);
				mainPanel.repaint();
			}
			catch(IllegalArgumentException e) {
				illegalArgumentHandler(1);
			}
		}
		if (a.getSource() == color2Button) {
			//Sets color2 (the lower color in the gradient)
			try {
				color2 = new Color(Integer.parseInt(color2Fields[0].getText()), Integer.parseInt(color2Fields[1].getText()), Integer.parseInt(color2Fields[2].getText()));
				mainPanel.setColor(2, color2);
				mainPanel.repaint();
				southPanel.setBackground(color2);
				okButton.setEnabled(true);
			}

			catch(IllegalArgumentException e) {
				illegalArgumentHandler(2);
			}
		}
		if (a.getSource() == okButton) {
			try {
				//Method to set colors and repaint must be present in the parent class.
				LoginWindow.setAndShowPanelColors(color1, color2);
				StudentAppGUI.setAndShowPanelColors(color1, color2);
				win.dispose();
			}

			catch(IllegalArgumentException e) {
				illegalArgumentHandler(1);
				illegalArgumentHandler(2);
			}
		}
	}
	public void illegalArgumentHandler(int x) {
		JOptionPane.showMessageDialog(win, "Color values must be numbers from 0-255.", "Invalid color values", JOptionPane.ERROR_MESSAGE);
		try {
			for (int i = 0; i <= 2; i++) {
				if (x == 1) {
					if (color1Fields[i].getText().equals(""))
						color1Fields[i].setText("0");
					if (Integer.parseInt(color1Fields[i].getText()) < 0)
						color1Fields[i].setText("0");
					if (Integer.parseInt(color1Fields[i].getText()) > 255)
						color1Fields[i].setText("255");
				}
				else {
					if (color2Fields[i].getText().equals(""))
						color2Fields[i].setText("0");
					if (Integer.parseInt(color2Fields[i].getText()) < 0)
						color2Fields[i].setText("0");
					if (Integer.parseInt(color2Fields[i].getText()) > 255)
						color2Fields[i].setText("255");
					else
						color2Fields[i].setText("0");
				}
			}
		}
		catch (IllegalArgumentException e) {
			for (int i = 0; i <= 2; i++) {
				if (x == 1)
					color1Fields[i].setText("0");
				else
					color2Fields[i].setText("0");
			}
		}
	}
}