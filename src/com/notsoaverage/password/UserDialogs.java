package com.notsoaverage.password;
import javax.swing.*;

public class UserDialogs {
	public static void addUser() {
		String name = JOptionPane.showInputDialog(null, "Please enter the new username: ");
		if ((name != null) && (name.length() > 0)) {
			UserManager.getUserList().add(new User());
			UserManager.getUserList().get(UserManager.getUserList().size() - 1).setUserName(name);
			LoginWindow.usernameBox.addItem(UserManager.getUserList().get(UserManager.getUserList().size() - 1).getUserName());
			LoginWindow.usernameBox.setSelectedIndex(UserManager.getUserList().size() - 1);
			newPassword();
			LoginWindow.usernameBox.setEditable(true);
			LoginWindow.passwordField.setEditable(true);
		}
		else
			return;
	}

	public static void changePassword() {
		String pwCheck = JOptionPane.showInputDialog(null, "Please enter the current user password: ");
		String pw = UserManager.selectedUser().getPassword();
		if (pwCheck.equals(pw))
			newPassword();
		else
			ErrorDialog.incorrectPassword();
	}

	public static void newPassword() {
		PasswordChecker passwordChecker = new PasswordChecker();
		String pw = JOptionPane.showInputDialog(null, "Please enter the new user password: ");
		String pwConfirmed = JOptionPane.showInputDialog(null, "Please confirm the new user password: ");
		if ((pwConfirmed != null) && (pwConfirmed.length() > 0)) {
			if (passwordChecker.isOldPassword(pwConfirmed) == true)
				ErrorDialog.cannotUseOldPassword();
			else if (pwConfirmed.equals(pw) == false)
				ErrorDialog.passwordsDoNotMatch();
			else {
				UserManager.selectedUser().setPassword(pwConfirmed);
				UserManager.selectedUser().setUses(0);
				for (User u : UserManager.getUserList())
					System.out.print(u.toString());
			}
		}
		else
			ErrorDialog.nothingEntered();
	}
}