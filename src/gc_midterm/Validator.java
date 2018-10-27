package gc_midterm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {

	/**
	 * Get any valid integer.
	 */
	public static int getInt(Scanner scnr, String prompt) {
		// This approach uses exception handling.
		System.out.print(prompt);
		try {
			int num = scnr.nextInt();
			while (num < 1 || num > 7) {
				System.out.println("Please input valid number selection 1 - 7");
				num = scnr.nextInt();
			}
			scnr.nextLine();
			return num;
		} catch (InputMismatchException e) {
			System.out.println("Enter a whole number, using digits.");
			scnr.nextLine();
			return getInt(scnr, prompt);
		}
	}

	/**
	 * Get a string of input from the user that must match the given regex.
	 */
	public static String getStringMatchingRegex(Scanner scnr) {
		boolean isValid = false;
		String input;
		String regex = "[A-Za-z0-9]";
		String regexTwo = "[A-Za-z0-9 ]";
		do {
			input = scnr.nextLine();

			if (input.matches(regex) || input.matches(regexTwo)) {
				isValid = true;
			} else {
				System.out.println("Input Error, please try again: ");
				isValid = false;
			}

		} while (!isValid);
		return input;
	}

}
