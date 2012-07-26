package com.notsoaverage.student;

import java.util.*;

public class StudentManager {
	private static List<Student> students = new ArrayList<Student>(0);
	
	public static List<Student> getList() {
		return students;
	}
}