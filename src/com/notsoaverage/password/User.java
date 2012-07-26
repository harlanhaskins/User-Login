package com.notsoaverage.password;

public class User {
	int numberOfUses;
	String username, password;

	public User() {
		numberOfUses = 0;
		password = "";
		username = "";
	}

	public void setUserName(String nm) {
		username = nm;
	}

	public String getUserName() {
		return username;
	}

	public void setPassword(String s) {
		PasswordChecker passwordChecker = new PasswordChecker();
		if (passwordChecker.isLongEnough(s) == false)
			ErrorDialog.tooShort();
		else if (passwordChecker.hasNumber(s) == false)
			ErrorDialog.needsNumberDialog();
		else {
			password = s;
			UserManager.getPasswordList().add(s);
		}
	}

	public void addUses() {
		numberOfUses++;
	}

	public void setUses(int i) {
		numberOfUses = i;
	}
	
	public int getUses() {
		return numberOfUses;
	}

	public String getPassword() {
		return password;
	}

	public String toString() {
		return "User: " + getUserName() + "\nUses: "
				+ getUses() + "\nPassword: " + getPassword() + "\n\n";
	}
}