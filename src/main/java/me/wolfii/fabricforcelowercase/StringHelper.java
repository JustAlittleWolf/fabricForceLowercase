package me.wolfii.fabricforcelowercase;


public class StringHelper {
	public static String firstArgumentToLowerCase(String input) {
		String[] args = input.split(" ", 2);
		return args[0].toLowerCase() + " " + args[1];
	}
}
