package com.notsoaverage.password;
import com.notsoaverage.student.*;

public class Login {
	public static void checkCredentials() {
		PasswordChecker passwordChecker = new PasswordChecker();
		String pw = UserManager.selectedUser().getPassword();
		String enteredPw = new String(LoginWindow.passwordField.getPassword());

		if (pw.equals(enteredPw)) {
			if (passwordChecker.isExpired() == true) {
				ErrorDialog.isExpired();
			} else {
				UserManager.selectedUser();
				if (UserManager.selectedUser().getUses() < 1)
					StudentAppGUI.showApp();
				else
					StudentAppGUI.getWindow().setVisible(true);
			}
			UserManager.selectedUser().addUses();
			System.out.println("Login successful.");
		}
		else {
			ErrorDialog.incorrectPassword();
		}
	}
}