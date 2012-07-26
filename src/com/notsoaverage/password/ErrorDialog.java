package com.notsoaverage.password;
import javax.swing.*;

public class ErrorDialog {

	public static void needsNumberDialog() {
		JOptionPane.showMessageDialog(LoginWindow.window, "Your password needs a number. Please add a number somewhere in the password.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
		UserDialogs.newPassword();
	}

	public static void tooShort() {
		JOptionPane.showMessageDialog(LoginWindow.window, "Your password is too short. Please try a password that's over 6 characters.", "Invalid Password",JOptionPane.ERROR_MESSAGE);
		UserDialogs.newPassword();
	}

	public static void isExpired() {
		JOptionPane.showMessageDialog(LoginWindow.window, "Your password is expired. You must create a new one after 3 uses.", "Password Expired", JOptionPane.ERROR_MESSAGE);
		UserDialogs.changePassword();
	}

	public static void passwordsDoNotMatch() {
		JOptionPane.showMessageDialog(LoginWindow.window, "Those two passwords don't match. Please enter them again.", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
		UserDialogs.newPassword();
	}

	public static void nothingEntered() {
		JOptionPane.showMessageDialog(LoginWindow.window, "Nothing was entered. Please enter a password.", "Empty Password", JOptionPane.ERROR_MESSAGE);
		UserDialogs.newPassword();
	}

	public static void incorrectPassword() {
		JOptionPane.showMessageDialog(LoginWindow.window, "The password you entered is incorrect. Please enter it again.", "Password Incorrect", JOptionPane.ERROR_MESSAGE);
	}

	public static void cannotUseOldPassword() {
		JOptionPane.showMessageDialog(LoginWindow.window, "You cannot use a password you've used before. Please enter another.", "Old Password", JOptionPane.ERROR_MESSAGE);
		UserDialogs.newPassword();
	}
}