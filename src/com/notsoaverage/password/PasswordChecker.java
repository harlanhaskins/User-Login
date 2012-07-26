package com.notsoaverage.password;

public class PasswordChecker {
	public boolean hasNumber(String s) {
		boolean hasNumber = false;
		for (char ch : s.toCharArray()) {
			if (Character.isDigit(ch) == true)
				hasNumber = true;
		}
		return hasNumber;
	}

	public boolean isLongEnough(String s) {
		if (s.length() >= 6)
			return true;
		else
			return false;
	}

	public boolean isExpired() {
		if (UserManager.selectedUser().getUses() >= 3)
			return true;
		else
			return false;
	}

	public boolean isOldPassword(String pw) {
		for (String s : UserManager.getPasswordList()) {
			if (pw.equals(s))
				return true;
		}
		return false;
	}
}