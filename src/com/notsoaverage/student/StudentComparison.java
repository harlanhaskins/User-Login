package com.notsoaverage.student;

import java.util.Comparator;

public class StudentComparison {


	static Comparator<Student> RANK_BY_NAME =  new Comparator<Student>() {
		public int compare(Student s1, Student s2) {
			return s1.getName().compareToIgnoreCase(s2.getName());
		}
	};

	static Comparator<Student> RANK_BY_AVERAGE =  new Comparator<Student>() {
		public int compare(Student s1, Student s2) {
			int result = 0;
			if (s1.getAverage() > s2.getAverage())
				result = -1;
			else if (s2.getAverage() > s1.getAverage())
				result = 1;
			return result;
		}
	};

	static Comparator<Student> RANK_BY_HIGH_TEST_SCORE =  new Comparator<Student>() {
		public int compare(Student s1, Student s2) {
			int result = 0;
			if (s1.getHighScore() > s2.getHighScore())
				result = -1;
			else if (s2.getHighScore() > s1.getHighScore())
				result = 1;
			return result;
		}
	};
}
