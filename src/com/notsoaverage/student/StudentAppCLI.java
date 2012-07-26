package com.notsoaverage.student;

import java.util.*;

public class StudentAppCLI {
	static Scanner input = new Scanner(System.in);

	public static void initialMenu() {
		System.out.println("\nStudent Manager\n===============\n\nPlease type your selection below.\n\n1: Add a student.\n2: Modify a student.\n3: Print students.\n4: Sort students.\nAnything else: Exit");
		try {
			int selection = input.nextInt();
			if (selection == 1)
				addMenu();
			else if (selection == 2)
				modifyMenu();
			else if (selection == 3)
				printMenu();
			else if (selection == 4)
				sortMenu();
			else {
				System.out.print("\n(c) Harlan Haskins, 2012");
				System.exit(0);
			}
		}
		catch (InputMismatchException e) {
			System.out.print("\n(c) Harlan Haskins, 2012");
			System.exit(0);
		}
	}

	public static void addMenu() {
		StudentManager.getList().add(new Student());
		input.nextLine();
		int lastStudent = StudentManager.getList().size() - 1;
		System.out.print("What's this student's name? ");
		String name = input.nextLine();
		StudentManager.getList().get(lastStudent).setName(name);
		for(int i = 1; i < 4; i++) {
			System.out.print("What's this student's test score? (#" + i + ") ");
			StudentManager.getList().get(lastStudent).setScore(i, input.nextInt());
		}
		System.out.print("Student added.\n\n");
		initialMenu();
	}

	public static void modifyMenu() {

	}

	public static void printMenu() {
		System.out.println("What would you like to print?\n1: All students.\n2: An individual student.");
		printOptions(input.nextInt());
	}

	public static void printOptions(int i) {
		if (i == 1) {
			for (Student s : StudentManager.getList())
				System.out.print(s.toString() + "\n");
		}
		else if (i == 2) {
			System.out.print("Which student would you like to print?");
			String name = input.nextLine();
			int s = searchStudents(name);
			if (s == -1) {
				System.out.print("Student not found. Please try again.");
				printOptions(2);
			}
			else
				System.out.print(StudentManager.getList().get(s) + "\n\n");
		}
		initialMenu();
	}

	public static void sortMenu() {
		System.out.println("How would you like to sort the students?\n1: By name.\n2: By highest average.\n3: By highest individual test score.");
		int selection = input.nextInt();
		if (selection == 1)
			Collections.sort(StudentManager.getList(), StudentComparison.RANK_BY_NAME);
		else if (selection == 2)
			Collections.sort(StudentManager.getList(), StudentComparison.RANK_BY_AVERAGE);
		else if (selection == 3)
			Collections.sort(StudentManager.getList(), StudentComparison.RANK_BY_HIGH_TEST_SCORE);
		printOptions(1);
	}
	public static int searchStudents(String name)  {
		for (int o = 0; o < StudentManager.getList().size(); o++) {
			if (name.equals(StudentManager.getList().get(o).getName())) {
				return o;
			}
		}
		return -1;
	}
}
