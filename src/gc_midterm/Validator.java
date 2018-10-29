package gc_midterm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {

	/**
	 * Get any valid integer.
	 */
	public static int getInt(Scanner scnr) {
		// Validate user menu choice.

		try {
			int num = scnr.nextInt();
			while (num < 1 || num > 8) {
				System.out.println("Please input valid number selection 1 - 8");
				num = scnr.nextInt();
			}
			scnr.nextLine();
			return num;
		} catch (InputMismatchException e) {
			System.out.println("Enter a whole number, using digits.");
			scnr.nextLine();
			return getInt(scnr);
		}
	}

	/**
	 * Get a string of input from the user that must match the given regex.
	 */
	public static String getStringNameRegex(Scanner scnr) {
		boolean isValid = false;
		String input;
		String regex = "[A-Za-z-.]{3,25}";
		String regexTwo = "[A-Za-z-. ]{3,25}";
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

	/**
	 * Get a string of input from the user that must match the given regex.
	 */
	public static String getStringTitleRegex(Scanner scnr) {
		boolean isValid = false;
		String input;
		String regex = "[A-Za-z0-9]{2,30}";
		String regexTwo = "[A-Za-z0-9:' ]{2,30}";
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

	/**
	 * Get a string of input from the user that must match the given regex.
	 */
	public static String getStringGenreRegex(Scanner scnr) {
		boolean isValid = false;
		String input;
		String regex = "[A-Za-z- ]{3,15}[, ]{*,4}";

		do {
			input = scnr.nextLine();

			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.out.println("Input error. There can only be up to 5 genres listed. Please try again: ");
				isValid = false;
			}

		} while (!isValid);
		return input;
	}

	/**
	 * Get any valid integer.
	 */
	public static int getChoice(Scanner scnr) {
		// Validate user menu choice.

		try {
			int num = scnr.nextInt();
			scnr.nextLine();
			return num;
		} catch (InputMismatchException e) {
			System.out.println("Enter a whole number, using digits.");
			scnr.nextLine();
			return getInt(scnr);
		}
	}

}
