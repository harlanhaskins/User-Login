package com.notsoaverage.student;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.*;

import com.notsoaverage.colormanager.*;

public class StudentAppGUI extends JFrame implements EventListener,
FocusListener, ActionListener {
	static JPanel topPanel = new JPanel();
	static Color color1 = new Color(230, 230, 230);
	static Color color2 = new Color(175, 175, 175);
	static GradientPanel middlePanel = new GradientPanel(color1, color2);
	static JPanel bottomPanel = new JPanel();
	JButton searchButton = new JButton("");
	JButton addButton = new JButton("Add");
	JButton modifyButton = new JButton("Modify");
	JButton averageButton = new JButton("Averages");
	JButton firstButton = new JButton("<<");
	JButton previousButton = new JButton("<");
	JButton nextButton = new JButton(">");
	JButton lastButton = new JButton(">>");
	JButton sortButton = new JButton("Sort");
	JButton colorsButton = new JButton("<html><center>Change<br>Colors</center></html>");
	JLabel highScoreLabel = new JLabel();
	JLabel searchLabel = new JLabel("Search:");
	JLabel nameLabel = new JLabel("Name: ");
	JLabel test1Label = new JLabel("Test 1: ");
	JLabel test2Label = new JLabel("Test 2: ");
	JLabel test3Label = new JLabel("Test 3: ");
	JLabel averageLabel = new JLabel("Average: ");
	JLabel countLabel = new JLabel("Count: ");
	JLabel indexLabel = new JLabel("Index: ");
	JTextField[] fields = new JTextField[8];
	static StudentAppGUI window = new StudentAppGUI();
	int searchCounter = 0;
	int currentStudent = 1;

	public static void showApp() {
		window.setTitle("Test Scores");
		window.setLayout(new BorderLayout());
		window.setContents(window);
		window.setSize(395, 250);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window.setResizable(false); // When I use absolute positions for things, I don't like having resizable windows.
		window.setVisible(true);
	}

	public void setContents(JFrame window) {
		StudentManager.getList().add(new Student());
		topPanel.setBackground(middlePanel.getColor(1));
		bottomPanel.setBackground(middlePanel.getColor(2));
		middlePanel.setLayout(null); // Default layout doesn't allow for absolute positioning.
		searchButton.setLayout(null);
		nameLabel.setBounds(27, 13, 100, 20);
		searchLabel.setBounds(8, 0, 70, 20);
		test1Label.setBounds(27, 41, 100, 20);
		test2Label.setBounds(27, 69, 100, 20);
		test3Label.setBounds(27, 97, 100, 20);
		averageLabel.setBounds(204, 13, 100, 20);
		countLabel.setBounds(204, 41, 100, 20);
		indexLabel.setBounds(204, 69, 100, 20);
		searchButton.setBounds(194, 98, 60, 20);
		for (int i = 1; i <= 8; i++) {
			fields[i - 1] = new JTextField();
			if (i <= 4 && i != 8) {
				fields[i - 1].setBounds(78, (i * 28) - 14, 105, 20);
				fields[i - 1].addFocusListener(this);
			} else
				fields[i - 1].setBounds(260, ((i - 4) * 28) - 14, 105, 20);
			if (i > 4)
				fields[i - 1].setEditable(false);
			middlePanel.add(fields[i - 1]);
		}
		fields[5].setText("" + currentStudent);
		fields[6].setText("" + currentStudent);
		addButton.setToolTipText("Adds a student and moves you to the last student.");
		modifyButton.setToolTipText("Enables all of the text fields so you can edit them.");
		sortButton.setToolTipText("Sorts all of the students by name, average, or highest test score.");
		colorsButton.setToolTipText("Changes the gradient colors in the background.");
		averageButton.setToolTipText("Shows who has the highest average and the class's total average.");
		firstButton.setToolTipText("Moves you to the first student in the class.");
		previousButton.setToolTipText("Moves you backward one student.");
		nextButton.setToolTipText("Moves you forward one student.");
		lastButton.setToolTipText("Moves you to the first student in the class.");
		searchButton.add(searchLabel);
		addButton.addActionListener(this);
		modifyButton.addActionListener(this);
		averageButton.addActionListener(this);
		colorsButton.addActionListener(this);
		sortButton.addActionListener(this);
		firstButton.addActionListener(this);
		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		lastButton.addActionListener(this);
		searchButton.addActionListener(this);
		averageButton.setLayout(new BorderLayout());
		colorsButton.setLayout(new BorderLayout());
		addButton.setPreferredSize(new Dimension(60, 40));
		modifyButton.setPreferredSize(new Dimension(71, 40));
		colorsButton.setPreferredSize(new Dimension(77, 40));
		sortButton.setPreferredSize(new Dimension(58, 40));
		averageButton.setPreferredSize(new Dimension(88, 40));
		firstButton.setPreferredSize(new Dimension(48, 30));
		previousButton.setPreferredSize(new Dimension(42, 30));
		nextButton.setPreferredSize(new Dimension(42, 30));
		lastButton.setPreferredSize(new Dimension(48, 30));
		previousButton.setEnabled(false);
		firstButton.setEnabled(false);
		nextButton.setEnabled(false);
		lastButton.setEnabled(false);
		searchButton.setEnabled(false);
		topPanel.add(addButton);
		topPanel.add(modifyButton);
		topPanel.add(colorsButton);
		topPanel.add(sortButton);
		topPanel.add(averageButton);
		middlePanel.add(nameLabel);
		middlePanel.add(test1Label);
		middlePanel.add(test2Label);
		middlePanel.add(test3Label);
		middlePanel.add(averageLabel);
		middlePanel.add(countLabel);
		middlePanel.add(indexLabel);
		middlePanel.add(searchButton);
		bottomPanel.add(firstButton);
		bottomPanel.add(previousButton);
		bottomPanel.add(nextButton);
		bottomPanel.add(lastButton);
		this.add(topPanel, BorderLayout.NORTH);
		this.add(middlePanel);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			StudentManager.getList().add(new Student());
			currentStudent = StudentManager.getList().size();
			searchButton.setEnabled(true);
			for (int i = 0; i <= 3; i++) {
				fields[i].setEditable(true);
				fields[i].setText("");
			}
			fields[7].setEditable(true);
			fields[4].setText("");
			fields[5].setText("" + currentStudent);
			fields[6].setText("" + currentStudent);
			navigationEnabled();
		} else if (e.getSource() == modifyButton) {
			for (int i = 0; i <= 3; i++) {
				fields[i].setEditable(true);
			}
		} else if (e.getSource() == previousButton) {
			for (int i = 0; i <= 3; i++) {
				fields[i].setEditable(false);
			}
			if (currentStudent > 1) {
				currentStudent--;
				fields[0].setText("" + StudentManager.getList().get(currentStudent - 1).getName());
				fields[1].setText("" + StudentManager.getList().get(currentStudent - 1).getScore(1));
				fields[2].setText("" + StudentManager.getList().get(currentStudent - 1).getScore(2));
				fields[3].setText("" + StudentManager.getList().get(currentStudent - 1).getScore(3));
				fields[4].setText("" + StudentManager.getList().get(currentStudent - 1).getAverage());
				fields[6].setText("" + (currentStudent));
				navigationEnabled();
			} else if (e.getSource() == firstButton) {
				currentStudent = 1;
				for (int i = 0; i <= 3; i++)
					fields[i].setEditable(false);
				fields[0].setText("" + StudentManager.getList().get(0).getName());
				fields[1].setText("" + StudentManager.getList().get(0).getScore(1));
				fields[2].setText("" + StudentManager.getList().get(0).getScore(2));
				fields[3].setText("" + StudentManager.getList().get(0).getScore(3));
				fields[4].setText("" + StudentManager.getList().get(0).getAverage());
				fields[6].setText("" + 1);
				navigationEnabled();
			}
		} else if (e.getSource() == nextButton) {
			for (int i = 0; i <= 3; i++)
				fields[i].setEditable(false);
			if (currentStudent < StudentManager.getList().size()) {
				currentStudent++;
				fields[0].setText("" + StudentManager.getList().get(currentStudent - 1).getName());
				fields[1].setText("" + StudentManager.getList().get(currentStudent - 1).getScore(1));
				fields[2].setText("" + StudentManager.getList().get(currentStudent - 1).getScore(2));
				fields[3].setText("" + StudentManager.getList().get(currentStudent - 1).getScore(3));
				fields[4].setText("" + StudentManager.getList().get(currentStudent - 1).getAverage());
				fields[6].setText("" + (currentStudent));
				navigationEnabled();
			}
		} else if (e.getSource() == lastButton) {
			for (int i = 0; i <= 3; i++)
				fields[i].setEditable(false);
			currentStudent = StudentManager.getList().size();
			fields[0].setText("" + StudentManager.getList().get(StudentManager.getList().size() - 1).getName());
			fields[1].setText("" + StudentManager.getList().get(StudentManager.getList().size() - 1).getScore(1));
			fields[2].setText("" + StudentManager.getList().get(StudentManager.getList().size() - 1).getScore(2));
			fields[3].setText("" + StudentManager.getList().get(StudentManager.getList().size() - 1).getScore(3));
			fields[4].setText("" + StudentManager.getList().get(StudentManager.getList().size() - 1).getAverage());
			fields[6].setText("" + currentStudent);
			navigationEnabled();
		} else if (e.getSource() == sortButton) {
			if (!fields[0].getText().equals("") || StudentManager.getList().size() > 1) {
				studentSorter();
				System.out.println("Sorted.");
			} else
				JOptionPane.showMessageDialog(window, "No students entered.");
		} else if (e.getSource() == averageButton) {
			studentAverageFinder();
			ColorSetter.setParentComponents(window, middlePanel);
			ColorSetter.colorSetterWindow();
		} else if (e.getSource() == searchButton) {
			boolean studentFound = false;
			int locationOfStudent = 0;
			for (int i = 0; i < StudentManager.getList().size(); i++) {
				if (fields[7].getText().equals(StudentManager.getList().get(i).getName())) {
					studentFound = true;
					locationOfStudent = i;
				}
			}
			if (studentFound == false)
				JOptionPane.showMessageDialog(window,
						"Student not found. Please try again.");
			if (studentFound == true) {
				currentStudent = locationOfStudent + 1;
				fields[0].setText("" + StudentManager.getList().get(currentStudent - 1).getName());
				fields[1].setText("" + StudentManager.getList().get(currentStudent - 1).getScore(1));
				fields[2].setText("" + StudentManager.getList().get(currentStudent - 1).getScore(2));
				fields[3].setText("" + StudentManager.getList().get(currentStudent - 1).getScore(3));
				fields[4].setText("" + StudentManager.getList().get(currentStudent - 1).getAverage());
				fields[6].setText("" + (currentStudent - 1));
				fields[7].setText("");
				navigationEnabled();
			}
		}
	}

	public void focusLost(FocusEvent e) {
		if (e.getSource() == fields[0]) {
			if (fields[0].getText().equals(""))
				JOptionPane.showMessageDialog(window,
						"Name field cannot be empty.", null,
						JOptionPane.ERROR_MESSAGE);
			else {
				StudentManager.getList().get(currentStudent - 1).setName(fields[0].getText());
				fields[0].setEditable(false);
			}
		} else if (e.getSource() == fields[1]) {
			try {
				fields[1].setEditable(false);
				if (Integer.parseInt(fields[1].getText()) > 100) {
					fields[1].setText("100");
					StudentManager.getList().get(currentStudent - 1).setScore(1, 100);
				} else if (Integer.parseInt(fields[1].getText()) < 0) {
					fields[1].setText("0");
					StudentManager.getList().get(currentStudent - 1).setScore(1, 0);
				} else {
					StudentManager.getList().get(currentStudent - 1).setScore(1, Integer.parseInt(fields[1].getText()));
					fields[4].setText("" + StudentManager.getList().get(currentStudent - 1).getAverage());
				}
			} catch (NumberFormatException ex) {
				fields[1].setText("0");
				fields[1].setEditable(false);
				fields[4].setText(""
						+ StudentManager.getList().get(currentStudent - 1).getAverage());
			}
		} else if (e.getSource() == fields[2]) {
			try {
				fields[2].setEditable(false);
				if (Integer.parseInt(fields[2].getText()) > 100) {
					fields[2].setText("100");
					StudentManager.getList().get(currentStudent - 1).setScore(2, 100);
				} else if (Integer.parseInt(fields[2].getText()) < 0) {
					fields[2].setText("0");
					StudentManager.getList().get(currentStudent - 1).setScore(2, 0);
				} else {
					StudentManager.getList().get(currentStudent - 1).setScore(2,
							Integer.parseInt(fields[2].getText()));
					fields[4].setText(""
							+ StudentManager.getList().get(currentStudent - 1).getAverage());
				}
			} catch (NumberFormatException ex) {
				fields[2].setText("0");
				fields[2].setEditable(false);
				fields[4].setText(""
						+ StudentManager.getList().get(currentStudent - 1).getAverage());
			}
		} else if (e.getSource() == fields[3]) {
			try {
				fields[3].setEditable(false);
				if (Integer.parseInt(fields[3].getText()) > 100) {
					fields[3].setText("100");
					StudentManager.getList().get(currentStudent - 1).setScore(3, 100);
				} else if (Integer.parseInt(fields[3].getText()) < 0) {
					fields[3].setText("0");
					StudentManager.getList().get(currentStudent - 1).setScore(3, 0);
				} else {
					StudentManager.getList().get(currentStudent - 1).setScore(3,
							Integer.parseInt(fields[3].getText()));
					fields[4].setText(""
							+ StudentManager.getList().get(currentStudent - 1).getAverage());
				}
			} catch (NumberFormatException ex) {
				fields[3].setText("0");
				fields[3].setEditable(false);
				fields[4].setText(""
						+ StudentManager.getList().get(currentStudent - 1).getAverage());
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == fields[1] && fields[1].getText().equals("0"))
			fields[1].setText("");
		else if (e.getSource() == fields[2] && fields[2].getText().equals("0"))
			fields[2].setText("");
		else if (e.getSource() == fields[3] && fields[3].getText().equals("0"))
			fields[3].setText("");
	}

	public Student getHighest() {
		int highScore = 0;
		Student highScorer = StudentManager.getList().get(0);
		for (Student s : StudentManager.getList()) {
			if (s.getAverage() > highScore) {
				highScore = s.getAverage();
				highScorer = s;
			}
		}
		return highScorer;
	}

	public void studentSorter() {
		Object[] options = { "Name", "Average", "High Score" };
		int n = JOptionPane.showOptionDialog(window,
				"How would you like to sort the students?", "Sort",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		sortStudents(n);
		JTextArea textArea = new JTextArea(3, 3);
		String sortedList = "";
		for (Student s : StudentManager.getList())
			sortedList += s.getName() + "\n";
		textArea.setText(sortedList.substring(0, sortedList.length() - 1));
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		JOptionPane.showMessageDialog(window, scrollPane);
		currentStudent = 1;
		for (int i = 0; i <= 3; i++)
			fields[i].setEditable(false);
		fields[0].setText("" + StudentManager.getList().get(0).getName());
		fields[1].setText("" + StudentManager.getList().get(0).getScore(1));
		fields[2].setText("" + StudentManager.getList().get(0).getScore(2));
		fields[3].setText("" + StudentManager.getList().get(0).getScore(3));
		fields[4].setText("" + StudentManager.getList().get(0).getAverage());
		fields[6].setText("" + currentStudent);
		navigationEnabled();
	}

	public void studentAverageFinder() {
		if (getHighest().getAverage() == 0)
			JOptionPane.showMessageDialog(window, "No students entered.");
		else {
			int averageSum = 0, classAverage = 0;
			for (Student s : StudentManager.getList())
				averageSum += s.getAverage();
			classAverage = (averageSum / StudentManager.getList().size());
			JOptionPane.showMessageDialog(window, getHighest().getName()
					+ " has the highest average, with " + getHighest().getAverage()
					+ ".\nThe class's average is " + classAverage + ".");
		}
	}

	public void navigationEnabled() {
		if (currentStudent == StudentManager.getList().size()) {
			nextButton.setEnabled(false);
			lastButton.setEnabled(false);
			previousButton.setEnabled(true);
			firstButton.setEnabled(true);
		} else if (currentStudent == 1) {
			previousButton.setEnabled(false);
			firstButton.setEnabled(false);
			nextButton.setEnabled(true);
			lastButton.setEnabled(true);
		} else {
			nextButton.setEnabled(true);
			lastButton.setEnabled(true);
			previousButton.setEnabled(true);
			firstButton.setEnabled(true);
		}
	}

	public static void setAndShowPanelColors(Color color1, Color color2) {
		middlePanel.setColors(color1, color2);
		topPanel.setBackground(color1);
		bottomPanel.setBackground(color2);
		middlePanel.repaint();
	}
	
	public void sortStudents(int n) {
		if (n == 0)
			Collections.sort(StudentManager.getList(), StudentComparison.RANK_BY_NAME);
		else if (n == 1)
			Collections.sort(StudentManager.getList(), StudentComparison.RANK_BY_AVERAGE);
		else
			Collections.sort(StudentManager.getList(), StudentComparison.RANK_BY_HIGH_TEST_SCORE);
	}

	public static StudentAppGUI getWindow() {
		return window;
	}
}