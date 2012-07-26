//Name: Harlan Haskins
//Date: 3/13/12
//File: Student.java
//Purpose: Test Grade Stuff
package com.notsoaverage.student;

public class Student {

	//Instance variables. Each student object has a name and 3 scores
	String name;
	int test1, test2, test3;
	int average;
	public Student() {
		name = "";
		test1 = 0;
		test2 = 0;
		test3 = 0;
	}

	public void setName (String nm) {
		name = nm;
	}

	public void setScore(int i, int score) {
		if (i == 1)
			test1 = score;
		else if (i == 2)
			test2 = score;
		else if (i == 3)
			test3 = score;
	}

	public int getHighScore() {
		int highScore;
		highScore = test1;
		if (test2 > highScore)
			highScore = test2;
		if (test3 > highScore)
			highScore = test3;
		return highScore;
	}

	public String getName() {
		return name;
	}
	
	public int getAverage() {
		average = (int)Math.round((test1 + test2 + test3) / 3.0);
		return average;
	}

	public int getScore(int i) {
		if (i == 1)
			return test1;
		else if (i == 2)
			return test2;
		else if (i == 3)
			return test3;
		return test1;
	}

	public String toString() {
		String equals = getName().replaceAll(".", "=");
		return 	"" + getName() + "\n" + equals + "\nTest 1: " + getScore(1) + "\nTest 2: "
				+ getScore(2) + "\nTest 3: " + getScore(3) + "\nAverage: " + getAverage()
				+ "\nHigh Score: " + getHighScore() + "\n";
	}
}