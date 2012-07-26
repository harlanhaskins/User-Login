package com.notsoaverage.password;
import java.util.*;

public class UserManager {
	
	private static ArrayList<User> users = new ArrayList<User>(0);
	private static ArrayList<String> passwords = new ArrayList<String>(0);
	
	public void userManager() {
		PasswordChecker passwordChecker = new PasswordChecker();
		if (passwordChecker.isExpired() == true)
			ErrorDialog.isExpired();
	}
	
	public static void addPasswordToOldPasswordsList(String pw) {
		passwords.add(pw);
	}
	
	public static User selectedUser() {
		int user = 0;
		for (User u : users) {
			if (LoginWindow.usernameBox.getItemAt(user).equals(u.getUserName()) == false)
				user++;
		}
		return users.get(user);
	}
	
	public static ArrayList<User> getUserList() {
		return users;
	}
	
	public static ArrayList<String> getPasswordList() {
		return passwords;
	}
}
