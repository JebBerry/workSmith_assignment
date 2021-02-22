package changeMachine;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class ChangeMachine {
	private static ArrayList<Integer> denominations;
	private static Scanner input;
	private static Integer amount;
	private static CoinCalculator coinCalculator;
	
	public static void main(String[] args) {
		coinCalculator = new CoinCalculator();
		denominations = new ArrayList<Integer>();
		denominations.add(1);
		requestUserInput("initialize");
		while(!readAmount()) {
			//intentionally blank
		}
		while(!readDenominationInput(denominations)) {
			//intentionally blank
		}
		coinCalculator.calculateChange(denominations, amount);
		displayOutput(coinCalculator.getChangeAmount());
	}
	
	// asks the user for input 
	private static void requestUserInput(String type) {
		switch(type) {
			case "initialize": {
				System.out.println("This machine will return the fewest number of coins required to make a given amount.\n");
				break;
			}
			case "amount": {
				System.out.println("Please enter an amount of money with the format 0.00.");
				break;
			}
			case "denomination": {
				System.out.println("Please enter your desired coin denomination as a whole number.");
				System.out.println("When you have input your desired denominations enter 0.");
				break;
			}
			case "failure": {
				System.out.println("Sorry that input was misunderstood. Please try again.\n");
				break;
			}
		}
	}
	
	// displays the breakdown to the user
	private static void displayOutput (TreeMap<Integer, Integer> change) {
		String lineOutput = "Denomination: %.2f -- Amount: %d";
		for (Integer denomination : change.keySet()) {
			System.out.println(String.format(lineOutput, denomination/100.00, change.get(denomination)));
		}
	}
	
	// prompts the user for denomination input
	private static boolean readDenominationInput(ArrayList<Integer> denominations) {
		requestUserInput("denomination");
		input = new Scanner(System.in);
		Integer value = -1;
		while (!value.equals(0)) {
			try {
				value = Integer.valueOf(input.nextInt());
			} catch (Throwable t) {
				requestUserInput("failure");
				return false;
			}
			if (!value.equals(0) && value >= 1) {
				if (!denominations.contains(value))
				denominations.add(value);
			}
		}
		return true;
	}
	
	// prompts the user for a total amount
	private static boolean readAmount() {
		requestUserInput("amount");
		input = new Scanner(System.in);
		float floatValue;
		Integer value;
		try {
			floatValue = input.nextFloat();
			floatValue = floatValue * 100;
			value = Integer.valueOf(Math.round(floatValue));
		} catch (Throwable t) {
			requestUserInput("failure");
			return false;
		}
		amount = value;
		return true;
	}
}
