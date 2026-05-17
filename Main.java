import java.util.Scanner;

public class Main {

	public static final String[] STATEMENTS = {
			"English is my native language.",
			"My parents graduated college.",
			"I have never wondered where my next meal will come from.",
			"I have no disabilities.",
			"My work and school holidays coincide with the religious holidays I celebrate.",
			"I studied the culture and history of my ancestors in elementary school.",
			"I have never been bullied or been made fun of based on something I could not change.",
			"I have never been stopped by law enforcement due to mere suspicion.",
			"I or my parents have inherited money or property.",
			"I am a US citizen.",
			"I feel safe going for a walk alone.",
			"I go by the same name I was given at birth.",
			"I am comfortable presenting my ID because it properly identifies me.",
			"My ancestors were not forced to come to the United States against their will.",
			"I have family or friends that can give me employment if I need it.",
			"I have never been told my natural hair looks dirty or unprofessional.",
			"I have gone to private school.",
			"I can easily find souvenirs with my name on them."
	};

	public static final int PTS_PER_ANSWER = 10;
	public static final int TOTAL_PTS_POSSIBLE =
			PTS_PER_ANSWER * STATEMENTS.length;

	public static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {

		// DECLARATION + INITIALIZATION
		Person p1 = new Person(
				"Amira",
				"she/her/hers",
				"I am a Syrian refugee.",
				40);

		Person p2 = new Person(
				"D'Andra",
				"she/her/hers",
				"I am an African-American trans woman.",
				-20);

		Person p3 = new Person(
				"Jennifer",
				"ze/hir/hirs",
				"I am a New Yorker.",
				140);

		Person p4 = new Person(
				"Pete",
				"he/him/his",
				"I am a guy from Pennsylvania.",
				200);

		Person self = new Person();

		Person[] people = {p1, p2, p3, p4, self};

		boolean done = false;
		int input;

		// WELCOME
		System.out.println("Welcome to the Privilege Calculator.\n");
		System.out.println("This exercise gives us a glimpse at how");
		System.out.println("fortunate we may have been in life.\n");

		fillInfo(self);

		do {

			System.out.println("\n~~~Main Menu~~~\n");

			System.out.println(
					"1. Take questionnaire to calculate privilege estimate.");

			System.out.println(
					"2. Check my estimate. (Defaults to "
							+ Person.DEFAULT_PRIVILEGE
							+ " if questionnaire has not been taken.)");

			System.out.println(
					"3. Compare my estimate with others.");

			System.out.println("4. Exit program.");

			System.out.print(
					"What would you like to do?\nEnter choice: ");

			input = keyboard.nextInt();
			System.out.println();

			switch (input) {

				case 1:

					self.setPrivilege(doPrivilegeQuestionnaire());

					System.out.println(
							"Your privilege estimate is: "
									+ self.getPrivilege());

					System.out.println(
							"\nReturning to main menu...\n");

					break;

				case 2:

					System.out.println(self);

					break;

				case 3:

					comparePeople(people);

					System.out.println(
							"\nReturning to main menu.\n");

					break;

				case 4:

					System.out.println("Exiting Program...\n");

					keyboard.close();

					done = true;

					break;

				default:

					System.out.println(
							"Invalid input. Returning to main menu...\n");

					break;
			}

		} while (!done);

		System.out.println(
				"Thank you for exploring your privilege!");
	}

	/***** PART 1: Compare People *****/
	public static void comparePeople(Person[] group) {

		Person self = group[group.length - 1];

		int compareResult;

		System.out.println("Summary of privilege estimates:\n");

		for (int i = 0; i < group.length - 1; i++) {

			compareResult = self.compareTo(group[i]);

			if (compareResult > 0) {

				System.out.println(
						"More privilege than "
								+ group[i].getName());

			} else if (compareResult == 0) {

				System.out.println(
						"Same privilege as "
								+ group[i].getName());

			} else {

				System.out.println(
						"Less privilege than "
								+ group[i].getName());
			}

			System.out.println(
					"Difference of " + compareResult + " points.\n");
		}
	}

	/***** PART 2: Fill User Info *****/
	public static void fillInfo(Person person) {

		String name;
		String pronouns;
		String background;

		keyboard.nextLine();

		System.out.print("What is your name? ");
		name = keyboard.nextLine();

		System.out.println("\nHello " + name + "!");

		System.out.println(
				"\nHere is a list of common examples of preferred pronouns:");

		System.out.println(
				"Gender Neutral/Nonbinary: they/them/theirs");

		System.out.println(
				"Feminine: she/her/hers");

		System.out.println(
				"Masculine: he/him/his");

		System.out.print(
				"What are your preferred pronouns? ");

		pronouns = keyboard.nextLine();

		System.out.println(
				"\nPlease share a small self-identifying statement "
						+ "about yourself and your background.");

		System.out.println(
				"For example: I'm a first-generation college student.");

		System.out.print(
				"Tell us about yourself: ");

		background = keyboard.nextLine();

		person.setName(name);
		person.setPronouns(pronouns);
		person.setBackground(background);
	}

	public static int doPrivilegeQuestionnaire() {

		boolean isValid;

		int choice;

		int privilegeEstimate = Person.DEFAULT_PRIVILEGE;

		System.out.println(
				"Please indicate whether the following statements "
						+ "are true or false.");

		System.out.println("Input 1 or 2 accordingly.\n");

		for (int i = 0; i < STATEMENTS.length; i++) {

			isValid = false;

			do {

				System.out.println(STATEMENTS[i]);

				System.out.print(
						"1. True\n2. False\n"
								+ "Enter the appropriate answer: ");

				choice = keyboard.nextInt();

				System.out.println();

				switch (choice) {

					case 1:

						privilegeEstimate += 10;

						isValid = true;

						break;

					case 2:

						privilegeEstimate -= 10;

						isValid = true;

						break;

					default:

						System.out.println(
								"Invalid choice. Please enter 1 or 2.");

						break;
				}

			} while (!isValid);
		}

		return privilegeEstimate;
	}
}